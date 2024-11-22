/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author lamee
 */

import java.util.Scanner;

public class Menu {
    
    public static void main(String[] args) {
        
    Scanner scanner = new Scanner(System.in);

        Processor processor = new Processor();
        processor.LoadF("stop.txt", "dataset.csv"); 
        Query queryProcessor = new Query(processor.invertedindBST);
        Rank ranker = new Rank(processor.invertedindBST, processor.index, "");
        
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Search Engine Menu =====");
            System.out.println("1. Retrieve Term");
            System.out.println("2. Boolean Retrieval");
            System.out.println("3. Ranked Retrieval");
            System.out.println("4. Show Number of Indexed Documents");
            System.out.println("5. Show Number of Indexed Tokens");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                
                case 1: //Retrieve Term
                    System.out.println("Enter a Term to Retrieve: ");
                    String term = scanner.nextLine();
                    System.out.println("Choose how to retrieve it: ");
                    System.out.println("1. Using Index with Linked List");
                    System.out.println("2. Using Inverted Index with Linked List");
                    System.out.println("3. Using Inverted Index with BST");
                    System.out.print("Enter your choice: ");
                    int c1 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (c1) {
                        case 1:
                            processor.index.findWordIndex(term);
                            break;
                        case 2:
                            processor.invertedind.findWordInvertedIndex(term);
                            break;
                        case 3:
                            processor.invertedindBST.findWordInvertedIndexBST(term);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");                    }
                    
              
                break;
                
                
                case 2: // Boolean Retrieval
                System.out.print("Enter a Boolean query: ");
                String booleanQuery = scanner.nextLine();
                LinkedList<Integer> booleanResults;
                booleanResults = queryProcessor.BooleanQuery(booleanQuery);
                
                // Display results
                System.out.println("Documents matching the query:");
                processor.displayDocsByIds(booleanResults);
                break;


                case 3: // Ranked Retrieval
                    System.out.print("Enter a query for ranked retrieval: ");
                    String rankedQuery = scanner.nextLine();
                    ranker.RankQuery(rankedQuery);
                    System.out.println("Ranked results:");
                    ranker.displayRankedDocs();
                    break;

                case 4: // Indexed Documents
                    processor.index.displayDocs();
                    System.out.println();
                    System.out.println("Total Tokens:"+processor.TotalTokens);
                    System.out.println("Total Vocabulary:"+processor.TotalVocabulary);
                    break;

                case 5: // Indexed Tokens
                    System.out.println("Type L to use LinkedList or B to use BST");
                    char c4 = scanner.next().charAt(0);
                    scanner.nextLine(); // Consume newline character
                    switch (c4) {
                        case 'L':
                            processor.invertedind.displayInvertedIndex();
                            break;
                        case 'B':
                            processor.invertedindBST.displayInvertedIndex();
                            break;  
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    
                    break;

                case 6: // Exit
                    System.out.println("Exiting the menu. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    
                    
       }//end switch
            
     }       scanner.close();
     
}//end main
    
    
    
}//end Menu

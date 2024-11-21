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
        Query queryProcessor = new Query(processor.invertedind);
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
                    int c1 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    switch (c1) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");                    }
                    
              
                break;
                
                
                case 2: // Boolean Retrieval
                System.out.print("Enter a Boolean query: ");
                String booleanQuery = scanner.nextLine();
                LinkedList<Integer> booleanResults;

                // Check the type of query
                if (booleanQuery.contains("AND") && booleanQuery.contains("OR")) {
                    booleanResults = queryProcessor.MixedQuery(booleanQuery);
                } else if (booleanQuery.contains("AND")) {
                    booleanResults = queryProcessor.andQuery(booleanQuery);
                } else if (booleanQuery.contains("OR")) {
                    booleanResults = queryProcessor.ORQuery(booleanQuery);
                } else {
                    // Treat as a single-term AND query
                    booleanResults = queryProcessor.andQuery(booleanQuery);  }

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

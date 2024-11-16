/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ghade
 */
public class Processor {
LinkedList <String> stopW;
Index index;
InvertedIndex invertedind;
InvertedIndexBST invertedindBST;

public Processor()
{
    stopW = new LinkedList<>();
    index = new Index();
    invertedind = new InvertedIndex();
    invertedindBST = new InvertedIndexBST();
}


public void loadStopWords(String stopWordsFile) {
    Scanner scanner = null;
    try {
        scanner = new Scanner(new File(stopWordsFile));
        while (scanner.hasNextLine()) {
            String stopWord = scanner.nextLine().trim().toLowerCase();
            stopW.insert(stopWord);
        }
    } catch (IOException e) {
        System.out.println("Error reading the file: " + stopWordsFile);
        e.printStackTrace(); 
    } finally {
        if (scanner != null) {
            scanner.close();  
        }
    }
}

    private String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    public void preprocessContent(String content, LinkedList<String> WordsLL, int id) {
       
        String[] words = content.toLowerCase().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = removePunctuation(words[i]);
            if (!isStopWord(word) && !word.isEmpty()) {
                WordsLL.insert(word);
                invertedind.addWord(id, word);
                invertedindBST.addWord(id,word);
            }
        }
        
    }

    private  boolean isStopWord(String word) {
        stopW.findFirst();
        while (stopW.retrieve() != null) {
            if (stopW.retrieve().equals(word)) {
                return true;
            }
            stopW.findNext();
        }
        return false;
    }
public LinkedList<String> WordsLLMethod (String content, int id){
    LinkedList <String> WordsLL = new LinkedList<String>();
     preprocessContent(content, WordsLL, id);
    return WordsLL;
}

    public  void readDocuments(String fileName) {
        try {
           
            Scanner scan = new Scanner(new File(fileName));
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String id = line.substring(0, line.indexOf(',')).trim();
                int docId = Integer.parseInt(id);
                String docContent = line.substring(line.indexOf(',') + 1).trim();

                LinkedList<String> WordsLL = WordsLLMethod(docContent, docId);
                index.addDocument(new Document (docId,WordsLL));
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("end of file");
        }
    }

    public void LoadF(String StopW, String Doc){
        loadStopWords(StopW);
        readDocuments(Doc);
    }
    
    
    public void displayDocsByIds(LinkedList<Integer> ids) {
    if (ids.isEmpty()) {
        System.out.println("The provided list of IDs is empty.");
        return;
    }

    ids.findFirst();
    while (ids.retrieve() != null) {
        int docId = ids.retrieve(); // Get the current document ID
        Document doc = index.returnDocument(docId); // Retrieve the document from the index
        if (doc != null) {
            System.out.println("Document ID: " + doc.docuID);
            doc.Words.displayWordsInline();
        } else {
            System.out.println("Document with ID " + docId + " not found.");
        }
        ids.findNext();
    }
}

    public static void main(String[] args) {
  
        Processor p = new Processor();
        p.LoadF("stop.txt", "dataset.csv");
      

            p.index.displayDocs();
            System.out.println("\n----------------\n");
      
            p.invertedind.displayInvertedIndex(); //using linkedList
            p.invertedindBST.displayInvertedIndex(); //using BST

    System.out.println("\n----------------\n");
    
    
    Query q=new Query(p.invertedind);
    BSTQuery q2= new BSTQuery (p.invertedindBST);
    
    LinkedList res=Query.andQuery("clean AND create");
    p.displayDocsByIds(res);
    System.out.println("\n---------------- OR Query ----------------");
    LinkedList<Integer> orResult = Query.ORQuery("ai OR accurate");
    p.displayDocsByIds(orResult);

    System.out.println("\n---------------- ORBST Query ----------------");
    LinkedList<Integer> orBstResult = BSTQuery.ORQuery("weather OR warming");
    p.displayDocsByIds(orBstResult);
    
    System.out.println("\n---------------- OR AND Query ----------------");
  LinkedList res2=Query.MixedQuery("market OR sports AND warming");
    p.displayDocsByIds(res2);
    
   
        System.out.println("\n---------------- RANK ----------------");

   
    Rank ranking = new Rank(p.invertedindBST, p.index,"market analysts forecast");
    ranking.RankQuery("market analysts forecast");
    ranking.displayRankedDocs();
    /*
    String query = "market analysts forecast";
    System.out.println("\nRanking Documents for Query: " + query);
    ranking.RankQuery(query);
    System.out.println("\nRanked Documents:");
    ranking.displayRankedDocs();*/
    

    }
  
    
}

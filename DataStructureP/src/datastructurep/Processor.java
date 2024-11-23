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
int TotalTokens=0;
int TotalVocabulary=0;

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
        content=content.replaceAll("\'"," ");
        content=content.replaceAll("-"," ");
        String[] words = content.toLowerCase().split("\\s+");
        TotalTokens+=words.length;
        for (int i = 0; i < words.length; i++) {
            
   
            String word = removePunctuation(words[i]);
            if (!isStopWord(word) && !word.isEmpty()) {
                WordsLL.insert(word);
                boolean check1 = invertedind.addWord(id, word);
                boolean check2 = invertedindBST.addWord(id,word);
                if(check1||check2)TotalVocabulary++;

            }
        }

    }
    

    
    private  boolean isStopWord(String word) {
        if(stopW==null||stopW.isEmpty()) return false;
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


  
    
}

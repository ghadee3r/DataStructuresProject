package datastructurep;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author nouraalmadhi
 */
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
public class Document {
    LinkedList<String> Words= new LinkedList<String>();
    int docuID;

    public Document(int docuID, LinkedList<String> Words) {
        this.docuID = docuID;
        this.Words = Words;
    }
    

    public static LinkedList<String> loadStopWords(String stopWordsFile) throws IOException {
        LinkedList<String> stopWords = new LinkedList<>();
        Scanner scanner = new Scanner(new File(stopWordsFile));
        while (scanner.hasNextLine()) {
            String stopWord = scanner.nextLine().trim().toLowerCase();
            stopWords.insert(stopWord);
        }
        scanner.close();
        return stopWords;
    }

    private static String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static LinkedList<String> preprocessContent(String content, LinkedList<String> stopWords) {
        LinkedList<String> wordsList = new LinkedList<>();
        String[] words = content.toLowerCase().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = removePunctuation(words[i]);
            if (!isStopWord(word, stopWords) && !word.isEmpty()) {
                wordsList.insert(word);
            }
        }
        return wordsList;
    }

    private static boolean isStopWord(String word, LinkedList<String> stopWords) {
        stopWords.findFirst();
        while (stopWords.retrieve() != null) {
            if (stopWords.retrieve().equals(word)) {
                return true;
            }
            stopWords.findNext();
        }
        return false;
    }

    public static void readDocuments(String fileName, LinkedList<String> stopWords) {
        try {
           
            Scanner scan = new Scanner(new File(fileName));
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if(line.trim().length()<3){
                    System.out.println("Empty Line");
                    break;
                }
                String id = line.substring(0, line.indexOf(',')).trim();
                int docId = Integer.parseInt(id);
                String docContent = line.substring(line.indexOf(',') + 1).trim();

                LinkedList<String> wordsList = preprocessContent(docContent, stopWords);
                Document document = new Document(docId, wordsList);

                System.out.print(  document.docuID +" ");
                System.out.println(document.Words.displayWordsInline());
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            LinkedList<String> stopWords = loadStopWords("stop.txt");
            readDocuments("dataset.csv", stopWords);
        } catch (IOException e) {
            System.out.println("Error loading stop-words: " + e.getMessage());
        }
    }
  
}

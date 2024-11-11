/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author nouraalmadhi
 */
import java.util.LinkedList;

public class InvertedIndex {
    private LinkedList<Word> wordsList;

    public InvertedIndex() {
        wordsList = new LinkedList<>();
    }

    // Adds a document's words to the inverted index
    public void addWord(int docId, LinkedList<String> words) {
        for (String word : words) { 
            Word w = getWordEntry(word); //اشيك لو الورد موجودة في اللست الاصلية او لا
            if (w == null) { //لو رجع لي نل يعني مو موجودة و اضيف اوبجكت ورد جديد
                w = new Word(word);
                wordsList.add(w);
            }
            w.addID(docId); //لو موجوده من قبل اضيف بس الايدي
        }
    }

    // Retrieves the Word object for a specific term, or returns null if it doesn't exist
    private Word getWordEntry(String term) {
        for (Word word : wordsList) {
            if (word.word.equals(term)) {
                return word;  
            }
        }
        return null;
    }
    
    public void displayInvertedIndex() {
        for (Word word : wordsList) {
            System.out.print(word.word + ": ");
            word.doc_Ids.display();
        }
    }

    
       public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();

        // Example usage
        LinkedList<String> words1 = new LinkedList<>();
        words1.add("national");
        words1.add("flag");
        invertedIndex.addWord(1, words1);

        LinkedList<String> words2 = new LinkedList<>();
        words2.add("green");
        words2.add("color");
        invertedIndex.addWord(2, words2);
        
         LinkedList<String> words3 = new LinkedList<>();
        words3.add("national");
        words3.add("flag");
        invertedIndex.addWord(3, words3);

        // Display inverted index
        invertedIndex.displayInvertedIndex();}
}

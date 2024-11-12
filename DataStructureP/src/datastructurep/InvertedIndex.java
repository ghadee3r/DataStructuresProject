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
    public void addWord(int docId, String WORD) {
          
            Word w = getWordEntry(WORD); //اشيك لو الورد موجودة في اللست الاصلية او لا
            if (w == null) { //لو رجع لي نل يعني مو موجودة و اضيف اوبجكت ورد جديد
                w = new Word(WORD);
                wordsList.add(w);
            }
            w.addID(docId); //لو موجوده من قبل اضيف بس الايدي
        
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
            System.out.print("\n"+ word.word + ":");
             
            word.doc_Ids.display();
      
        }
    }

    
       public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.addWord(1,"flag");
        invertedIndex.addWord(2,"green");
        invertedIndex.addWord(3,"flag");
        // Display inverted index
        invertedIndex.displayInvertedIndex();
                    }
       
       
}// end class InvertedIndex



class Word {
    String word;
    datastructurep.LinkedList<Integer>doc_Ids;

    public Word(String w){
        word=w;
        doc_Ids=new datastructurep.LinkedList<Integer>();   
                }
    
    public void addID(int id){
        if(!existIN_docIDs(id))
            doc_Ids.insert(id);
            }
    public boolean existIN_docIDs(Integer id){
        if (doc_Ids.isEmpty())
            return false;
    
        doc_Ids.findFirst();
        while(!doc_Ids.last()){
            if(doc_Ids.retrieve().equals(id)){
                return true;
                }
            doc_Ids.findNext();
               }
           if(doc_Ids.retrieve().equals(id)){  //for last obj
               return true;
           }
        return false;
                }
    
        }//end class Word

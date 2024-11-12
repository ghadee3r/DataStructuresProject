/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author nouraalmadhi
 */

public class InvertedIndex {
    private datastructurep.LinkedList<Word> invertedList;

    public InvertedIndex() {
        invertedList = new datastructurep.LinkedList<Word>();
    }

    // Adds a document's words to the inverted index
    public void addWord(int docId, String WORD) {
            if (!indexWordEntry(WORD)){
                Word w = new Word(WORD); 
                w.doc_Ids.insert(docId);
                invertedList.insert(w);
            }
            else {
                Word exist = invertedList.retrieve();
                exist.addID(docId);
                }
        
    }

    private boolean indexWordEntry(String term) {
       if(invertedList==null||invertedList.isEmpty())
           return false;
       invertedList.findFirst();
       while(!invertedList.last()){
           if(invertedList.retrieve().equals(term))
               return true;
           invertedList.findNext();    
       }
       //for last node
       if(invertedList.retrieve().equals(term))
            return true;
       return false;
    }

     void displayInvertedIndex() {
         invertedList.findFirst();
         while(!invertedList.last()){
             Word W = invertedList.retrieve();
             System.out.print("\nWord: "+W.word + "[ ");
             W.doc_Ids.display();
             System.out.print("]");
             invertedList.findNext();
         }
         //for last node
         Word W = invertedList.retrieve();
         System.out.print("\nWord: "+W.word + "[ ");
         W.doc_Ids.display();
         System.out.print("]");

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

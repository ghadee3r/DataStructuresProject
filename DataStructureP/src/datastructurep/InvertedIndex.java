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
    private datastructurep.LinkedList<String> words;      
    private datastructurep.LinkedList<datastructurep.LinkedList<Integer>> docIdsList;

    public InvertedIndex() {
    words = new datastructurep.LinkedList<String>();
    docIdsList = new datastructurep.LinkedList<datastructurep.LinkedList<Integer>>();
    }

    
    
     public void addID(int id , datastructurep.LinkedList<Integer> docIds){
        if(!existIN_docIDs(id,docIds))
            docIds.insert(id);
            }
    
        public boolean existIN_docIDs(int id,datastructurep.LinkedList<Integer> docIds){
        if (docIds.isEmpty())
            return false;
    
        docIds.findFirst();
        while(!docIds.last()){
            if(docIds.retrieve().equals(id)){
                return true;
                }
            docIds.findNext();
               }
           if(docIds.retrieve().equals(id)){  //for last obj
               return true;
           }
        return false;
                }
        
        
        
        
        
    // Adds a document's words to the inverted index
    public void addWord(int docId, String WORD) {
            if (!indexWordEntry(WORD)){
            datastructurep.LinkedList<Integer> newDocIds = new datastructurep.LinkedList<>();
            newDocIds.insert(docId);
            words.insert(WORD);
            docIdsList.insert(newDocIds);
            }
            else {
                datastructurep.LinkedList<Integer> exist = docIdsList.retrieve();
                    addID(docId,exist);                }
    }
    
  
        
        
    private boolean indexWordEntry(String word) {
        if (words.isEmpty()) return false;
        
        words.findFirst();
        
        while (!words.last()) {
            if (words.retrieve().equals(word)) return true;
            words.findNext();
        }
        
        //for last node
        return words.retrieve().equals(word);
    }

public void displayInvertedIndex() {
        words.findFirst();
        docIdsList.findFirst();

        while (!words.last()) {
            String word = words.retrieve();
            System.out.print("\nWord: " + word + " [ ");
            datastructurep.LinkedList<Integer> docIds = docIdsList.retrieve();
            docIds.display();
            System.out.print(" ]");
            words.findNext();
            docIdsList.findNext();
        }
        
        //for last node
        String word = words.retrieve();
        System.out.print("\nWord: " + word + " [ ");
        datastructurep.LinkedList<Integer> docIds = docIdsList.retrieve();
        docIds.display();
        System.out.print(" ]");
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



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
    public datastructurep.LinkedList<WordEntry> invertedList;
    
    public InvertedIndex() {
    invertedList = new datastructurep.LinkedList<>();
    }

    
        
        
    // Adds a document's words to the inverted index
    public void addWord(int ID, String WORD) {
            if (!indexWordEntry(WORD)){
            WordEntry newWord = new WordEntry(WORD);
            newWord.getDocIds().insert(ID);
            invertedList.insert(newWord);
            }
            else {
                WordEntry exist = invertedList.retrieve();
                    exist.addID(ID);                
            }            
    }
    
    


     
        
    public boolean indexWordEntry(String w) {
        if (invertedList.isEmpty()) return false;
        
        invertedList.findFirst();
        
        while (!invertedList.last()) {
            if (invertedList.retrieve().getWord().equals(w)) return true;
            invertedList.findNext();
        } 
        
        //for last nodev
        return invertedList.retrieve().getWord().equals(w);
    }

    






    
public void displayInvertedIndex() {
        if (invertedList.isEmpty()){
            System.out.println("empty documents");
        return; }
        
        invertedList.findFirst();

        while (!invertedList.last()) {
            WordEntry l = invertedList.retrieve();
            System.out.print("\nWord: " + l.getWord() + " [ ");
            l.getDocIds().display();
            System.out.print("]"+ "Number of Documents:" +l.getWordDocCount());
            
            invertedList.findNext();
        }
        
        //for last node
            WordEntry l = invertedList.retrieve();
            System.out.print("\nWord: " + l.getWord() + " [ ");
            l.getDocIds().display();
            System.out.print("]"+ " Number of Documents: " +l.getWordDocCount());
   }
          


       
}// end class InvertedIndex



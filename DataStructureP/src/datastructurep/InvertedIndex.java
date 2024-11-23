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
    public LinkedList<WordEntry> invertedList;
    
    public InvertedIndex() {
    invertedList = new LinkedList<>();
    }

    
        
        
    // Adds a document's words to the inverted index
    public boolean addWord(int ID, String WORD) {
            if (!indexWordEntry(WORD)){
            WordEntry newWord = new WordEntry(WORD);
            newWord.getDocIds().insert(ID);
            invertedList.insert(newWord);
            return true;}
            else {
                WordEntry exist = invertedList.retrieve();
                    exist.addID(ID);                
            return false;}            
    }
    
    


     
        
    public boolean indexWordEntry(String w) {
        if (invertedList.isEmpty()) {
        return false;}
        
        invertedList.findFirst();
        
        while (!invertedList.last()) {
            if (invertedList.retrieve().getWord().equals(w)) return true;
            invertedList.findNext();
        } 
        
        //for last nodev
        return invertedList.retrieve().getWord().equals(w);
    }

    


public void findWordInvertedIndex(String word) {
    if (invertedList.isEmpty()) {
        System.out.println("The inverted index is empty.");
        return;
    }

    if (indexWordEntry(word)) {
        WordEntry entry = invertedList.retrieve(); 
        System.out.print("Word found in Document IDs: ");
        entry.getDocIds().display(); 
    } else {
        System.out.println("The word [" + word + "] was not found.");
    }
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



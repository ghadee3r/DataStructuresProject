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
    int VocabCount;
    
    public InvertedIndex() {
    invertedList = new datastructurep.LinkedList<>();
    VocabCount=0;
    }

    
        
        
    // Adds a document's words to the inverted index
    public void addWord(int ID, String WORD) {
            if (!indexWordEntry(WORD)){
            WordEntry newWord = new WordEntry(WORD);
            newWord.getDocIds().insert(ID);
            invertedList.insert(newWord);
            VocabCount++;
            }
            else {
                WordEntry exist = invertedList.retrieve();
                    exist.addID(ID);                
            }            
    }
    
    
public int getVocabularySize() {
    return VocabCount;
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

    


public int getTokenCount() {
    int tokenCount = 0;

    invertedList.findFirst(); // Start from the first element in the linked list
    while (invertedList.retrieve() != null) {
        WordEntry entry = invertedList.retrieve(); // Retrieve the current WordEntry
        if (!entry.getDocIds().isEmpty()) { // Check if the WordEntry contains document IDs
            entry.getDocIds().findFirst(); // Start from the first doc ID in the list
            while (entry.getDocIds().retrieve() != null) { // Traverse the doc IDs
                tokenCount++; // Increment the token count
                if (!entry.getDocIds().last()) {
                    entry.getDocIds().findNext(); // Move to the next doc ID
                } else {
                    break; // Stop when at the last element
                }
            }
        }
        if (!invertedList.last()) {
            invertedList.findNext(); // Move to the next WordEntry in the inverted list
        } else {
            break; // Stop when at the last element of the inverted list
        }
    }
    return tokenCount; // Return the total token count
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
            System.out.print("]");
            invertedList.findNext();
        }
        
        //for last node
            WordEntry l = invertedList.retrieve();
            System.out.print("\nWord: " + l.getWord() + " [ ");
            l.getDocIds().display();
            System.out.print("]");
   }
          


       
}// end class InvertedIndex



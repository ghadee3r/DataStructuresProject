/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author lamee
 */
public class InvertedIndexBST {
    
BST<WordEntry> invertedList;

public InvertedIndexBST(){
    invertedList=new BST<WordEntry>();
}

public boolean addWord(int ID, String WORD) {
            if (!searchWord(WORD)){
            WordEntry newWord = new WordEntry(WORD);
            newWord.getDocIds().insert(ID);
            invertedList.insert(WORD,newWord);
            return true;}
            else {
                WordEntry exist = invertedList.retrieve();
                    exist.addID(ID);    
return false;            }
    }

public boolean searchWord(String WORD){
    return invertedList.FindKey(WORD);
}


public void findWordInvertedIndexBST(String word) {
    if (invertedList.empty() || invertedList == null) {
        System.out.println("The inverted index is empty.");
        return;
    }

    if (searchWord(word)) {
        WordEntry entry = invertedList.retrieve(); 
        System.out.print("Word found in Document IDs: ");
        entry.getDocIds().display(); // Display document IDs
    } else {
        System.out.println("The word [" + word + "] was not found.");
    }
}



public void displayInvertedIndex(){
    if (invertedList.empty()||invertedList==null) {
     System.out.println("Empty"); 
     return; }
    invertedList.preOrder();
}

}// end InvertedIndexBST

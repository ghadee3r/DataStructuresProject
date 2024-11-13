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

public void addWord(int ID, String WORD) {
            if (!searchWord_inInverted(WORD)){
            WordEntry newWord = new WordEntry(WORD);
            newWord.getDocIds().insert(ID);
            invertedList.insert(WORD,newWord);
            }
            else {
                WordEntry exist = invertedList.retrieve();
                    exist.addID(ID);                }
    }

public boolean searchWord_inInverted(String WORD){
    return invertedList.FindKey(WORD);
}

}// end InvertedIndexBST

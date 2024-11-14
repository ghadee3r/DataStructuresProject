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
public void displayInvertedIndex() {
    if (invertedList.empty()) {
        System.out.println("The inverted index is empty.");
    } else {
        displayPreOrder(invertedList.getRoot()); // Start from the root node of the BST
    }
}

private void displayPreOrder(BSTNode<WordEntry> node) {
    if (node == null) {
        return;
    }

    // Display the current node
    WordEntry wordEntry = node.data;
    System.out.print("\nWord: " + wordEntry.getWord() + " [ ");
    wordEntry.getDocIds().display(); // Display document IDs associated with this word
    System.out.print(" ]");

    // Traverse the left subtree
    displayPreOrder(node.left);

    // Traverse the right subtree
    displayPreOrder(node.right);
}

}// end InvertedIndexBST

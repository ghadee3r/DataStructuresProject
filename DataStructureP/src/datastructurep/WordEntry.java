/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author lamee
 */
public class WordEntry {
    private String word;
    private datastructurep.LinkedList<Integer> docIds;
    public WordEntry(String word) {
        this.word = word;
        this.docIds = new datastructurep.LinkedList<>();
    }

    public String getWord() {
        return word;
    }

    public datastructurep.LinkedList<Integer> getDocIds() {
        return docIds;
    }
    
    public int getWordDocCount(){
    return docIds.length;   }
    
    public void addID(int docId) {
        if (!existsInDocIds(docId)) {
            docIds.insert(docId);
        }
    }

    private boolean existsInDocIds(int docId) {
        if (docIds.isEmpty()) return false;

        docIds.findFirst();
        while (!docIds.last()) {
            if (docIds.retrieve().equals(docId)) {
                return true;
            }
            docIds.findNext();
        }
        return docIds.retrieve().equals(docId);  // for last element
    }
    
    @Override
public String toString() {
    String ids = ""; 
    docIds.findFirst(); 
    while (!docIds.last()) {
        ids += docIds.retrieve() + " "; 
        docIds.findNext();
        
    }
    ids += docIds.retrieve();  // for last element
    return "Word: " + word + " [" + ids + "] " + "Number of Documents: " + docIds.length;
}


}// end class WordEntry

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
}// end class WordEntry

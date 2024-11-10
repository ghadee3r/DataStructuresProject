/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author lamee
 */
public class Index {
    
    private LinkedList documents ;
    
    public Index() {
    documents = new LinkedList();        }
    
    public void addDocument (int docId , String docContent) {
        Document doc = new Document(docId,docContent);
        documents.insert(doc);
            }//end addDocument 
    
}//end Index
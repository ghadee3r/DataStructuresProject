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
    
    public void addDocument (int docId , LinkedList<String> Words) {
        Document doc = new Document(docId,Words);
        documents.insert(doc);
            }//end addDocument 
    
    public Document returnDocument (int docId){
        documents.findFirst();
        while(documents.retrieve()!=null){
            Document doc = (Document)documents.retrieve();
            if(doc.docuID==docId){
                return doc;     }
            documents.findNext();
            }
        return null;
             }//end returnDocument
    
}//end Index
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
    private int DocCount;
    
    public Index() {
    documents = new LinkedList(); 
    DocCount=0;}
    
    public void addDocument (Document doc ) {
        documents.insert(doc);
        DocCount++;
            }//end addDocument 
    
public int getNumberOfDocuments() {
    return DocCount; 
}

    
    public void displayDocs(){
        if (documents.isEmpty()){
            System.out.println("empty documents");
        return; }
        
        documents.findFirst();
        while(!documents.last()){
            
            Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.displayWordsInline();
            documents.findNext();
            
        }
        Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.displayWordsInline();
    }
    
    public Document returnDocument(int docId) {
    if (documents.isEmpty()) {
        return null; 
    }
    documents.findFirst();
    while (documents.retrieve()!=null) {
        Document doc = (Document) documents.retrieve();
        if (doc.docuID == docId) 
            return doc; 
        documents.findNext(); 
    }
    return null; 
}


}//end Index
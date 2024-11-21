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
    documents = new LinkedList(); 
}
    
    public void addDocument (Document doc ) {
        documents.insert(doc);
            }//end addDocument 
    


    
    public void displayDocs(){
        if (documents.isEmpty()){
            System.out.println("empty documents");
        return; }
        
        documents.findFirst();
        while(!documents.last()){
            
            Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.displayWordsInline();
            System.out.println("Token Count:"+ doc.getTokenCountPerDoc());
            documents.findNext();
            
        }
        Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.displayWordsInline();
            System.out.println("Token Count:"+ doc.getTokenCountPerDoc());
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
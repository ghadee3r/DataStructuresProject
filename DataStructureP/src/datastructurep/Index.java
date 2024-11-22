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

public void findWordIndex(String word) {
    if (documents.isEmpty()) {
        System.out.println("No documents available.");
        return;
    }
    
    boolean wordFound = false;
    String ids = "";
    documents.findFirst();
    while (!documents.last()) {
        Document doc = (Document) documents.retrieve();
        if (doc.Words.exists(word)) { // Assuming Words is a structure with a 'contains' method
            ids+= doc.docuID + " ";
            wordFound = true;
        }
        documents.findNext();
    }
    
    // Check the last document
    Document doc = (Document) documents.retrieve();
    if (doc.Words.exists(word)) {
            ids+= doc.docuID;
        wordFound = true;
    }
    System.out.println("Word found in Document IDs: "+ids);
    if (!wordFound) {
        System.out.println("The word [" + word + "] was not found.");
    }
}

}//end Index
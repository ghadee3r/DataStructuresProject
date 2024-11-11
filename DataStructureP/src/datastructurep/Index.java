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
    
    public void displayDocs(){
        if (documents.isEmpty()){
            System.out.println("empty documents");
        return; }
        
        documents.findFirst();
        while(!documents.last()){
            
            Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.display();
            documents.findNext();
            
        }
        Document doc= (Document) documents.retrieve();
            System.out.println("ID:"+ doc.docuID);
            doc.Words.display();
    }
    
    public static void main (String[]args){

Index ind1=new Index () ;
LinkedList<String>words=new LinkedList<> ();
words.insert ("national");
words.insert ("flag");
Document d1=new Document (1, words) ;

LinkedList<String>words2=new LinkedList<> ();
words2.insert ("green");
words2.insert ("color");
Document d2=new Document (2, words2);

ind1.addDocument (d1.docuID, d1.Words);

ind1.addDocument (d2.docuID, d2.Words);

ind1.displayDocs();
    
    }

}//end Index
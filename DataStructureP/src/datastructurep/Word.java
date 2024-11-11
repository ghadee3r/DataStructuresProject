/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author nouraalmadhi
 */
public class Word {
 String word;
LinkedList<Integer>doc_Ids;

public Word(String w){
    word=w;
    doc_Ids=new LinkedList<Integer>();
   
}
public void addID(int id){
    if(!existIN_docIDs(id))
        doc_Ids.insert(id);
}
public boolean existIN_docIDs(Integer id){
    if (doc_Ids.isEmpty())
        return false;
    
    doc_Ids.findFirst();
    while(!doc_Ids.last()){
        if(doc_Ids.retrieve().equals(id)){
            return true;
        }
        doc_Ids.findNext();
    }
           if(doc_Ids.retrieve().equals(id)){  //for last obj
               return true;
           }
        return false;
}
}

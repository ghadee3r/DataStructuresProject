package datastructurep;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ghade
 */

public class Document {
        LinkedList<String> Words= new LinkedList<>();
    int docuID;

    public Document(int docuID, LinkedList<String> Words) {
        this.docuID = docuID;
        this.Words = Words;
    }
    
}

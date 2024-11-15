/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author ghade
 */
public class Query {
    
   static InvertedIndex inverted;
   
   public Query (InvertedIndex inverted){
       this.inverted=inverted;
   }
   
public static LinkedList<Integer> andQuery(String Query) {
    LinkedList<Integer> A = new LinkedList<>();
    LinkedList<Integer> B = new LinkedList<>();
    String terms[] = Query.split("AND");

    if (terms.length == 0) {
        System.out.println("No terms in query.");
        return A; // Return an empty list
    }


    // Process the first term
    boolean found = inverted.indexWordEntry(terms[0].trim().toLowerCase());
    if (found) {
        A = inverted.invertedList.retrieve().getDocIds();
        System.out.println("Doc IDs for term '" + terms[0].trim().toLowerCase() + "': " + A);
    } else {
        System.out.println("Term '" + terms[0].trim().toLowerCase() + "' not found in index.");
        return A; // Return empty if the first term is not found
    }

    // Process remaining terms
    for (int i = 1; i < terms.length; i++) {
        System.out.println("Processing term: " + terms[i].trim().toLowerCase());
        found = inverted.indexWordEntry(terms[i].trim().toLowerCase());
        if (found) {
            B = inverted.invertedList.retrieve().getDocIds();
            System.out.println("Doc IDs for term '" + terms[i].trim().toLowerCase() + "': " + B);
        } else {
            System.out.println("Term '" + terms[i].trim().toLowerCase() + "' not found in index.");
            return new LinkedList<>(); // Return empty if any term is not found
        }
        A = andQuery(A, B); // Perform intersection
    }

    return A;
}

   
     public static LinkedList<Integer>andQuery(LinkedList<Integer>A, LinkedList<Integer>B){
         
         LinkedList<Integer> result = new LinkedList<Integer>();
         if(A.isEmpty()||B.isEmpty())
             return result;
         
         A.findFirst();
         while (true){
             boolean found = existsInResult(result,A.retrieve());
             if (!found){
                 B.findFirst();
                 while( true){
                     if (B.retrieve().equals(A.retrieve()))
                     {
                 result.insert(A.retrieve());
                 break;} 
                     if(!B.last())
                         B.findNext();
                     else
                         break;
                 }
             }     
              if (!A.last())
                  A.findNext();
              else
                  break;
         } 
         return result;
                 
             }
     
  public static boolean existsInResult(LinkedList<Integer> list, Integer element) {
    if (list.isEmpty()) {
        return false; // No elements to check
    }

    list.findFirst();
    while (!list.last()) {
        if (list.retrieve().equals(element)) return true;
        list.findNext();
    }
    return list.retrieve() != null && list.retrieve().equals(element); // Final check
}

   
}

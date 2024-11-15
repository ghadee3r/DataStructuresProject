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
   static InvertedIndexBST invertedBST;
   
   public Query (InvertedIndex inverted){
       this.inverted=inverted;
      
   }
   
   
   public Query (InvertedIndexBST invertedBST){
 
       this.invertedBST=invertedBST;
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
        System.out.println("Doc IDs for term: " + terms[0].trim().toLowerCase());
    } else {
        System.out.println("Term '" + terms[0].trim().toLowerCase() + "' not found in index.");
        return A; // Return empty if the first term is not found
    }

    // Process remaining terms
    for (int i = 1; i < terms.length; i++) {
     
        found = inverted.indexWordEntry(terms[i].trim().toLowerCase());
        if (found) {
            B = inverted.invertedList.retrieve().getDocIds();
            System.out.println("Doc IDs for term: " + terms[i].trim().toLowerCase() );
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

   public static LinkedList<Integer> ORQuery(String query) {
    LinkedList<Integer> A = new LinkedList<>();
    LinkedList<Integer> B = new LinkedList<>();
    String terms[] = query.split("OR");

    if (terms.length == 0) return A;

    boolean found = inverted.indexWordEntry(terms[0].trim().toLowerCase());
    if (found) {
        A = inverted.invertedList.retrieve().getDocIds();
    }

    for (int i = 1; i < terms.length; i++) {
        found = inverted.indexWordEntry(terms[i].trim().toLowerCase());
        if (found) {
            B = inverted.invertedList.retrieve().getDocIds();
            A = ORQuery(A, B); // Perform OR operation between A and B
        }
    }

    return A;
}
public static LinkedList<Integer> ORQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
    LinkedList<Integer> result = new LinkedList<>();

    if (A.isEmpty() && B.isEmpty()) {
        return result;
    }

    A.findFirst();
    while (!A.isEmpty()) {
        boolean found = existsInResult(result, A.retrieve());
        if (!found) { 
            result.insert(A.retrieve());
        }
        if (!A.last()) {
            A.findNext();
        } else {
            break;
        }
    }

    B.findFirst();
    while (!B.isEmpty()) {
        boolean found = existsInResult(result, B.retrieve());
        if (!found) { 
            result.insert(B.retrieve());
        }
        if (!B.last()) {
            B.findNext();
        } else {
            break;
        }
    }

    return result; 
}

public static LinkedList<Integer> ORBSTQuery(String query) {
    LinkedList<Integer> A = new LinkedList<>();
    LinkedList<Integer> B = new LinkedList<>();
    String terms[] = query.split("OR");

    if (terms.length == 0) return A;

    boolean found = invertedBST.searchWord(terms[0].trim().toLowerCase());
    if (found) {
        A = invertedBST.invertedList.retrieve().getDocIds();
    }

    for (int i = 1; i < terms.length; i++) {
        found = invertedBST.searchWord(terms[i].trim().toLowerCase());
        if (found) {
            B = invertedBST.invertedList.retrieve().getDocIds();
            A = ORQuery(A, B); // Perform OR operation between A and B
        }
    }

    return A;
}
public static LinkedList<Integer> ORBSTQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
    LinkedList<Integer> result = new LinkedList<>();

    if (A.isEmpty() && B.isEmpty()) {
        return result;
    }

    A.findFirst();
    while (!A.isEmpty()) {
        boolean found = existsInResult(result, A.retrieve());
        if (!found) { 
            result.insert(A.retrieve());
        }
        if (!A.last()) {
            A.findNext();
        } else {
            break;
        }
    }

    B.findFirst();
    while (!B.isEmpty()) {
        boolean found = existsInResult(result, B.retrieve());
        if (!found) { 
            result.insert(B.retrieve());
        }
        if (!B.last()) {
            B.findNext();
        } else {
            break;
        }
    }

    return result; 
}

public static LinkedList<Integer> andBSTQuery(String Query) {
    LinkedList<Integer> A = new LinkedList<>();
    LinkedList<Integer> B = new LinkedList<>();
    String terms[] = Query.split("AND");

    if (terms.length == 0) {
        System.out.println("No terms in query.");
        return A; // Return an empty list
    }


    // Process the first term
    boolean found = invertedBST.searchWord(terms[0].trim().toLowerCase());
    if (found) {
        A = invertedBST.invertedList.retrieve().getDocIds();
        System.out.println("Doc IDs for term: " + terms[0].trim().toLowerCase());
    } else {
        System.out.println("Term '" + terms[0].trim().toLowerCase() + "' not found in index.");
        return A; // Return empty if the first term is not found
    }

    // Process remaining terms
    for (int i = 1; i < terms.length; i++) {
     
        found = invertedBST.searchWord(terms[i].trim().toLowerCase());
        if (found) {
            B = invertedBST.invertedList.retrieve().getDocIds();
            System.out.println("Doc IDs for term: " + terms[i].trim().toLowerCase() );
        } else {
            System.out.println("Term '" + terms[i].trim().toLowerCase() + "' not found in index.");
            return new LinkedList<>(); // Return empty if any term is not found
        }
        A = andQuery(A, B); // Perform intersection
    }

    return A;
}

public static LinkedList<Integer>andBSTQuery(LinkedList<Integer>A, LinkedList<Integer>B){
         
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

public static LinkedList<Integer> BooleanQuery(String Query){
    if(!Query.contains("AND") && !Query.contains("OR"))
        return andQuery(Query);
    else if(Query.contains("AND") && !Query.contains("OR"))
        return andQuery(Query);
    else if(!Query.contains("AND") && Query.contains("OR"))
        return ORQuery(Query);
    else
        return MixedQuery(Query);
}


public static LinkedList<Integer> MixedQuery(String Query){
    LinkedList<Integer> A = new LinkedList<Integer>();
    LinkedList<Integer> B = new LinkedList<Integer>();
    
    if(Query.length() == 0) return A;
    
    String ors[] = Query.split("OR");
    
    A = andQuery(ors[0]);
    for(int i = 1; i < ors.length; i++) {
        B = andQuery(ors[i]);
        A = ORQuery(A, B);
    }
    
    return A;
}

}

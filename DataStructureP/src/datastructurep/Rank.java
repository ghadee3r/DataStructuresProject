/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author lamee
 */
public class Rank {
    String query; 
    InvertedIndexBST invBST;
    Index indx; 
    LinkedList<Integer> inQueryDocs; 
    LinkedList<DocRank> rankDocs;
    
     public Rank(InvertedIndexBST invBST, Index indx) {
        this.invBST = invBST;
        this.indx = indx;
        this.inQueryDocs = new LinkedList<Integer>();
        this.rankDocs = new LinkedList<DocRank>();
    }
    
   
    public static int TermFrequency(Document doc, String term) {
        int count = 0;
        LinkedList<String> WORDS = doc.Words; 
        if(WORDS.isEmpty()) return 0;
        WORDS.findFirst();
        while (WORDS.retrieve()!= null){
            if (WORDS.retrieve().equalsIgnoreCase(term))
                count++;
                WORDS.findNext();
        }
        return count;
    }
    /*
        public static int TermFrequency(Document doc, String term) {
        int count = 0;
        LinkedList<String> WORDS = doc.Words; 
        if(WORDS.isEmpty()) return 0;
        WORDS.findFirst();
        
        while (!WORDS.last()){
            if (WORDS.retrieve().equalsIgnoreCase(term))
                count++;
                WORDS.findNext();
        }
           if (WORDS.retrieve().equalsIgnoreCase(term))
                count++;
        return count;
    }
    */
    public static int RankScore(Document doc, String query) {
        if(query.length()==0) return 0;
        String[] terms = query.split(" "); 
        int score = 0;
        for (String term : terms) {
            score += TermFrequency(doc, term);
        }
        return score;
    }
    
    
    public void RankQuery(String query) {
        if(query.length()==0)return;
        LinkedList<Integer> L = new LinkedList<>();
        String[] terms = query.split(" ");
        boolean found = false;
        
        
        for (String term : terms) {
            found=invBST.searchWord(term);
            if (found) {
            L=invBST.invertedList.retrieve().getDocIds();
            sortRankedDocs(L); // Sort documents by rank
            }
        }
    }


    public boolean existsInList(LinkedList<Integer> list, int value) {
        list.findFirst();
        while (list.retrieve() != null) {
            if (list.retrieve() == value) {
                return true;
            }
            if (!list.last()) 
                list.findNext();
             else 
                break;   
        }
        return false;
    }

    public void sortRankedDocs(LinkedList<Integer> IDs) {
        if (IDs.isEmpty())
            return;
    IDs.findFirst();
    while (IDs.retrieve() != null) {
        int id = IDs.retrieve();
        if (!existsInList(inQueryDocs, id)) {
            insertID_IntoSortedList(IDs.retrieve());
            }
        if (!IDs.last()) 
            IDs.findNext();
         else 
            break;   
    }
}


public void insertID_IntoSortedList(int id) {
        if (inQueryDocs.isEmpty()) {
        inQueryDocs.insert(id);
        return;
    }
    inQueryDocs.findFirst();
    while (!inQueryDocs.last()) {
        
       if (id < inQueryDocs.retrieve()) {
          Integer temp = inQueryDocs.retrieve();
           inQueryDocs.update(id);
           inQueryDocs.insert(temp);   //if
     return;}
       else
             inQueryDocs.findNext();
         
    }// while
      if (id < inQueryDocs.retrieve()) {
          Integer temp = inQueryDocs.retrieve();
           inQueryDocs.update(id);
           inQueryDocs.insert(temp);
      return;
      } //if
      else
    inQueryDocs.insert(id);
}



public void insert_IntoSortedList() {
    if (inQueryDocs.isEmpty()) {
        System.out.println("empty query");
        return;
    }
    inQueryDocs.findFirst();
    
    while (!inQueryDocs.last()) {
        
        int docId = inQueryDocs.retrieve();
        Document d = indx.returnDocument(docId);
        
        int rank = RankScore(d, query);
        
        insert_IntoSortedList(new DocRank(docId, rank));
        inQueryDocs.findNext();
    }
    // for last
    int docId = inQueryDocs.retrieve();
    Document d = indx.returnDocument(docId);
    int rank = RankScore(d, query);
    insert_IntoSortedList(new DocRank(docId, rank));
}

public void insert_IntoSortedList(DocRank dr) {
    if (rankDocs.isEmpty()) {
        rankDocs.insert(dr);
        return;
    }
    rankDocs.findFirst();
    
    while (!rankDocs.last()) {
        if (dr.rank > rankDocs.retrieve().rank) {
            DocRank dr1 = rankDocs.retrieve();
            rankDocs.update(dr);
            rankDocs.insert(dr1); 
            return;
        }
        else
        rankDocs.findNext();
    }
    //for last
    if (dr.rank > rankDocs.retrieve().rank) {
            DocRank dr1 = rankDocs.retrieve();
            rankDocs.update(dr);
            rankDocs.insert(dr1); 
            return;
        }
    else {
       
        rankDocs.insert(dr);
    }
}

    // Display ranked documents
    public void displayRankedDocs() {
        if (rankDocs.isEmpty()){
            System.out.println("empty list");
        }
        else {
        System.out.println("Doc ID:     Score:");
        rankDocs.findFirst();
        while (!rankDocs.last()) {
            rankDocs.retrieve().displayIdRank();
            rankDocs.findNext();
        }
         rankDocs.retrieve().displayIdRank();
        }
        
    }
    
     class DocRank {
        int id; 
        int rank; 
        
        public DocRank(int id, int rank) {
            this.id = id;
            this.rank = rank;
        }
        
        public void displayIdRank(){
        System.out.println("ID:"+id+"Rank:"+rank);
        }
        
    }//end DocRank
    
    // Main method for testing
public static void main(String[] args) {
    // Create instances of required components
    InvertedIndexBST invBST = new InvertedIndexBST();
    Index indx = new Index();

    // Populate the inverted index with some data
    invBST.addWord(1, "hello");
    invBST.addWord(1, "world");
    invBST.addWord(2, "hello");

    // Create Document 1
    LinkedList<String> words = new LinkedList<>();
    words.insert("hello");
    words.insert("world");
    Document d1 = new Document(1, words);

    // Create Document 2
    LinkedList<String> words2 = new LinkedList<>();
    words2.insert("hello");
    Document d2 = new Document(2, words2);

    // Add documents to the index
    indx.addDocument(d1);
    indx.addDocument(d2);

    // Create a Rank instance
    Rank ranker = new Rank(invBST, indx);

    // Define a query
    String query = "hello world";

    // Rank the query
    ranker.RankQuery(query);

    // Display the ranked documents
    ranker.displayRankedDocs();
}

}//end Rank

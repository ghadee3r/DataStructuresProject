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
        this.inQueryDocs = new LinkedList<>();
        this.rankDocs = new LinkedList<>();
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
    
    public static int RankScore(Document doc, String query) {
        if(query.length()==0) return 0;
        String[] terms = query.split(" "); 
        int score = 0;
        for (String term : terms) {
            score += TermFrequency(doc, term);
        }
        return score;
    }
    /*
    public void RankQuery(String query) {
        rankDocs = new LinkedList<>(); // Clear previous ranks

        String[] terms = query.split(" ");
        inQueryDocs = getRelevantDocs(terms); // Retrieve relevant document IDs

        // Loop through relevant documents and calculate their ranks
        inQueryDocs.findFirst();
        while (inQueryDocs.retrieve() != null) {
            int docId = inQueryDocs.retrieve();
            Document doc = indx.returnDocument(docId); // Retrieve document from index

            if (doc != null) {
                int rank = RankScore(doc, query);
                rankDocs.insert(new DocRank(docId, rank)); // Add to ranked documents
            }

            if (!inQueryDocs.last()) {
                inQueryDocs.findNext();
            } else {
                break;
            }
        }

        sortRankedDocs(); // Sort documents by rank
        displayRankedDocs(); // Display ranked results
    }

    // Retrieve document IDs relevant to the query terms
    private LinkedList<Integer> getRelevantDocs(String[] terms) {
        LinkedList<Integer> relevantDocs = new LinkedList<>();

        for (String term : terms) {
            if (invBST.searchWord(term.toLowerCase())) {
                WordEntry entry = invBST.invertedList.retrieve();
                LinkedList<Integer> docIds = entry.getDocIds();

                // Add all document IDs from the word entry to the relevantDocs list
                docIds.findFirst();
                while (docIds.retrieve() != null) {
                    if (!existsInList(relevantDocs, docIds.retrieve())) {
                        relevantDocs.insert(docIds.retrieve());
                    }
                    if (!docIds.last()) {
                        docIds.findNext();
                    } else {
                        break;
                    }
                }
            }
        }

        return relevantDocs;
    }

        // Check if a value exists in a LinkedList
    public boolean existsInList(LinkedList<Integer> list, int value) {
        list.findFirst();
        while (list.retrieve() != null) {
            if (list.retrieve() == value) {
                return true;
            }
            if (!list.last()) {
                list.findNext();
            } else {
                break;
            }
        }
        return false;
    }

    // Sort ranked documents by their rank scores in descending order
    public void sortRankedDocs() {
        LinkedList<DocRank> sortedList = new LinkedList<>();
        while (!rankDocs.isEmpty()) {
            rankDocs.findFirst();
            DocRank highest = rankDocs.retrieve();
            rankDocs.findNext();

            while (!rankDocs.last()) {
                DocRank current = rankDocs.retrieve();
                if (current.rank > highest.rank) {
                    highest = current;
                }
                rankDocs.findNext();
            }

            sortedList.insert(highest);
            rankDocs.remove(); // Remove the highest-ranked doc from the original list
        }

        rankDocs = sortedList;
    } */

    // Display ranked documents
    public void displayRankedDocs() {
        System.out.println("Doc ID:     Score:");
        rankDocs.findFirst();
        while (rankDocs.retrieve() != null) {
            rankDocs.retrieve().displayIdRank();
            rankDocs.findNext();
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
    
    
}//end Rank

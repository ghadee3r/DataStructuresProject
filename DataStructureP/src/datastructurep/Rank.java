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
    
    public Rank(InvertedIndexBST invBST, Index indx, String query) {
        this.invBST = invBST;
        this.indx = indx;
        this.inQueryDocs = new LinkedList<Integer>();
        this.rankDocs = new LinkedList<DocRank>();
        this.query = query;
    }
    
    // Calculate Term Frequency (TF)
    public static int TermFrequency(Document doc, String term) {
        int count = 0;
        LinkedList<String> WORDS = doc.Words; 
        if (WORDS.isEmpty()) return 0;
        WORDS.findFirst();
        while (WORDS.retrieve() != null) {
            if (WORDS.retrieve().equalsIgnoreCase(term)) {
                count++;
            }
            if (!WORDS.last()) {
                WORDS.findNext();
            } else {
                break;
            }
        }
        return count;
    }

    // Calculate the rank score for a document based on a query
    public static int RankScore(Document doc, String query) {
        if (query.length() == 0) return 0;
        String[] terms = query.split(" "); 
        int score = 0;
        for (String term : terms) {
            score += TermFrequency(doc, term);
        }
        return score;
    }
    
    // Rank documents based on the query
    public void RankQuery(String query) {
        if (query.length() == 0) return;

        LinkedList<Integer> allDocIds = new LinkedList<>();
        String[] terms = query.split(" ");
        
        for (String term : terms) {
            boolean found = invBST.searchWord(term);
            if (found) {
                LinkedList<Integer> docIds = invBST.invertedList.retrieve().getDocIds();
                docIds.findFirst();
                while (docIds.retrieve() != null) {
                    int docId = docIds.retrieve();
                    if (!existsInList(allDocIds, docId)) {
                        allDocIds.insert(docId); // Add unique document IDs
                    }
                    if (!docIds.last()) {
                        docIds.findNext();
                    } else {
                        break;
                    }
                }
            }
        }

        // Calculate rank score for each document and insert into rankDocs
        allDocIds.findFirst();
        while (allDocIds.retrieve() != null) {
            int docId = allDocIds.retrieve();
            Document doc = indx.returnDocument(docId);
            int score = RankScore(doc, query);
            insertDR_IntoSortedList(new DocRank(docId, score));
            if (!allDocIds.last()) {
                allDocIds.findNext();
            } else {
                break;
            }
        }
    }

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

    public void insertDR_IntoSortedList(DocRank dr) {
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
            rankDocs.findNext();
        }
        // For the last element
        if (dr.rank > rankDocs.retrieve().rank) {
            DocRank dr1 = rankDocs.retrieve();
            rankDocs.update(dr);
            rankDocs.insert(dr1);
        } else {
            rankDocs.insert(dr);
        }
    }

    // Display ranked documents
    public void displayRankedDocs() {
        if (rankDocs.isEmpty()) {
            System.out.println("empty list");
        } else {
            System.out.println("Doc ID:     Score:");
            rankDocs.findFirst();
            while (!rankDocs.last()) {
                rankDocs.retrieve().displayIdRank();
                rankDocs.findNext();
            }
            rankDocs.retrieve().displayIdRank();
        }
    }
    
    // Inner class for DocRank
    class DocRank {
        int id; 
        int rank; 
        
        public DocRank(int id, int rank) {
            this.id = id;
            this.rank = rank;
        }
        
        public void displayIdRank() {
            System.out.println("ID: " + id + ", Rank: " + rank);
        }
    }

}


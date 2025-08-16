# Search Engine using Linked Lists and Binary Search Trees  

This project was developed for **CSC212 – Data Structures** at King Saud University.  
It implements a simple yet scalable **document search engine** using two core data structures: **Linked Lists** and **Binary Search Trees (BSTs)**.  

The system supports document indexing, Boolean retrieval (AND/OR), and ranked retrieval based on term frequency.  

---

## Project Overview  

The goal of the project was to design a search engine that highlights the differences in performance between linear structures (Linked Lists) and hierarchical structures (BSTs).  

- **Linked Lists:** used for managing documents, tokens, and inverted indices.  
- **Binary Search Trees:** used for faster word lookups in the inverted index.  
- **Boolean Queries:** support for `AND`, `OR`, and mixed queries.  
- **Ranked Retrieval:** scores documents based on term frequency to rank relevance.  

---

## Features  

- **Preprocessing:**  
  - Lowercasing text, removing punctuation, filtering out stop words.  
  - Each document is stored with a unique ID and processed content.  

- **Indexing:**  
  - Basic Index (documents managed in a linked list).  
  - Inverted Index (words mapped to documents via linked list).  
  - Inverted Index with BST (words mapped to documents via binary search tree).  

- **Query Processing:**  
  - Boolean retrieval: AND, OR, mixed queries.  
  - Ranked retrieval: documents scored using **term frequency (TF)**.  

- **User Interaction (Menu System):**  
  - Retrieve terms using Index, Inverted Index (LL), or Inverted Index (BST).  
  - Perform Boolean queries.  
  - Run ranked retrieval for relevance-based results.  
  - Display indexed documents and tokens.  

---

## Performance Analysis  

- **Index with Linked List:**  
  - Simple, but linear search → inefficient for large datasets.  

- **Inverted Index with Linked List:**  
  - Faster retrieval than basic indexing, supports Boolean queries.  
  - Still O(n) for word lookup.  

- **Inverted Index with BST:**  
  - Best performance, O(log n) search complexity (balanced).  
  - Suitable for large datasets.  

**Conclusion:**  
The **BST-based inverted index** provides the most efficient retrieval method, while linked lists are sufficient only for small datasets.  

---

## Contributors  

- Ghadeer Alnuwaysir – 444200420  
- Norah Almadhi – 444200890  
- Lamees Alghamdi – 444201177  


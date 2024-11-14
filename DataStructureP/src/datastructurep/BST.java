/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructurep;

/**
 *
 * @author ghade
 */

class BSTNode<T>{
    public String key;
    public T data;
    public BSTNode<T> left, right;
    
    public BSTNode( String key, T data){
        this.key=key;
        this.data=data;
        left=right=null;
    }
            
}
public class BST<T> {
    private BSTNode<T> root, current;
    
    public BST(){
        current=root=null;
    }
    
    public BSTNode<T> getRoot() {
    return root;
}

    public void clear(){
        current=root=null;
    }
    
    public boolean empty(){
        return root==null;
    }
    public boolean full(){
        return false;
    }
        public T retrieve() {
        if (current == null) {
            return null;  // Handle case where current is null
        }
        return current.data;
    }
        
        public boolean FindKey( String k ){
            BSTNode<T> p =root;
            while (p !=null)
            {
                current=p;
                if (k.compareToIgnoreCase(p.key)==0)
                    return true;
                else if (k.compareToIgnoreCase(p.key)<0)
                    p=p.left;
                else 
                    p=p.right;
            
        
            } 
            return false;
            
            
        }
        public void inOrder(BSTNode p){
            if (p==null)
                return;
            inOrder(p.left);
            System.out.println("keys: "+p.key);
              System.out.println(p.data);
              inOrder(p.right);
            
            
        }
        
        public void preOrder(){
            if (root==null)
                  System.out.println("empty tree");
            else
                preOrder(root);
        }
        
    public void preOrder(BSTNode p){
        if (p==null)
            return;
                   System.out.println("keys: "+p.key);
                              System.out.println(p.data.toString());
                              
             preOrder(p.left);
             preOrder(p.right);
        
    }

   public boolean insert(String k, T val) {
    if (root == null) {
     
        current = root = new BSTNode<T>(k, val);
        return true;
    }

    BSTNode<T> p = current;

    if (FindKey(k)) {
        current = p;
        return false;
    }

    BSTNode<T> tmp = new BSTNode<T>(k, val);

    if (k.compareToIgnoreCase(current.key) < 0) {
        current.left = tmp;
    } else {
        current.right = tmp;
    }

    current = tmp;

    return true; 
}

}

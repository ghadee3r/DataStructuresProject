package datastructurep;




    class Node <T> {
    public T data;
    public Node<T> next;
    
    public Node ( T d){
        
        next=null;
        data=d;
    }
    
}
public class LinkedList<T> {
    private Node<T> Head;
    private Node <T> current;
    
    public LinkedList(){
        Head=current=null;
    }
    public boolean isEmpty(){
        return Head==null;
    }
    public boolean last(){
        return current.next==null;
    }
    public boolean full(){
        return false;
    }
    public void findFirst(){
        current=Head;
    }
    public void findNext(){
        current=current.next;
    }
    public void update(T d){
        current.data=d;
    }
    
    public T retrieve() {
        if (current == null) {
            return null;  // Handle case where current is null
        }
        return current.data;
    }
 public void displayWordsInline() {
    if (Head == null) {
        System.out.println("empty");
        return;
    }
    
    Node<T> p = Head;
    StringBuilder line = new StringBuilder();

    while (p != null) {
        line.append(p.data).append(" ");
        p = p.next;
    }
    
    System.out.println(line.toString().trim());  // Print all words in a single line
}

  public void insert(T d) {
        Node <T> p=new Node<T>(d);
        
        if (isEmpty())
        {
           Head=p;
           current=p;
            
        }
        else{
            
            p.next=current.next;
            current.next=p;
            current=p;
        
        }
    }
    public void remove(){
        if( current==Head){
            Head=Head.next;
        }
        
        else{
            
            Node<T>p=Head;
            while(p.next!=current)
                p=p.next;
            
            p.next=current.next;
        }
               if(current.next!=null)
                current=current.next;
                else
                    current=Head;
                }
    
    public void display(){
        if (Head==null)
            System.out.println("empty");
        
        Node<T>p=Head;
        while(p!=null)
        {
            System.out.println(p.data+" ");
            p=p.next;
        }
    }
    
    
    public static void main(String[] args) {
        
        LinkedList<String> L= new LinkedList<String>();
        L.insert("Hello");
        L.insert("world");
        L.remove();
        L.display();
    }
}

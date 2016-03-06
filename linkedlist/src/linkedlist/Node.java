package linkedlist;

/**Node of a singly linked list of ints*/
public class Node {

    private int element; //we assume that elements are character strings
    private Node next;
	
    /**default constructor*/
    public Node(){this(0,null);}
	
    /** Creates a node with the given element 
     * and next node */
    public Node(int s, Node n){element = s; next = n;}	
	
    /** Returns the element of this node */
    public int getElement(){return element;}
	
    /** Returns the next node of this node. */
    public Node getNext(){return next;}

    public boolean hasNext(){return next == null;}
    
    /** Sets the element of this node */
    public void setElement(int s){element = s;}
	
    /** Sets the next field of this node. */
    public void setNext(Node node){next = node;}

    public String toString(){return String.valueOf(element);}
	
}
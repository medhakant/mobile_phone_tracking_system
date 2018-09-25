// Java class for LinkedList
public class LinkedList
{
    public Node head;  // head of list

    //Linked list node
    static class Node {
        Object data;
        Node next;
        Node prev;
        Node(Object d)  { data = d;  next=null; prev=null;} // Constructor
    }
}


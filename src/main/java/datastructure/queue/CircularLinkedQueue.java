package datastructure.queue;

public class CircularLinkedQueue {
    private Node front, rear;
    
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public void insert(int value) { 
        Node next = new Node(value);
        
        if(front == null && rear == null)
            front = rear = next;
        else 
            rear = rear.next = next;
            
        rear.next = front;
    }

    public int delete() { 
        if(isEmpty())
            throw  new RuntimeException("Queue is empty");

        int value = front.value;
        front.value = 0;
        
        if(front == rear) {
            front = null; rear = null;
        } else {
            front = front.next;
            rear.next = front;
        }
            
        return value;
    }
    
    public boolean isEmpty(){
       return front == null && rear == null; 
    }

    @Override
    public String toString() {
        if(front == null) return  "[]";

        StringBuilder builder = new StringBuilder("["  + front.value);

        for (Node c = front.next; c != front  ; c = c.next)
            builder.append(", ").append(c.value);

        return builder.append("]").toString();
    }
    
    public static void main(String[] args) {
        int size = 5;
        CircularLinkedQueue linkedQueue = new CircularLinkedQueue();
        for (int i = 0; i < size; i++) {
            linkedQueue.insert(i);
        }
        System.out.println(linkedQueue);

        System.out.println("Delete ");
        linkedQueue.delete();
        System.out.println(linkedQueue);
//
        for (int i = 0; i < size -1; i++) {
            System.out.print(linkedQueue.delete() + " ");
        }
        System.out.println();
        System.out.println(linkedQueue);

        System.out.println("Is empty " + linkedQueue.isEmpty());

    }
}

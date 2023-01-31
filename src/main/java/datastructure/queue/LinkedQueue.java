package datastructure.queue;

public class LinkedQueue {
    private Node front;
    private Node rear;
    
    static class Node {
        int value;
        Node next;
        
        Node(int value){
            this.value = value;
        }
    }
    
    public void insert(int value){
        if(front == null && rear == null){
           front = rear = new Node(value);
        } else if(front != null && rear != null){
           rear = rear.next = new Node(value);
        } else 
            throw new RuntimeException("");
    }

    public int delete(){
        if(front == null || rear == null) 
            throw new RuntimeException("Queue is empty");
        
        int value = front.value;
        front = front.next;
        
        return value;
    }

    public boolean isEmpty() {
        return front == null;
    }
    
    @Override
    public String toString() {
        if(front == null) return  "[]";
        
        StringBuilder builder = new StringBuilder("["  + front.value);

        for (Node c = front.next; c != null  ; c = c.next)
            builder.append(", ").append(c.value);

        return builder.append("]").toString();
    }

    public static void main(String[] args) {
        int size = 5;
        LinkedQueue linkedQueue = new LinkedQueue();
        for (int i = 0; i < size; i++) {
            linkedQueue.insert(i);
        }

        System.out.println(linkedQueue);

        linkedQueue.delete();
        System.out.println(linkedQueue);
//        System.out.println(arrayStack.change(size - 1, 26));
//        System.out.println(arrayStack.peek(size - 1));
//
        for (int i = 0; i < size -1; i++) {
            System.out.print(linkedQueue.delete() + " ");
        }
        System.out.println();
        System.out.println(linkedQueue);

        System.out.println("Is empty " + linkedQueue.isEmpty());

    }
}

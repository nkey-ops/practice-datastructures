package datastructure.queue;

import java.util.PriorityQueue;
import java.util.Random;

/**
 *  insert O(n)
 *  delete O(1)
 *  peek O(1)
 *  search O(n) 
 */
public class LinkedPriorityQueue {
    Node front;

    static class Node {
        private int priority;
        private Node next;
        private int value;

        public Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    public void insert(int value, int priority) {
        Node node = new Node(value, priority);
        if (front == null) {
            front = node;
        
        } else if (front.priority > priority) {
            node.next = front;
            front = node;
        } else {
            Node c = front;
            while (c.next != null && c.next.priority < priority)
                c = c.next;
                        
            Node o = c.next;
            c.next = node;
            node.next = o;
        }
    }
 
    public int delete(){
        if(isEmpty())
            throw new RuntimeException("Queue is empty");

        int value = front.value;
        front = front.next;
     
        return value;
    }
    
    public int peek(){
        if(isEmpty())
            throw new RuntimeException("Queue is empty");
        
        return front.value;
    }
    
    public boolean isEmpty(){
        return front == null;
    }
    
    @Override
    public String toString() {
        if(front == null) return  "[]";

        StringBuilder builder = new StringBuilder("["  + front.value).append(" : ").append(front.priority);;

        for (Node c = front.next; c != null  ; c = c.next)
            builder.append(" | ").append(c.value)
                    .append(" : ").append(c.priority);

        return builder.append("]").toString();
    }

    public static void main(String[] args) {
        LinkedPriorityQueue priorityQueue = new LinkedPriorityQueue();
        for (int i = 0; i < 10; i++) {
            priorityQueue.insert(1, new Random().nextInt(10));
        }
        System.out.println(priorityQueue);
        
        System.out.println("Peek " + priorityQueue.peek());
        System.out.println("Delete " + priorityQueue.delete());
        System.out.println(priorityQueue);
        
        priorityQueue.insert(1, -1);
        System.out.println(priorityQueue);
    }

}

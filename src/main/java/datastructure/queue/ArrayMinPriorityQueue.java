package datastructure.queue;

import java.util.Arrays;


/**
 * Implementation of Max Priority Queue via Binary Heap using array
 */
public class ArrayMinPriorityQueue {
    private final int[] queue;
    private int size;

    public ArrayMinPriorityQueue(int size) {
        this.queue = new int[size];
    }
    
    
    public void insert(int v) {
        if(size >= queue.length) 
            throw new RuntimeException("Queue is full");
        
        queue[size] = v;

        for (int c = size, p = c / 2 ; queue[c] < queue[p]; 
             c = p, p /= 2) {
            
           swap(c, p); 
        }
        
        size++;
    }
    
    public int delete(){
        if(size <= 0)
            throw new RuntimeException("Queue is empty");

        int v = queue[0];
        queue[0] = queue[size - 1] ;
        queue[size - 1] = 0;

        size--;
        
        for (int c = 0; (leftNode(c) < parent(c) 
                || rightNode(c) < parent(c))
                && c < size - 1 ;) {
            
            if(leftNode(c) < parent(c)){
                swap(c / 2 + 1, c / 2);
                c = c / 2 + 1;
            } else if(rightNode(c) < parent(c)) {
                swap(c / 2 + 2, c / 2);
                c = c / 2 + 2;
            } else 
                return v; 
        }
        
        
        return v;
    }

    private int parent(int i) {
        return queue[i / 2];      
    } 
    
    private int leftNode(int i) {
        return queue[i / 2 + 1];
    }

    private int rightNode(int i) {
        return queue[i / 2 + 2];
    }
    
    private void swap(int i1, int i2) {
        int tmp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = tmp;
    }


    @Override
    public String toString() {
        System.out.println(Arrays.toString(queue));
        System.out.println("PARENT NODE" + "\t" + "LEFT NODE" + "\t" + "RIGHT NODE");
        for (int i = 0; i < queue.length / 2 - 1; i++) {
            System.out.print(" " + queue[i] + "\t\t\t\t" + queue[2 * i + 1]
                    + "\t\t\t\t" + queue[2 * i + 2]);
            System.out.println();
        }
        return "";
    }

    public static void main(String[] args) {
        ArrayMinPriorityQueue queue = new ArrayMinPriorityQueue(10);

        queue.insert(15);
        queue.insert(40);
        queue.insert(12);
        queue.insert(90);
        queue.insert(30);
        queue.insert(50);
        queue.insert(45);

        System.out.println(queue);

        for (int i = 0; i < 7; i++) {
            System.out.println(queue.delete());
            System.out.println(queue);
        }
    }
}

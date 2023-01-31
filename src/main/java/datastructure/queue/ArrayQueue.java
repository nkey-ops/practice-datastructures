package datastructure.queue;

import java.util.Arrays;

public class ArrayQueue {
    private int front = -1;
    private int rear = -1;
    private int[] queue;

    public ArrayQueue(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size is equal or below zero");

        this.queue = new int[size];
    }

    public void insert(int value) {
        if (rear >= queue.length - 1)
            throw new RuntimeException("Queue is full");
        
        if(front == -1 && rear == -1) {
            front = 0; rear = 0;
        }else 
            rear++;
        
        queue[rear] = value;
    }

    public int delete() {
        if (front == -1)
            throw new RuntimeException("Queue is empty");

        int value = queue[front];
        queue[front++] = 0;

        if(front > rear) {
            front = -1; rear = -1;
        }
        
        return value;
    }

    public boolean isFull() {
        return rear >= queue.length - 1;
    }
    
    public boolean isEmpty() {
        return front == -1;
    }

    public void display() {
        System.out.println(Arrays.toString(queue)); 
    }

    public static void main(String[] args) {
        int size = 5;
        ArrayQueue arrayStack = new ArrayQueue(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Is full " + arrayStack.isFull());
            arrayStack.insert(i);
        }
        
        System.out.println("Is full " + arrayStack.isFull());
        arrayStack.display();
        
        arrayStack.delete();
        System.out.println("Is full " + arrayStack.isFull());
//        System.out.println(arrayStack.change(size - 1, 26));
//        System.out.println(arrayStack.peek(size - 1));
//
        for (int i = 0; i < size -1; i++) {
            System.out.print(arrayStack.delete() + " ");
        }
        System.out.println();
        arrayStack.display();

        System.out.println("Is empty " + arrayStack.isEmpty());

    }

}

package datastructure.queue;

import java.util.Arrays;

public class CircularArrayQueue {
    private int size;
    private int rear = -1, front = -1;

    private final int[] queue;

    public CircularArrayQueue(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size cannot be equal or below zero");

        this.queue = new int[size];
    }

    public void insert(int value) {
        if(isFull())
            throw new RuntimeException("Queue is full");
        
        if (front == -1 && rear == -1)
            front = 0;
        else if (front != 0 && rear == queue.length - 1)
            rear = 0;
        
        queue[++rear] = value;
        size++;
    }

    public int delete() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        int value = queue[front];
        queue[front] = 0;
        size--;
        
        if (front < rear) front++; 
        else front--;
        
      
        
        return value;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }


    public boolean isFull() {
        return size == queue.length;
    }


    public boolean isEmpty() {
        return size != queue.length;
    }

    public static void main(String[] args) {
        int size = 5;
        CircularArrayQueue arrayQueue = new CircularArrayQueue(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Is full " + arrayQueue.isFull());
            arrayQueue.insert(i);
        }

        

        System.out.println("Is full " + arrayQueue.isFull());
        System.out.println(arrayQueue);

        System.out.println("Delete: ");
        arrayQueue.delete();
        System.out.println("Is full " + arrayQueue.isFull());
        System.out.println(arrayQueue);
//
        for (int i = 0; i < size - 1; i++) {
            System.out.print(arrayQueue.delete() + " ");
        }
        System.out.println();
        System.out.println(arrayQueue);

        System.out.println("Is empty " + arrayQueue.isEmpty());
    }
}

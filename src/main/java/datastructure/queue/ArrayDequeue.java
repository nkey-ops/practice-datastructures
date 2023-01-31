package datastructure.queue;

import java.util.Arrays;

public class ArrayDequeue {
    private int front = -1, rear = -1;
    private int[] queue;
    private int size;

    public ArrayDequeue(int size) {
        this.queue = new int[size];
    }

    public void insertFront(int value) {
        if (isFull())
            throw new RuntimeException("Queue is full");
        
        if(front == -1) {
            front = 1; rear = 0 ;
        } else if (front == 0) 
            front = queue.length;

        queue[--front] = value;

        size++;
    }

    public void insertRear(int value) {
        if (isFull())
            throw new RuntimeException("Queue is full");

        if (front == -1) front++;
        if (rear == queue.length - 1) 
            rear = 0;

        queue[++rear] = value;

        size++;
    }

    public int deleteFront() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        int value = queue[front];
        queue[front++] = 0;
            
        if(front == queue.length) front = 0;
        
        size--;
        return value;
    }

    public int deleteRear() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        int value = queue[rear];
        queue[rear--] = 0;
        
        if(rear == -1) rear = queue.length - 1;

        size--;
        
        return value;
    }
    
    public int peekFront() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        return queue[front];
    }

    public int peekRear() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        return queue[rear];
    }
        

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == queue.length;
    }


    @Override
    public String toString() {
        return Arrays.toString(queue);
    }

    public static void main(String[] args) {
        int size = 5;
        ArrayDequeue arrayQueue = new ArrayDequeue(size);

        arrayQueue.insertFront(20);
        arrayQueue.insertFront(10);
        arrayQueue.insertRear(30);
        arrayQueue.insertRear(50);
        arrayQueue.insertRear(80);
        System.out.println(arrayQueue);

        System.out.println("Peek at front " + arrayQueue.peekFront());
        System.out.println("Peek at rear "  + arrayQueue.peekRear());

        System.out.println("Delete front " + arrayQueue.deleteFront());
        System.out.println("Delete rear " + arrayQueue.deleteRear());
        System.out.println(arrayQueue);
        
        System.out.println("delete f x2");
        arrayQueue.deleteFront();
        System.out.println(arrayQueue);
        arrayQueue.deleteFront();
        System.out.println(arrayQueue);

        System.out.println("insert f x2");
        arrayQueue.insertFront(9);
        arrayQueue.insertFront(9);
        System.out.println(arrayQueue);

        System.out.println("delete r x1");
        arrayQueue.deleteRear();
        System.out.println(arrayQueue);
        
        System.out.println("insert r x1");
        arrayQueue.insertRear(10);
        System.out.println(arrayQueue);
        

    }
}

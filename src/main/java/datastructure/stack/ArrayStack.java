package datastructure.stack;

import java.util.Arrays;

public class ArrayStack {
    private int top = -1;
    private int[] stack;
    
    public ArrayStack(int size){
        if(size <= 0) 
            throw  new IllegalArgumentException("Size equal or below zero");
        
        this.stack = new int[size];
    }
    
    public void push(int value) {
        if(top + 1 >= stack.length)
            throw new RuntimeException("Stack is over flown");
        
        top++;
        stack[top] = value;
    }
    
    public int pop() {
        if(top - 1 < -1) 
            throw new RuntimeException("Stack is empty");
     
        int value = stack[top];
        stack[top--] = 0;
        return value;
    }

    public boolean isEmpty() {
       return top == -1;
    }

    public boolean isFull() {
       return top == stack.length - 1;
    }
    
    public int peek(int index) {
        if (index < 0 || index >= stack.length)
            throw new IllegalArgumentException(
                    "Index is below zero or bigger than stack length");
        
        return stack[index];
    }
    
    public int change(int index, int value) {
        if (index < 0 || index >= stack.length)
            throw new IllegalArgumentException(
                    "Index is below zero or bigger than stack length");
        int oldValue = stack[index];
        stack[index] = value;
        
        return oldValue;
    }
    public void display() {
        System.out.println(Arrays.toString(stack));
    }
 
    public static void main(String[] args) {
        int size = 5;
        ArrayStack arrayStack = new ArrayStack(size);
        for (int i = 0; i < size; i++) {
            arrayStack.push(i);
        }
        System.out.println("Is full " + arrayStack.isFull());
        arrayStack.display();

        System.out.println(arrayStack.change(size - 1, 26));
        System.out.println(arrayStack.peek(size - 1));
        
        for (int i = 0; i < size; i++) {
            System.out.print(arrayStack.pop() + " ");
        }
        System.out.println();
        arrayStack.display();
        
        System.out.println("Is empty " + arrayStack.isEmpty());
        
    }

}

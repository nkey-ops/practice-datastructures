package datastructure.stack;

public class LinkedListStack {
    int size;
    private Node top;

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public void push(int value) {
        Node node = new Node(value);

        if (top == null) top = node;
        else {
            node.next = top;
            top = node;
        }
    }

    public int pop() {
        if (top == null)
            throw new RuntimeException("Stack is empty");

        int deletedValue = top.value;

        top = top.next;

        return deletedValue;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean isFull() {
        return false;
    }

    public int peek(int index) {
        return top.value;
    }

    public int change(int value) {
        if (top == null)
            throw new RuntimeException("Stack is empty");
        
        int oldValue = top.value;
         top.value = value;

        return oldValue;
    }

    public void display() {
        if(top == null){
            System.out.println("[]");
            return;
        }
        
        StringBuilder builder = new StringBuilder("[" + top.value);

        for (Node cur = top.next; cur.next != null; cur = cur.next) {
            builder.append(", ").append(cur.value);;
        }

        builder.append("]");

        System.out.println(builder);
    }

    public static void main(String[] args) {
        int size = 5;
        LinkedListStack linkedListStack = new LinkedListStack();
        for (int i = 0; i < size; i++) {
            linkedListStack.push(i);
        }
        System.out.println("Is full " + linkedListStack.isFull());
        linkedListStack.display();

        System.out.println(linkedListStack.change( 26));
        System.out.println(linkedListStack.peek(size - 1));

        for (int i = 0; i < size; i++) {
            System.out.print(linkedListStack.pop() + " ");
        }
        System.out.println();
        linkedListStack.display();

        System.out.println("Is empty " + linkedListStack.isEmpty());

    }
}
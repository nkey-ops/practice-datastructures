package datastructure.tree;

import java.util.TreeSet;

public class AVLTreeImproved<T extends Comparable<T>> extends BinarySearchTree<T>{


    private long height(Node<T> node) {
        return node != null ? node.height : -1;
    }
    
    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    
    private long balanceFactor(Node<T> node) {
        if(node == null) return 0;
        return height(node.left) - height(node.right);
    }
    
    
    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftChild = node.left;
        
        node.left = leftChild.right;   
        leftChild.right = node;
        
        updateHeight(node);
        updateHeight(leftChild);
        
        return leftChild;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightChild = node.right;
        
        node.right = rightChild.left;   
        rightChild.left = node;
        
        updateHeight(node);
        updateHeight(rightChild);
        
        return rightChild;
    }

    @Override
    protected Node<T> insert(Node<T> n, T v) {
        Node<T> node = super.insert(n, v);
        
        updateHeight(node);
        return rebalance(node);
    }
    
    private Node<T> rebalance(Node<T> node) {
        long balanceFactor = balanceFactor(node);
        
        if (balanceFactor == 0 ) return node;
        
        if(balanceFactor > 1){
            if(balanceFactor(node.left) >= 0)
                node = rotateRight(node);                
            else{
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        } else if (balanceFactor < -1){
            if (balanceFactor(node.right) <= 0)
                node = rotateLeft(node);

            else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        
        return node;
    }

    public static void main(String[] args) {
        AVLTreeImproved<String> aVLTree = new AVLTreeImproved<>();

        System.out.println("RR rotation");

        aVLTree.insert("H");
        System.out.println(aVLTree);
        aVLTree.insert("I");
        System.out.println(aVLTree);
        aVLTree.insert("J");
        System.out.println(aVLTree);


        System.out.println("LL rotation");

        aVLTree.insert("B");
        System.out.println(aVLTree);
        aVLTree.insert("A");
        System.out.println(aVLTree);


        System.out.println("LR rotation");

        aVLTree.insert("E");
        System.out.println(aVLTree);


        System.out.println("RL rotation");

        aVLTree.insert("C");
        System.out.println(aVLTree);
        aVLTree.insert("F");
        System.out.println(aVLTree);
        aVLTree.insert("D");
        System.out.println(aVLTree);


        System.out.println("LR rotation");

        aVLTree.insert("G");
        System.out.println(aVLTree);


        System.out.println("RR rotation");

        aVLTree.insert("K");
        System.out.println(aVLTree);


        aVLTree.insert("L");
        System.out.println(aVLTree);
    }
}
/* "C:\Program Files\Java\jdk-17.0.1\bin\java.exe" "-javaagent:C:\MyFiles\Programming Staff\IntelliJ IDEA 2022.1.1\lib\idea_rt.jar=53565:C:\MyFiles\Programming Staff\IntelliJ IDEA 2022.1.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\MyFiles\Programming Staff\Eclipse-Workspace\practice-datastructures\out\production\practice-datastructures" datastructure.tree.AVLTreeImproved
RR rotation
└── H

│   ┌── I
└── H

│   ┌── J
└── I
    └── H

LL rotation
│   ┌── J
└── I
    └── H
        └── B

│   ┌── J
└── I
    │   ┌── H
    └── B
        └── A

LR rotation
│       ┌── J
│   ┌── I
└── H
    │   ┌── E
    └── B
        └── A

RL rotation
│       ┌── J
│   ┌── I
└── H
    │   ┌── E
    │   │   └── C
    └── B
        └── A

│       ┌── J
│   ┌── I
└── H
    │       ┌── F
    │   ┌── E
    │   │   └── C
    └── B
        └── A

│       ┌── J
│   ┌── I
└── H
    │       ┌── F
    │   ┌── E
    │   │   └── D
    └── C
        └── B
            └── A

LR rotation
│           ┌── J
│       ┌── I
│   ┌── H
│   │   │   ┌── G
│   │   └── F
└── E
    │   ┌── D
    └── C
        └── B
            └── A

RR rotation
│           ┌── K
│       ┌── J
│       │   └── I
│   ┌── H
│   │   │   ┌── G
│   │   └── F
└── E
    │   ┌── D
    └── C
        └── B
            └── A

│               ┌── L
│           ┌── K
│       ┌── J
│       │   └── I
│   ┌── H
│   │   │   ┌── G
│   │   └── F
└── E
    │   ┌── D
    └── C
        └── B
            └── A
*/
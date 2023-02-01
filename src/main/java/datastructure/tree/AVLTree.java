package datastructure.tree;



public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    @Override
    public void insert(T value) {
        super.insert(value);

        if (!isBalanced())
            balance();
    }

    private void balance() {
        if (isBalanced())
            throw new RuntimeException("Tree is balanced");


        Node<T> unbalancedNode = getUnbalancedNode();
        Node<T> parentNode = searchParent(unbalancedNode.data)
                .orElseThrow();
        
        System.out.println(this);

        if (balance(unbalancedNode) == 2) {
            if (isLL(unbalancedNode))
                balanceLL(parentNode, unbalancedNode);
            else
                balanceLR(parentNode, unbalancedNode);
        } else {
            if (isRR(unbalancedNode))
                balanceRR(parentNode, unbalancedNode);
            else
                balanceRL(parentNode, unbalancedNode);
        }
    }

    private boolean isLL(Node<T> unbalancedNode) {
        return balance(unbalancedNode.left) > 0;
    }

    private boolean isRR(Node<T> unbalancedNode) {
        return balance(unbalancedNode.right) < 0;
    }

    private void balanceRL(Node<T> parent, Node<T> unbalanced) {
        balanceLL(unbalanced, unbalanced.right);
        balanceRR(parent, unbalanced);
    }

    private void balanceLR(Node<T> parent, Node<T> unbalanced) {
        balanceRR(unbalanced, unbalanced.left);
        balanceLL(parent, unbalanced);
    }

    private void balanceLL(Node<T> parent, Node<T> unbalanced) {
        Node<T> lr = unbalanced.left.right;
        unbalanced.left.right = unbalanced;

        if (unbalanced == root)
            root = unbalanced.left;
        else if (parent.left == unbalanced)
            parent.left = unbalanced.left;
        else
            parent.right = unbalanced.left;

        unbalanced.left = lr;
    }

    private void balanceRR(Node<T> parent, Node<T> unbalanced) {
        Node<T> rl = unbalanced.right.left;
        unbalanced.right.left = unbalanced;

        if (unbalanced == root)
            root = unbalanced.right;    
        else if (parent.left == unbalanced)
            parent.left = unbalanced.right;
        else
            parent.right = unbalanced.right;

        unbalanced.right = rl;
    }

    private  Node<T> getUnbalancedNode() {
        return getUnbalancedNode(root);
    }

    private Node<T> getUnbalancedNode(Node<T> node) {
        long balance = balance(node);
        if (balance == 0) return null;

        Node<T> unbalancedNode;
        if (balance >= 1) {
            unbalancedNode = getUnbalancedNode(node.left);
        } else
            unbalancedNode = getUnbalancedNode(node.right);
        
        if(unbalancedNode != null)
            return unbalancedNode;
        else if(balance == 2 || balance == -2) 
          return node;
        else 
            return null;
    }

    private boolean isBalanced() {
        if (root == null)
            return true;

        return balanceHeight(root) != -1;
    }

    private int balanceHeight(Node<T> node) {
        if (node == null)
            return 0;

        // checking left subtree
        int leftSubtreeHeight = balanceHeight(node.left);
        if (leftSubtreeHeight == -1) return -1;
        // if left subtree is not balanced then the entire tree is also not balanced

        //checking right subtree
        int rightSubtreeHeight = balanceHeight(node.right);
        if (rightSubtreeHeight == -1) return -1;
        // if right subtree is not balanced then the entire          tree is also not balanced

        //checking the difference of left and right subtree for current node
        if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
            return -1;
        }
        //if it is balanced then return the height
        return (Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1);
    }

    private long balance(Node<T> node) {
        if (root == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    private long height(Node<T> node) {
        if (node == null) return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        AVLTree<String> aVLTree = new AVLTree<>();

        String[] in = {"H", "I", "J", "B", "A", "E", "C", "F", "D", "G", "K", "L"};

        
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
    
  /* RR rotation
└── H

│   ┌── I
└── H

│       ┌── J
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
    └── H
        └── B
            └── A

│   ┌── J
└── I
    │   ┌── H
    └── B
        └── A

LR rotation
│   ┌── J
└── I
    │   ┌── H
    │   │   └── E
    └── B
        └── A

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
    │   │   │   ┌── D
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
│       ┌── J
│   ┌── I
└── H
    │           ┌── G
    │       ┌── F
    │   ┌── E
    │   │   └── D
    └── C
        └── B
            └── A

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
│               ┌── K
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
}

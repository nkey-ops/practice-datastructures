package datastructure.tree;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class BinarySearchTree<T extends Comparable<T>> {
    protected Node<T> root;

    public static class Node<T> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public Node(T value) {
            this.data = value;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if (right != null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(data.toString()).append("\n");
            if (left != null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            insert(root, value);
        }
    }

    private Node<T> insert(Node<T> n, T v) {
        if (n == null)
            return new Node<>(v);

        if (v.compareTo(n.data) < 0)
            n.left = insert(n.left, v);
        else if (v.compareTo(n.data) > 0)
            n.right = insert(n.right, v);
        else
            return n;

        return n;
    }

    public boolean delete(T v) {
        if (root == null)
            throw new RuntimeException("Tree is empty");

        return delete(root, v);
    }

    private boolean delete(Node<T> node, T v) {
        Node<T> parentNode, deleteNode;

        if (root.data.compareTo(v) == 0) {
            parentNode = deleteNode = root;
        } else {
            Optional<Node<T>> opN = searchParent(node, v);
            if (opN.isEmpty()) return false;
            parentNode = opN.get();

            boolean isLeft = parentNode.left != null && parentNode.left.data.compareTo(v) == 0;
            deleteNode = isLeft ? parentNode.left : parentNode.right;
        }

        if (deleteNode.left == null && deleteNode.right == null) {
            if (deleteNode == root) root = null;

            else if (parentNode.left == deleteNode) parentNode.left = null;
            else parentNode.right = null;

        } else if (deleteNode.left == null || deleteNode.right == null) {
            Node<T> rNode = deleteNode.left == null ? deleteNode.right : deleteNode.left;

            if (deleteNode == root) root = rNode;

            else if (parentNode.left == deleteNode) parentNode.left = rNode;
            else parentNode.right = rNode;

        } else {
            Node<T> minNode = getMinNodeAfter(deleteNode);
            delete(node, minNode.data);
            deleteNode.data = minNode.data;
        }

        return true;
    }

    private Node<T> getMinNodeAfter(Node<T> dNode) {
        Node<T> c = dNode.right;

        while (c.left != null)
            c = c.left;

        return c;
    }


    public Optional<Node<T>> search(T v) {
        return search(root, v);
    }

    private Optional<Node<T>> search(Node<T> node, T v) {
        if (node == null)
            return Optional.empty();

        Optional<Node<T>> result;
        if (v.compareTo(node.data) < 0)
            result = search(node.left, v);
        else if (v.compareTo(node.data) > 0)
            result = search(node.right, v);
        else {
            result = Optional.of(node);
        }

        return result;
    }

    protected Optional<Node<T>> searchParent(T v) {
        if(v == null) return Optional.empty();
        
        return searchParent(root, v);
    }
    
    protected Optional<Node<T>> searchParent(Node<T> curr, T v) {
        if (curr == null)
            return Optional.empty();

        Node<T> p = curr;
        while (curr != null && curr.data.compareTo(v) != 0) {
            p = curr;

            if (v.compareTo(curr.data) < 0)
                curr = curr.left;
            else if (v.compareTo(curr.data) > 0)
                curr = curr.right;

        }

        return curr == null ? Optional.empty() : Optional.of(p);
    }

    /**
     * Returns string representation of the tree in ascending order 
     * Time Complexity O(n)
     */
    public String inorder() {
        return inorder(root, new StringBuilder());
    }

    private String inorder(Node<T> node, StringBuilder builder) {
        if (node == null) return "";

        inorder(node.left, builder);
        builder.append(node.data).append(", ");
        inorder(node.right, builder);

        return builder.toString();
    }

    /**
     * Returns string representation of the tree printing in order 
     * first parent then left and then right node 
     * Time Complexity O(n)
     */
    public String preorder() {
        return preorder(root, new StringBuilder());
    }

    private String preorder(Node<T> node, StringBuilder builder) {
        if (node == null) return "";

        builder.append(node.data).append(", ");
        this.preorder(node.left, builder);
        preorder(node.right, builder);

        return builder.toString();
    }

    /**
     * Returns string representation of the tree using postorder traversal
     * using principal Left-Right-Node 
     * Time Complexity O(n)
     */
    public String postorder() {
        return postorder(root, new StringBuilder());
    }

    private String postorder(Node<T> node, StringBuilder builder) {
        if (node == null) return "";

        this.postorder(node.left, builder);
        postorder(node.right, builder);
        builder.append(node.data).append(", ");

        return builder.toString();
    }

    @Override
    public String toString() {
        if (root == null) return "";
        return root.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Random random = new Random();
        Set<Integer> integers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            int nextInt = random.nextInt(20);
            tree.insert(nextInt);
            integers.add(nextInt);
        }


        System.out.println(tree);
        System.out.println("Inorder " + tree.inorder());
        System.out.println("Preorder " + tree.preorder());
        System.out.println("Postorder " + tree.postorder());

        for (int nextInt : integers) {
            System.out.println("Delete " + nextInt);
            System.out.println("is " + tree.delete(nextInt));
            System.out.println(tree);
        }
    }
}
package datastructure.tree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * BTree is a data structure that minimizes the disk access.
 * <p>
 * 1. For each node x, the keys are stored in increasing order.
 * 2. In each node, there is a boolean value x.leaf which is true if x is a leaf.
 * 3. If n is the order of the tree, each internal node can contain
 * at most n - 1 keys along with a pointer to each child.
 * 4. Each node except root can have at most n children and at least n/2 children.
 * 5. All leaves have the same depth (i.e. height-h of the tree).
 * 6. The root has at least 2 children and contains a minimum of 1 key.
 * 7. If n ≥ 1, then for any n-key B-tree of height h and minimum degree t ≥ 2, h ≥ logt (n+1)/2
 */
public class BTree<T extends Comparable<T>> {
    private Node<T> root;
    private final int order;

    public BTree() {
        this.order = 2;
    }

    public BTree(int order) {
        if(order < 2 )
            throw  new IllegalArgumentException("Order cannot be lower than 2");
        this.order = order;
    }

    static class Node<T extends Comparable<T>> {
        T[] keys;
        Node<T>[] nodes;
        final int order;
        int nKeys;

        final boolean isLeaf;

        public Node(T[] keys, Node<T>[] nodes, int order, boolean isLeaf) {
            this(order, isLeaf);

            for (T key : keys)
                add(key);

            int i = 0;
            for (Node<T> node : nodes) {
                addNode(node, i++);
            }
        }

        public Node(T key, int order, boolean isLeaf) {
            this(order, isLeaf);
            this.keys[nKeys++] = key;
        }

        @SuppressWarnings("unchecked")
        public Node(int order, boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.order = order;

            this.keys = (T[]) Array.newInstance(Comparable.class, 2 * order - 1);
            this.nodes = (Node<T>[]) new Node[2 * order];
        }


        public boolean add(T key) {
            return addKey(key) != -1;
        }

        private int addKey(T key) {
            if (isFull()) return -1;

            int i;
            for (i = 0; i < keys.length && key != null; i++) {
                T currKey = keys[i];

                if (currKey == null) {
                    keys[i] = key;
                    break;
                } else if (key.compareTo(currKey) == 0)
                    return -1;
                else if (key.compareTo(currKey) < 0) {
                    keys[i] = key;
                    key = currKey;
                }
            }

            nKeys++;
            return i;
        }

        private void addNode(Node<T> node, int index) {
            if (index < 0 || index >= nodes.length)
                throw new IllegalArgumentException(
                        "Index cannot be smaller than O, equal or bigger than length of the nodes");

            nodes[index] = node;
        }

        private boolean addKeyAndNodes(T key, Node<T> left, Node<T> right) {
            int keyIndex = addKey(key);

            if (keyIndex == -1) return false;

            addNode(left, keyIndex);
            addNode(right, keyIndex + 1);

            return true;
        }

        public boolean isFull() {
            return keys.length == nKeys;
        }

        public boolean containsKey(T key) {
            if(key == null) return false;

            for (int i = 0; i < nKeys; i++) {
                if(key.compareTo(keys[i]) == 0)
                    return true;
            }
            
            return false;
        }
    }

    /**
     * Inserting an element on a B-tree consists of two events: 
     * <br> searching the appropriate node to insert the element 
     * <br> and splitting the node if required. 
     * <br> Insertion operation always takes place in the bottom-up approach.
     */
    public boolean add(T value) {
        if (root == null)
            root = new Node<>(value, 2, true);
        else
            return add(root, value) != null;

        return true;
    }

    private Node<T> add(Node<T> node, T key) {
        Objects.requireNonNull(node);

        Node<T> leaf = null;

        //if node is root and leaf skip a search for the leaf node
        if(node == root && node.isLeaf ) {
            leaf = node;
            node = null;
        } else {

            if (node.isLeaf) { // return found leaf
                return node;
            }

            for (int i = 0; i < node.nKeys + 1; i++) {  // Search the leaf node for insertion using recursion.
                if (i == node.nKeys || key.compareTo(node.keys[i]) < 0) {
                    leaf = add(node.nodes[i], key);
                    break;
                }
            }
        }

        Objects.requireNonNull(leaf); //we didn't find leaf node !! impossible
            
       // Insert the elements in increasing order.
        if (leaf.isLeaf && !leaf.add(key))
            return null;  //return null -> presumably object was already in the tree

        /* If the node is full
         * split at the median.
         * Push the median key upwards and make the left keys as a left child and the right keys as a right child.*/
        if (leaf.isFull() && !split(leaf, node)) 
            return null; //return null -> presumably object was already in the tree !! impossible

        // if it's a root and full node -> split it 
        if(node == root && node.isFull() && !split(node, null)) 
            return  null;

        return node;
    }

    private boolean split(Node<T> node, Node<T> parent) {
        if (!node.isFull())
            throw new RuntimeException("Cannot split non full array");


        int kMedian = node.keys.length / 2;
        T[] leftKeys = Arrays.copyOf(node.keys, kMedian);
        T[] rightKeys = Arrays.copyOfRange(node.keys, kMedian + 1, node.keys.length);

        int nMedian = node.nodes.length / 2;
        Node<T>[] leftSubNods = Arrays.copyOf(node.nodes, nMedian);
        Node<T>[] rightSubNodes = Arrays.copyOfRange(node.nodes, nMedian, node.nodes.length);


        T medianKey = node.keys[kMedian];
        Node<T> leftNode = new Node<>(leftKeys, leftSubNods, order, node.isLeaf);
        Node<T> rightNode = new Node<>(rightKeys, rightSubNodes, order, node.isLeaf);

        if (parent == null) {
            root = parent = new Node<>(2, false);
        }

        return parent.addKeyAndNodes(medianKey, leftNode, rightNode);
    }


    public void show() {
        show(root, 1);
        System.out.println("-".repeat(30));
    }

    public void show(Node<T> node, int level) {
        if (node == null) {
            return;
        }

        System.out.print("Level : " + level + " " + "Data : ");
        for (int i = 0; i < node.nKeys; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println();
        if (node.isLeaf) {
            return;
        }
        for (int i = 0; i < node.nKeys + 1; i++) {
            show(node.nodes[i], level + 1);
        }
    }

    public static void main(String[] args) {
        BTree<Integer> b = new BTree<>(2);
        b.add(8);
        b.show();
        b.add(9);
        b.show();
        b.add(10);
        b.show();
        b.add(11);
        b.show();
        b.add(15);
        b.show();
        b.add(20);
        b.show();
        b.add(17);
        b.show();
        b.add(21);
        b.show();
        b.add(22);
        b.show();
        b.add(23);
        b.show();
    }

}

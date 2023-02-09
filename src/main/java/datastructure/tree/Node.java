package datastructure.tree;

public class Node<T extends Comparable<T>> {
        public T data;
        public Node<T> left;
        public Node<T> right;

        public long height;

        public Node(T value) {
            this.data = value;
        }
        

        @Override
        public String toString() {
            return data.toString();
        }

        public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
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
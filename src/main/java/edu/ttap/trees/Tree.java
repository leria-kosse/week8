package edu.ttap.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic binary tree implementation.
 */
public class Tree<T extends Comparable<T>> {

    /**
     * A node of the binary tree.
     */
    public static class Node<T> {

        public T value;

        public Node<T> left;

        public Node<T> right;

        /**
         * @param value the value of the node
         * @param left  the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new, empty binary tree.
     */
    public Tree() {
        this.root = null;
    }

    /**
     * @return a sample binary tree for testing purposes
     */
    public static Tree<Integer> makeSampleTree() {
        Tree<Integer> tree = new Tree<Integer>();
        tree.root = new Node<>(
                5,
                new Node<>(2,
                        new Node<>(1),
                        new Node<>(3)),
                new Node<>(8,
                        new Node<>(7,
                                new Node<>(6),
                                null),
                        new Node<>(9,
                                null,
                                new Node<>(10))));
        return tree;
    }

    /**
     * @param node the root of the tree
     * @return the number elements found in this tree rooted at node
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        return sizeH(root);
    }

    private boolean containsH(Node<T> node, T value) {
        if (node == null) {
            return false;
        } else {
            if (node.value.equals(value)) {
                return true;
            } else {
                return containsH(node.left, value) || containsH(node.right, value);
            }
        }
    }

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        return containsH(root, value);
    }

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> elements = new ArrayList<>();
        inOrder(root, elements);
        return elements;
    }

    private void inOrder(Node<T> n, List<T> elements) {
        if (n == null) {
            return;
        }
        inOrder(n.left, elements);
        elements.add(n.value);
        inOrder(n.right, elements);
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> elements = new ArrayList<>();
        preOrder(root, elements);
        return elements;
    }

    /**
     * Performs a pre-order traversal of the tree.
     *
     * @param n the current node
     * @param elements the list to add elements to
     */
    public void preOrder(Node<T> n, List<T> elements) {
        if (n == null) {
            return;
        }
        elements.add(n.value);
        preOrder(n.left, elements);
        preOrder(n.right, elements);
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> elements = new ArrayList<>();
        postOrder(root, elements);
        return elements;
    }

    private void postOrder(Node<T> n, List<T> elements) {
        if (n == null) {
            return;
        }
        postOrder(n.left, elements);
        postOrder(n.right, elements);
        elements.add(n.value);
    }

    /**
     * @return a string represent of this tree in the form, "[x1, ..., xk]."
     *         The order of the elements is left unspecified.
     */
    @Override
    public String toString() {
        List<T> elements = new ArrayList<>();
        preOrder(root, elements);
        return elements.toString();
    }

    /**
     * @return a string represent of this tree in bulleted list form.
     */
    public String toPrettyString() {
        return prettyHelper(root, 0);
    }

    /**
     * Recursive helper for pretty printing the tree.
     *
     * @param node the current node
     * @param depth the current depth in the tree
     * @return a bulleted string representation of the tree
     */
    private String prettyHelper(Node<T> node, int depth) {
        if (node == null) {
            return "";
        }
        String pretty = "  ".repeat(depth);
        return pretty + "- " + node.value + "\n"
                + prettyHelper(node.left, depth + 1)
                + prettyHelper(node.right, depth + 1);
    }

    /**
     * The main driver for this program.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Nothing to do. 'Run' via the JUnit tests instead!");
    }
}
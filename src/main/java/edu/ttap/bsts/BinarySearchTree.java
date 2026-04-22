package edu.ttap.bsts;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {
        public T value;
        
        public Node<T> left;
        
        public Node<T> right;

        /**
         * @param value the value of the node
         * @param left  the left child of the node
         * @param right the right child of the node
         */
        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        public Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param node the root of the tree
     * @return the number of elements in the specified tree
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in this tree
     */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Insertion

    /**
     * Inserts the given value into this binary search tree.
     * 
     * @param v the value to insert
     */
    public void insert(T v) {
        if (root == null) {
            root = new Node<>(v);
        } else {
            insertH(root, v);
        }
    }

    /**
     * Inserts the given value into this binary search tree.
     * 
     * @param n is the node to insert
     * @param value is the value to insert
     */
    public void insertH(Node<T> n, T value) {
        if (n.value.compareTo(value) > 0) {
            if (n.left == null) {
                n.left = new Node<>(value);
            } else {
                insertH(n.left, value);
            }
        } else {
            if (n.right == null) {
                n.right = new Node<>(value);
            } else {
                insertH(n.right, value);
            }
        }
    }

    ///// Part 2: Contains

    /**
     * @param v the value to find
     * @return true iff this tree contains <code>v</code>
     */
    public boolean contains(T v) {
        return containsHelper(root, v);
    }

    private boolean containsHelper(Node<T> node, T v) {
        if (node == null) {
            return false;
        }
        int compare = node.value.compareTo(v);

        if (compare == 0) {

            return true;
        } else if (compare > 0) {

            return containsHelper(node.left, v);
        } else {
            return containsHelper(node.right, v);
        }
    }

    ///// Part 3: Ordered Traversals

    /**
     * @return a list contains the elements of this BST in-order.
     */
    public List<T> toList() {
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
     * @return the (linearized) string representation of this BST
     */
    @Override
    public String toString() {
        return toList().toString();
    }

    ///// Part 4: BST Sorting

    /**
     * @param <T> the carrier type of the lists
     * @param lst the list to sort
     * @return a copy of <code>lst</code> but sorted
     * @implSpec <code>sort</code> runs in ___ time if the tree remains balanced.
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> lst) {
        BinarySearchTree<T> bst = new BinarySearchTree<>();
        for (T element : lst) {
            bst.insert(element);
        }
        return bst.toList();

    }

    ///// Part 5: Deletion

    /*
     * The three cases of deletion are:
     * 1. A leaf (left&right are both null)
     * 2. Either left or right is null
     * 3. Both left&right exist
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code>
     * found
     * in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        if (contains(value)) {
            root = deleteH(root, value);
        }
    }

    private Node<T> deleteH(Node<T> n, T value) {
        if (n == null) {
            return null;
        }
        int compare = n.value.compareTo(value);

        if (compare > 0) {
            // value is smaller: go left
            n.left = deleteH(n.left, value);
        } else if (compare < 0) {
            n.right = deleteH(n.right, value);
        } else {
            // case 1 & 2: one or both children are null
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            }

            // case 3, two kids
            // the
            Node<T> successor = n.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            n.value = successor.value;
            n.right = deleteH(n.right, value);
        }
        return n;

    }
}
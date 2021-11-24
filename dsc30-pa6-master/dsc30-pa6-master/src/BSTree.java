/*
 * Name: XING HONG
 * PID:  A15867895
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Xing Hong
 * @since  2/14/2021
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.dataList = dataList;
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.dataList = new LinkedList<T>();
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            this.dataList.remove(data);
            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        nelems = 0;
        root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int oldSize = getSize();            // to save the older size
        root = insertHelper(root, key);     // run insertion
        return oldSize != getSize();        // determine whether insterted
    }

    /**
     * helper function for insert
     *
     * @param n
     * @param obj
     * @return n - the inserted node
     */
    private BSTNode insertHelper(BSTNode n, T obj) {
        if (n == null) {                                // add root
            n = new BSTNode(null, null, obj);
            nelems++;
        } else {
            int diff = obj.compareTo((T) n.getKey());
            if (diff < 0)   // search left is smaller
                n.setleft(insertHelper(n.getLeft(), obj));
            else if (diff > 0)  // search right if bigger
                n.setright(insertHelper(n.getRight(), obj));
        }
        return n;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return findkeyHelper(root, key);
    }

    /**
     * Helper function for finding key
     *
     * @param n, obj
     * @return result whether found or not
     */
    private boolean findkeyHelper(BSTNode n, T obj) {
        boolean result = false;
        if (n == null) {
            result = false;
        } else {
            int diff = obj.compareTo((T) n.getKey());
            if (diff < 0)   // search left if smaller
                result = findkeyHelper(n.getLeft(), obj);
            else if (diff > 0)      // search right if bigger
                result = findkeyHelper(n.getRight(), obj);
            else if (diff == 0)     // node found
                result = true;
        }
        return result;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (findKey(key) == false) {
            throw new IllegalArgumentException();
        }

        insertDataHelper(root, key, data);
    }

    /**
     * Helper function for inserting
     *
     * @param n, obj
     * @return null
     */
    private T insertDataHelper(BSTNode n, T obj, T data) {
        if (n != null) {
            int diff = obj.compareTo((T) n.getKey());
            if (diff < 0)   // search left if smaller
                return insertDataHelper(n.getLeft(), obj, data);
            else if (diff > 0)  // search right if smaller
                return insertDataHelper(n.getRight(), obj, data);
            else if (diff == 0)     // node found
                n.addNewInfo(data);
        }
        return null;
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key) == false) {
            throw new IllegalArgumentException();
        }
        return (LinkedList<T>) findDataListHelper(root, key);
    }

    /**
     * Helper function for finding key
     *
     * @param n, obj
     * @return null
     */
    private LinkedList<T> findDataListHelper(BSTNode n, T obj) {
        if (n != null) {
            int diff = obj.compareTo((T) n.getKey());
            if (diff < 0) // search left if smaller
                return findDataListHelper(n.getLeft(), obj);
            else if (diff > 0)  // search right if bigger
                return findDataListHelper(n.getRight(), obj);
            else if (diff == 0)     // node found
                return (LinkedList<T>) n.getDataList();
        }
        return null;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        int tempLeft, tempRight;
        if (root == null)
            return -1;
        tempLeft = findHeightHelper(root.getLeft()) + 1;
        tempRight = findHeightHelper(root.getRight()) + 1;
        if (tempLeft > tempRight)
            return tempLeft;
        else
            return tempRight;
    }

    /* * * * * BST Iterator * * * * */

    /**
     * The Iterator of BSTree
     */
    public class BSTree_Iterator implements Iterator<T> {

        Stack<BSTNode> stack;

        /**
         * Initiating the Iterator and stack
         *
         */
        public BSTree_Iterator() {
            stack = new Stack<BSTNode>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * Determine whether the iterator have a next node

         * @return - whether the iterator is at an end
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Iterater move forward to the next node

         * @return - the current node
         */
        public T next() {
            BSTNode node;
            try {
                node = stack.pop();
            } catch (Exception e) {
                throw new NoSuchElementException();
            }

            T result = node.getKey();     // the current node
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }

    /**
     * Initializing the BSTree iterator

     * @return - a new iterator object
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    /**
     * A method that counts the number of nodes at a given level.
     *
     * @return - the number of lever nodes
     */
    public int levelCount(int level) {
        return levelCountHelper(root, level);
    }

    /**
     * Helper function for levercount
     *
     * @return - the number of lever nodes
     */
    private int levelCountHelper(BSTNode n, int level){
        int count = 0;

        if(n != null){
            if(level == 0)
                count = 1;
            else{
                count += levelCountHelper(n.getLeft(), level - 1);
                count += levelCountHelper(n.getRight(), level - 1);
            }
        }
        return count;
    }
}

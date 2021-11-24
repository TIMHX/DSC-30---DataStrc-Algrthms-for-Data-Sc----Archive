/*
 * NAME: XING HONG
 * PID: A15867895
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Xing Hong
 * @since 1/30/2021
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            if (this == head || this == tail) {
                return;
            }

            Node sucNode = this.next;
            Node preNode = this.prev;

            sucNode.prev = preNode;
            preNode.next = sucNode;
        }
    }

    /**
     * Creates a new, empty doubly-linked list by using clear()
     */
    public DoublyLinkedList() {
        clear();
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        // Implementation for throwing exceptions followed by
        // implementation of adding the new data
        if (element == null) throw new NullPointerException();

        Node newNode = new Node(element, this.tail, this.tail.getPrev());
        this.tail.getPrev().setNext(newNode);
        this.tail.setPrev(newNode);
        nelems++;

        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index   to add in the list of certain index
     * @param element the data to put into list
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // Implementation for throwing exceptions followed by
        // implementation of adding the new data
        if (element == null) throw new NullPointerException();
        if (index > this.size() || index < 0) throw new IndexOutOfBoundsException();

        if (index == 0) { // head add, add the node after the head
            Node firstNode = head.next;
            Node newNode = new Node(element, this.head.next, this.head);
            this.head.setNext(newNode);
            firstNode.setPrev(newNode);
            nelems++;
        } else if (index == this.size()) { // tail add, at the node before tail
            add(element);
        } else {                           // add in the middle
            Node CurrentNode = this.getNth(index);
            //add the newNode before the currentNode
            Node newNode = new Node(element, CurrentNode, CurrentNode.getPrev());
            CurrentNode.getPrev().setNext(newNode);
            CurrentNode.setPrev(newNode);
            nelems++;
        }
    }

    /**
     * Clear the linked list by rebuilding the dummies
     */
    @Override
    public void clear() {
        nelems = 0;
        this.head = new Node(null);
        this.tail = new Node(null, null, this.head);
        this.head.setNext(this.tail);
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element is the element we want to determine
     * @return whether the element is in the list
     */
    @Override
    public boolean contains(Object element) {
        if (isEmpty()) {
            return false;
        }

        T data = (T) element;
        Node current = head.next;
        while (current != this.tail && !current.data.equals(data)) { // iterate through the list
            current = current.next;
        }
        if (current == this.tail) {     // end condition
            return false;
        }
        return true;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index of element to get
     * @return the element in the node of certain index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
        return this.getNth(index).getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index is where the node we want to get
     * @return the nth element
     */
    private Node getNth(int index) {
        if (index >= this.size()) {
            return null;
        }
        Node current = this.head;
        for (int i = 0; i <= index; i++) { //iterate
            current = current.getNext();
        }
        return current;
    }

    /**
     * Determine if the list empty
     *
     * @return a boolean whether it's empty
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the location of the node we want to remove
     * @return the data we want from the removed node
     * @throws IndexOutOfBoundsException
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
        Node CurrentNode = this.getNth(index);
        CurrentNode.remove();
        nelems--;
        return CurrentNode.data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index   where to add in list
     * @param element to add
     * @return the data before adding
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null) throw new NullPointerException();
        if (index >= this.size() || index < 0) throw new IndexOutOfBoundsException();

        // alternate the CurrentnNode with NewNode
        Node CurrentNode = this.getNth(index);
        Node newNode = new Node(element, CurrentNode.getNext(), CurrentNode.getPrev());
        try {
            CurrentNode.getPrev().setNext(newNode);
        } catch (NullPointerException e) {
            head.setNext(newNode);
        }
        CurrentNode.getNext().setPrev(newNode);
        return CurrentNode.data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return an int the amount of elements
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return a String return the String representation of the list
     */
    @Override
    public String toString() {
        String result = "";
        Node current = head.next;
        while (current.getNext() != null) {  // iterate before hitting tail
            result += current.getElement().toString();
            if (current.getNext() != null) {
                result += " -> ";
            }
            current = current.getNext();
        }
        return "[(head) -> " + result + "(tail)]";
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Inserts another linked list of the same type into this one
     * <p>
     * This method will splice otherList into the current list at the
     * specified index, such that the node before index is linked to
     * the first non-empty node of otherList, and the node that used to be at the otherList.
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        if (index > this.size() - 1 || index < 0) throw new IndexOutOfBoundsException();

        Node currentNode = this.getNth(index);
        Node preNode = currentNode.getPrev();
        Node otherHead = otherList.head.getNext();
        Node otherTail = otherList.tail.getPrev();

        otherHead.setPrev(preNode);
        otherTail.setNext(currentNode);
        preNode.setNext(otherHead);
        currentNode.setPrev(otherTail);

        this.nelems += otherList.size();
        //otherList.clear();
    }

    /**
     * Determine the starting indices that match the subSequence
     * <p>
     * Determine the starting indices for all match locations of
     * the subSequence. These indices can result in overlapping subsequences.
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        // A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        // TODO: Add implementation to find the starting indices

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}
/*
    Name: XING HONG
    PID:  A15867895
 */

import java.util.EmptyStackException;

/**
 * Using an integer array to implement stack structure with methods
 * @author Xing Hong
 * @since  1/15/2021
 */
public class IntStack {

    /* static constants, feel free to add more if you need */
    private static final int    MIN_INIT_CAPACITY = 5;
    private static final int    RESIZE_FACTOR     = 2;
    private static final double DEF_LOAD_FACTOR   = 0.75;
    private static final double MIN_LOAD_FACTOR   = 0.67;
    private static final double DEF_SHRINK_FACTOR = 0.25;
    private static final double MAX_SHRINK_FACTOR = 0.33;

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int originalCapacity;
    private int capacity;
    private int top;

    /**
     * Initializes a stack with the given capacity, load factor and shrink factor.
     * @param capacity is an int
     * @param loadF is a double as loadfactor
     * @param shrinkF is a double as shrinkfactor
     * @throws IllegalArgumentException if capacity, loadF or shrinkF is out of valid range
     */
    public IntStack(int capacity, double loadF, double shrinkF) {
        if (capacity < MIN_INIT_CAPACITY || loadF < MIN_LOAD_FACTOR || loadF > 1 || shrinkF <= 0
                || shrinkF > MAX_SHRINK_FACTOR) {
            throw new IllegalArgumentException();
        }
        this.data = new int[capacity];
        this.loadFactor = loadF;
        this.shrinkFactor = shrinkF;
        this.nElems = 0;
        this.originalCapacity = capacity;
        this.capacity = capacity;
        this.top = -1;
    }

    /**
     * Initializes a stack with the given capacity, load factor.
     * @param loadF is a double
     * @param capacity is an int
     * @throws IllegalArgumentException if capacity, loadF is out of valid range
     */
    public IntStack(int capacity, double loadF) {
        this(capacity, loadF, DEF_SHRINK_FACTOR);
    }

    /**
     * Initializes a stack with the given capacity.
     * @param capacity is an int
     * @throws IllegalArgumentException if capacity is out of valid range
     */
    public IntStack(int capacity) {
        this(capacity, DEF_LOAD_FACTOR, DEF_SHRINK_FACTOR);
    }

    /** Checks if the stack is empty. Returns true if it is empty, false otherwise.
     * @return a boolean
     */
    public boolean isEmpty() {
        return this.nElems == 0;
    }

    /** Clears all elements in the stack. After clearing, the capacity
     * of stack will be reset to the initial capacity.
     */
    public void clear() {
        this.data = new int[this.originalCapacity];
        this.capacity = originalCapacity;
        this.nElems = 0;
        this.top = -1;
//        for (int i = 0; i < this.originalCapacity; i++) {
//            data[i] = 0;
//        }
    }

    /** Returns the number of elements currently stored in the stack.
     * @return an int
     */
    public int size() {
        return this.nElems;
    }

    /** Returns the maximum number of elements the stack currently can store.
     * In other words, the length of the backed data array.
     * @return an int
     */
    public int capacity() {
       return this.capacity;
    }

    /**
     * Returns the top element of the stack.
     * @return an int
     * @throws EmptyStackException if the stack is empty
     */
    public int peek() {
        if (size() == 0) {
            throw new EmptyStackException();
        } else {
            return data[top];
        }
    }

    /** Pushes the given element to the stack. Double the
     * capacity of the stack before pushing the element if the condition meets.
     * @param element is an int
     */
    public void push(int element) {
        this.nElems = this.nElems + 1;
        double s = size();
        double c = this.capacity;
        if (s / c >= this.loadFactor) {     //expand the stack if needed
            this.capacity = this.capacity * RESIZE_FACTOR;
            int[] newStack = new int[this.capacity];

            for (int i = 0; i < size(); i++) {     //move to the new stack
                newStack[i] = this.data[i];
            }
            this.data = newStack;
        }
        this.top = this.top + 1;            //top +1
        this.data[top] = element;
    }

    /** Returns and removes the top element of the stack. Half the capacity
     * of the stack after popping the element if the condition meets.
     * @return an int
     * @throws EmptyStackException if the stack is empty
     */
    public int pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        int out = this.data[top];
        this.top = top - 1;

        this.nElems = this.nElems - 1;
        double s = size();
        double c = this.capacity;
        if (s / c <= this.shrinkFactor) {       //shrink if needed
            this.capacity = this.capacity / RESIZE_FACTOR;
            if (capacity < originalCapacity) {
                this.capacity = originalCapacity;
            }
            int[] newStack = new int[this.capacity];

            for (int i = 0; i < size(); i++) {  //move to the new
                newStack[i] = this.data[i];
            }
            this.data = newStack;
        }
        return out;
    }

    /** Pushes all numbers in the array elems to the stack.
     * @param elements is an int array
     * @throws IllegalArgumentException if elems is null
     */
    public void multiPush(int[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < elements.length; i++) {
            push(elements[i]);
        }
    }

    /** Pops the given amount of elements from the stack. If the stack
     * does not have the given amount of elements, pop all elements from the stack.
     * @param amount is an int
     * @throws IllegalArgumentException if amount is not a positive number
     */
    public int[] multiPop(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        if (amount > size()) {
            amount = size();
        }
        int[] out = new int[amount];

        for (int i = 0; i < amount; i++) {
            out[i] = pop();
        }
        return out;
    }
}

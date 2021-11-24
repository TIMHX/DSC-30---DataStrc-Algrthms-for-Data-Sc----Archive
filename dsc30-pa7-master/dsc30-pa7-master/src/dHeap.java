/*
 * Name: Xing Hong
 * PID:  A15867895
 */

import java.util.*;

/**
 * Create Arraybased heaps with branching factor d
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    public T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private static final int Default_size = 6;
    private static final int Double = 2;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(Default_size);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(Double, heapSize, true);
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        this.isMaxHeap = isMaxHeap;
        this.d = d;
        nelems = 0;
        heap = (T[]) new Comparable[heapSize];
        for (int i = 0; i < heap.length; i++) {
            heap[i] = null;
        }
    }

    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Adds the given data to the heap.
     *
     * @param data to add
     * @throws NullPointerException if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }

        if (size() == heap.length) {
            resize();
        }

        heap[nelems] = data;
        bubbleUp(nelems);
        nelems++;
    }

    /**
     * Returns and removes the root element from the heap.
     *
     * @return the root
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        T root = heap[0];
        heap[0] = heap[nelems - 1];
        nelems--;
        trickleDown(0);
        return root;
    }

    /**
     * Clear all elements in the heap.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        nelems = 0;
        heap = (T[]) new Comparable[Default_size];
    }

    /**
     * Returns the root element of the heap.
     *
     * @return root
     * @throws NoSuchElementException if the heap is empty
     */
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * Trickledown after remove the root
     *
     * @param index where we start below the root
     */
    private void trickleDown(int index) {
        int child;
        T t = heap[index];
        int childIndex = d * index + 1;

        if (isMaxHeap) {
            while (childIndex < nelems) {
                child = findMax(index);
                if (heap[child].compareTo(t) > 0) {
                    heap[index] = heap[child];
                } else {
                    break;
                }
                index = child;
            }
        } else {
            while (childIndex < nelems) {
                child = findMin(index);
                if (heap[child].compareTo(t) < 0) {
                    heap[index] = heap[child];
                } else {
                    break;
                }
                index = child;
            }
        }
        heap[index] = t;
    }

    /**
     * Find max child
     *
     * @param index for the given parent
     * @return the index of the child
     */
    private int findMax(int index) {
        int t = d * index + 1;
        int p = d * index + d;
        int k = t;
        T a = heap[t];


        for (int i = t+1; i <= p; i++) {
            if(a.compareTo(heap[t]) < 0){
                a = heap[i];
                k = i;
            }
        }

        return k;
    }

    /**
     * Find min child
     *
     * @param index for the given parent
     * @return
     */
    private int findMin(int index) {
        int t = d * index + 1;
        int p = d * index + d;
        int k = t;
        T a = heap[t];


        for (int i = t+1; i <= p; i++) {
            if(a.compareTo(heap[t]) > 0){
                a = heap[i];
                k = i;
            }
        }

        return k;
    }

    /**
     * For bubble up
     *
     * @param index the start point
     */
    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / d;
        int i = index;
        if (index == 0) {
            return;
        }
        if (isMaxHeap) {
            if (heap[parentIndex].compareTo(heap[index]) < 0) {
                i = parentIndex;
            }
        } else {
            if (heap[parentIndex].compareTo((heap[index])) > 0) {
                i = parentIndex;
            }
        }
        if (i != index) {
            T t = heap[parentIndex];
            heap[parentIndex] = heap[index];
            heap[index] = t;
            bubbleUp(i);
        }
    }

    /**
     * double the size of array before adding if is full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newHeap = (T[]) new Comparable[Double * this.heap.length];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }

    /**
     * find the index of parent
     *
     * @param index the index of child
     * @return the index of its parent
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

}

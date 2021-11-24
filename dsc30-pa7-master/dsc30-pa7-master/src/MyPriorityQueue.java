/*
 * Name: Xing Hong
 * PID:  A15867895
 */

/**
 *
 * @param <T> Generic type
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     *
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        pQueue = new dHeap<T>(5, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @return returns true
     * @throws NullPointerException if the element received is null.
     */
    public boolean offer(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        pQueue.add(element);
        return false;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        if (pQueue.size() == 0) {
            return null;
        }
        return pQueue.remove();
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     *
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        if (pQueue.size() == 0) {
            return null;
        } else {
            return pQueue.element();
        }
    }

    /**
     * Return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (pQueue.size() == 0) {
            return true;
        } else {
            return false;
        }

    }
}

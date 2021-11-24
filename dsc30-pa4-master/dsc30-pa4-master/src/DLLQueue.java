/*
 * NAME: Xing Hong
 * PID: A15867895
 */

/**
 * Queue implementation using Doubly-linked list.
 * @param <T> generic container
 * @author Xinng Hong
 * @since 2/1/2021
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    /**
     * Initialize the instance variables of this queue
     * */
    public DLLQueue() {
        this.queue = new DoublyLinkedList<>();
    }

    /**
     * Return the number of elements currently stored in this queue.
     * @return the queue size
     * */
    public int size() {
        return this.queue.size();
    }

    /**
     * Return true if this queue is empty, false otherwise.
     * @return whether the queue is empty
     * */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Add the given data to this queue.
     * @throws IllegalArgumentException
     * */
    public void enqueue(T data) {
        if(data == null){
            throw new IllegalArgumentException();
        }
        this.queue.add(data);
    }

    /**
     * Remove and return the top element from this stack/queue.
     * Return null if this queue has no elements.
     * @return whether the removed value or null if empty
     * */
    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        return this.queue.remove(0);
    }

    /**
     * Peek and return the top element from this queue.
     * Return null if this queue has no elements.
     * @return the peek value or null if empty
     * */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.queue.get(0);
    }

}

/*
 * NAME: Xing Hong
 * PID: A15867895
 */

/**
 * Stack implementation using Doubly-linked list.
 * @param <T> generic container
 * @author Xing Hong
 * @since 2/1/2021
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    /**
     * Initialize the instance variables of this stack
     * */
    public DLLStack() {
        this.stack = new DoublyLinkedList<T>();
    }

    /**
     * Return the number of elements currently stored in this stack.
     * @return the stack size
     * */
    public int size() {
        return this.stack.size();
    }

    /**
     * Return true if this stack is empty, false otherwise.
     * @return whether the stack is empty
     * */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Add the given data to this queue.
     * @throws IllegalArgumentException
     * */
    public void push(T data) {
        if(data == null){
            throw new IllegalArgumentException();
        }
        this.stack.add(data);
    }

    /**
     * Remove and return the top element from this stack/queue.
     * Return null if this queue has no elements.
     * @return whether the removed value or null if empty
     * */
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.remove(this.size() - 1);
    }

    /**
     * Peek and return the top element from this stack.
     * Return null if this stack has no elements.
     * @return the peek value or null if empty
     * */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.get(size()-1);
    }

}

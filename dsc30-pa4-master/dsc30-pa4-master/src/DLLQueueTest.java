import org.junit.Test;

import static org.junit.Assert.*;

public class DLLQueueTest {
    DLLQueue t1 = new DLLQueue();
    DLLQueue t2 = new DLLQueue();
    DLLQueue t3 = new DLLQueue();

    @Test
    public void size() {
        t1.enqueue("1");
        t1.enqueue("2");
        t1.enqueue("3");
        assertEquals(3,t1.size());

        t2.enqueue(1);
        t2.enqueue(2);
        t2.enqueue(3);
        assertEquals(3,t2.size());

        assertEquals(0,t3.size());
    }

    @Test
    public void isEmpty() {
        t1.enqueue("1");
        t1.enqueue("2");
        t1.enqueue("3");
        assertFalse(t1.isEmpty());

        t2.enqueue(1);
        t2.enqueue(2);
        t2.enqueue(3);
        assertFalse(t2.isEmpty());

        assertTrue(t3.isEmpty());
    }

    @Test
    public void enqueue() {
        t1.enqueue("1");
        t1.enqueue("2");
        t1.enqueue(3);

        assertEquals("1", t1.dequeue());
        assertEquals("2", t1.dequeue());
        assertEquals(3, t1.dequeue());
    }

    @Test
    public void dequeue() {
        t1.enqueue("1");
        t1.enqueue("2");
        t1.enqueue(3);

        assertEquals("1", t1.dequeue());
        assertEquals("2", t1.dequeue());
        assertEquals(3, t1.dequeue());
    }

    @Test
    public void peek() {
        t1.enqueue("1");
        t1.enqueue("2");
        t1.enqueue(3);

        assertEquals("1", t1.peek());
        t1.dequeue();
        assertEquals("2", t1.peek());
        t1.dequeue();
        assertEquals(3, t1.peek());
    }
}
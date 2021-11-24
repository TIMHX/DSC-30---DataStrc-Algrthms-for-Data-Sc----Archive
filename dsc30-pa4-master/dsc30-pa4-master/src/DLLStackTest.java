import org.junit.Test;

import static org.junit.Assert.*;

public class DLLStackTest {

    DLLStack t1 = new DLLStack();
    DLLStack t2 = new DLLStack();
    DLLStack t3 = new DLLStack();

    @Test
    public void size() {
        t1.push("1");
        t1.push("2");
        t1.push("3");
        assertEquals(3,t1.size());

        t2.push(1);
        t2.push(2);
        t2.push(3);
        assertEquals(3,t2.size());

        assertEquals(0,t3.size());
    }

    @Test
    public void isEmpty() {
        t1.push("1");
        t1.push("2");
        t1.push("3");
        assertFalse(t1.isEmpty());

        t2.push(1);
        t2.push(2);
        t2.push(3);
        assertFalse(t2.isEmpty());

        assertTrue(t3.isEmpty());
    }

    @Test
    public void push() {
        t1.push("1");
        t1.push("2");
        t1.push(1);

        assertEquals(1, t1.pop());
        assertEquals("2", t1.pop());
        assertEquals("1", t1.pop());
    }

    @Test
    public void pop() {
        t1.push("1");
        t1.push("2");
        t1.push(1);

        assertEquals(1, t1.pop());
        assertEquals("2", t1.pop());
        assertEquals("1", t1.pop());
    }

    @Test
    public void peek() {
        t1.push("1");
        t1.push("2");
        t1.push(1);

        assertEquals(1, t1.peek());
        t1.pop();
        assertEquals("2", t1.peek());
        t1.pop();
        assertEquals("1", t1.peek());
    }
}
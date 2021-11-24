import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IntStackTest {
    IntStack stackA = new IntStack(5, 0.75, 0.2);
    IntStack stackB = new IntStack(6, 0.75, 0.2);
    IntStack stackC = new IntStack(7, 0.75, 0.2);

    IntStack stackD = new IntStack(5, 0.75);
    IntStack stackE = new IntStack(6, 0.75);
    IntStack stackF = new IntStack(7, 0.75);

    IntStack stackG = new IntStack(5);
    IntStack stackH = new IntStack(6);
    IntStack stackI = new IntStack(7);

    @Test
    public void IntStack() {
        for (int i = 1; i < 4; i++) {
            stackA.push(i);
            stackD.push(i);
            stackG.push(i);
        }
        assertFalse(stackA.isEmpty());
        assertEquals(3, stackA.size());
        assertEquals(5, stackA.capacity());

        assertFalse(stackD.isEmpty());
        assertEquals(3, stackD.size());
        assertEquals(5, stackD.capacity());

        assertFalse(stackG.isEmpty());
        assertEquals(3, stackG.size());
        assertEquals(5, stackG.capacity());

        assertEquals(3, stackA.pop());
        assertEquals(3, stackD.pop());
        assertEquals(3, stackG.pop());

        assertEquals(2, stackA.size());
        assertEquals(2, stackD.size());
        assertEquals(2, stackG.size());

        assertEquals(2, stackA.pop());
        assertEquals(2, stackD.pop());
        assertEquals(2, stackG.pop());

        assertEquals(1, stackA.size());
        assertEquals(1, stackD.size());
        assertEquals(1, stackG.size());
    }


    @Test
    public void isEmpty() {
        for (int i = 1; i < 5; i++) {
            stackB.push(i);
        }
        assertFalse(stackB.isEmpty());

        stackB.pop();
        assertFalse(stackB.isEmpty());

        for (int i = 0; i < 3; i++) {
            stackB.pop();
        }
        assertTrue(stackB.isEmpty());
    }

    @Test
    public void clear() {
        for (int i = 1; i < 6; i++) {
            stackC.push(i);
            stackF.push(i);
            stackI.push(i);
        }
        stackC.clear();
        stackF.clear();
        stackI.clear();

        assertTrue(stackC.isEmpty());
        assertTrue(stackF.isEmpty());
        assertTrue(stackI.isEmpty());
    }

    @Test
    public void size() {
        for (int i = 1; i < 5; i++) {
            stackE.push(i);
        }

        stackE.pop();
        assertEquals(3, stackE.size());

        stackE.pop();
        assertEquals(2, stackE.size());

        stackE.pop();
        assertEquals(1, stackE.size());

        stackE.pop();
        assertEquals(0, stackE.size());

    }

    @Test
    public void capacity() {
        stackH.push(1);
        assertEquals(6, stackH.capacity());

        stackH.push(2);
        assertEquals(6, stackH.capacity());

        stackH.push(3);
        assertEquals(6, stackH.capacity());

        stackH.push(4);
        assertEquals(6, stackH.capacity());

        stackH.push(5);
        assertEquals(12, stackH.capacity());
    }

    @Test
    public void peek() {
        for (int i = 1; i < 5; i++) {
            stackF.push(i);
            assertEquals(i, stackF.peek());
        }

        stackB.push(1);
        assertEquals(1, stackB.peek());

        stackB.push(100);
        assertEquals(100, stackB.peek());
    }

    @Test
    public void push() {
        for (int i = 1; i < 10; i++) {
            stackC.push(i);
        }
        assertFalse(stackC.isEmpty());
        assertEquals(14, stackC.capacity());

        for (int i = 1; i < 5; i++) {
            stackF.push(i);
        }
        assertFalse(stackF.isEmpty());
        assertEquals(7, stackF.capacity());

        stackB.push(1);
        assertEquals(1, stackB.pop());

        stackB.push(2);
        assertEquals(2, stackB.pop());

        stackB.push(3);
        assertEquals(3, stackB.pop());
    }

    @Test
    public void pop() {
        for (int i = 1; i < 10; i++) {
            stackC.push(i);
        }

        assertEquals(9, stackC.pop());
        assertEquals(8, stackC.pop());
        assertEquals(7, stackC.pop());

        for (int i = 0; i < 4; i++) {
            stackC.pop();
        }
        assertEquals(7, stackC.capacity());


    }

    @Test
    public void multiPush() {
        stackC.multiPush(new int[]{1, 2, 3, 4, 5});
        assertEquals(7, stackC.capacity());

        assertEquals(5, stackC.pop());
        assertEquals(4, stackC.pop());
        assertEquals(3, stackC.pop());

        stackB.multiPush(new int[]{1, 2, 3, 4, 5});
        assertEquals(5, stackB.pop());
        assertEquals(4, stackB.pop());
        assertEquals(3, stackB.pop());

    }

    @Test
    public void multiPop() {
        stackC.multiPush(new int[]{1, 2, 3, 4, 5});
        int[] out = stackC.multiPop(5);
        int[] expected = new int[]{5, 4, 3, 2, 1};
        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], out[i]);
        }
        assertTrue(stackC.isEmpty());

        stackA.multiPush(new int[]{1, 2, 3, 4, 5});
        stackA.multiPop(4);
        assertEquals(5, stackA.capacity());
    }

    @Test
    public void multiPushPop() {
        stackC.multiPop(100);
        stackC.multiPush(new int[100]);
        assertEquals(100, stackC.size());
        int[] out = stackC.multiPop(101);
        int[] expected = new int[100];
        assertArrayEquals(expected, out);
        assertEquals(expected.length, out.length);
        assertTrue(stackC.isEmpty());
        assertEquals(0, stackC.size());
        assertEquals(7, stackC.capacity());
    }
}

import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    DoublyLinkedList<String> t1 = new DoublyLinkedList<>();
    DoublyLinkedList<String> t2 = new DoublyLinkedList<>();
    DoublyLinkedList<Integer> t3 = new DoublyLinkedList<>();

    @Test
    public void add() {
        t1.add("001");
        t1.add("002");
        t1.add("Hello");

        assertEquals("001", t1.get(0));
        assertEquals("002", t1.get(1));
        assertEquals("Hello", t1.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointerExceptionInAdd() {
        t1.add(null);
        t2.add(null);
        t3.add(null);
    }

    @Test
    public void testAdd() {
        t2.add(0, "001");
        t2.add(1, "002");
        t2.add(2, "Hello");

        assertEquals("001", t2.get(0));
        assertEquals("002", t2.get(1));
        assertEquals("Hello", t2.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointerExceptionInTestAdd() {
        t1.add(0, null);
        t2.add(0, null);
        t3.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInTestAdd1() {
        t1.add(-1, "1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInTestAdd2() {
        t3.add(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInTestAdd3() {
        t1.add(1, "1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInTestAdd4() {
        t2.add(2, "2");
    }


    @Test
    public void clear() {
        t1.add("001");
        t1.add("002");
        t1.add("Hello");
        t1.clear();
        assertTrue(t1.isEmpty());


        t1.add("003");
        assertEquals("003", t1.get(0));
        t1.clear();
        assertTrue(t1.isEmpty());

        t2.clear();
        t2.add("003");
        t2.add("Hello");
        t2.clear();
        assertTrue(t2.isEmpty());

        t3.clear();
        t3.add(1);
        t3.add(2);
        t3.clear();
        assertTrue(t3.isEmpty());

    }

    @Test
    public void get() {
        t3.add(1);
        t3.add(2);
        t3.add(3);

        assertEquals(new Integer(1), t3.get(0));
        assertEquals(new Integer(2), t3.get(1));
        assertEquals(new Integer(3), t3.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInGet() {
        t2.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInGet2() {
        t3.add(1);
        t3.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInGet3() {
        t1.add("1");
        t1.add("1");
        t1.add("1");
        t1.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInGet4() {
        t1.get(-1);
    }


    @Test
    public void isEmpty() {
        t1.add("001");
        t2.add("001");

        assertFalse(t1.isEmpty());
        assertFalse(t2.isEmpty());
        assertTrue(t3.isEmpty());

        t2.remove(0);
        assertTrue(t2.isEmpty());
    }

    @Test
    public void remove() {
        t2.add(0, "001");
        t2.add(1, "002");
        t2.add(2, "Hello");

        assertEquals("Hello", t2.remove(2));
        assertEquals("002", t2.remove(1));
        assertEquals("001", t2.get(0));
        assertEquals(1, t2.size());
        assertEquals("001", t2.remove(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInRemove() {
        t2.add("001");
        t2.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInRemove2() {
        t2.add("001");
        t2.add("001");
        t2.add("001");
        t2.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInRemove3() {
        t2.remove(0);
    }

    @Test
    public void set() {

        t2.add(0, "001");
        t2.add(1, "002");
        t2.add(2, "Hello");

        t2.set(0, "0001");
        assertEquals("0001", t2.get(0));

        t2.set(1, "0002");
        assertEquals("0002", t2.get(1));

        t2.set(2, "0003");
        assertEquals("0003", t2.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInzSet() {
        t1.set(1, "1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInzSet2() {
        t2.set(-1, "1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutOfBoundsExceptionInzSet3() {
        t3.add(1);
        t3.add(1);
        t3.add(1);
        t3.set(3, 1);
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerExceptionInzSet() {
        t1.set(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerExceptionInzSet2() {
        t2.set(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerExceptionInzSet3() {
        t3.set(1, null);
    }

    @Test
    public void size() {
        t1.add(0, "001");
        t1.add(1, "002");
        t1.add(2, "Hello");

        t2.add(0, "001");
        t2.add(1, "002");
        t2.add(2, "Hello");

        assertEquals(3, t1.size());
        assertEquals(3, t2.size());
        assertEquals(0, t3.size());

    }

    @Test
    public void testContains() {
        t2.add("001");
        t2.add("002");
        t2.add("003");

        assertTrue(t2.contains("001"));
        assertTrue(t2.contains("002"));
        assertTrue(t2.contains("003"));


        assertFalse(t1.contains("001"));
        t1.add("001");
        assertFalse(t1.contains("002"));
        t1.add("002");
        assertFalse(t1.contains("003"));
        t1.add("003");

        assertTrue(t1.contains("001"));
        assertTrue(t1.contains("002"));
        assertTrue(t1.contains("003"));

        assertFalse(t3.contains(1));
        t3.add(1);
        assertFalse(t3.contains(2));
        t3.add(2);
        assertFalse(t3.contains(3));
        t3.add(3);

        assertTrue(t3.contains(3));
        assertTrue(t3.contains(2));
        assertTrue(t3.contains(1));
    }

    @Test
    public void testToString() {
        assertEquals("[(head) -> (tail)]", t2.toString());
        t2.add(0, "001");
        t2.add(1, "002");
        t2.add(2, "Hello");

        assertEquals("[(head) -> (tail)]", t1.toString());
        t1.add("001");
        t1.add("002");
        t1.add("Hello");

        assertEquals("[(head) -> (tail)]", t3.toString());
        t3.add(0, 3);
        t3.add(1, 4);
        t3.add(2, 5);

        assertEquals("[(head) -> 001 -> 002 -> Hello -> (tail)]", t1.toString());
        assertEquals("[(head) -> 001 -> 002 -> Hello -> (tail)]", t2.toString());
        assertEquals("[(head) -> 3 -> 4 -> 5 -> (tail)]", t3.toString());
    }

    @Test
    public void splice() {
        t1.add("A");
        t1.add("B");
        t1.add("C");

        t2.add("D");
        t2.add("E");
        t2.add("F");

        t1.splice(1, t2);
        assertEquals(6, t1.size());
        assertEquals(3, t2.size());

        assertEquals("[(head) -> A -> D -> E -> F -> " +
                "B -> C -> (tail)]", t1.toString());
        assertEquals("[(head) -> D -> E -> F -> B -> C -> (tail)]", t2.toString());
    }

    @Test
    public void slice2() {
        t2.add("A");
        t2.add("B");
        t2.add("C");

        t1.add("D");
        t1.add("E");

        t1.splice(1, t2);
        assertEquals(5, t1.size());
        assertEquals(3, t2.size());

        assertEquals("[(head) -> D -> A -> B -> C -> E -> (tail)]", t1.toString());
        assertEquals("[(head) -> A -> B -> C -> E -> (tail)]", t2.toString());
    }

    @Test
    public void slice3() {
        t1.add("A");
        t1.add("B");
        t1.add("C");

        t2.add("D");
        t2.add("E");

        t1.splice(0, t2);
        assertEquals(5, t1.size());
        assertEquals(2, t2.size());

        assertEquals("[(head) -> D -> E -> A -> B -> " +
                "C -> (tail)]", t1.toString());
        assertEquals("[(head) -> D -> E -> A -> B -> C -> (tail)]", t2.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestSliceIndexOutOfBoundsException1(){
        t1.add("A");
        t1.add("B");
        t1.add("C");

        t2.add("D");
        t2.add("E");

        t1.splice(-1, t2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestSliceIndexOutOfBoundsException2(){
        t1.add("A");
        t1.add("B");
        t1.add("C");

        t2.add("D");
        t2.add("E");

        t1.splice(3, t2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestSliceIndexOutOfBoundsException3(){
        t1.add("A");
        t1.add("B");
        t1.add("C");

        t2.add("D");
        t2.add("E");

        t2.splice(3, t1);
    }

    @Test
    public void match() {
    }
}
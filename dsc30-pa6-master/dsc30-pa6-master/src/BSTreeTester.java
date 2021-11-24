import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree TreeA = new BSTree();
    BSTree TreeB = new BSTree();
    BSTree TreeC = new BSTree();


    @org.junit.Test
    public void getRoot() {
        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);
        TreeC.insert(4);
        TreeC.insert(1);

        assertNull(TreeA.getRoot());
        assertEquals(1,TreeB.getRoot().getKey());
        assertEquals(3,TreeC.getRoot().getKey());
    }

    @org.junit.Test
    public void getSize() {
        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);

        assertEquals(0,TreeA.getSize());
        assertEquals(5,TreeB.getSize());
        assertEquals(3,TreeC.getSize());
    }

    @org.junit.Test
    public void insert() {
        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);
        TreeC.insert(4);
        TreeC.insert(1);

        assertEquals(4,TreeB.findHeight());
        assertEquals(2,TreeC.findHeight());
        assertTrue(TreeC.findKey(5));
    }

    @org.junit.Test
    public void findKey() {
        TreeB.insert("4");
        TreeB.insert("5");

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);

        assertFalse(TreeA.findKey(1));
        assertTrue(TreeB.findKey("4"));
        assertTrue(TreeC.findKey(3));
    }

    @org.junit.Test
    public void insertData() {
        TreeB.insert(1);
        TreeB.insertData(1,"Hello");
        TreeB.insertData(1,"Hola");
        TreeB.insert(2);
        TreeB.insertData(2,"World");
        TreeB.insert(3);
        TreeB.insertData(3,"Alien");
        TreeB.insertData(3,"Bonjour");

        TreeC.insert(3);
        TreeC.insertData(3,"Hello");
        TreeC.insertData(3,12345);
        TreeC.insert(2);
        TreeC.insertData(2,98765);
        TreeC.insertData(2,"Testing");

        assertEquals("Hello",TreeB.findDataList(1).get(0));
        assertEquals("Hola",TreeB.findDataList(1).get(1));

        assertEquals("World",TreeB.findDataList(2).get(0));
        assertEquals("Alien",TreeB.findDataList(3).get(0));
        assertEquals("Bonjour",TreeB.findDataList(3).get(1));

        assertEquals("Hello",TreeC.findDataList(3).get(0));
        assertEquals(12345,TreeC.findDataList(3).get(1));
        assertEquals(98765,TreeC.findDataList(2).get(0));
        assertEquals("Testing",TreeC.findDataList(2).get(1));
    }

    @org.junit.Test
    public void findDataList() {
        TreeB.insert(1);
        TreeB.insertData(1,"Hello");
        TreeB.insertData(1,"Hola");
        TreeB.insert(2);
        TreeB.insertData(2,"World");
        TreeB.insert(3);
        TreeB.insertData(3,"Alien");
        TreeB.insertData(3,"Bonjour");

        TreeC.insert(3);
        TreeC.insertData(3,"Hello");
        TreeC.insertData(3,12345);
        TreeC.insert(2);
        TreeC.insertData(2,98765);
        TreeC.insertData(2,"Testing");

        assertEquals("Hello",TreeB.findDataList(1).get(0));
        assertEquals("Hola",TreeB.findDataList(1).get(1));

        assertEquals("World",TreeB.findDataList(2).get(0));
        assertEquals("Alien",TreeB.findDataList(3).get(0));
        assertEquals("Bonjour",TreeB.findDataList(3).get(1));

        assertEquals("Hello",TreeC.findDataList(3).get(0));
        assertEquals(12345,TreeC.findDataList(3).get(1));
        assertEquals(98765,TreeC.findDataList(2).get(0));
        assertEquals("Testing",TreeC.findDataList(2).get(1));
    }

    @org.junit.Test
    public void findHeight() {
        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);
        TreeC.insert(4);
        TreeC.insert(1);

        assertEquals(-1,TreeA.findHeight());
        assertEquals(4,TreeB.findHeight());
        assertEquals(2,TreeC.findHeight());

    }


    @org.junit.Test
    public void iterator() {
        TreeA.insert("1");
        TreeA.insert("2");
        TreeA.insert("3");
        TreeA.insert("4");


        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);
        TreeC.insert(4);
        TreeC.insert(1);

        Iterator<Object> iterB = TreeB.iterator();
        Iterator<Object> iterC = TreeC.iterator();
        Iterator<Object> iterA = TreeA.iterator();

        assertTrue(iterA.hasNext());
        assertEquals("1",iterA.next());

        assertTrue(iterA.hasNext());
        assertEquals("2",iterA.next());

        assertTrue(iterA.hasNext());
        assertEquals("3",iterA.next());

        assertTrue(iterA.hasNext());
        assertEquals("4",iterA.next());

        assertFalse(iterA.hasNext());

        assertEquals(1,iterB.next());
        assertEquals(2,iterB.next());
        assertEquals(3,iterB.next());
        assertEquals(4,iterB.next());
        assertEquals(5,iterB.next());

        assertEquals(1,iterC.next());
        assertEquals(2,iterC.next());
        assertEquals(3,iterC.next());
        assertEquals(4,iterC.next());
        assertEquals(5,iterC.next());
    }

    @org.junit.Test
    public void intersection() {

    }

    @org.junit.Test
    public void levelCount() {
        TreeB.insert(1);
        TreeB.insert(2);
        TreeB.insert(3);
        TreeB.insert(4);
        TreeB.insert(5);

        TreeC.insert(3);
        TreeC.insert(2);
        TreeC.insert(5);
        TreeC.insert(4);
        TreeC.insert(1);

        assertEquals(1,TreeB.levelCount(0));
        assertEquals(1,TreeB.levelCount(2));
        assertEquals(1,TreeB.levelCount(3));

        assertEquals(2,TreeC.levelCount(1));
        assertEquals(2,TreeC.levelCount(2));
        assertEquals(0,TreeC.levelCount(3));
    }
}
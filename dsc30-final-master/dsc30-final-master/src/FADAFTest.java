import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class FADAFTest {

    FADAF A = new FADAF(10);
    FADAF B = new FADAF(15);

    @Before
    public void setUp(){
        A.insert(4, "D", 1);
        A.insert(2, "B", 1);
        A.insert(6, "F", 1);
        A.insert(1, "A", 1);
        A.insert(3, "C", 1);
        A.insert(5, "E", 1);
        A.insert(8, "H", 1);
        A.insert(7, "G", 1);
        A.insert(9, "I", 1);
    }

    @Test
    public void size() {
        assertEquals(9, A.size());
        A.remove(2,1);
        assertEquals(8, A.size());

        A.remove(6,1);
        assertEquals(7, A.size());

        A.insert(4,10,10);
        assertEquals(17, A.size());

        A.remove(4,10);
        assertEquals(7, A.size());

        A.insert(7,10,10);
        assertEquals(17, A.size());

        A.remove(7,10);
        assertEquals(7, A.size());
    }

    @Test
    public void nUniqueKeys() {
        assertEquals(9, A.nUniqueKeys());

        A.remove(6,1);
        assertEquals(8, A.nUniqueKeys());

        A.insert(4,10,10);
        assertEquals(8, A.nUniqueKeys());

        A.remove(4,10);
        assertEquals(8, A.nUniqueKeys());
    }

    @Test
    public void insert() {
    }

    @Test
    public void lookup() {
        assertEquals(1, A.lookup(1));
        assertEquals(1, A.lookup(2));
        assertEquals(1, A.lookup(3));
        assertEquals(1, A.lookup(4));

        A.insert(4,10,10);
        assertEquals(11, A.lookup(4));

        A.remove(4, 10);
        assertEquals(1, A.lookup(4));

        A.insert(4,10,10);
        A.remove(4, 11);
        assertEquals(0, A.lookup(4));
        assertEquals(8, A.nUniqueKeys());
        assertEquals(8, A.size());
    }

    @Test
    public void remove() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);

        A.remove(4, 5);
        System.out.println(A.getAllKeys(true));
        assertEquals(9, A.nUniqueKeys());
        assertEquals(13, A.size());

        A.remove(4, 5);
        System.out.println(A.getAllKeys(true));
        assertEquals(8, A.nUniqueKeys());
        assertEquals(12, A.size());

        A.remove(9, 5);
        System.out.println(A.getAllKeys(true));
        assertEquals(7, A.nUniqueKeys());
        assertEquals(11, A.size());
    }

    @Test
    public void removeAll() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);

        A.removeAll(4);
        System.out.println(A.getAllKeys(true));
        assertEquals(8, A.nUniqueKeys());
        assertEquals(12, A.size());

        A.removeAll(9);
        System.out.println(A.getAllKeys(true));
        A.lookup(9);
        assertEquals(7, A.nUniqueKeys());
        assertEquals(11, A.size());
    }

    @Test
    public void update() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);
        A.update(4,"PP");
    }

    @Test
    public void getAllKeys() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);
        A.remove(4, 3);
        A.remove(7, 1);
        System.out.println(A.getAllKeys(true));
        System.out.println(A.getAllKeys(false));
    }

    @Test
    public void getUniqueKeysInRange() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);
        A.remove(4, 3);
        A.remove(7, 1);

        System.out.println(A.getUniqueKeysInRange(3,8));
    }

    @Test
    public void getMinKey() {
        A.insert(4,10,5);
        A.insert(1,10,2);
        A.insert(7,10,2);
        A.remove(4, 3);
        A.remove(7, 1);

        System.out.println(A.getMinKey());
    }

    @Test
    public void getMaxKey() {
        System.out.println(A.getMaxKey());
    }
}
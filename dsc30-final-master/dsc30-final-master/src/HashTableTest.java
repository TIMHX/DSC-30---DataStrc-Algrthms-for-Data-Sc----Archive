import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    HashTable tableA = new HashTable(10);
    HashTable tableB = new HashTable(20);
    @Before
    public void setup(){
       tableA.insert(1, "A");
        tableA.insert(2, "B");
        tableA.insert(3, "C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInit(){
        HashTable tableA = new HashTable(5);
    }

    @org.junit.Test
    public void insert() {
        tableB.insert(1, "A");
        tableB.insert(2, "B");
        tableB.insert(3, "C");

        assertEquals(3,tableB.size());
        assertEquals(20,tableB.capacity());

        tableA.insert(4, "D");
        tableA.insert(5, "E");
        tableA.insert(6, "F");
        tableA.insert(7, "G");
        assertEquals(7,tableA.size());
        assertEquals(10,tableA.capacity());

        tableA.insert(8, "E");
        assertEquals(8,tableA.size());
        assertEquals(20,tableA.capacity());

        assertFalse(tableA.insert(1,"B"));
        assertFalse(tableA.insert(2,"C"));
        assertEquals(8,tableA.size());

        tableA.insert("a", "a");
        tableA.insert("b", "a");
        tableA.insert("c", "a");
        tableA.insert("d", "a");
        tableA.insert("e", "a");
        tableA.insert("f", "a");
        tableA.insert("g", "a");
    }

    @org.junit.Test
    public void update() {
        assertTrue(tableA.update(1,"a"));
        assertTrue(tableA.update(2,"b"));
        assertTrue(tableA.update(3,"c"));

        tableA.insert("e", "e");
        tableA.insert("f", "f");

        assertFalse(tableA.update(4,"a"));
        assertFalse(tableA.update(4,"a"));
        assertFalse(tableA.update(4,"a"));

        assertTrue(tableA.update("e","w"));
        assertTrue(tableA.update("f","w"));
    }

    @org.junit.Test
    public void delete() {
        tableA.insert("e","e");
        tableA.insert("f","f");

        assertTrue(tableA.delete(3));
        assertEquals(4, tableA.size());

        assertTrue(tableA.delete(2));
        assertEquals(3, tableA.size());

        assertTrue(tableA.delete("e"));
        assertEquals(2, tableA.size());

        assertTrue(tableA.delete("f"));
        assertEquals(1, tableA.size());

        assertTrue(tableA.delete(1));
        assertEquals(0, tableA.size());
    }

    @org.junit.Test
    public void lookup() {
        tableA.insert("e","e");
        tableA.insert("f","f");

        assertEquals("A", tableA.lookup(1));
        assertEquals("B", tableA.lookup(2));
        assertEquals("C", tableA.lookup(3));
        assertEquals("e", tableA.lookup("e"));
        assertEquals("f", tableA.lookup("f"));
        assertEquals(null, tableA.lookup(4));

        tableA.delete(1);
        assertEquals(null, tableA.lookup(1));

    }

    @org.junit.Test
    public void size() {
        tableA.insert("a", "a");
        tableA.insert("b", "a");
        tableA.insert("c", "a");
        tableA.insert("d", "a");
        tableA.insert("e", "a");
        tableA.insert("f", "a");
        tableA.insert("g", "a");

        assertEquals(10, tableA.size());
    }

    @org.junit.Test
    public void capacity() {
    }
}
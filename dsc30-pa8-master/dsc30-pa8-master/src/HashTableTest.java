import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    HashTable A = new HashTable();
    HashTable A1 = new HashTable();
    HashTable A2 = new HashTable();

    HashTable B = new HashTable(5);
    HashTable B1 = new HashTable(30);

    HashTable C = new HashTable(25);

    @Before
    public void setUp(){
        A.insert("1");
        A.insert("2");
        A.insert("3");
        A.insert("4");
        A.insert("11");
        A.insert("12");
        A.insert("6");
        A.insert("7");
    }

    @Test
    public void insert() {
        assertFalse(A.insert("1"));
    }

    @Test
    public void delete() {
        A.insert("1");
        A.insert("2");
        A.insert("3");
        A.insert("4");

        A.delete("1");
        assertEquals(false, A.lookup("1"));
        assertEquals(7,A.size());

        A.delete("3");
        assertEquals(false, A.lookup("3"));
        assertEquals(6,A.size());

        A.delete("4");
        assertEquals(false, A.lookup("4"));
        assertEquals(5,A.size());

        B1.delete("KK");
    }

    @Test
    public void lookup() {
        A.insert("1");
        A.insert("2");
        A.insert("3");
        A.insert("4");
        assertEquals(false, A.lookup("5"));
        assertEquals(true, A.lookup("1"));
        assertEquals(true, A.lookup("2"));
        assertEquals(true, A.lookup("4"));

        B.insert("1");
        B.insert("2");
        B.insert("3");
        B.insert("4");
        assertEquals(false, A.lookup("5"));
        assertEquals(true, A.lookup("1"));
        assertEquals(true, A.lookup("2"));
        assertEquals(true, A.lookup("4"));

        B1.lookup("KK");
    }

    @Test
    public void size() {
        A.insert("1");
        assertEquals(8, A.size());
        A.insert("2");
        assertEquals(8, A.size());
        A.insert("3");
        assertEquals(8, A.size());
        A.insert("00");
        assertEquals(9, A.size());

        B.insert("1");
        assertEquals(1, B.size());
        B.delete("1");
        assertEquals(0, B.size());
    }

    @Test
    public void capacity() {
        assertEquals(15, A.capacity());
        assertEquals(5, B.capacity());
        assertEquals(25, C.capacity());

        String a = "1";
        for (int i = 0; i < 5; i++) {
            B.insert(a);
            a+=a;
        }
        assertEquals(10, B.capacity());
    }

    @Test
    public void getStatsLog() {
        System.out.println(B.getStatsLog());
        B.insert("1");
        B.insert("2");
        System.out.println(B.getStatsLog());
        B.insert("3");
        B.insert("4");
        System.out.println(B.getStatsLog());
        B.insert("5");
        B.insert("6");
        System.out.println(B.getStatsLog());
        B.insert("7");
        B.insert("8");
        System.out.println(B.getStatsLog());
        B.insert("9");
        B.insert("10");
        B.insert("A");
        B.insert("B");
        B.insert("u");
        B.insert("xs");
        B.insert("C");
        B.insert("E");
        System.out.println(B.getStatsLog());
        System.out.println(B.toString());

        System.out.println(A.getStatsLog());
    }
}
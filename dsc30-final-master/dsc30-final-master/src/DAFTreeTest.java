import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DAFTreeTest {

    DAFTree A = new DAFTree();
    DAFTree B = new DAFTree();
    DAFTree C = new DAFTree();

    @Before
    public void setUp() {
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
    public void insertDuplicate() {
        A.insertDuplicate(1,10);
        A.insertDuplicate(2,10);
        A.insertDuplicate(3,10);
        A.insertDuplicate(4, 6);

        assertEquals(45,A.size());
        assertEquals(9,A.nUniqueKeys());

        A.remove(4, 6);
        assertEquals(39,A.size());
        assertEquals(9,A.nUniqueKeys());

        A.insertDuplicate(9,10);
        assertEquals(49, A.size());
    }

    @Test
    public void lookup() {
        DAFTree.DAFNode a = A.lookup(4);
        DAFTree.DAFNode b = A.lookup(9);
        DAFTree.DAFNode c = A.lookup(5);
        DAFTree.DAFNode d = A.lookup(7);
    }

    @Test
    public void updateData() {
        A.updateData(4, "4");
        A.updateData(9, "9");
        A.updateData(8, "8");

        DAFTree.DAFNode a = A.lookup(4);
        DAFTree.DAFNode b = A.lookup(9);
        DAFTree.DAFNode c = A.lookup(8);
    }

    @Test
    public void remove() {
        A.insertDuplicate(1,10);
        A.insertDuplicate(2,10);
        A.insertDuplicate(3,10);
        A.insertDuplicate(4, 6);

        A.remove(1, 6);
        assertEquals(39, A.size());
    }

    @Test
    public void findExtreme() {
        DAFTree.DAFNode a = A.findExtreme(true);
        DAFTree.DAFNode b = A.findExtreme(false);
    }

    @Test
    public void iterator() {
        A.insert(4,10,3);
       Iterator a = A.iterator();

       System.out.println(a.hasNext());
       System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.hasNext());

        A.insert(1,"0",3);
        A.insert(2,"0",10);
        A.insert(3,"0",10);
        Iterator b = A.iteratorNoDup();
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.next());
        System.out.println(b.hasNext());
    }
}
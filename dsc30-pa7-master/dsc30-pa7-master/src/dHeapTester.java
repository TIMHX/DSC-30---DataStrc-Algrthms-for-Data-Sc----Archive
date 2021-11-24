import static org.junit.Assert.*;

public class dHeapTester {

    dHeap<Integer> MaxH;
    dHeap<Integer> MinH;

    @org.junit.Before
    public void setUp() throws Exception {
        MaxH = new dHeap<Integer>();
        MaxH.add(1);
        MaxH.add(2);
        MaxH.add(3);
        MaxH.add(4);
        MaxH.add(5);
        MaxH.add(6);


        MinH = new dHeap<Integer>(3, 6, false);
        MinH.add(6);
        MinH.add(5);
        MinH.add(4);
        MinH.add(3);
        MinH.add(2);
        MinH.add(1);
    }


    @org.junit.Test
    public void size() {
        assertEquals(6, MaxH.size());
        MaxH.add(7);
        assertEquals(7, MaxH.size());
        MaxH.add(8);
        assertEquals(8, MaxH.size());

        assertEquals(6, MinH.size());
        MinH.add(7);
        assertEquals(7, MinH.size());
        MinH.clear();
        assertEquals(0, MinH.size());

    }

    @org.junit.Test
    public void add() {
        MaxH.add(100);
        int head1 = MaxH.element();
        MaxH.add(200);
        int head2 = MaxH.element();
        MinH.add(-100);
        int head3 = MinH.element();
        MinH.add(-200);
        int head4 = MinH.element();

        assertEquals(100, head1);
        assertEquals(200, head2);
        assertEquals(-100, head3);
        assertEquals(-200, head4);

    }

    @org.junit.Test
    public void remove() {
        MaxH.remove();
        int head1 = MaxH.element();
        MaxH.remove();
        int head2 = MaxH.element();
        MinH.remove();
        int head3 = MinH.element();
        MinH.remove();
        int head4 = MinH.element();

        assertEquals(5, head1);
        assertEquals(4, head2);
        assertEquals(2, head3);
        assertEquals(3, head4);
    }

    @org.junit.Test
    public void clear() {
        MaxH.clear();
        assertEquals(0, MaxH.size());
        MaxH.add(1);
        assertEquals(1, MaxH.size());
        MaxH.clear();
        assertEquals(0, MaxH.size());

        MinH.clear();
        assertEquals(0, MinH.size());
        MinH.add(1);
        assertEquals(1, MinH.size());
        MinH.clear();
        assertEquals(0, MinH.size());
    }

    @org.junit.Test
    public void element() {
        int head1 = MaxH.element();
        MaxH.remove();
        int head2 = MaxH.element();
        MaxH.remove();
        int head3 = MaxH.element();
        assertEquals(6, head1);
        assertEquals(5, head2);
        assertEquals(4, head3);

        int head4 = MinH.element();
        MinH.remove();
        int head5 = MinH.element();
        MinH.remove();
        int head6 = MinH.element();
        assertEquals(1, head4);
        assertEquals(2, head5);
        assertEquals(3, head6);
    }

}
import java.util.*;

@SuppressWarnings("rawtypes")
public class HashTable<K, D> {

    protected class TableEntry<K, D> {
        private K key;
        private D data;

        public TableEntry(K key, D data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public boolean equals(Object obj) {
            if ((obj == null) || !(obj instanceof TableEntry))
                return false;
            return key.equals(((TableEntry<?, ?>) obj).key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    // constants
    public static final int RESIZE_FACTOR = 2; // resize factor
    public static final int MIN_CAPACITY = 10; // minimum initial capacity
    public static final double MAX_LOAD_FACTOR = (double) 2 / 3; // maximum load factor

    private LinkedList<TableEntry<K, D>>[] table;
    private int nElems;


    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < MIN_CAPACITY) throw new IllegalArgumentException();

        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<TableEntry<K, D>>();
        }
    }

    public boolean insert(K key, D data) {
        if (key == null || data == null) throw new NullPointerException();
        LinkedList<TableEntry<K, D>> whichList = table[hashValue(key)];
        TableEntry<K, D> toInsert = new TableEntry(key, data);

        // iterate
        if (!whichList.contains(toInsert)) {
            if (nElems > table.length * MAX_LOAD_FACTOR) {
                rehash();
            }
            whichList.add(toInsert);
            nElems++;
            return true;
        }
        return false;
    }

    public boolean update(K key, D newData) {
        if (key == null || newData == null) throw new NullPointerException();
        LinkedList<TableEntry<K, D>> whichList = table[hashValue(key)];
        TableEntry<K, D> toInsert = new TableEntry(key, newData);

        // iterate
        if (whichList.contains(toInsert)) {
            for (int i = 0; i < whichList.size(); i++) {
                if (whichList.get(i).equals(toInsert)) {
                    whichList.set(i, toInsert);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(K key) {
        List<TableEntry<K, D>> whichList = table[hashValue(key)];
        TableEntry<K, D> toInsert = new TableEntry(key, null);

        // iterate
        if (whichList.contains(toInsert)) {
            for (int i = 0; i < whichList.size(); i++) {
                if (whichList.get(i).equals(toInsert)) {
                    whichList.remove(i);
                }
            }
            nElems--;
            return true;
        }
        return false;
    }

    public D lookup(K key) {
        if (key == null) throw new NullPointerException();
        List<TableEntry<K, D>> whichList = table[hashValue(key)];
        TableEntry<K, D> toInsert = new TableEntry(key, null);

        // iterate
        if (whichList.contains(toInsert)) {
            for (int i = 0; i < whichList.size(); i++) {
                TableEntry<K, D> target = whichList.get(i);
                if (target.equals(toInsert)) {
                    return target.data;
                }
            }
        }
        return null;
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        return table.length;
    }

    private int hashValue(K key) {
        int hashVal = key.hashCode();
        hashVal %= table.length;
        if (hashVal < 0) {
            hashVal += table.length;
        }
        return hashVal;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<TableEntry<K, D>>[] oldLists = table;
        table = new LinkedList[RESIZE_FACTOR * table.length];
        for (int j = 0; j < table.length; j++) {
            table[j] = new LinkedList<TableEntry<K, D>>();
        }
        nElems = -1;

        // iterate
        for (List<TableEntry<K, D>> list : oldLists) {
            for (TableEntry<K, D> item : list) {
                this.insert(item.key, item.data);
            }
        }
    }

}

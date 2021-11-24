import java.util.*;

@SuppressWarnings("rawtypes")
public class FADAF<K extends Comparable<? super K>, D> {

    HashTable hash;
    DAFTree tree;

    public FADAF(int capacity) {
        if (capacity < 10) throw new IllegalArgumentException();
        hash = new HashTable(capacity);
        tree = new DAFTree();
    }

    public int size() {
        return tree.size();
    }

    public int nUniqueKeys() {
        return hash.size();
        //return tree.nUniqueKeys();
    }

    public boolean insert(K key, D data, int nCopy) {
        if (key == null || data == null) throw new NullPointerException();
        if (nCopy < 1) throw new IllegalArgumentException();

        DAFTree.DAFNode node = tree.insert(key, data, nCopy);

        boolean out = hash.insert(key,node);
        return out;
    }

    public int lookup(K key) {
        DAFTree.DAFNode node = (DAFTree.DAFNode) hash.lookup(key);
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    public boolean remove(K key, int nCopy) {
        if (key == null) throw new NullPointerException();
        if (nCopy < 1) throw new IllegalArgumentException();

        DAFTree.DAFNode node = (DAFTree.DAFNode) hash.lookup(key);
        if (node == null){
            return false;
        }
        else {
            if (node.count - nCopy > 0){
                node.count -= nCopy;
                tree.setNelems(-nCopy);
            }
            else {
                hash.delete(key);
                tree.setNelems(-node.count);
                tree.setnKeys(-1);
                node.count = 0;
            }
        }
        return false;
    }

    public boolean removeAll(K key) {
        if (key == null) throw new NullPointerException();
        DAFTree.DAFNode node = (DAFTree.DAFNode) hash.lookup(key);
        if (node == null){
            return false;
        }
        else{
            hash.delete(key);
            tree.setNelems(-node.count);
            tree.setnKeys(-1);
            node.count = 0;
            return true;
        }
    }

    public boolean update(K key, D newData) {
        if (key == null || newData == null) throw new NullPointerException();
        DAFTree.DAFNode node = (DAFTree.DAFNode) hash.lookup(key);
        if (node == null){
            return false;
        }
        else{
            node.data = newData;
            return true;
        }
    }

    public List<K> getAllKeys(boolean allowDuplicate) {
        Iterator a;
        LinkedList list = new LinkedList();
        if (!allowDuplicate){
            a = tree.iteratorNoDup();
        }
        else{
            a = tree.iterator();
        }

        while(a.hasNext()) {
            list.add(a.next());
        }
        return list;
    }

    public List<K> getUniqueKeysInRange(K lower, K upper) {
        Iterator a = tree.iteratorNoDup();
        LinkedList list = new LinkedList();
        while(a.hasNext()) {
            K key = (K) a.next();
            if(key.compareTo(lower) > 0 && key.compareTo(upper) < 0){
                list.add(key);
            }
        }
        return list;
    }

    public K getMinKey() {
        return (K) tree.findExtreme(false).key;
    }

    public K getMaxKey() {
        return (K) tree.findExtreme(true).key;
    }

}

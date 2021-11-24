import java.util.*;

@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    private DAFNode<K, D> root;
    private int nelems; // number of elements stored
    private int nKeys; // number of unique keys stored

    protected class DAFNode<K extends Comparable<? super K>, D> {
        K key;
        D data;
        int count; // duplicate counter
        DAFNode<K, D> left, right;

        public DAFNode(K key, D data) {
            if (key == null || data == null) throw new NullPointerException();
            this.data = data;
            this.left = null;
            this.right = null;
            this.count = 1;
            this.key = key;
        }

        public DAFNode(K key, D data, int nCopy) {
            if (key == null || data == null) throw new NullPointerException();
            if (nCopy < 1) throw new IllegalArgumentException();
            this.data = data;
            this.left = null;
            this.right = null;
            this.count = nCopy;
            this.key = key;
        }
    }

    public DAFTree() {
        nelems = 0;
        nKeys = 0;
        root = null;
    }

    public int size() {
        return nelems;
    }

    public int nUniqueKeys() {
        return nKeys;
    }

    public void setNelems(int k){
        this.nelems += k;
    }

    public void setnKeys(int k){
        this.nKeys += k;
    }

    public DAFNode<K, D> insert(K key, D data, int nCopy) {
        if (key == null || data == null) throw new NullPointerException();
        if (nCopy < 1) throw new IllegalArgumentException();

        root = insertHelper(root, key, data, nCopy);     // run insertion
        return lookup(key);
    }

    private DAFNode insertHelper(DAFNode n, K key, D data, int nCopy) {
        if (n == null) {                                // add root
            n = new DAFNode(key, data, nCopy);
            nelems+=nCopy;
            nKeys++;
        } else {
            int diff = key.compareTo((K) n.key);
            if (diff < 0) {   // search left is smaller
                n.left = insertHelper(n.left, key, data, nCopy);
            } else if (diff > 0) { // search right if bigger
                n.right = insertHelper(n.right, key, data, nCopy);
            } else {
                n.count+=nCopy;
                nelems+=nCopy;
            }
        }
        return n;
    }

    public DAFNode<K, D> insertDuplicate(K key, int nCopy) {
        if (key == null) throw new NullPointerException();
        if (nCopy < 1) throw new IllegalArgumentException();

        DAFNode node = lookup(key);
        if (node == null) {
            return null;
        } else {
            node.count += nCopy;
            nelems += nCopy;
        }
        return node;
    }

    public DAFNode<K, D> lookup(K key) {
        if (key == null) throw new NullPointerException();
        return lookUpHelper(root, key);
    }

    private DAFNode lookUpHelper(DAFNode n, K key) {
        DAFNode result = null;
        if (n == null) {
            return result;
        } else {
            int diff = key.compareTo((K) n.key);
            if (diff < 0) {
                result = lookUpHelper(n.left, key);
            } else if (diff > 0) {
                result = lookUpHelper(n.right, key);
            } else if (diff == 0) {
                result = n;
            }
        }
        return result;
    }

    public DAFNode<K, D> updateData(K key, D newData) {
        if (key == null || newData == null) throw new NullPointerException();
        DAFNode node = lookup(key);
        if (node == null) {
            return null;
        } else {
            node.data = newData;
            return node;
        }
    }

    public DAFNode<K, D> remove(K key, int nCopy) {
        if (key == null) throw new NullPointerException();
        if (nCopy < 1) throw new IllegalArgumentException();

        DAFNode parent = null;
        DAFNode curr = root;

        // finding the node and
        while (curr != null && curr.key != key) {
            parent = curr;

            if (key.compareTo((K) curr.key) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // if not found
        if (curr == null) {
            return null;
        }

        if (curr.count - nCopy > 0) {
            curr.count -= nCopy;
            nelems -= nCopy;
        } else {
            removeHelper(curr, parent);
            return curr;
        }
        return curr;
    }

    private void removeHelper(DAFNode curr, DAFNode parent) {

        // Case 1: Leaf node
        if (curr.left == null && curr.right == null) {
            // not the root, remove itself
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // if the tree has only a root node, set it to null
            else {
                root = null;
            }
        }

        // Case 2: two children
        else if (curr.left != null && curr.right != null) {
            // find its inorder successor node
            DAFNode successor = findMin(curr.right);

            // remove successor as a leaf
            remove((K) successor.key, successor.count);
            nelems += successor.count;;
            nKeys ++;

            // replace curr as successor
            successor.left = curr.left;
            successor.right = curr.right;

            if (parent != null) {
                if (parent.left == curr) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
            // if curr is root, set root to successor
            else {
                root = successor;
            }
        }

        // Case 3: one child
        else {
            // choose a child node
            DAFNode child = (curr.left != null)? curr.left: curr.right;

            // if the node to be deleted is not a root node, set its parent
            // to its child
            if (curr != root)
            {
                if (curr == parent.left) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }

            // if the node to be deleted is a root node, then set the root to the child
            else {
                root = child;
            }
        }

        nelems -= curr.count;
        nKeys--;
    }

    private DAFNode<K, D> findMin(DAFNode curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private DAFNode<K, D> findMax(DAFNode curr) {
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public DAFNode<K, D> findExtreme(boolean isMax) {
        if (root == null) {
            return null;
        }

        if (isMax) {
            return findMax(root);
        } else {
            return findMin(root);
        }
    }

    public class DAFTreeIterator implements Iterator<K> {

        Stack<DAFNode<K, D>> stack = new Stack<DAFNode<K, D>>();


        public DAFTreeIterator() {
            inorder(root);
        }

        public DAFTreeIterator(boolean a) {
            inorderNoDup(root);
        }

        private void inorderNoDup(DAFNode root)
        {
            if (root == null) {
                return;
            }
            inorderNoDup(root.right);
            if (root.count != 0){
                stack.push(root);
            }
            inorderNoDup(root.left);
            return;
        }

        private void inorder(DAFNode root)
        {
            if (root == null) {
                return;
            }
            inorder(root.right);
            if(root.count!=1){
                for (int i = 0; i < root.count-1; i++) {
                    stack.push(root);
                }
            }
            if (root.count != 0){
                stack.push(root);
            }
            inorder(root.left);
            return;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public K next() {
            DAFNode node;
            try {
                node = stack.pop();
            } catch (Exception e) {
                throw new NoSuchElementException();
            }

            return (K) node.key;
        }
    }

    public Iterator<K> iterator() {
        return new DAFTreeIterator();
    }
    public Iterator<K> iteratorNoDup() {
        return new DAFTreeIterator(true);
    }

}

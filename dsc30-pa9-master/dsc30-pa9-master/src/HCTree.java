/*
 * Name: XING HONG
 * PID: A15867895
 */

import java.io.*;
import java.util.Stack;
import java.util.PriorityQueue;

/**
 * The Huffman Coding Tree
 */
public class HCTree {
    // alphabet size of extended ASCII
    private static final int NUM_CHARS = 256;
    // number of bits in a bytef
    private static final int BYTE_BITS = 8;

    // the root of HCTree
    private HCNode root;
    // the leaves of HCTree that contain all the symbols
    private HCNode[] leaves = new HCNode[NUM_CHARS];

    /**
     * The Huffman Coding Node
     */
    protected class HCNode implements Comparable<HCNode> {

        byte symbol; // the symbol contained in this HCNode
        int freq; // the frequency of this symbol
        HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child

        /**
         * Initialize a HCNode with given parameters
         *
         * @param symbol the symbol contained in this HCNode
         * @param freq   the frequency of this symbol
         */
        HCNode(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
        }

        /**
         * Getter for symbol
         *
         * @return the symbol contained in this HCNode
         */
        byte getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         *
         * @param symbol the given symbol
         */
        void setSymbol(byte symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         *
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         *
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         *
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         *
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         *
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         *
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         *
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         *
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf (has no children)
         *
         * @return if it's leaf, return true. Otherwise, return false.
         */
        boolean isLeaf() {
            if (this.c0 == null && this.c1 == null) {
                return true;
            }
            return false;
        }

        /**
         * String representation
         *
         * @return string representation
         */
        public String toString() {
            return "Symbol: " + this.symbol + "; Freq: " + this.freq;
        }

        /**
         * Compare two nodes
         *
         * @param o node to compare
         * @return int positive if this node is greater
         */
        public int compareTo(HCNode o) {
            return this.freq - o.freq;
        }
    }

    /**
     * Returns the root node
     *
     * @return root node
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Sets the root node
     *
     * @param root node to set
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * Initialize a Huffmancode Tree from given character frequencies
     *
     * @param freq
     */
    public void buildTree(int[] freq) {
        PriorityQueue<HCNode> queueTree = new PriorityQueue<HCNode>();

        // Iterate through freq[] and create the nodes then add into queue
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                HCNode currNode = new HCNode((byte) i, freq[i]);
                queueTree.add(currNode);
            }
        }

        // build the tree by pairing
        while (queueTree.size() > 1) {
            // take the smallest two nodes
            HCNode left = queueTree.poll();
            HCNode right = queueTree.poll();

            // creat the parent and link it to its children
            HCNode parent = new HCNode(left.getSymbol(), left.getFreq() + right.getFreq());
            parent.setC0(left);
            parent.setC1(right);
            left.setParent(parent);
            right.setParent(parent);

            // add the parent to queue
            queueTree.offer(parent);
        }

        //save the root
        this.root = queueTree.peek();
        buildLeaf(root);
    }

    /**
     * Helper function for saving the leaves
     * */
    private void buildLeaf(HCNode x) {
        //recursive search
        if (!x.isLeaf()) {
            buildLeaf(x.getC0());
            buildLeaf(x.getC1());
        } else {
            int ascii = x.getSymbol() & 0xff;
            leaves[ascii] = x;
        }
    }

    /**
     * write the given symbol to the given output in bits
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException {
        int ascii = symbol & 0xff;
        Stack output = new Stack();
        HCNode leaf = leaves[ascii];

        // if the leaf is not root, keep going up
        while (leaf != getRoot()) {
            // if leaf is left child
            if (leaf.parent.getC0() == leaf) {
                output.push(0);
            } else { // if leaf is right child
                output.push(1);
            }
            leaf = leaf.parent;
        }

        //System.out.println(output.toString());
        int size = output.size();
        for (int i = 0; i < size; i++) {
            int a = (int) output.pop();
            //System.out.println(a);
            out.writeBit(a);
        }

    }

    /**
     * Read individual bits from the input and return the corresponding character
     *
     * @param in
     * @return The decoded char
     * @throws IOException
     */
    public byte decode(BitInputStream in) throws IOException {
        byte value;
        value = readIndBits(in);
        return value;
    }

    /**
     * Helper function to do the search for leaf
     * */
    private byte readIndBits(BitInputStream input) throws IOException {
        int bit;
        HCNode tempNode = root;

        //keep searching down until is leaf
        while (!tempNode.isLeaf()) {
            bit = input.readBit();
            if (bit == 0) { // left child
                tempNode = tempNode.getC0();
            } else { // right child
                tempNode = tempNode.getC1();
            }
        }
        return tempNode.getSymbol();
    }

    /**
     * recursive pre-order traversing to “print out” the structure of the HCTree in bits.
     *
     * @param node
     * @param out
     * @throws IOException
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException {
        if (node == null) {
            return;
        }
        if (!node.isLeaf()) {
            out.writeBit(0);
        } else {
            out.writeBit(1);
            out.writeByte(node.getSymbol());
        }
        encodeHCTree(node.c0, out);
        encodeHCTree(node.c1, out);
    }

    /**
     * building the original HCTree from the header that we “printed” in bits
     * when encoding the HCTree.
     *
     * @param in
     * @return
     * @throws IOException
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {
        decodeHCTreeHelper(root, in);
        return root;
    }

    private void decodeHCTreeHelper(HCNode node, BitInputStream in) throws IOException {
//        HCNode left = new HCNode((byte) 0, 1);
//        HCNode right = new HCNode((byte) 0, 1);
//        node.setC0(left);
//        left.setParent(node);
//        node.setC1(right);
//        right.setParent(node);
//
//        if(in.readBit()!=0){
//            int symbol = in.readByte();
//            left.setSymbol((byte) symbol);
//            int ascii = symbol & 0xff;
//            leaves[ascii] = left;
//            decodeHCTreeHelper(node.getC1(), in);
//        }
//        decodeHCTreeHelper(node.getC0(), in);
        return;
    }
}
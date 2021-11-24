import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;

public class HCTreeTester {


    HCTree A = new HCTree();
    HCTree B = new HCTree();
    HCTree C = new HCTree();
    int[] freqArray = new int[256];
    int[] freqArray2 = new int[256];
    int[] freqArray3 = new int[256];
    @Before
    public void setUp() {
        // aaaaaaaaaaaaaaaaabbbbbbbbcccccccddddddddddddddeeeeeeeeef\n
        freqArray[10] = 1;
        freqArray[97] = 17;
        freqArray[98] = 8;
        freqArray[99] = 7;
        freqArray[100] = 14;
        freqArray[101] = 9;
        freqArray[102] = 1;

        // dsc10dsc20dsc30dsc40adsc40bdsc80\n
        freqArray2[10] = 1;
        freqArray2[99] = 6;
        freqArray2[115] = 6;
        freqArray2[50] = 1;
        freqArray2[52] = 2;
        freqArray2[49] = 1;
        freqArray2[56] = 1;
        freqArray2[98] = 1;
        freqArray2[51] = 1;
        freqArray2[97] = 1;
        freqArray2[48] = 6;
        freqArray2[100] = 6;

        freqArray3[10] = 1;
        freqArray3[11] = 6;
        freqArray3[12] = 6;
        freqArray3[13] = 1;
        freqArray3[14] = 2;
        freqArray3[15] = 1;
        freqArray3[16] = 1;


    }

    @Test
    public void testBuild() throws IOException{
        A.buildTree(freqArray);
        assertTrue(testByte(A, (byte)10));
        assertTrue(testByte(A, (byte)97));
        assertTrue(testByte(A, (byte)98));
        assertTrue(testByte(A, (byte)99));
        assertTrue(testByte(A, (byte)100));
        assertTrue(testByte(A, (byte)101));
        assertTrue(testByte(A, (byte)102));

        B.buildTree(freqArray2);
        assertTrue(testByte(B, (byte)10));
        assertTrue(testByte(B, (byte)99));
        assertTrue(testByte(B, (byte)115));
        assertTrue(testByte(B, (byte)50));
        assertTrue(testByte(B, (byte)52));
        assertTrue(testByte(B, (byte)49));
        assertTrue(testByte(B, (byte)56));
        assertTrue(testByte(B, (byte)98));
        assertTrue(testByte(B, (byte)51));
        assertTrue(testByte(B, (byte)97));
        assertTrue(testByte(B, (byte)48));
        assertTrue(testByte(B, (byte)100));

        C.buildTree(freqArray3);
        assertTrue(testByte(C, (byte)10));
        assertTrue(testByte(C, (byte)11));
        assertTrue(testByte(C, (byte)12));
        assertTrue(testByte(C, (byte)13));
        assertTrue(testByte(C, (byte)14));
        assertTrue(testByte(C, (byte)15));
        assertTrue(testByte(C, (byte)16));
    }

    @Test
    public void testTrees() throws IOException {
        A.buildTree(freqArray);
        testTree(A);
        B.buildTree(freqArray2);
        testTree(B);
        C.buildTree(freqArray3);
        testTree(C);
    }


    /**
     * Tests encode() and decode().
     *
     * @param tree  HCTree to test
     * @param input the byte to reconstruct
     * @return whether the encode-decode can reconstruct the input byte
     * @throws IOException from stream
     */
    private static boolean testByte(HCTree tree, byte input) throws IOException {

        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode byte
        tree.encode(input, bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode byte and compare with input
        boolean result = (input == tree.decode(bitIn));

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

    /**
     * Checks if `expected` and `actual` have the same structure,
     * regardless of the instance variables on the nodes.
     *
     * @param expected the root of the expected tree
     * @param actual   the root of the actual tree
     * @return whether they share the same structure
     */
    private static boolean sameTreeStructure(HCTree.HCNode expected, HCTree.HCNode actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        return sameTreeStructure(expected.c0, actual.c0)
                && sameTreeStructure(expected.c1, actual.c1);
    }

    /**
     * Tests encodeHCTree() and decodeHCTree().
     *
     * @param tree HCTree to test
     * @return whether the encode-decode can reconstruct the tree
     * @throws IOException from stream
     */
    private static boolean testTree(HCTree tree) throws IOException {
        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode tree
        tree.encodeHCTree(tree.getRoot(), bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode tree and compare with input
        HCTree treeOut = new HCTree();
        treeOut.setRoot(treeOut.decodeHCTree(bitIn));
        boolean result = sameTreeStructure(tree.getRoot(), treeOut.getRoot());

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

}


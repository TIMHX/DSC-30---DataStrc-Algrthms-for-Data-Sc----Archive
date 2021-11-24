/*
 * Name: Xing Hong
 * PID:  A15867895
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 *
 * @author Xing Hong
 * @since 2/15/2021
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     *
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim().toLowerCase();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees
                for (String i : cast) {     //building movie & rating tree
                    insertionHelper(movieTree, i.toLowerCase(), movie);
                    insertionHelper(ratingTree, i.toLowerCase(), rating);
                }
                for (String i : studios) {  //building studios tree
                    insertionHelper(studioTree, i.toLowerCase(), movie);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper function for building trees
     *
     * @param A    - the tree to insert
     * @param key  - the given key to store data
     * @param data - the data to store in the given key
     */
    private static void insertionHelper(BSTree A, String key, String data) {
        if (A.findKey(key) == false) {
            A.insert(key);
            A.insertData(key, data);
        } else {
            LinkedList<String> B = A.findDataList(key);
            if (B.contains(data)) {
                return;
            }
            A.insertData(key, data);
        }
    }

    /**
     * Search a query in a BST
     *
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");

        // to store the intersect data of given keys
        LinkedList<String> intersection = new LinkedList();

        // for iteration
        LinkedList<String> curr = new LinkedList();

        // to store the data linkedlist of each keys
        LinkedList<LinkedList> all = new LinkedList();

        // to test whether the given key cann be found
        Boolean exist = true;


        // search and output intersection results for single query
        if (keys.length == 1) {
            try {
                print(query.toLowerCase(), searchTree.findDataList(query.toLowerCase()));
                return;
            } catch (Exception A) {     // print the not found message
                print(query.toLowerCase(), null);
            }
        }

        // To test whether the key can be found and save the data linkedlist for each key
        for (String name : keys) {
            // if key is not found
            if (searchTree.findKey(name) == false) {
                all.add(null);
                exist = false;
            } else { // if found, save data
                all.add(searchTree.findDataList(name));
            }
        }

        if (exist == true) {  // if all keys found, find their intersection
            // initial with the first key data
            intersection.addAll(searchTree.findDataList(keys[0]));

            // iterate and find intersect
            for (int i = 1; i < keys.length; i++) {
                curr = searchTree.findDataList(keys[i]);
                intersection.retainAll(curr); // filter data in each iteration
            }
        }

        print(query, intersection); // print intersect message
        queryIndividualHelper(keys, all, intersection);  // print individual messages
    }

    /**
     * Helper function for query of returning individual messages
     *
     * @param keys         - the key to search
     * @param all          - the linkedlist of lists that saved all data of each keys
     * @param intersection - the intersections
     */
    private static void queryIndividualHelper(String[] keys,
                                              LinkedList<LinkedList> all,
                                              LinkedList intersection) {
        //save repeated data in messages that printed before
        LinkedList save = new LinkedList();

        // iterate through keys and print out messages
        for (int i = 0; i < keys.length; i++) {
            LinkedList curr = all.get(i);

            // remove the common intersection of all keys
            for (int j = 0; j < intersection.size(); j++) {
                curr.remove(intersection.get(j));
            }

            if (curr == null) {   // if curr is null (the given key not found)
                print(keys[i], curr);
                continue;
            } else {             // if the key is found
                curr.removeAll(save); // remove the sent results of messages before
                save.addAll(curr);    // save the current data into save

                // if all data has been sent before, no repeated print for curr needed
                if (curr.size() == 0) {
                    continue;
                }
            }

            print(keys[i], curr);   // print the current message
        }
    }

    /**
     * Print output of query
     *
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree movieTree = new BSTree();
        BSTree studioTree = new BSTree();
        BSTree ratingTree = new BSTree();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += args[i];
            query += " ";
        }
        query = query.trim();

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);

        // choose the right tree to query
        switch (searchKind) {
            case 0:
                searchMyQuery(movieTree, query);
                break;
            case 1:
                searchMyQuery(studioTree, query);
                break;
            case 2:
                searchMyQuery(ratingTree, query);
                break;
        }
    }
}

/*
 * Name: Xing Hong
 * PID: A15867895
 */

import java.util.Arrays;

/**
 * Hashtable and its methods with Linear Probing Hashing
 *
 * @author Xing Hong
 * @since 2021/3/1
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String bridge = new String("[BRIDGE]".toCharArray());

    /* instance variables */
    private int size; // number of elements stored
    private int maxSize;
    private int numCollisions;
    private int numRehash;
    private String[] table; // data table
    private int DOUBLE = 2;
    private int LEFT = 5;
    private int RIGHT = 27;
    private static final int DEFAULT_CAPACITY = 15;
    private static final int MIN_CAPACITY = 5;
    private static final double MAX_LOAD_FACTOR = 0.55;
    private String log = "";


    /**
     * Initialize the hashtable with default capacity of 15
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the hashtable with the given capacity
     *
     * @param capacity is the given capacity to initialize
     */
    public HashTable(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException();
        }
        this.size = 0;
        this.maxSize = capacity;
        table = new String[capacity];
    }


    /*private int hashing(String value) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = (hash << 5) - hash + value.charAt(i);
        }
        return hash % this.maxSize;
    }*/

    /**
     * Insert the string value into the hash table.
     *
     * @param value value to insert
     * @return true if the value was inserted, false if the value was already
     * present
     * @throws NullPointerException if value is null
     */
    @Override
    public boolean insert(String value) {
        if (value == null || value.length() < 1) {
            throw new NullPointerException();
        }

        int index = 0;
        boolean found = lookup(value);

        // if string does not already exist
        if (found == false) {
            // Rehash if load factor exceeded
            if (((double)size /(double) this.maxSize) > MAX_LOAD_FACTOR){
                rehash();
            }

            index = hashString(value);
            // Loop until empty or bridge location is found
            while (this.table[index] != null && !this.table[index].equals(bridge)) {
                index++;
                index = index % this.maxSize;
            }
            int newCollisions = countCollisions(value);
            table[index] = value;
            numCollisions += newCollisions;
            size++;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Counts number of collisions specified string
	 *
     * @param value 	string for which to count collisions in hash table
	 * @return 			number of collisions in hash table with specified string
	 */
    private int countCollisions(String value) {

        // Check for illegal arguments
        if (value == null || value.length() < 1) return 0;

        int collCount = 0;
        int origHash = hashString(value);
        int index = origHash;

        // Loop counting collisions with existing but different keys
        while (table[index] != null) {
            if (hashString(table[index]) == origHash
                    && !value.equals(table[index])) collCount++;
            index++;
            index = index % maxSize;
        }

        return collCount;
    }

    /**
     * Delete the given value from the hash table.
     *
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     * @throws NullPointerException if value is null
     */
    @Override
    public boolean delete(String value) {
        if (value == null || value.length() < 1) {
            throw new NullPointerException();
        }

        int index = find(value); // the index of the value
        if (index == -1) {        // if the value is not found
            return false;
        }
        table[index] = bridge;
        size--;
        return true;
    }

    /**
     * The helper function to return the index of the given value
     *
     * @param value value to look up
     * @return the index of the value, -1 if the value was not found
     */
    private int find(String value) {
        int index = hashString(value);

        while (table[index] != null) {
            if (table[index].equals(value)) return index; //Key exists
            index++;
            index = index % this.maxSize;
        }
        return -1;
    }

    /**
     * Check if the given value is present in the hash table.
     *
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     * @throws NullPointerException if value is null
     */
    @Override
    public boolean lookup(String value) {
        if (value == null || value.length() < 1) {
            throw new NullPointerException();
        }

        int found = find(value);
        if (found != -1) {
            return true;
        }
        return false; // Key does not exist
    }

    /**
     * Return the number of elements currently stored in the hashtable.
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Get the total capacity of the hash table.
     *
     * @return total capacity
     */
    @Override
    public int capacity() {
        return this.maxSize;
    }

    /**
    * Returns the statistics log of the HashTable.
     * @return the log in seperated lines
    * */
    public String getStatsLog() {
        double load = (double)this.size / (double)this.maxSize;
        if(this.numRehash == 0){
            return "Before rehash # 1: load factor " + String.format("%.2f", load)
                    + ", " + numCollisions + " collision(s).\n";
        }
        return this.log;
    }

    /**
     * The function to rehash the whole table
     * */
    private void rehash() {
        String[] OldTable = this.table;

        // the current load factor
        double load = (double)this.size / (double)this.maxSize;

        // Record the log
        this.numRehash += 1;
        String log = "Before rehash # " + numRehash + ": load factor " +
                String.format("%.2f", load) + ", " + numCollisions
                + " collision(s).\n";
        this.log += log;

        // Create a new table
        this.numCollisions = 0;
        this.size = 0;
        this.maxSize *= DOUBLE;
        this.table = new String[maxSize];

        // loop through old table and insert into the new one
        for (int i = 0; i < OldTable.length; i++) {
            if (OldTable[i] != null && !OldTable[i].equals(bridge)) {
                String oldKey = OldTable[i];
                insert(oldKey);
            }
        }
    }

    /**
     * Return the hash value of th given string
     * @return the hash value
     * */
    private int hashString(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int left = hashValue << LEFT;
            int right = hashValue >>> RIGHT;
            hashValue = (left | right) ^ value.charAt(i);
        }
        return hashValue % this.maxSize;
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}

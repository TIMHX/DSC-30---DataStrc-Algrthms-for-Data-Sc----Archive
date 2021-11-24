/*
 * NAME: XING HONG
 * PID: A15867895
 */

//import java.util.Arrays;

/**
 * Description of Startup
 * Starter of winter 2021 dsc30 PA 1
 *
 * @author XING HONG
 * @since ${2021.1.7}
 */
public class Startup {

    /* Declare constants and magic numbers */
    private static final int detector = 2;
    private static final int step = 2;
    private static final String vowel = "[AEIOUaeiou]";
    private static final int lowerBound1 = 97;
    private static final int lowerBound2 = 122;
    private static final int upperBound1 = 65;
    private static final int upperBound2 = 90;
    private static final int box = 3;

    /**
     * takes an array arr and checks whether any odd position
     * contains an odd element and any even position contains an even element.
     *
     * @param arr is an int array
     * @return return an boolean array
     * representing odd and even
     */
    public static boolean[] arrEvenOdd(int[] arr) {
        int parity_1, parity_2;
        /* Declare demanding char array */
        boolean[] out = new boolean[arr.length];

        //
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] *= -1; //Turn negative num into positive
            }

            /* Calculate parity for both */
            parity_1 = arr[i] % detector;
            parity_2 = i % detector;

            /* Compare parity */
            if (parity_1 == parity_2) {
                out[i] = true;
            } else {
                out[i] = false;
            }
        }
        return out;
    }

    /**
     * Takes a string input and returns this string with all vowels (AEIOU)
     * removed, regardless of the case.
     *
     * @param input
     * @return return a string without the vowels
     */
    public static String removeVowels(String input) {
        String result = input.replaceAll(vowel, "");
        return result;
    }

    /**
     * Takes a string compound and calculates its mass.
     *
     * @param compound
     * @return return the mass of compound
     */
    public static int compoundMass(String compound) {
        int mass = 0;
        for (int i = 0; i < compound.length(); i += step)
            mass += (compound.charAt(i) - 'A' + 1) * (compound.charAt(i + 1) - '0');
        return mass;
    }

    /**
     * Takes a 2-dimensional matrix and a threshold and returns the binarized matrix.
     * To binarize a matrix, each element smaller than the threshold is changed to 0,
     * and other elements are changed to 1.
     *
     * @param matrix
     * @param threshold
     * @return binarized matrix
     */
    public static int[][] binarizeMatrix(double[][] matrix, double threshold) {
        int[][] out = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < threshold) {
                    out[i][j] = 0;
                } else {
                    out[i][j] = 1;
                }
            }
        }
        return out;
    }

    /**
     * This method encrypts the input string by applying the Atbash cipher on
     * letters (both uppercase and lowercase) and reversing the encrypted string,
     * and then returns the reversed encrypted string.
     *
     * @param input
     * @return a string of encrypt massage
     */
    public static String encryptString(String input) {
        String newString = "";
        String out = "";

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= lowerBound1 && input.charAt(i) <= lowerBound2) { // Lowercase
                newString = newString + (char) (lowerBound2 - input.charAt(i) + lowerBound1);
            } else if (input.charAt(i) >= upperBound1 && input.charAt(i) <= upperBound2) { // Uppercase
                newString = newString + (char) (upperBound2 - input.charAt(i) + upperBound1);
            } else {
                newString = newString + input.charAt(i); // Other symbols
            }
        }

        for (int i = newString.length() - 1; i >= 0; i--) { // Reverse
            out = out + newString.charAt(i);
        }
        return out;
    }

    /**
     * This method takes an array arr and a direction, and rotates the array to the specified
     * direction for 1 position (index). When direction is true,
     * rotate to the left; otherwise to the right.
     *
     * @param arr
     * @param direction
     * @return a rotated array
     */
    public static double[] rotateArray(double[] arr, boolean direction) {
        int i;
        double temp;
        double[] out = arr.clone();

        if (arr.length == 0) { // Return the empty array
            return arr;
        }

        if (direction == true) { // Rotate left
            temp = arr[0];      // save the head
//            Shift the elements in arr to out on by one
            for (i = 0; i < arr.length - 1; i++) {
                out[i] = arr[i + 1];
            }
            out[i] = temp;      // put the head to the end
        } else {                // Rotate right
            temp = arr[arr.length - 1];     //save the end
            for (int j = arr.length - 1; j > 0; j--) {
                out[j] = arr[j - 1];
            }
            out[0] = temp;      // put end to the head
        }
        return out;
    }

    /**
     * This method takes an array arr, rotates every three consecutive numbers,
     * and returns the result. The method will start by rotating the first three
     * numbers left, then rotate the next three numbers right, then rotate the next
     * three numbers left, so on and so forth. If the last group only has 1 number,
     * keep it unchanged; if the last group only has 2 numbers, swap them.
     *
     * @param arr
     * @return a rotated array
     */
    public static double[] rotateTriplets(double[] arr) {
        double temp;    //temporary save the head of rotation
        int i = 2;      //the index of rotation
        int count = 0;  //the indicator of rotation number
        if (arr.length == 0 || arr.length == 1) {   //the case of length 0 & 1
            return arr;
        }
        /*swap the last 2 elements if they are left over*/
        if (arr.length % box == detector) {
            temp = arr[arr.length - step];
            arr[arr.length - step] = arr[arr.length - 1];
            arr[arr.length - 1] = temp;
        }
        /*Main rotation function, change direction every rotation, move forward
        * and rotate in a box of 3*/
        while (i <= arr.length - arr.length % box - 1) {
            if (count % detector == 0) {    //rotate left
                temp = arr[i - step];
                for (int j = i - step; j < i; j++) {    //rotate in the box
                    arr[j] = arr[j + 1];
                }
                arr[i] = temp;
            } else {    //rotate right
                temp = arr[i];
                for (int j = i; j > i - step; j--) {    //rotate in the box
                    arr[j] = arr[j - 1];
                }
                arr[i - step] = temp;
            }
            count++;    //for the purpose of changing direction
            i += box;     //move forward in a step of 3

        }
        return arr;
    }

    /**
     * Determine whether set1 is a subset of set2 or not
     *
     * @param set1 is an int arr
     * @param set2 is an int arr
     * @return return a boolean to specify the result
     */
    public static boolean subsetChecker(int[] set1, int[] set2) {
        outer:
        for (int i = 0; i < set1.length; i++) {
            for (int j = 0; j < set2.length; j++) {
                if (set1[i] == set2[j]) {
                    continue outer; // Check whether each element in set1 is in set2, if it is, continue
                }
            }
            return false; // If the element in set1 cannot be found in set2, return false
        }
        return true;
    }


    /**
     * Checks if all digits in num are in the same row or column of the numpad.
     *
     * @param num is an int
     * @return return a boolean specify the result
     */
    public static boolean numpadSRC(int num) {
        String numString = Integer.toString(num);
        int rowCount = 0, colCount = 0;

        //To count the existence of numbers in rows
        if (numString.contains("0")) {
            rowCount += 1;
        }
        if (numString.contains("1") || numString.contains("2") || numString.contains("3")) { //First row
            rowCount += 1;
        }
        if (numString.contains("4") || numString.contains("5") || numString.contains("6")) { //Second row
            rowCount += 1;
        }
        if (numString.contains("7") || numString.contains("8") || numString.contains("9")) { //Third row
            rowCount += 1;
        }

        //To count the existence of numbers in columns, no need to check for 0
        if (numString.contains("1") || numString.contains("7") || numString.contains("4")) { //1st col
            colCount += 1;
        }
        if (numString.contains("2") || numString.contains("5") || numString.contains("8")) { //2nd col
            colCount += 1;
        }
        if (numString.contains("3") || numString.contains("6") || numString.contains("9")) { //3rd col
            colCount += 1;
        }
        if (colCount == 1 || rowCount == 1) { // If the numbers only occur in single col or row
            return true;
        } else {
            return false;
        }
    }
}

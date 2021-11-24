/*
 * NAME: Xing Hong
 * PID:  A15867895
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Xing Hong
 * @since  2/9/2021
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int j = i;
            while (j > start && list.get(j).compareTo(list.get(j - 1)) < 0) {
                T t = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1, t);
                --j;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        int j = 0;

        if (start >= end) {
            return;
        }

        j = partition(list, start, end);
        QuickSort(list, start, j);
        QuickSort(list, j + 1, end);
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        T temp;
        boolean done = false;

        int midpoint = l + (h - l) / MIDDLE_IDX;
        T pivot = arr.get(midpoint);

        while (!done) {
            while (arr.get(l).compareTo(pivot) < 0) {
                ++l;
            }
            while (arr.get(h).compareTo(pivot) > 0) {
                --h;
            }

            if (l >= h) {
                done = true;
            } else {
                temp = arr.get(l);
                arr.set(l, arr.get(h));
                arr.set(h, temp);

                ++l;
                --h;
            }
        }
        return h;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if (start >= end) {
            return;
        }
        int part = this.partition(list, start, end);
        if ((part + 1 - start) <= cutoff) {
            this.InsertionSort(list, start, end);
        } else {
            this.Modified_QuickSort(list, start, part, cutoff);
        }

        if (end - part <= cutoff) {
            this.InsertionSort(list, part + 1, end);
        } else {
            this.Modified_QuickSort(list, part + 1, end, cutoff);
        }
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param param The length of the initial splits that are sorted prior to merging
     */
    public void TimSort(ArrayList<T> list, int start, int end, int param){
        int l = end - start + 1;
        if (l <= param) {
            this.InsertionSort(list, start, end);
        } else {
            for (int i = start; i <= end; i += param) {
                int n = Math.min(i + param - 1, end);
                this.InsertionSort(list, i, n);
            }

            for (int h = param; h <= end; h = h * MIDDLE_IDX) {
                for (int k = start; k < end; k += h * MIDDLE_IDX) {
                    int left = k;
                    int middle = left + h - 1;
                    if (middle > end) {
                        break;
                    }
                    int right = Math.min(left + h * MIDDLE_IDX - 1, end);
                    merge(list, left, middle, right);
                }
            }
        }
    }
}
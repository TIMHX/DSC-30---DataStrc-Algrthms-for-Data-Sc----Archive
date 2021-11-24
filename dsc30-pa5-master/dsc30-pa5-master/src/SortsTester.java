import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SortsTester {



    Sorts<Integer> sorts = new Sorts<>();
    Integer[] randomArr = {9, 4, 2, 5, 7, 0, 3, 6, 8, 1, 3, 5};
    ArrayList<Integer> random = new ArrayList<>(Arrays.asList(randomArr));
    Integer[] sortedArr = {0, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9};
    ArrayList<Integer> sorted = new ArrayList<>(Arrays.asList(sortedArr));
    Integer[] reversedArr = {9, 8, 7, 6, 5, 5, 4, 3, 3, 2, 1, 0};
    ArrayList<Integer> reversed = new ArrayList<>(Arrays.asList(reversedArr));
    Integer[] partialSortedArr = {0, 1, 2, 4, 3, 3, 6, 5, 5, 7, 8, 9};
    ArrayList<Integer> partialSorted = new ArrayList<>(Arrays.asList(partialSortedArr));

    ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(sortedArr));

    @org.junit.Test
    public void InsertionSortTest() {
        sorts.InsertionSort(random, 0, random.size() - 1);
        sorts.InsertionSort(sorted, 0, sorted.size() - 1);
        sorts.InsertionSort(reversed, 0, reversed.size() - 1);
        sorts.InsertionSort(partialSorted, 0, partialSorted.size() - 1);

        for (int i=0; i<random.size(); i++) {
            assertSame(answer.get(i),random.get(i));
            System.out.print(random.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<sorted.size(); i++) {
            assertSame(answer.get(i),sorted.get(i));
            System.out.print(sorted.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<reversed.size(); i++) {
            assertSame(answer.get(i),reversed.get(i));
            System.out.print(reversed.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<partialSorted.size(); i++) {
            assertSame(answer.get(i),partialSorted.get(i));
            System.out.print(partialSorted.get(i));
        }

    }

    @org.junit.Test
    public void MergeSortTest() {
        sorts.MergeSort(random, 0, random.size() - 1);
        sorts.MergeSort(sorted, 0, sorted.size() - 1);
        sorts.MergeSort(reversed, 0, reversed.size() - 1);
        sorts.MergeSort(partialSorted, 0, partialSorted.size() - 1);

        for (int i=0; i<random.size(); i++) {
            assertSame(answer.get(i),random.get(i));
            System.out.print(random.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<sorted.size(); i++) {
            assertSame(answer.get(i),sorted.get(i));
            System.out.print(sorted.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<reversed.size(); i++) {
            assertSame(answer.get(i),reversed.get(i));
            System.out.print(reversed.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<partialSorted.size(); i++) {
            assertSame(answer.get(i),partialSorted.get(i));
            System.out.print(partialSorted.get(i));
        }
    }

    @org.junit.Test
    public void QuickSortTest() {
        sorts.QuickSort(random, 0, random.size() - 1);
        sorts.QuickSort(sorted, 0, sorted.size() - 1);
        sorts.QuickSort(reversed, 0, reversed.size() - 1);
        sorts.QuickSort(partialSorted, 0, partialSorted.size() - 1);

        for (int i=0; i<random.size(); i++) {
            assertSame(answer.get(i),random.get(i));
            System.out.print(random.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<sorted.size(); i++) {
            assertSame(answer.get(i),sorted.get(i));
            System.out.print(sorted.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<reversed.size(); i++) {
            assertSame(answer.get(i),reversed.get(i));
            System.out.print(reversed.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<partialSorted.size(); i++) {
            assertSame(answer.get(i),partialSorted.get(i));
            System.out.print(partialSorted.get(i));
        }
    }

    @org.junit.Test
    public void Modified_QuickSortTest() {
        sorts.Modified_QuickSort(random, 0, random.size() - 1,2);
        sorts.Modified_QuickSort(sorted, 0, sorted.size() - 1,3);
        sorts.Modified_QuickSort(reversed, 0, reversed.size() - 1,4);
        sorts.Modified_QuickSort(partialSorted, 0, partialSorted.size() - 1,5);

        for (int i=0; i<random.size(); i++) {
            assertSame(answer.get(i),random.get(i));
            System.out.print(random.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<sorted.size(); i++) {
            assertSame(answer.get(i),sorted.get(i));
            System.out.print(sorted.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<reversed.size(); i++) {
            assertSame(answer.get(i),reversed.get(i));
            System.out.print(reversed.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<partialSorted.size(); i++) {
            assertSame(answer.get(i),partialSorted.get(i));
            System.out.print(partialSorted.get(i));
        }
    }

    @org.junit.Test
    public void TimSortTest() {
        sorts.TimSort(random, 0, random.size() - 1, 2);
        sorts.TimSort(sorted, 0, sorted.size() - 1,3);
        sorts.TimSort(reversed, 0, reversed.size() - 1,4);
        sorts.TimSort(partialSorted, 0, partialSorted.size() - 1,5);

        for (int i=0; i<random.size(); i++) {
            assertSame(answer.get(i),random.get(i));
            System.out.print(random.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<sorted.size(); i++) {
            assertSame(answer.get(i),sorted.get(i));
            System.out.print(sorted.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<reversed.size(); i++) {
            assertSame(answer.get(i),reversed.get(i));
            System.out.print(reversed.get(i));
        }
        System.out.print("\n");
        for (int i=0; i<partialSorted.size(); i++) {
            assertSame(answer.get(i),partialSorted.get(i));
            System.out.print(partialSorted.get(i));
        }
    }
}
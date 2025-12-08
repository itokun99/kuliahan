import java.util.Arrays;

public class CountingSort {

  public static void main(String[] args) {
    // min 8 length of elements as per the question
    // note: Counting Sort works best with a known range of integers
    int[] data = {5, 2, 8, 10, 5, 1, 20, 15};

    System.out.println("--- SOAL 2: COUNTING SORT (DESCENDING) ---");
    System.out.println("Input: " + Arrays.toString(data));

    countingSort(data);

    System.out.println("Output: " + Arrays.toString(data));
  }

  public static void countingSort(int[] arr) {
    int n = arr.length;
    if (n == 0) return;

    // find the maximum value in the array
    int max = arr[0];
    for (int i = 1; i < n; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }

    // initializing the count array (counter)
    // the size is (max + 1) to accommodate the index 'max'
    int[] count = new int[max + 1];

    // counting frequency of each element in the original array
    for (int i = 0; i < n; i++) {
      count[arr[i]]++;
    }

    // build the sorted array in descending order
    // reverse iteration for descending order from max to 0
    // the biggest numbers are placed first
    int index = 0;
    for (int i = max; i >= 0; i--) {
      while (count[i] > 0) {
        arr[index] = i; // insert the number into the original array
        index++;
        count[i]--; // decrease the count utill it reaches zero
      }
    }
  }
}

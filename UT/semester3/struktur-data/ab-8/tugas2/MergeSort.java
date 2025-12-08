import java.util.Arrays;

public class MergeSort {

  public static void main(String[] args) {
    // min 8 length of elements
    int[] data = {15, 3, 99, 12, 1, 45, 12, 8};

    System.out.println("--- SOAL 1: MERGE SORT (DESCENDING) ---");
    System.out.println("Input: " + Arrays.toString(data));

    // call the sort function
    mergeSort(data, 0, data.length - 1);

    System.out.println("Output: " + Arrays.toString(data));
  }

  // recursive for Divide an  array (Divide)
  public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      // mid value
      int mid = (left + right) / 2;

      // divide left side
      mergeSort(arr, left, mid);

      // divide right side
      mergeSort(arr, mid + 1, right);

      // process to merge (Conquer / Merge)
      merge(arr, left, mid, right);
    }
  }

  // Function to merge two sub-arrays into a sorted array
  public static void merge(int[] arr, int left, int mid, int right) {
    // get the size of two sub-arrays to be merged
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // temporary arrays
    int[] LeftArray = new int[n1];
    int[] RightArray = new int[n2];

    // copy data to temporary arrays
    for (int i = 0; i < n1; ++i) LeftArray[i] = arr[left + i];
    for (int j = 0; j < n2; ++j) RightArray[j] = arr[mid + 1 + j];

    // merge process
    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
      // because we want DESCENDING order
      // use >= operator
      // and the element from the left side is taken first
      if (LeftArray[i] >= RightArray[j]) {
        arr[k] = LeftArray[i];
        i++;
      } else {
        arr[k] = RightArray[j];
        j++;
      }
      k++;
    }

    // copy remaining elements of LeftArray if any
    while (i < n1) {
      arr[k] = LeftArray[i];
      i++;
      k++;
    }

    // copy remaining elements of RightArray if any
    while (j < n2) {
      arr[k] = RightArray[j];
      j++;
      k++;
    }
  }
}

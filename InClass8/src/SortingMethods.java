public class SortingMethods {
    public void selectionSort(int[] x) {
        for (int i = 0; i < x.length - 1; i++) {
            int jmin = i;
            for (int j = i + 1; j < x.length - 1; j++) {
                if (x[j] < x[jmin]) {
                    jmin = j;
                }
            }
            swap(x, i, jmin);
        }
    }

    public void bubbleSort(int[] x) {
        for (int i = x.length - 1; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (x[j] > x[j + 1]) {
                    swap(x, j, j+1);
                }
            }
        }
    }

    public void insertionSort(int[] x) {
        for (int i = 1; i < x.length - 1; i++) {
            int key = x[i];
            int j = i;
            while (j > 0 && x[j-1] > key) {
                x[j] = x[j-1];
                j = j - 1;
            }
            x[j] = key;
        }
    }

    public void mergeSort(int[] x) {

    }

    public void heapSort(int[] x) {

    }

    public void quickSort(int[] x) {
        
    }

    public void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
    public static void main(String[] args) {

    }
}

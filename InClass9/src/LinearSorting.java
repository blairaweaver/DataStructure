public class LinearSorting {

    public static int[] insertionSort(int[] x, int d, int exp) {
        int[] temp = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            temp[i] = (x[i]/exp) % d;
        }
        for (int i = 1; i < temp.length; i++) {
            int key = temp[i];
            int realkey = x[i];
            int j = i;
            while (j > 0 && temp[j-1] > key) {
                x[j] = x[j-1];
                temp[j] = temp[j-1];
                j = j - 1;
            }
            temp[j] = key;
            x[j] = realkey;

        }
        return x;
    }

    public static int[] countingSort(int[] x) {
        int max = findMax(x);
        int[] counts = new int[max + 1];
        int[] sorted = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            counts[x[i]] += 1;
        }
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] != 0) {
                sorted[pos] = i;
                counts[i]--;
                pos++;
            }
        }
        return sorted;
    }

    public static int findMax(int[] x) {
        int max = x[0];
        for (int i = 1; i < x.length; i++) {
            if (x[0] < x[i]) {
                max = x[i];
            }
        }
        return max;
    }

//    public static double[] bucketSort(double[] x) {
//        int numOfBins = x.length;
//        ArrayList[] bins = new ArrayList[numOfBins];
//        for (int i = 0; i < bins.length; i++) {
//            bins[i] = new ArrayList();
//        }
//
//
//    }

    public static int[] radixSort(int[] x, int d) {
        int max = findMax(x);
        for (int i = 1; max/i > 0; i*=10) {
            insertionSort(x, 10 * i, i);
        }
        return x;
    }

    public static void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] x = new int[]{1,0,2,0,1,1,0,2};
        printArray(x);
        printArray(countingSort(x));

        int[] y = new int[]{344, 125, 333, 134, 224, 334, 143, 225, 325, 243};
        printArray(y);
        printArray(radixSort(y, 3));

//        double[] z = new double[]{0.5, 0.25, 0.31, 0.11, 0.93, 0.88, 0.57, 0.76};
    }
}

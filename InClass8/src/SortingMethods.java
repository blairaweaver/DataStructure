class MyPair {
    private int[] array1;
    private int[] array2;
    public MyPair(int[] x, int[]y) {
        array1 = x;
        array2 = y;
    }

    public int[] getArray1() {
        return array1;
    }

    public int[] getArray2() {
        return array2;
    }
}

class MyTrio {
    private ModArray array1;
    private ModArray array2;
    private ModArray array3;
    public MyTrio(ModArray x, ModArray y, ModArray z) {
        array1 = x;
        array2 = y;
        array3 = z;
    }

    public MyTrio(int[] x, int[] y, int[] z) {
        array1 = new ModArray(x);
        array2 = new ModArray(y);
        array3 = new ModArray(z);
    }

    public ModArray getArray1() {
        return array1;
    }

    public ModArray getArray2() {
        return array2;
    }

    public ModArray getArray3() {
        return array3;
    }

    public void setArray1(int[] array1) {
        this.array1 = new ModArray(array1);
    }

    public void setArray2(int[] array2) {
        this.array2 = new ModArray(array2);
    }

    public void setArray3(int[] array3) {
        this.array3 = new ModArray(array3);
    }

    public void setArray1(ModArray array1) {
        this.array1 = array1;
    }

    public void setArray2(ModArray array2) {
        this.array2 = array2;
    }

    public void setArray3(ModArray array3) {
        this.array3 = array3;
    }
}

class ModArray {
    private int[] array1;
    private int size;
    private int firstPos;
    private int lastPos;

    public ModArray(int[] x) {
        array1 = x;
        size = x.length;
        firstPos = 0;
        lastPos = x.length - 1;
    }

    public ModArray(int x) {
        array1 = new int[x];
        size = 0;
        firstPos = 0;
        lastPos = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getLastPos() {
        return lastPos;
    }

    public int removeFirst(){
        firstPos++;
        size--;
        return array1[firstPos-1];
    }

    public int[] getArray1() {
        return array1;
    }

    public int first() {
        return array1[firstPos];
    }

    public int getSize() {
        return size;
    }

    public int getElement(int pos) {
        return array1[pos];
    }

    public void add(int x) {
        if (lastPos + 1 < array1.length) {
            array1[lastPos + 1] = x;
            lastPos++;
            size++;
        }
        else {
            int[] temp = new int[size * 2];
            for (int i = 0; i < array1.length; i++) {
                temp[i] = array1[i];
            }
            array1 = temp;
            array1[lastPos + 1] = x;
            lastPos++;
            size++;
        }
    }
}

class MaxHeap {}

public class SortingMethods {
    public static void selectionSort(int[] x) {
        for (int i = 0; i < x.length - 1; i++) {
            int jmin = i;
            for (int j = i + 1; j < x.length; j++) {
                if (x[j] < x[jmin]) {
                    jmin = j;
                }
            }
            swap(x, i, jmin);
        }
    }

    public static void bubbleSort(int[] x) {
        for (int i = x.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (x[j] > x[j + 1]) {
                    swap(x, j, j+1);
                }
            }
        }
    }

    public static void insertionSort(int[] x) {
        for (int i = 1; i < x.length; i++) {
            int key = x[i];
            int j = i;
            while (j > 0 && x[j-1] > key) {
                x[j] = x[j-1];
                j = j - 1;
            }
            x[j] = key;
        }
    }

    public static int[] mergeSort(int[] x) {
        if (x.length > 1) {
            MyPair split = mergePartition(x);
            int[] array1 = mergeSort(split.getArray1());
            int[] array2 = mergeSort(split.getArray2());
            int[] rejoined = merge(array1, array2);
            return rejoined;
        }
        else {
            return x;
        }
    }

    public static void heapSort(int[] x) {

    }

    public static ModArray quickSort(ModArray A) {
        if (A.getSize() > 1) {
            MyTrio split = quickPartition(A);
            split.setArray1(quickSort(split.getArray1()));
            split.setArray3(quickSort(split.getArray3()));
            ModArray rejoined = quickMerge(split);
            return rejoined;
        }
        else {
            return A;
        }
    }

    public static MyTrio quickPartition(ModArray x) {
        ModArray L = new ModArray(1);
        ModArray E = new ModArray(1);
        ModArray G = new ModArray(1);
        int pivot = x.getElement(x.getLastPos());
        while (!x.isEmpty()) {
            int y = x.removeFirst();
            if (y < pivot) {
                L.add(y);
            }
            else if (y == pivot) {
                E.add(y);
            }
            else {
                G.add(y);
            }
        }
        return new MyTrio(L, E, G);
    }

    public static MyPair mergePartition(int[] x) {
        int half = x.length/2;
        int[] array1 = new int[half];
        int[] array2 = new int[half + x.length%2];
        for (int i = 0; i < x.length; i++) {
            if (i < half) {
                array1[i] = x[i];
            }
            else {
                array2[i - half] = x[i];
            }
        }
        return new MyPair(array1, array2);
    }

    public static int[] merge(int[] x, int[] y) {
        ModArray S = new ModArray(x.length + y.length);
        ModArray A = new ModArray(x);
        ModArray B = new ModArray(y);
        while (!A.isEmpty() && !B.isEmpty()) {
            if (A.first() < B.first()) {
                S.add(A.removeFirst());
            }
            else
                S.add(B.removeFirst());
        }
        while (!A.isEmpty()) {
            S.add(A.removeFirst());
        }
        while (!B.isEmpty()) {
            S.add(B.removeFirst());
        }
        return S.getArray1();
    }

    public static ModArray quickMerge(MyTrio x) {
        ModArray L = x.getArray1();
        ModArray E = x.getArray2();
        ModArray G = x.getArray3();
        ModArray Comb = new ModArray(1);
        while (!L.isEmpty()) {
            Comb.add(L.removeFirst());
        }
        while (!E.isEmpty()) {
            Comb.add(E.removeFirst());
        }
        while (!G.isEmpty()) {
            Comb.add(G.removeFirst());
        }
        return Comb;
    }

    public static void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    public static void main(String[] args) {
        int[] x = new int[]{10, 9 ,4 ,6, 13, 5, 2, 1};

        ModArray quick = quickSort(new ModArray(x));
        for (int i = 0; i < quick.getSize(); i++) {
            System.out.print(quick.getElement(i) + " ");
        }
        System.out.println();

        int[] y = mergeSort(x);
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }
    }
}

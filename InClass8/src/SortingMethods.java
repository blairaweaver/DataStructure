import java.util.Arrays;

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

class MaxHeap {
    private int[] data;
    private int capacity;
    private int lastNode;

    public MaxHeap(int[] x) {
        data = new int[x.length];
        this.capacity = x.length;
        lastNode = -1;
//        A simple insert method to building
//        buildSim(x);
        build(x);
    }

    private int getRoot(){
        return data[0];
    }

    public int getCapacity() {
        return capacity;
    }

    private int getRightChildIndex(int parentIndex) {
        if (lastNode < parentIndex * 2 + 2) return -1;
        return parentIndex * 2 + 2;
    }
    private int getLeftChildIndex(int parentIndex) {
        if (lastNode < parentIndex * 2 + 1) return -1;
        return parentIndex * 2 + 1;
    }

    private int getParentIndex(int childIndex) {
        if (childIndex % 2 == 1) return (childIndex - 1) / 2;
        else return (childIndex - 2) / 2;
    }

    public void insert(int x){
        if (lastNode + 1 >= capacity) {
            expandHeap();
        }
        data[lastNode + 1] = x;
        lastNode++;
        upHeap(lastNode);
    }

    public int max(){
        if (lastNode != -1) {
            return data[0];
        }
        else return -1;
    }

    public int removeMax() {
        int temp = data[0];
        data[0] = data[lastNode];
        lastNode--;
        downHeap(0);
        return temp;
    }

    private void upHeap(int pos){
        if (pos == 0) return;
        if (data[pos] > data[getParentIndex(pos)]){ //change to data[getParentIndex(pos)] ??
            int temp = data[pos];
            data[pos] = data[getParentIndex(pos)]; //change to data[getParentIndex(pos)] ??
            data[getParentIndex(pos)] = temp; //change to data[getParentIndex(pos)] ??
            upHeap(getParentIndex(pos));
        }
        else return;
    }

    private void downHeap(int pos){
        if (getRightChildIndex(pos) == -1) {
            if (getLeftChildIndex(pos) == -1) return;
            else if (data[getLeftChildIndex(pos)] > data[pos]){
                int temp = data[pos];
                data[pos] = data[getLeftChildIndex(pos)];
                data[getLeftChildIndex(pos)] = temp;
                return;
            }
            else return;
        }
        else if (data[getLeftChildIndex(pos)] > data[getRightChildIndex(pos)] && data[getLeftChildIndex(pos)] > data[pos]) {
            int temp = data[pos];
            data[pos] = data[getLeftChildIndex(pos)];
            data[getLeftChildIndex(pos)] = temp;
            downHeap(getLeftChildIndex(pos));
        }
        else if (data[getRightChildIndex(pos)] > data[getLeftChildIndex(pos)] && data[getRightChildIndex(pos)] > data[pos]) {
            int temp = data[pos];
            data[pos] = data[getRightChildIndex(pos)];
            data[getRightChildIndex(pos)] = temp;
            downHeap(getRightChildIndex(pos));
        }

        else return;
    }

    private void expandHeap() {
        capacity = capacity * 2;
        int[] temp = new int[capacity];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data [i];
        }
        data = temp;
    }

    public void buildSim(int[] x) {
        for (int i = 0; i < x.length; i++){
            insert(x[i]);
        }
    }

    public void build(int[] x) {
        lastNode = x.length - 1;
        int h = (int) Math.ceil((Math.log(x.length + 1)/Math.log(2)) -1);
        for (int i = (int) Math.pow(2, h) - 1; i < x.length; i++){
            data[i] = x[i];
        }
        h--;
        for (int j = h; j >= 0; j--) {
            for (int i = (int) Math.pow(2, j) - 1; i < (int) Math.pow(2, j + 1) - 1; i++) {
                data[i] = x[i];
                downHeap(i);
            }
        }
    }

    public void inOrder(int index){
        if (index > lastNode) return;
        if (getLeftChildIndex(index) != -1) inOrder(getLeftChildIndex(index));
        System.out.print(data[index] + " ");
        if (getRightChildIndex(index) != -1) inOrder(getRightChildIndex(index));
    }

    public void print() {
        for (int i = 0; i < data.length; i ++) {
            System.out.print(data[i] + " ");
        }
    }
}

public class SortingMethods {
    public static int[] selectionSort(int[] x) {
        for (int i = 0; i < x.length - 1; i++) {
            int jmin = i;
            for (int j = i + 1; j < x.length; j++) {
                if (x[j] < x[jmin]) {
                    jmin = j;
                }
            }
            swap(x, i, jmin);
        }
        return x;
    }

    public static int[] bubbleSort(int[] x) {
        for (int i = x.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (x[j] > x[j + 1]) {
                    swap(x, j, j+1);
                }
            }
        }
        return x;
    }

    public static int[] insertionSort(int[] x) {
        for (int i = 1; i < x.length; i++) {
            int key = x[i];
            int j = i;
            while (j > 0 && x[j-1] > key) {
                x[j] = x[j-1];
                j = j - 1;
            }
            x[j] = key;
        }
        return x;
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

    public static int[] heapSort(int [] x) {
        MaxHeap xheap = new MaxHeap(x);
        int[] temp = new int[x.length];
        for (int i = x.length-1; i>=0;i--){
            temp[i] = xheap.removeMax();
        }
        return temp;
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
        System.out.println("Original Array");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();

        System.out.println("Quick Sort");
        ModArray quick = quickSort(new ModArray(x));
        for (int i = 0; i < quick.getSize(); i++) {
            System.out.print(quick.getElement(i) + " ");
        }
        System.out.println();

        System.out.println("Merge Sort");
        int[] y = mergeSort(Arrays.copyOf(x,x.length));
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }
        System.out.println();

        System.out.println("Selection Sort");
        int[] sel = selectionSort(Arrays.copyOf(x,x.length));
        for (int i = 0; i < sel.length; i++) {
            System.out.print(sel[i] + " ");
        }
        System.out.println();

        System.out.println("Insertion Sort");
        int[] insert = insertionSort(Arrays.copyOf(x,x.length));
        for (int i = 0; i < insert.length; i++) {
            System.out.print(insert[i] + " ");
        }
        System.out.println();

        System.out.println("Bubble Sort");
        int[] bubble = bubbleSort(Arrays.copyOf(x,x.length));
        for (int i = 0; i < bubble.length; i++) {
            System.out.print(bubble[i] + " ");
        }
        System.out.println();

        System.out.println("Heap Sort");
        int[] heap = heapSort(x);
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
    }
}

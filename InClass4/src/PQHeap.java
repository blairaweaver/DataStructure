import java.util.Random;

public class PQHeap {
    private int[] data;
    private int[] values; //needed?
    private int capacity;
    private int lastNode;

    private int getRoot(){
        return data[0];
    }

    public PQHeap(int capacity){
        data = new int[capacity];
        this.capacity = capacity;
        lastNode = -1;
    }

    public PQHeap(int[] x){
        data = new int[x.length];
        this.capacity = x.length;
        lastNode = -1;
//        A simple insert method to building
//        buildSim(x);
        build(x);
    }

    //    rank of root = 0
    //    rank of left child is 2 * rank of parent + 1
    //    rank of right child is 2 * rank of parent + 2

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

    public int min(){
        if (lastNode != -1) {
            return data[0];
        }
        else return -1;
    }

    public void removeMin() {
        data[0] = data[lastNode];
        lastNode--;
        downHeap(0);
    }

    private void upHeap(int pos){
        if (pos == 0) return;
        if (data[pos] < data[getParentIndex(pos)]){ //change to data[getParentIndex(pos)] ??
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
            else if (data[getLeftChildIndex(pos)] < data[pos]){
                int temp = data[pos];
                data[pos] = data[getLeftChildIndex(pos)];
                data[getLeftChildIndex(pos)] = temp;
                return;
            }
            else return;
        }
        else if (data[getLeftChildIndex(pos)] < data[getRightChildIndex(pos)] && data[getLeftChildIndex(pos)] < data[pos]) {
            int temp = data[pos];
            data[pos] = data[getLeftChildIndex(pos)];
            data[getLeftChildIndex(pos)] = temp;
            downHeap(getLeftChildIndex(pos));
        }
        else if (data[getRightChildIndex(pos)] < data[getLeftChildIndex(pos)] && data[getRightChildIndex(pos)] < data[pos]) {
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

    public static void main(String[] args) {
        int[] testA = new int[20];
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < testA.length; i++){
            testA[i] = rand.nextInt(testA.length);
        }
        System.out.println();
        PQHeap test = new PQHeap(testA);
        test.inOrder(0);
        System.out.println();
        test.print();

    }
}

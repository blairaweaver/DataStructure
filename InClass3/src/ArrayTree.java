public class ArrayTree {
    private Object[] data;
    private int size = 0;
    private int height = 0;
    private int capacity = 0;

    public Object getRoot() {return data[0];}
    public int getSize() {return size;}
    public int getHeight() {return height;}
    public ArrayTree(int capacity) {
        data = new Object[capacity];
        this.capacity = capacity;
    }
    public ArrayTree(Object root, int capacity) {
        data = new Object[capacity];
        data[0] = root;
        size++;
        this.capacity = capacity;
    }
//    rank of root = 0
//    rank of left child is 2 * rank of parent + 1
//    rank of right child is 2 * rank of parent + 2
    public Object getRightChild(int parentIndex) {
        if (parentIndex >= capacity -1 || parentIndex * 2 + 2 >= capacity) return null;
        return data[parentIndex * 2 + 2];
    }
    public Object getLeftChild(int parentIndex) {
        if (parentIndex >= capacity -1 || parentIndex * 2 + 1 >= capacity) return null;
        return data[parentIndex * 2 + 1];
    }

    public Object getParent(int childIndex) {
        if (childIndex % 2 == 1) return data[(childIndex - 1) / 2];
        else return data[(childIndex - 2) / 2];
    }
    public void setLeftChild(int parentIndex, Object child) {
        if (2 * parentIndex + 1 >= data.length) expandTree(data.length * 2);
        data[2 * parentIndex + 1] = child;
        size++;
    }
    public void setRightChild(int parentIndex, Object child) {
        if (2 * parentIndex + 2 >= data.length) expandTree(data.length * 2);
        data[2 * parentIndex + 2] = child;
        size++;
    }

    public int numberOfAncestors(int index) {
//        root returns zero since the first call will add to the total
        if (index == 0) return 0;
        else if (index % 2 == 1) return 1 + numberOfAncestors((index - 1) / 2);
        else return 1 + numberOfAncestors((index - 2) / 2);
    }

    public void addLayer(int parentIndex, Object leftChild, Object rightChild) {
        setLeftChild(parentIndex, leftChild);
        setRightChild(parentIndex, rightChild);
        if (numberOfAncestors(parentIndex * 2 + 1) > height) height = numberOfAncestors(parentIndex * 2 + 1);
    }

    public boolean isEmpty() {return size==0;}
    public boolean isInternal(int index) {
        if (getLeftChild(index) != null || getRightChild(index) != null) return true;
        else return false;
    }
    public boolean isExternal(int index) {
        if (getRightChild(index) != null || getLeftChild(index) != null) return true;
        else return false;
    }
    public void inOrder(int index){
        if (index >= capacity -1) return;
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        if (data[index] == null) return;
        else System.out.print(data[index]);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
    }
    public void preOrder(int index){
        if (index >= capacity  - 1) return;
        if (data[index] == null) return;
        else System.out.print(data[index]);
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
    }
    public void postOrder(int index){
        if (index >= capacity -1) return;
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
        if (data[index] == null) return;
        else System.out.print(data[index]);
    }

    public void expandTree(int newCapacity) {
        Object[] temp = new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data [i];
        }
        data = temp;
        capacity = newCapacity;
    }
    public static void main(String[] args) {
        ArrayTree test = new ArrayTree(10);
        System.out.println(test.isEmpty());
        test.setLeftChild(0, '-');
        test.setRightChild(0, '3');
        test.setLeftChild(1, '1');
        test.setRightChild(1, '3');
        System.out.println(test.getSize());
        System.out.println(test.isExternal(0));
        System.out.println(test.getHeight());
        System.out.println(test.isInternal(1));
        test.inOrder(0);
        System.out.println("");
        test.preOrder(0);
        System.out.println("");
        test.postOrder(0);
    }
}

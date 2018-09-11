public class ArrayTree {
    private Object[] data;
    private int size = 0;
    private int height = 0;

    public Object getRoot() {return data[0];}
    public int getSize() {return size;}
    public int getHeight() {return height;}
    public ArrayTree(int capacity) {
        data = new Object[capacity];
    }
    public ArrayTree(Object root, int capacity) {
        data = new Object[capacity];
        data[0] = root;
        size++;
    }
//    rank of root = 0
//    rank of left child is 2 * rank of parent + 1
//    rank of right child is 2 * rank of parent + 2
    public Object getRightChild(int parentIndex) {
        return data[parentIndex * 2 + 2];
    }
    public Object getLeftChild(int parentIndex) {
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
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        System.out.println(data[index]);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
    }
    public void preOrder(int index){
        System.out.println(data[index]);
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
    }
    public void postOrder(int index){
        if (getLeftChild(index) != null) inOrder(index * 2 + 1);
        if (getRightChild(index) != null) inOrder(index * 2 + 2);
        System.out.println(data[index]);
    }

    public void expandTree(int newCapacity) {
        Object[] temp = new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data [i];
        }
        data = temp;
    }
}

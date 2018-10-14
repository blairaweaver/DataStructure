public class BinarySearchTree<K extends Comparable<K>, E> {
    private static class BinaryTreeNode<K, E>{
        private E element;
        private K key;
        private BinaryTreeNode<K, E> parent;
        private BinaryTreeNode<K, E> leftChild;
        private BinaryTreeNode<K, E> rightChild;
        public BinaryTreeNode(K k, E e, BinaryTreeNode<K, E> p) {
            this.element = e;
            this.key = k;
            this.parent = p;
            this.leftChild = null;
            this.rightChild = null;
        }

        public E getElement() {
            return element;
        }

        public K getKey() {return key;}

        public BinaryTreeNode<K, E> getParent() {
            return parent;
        }

        public void setParent(BinaryTreeNode<K, E> p){
            this.parent = p;
        }

        public BinaryTreeNode<K, E> getLeftChild() {
            return leftChild;
        }

        public BinaryTreeNode<K, E> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(BinaryTreeNode<K, E> l) {
            this.leftChild = l;
        }

        public void setRightChild(BinaryTreeNode<K, E> r) {
            this.rightChild = r;
        }

        public BinaryTreeNode<K, E> getSibling(BinaryTreeNode<K, E> sibling){
            BinaryTreeNode<K, E> parent = sibling.getParent();
            if (parent.rightChild == sibling) return parent.leftChild;
            else return parent.rightChild;
        }

        public int numChildren() {
            int sum = 0;
            if (leftChild != null) sum++;
            if (rightChild != null) sum++;
            return sum;
        }
    }

    private BinaryTreeNode<K, E> root;
    private int size = 0;
    private int height = 0;
    public BinarySearchTree() {
        root = null;
    }
    public BinarySearchTree(K k, E e){
        this.root = new BinaryTreeNode<>(k, e, null);
        size++;
    }

    public BinaryTreeNode getRoot() {return this.root;}

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

//    reworked the isExternal
//    public boolean isInternal(BinaryTreeNode<K, E> e){
//        if (e.getLeftChild() != null && e.getRightChild() != null) {
//            return true;
//        }
//        else return false;
//    }

    public boolean isExternal(BinaryTreeNode<K, E> e){
        if (e.getElement() == null) {
            return true;
        }
        else return false;
    }

    public boolean isRoot(BinaryTreeNode<K, E> e) {
        return root == e;
    }

    public void inOrder(BinaryTreeNode<K, E> e) {
        if (e.getLeftChild() != null) inOrder(e.getLeftChild());
        System.out.print(e.getElement());
        if (e.getRightChild() != null) inOrder(e.getRightChild());
    }

    public void preOrder(BinaryTreeNode<K, E> e) {
        System.out.print(e.getElement());
        if (e.getLeftChild() != null) preOrder(e.getLeftChild());
        if (e.getRightChild() != null) preOrder(e.getRightChild());
    }

    public void postOrder(BinaryTreeNode<K, E> e) {
        if (e.getLeftChild() != null) postOrder(e.getLeftChild());
        if (e.getRightChild() != null) postOrder(e.getRightChild());
        System.out.print(e.getElement());
    }

    public void addLayer(BinaryTreeNode<K, E> parent, E left, E right) {
        BinaryTreeNode leftChild = new BinaryTreeNode<>(left, parent);
        BinaryTreeNode rightChild = new BinaryTreeNode<>(right, parent);
        parent.setLeftChild(leftChild);
        size++;
        parent.setRightChild(rightChild);
        size++;
        if (getHeightAtNode(leftChild) > height) {
            height = getHeightAtNode(leftChild);
        }
    }

    public int getHeightAtNode(BinaryTreeNode<K, E> e){
//        root returns zero since the first call will add to the total
        if (e.getParent() == null) return 0;
        else return 1 + getHeightAtNode(e.getParent());
    }

    public int getHeight() {return height;}

    public BinaryTreeNode<K, E> TreeSearch(K k, BinaryTreeNode<K, E> e) {
        if (isExternal(e)) return e;
        int cmp = k.compareTo(e.getKey());
        if (cmp < 0) return TreeSearch(k, e.getLeftChild());
        else if (cmp == 0) return e;
        else return TreeSearch(k, e.getRightChild());
    }

    public static void main(String[] args){
//        BinarySearchTree test = new BinarySearchTree<>('+');
//        System.out.println(test.isEmpty());
//        test.addLayer(test.getRoot(), '-', '3');
//        test.addLayer(test.getRoot().getLeftChild(), '1', '3');
//        System.out.println(test.getSize());
//        System.out.println(test.isExternal(test.getRoot()));
//        System.out.println(test.getHeight());
//        System.out.println(test.isRoot(test.getRoot().getRightChild()));
//        System.out.println(test.isInternal(test.getRoot()));
//        test.inOrder(test.getRoot());
//        System.out.println("");
//        test.preOrder(test.getRoot());
//        System.out.println("");
//        test.postOrder(test.getRoot());
    }
}

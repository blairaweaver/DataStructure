public class LinkTree<E> {
    private static class BinaryTreeNode<E>{
        private E element;
        private BinaryTreeNode<E> parent;
        private BinaryTreeNode<E> leftChild;
        private BinaryTreeNode<E> rightChild;
        public BinaryTreeNode(E e, BinaryTreeNode<E> p) {
            this.element = e;
            this.parent = p;
            this.leftChild = null;
            this.rightChild = null;
        }

        public E getElement() {
            return element;
        }

        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        public void setParent(BinaryTreeNode<E> p){
            this.parent = p;
        }

        public BinaryTreeNode<E> getLeftChild() {
            return leftChild;
        }

        public BinaryTreeNode<E> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(BinaryTreeNode<E> l) {
            this.leftChild = l;
        }

        public void setRightChild(BinaryTreeNode<E> r) {
            this.rightChild = r;
        }

        public BinaryTreeNode<E> getSibling(BinaryTreeNode<E> sibling){
            BinaryTreeNode<E> parent = sibling.getParent();
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

    private BinaryTreeNode<E> root;
    private int size = 0;
    private int height = 0;
    public LinkTree() {
        root = null;
    }
    public LinkTree(E e){
        this.root = new BinaryTreeNode<>(e, null);
        size++;
    }

    public BinaryTreeNode getRoot() {return this.root;}

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isInternal(BinaryTreeNode<E> e){
        if (e.getLeftChild() != null && e.getRightChild() != null) {
            return true;
        }
        else return false;
    }

    public boolean isExternal(BinaryTreeNode<E> e){
        if (e.getLeftChild() != null || e.getRightChild() != null) {
            return false;
        }
        else return true;
    }

    public boolean isRoot(BinaryTreeNode<E> e) {
        return root == e;
    }

    public void inOrder(BinaryTreeNode<E> e) {
        if (e.getLeftChild() != null) inOrder(e.getLeftChild());
        System.out.print(e.getElement());
        if (e.getRightChild() != null) inOrder(e.getRightChild());
    }

    public void preOrder(BinaryTreeNode<E> e) {
        System.out.print(e.getElement());
        if (e.getLeftChild() != null) preOrder(e.getLeftChild());
        if (e.getRightChild() != null) preOrder(e.getRightChild());
    }

    public void postOrder(BinaryTreeNode<E> e) {
        if (e.getLeftChild() != null) postOrder(e.getLeftChild());
        if (e.getRightChild() != null) postOrder(e.getRightChild());
        System.out.print(e.getElement());
    }

    public void addLayer(BinaryTreeNode<E> parent, E left, E right) {
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

    public int getHeightAtNode(BinaryTreeNode<E> e){
//        root returns zero since the first call will add to the total
        if (e.getParent() == null) return 0;
        else return 1 + getHeightAtNode(e.getParent());
    }

    public int getHeight() {return height;}

    public static void main(String[] args){
        LinkTree test = new LinkTree<>('+');
        System.out.println(test.isEmpty());
        test.addLayer(test.getRoot(), '-', '3');
        test.addLayer(test.getRoot().getLeftChild(), '1', '3');
        System.out.println(test.getSize());
        System.out.println(test.isExternal(test.getRoot()));
        System.out.println(test.getHeight());
        System.out.println(test.isRoot(test.getRoot().getRightChild()));
        System.out.println(test.isInternal(test.getRoot()));
        test.inOrder(test.getRoot());
        System.out.println("");
        test.preOrder(test.getRoot());
        System.out.println("");
        test.postOrder(test.getRoot());
    }
}

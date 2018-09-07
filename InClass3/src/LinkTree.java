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
    public int height = 0;
    public LinkTree(E e){
        this.root = new BinaryTreeNode<>(e, null);
        size++;
    }

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
        System.out.println(e.getElement());
        if (e.getRightChild() != null) inOrder(e.getRightChild());
    }

    public void preOrder(BinaryTreeNode<E> e) {
        System.out.println(e.getElement());
        if (e.getLeftChild() != null) preOrder(e.getLeftChild());
        if (e.getRightChild() != null) preOrder(e.getRightChild());
    }

    public void postOrder(BinaryTreeNode<E> e) {
        if (e.getLeftChild() != null) postOrder(e.getLeftChild());
        if (e.getRightChild() != null) postOrder(e.getRightChild());
        System.out.println(e.getElement());
    }

    public static void main(String[] args){

    }
}

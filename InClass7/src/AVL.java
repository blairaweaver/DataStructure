public class AVL<K extends Comparable<K>, E> {
    private static class AVLNode<K, E>{
        private E element;
        private K key;
        private AVLNode<K, E> parent;
        private AVLNode<K, E> leftChild;
        private AVLNode<K, E> rightChild;
        public AVLNode(K k, E e, AVLNode<K, E> p) {
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

        public void setElement(E element) {
            this.element = element;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public AVLNode<K, E> getParent() {
            return parent;
        }

        public void setParent(AVLNode<K, E> p){
            this.parent = p;
        }

        public AVLNode<K, E> getLeftChild() {
            return leftChild;
        }

        public AVLNode<K, E> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(AVLNode<K, E> l) {
            this.leftChild = l;
        }

        public void setRightChild(AVLNode<K, E> r) {
            this.rightChild = r;
        }

    }

    private AVLNode<K, E> root;
    private int size = 0;
    private int height = 0;
    public AVL() {
        root = null;
    }
    public AVL(K k, E e){
        this.root = new AVLNode<>(k, e, null);
        root.setLeftChild(new AVLNode<>(null, null, root));
        root.setRightChild(new AVLNode<>(null, null, root));
        size++;
    }

    public AVLNode getRoot() {return this.root;}

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isExternal(AVLNode<K, E> e){
        if (e.getElement() == null) {
            return true;
        }
        else return false;
    }

    public boolean isRoot(AVLNode<K, E> e) {
        return root == e;
    }

    public void inOrder(AVLNode<K, E> e) {
        if (e.getLeftChild() != null) inOrder(e.getLeftChild());
        if (e.getElement() != null) System.out.print(e.getElement());
        if (e.getRightChild() != null) inOrder(e.getRightChild());
    }

    public void preOrder(AVLNode<K, E> e) {
        if (e.getElement() != null) System.out.print(e.getElement());
        if (e.getLeftChild() != null) preOrder(e.getLeftChild());
        if (e.getRightChild() != null) preOrder(e.getRightChild());
    }

    public void postOrder(AVLNode<K, E> e) {
        if (e.getLeftChild() != null) postOrder(e.getLeftChild());
        if (e.getRightChild() != null) postOrder(e.getRightChild());
        if (e.getElement() != null) System.out.print(e.getElement());
    }

    public AVLNode<K, E> TreeSearch(K k, AVLNode<K, E> e) {
        if (isExternal(e)) return e;
        int cmp = k.compareTo(e.getKey());
        if (cmp < 0) return TreeSearch(k, e.getLeftChild());
        else if (cmp == 0) return e;
        else return TreeSearch(k, e.getRightChild());
    }

    public void insert(K k, E e) {
        AVLNode<K, E> search = TreeSearch(k, root);
        if (isExternal(search)) add(search, k, e);
        else updateNode(search, e);
    }

    public void delete(K k) {
        AVLNode<K, E> search = TreeSearch(k, root);

//        didn't find the key in tree
        if (isExternal(search)) return;

        AVLNode<K, E> parent = search.getParent();

        int dir = 0;
//      determine if the node to delete is left or right child of parent
        if (parent.getLeftChild() == search) {
            dir = -1;
        }

//        If the node only has null children
        if (isExternal(search.getLeftChild()) && isExternal(search.getRightChild())) {
            if (dir == -1) {
                parent.setLeftChild(new AVLNode<>(null, null, parent));
            }
            else parent.setRightChild(new AVLNode<>(null, null, parent));
            return;
        }

//        If the node has one null child
        if (isExternal(search.getLeftChild())) {
            if (dir == -1) {
                parent.setLeftChild(search.getRightChild());
                search.getRightChild().setParent(parent);
            }
            else {
                parent.setRightChild(search.getRightChild());
                search.getRightChild().setParent(parent);
            }
            return;
        }

//        If two children, find smallest on right side to place in old node spot
        AVLNode<K, E> minRight = findMin(search.getRightChild());
//        Remove link from parent of smallest
        if (minRight.getParent().getLeftChild() == minRight) {
            minRight.getParent().setLeftChild(new AVLNode<>(null, null, minRight.getParent()));
        }
        else {
            minRight.getParent().setRightChild(new AVLNode<>(null, null, minRight.getParent()));
        }
//        add new link for smallest and other nodes
        minRight.setParent(parent);
        minRight.setRightChild(search.getRightChild());
        minRight.setLeftChild(search.getLeftChild());
        search.getRightChild().setParent(minRight);
        search.getLeftChild().setParent(minRight);
        if (dir == -1) {
            parent.setLeftChild(minRight);
        }
        else {
            parent.setRightChild(minRight);
        }
    }

    private void add(AVLNode<K, E> old, K k, E e) {
        old.setKey(k);
        old.setElement(e);
        old.setLeftChild(new AVLNode<>(null, null, old));
        old.setRightChild(new AVLNode<>(null, null, old));
    }

    private void updateNode(AVLNode<K, E> old, E e) {
        old.setElement(e);
    }

    private AVLNode<K, E> findMax(AVLNode<K, E> start) {
        if (!isExternal(start.getRightChild())) return findMax(start.getRightChild());
        else return start;
    }

    private AVLNode<K, E> findMin(AVLNode<K, E> start) {
        if (!isExternal(start.getLeftChild())) return findMin(start.getLeftChild());
        else return start;
    }

    public static void main(String[] args){
        AVL test = new AVL(6, '6');
        test.insert(2, '2');
        test.insert(1, '1');
        test.insert(4, '4');
        test.insert(9, '9');
        test.insert(8, '8');
        System.out.println(test.TreeSearch(4, test.root).getKey());
        test.inOrder(test.root);
        test.insert(5, '5');
        System.out.println();
        test.inOrder(test.root);
        test.delete(4);
        System.out.println();
        test.inOrder(test.root);

        AVL test2 = new AVL(1, '1');
        test2.insert(3, '3');
        test2.insert(2, '2');
        test2.insert(8, '8');
        test2.insert(9, '9');
        test2.insert(6, '6');
        test2.insert(5, '5');
        System.out.println();
        test2.inOrder(test2.root);
        test2.delete(3);
        System.out.println();
        test2.inOrder(test2.root);
    }
}

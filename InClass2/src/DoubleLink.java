public class DoubleLink<E> {
    private static class Node<E>{
        private E element;
        private Node<E> next;
        private Node<E> prev;
        public Node(E e, Node<E> n, Node<E> p) {
            element = e;
            next = n;
            prev = p;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    public DoubleLink(){
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();
    }

    public void addFirst (E e){
        addBetween(e, head, head.getNext());
    }

    public void addLast (E e) {
        addBetween(e, tail.getPrev(), tail);
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(head.getNext());
    }

    public E removeLast() {
        if(isEmpty()) return null;
        return remove(tail.getPrev());
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> sucessor = node.getNext();
        predecessor.setNext(sucessor);
        sucessor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public static void main(String[] args){
        DoubleLink testList = new DoubleLink();
        System.out.println(""+testList.size +" " + testList.isEmpty());
        testList.addFirst(3);
        testList.addLast(5);
        testList.addLast(6);
        System.out.println(""+testList.getSize() +" " + testList.isEmpty() + " " + testList.last() + " " + testList.first());
        System.out.println(testList.removeFirst());
        System.out.println(testList.removeLast());
    }
}

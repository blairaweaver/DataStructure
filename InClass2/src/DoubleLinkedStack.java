public class DoubleLinkedStack<E> {
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
    public DoubleLinkedStack(){
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, head);
        head.setNext(tail);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

//    public E first() {
//        if (isEmpty()) return null;
//        return head.getNext().getElement();
//    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();
    }

//    public void addFirst (E e){
//        addBetween(e, head, head.getNext());
//    }

    public void push (E e) {
        addBetween(e, tail.getPrev(), tail);
    }

//    public E removeFirst() {
//        if (isEmpty()) return null;
//        return remove(head.getNext());
//    }

    public E pop() {
        if(isEmpty()) return null;
        return remove(tail.getPrev());
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, successor, predecessor);
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
        DoubleLinkedStack testList = new DoubleLinkedStack();
        System.out.println(""+testList.size +" " + testList.isEmpty());
        testList.push(5);
        testList.push(6);
//        System.out.println(""+testList.getSize() +" " + testList.isEmpty() + " " + testList.last());
//        System.out.println(testList.pop());
        while (!testList.isEmpty()){
            System.out.println(testList.pop());
        }
    }
}

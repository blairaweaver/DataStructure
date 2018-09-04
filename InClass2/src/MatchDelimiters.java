public class MatchDelimiters<E> {
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
    public MatchDelimiters(){
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, head);
        head.setNext(tail);
    }

    private int getSize() {
        return this.size;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

//    public E first() {
//        if (isEmpty()) return null;
//        return head.getNext().getElement();
//    }

    private E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();
    }

//    public void addFirst (E e){
//        addBetween(e, head, head.getNext());
//    }

    private void push (E e) {
        addBetween(e, tail.getPrev(), tail);
    }

//    public E removeFirst() {
//        if (isEmpty()) return null;
//        return remove(head.getNext());
//    }

    private E pop() {
        if(isEmpty()) return null;
        return remove(tail.getPrev());
    }

    public boolean match(E[] e){
        for (int i = 0; i < e.length; i++){
            if (e[i].equals("(")  || e[i].equals("[")  || e[i].equals("{")){
                push(e[i]);
            }
            else if (e[i].equals(")")  || e[i].equals("]")  || e[i].equals("}")){
                if (isEmpty()) return false;
                if (e[i].equals(")")){
                    if (!pop().equals("("))return false;
                }
                else if (e[i].equals("]")){
                    if (!pop().equals("["))return false;
                }
                else if (!pop().equals("{"))return false;
            }
        }
        if (isEmpty()) return true;
        else return false;
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
        MatchDelimiters testList = new MatchDelimiters();
        String[] test1 = new String[]{"(",")","(","(",")",")","{","(","[","(",")","]",")","}",};
        String[] test2 = new String[]{"(","{","[","]",")","}",};
        System.out.println(testList.match(test1));
        System.out.println(testList.match(test2));
    }
}

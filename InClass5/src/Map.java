import java.util.ArrayList;
import java.util.List;

public class Map<K, V> {
    private static class Node<K, V>{
        private K key;
        private V value;
        private Node<K, V> next;
        private Node<K, V> prev;
        public Node(K k, V v, Node<K, V> n, Node<K, V> p) {
            value = v;
            next = n;
            prev = p;
            key = k;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {return key;}

        public void setKey(K k) {key = k;}

        public void setValue(V v) {value = v;}

        public Node<K, V> getNext() {
            return next;
        }

        public Node<K, V> getPrev() {
            return prev;
        }

        public void setNext(Node<K, V> n) {
            this.next = n;
        }

        public void setPrev(Node<K, V> prev) {
            this.prev = prev;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail;
    private int size = 0;
    public Map(){
        head = new Node<>(null, null, null, null);
        tail = new Node<>(null, null, null, head);
        head.setNext(tail);
    }

//    size, isEmpty, get, put, remove, interable by key, value, and entry

    public int getSize() {return this.size;}

    public boolean isEmpty() {
        return this.size == 0;
    }

    public V get(K k) {
        Node<K, V> n = head;
        while (n.getNext() != tail) {
            n = n.getNext();
            if (n.getKey() == k) return n.getValue();
        }
        return null;
    }

    public V put(K k, V v) {
        Node<K, V> n = head;
        while (n.getNext() != tail) {
            n = n.getNext();
            if (n.getKey() == k) {
                V t = n.getValue();
                set(n, k, v);
                return t;
            }
        }
        add(k,v);
        return null;
    }

    public V remove(K k) {
        Node<K, V> n = head;
        while (n.getNext() != tail) {
            n = n.getNext();
            if (n.getKey() == k) {
                remove(n);
                return n.getValue();
            }
        }
        return null;
    }

    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<K>();
        Node<K, V> n = head;
        while (n.getNext() !=  tail) {
            n = n.getNext();
            keys.add(n.getKey());
        }
        return keys;
    }

    public Iterable<V> values() {
        List<V> values = new ArrayList<V>();
        Node<K, V> n = head;
        while (n.getNext() !=  tail) {
            n = n.getNext();
            values.add(n.getValue());
        }
        return values;
    }

    public Iterable<Node> entires() {
        List<Node> entries = new ArrayList<Node>();
        Node<K, V> n = head;
        while (n.getNext() !=  tail) {
            n = n.getNext();
            entries.add(n);
        }
        return entries;
    }

    private void set(Node p, K k, V v) {
        p.setKey(k);
        p.setValue(v);
    }

    private void remove(Node r) {
        Node p = r.getPrev();
        Node n = r.getNext();
        p.setNext(n);
        n.setPrev(p);
        size--;
    }

    private void add(K k , V e) {
        Node<K, V> newest = new Node<>(k, e, tail, tail.getPrev());
        tail.getPrev().setNext(newest);
        tail.setPrev(newest);
        size++;
    }

    public static void main(String[] args){
        Map testmap = new Map();
        System.out.println(testmap.isEmpty());
        System.out.println(testmap.put(5, 'A'));
        System.out.println(testmap.put(7, 'B'));
        System.out.println(testmap.put(2, 'C'));
        System.out.println(testmap.put(2, 'E'));
        System.out.println(testmap.put(8, 'D'));
        System.out.println(testmap.get(7));
        System.out.println(testmap.get(4));
        System.out.println(testmap.get(2));
        System.out.println(testmap.getSize());
        System.out.println(testmap.remove(2));
        System.out.println(testmap.remove(5));
        System.out.println(testmap.get(2));
        System.out.println(testmap.isEmpty());
    }
}


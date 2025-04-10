package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private int size;
    private final Node sentinel;

    private class Node{
        Node perv;
        Node next;
        T item;

        public Node(Node perv, Node next, T item) {
            this.perv = perv;
            this.next = next;
            this.item = item;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null,null,null);
        sentinel.perv = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        Node first = sentinel.next;
        Node newNode = new Node(sentinel,first,x);
        sentinel.next = newNode;
        first.perv = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node last = sentinel.perv;
        Node newNode = new Node(last,sentinel,x);
        sentinel.perv = newNode;
        last.next = newNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        // 只要没回到哨兵，就继续
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 0){
            return null;
        }
        Node newFirst = sentinel.next.next;
        sentinel.next = newFirst;
        newFirst.perv = sentinel;
        size--;
        return newFirst.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 0){
            return null;
        }
        Node newLast = sentinel.perv.perv;
        sentinel.perv = newLast;
        newLast.next = sentinel;
        size--;
        return newLast.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (index >= size) return null;
        Node n = sentinel.next;
        for (int i = 0; i <= index; i++) {
            n = n.next;
        }
        return n.item;
    }

    @Override
    public T getRecursive(int index) {
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (index >= size) return null;

        return getRecursive(0,index,sentinel.next);
    }

    public T getRecursive(int pos, int index, Node x) {
        if (pos == index){
            return x.item;
        }
        return getRecursive(pos+1,index,x.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{

        private Node tmp;

        public LinkedListDequeIterator() {
            tmp = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return tmp.next == null;
        }

        @Override
        public T next() {
            tmp = tmp.next;
            return tmp.item;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof LinkedListDeque61B<?>)){
            return false;
        }
        LinkedListDeque61B<?> other = (LinkedListDeque61B<?>) obj;
        if (this.size != other.size){
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = other.iterator();

        while (it1.hasNext() && it2.hasNext()){
            T e1 = it1.next();
            Object e2 = it2.next();

            if (e1 == null ? e2 != null : !e1.equals(e2)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T x:this){
            sb.append(x);
        }
        sb.append("]");
        return super.toString();
    }
}


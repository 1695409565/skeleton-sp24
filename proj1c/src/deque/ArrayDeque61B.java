package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int capacity;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = items.length;
        nextFirst = capacity - 1;
        nextLast = 0;
    }

    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = get(Math.floorMod(++nextFirst,this.capacity));
        }
        this.capacity = capacity;
        this.nextFirst = 0;
        this.nextLast = size + 1;
        items = temp;
    }

    @Override
    public void addFirst(T x) {
        if (size == capacity){
            resize(capacity * 2);
        }
        items[nextFirst] = x;
        size++;
        nextFirst = nextFirst == 0 ? capacity - 1 : nextFirst-1;
    }

    @Override
    public void addLast(T x) {
        if (size == capacity){
            resize(capacity * 2);
        }
        items[nextLast] = x;
        size++;
        nextLast = Math.floorMod(nextLast+1,capacity);
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.set(i, get(Math.floorMod(++nextFirst, this.capacity)));
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
        if (size == 0){
            return null;
        }
        nextFirst = Math.floorMod(nextFirst + 1,capacity);
        T temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (capacity >= 16 && size < capacity / 4){
            resize(capacity / 2);
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        nextLast = Math.floorMod(nextLast - 1,capacity);
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        if (capacity >= 16 && size < capacity / 4){
            resize(capacity / 2);
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size){
            return null;
        }
        return items[Math.floorMod(index + nextFirst + 1,capacity)];

    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque();
    }

    private class ArrayDeque implements Iterator<T>{
        int wizPos;

        public ArrayDeque() {
            wizPos = Math.floorMod(nextFirst+1,capacity);
        }

        @Override
        public boolean hasNext() {
            return wizPos < Math.floorMod(nextLast-1,capacity);
        }

        @Override
        public T next() {
            T temp = items[wizPos];
            wizPos = Math.floorMod(wizPos+1,capacity);
            return temp;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof ArrayDeque61B<?>)){
            return false;
        }
        ArrayDeque61B<?> other = (ArrayDeque61B<?>) obj;
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


import java.util.ArrayList;
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
        nextFirst = nextLast == 0 ? capacity - 1 : nextFirst-1;
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
}

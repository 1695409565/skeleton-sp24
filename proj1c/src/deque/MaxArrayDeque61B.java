package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{

    private Comparator<T> defaultComparator;

    public MaxArrayDeque61B(Comparator<T> c){
        super();
        this.defaultComparator = c;
    }

    public T max(){
        if (this.isEmpty()){
            return null;
        }
        return max(defaultComparator);
    }

    public T max(Comparator<T> c){
        if (this.isEmpty()){
            return null;
        }
        T maxItem = null;
        int i = 0;
        while (i < this.size() && (maxItem = this.get(i)) == null){
            i++;
        }
        if (maxItem == null){
            return null;
        }

        for (int j = i + 1 ; j < this.size(); j++) {
            T current = this.get(j);
            if (current == null){
                continue;
            }
            if (c.compare(current,maxItem) > 0)
                maxItem = current;
            }
        return maxItem;
    }
}

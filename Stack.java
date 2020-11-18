import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Stack<E> extends ArrayList<Integer> implements Iterable<Integer> {

    ArrayList<Integer> arrayList;

    public Stack() {
        arrayList = new ArrayList<>();
    }
    public void push(Integer integer) {
        arrayList.add(integer);
    }

    public boolean containsS(Integer integer) {
        return arrayList.contains(integer);
    }

    public Integer pop() {
        if(!arrayList.isEmpty()){
            Integer a = this.peek();
            arrayList.remove(arrayList.size() - 1);
            return a;
        }
        return null;
    }

    public void sortS() {
        Collections.sort(arrayList);
    }

    public void addAllS(Collection<? extends Integer> c) {
        for(Integer integer: c) {
            arrayList.add(integer);
        }
    }

    public Integer peek() {
        if(!arrayList.isEmpty()) {
            return arrayList.get(arrayList.size() - 1);
        }
        else {
            return null;
        }
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.size() == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return arrayList.iterator();
    }

}
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Queue<E> extends ArrayList<Integer> implements Iterable<Integer> {
    ArrayList<Integer> arrayList;


    public Queue() {
        arrayList = new ArrayList<>();
    }
    public void enqueue(Integer integer) {
        arrayList.add(integer);
    }

    @Override
    public Iterator<Integer> iterator() {
        return arrayList.iterator();
    }

    public void sortQ() {
        Collections.sort(arrayList);
    }

    public Integer poll() {
        if(!arrayList.isEmpty()){
            Integer a = this.element();
            arrayList.remove(0);
            return a;
        }
        else {
            return null;
        }
    }

    public void addAllQ(Collection<? extends Integer> c) {
        for(Integer integer: c) {
            arrayList.add(integer);
        }
    }

    public Integer element() {
        if(!arrayList.isEmpty()) {
            return arrayList.get(0);
        }
        else {
            return null;
        }
    }

    public int size() {
        return arrayList.size();
    }

    public boolean containsQ(Integer integer) {
        return arrayList.contains(integer);
    }

    public boolean isEmpty() {
        if(arrayList.size() == 0) {
            return true;
        }
        else{
            return false;
        }
    }

}

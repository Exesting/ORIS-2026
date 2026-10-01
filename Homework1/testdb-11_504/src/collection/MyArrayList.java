package collection;

import java.util.Iterator;

public class MyArrayList implements Iterable<Integer>{

    private final static int DEFAULT_MAX_SIZE = 10;

    private int elemets[];
    private int count;

    public MyArrayList() {
        this.elemets = new int[DEFAULT_MAX_SIZE];
        this.count= 0;
    }

    public void add(int element) {
        this.elemets[count] = element;
        this.count++;
    }

    private class MyArrayListIterator implements Iterator<Integer> {

        int currentIndex = 0;


        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public Integer next() {
            int value = elemets[currentIndex];
            currentIndex++;
            return value;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyArrayListIterator();
    }
}

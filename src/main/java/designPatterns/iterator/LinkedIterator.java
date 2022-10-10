package designPatterns.iterator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @Author: zhanmingwei
 */
public class LinkedIterator<E> implements Iterator<E> {

    private int cursor;
    private LinkedList<E> linkedList;

    LinkedIterator(LinkedList<E> linkedList) {
        this.linkedList = linkedList;
    }


    @Override
    public boolean hasNext() {
        return cursor < linkedList.size();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= linkedList.size()) {
            throw new NoSuchElementException();
        }
        return linkedList.get(cursor);
    }
}

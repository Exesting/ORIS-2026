package generics;

import org.w3c.dom.Node;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<C> implements List<C> {

    private Node<C> first;

    @Override
    public void add(C c) {

    }

    @Override
    public C get(int index) {
        return null;
    }

    private static class Node<D> {
        D value;
        Node<D> next;
    }

}

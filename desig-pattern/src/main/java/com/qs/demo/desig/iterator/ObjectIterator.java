package com.qs.demo.desig.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ObjectIterator implements Iterator {
    int index = -1;
    private List<Object> list;

    public ObjectIterator(List<Object> list) {
        this.list = list;
    }

    public ObjectIterator(Object... objects) {
        this.list = Arrays.asList(objects);
    }

    @Override
    public boolean hasNext() {
        if (index > list.size()) {
            return true;
        } else {
            index++;
            return false;
        }
    }

    @Override
    public Object next() {
        return list.get(index);
    }

    @Override
    public void remove() {
        list.remove(index);
    }
}

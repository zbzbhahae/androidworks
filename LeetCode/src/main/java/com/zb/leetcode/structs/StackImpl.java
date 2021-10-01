package com.zb.leetcode.structs;

import java.util.ArrayList;

public class StackImpl<E> {
    private ArrayList<E> data;

    public StackImpl() {
        data = new ArrayList<>();
    }

    public void push(E element) {
        data.add(element);
    }

    public E pop() {
        if(isEmpty())
            return null;
        return data.remove(data.size() - 1);
    }

    public E top() {
        return data.get(data.size() - 1);
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }


}

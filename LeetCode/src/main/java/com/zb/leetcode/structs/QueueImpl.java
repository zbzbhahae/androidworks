package com.zb.leetcode.structs;

import com.zb.leetcode.interfaces.QueueI;

import java.util.LinkedList;

public class QueueImpl<E> implements QueueI<E> {
    LinkedList<E> data;

    public QueueImpl() {
        data = new LinkedList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public void enQueue(E element) {
        data.add(element);
    }

    @Override
    public E deQueue() {
        if(data.size() == 0)
            return null;
        return data.removeFirst();
    }

    @Override
    public E front() {
        return data.peekFirst();
    }

    @Override
    public void clear() {
        data.clear();
    }
}

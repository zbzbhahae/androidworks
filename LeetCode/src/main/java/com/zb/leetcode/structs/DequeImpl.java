package com.zb.leetcode.structs;

import com.zb.leetcode.interfaces.DequeI;

import java.util.LinkedList;

public class DequeImpl<E> implements DequeI<E> {


    private LinkedList<E> data;

    public DequeImpl() {
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
    public void enQueueRear(E element) {
        data.addLast(element);
    }

    @Override
    public E deQueueRear() {
        if(isEmpty())
            return null;
        return data.pollLast();
    }

    @Override
    public void enQueueFront(E element) {
        data.addFirst(element);
    }

    @Override
    public E deQueueFront() {
        if(isEmpty())
            return null;
        return data.pollFirst();
    }

    @Override
    public E front() {
        if(isEmpty())
            return null;
        return data.pollFirst();
    }

    @Override
    public E tear() {
        if(isEmpty())
            return null;
        return data.pollLast();
    }
}

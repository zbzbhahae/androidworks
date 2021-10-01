package com.zb.leetcode.structs;

import com.zb.leetcode.interfaces.QueueI;

public class CircleQueueImpl<E> implements QueueI<E> {
    private int size;
    private int head;
//    private int tail;
    private E[] data;
    private static final int DEFAULT_CAPACITY = 3;

    public CircleQueueImpl() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = 0;
//        tail = -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enQueue(E element) {
        check();
        data[(size + head) % data.length] = element;
        size++;
    }

    @Override
    public E deQueue() {
        if(head == -1)
            return null;

        E element = data[head];
        data[head] = null;
        head = (head+1) % data.length;
        size--;
        return element;
    }

    @Override
    public E front() {
        if(size == 0)
            return null;
        return data[head];
    }

    @Override
    public void clear() {
        for(int i=0; i<data.length; i++) {
            data[i] = null;
        }
        size = 0;
        head = 0;
    }

    //检查容量
    void check() {
        if (size == data.length)
            grow();
    }
    //扩容
    void grow() {
        System.out.println("CircleQueueImpl 扩容了");
        E[] newData = (E[]) new Object[data.length * 2];
        for(int i=0; i<size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
    }
}

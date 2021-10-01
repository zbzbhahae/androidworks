package com.zb.leetcode.interfaces;

public interface QueueI<E> {

    int size();

    boolean isEmpty();

    void enQueue(E element);

    E deQueue();

    E front();

    void clear();
}

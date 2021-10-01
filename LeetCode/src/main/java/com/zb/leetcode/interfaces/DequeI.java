package com.zb.leetcode.interfaces;

public interface DequeI<E> {

    int size();

    boolean isEmpty();

    void enQueueRear(E element);//队尾插入

    E deQueueRear();//队尾取出

    void enQueueFront(E element);

    E deQueueFront();

    E front();

    E tear();
}

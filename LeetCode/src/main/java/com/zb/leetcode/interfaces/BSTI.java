package com.zb.leetcode.interfaces;

public interface BSTI<E> {

    int size();
    boolean isEmpty();
    void clear();
    void add(E element);
    void remove(E element);
    boolean contains(E element);
}

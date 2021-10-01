package com.zb.leetcode.structs;

import com.zb.leetcode.interfaces.QueueI;

import java.util.Stack;

public class StackQueue<E> implements QueueI<E> {

    private Stack<E> inStack;
    private Stack<E> outStack;

    public StackQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    private void popInStackToOutStack() {
        if(inStack.size() == 0 || outStack.size() != 0)
            return;
        while (inStack.size() > 0) {
            outStack.push(inStack.pop());
        }
    }


    @Override
    public int size() {
        return inStack.size() + outStack.size();
    }

    @Override
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    @Override
    public void enQueue(E element) {
        inStack.push(element);
    }

    @Override
    public E deQueue() {
        if(outStack.isEmpty()) {
            popInStackToOutStack();
        }
        if(outStack.isEmpty()) {
            return null;
        } else {
            return outStack.pop();
        }
    }

    @Override
    public E front() {
        if(!outStack.isEmpty())
            return outStack.peek();
        popInStackToOutStack();
        if(!outStack.isEmpty())
            return outStack.peek();
        return null;
    }

    @Override
    public void clear() {
        inStack.clear();
        outStack.clear();
    }
}

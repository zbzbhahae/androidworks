package com.zb.leetcode.leetcode;


import com.sun.jmx.remote.internal.ArrayQueue;
import com.zb.leetcode.utils.P;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 *
 * 注意：
 *
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 */
public class LC225 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        P.p("" + stack.top());
        P.p(stack.toString());
    }
}

class MyStack {

    private Queue<Integer> inQueue;
    private Queue<Integer> outQueue;
    private Queue<Integer> currentQueue;

    /** Initialize your data structure here. */
    public MyStack() {
        inQueue = new LinkedList<>();
        outQueue = new LinkedList<Integer>();
        currentQueue = inQueue;
    }

    /** Push element x onto stack. */
    public void push(int x) {
        currentQueue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(currentQueue == inQueue) {
            int size = inQueue.size() - 1;
            for(int i=0; i<size; i++) {
                outQueue.offer(inQueue.poll());
            }
            currentQueue = outQueue;
            return inQueue.poll();
        } else {
            int size = outQueue.size() - 1;
            for(int i=0; i < size; i++) {
                inQueue.offer(outQueue.poll());
            }
            currentQueue = inQueue;
            return outQueue.poll();
        }
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "inQueue=" + inQueue +
                ", outQueue=" + outQueue +
                ", currentQueue=" + currentQueue +
                '}';
    }

    /** Get the top element. */
    public int top() {
        if(currentQueue == inQueue) {
            int size = inQueue.size() - 1;
            for(int i=0; i < size; i++) {
                outQueue.offer(inQueue.poll());
            }
            currentQueue = outQueue;
            int element =  inQueue.poll();
            outQueue.offer(element);
            return element;
        } else {
            int size = outQueue.size() - 1;
            for(int i=0; i < size; i++) {
                inQueue.offer(outQueue.poll());
            }
            currentQueue = inQueue;
            int element =  outQueue.poll();
            inQueue.offer(element);
            return element;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return inQueue.isEmpty() && outQueue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

package com.zb.leetcode;

import com.zb.leetcode.structs.BinarySearchTree;
import com.zb.leetcode.structs.CircleQueueImpl;
import com.zb.leetcode.structs.DequeImpl;
import com.zb.leetcode.structs.QueueImpl;
import com.zb.leetcode.structs.StackImpl;
import com.zb.leetcode.structs.StackQueue;
import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;
import com.zb.leetcode.utils.printer.BinaryTrees;
import com.zb.leetcode.utils.printer.InorderPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;


public class Test {

    public static void main(String[] args) {
        TimeCheck.check("", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                testBinarySearchTree();
            }
        });

    }

    //方法栈溢出  每一个方法最后是栈桢结构体 方法的调用是栈桢
    // 的入栈出栈(方法栈)
    static void tt() {
        while (true)
            tt();
    }

    static void t1(int x) {
        int[] numbers = new int[10];
        int index = 0;
        int i = 0;
        while(true) {
            i = Math.abs(x % 10);
            x = x / 10;
            numbers[index] = i;
            index++;
            P.p(" " + i);
            if(x == 0)
                break;
            P.p(numbers + "");
        }
    }

    static void stack() {
        StackImpl<String> stack = new StackImpl<>();

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        P.p("stack->" + stack.pop());
        P.p("stack->" + stack.pop());
        P.p("stack->" + stack.pop());
        P.p("stack->" + stack.pop());
        P.p("stack->" + stack.pop());
        P.p("stack->" + stack.pop());
    }

    static void queue() {
        QueueImpl<String> queue = new QueueImpl<>();
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        queue.enQueue("e");
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
    }

    static void stackQueue() {
        StackQueue<String> queue = new StackQueue<>();
        queue.enQueue("a");
        queue.enQueue("b");
        P.p(queue.deQueue());
        queue.enQueue("c");
        queue.front();
        queue.enQueue("d");
        P.p(queue.deQueue());
        queue.enQueue("e");
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
    }

    static void Deque() {
        DequeImpl<String> deque = new DequeImpl();
        deque.enQueueFront("a");
        deque.enQueueFront("b");
        deque.enQueueFront("c");
        deque.enQueueRear("d");
        deque.enQueueRear("e");
        deque.enQueueRear("f");
        P.p(deque.deQueueFront());
        P.p(deque.deQueueFront());
        P.p(deque.deQueueFront());
        P.p(deque.deQueueRear());
        P.p(deque.deQueueRear());
        P.p(deque.deQueueRear());

    }

    static void circleQueue() {
        CircleQueueImpl queue = new CircleQueueImpl();
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");
        P.p(queue.deQueue());
        queue.enQueue("11");
        P.p(queue.deQueue());
        queue.enQueue("12");
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());
        P.p(queue.deQueue());


    }

    static void testBinarySearchTree() {
        Integer[] data = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3
        };
        data = new Integer[] {
                7, 4, 9, 2, 5
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i=0; i<data.length; i++) {
            bst.add(data[i]);
        }
//        for (int i=0; i<100; i++) {
//            bst.add((int) (Math.random() * 100));
//        }

//        P.p("" + bst.contains(13));
//        bst.remove(7);

        P.line();
        BinaryTrees.println(bst);
        P.line();
        P.p(bst);
        P.line();
        P.p(bst.height() + "");
        P.p("是完全二叉树吗:" + bst.isCompleteTree());
//        bst.traversalLevelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                P.p("____" + element + "_____");
//                if(element == 2)
//                    return true;
//                return false;
//            }
//        });
    }


}

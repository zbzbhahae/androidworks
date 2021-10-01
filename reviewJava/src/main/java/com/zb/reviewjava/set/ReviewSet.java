package com.zb.reviewjava.set;

import com.zb.reviewjava.P;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class ReviewSet {

    /**
     * TreeSet简介
     * TreeSet 是一个有序的集合，它的作用是提供有序的Set集合。它继承于AbstractSet抽象类，实现了NavigableSet<E>, Cloneable, java.io.Serializable接口。
     * TreeSet 继承于AbstractSet，所以它是一个Set集合，具有Set的属性和方法。
     * TreeSet 实现了NavigableSet接口，意味着它支持一系列的导航方法。比如查找与指定目标最匹配项。
     * TreeSet 实现了Cloneable接口，意味着它能被克隆。
     * TreeSet 实现了java.io.Serializable接口，意味着它支持序列化。
     * TreeSet是基于TreeMap实现的。TreeSet中的元素支持2种排序方式：自然排序 或者 根据创建TreeSet 时提供的 Comparator 进行排序。这取决于使用的构造方法。
     * TreeSet为基本操作（add、remove 和 contains）提供受保证的 log(n) 时间开销。
     * 另外，TreeSet是非同步的。 它的iterator 方法返回的迭代器是fail-fast的。
     * https://blog.csdn.net/a1439775520/article/details/95373610
     */
    private TreeSet<Object> treeSet ;
    //无序 内部为hashmap
    private HashSet<Object> hashSet = new HashSet<>();
    //保持有序
    private LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();

    public void doSort() {
        /**
         * treeset 是加入自动排序，基本类型会自动排序，
         * 如果想实现对象排序，需要对象类实现Comparable接口或者
         * 创建treeset时实用带Comparator构造方法。
         */
        treeSet = new TreeSet<>(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        treeSet.add("a");
        treeSet.add("c");
        treeSet.add("e");
        treeSet.add("b");
        treeSet.add("d");

        //会使用自带的自然排序为 a b c d e  非线程安全
        hashSet.add("1");
        //hashSet没有get方法
        //linkedHashSet也没有get方法 但内部保持有序，遍历顺序与插入顺序一致

    }

    public void doTraversal() {
        //不推荐for eatch遍历 需要转化为数组
        for (Object a:treeSet.toArray()) {
            P.p(a.toString());
        }

        Iterator iterator = treeSet.iterator();
        iterator = treeSet.descendingIterator();//逆序遍历
        while (iterator.hasNext()) {
            iterator.next();
        }
    }
}

package com.zb.reviewjava.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

//ConcurrentLinkedQueue应该算是在高并发环境中性能最好的队列，没有之一。
public class ReviewList {

    //线程不安全，查询速度快 已替代Vector
    private ArrayList<Object> arrayList = new ArrayList<>();
    //链表结果，增删速度快
    private LinkedList<Object> linkedList = new LinkedList<>();

    public void sortList() {
        //1 实现排序接口进行排序
        arrayList.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        //2 list中的元素需要实现Comparable接口进行排序
//        Collections.sort(arrayList);

        //实用排序类实现排序接口来进行排序
        ListComparator listComparator = new ListComparator();
        Collections.sort(arrayList, listComparator);

    }

    class ListComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    }

    //list的遍历
    public void listTraversal() {
        //1.foreach 不常用
        arrayList.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
            }
        });
        //2.for循环
        for(int i = 0; i<arrayList.size(); i++) {
            Object o = arrayList.get(i);
        }
        for (int i = 0; i<linkedList.size(); i++) {
            linkedList.get(i);
        }
        //3.Iterator   list可以获得listIterator 可以进行添加修改等操作
        Iterator<Object> iterator = arrayList.iterator();
        while(iterator.hasNext()) {
            Object o = iterator.next();
        }
    }
}

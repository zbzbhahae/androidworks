package com.zb.reviewjava.set;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * hashMap treeMap  linkedHashMap与hashSet treeSet linkedHashSet类似
 */
public class ReviewMap {

    private HashMap<Object, Object> hashMap = new HashMap();
    private LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
    private TreeMap<Object, Object> treeMap;

    public void doSort() {
        //与treeSet相似 构造可以设置排序条件
        treeMap = new TreeMap<>(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });

    }

    public void doTarversal() {
        for(Object key : treeMap.keySet()) {
            treeMap.get(key);
        }
        for(Map.Entry<Object, Object> entry : treeMap.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        Iterator<Map.Entry<Object, Object>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }
    }

}

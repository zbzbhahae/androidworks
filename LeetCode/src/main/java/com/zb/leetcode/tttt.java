package com.zb.leetcode;


import com.zb.leetcode.structs.BinarySearchTree;
import com.zb.leetcode.utils.P;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;


public class tttt {

    private static volatile tttt singletton;
    public static void main(String[] args) {
//        P.p("" + Math.multiplyExact(2, 16));
//        short s1 = 1;
//        s1 = s1;
//        s1 += 1;
//        String str = "1434324324321";
//        P.p(str.hashCode());

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(7);
        tree.add(5);
        tree.add(9);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(10);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(11);
        tree.traversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                P.p(", " + element);
                return false;
            }
        });

    }
}

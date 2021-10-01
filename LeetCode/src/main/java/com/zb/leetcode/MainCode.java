package com.zb.leetcode;

import com.zb.leetcode.leetcode.TwoSums;
import com.zb.leetcode.utils.P;

import java.util.Arrays;

public class MainCode {

    public static void main(String args[]) {

        P.p("阿");
        P.line();
        P.line();
        Feibonaqi.code();
        int[] nums = {1, 3, 4, 5, 6, 7, 8, 9, 32, 11, 12};


        P.p("" + Arrays.toString(TwoSums.sum(nums, 9)));
    }
}
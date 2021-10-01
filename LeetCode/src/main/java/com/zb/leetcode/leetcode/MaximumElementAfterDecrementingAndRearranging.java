package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

import java.util.Arrays;

public class MaximumElementAfterDecrementingAndRearranging {

    public static void main(String args[]) {
        int[] arr = new int[100000];
        for(int i=0; i<arr.length; i++) {
            arr[i] = 1;
        }
        long start = System.currentTimeMillis();
        int result = maximumElementAfterDecrementingAndRearranging(arr);
        long stop = System.currentTimeMillis();
        P.p("Time used :" + (stop - start) + "  result " + result);

    }

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        if(null == arr || arr.length == 0)
            return 0;
        long start = System.currentTimeMillis();
        Arrays.sort(arr);
//        for(int i=0; i<arr.length - 1; i++) {
//            for(int j=i+1; j<arr.length; j++) {
//                if(arr[i] > arr[j]) {
//                    int tem = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = tem;
//                }
//            }
//        }
        long stop = System.currentTimeMillis();
        P.p("sort time used->" + (stop - start));
        arr[0] = 1;
        for(int i=0; i<arr.length - 1; i++) {
            if((arr[i+1] - arr[i])>1)
                arr[i+1] = arr[i] + 1;
        }
        return arr[arr.length - 1];
    }
}

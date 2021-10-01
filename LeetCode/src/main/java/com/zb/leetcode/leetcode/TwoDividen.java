package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class TwoDividen {

    public static void main(String args[]) {
        divide(100, 30);
    }

    public static int divide(int dividend, int divisor) {
        int n=1;
        while (true) {
            int tem = dividend >> n;
            if(tem < divisor)
                break;
            n++;
        }
        n--;
//        int left = dividend - (divisor << n);
//        int m = 0;
//        while (true) {
//
//            if((dividend >> m) < (divisor >> (m+1))) {
//                break;
//            }
//        }

        P.p(" n---m " + (1<<n) + "==" );
        return 0;
    }
}

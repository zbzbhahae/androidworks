package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/implement-strstr
 *
 *
 */
public class FindStringInString {

    public static void main(String args[]) {
        TimeCheck.check("str", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                P.p("length-> " + strStr("mississigbfsdgfsdgfsdgfsdgfsdgfsdgppi", "issigfsdgdsgdp"));
            }
        });

    }

    public static int strStr(String haystack, String needle) {
        if(null == needle || needle.length() == 0)
            return 0;
        if(null == haystack || haystack.length() == 0)
            return -1;
        int lengthNeedle = needle.length();
        int lengthHaystack = haystack.length();
        char[] str1 = haystack.toCharArray();
        char[] str2 = needle.toCharArray();
        boolean found = false;
        for(int i=0; i<(lengthHaystack - lengthNeedle + 1); i++) {
            if(str1[i] == str2[0] && (lengthHaystack - i) >= lengthNeedle) {
                found = true;
                for(int j=1; j<lengthNeedle; j++) {
                    if(str1[i+j] != str2[j]) {
                        found = false;
                    }
                }
            }
            if(found)
                return i;
        }
        return -1;

    }
}

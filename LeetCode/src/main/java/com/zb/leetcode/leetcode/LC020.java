package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC020 {

    public static void main(String[] args) {
        TimeCheck.check("LC020", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                P.p("" + isValid("[()[][][]{}]"));
            }
        });

    }

    static boolean isValid(String s) {
        if(null == s || s.length() == 0 || s.length()%2 != 0)
            return false;
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for(int i=0; i<length; i++) {
            char c = s.charAt(i);
            if(isLeft(c)) {
                stack.push(c);
            } else if(stack.isEmpty()) {
                return false;
            } else if(rightWithLeft(stack.peek(), c)){
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    static boolean isLeft(char c) {
        switch (c) {
            case '(':
            case '[':
            case '{':
                return true;
            default:
                return false;
        }
    }

    static boolean rightWithLeft(char left, char right) {
        switch (left) {
            case '(':
                return right == ')';
            case '[':
                return right == ']';
            case '{':
                return right == '}';
        }
        return false;
    }


    /**
     * 答案
     */
    static boolean answer(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }

    /**
     * 效率低
     * @param s
     * @return
     */
    static boolean answer2(String s) {
        if(null == s || s.length() == 0 || s.length()%2 != 0)
            return false;
        while(s.contains("{}") || s.contains("[]")
                || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }
}

package com.zb.leetcode.leetcode;

import com.zb.leetcode.utils.P;
import com.zb.leetcode.utils.TimeCheck;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LC03 {

    public static void main(String[] args) {
        String s = "aaghuisdhgidshgihdfighdfiughidfshgidfhgiudfhsgifhdighdfisg" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "hdfisfdavn74308avn74089r37awv04nr7360wa89v6r07a0v8hn7308w9an7vrf0" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "1234567890qwertyuiopasdfghjkl;zxcvbnmvt648w397vtn9487wnv3t0987w340v" +
                "ghiub";
        char[] seq = s.toCharArray();
        int maxLength = 0;
        Set set = new Set(seq.length);
        for(char c : seq) {
            boolean in = set.add(c);
            if(!in) {
                int tem = set.getLength();
                if(tem-1 > maxLength)
                    maxLength = tem-1;
                set.removeAndBeforeFirst(c);
            }

        }
        if(maxLength == 0 || maxLength<set.getLength())
            maxLength = set.getLength();
        P.p(" " + maxLength);
        char[] dd = new char[10];
        P.p(" " + dd.toString());

        TimeCheck.check("my solustion", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                char[] seq = s.toCharArray();
                int maxLength = 0;
                Set set = new Set(seq.length);
                for(char c : seq) {
                    boolean in = set.add(c);
                    if(!in) {
                        int tem = set.getLength();
                        if(tem-1 > maxLength)
                            maxLength = tem-1;
                        set.removeAndBeforeFirst(c);
                    }

                }
                if(maxLength == 0 || maxLength<set.getLength())
                    maxLength = set.getLength();
            }
        });
        TimeCheck.check("answer", new TimeCheck.CheckCallback() {
            @Override
            public void callFun() {
                P.p("" + answer(s));
            }
        });
    }

    static class Set {
        private char[] data;
        private int index = 0;
        public Set(int length) {
            data = new char[length];
        }

        public boolean add(char c) {
            for(char ch : data) {
                if(ch == c) {
                    data[index] = c;
                    index++;
                    return false;
                }
            }
            data[index] = c;
            index++;
            return true;
        }

        public void removeFirst() {
            for(int i=0; i<data.length; i++) {
                if(data[i] != '\u0000')
                    data[i] = '\u0000';
            }
        }

        public void removeAndBeforeFirst(char c) {
            for(int i=0; i<data.length; i++) {
                if(data[i] == c) {
                    data[i] = '\u0000';
                    return;
                }
                data[i] = '\u0000';
            }
        }

        public int getLength() {
            int count = 0;
            for(int i=0; i<data.length; i++) {
                if(data[i] != '\u0000') {
                    count++;
                }
            }
            return count;
        }
    }


    static int answer(String s) {
        if(0 == s.length())
            return 0;
        if(1 == s.length())
            return 1;

        //通过int数组来保存字符和字符出现位置信息，index是字符，内容是最近出现的位置下标
        int[] chars = new int[128];
        int start = 0;//指向一段字符的起始位置
        int length = 0;

        for(int i=0; i<s.length(); i++) {
            //获得字符信息
            int charInInt = s.charAt(i);
            //读取上次该字符出现位置
            int lastPosition = chars[charInInt];
            //与start比较，如果小于start，说明上次出现位置不在计算范围内
            //如果大于等于start， 说明start到i之间该字符重复出现，start需要定位到上次该
            //字符出现位置之后
            // start = Math.max(start, lastPosition + 1);
            //将+1逻辑写到更新下标中
            start = Math.max(start, lastPosition);
            //这样能保证start与i之间没有重复的字符
            //计算不重复长度
            length = Math.max(length, i - start + 1);
            // 然后更新该字符位置信息  将下标+1  这样字符位置是从1开始，防止
            // 之前初始化的0带 来影响
            chars[charInInt] = i + 1;
        }
        return length;
    }
}

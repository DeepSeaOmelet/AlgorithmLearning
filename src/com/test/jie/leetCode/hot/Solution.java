package com.test.jie.leetCode.hot;

import com.test.jie.leetCode.tool.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    HashMap、TreeMap，TreeSet，PriorityQueue，Deque
    数据结构：数组、链表、栈、队列、堆、二叉树、图、哈希表、并查集
    ● 算法思想 ： 递归、动态规划、二分查找、贪心、分治、回溯、DFS、BFS、KMP、树的广度和深度优先搜索、
    ● 数学： 位运算、质数、排列组合
    1. 两数之和     https://leetcode.cn/problems/two-sum/
    2. 两数相加     https://leetcode.cn/problems/add-two-numbers/
    3. 无重复字符的最长子串       https://leetcode.cn/problems/longest-substring-without-repeating-characters/
    4. 寻找两个正序数组的中位数     https://leetcode.cn/problems/median-of-two-sorted-arrays/
    5. 最长回文子串       https://leetcode.cn/problems/longest-palindromic-substring/

 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    //5. 最长回文子串
    //回文 正反读都是一样
    public String longestPalindrome(String s) {
        //dp
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //dp[i][j] 表示 s[s..j] 是否是回文串
        boolean[][] dp = new boolean[n][n];
        //初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] cs = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= n; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < n; i++) {
                //由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= n) {
                    break;
                }
                if (cs[i] != cs[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }
        return s.substring(begin, begin + maxLen);

        //暴力破解(n^3)，不行
//        String ans = "";
//        int max = 0;
//        int n = s.length();
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j <= n; j++) {
//                String sub = s.substring(i, j);
//                if (isPalindrome(sub)){
//                    if (sub.length()>max){
//                        max=sub.length();
//                        ans=sub;
//                    }
//                }
//            }
//        }
//        return ans;
    }

    public boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    //寻找两个正序数组的中位数【困难】  解法1
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        //合并再找
        int n1 = nums1.length;
        int n2 = nums2.length;
        //合并
        int[] nums = new int[n1 + n2];
        for (int i = 0; i < nums.length; i++) {
            if (i < n1) {
                nums[i] = nums1[i];
            } else {
                nums[i] = nums2[i - n1];
            }
        }
        Arrays.sort(nums);
        int size = nums.length - 1;
        if (size % 2 == 1) {
            return (double) (nums[size / 2] + nums[size / 2 + 1]) / 2;
        } else {
            return nums[size / 2];
        }
    }

    //无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        //计数+滑动窗口
        if (s.length() < 2) {
            return s.length();
        }
        char[] cs = s.toCharArray();
        int[] idx = new int[1024];
        Arrays.fill(idx, -1);
        int ans = 0;
        for (int i = 0, j = 0; i < cs.length; i++) {
            if (idx[cs[i]] != -1 && idx[cs[i]] >= j) {
                j = idx[cs[i]] + 1;
            }
            ans = Math.max(ans, i - j + 1);
            idx[cs[i]] = i;
        }
        return ans;
    }

    //两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //链表遍历
        ListNode head = new ListNode();
        ListNode tail = head;
        int up = 0;
        while (l1 != null || l2 != null) {
            int num = tail.val;
            if (l1 != null) {
                num += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num += l2.val;
                l2 = l2.next;
            }
            up = num / 10;
            tail.val = num % 10;
            if (l1 != null || l2 != null || up != 0) {
                tail.next = new ListNode(up);
                tail = tail.next;
            }
        }
        return head;
    }

    //两数之和
    public int[] twoSum(int[] nums, int target) {
        //哈希表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
}

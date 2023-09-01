package com.test.jie.leetCode.Daily.Y2023;

import com.test.jie.leetCode.tool.ListNode;

import java.util.Stack;

public class mon5 {
    //92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        head = dummyNode;
        int count = 1;
        while (count < left) {
            head = head.next;
            count++;
        }
        ListNode nextNode = head.next;
        while (count < right) {
            ListNode cur = nextNode.next;
            nextNode.next = cur.next;
            cur.next = head.next;
            head.next = cur;
            count++;
        }
        return dummyNode.next;
    }

    //92. 反转链表 II
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> s = new Stack<>();
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        int count = 1;
        while (count < left) {
            cur = head;
            head = head.next;
            count++;
        }
        System.out.println(count);
        while (count <= right) {
            s.push(head);
            head = head.next;
            count++;
        }
        while (!s.isEmpty()) {
            cur.next = s.pop();
            cur = cur.next;
        }
        cur.next = head;
        return dummyHead.next;
    }

    /**
     * 2451. 差值数组不同的字符串
     * 相关企业
     * 给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
     * 每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
     * 比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
     * words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
     * 请你返回 words中 差值整数数组 不同的字符串。
     * <p>
     * 示例 1：
     * 输入：words = ["adc","wzy","abc"]
     * 输出："abc"
     * 解释：
     * - "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
     * - "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
     * - "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
     * 不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
     * 示例 2：
     * 输入：words = ["aaa","bob","ccc","ddd"]
     * 输出："bob"
     * 解释：除了 "bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0] 。
     * <p>
     * 提示：
     * 3 <= words.length <= 100
     * n == words[i].length
     * 2 <= n <= 20
     * words[i] 只含有小写英文字母。
     *
     * @param words
     * @return
     */
    public String oddString(String[] words) {
        int len = words.length;
        int n = words[0].length() - 1;
        int[] difference = new int[n];
        for (int i = 0, j = 0; i < n; i++, j++) {
            difference[i] = words[0].charAt(j + 1) - words[0].charAt(j);
        }
        int count = 1;
        int idx = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < n; j++) {
                if (difference[j] != (words[i].charAt(j + 1) - words[i].charAt(j))) {
                    count--;
                    idx = i;
                    break;
                }
            }
        }
        return count >= 0 ? words[idx] : words[0];
    }

    public static void main(String[] args) {
        int x = 0xFEDC;
        System.out.println(x);
        System.out.println(x>>>7);
        System.out.println(x>>7);
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x>>7));
        System.out.println(Integer.toHexString(x>>7));
        System.out.println(Integer.toBinaryString(x>>>7));


    }
}


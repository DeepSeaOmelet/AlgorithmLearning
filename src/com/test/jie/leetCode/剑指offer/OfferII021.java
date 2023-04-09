package com.test.jie.leetCode.剑指offer;

import com.test.jie.leetCode.tool.ListNode;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class OfferII021 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head.next == null) {
            return null;
        }
        ListNode l = head;
        ListNode r = head;
        int cnt = 0;
        while (r.next != null && cnt++ < n) {
            r = r.next;
        }
        while (r.next != null) {
            l = l.next;
            r = r.next;
        }
        if (cnt<n){
            return head.next;
        }else {
            l.next = l.next.next;
        }
        return head;
    }


    public static void main(String[] args) {

    }
}

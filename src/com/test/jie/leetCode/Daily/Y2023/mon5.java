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
}


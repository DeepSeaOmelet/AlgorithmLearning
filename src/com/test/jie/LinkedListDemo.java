package com.test.jie;

import com.test.jie.leetCode.tool.ListNode;
import com.test.jie.leetCode.tool.Node;

import java.util.*;

public class LinkedListDemo {
    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 示例 1：
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * 示例 2：
     * 输入：head = [], val = 1
     * 输出：[]
     * 示例 3：
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     * 提示：
     * 列表中的节点数目在范围 [0, 104] 内
     * 1 <= Node.val <= 50
     * 0 <= val <= 50
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    /**
     * 707. 设计链表
     * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
     * val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
     * 在链表类中实现这些功能：
     * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
     * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
     * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
     * 示例：
     * MyLinkedList linkedList = new MyLinkedList();
     * linkedList.addAtHead(1);
     * linkedList.addAtTail(3);
     * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
     * linkedList.get(1);            //返回2
     * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
     * linkedList.get(1);            //返回3
     * 提示：
     * 0 <= index, val <= 1000
     * 请不要使用内置的 LinkedList 库。
     * get, addAtHead, addAtTail, addAtIndex 和 deleteAtIndex 的操作次数不超过 2000。
     */
    class MyLinkedList {
        class ChainList {
            int val;
            ChainList next;
            ChainList prev;

            public ChainList(int _val) {
                val = _val;
            }
        }

        ChainList head = new ChainList(-1);
        ChainList tail = new ChainList(-1);
        int size = 0;

        public MyLinkedList() {
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
         *
         * @param index
         * @return
         */
        public int get(int index) {
            if (size <= index) {
                return -1;
            }
            ChainList node = this.head;
            while (index-- >= 0) {
                node = node.next;
            }
            return node.val;
        }

        /**
         * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         *
         * @param val
         */
        public void addAtHead(int val) {
            ChainList node = new ChainList(val);
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        /**
         * 将值为 val 的节点追加到链表的最后一个元素。
         *
         * @param val
         */
        public void addAtTail(int val) {
            ChainList node = new ChainList(val);
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        /**
         * 在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
         *
         * @param index
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if (index > size) return;
            if (index <= 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else {
                ChainList cur = this.head;
                ChainList node = new ChainList(val);
                while (index-- >= 0) {
                    cur = cur.next;
                }
                node.next = cur;
                node.prev = cur.prev;
                cur.prev.next = node;
                cur.prev = node;
                size++;
            }
        }

        /**
         * 如果索引 index 有效，则删除链表中的第 index 个节点。
         *
         * @param index
         */
        public void deleteAtIndex(int index) {
            if (index >= size) return;
            ChainList cur = this.head;
            while (index-- >= 0) {
                cur = cur.next;
            }
            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
            size--;
        }
    }

    /**
     * 206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 示例 1：
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：[2,1]
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     * 提示：
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        //注意第一个head的next没有改变，需要修改
        head.next = null;
        return tail;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 24. 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     * 提示：
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head.next;
        ListNode pre = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            if (next != null) {
                head.next = next.next;
                next.next = head;
                pre.next = next;
            }
            pre = head;
            head = head.next;
        }
        return dummyHead.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
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
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     * 你能尝试使用一趟扫描实现吗？
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = new ListNode(-1);
        slow.next = head;
        head = slow;
        ListNode quick = slow;
        while (n-- >= 0 && quick != null) {
            quick = quick.next;
        }
        while (quick != null) {
            slow = slow.next;
            quick = quick.next;
        }
        //此时slow指向第n个结点的前一个
        slow.next = slow.next.next;
        return head.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        lastN = n;
        return removeNthFromEndDFS(head);
    }

    int lastN;

    public ListNode removeNthFromEndDFS(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = removeNthFromEndDFS(head.next);
        lastN--;
        if (lastN == 0) {
            return cur;
        }
        head.next = cur;
        return head;
    }

    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     * 提示：
     * 链表中节点的数目范围是 [0, 10^4]
     * -10^5 <= Node.val <= 10^5
     * pos 为 -1 或者链表中的一个 有效索引 。
     * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        //能出来必定没有循环
        return false;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
     * * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：返回索引为 0 的链表节点
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：返回 null
     * 解释：链表中没有环。
     * 提示：
     * 链表中节点的数目范围在范围 [0, 104] 内
     * -105 <= Node.val <= 105
     * pos 的值为 -1 或者链表中的一个有效索引
     * <p>
     * <p>
     * 进阶：你是否可以使用 O(1) 空间解决此题？
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null;
    }

    /**
     * 面试题 02.07. 链表相交
     * 简单
     * 290
     * 相关企业
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * 图示两个链表在节点 c1 开始相交：
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     * 示例 1：
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * 示例 2：
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Intersected at '2'
     * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     * 示例 3：
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
     * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 这两个链表不相交，因此返回 null 。
     * 提示：
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 0 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        //使lenA>lenB
        if (lenA < lenB) {
            int temp = lenA;
            lenA = lenB;
            lenB = temp;
            ListNode t = curA;
            curA = curB;
            curB = t;
        }
        int d = lenA - lenB;
        while (d-- > 0) {
            curA = curA.next;
        }
        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}


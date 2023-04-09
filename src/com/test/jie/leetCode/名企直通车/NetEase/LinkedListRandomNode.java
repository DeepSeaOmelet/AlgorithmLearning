package com.test.jie.leetCode.名企直通车.NetEase;

import com.test.jie.leetCode.tool.ListNode;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * * 382. 链表随机节点
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 * 示例：
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 * <p>
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 * <p>
 * 提示：
 * 链表中的节点数在范围 [1, 10^4] 内
 * -104 <= Node.val <= 10^4
 * 至多调用 getRandom 方法 10^4 次
 * <p>
 * 进阶：
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 */
public class LinkedListRandomNode {
    public static void main(String[] args) {

    }

}

class Solution {
    private List<Integer> list = new ArrayList<>();
    private Integer cnt = 0;
    private Random ran = new Random();

    public Solution(ListNode head) {
        while (head != null) {
            list.add(head.val);
            cnt++;
            head = head.next;
        }
    }

    public int getRandom() {
        return list.get(ran.nextInt(cnt));
    }
}

class Solution2 {
    //蓄水池
    private ListNode head;
    private Random ran = new Random(20220906);

    public Solution2(ListNode _head) {
        head = _head;
    }

    public int getRandom() {
        int ans = 0;
        int idx = 0;
        ListNode node = head;
        while (node != null && ++idx >= 0) {
            if (ran.nextInt(idx) == 0) ans = node.val;
            node=node.next;
        }
        return ans;
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.Random;

/**
 * 2022/07/26
 * 1206. 设计跳表
 * 不使用任何库函数，设计一个 跳表 。
 * <p>
 * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。
 * 跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * <p>
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * <p>
 * 示例 1:
 * 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 * <p>
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * <p>
 * 提示:
 * 0 <= num, target <= 2 * 104
 * 调用search, add,  erase操作次数不大于 5 * 104
 */
public class DesignSkiplist {
    public static void main(String[] args) {

    }
}

class Skiplist {
    int level = 10;

    class Node {
        int val;
        Node[] ne = new Node[level];

        public Node(int _val) {
            this.val = _val;
        }
    }

    Random ran = new Random();
    //头指针
    Node he = new Node(-1);

    public Skiplist() {

    }

    //查找出每一层比 t 严格小的最后一个节点，将其存成 ns 数组。即 ns[i] 为 level = i层严格比 t 小的最后一个节点。
    void find(int t, Node[] ns) {
        Node cur = he;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.ne[i] != null && cur.ne[i].val < t) {
                cur = cur.ne[i];
            }
            ns[i] = cur;
        }
    }

    public boolean search(int target) {
        Node[] ns = new Node[level];
        find(target, ns);
        return ns[0].ne[0] != null && ns[0].ne[0].val == target;
    }

    public void add(int num) {
        Node[] ns = new Node[level];
        find(num, ns);
        Node node = new Node(num);
        for (int i = 0; i < level; i++) {
            //链表插入
            node.ne[i] = ns[i].ne[i];
            ns[i].ne[i] = node;
            if (ran.nextInt(2) == 0) break;
        }
    }

    public boolean erase(int num) {
        Node[] ns = new Node[level];
        find(num, ns);
        Node cur = ns[0].ne[0];
        if (cur == null || cur.val != num) {
            return false;
        }
        for (int i = 0; i < level && ns[i].ne[i] == cur; i++) {
            //逐级删除链表
            ns[i].ne[i] = ns[i].ne[i].ne[i];
        }
        return true;
    }
}
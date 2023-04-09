package com.test.jie.leetCode.Daily.Y2022.mon4;

import com.test.jie.leetCode.tool.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * N 叉树的层序遍历
 * <p>
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * 提示：
 * 树的高度不会超过1000
 * 树的节点总数在 [0,10^4] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        NAryTreeLevelOrderTraversal traversal = new NAryTreeLevelOrderTraversal();
    }

    public List<List<Integer>> levelOrder(Node root) {
        ArrayDeque<Node> deque = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            deque.addLast(root);
        }
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            while (size > 0) {
                Node pollNode = deque.poll();
                list.add(pollNode.val);
                //添加到deque底部
                for (Node n:pollNode.children){
                    deque.addLast(n);
                }
                size--;
            }
            ans.add(list);
        }
        return ans;
    }
}
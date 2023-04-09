package com.test.jie.leetCode.Daily.Y2022.mon8;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2022/08/17
 * 1302. 层数最深叶子节点的和
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 示例 2：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 * <p>
 * 提示：
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {
    public static void main(String[] args) {

    }

    public int deepestLeavesSum(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int ans = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            boolean isDeepest = true;
            while (size-- > 0) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.addLast(node.left);
                    isDeepest = false;
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                    isDeepest = false;
                }
                if (isDeepest){
                    ans += node.val;
                }
            }
            if (!isDeepest) {
                ans = 0;
            }
        }
        return ans;
    }
}
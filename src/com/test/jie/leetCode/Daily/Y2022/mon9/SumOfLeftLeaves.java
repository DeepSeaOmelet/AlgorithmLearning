package com.test.jie.leetCode.Daily.Y2022.mon9;

import com.test.jie.leetCode.tool.TreeNode;

/**
 * https://leetcode.cn/problems/sum-of-left-leaves/
 * * https://www.bilibili.com/video/BV1GY4y1K7z8
 * 2022/09/13
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 * <p>
 * 示例 1：
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 * 输入: root = [1]
 * 输出: 0
 * <p>
 * 提示:
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 */
public class SumOfLeftLeaves {
    public static void main(String[] args) {

    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        TreeNode left = root.left;
        if (left != null && left.left == null && left.right == null) {
            sum = root.left.val;
        }
        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

//    int sum = 0;
//
//    public int sumOfLeftLeaves(TreeNode root) {
//        dfs(root, false);
//        return sum;
//    }
//
//    void dfs(TreeNode root, boolean isLeft) {
//        if (root == null) return;
//        if (root.left==null && root.right==null && isLeft){
//            sum+= root.val;
//        }
//        dfs(root.left,true);
//        dfs(root.right,false);
//    }
}
package com.test.jie.leetCode.Daily.Y2022.mon9;

import com.test.jie.leetCode.tool.TreeNode;

/**
 * 2022/09/02
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * <p>
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 * 提示:
 * 树的节点数的范围是 [0, 104]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 * 通过次数70,908提交次数150,535
 */
public class LongestUnivaluePath {
    public static void main(String[] args) {

    }

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;//最长的一边
        int cur = 0;//以当前root为中间，左右两子节点的长度
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (root.left != null && root.left.val == root.val) {
            ans = left + 1;
            cur = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            ans = Math.max(right + 1, ans);
            cur += right + 1;
        }
        max=Math.max(cur,max);
        return ans;
    }
}
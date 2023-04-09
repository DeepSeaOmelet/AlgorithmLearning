package com.test.jie.leetCode.Daily.Y2022.mon8;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/08/22
 * https://leetcode.cn/problems/print-binary-tree/
 * 655. 输出二叉树
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，
 * * 用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 * <p>
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2^(height+1) - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2^(height-r-1)] ，右子节点放置在 res[r+1][c+2^(height-r-1)] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2]
 * 输出：
 * [["","1",""],
 * ["2","",""]]
 * 示例 2：
 * 输入：root = [1,2,3,null,4]
 * 输出：
 * [["","","","1","","",""],
 * ["","2","","","","3",""],
 * ["","","4","","","",""]]
 * <p>
 * 提示：
 * 树中节点数在范围 [1, 210] 内
 * -99 <= Node.val <= 99
 * 树的深度在范围 [1, 10] 内
 */

public class PrintBinaryTree {
    public static void main(String[] args) {

    }

    int h, m, n;
    List<List<String>> ans;

    public List<List<String>> printTree(TreeNode root) {
        //1.先获取树深度h，及与其相关的矩阵行列大小，并初始化
        getDepth(root, 0);
        m = h + 1;
        n = (1 << (h + 1)) - 1;
        ans = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                cur.add("");
            }
            ans.add(cur);
        }
        //2.根据规则填充
        //根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
        dfs(root, 0, (n - 1) / 2);
        return ans;
    }

    void dfs(TreeNode root, int r, int c) {
        if (root == null) {
            return;
        }
        ans.get(r).set(c, String.valueOf(root.val));
        //将其左子节点放置在 res[r+1][c-2^(height-r-1)] ，
        dfs(root.left, r + 1, c - (1 << (h - r - 1)));
        // 右子节点放置在 res[r+1][c+2^(height-r-1)]
        dfs(root.right, r + 1, c + (1 << (h - r - 1)));
    }

    void getDepth(TreeNode root, int curDepth) {
        if (root == null) {
            return;
        }
        h = Math.max(curDepth, h);
        getDepth(root.left, curDepth + 1);
        getDepth(root.right, curDepth + 1);
    }

}
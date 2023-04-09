package com.test.jie.leetCode.Daily.Y2022.mon6;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，
 * 返回所有出现次数最多的子树元素和（不限顺序）。
 * <p>
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * <p>
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 * <p>
 * 提示:
 * 节点数在 [1, 104] 范围内
 * -105 <= Node.val <= 105
 */
public class MostFrequentSubtreeSum {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> list = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k) == max) {
                list.add(k);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int cur = root.val + dfs(root.left) + dfs(root.right);
        map.put(cur,map.getOrDefault(cur,0)+1);
        max=Math.max(map.get(cur),max);
        return cur;
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon9;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2022/09/05
 * https://leetcode.cn/problems/find-duplicate-subtrees/
 * 652. 寻找重复的子树
 * 给定一棵二叉树 root，返回所有重复的子树。
 * <p>
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 * 示例 2：
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 * 示例 3：
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 * 提示：
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {

    }

    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        String key = new StringBuilder().append(root.val)
                .append("(").append(dfs(root.left)).append(")").append(dfs(root.right)).toString();
        int cnt = map.getOrDefault(key, 0);
        if (cnt == 1) {
            ans.add(root);
        }
        map.put(key, cnt + 1);
        return key;
    }
}
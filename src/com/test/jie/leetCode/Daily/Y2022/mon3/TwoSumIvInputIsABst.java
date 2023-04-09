package com.test.jie.leetCode.Daily.Y2022.mon3;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 两数之和 IV - 输入 BST
 * <p>
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * 示例 1：
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * 示例 2：
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是[1, 104].
 * -104<= Node.val <= 104
 * root为二叉搜索树
 * -105<= k <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSumIvInputIsABst {
    public static void main(String[] args) {
        TwoSumIvInputIsABst isABst = new TwoSumIvInputIsABst();
    }

    Set<Integer> set = new HashSet();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}

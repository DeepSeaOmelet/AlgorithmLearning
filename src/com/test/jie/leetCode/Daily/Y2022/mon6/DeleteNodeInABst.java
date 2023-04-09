package com.test.jie.leetCode.Daily.Y2022.mon6;

import com.test.jie.leetCode.tool.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 示例 1:
 * <p>
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 * 输入: root = [], key = 0
 * 输出: []
 * <p>
 * 提示:
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 * <p>
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 */
public class DeleteNodeInABst {
    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            //找到要删除的结点
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            //若左右子树均不为空，我们有两种选择：
            //从「当前节点的左子树」中选择「值最大」的节点替代 root 的位置，确保替代后仍满足 BST 特性；
            //从「当前节点的右子树」中选择「值最小」的节点替代 root 的位置，确保替代后仍满足 BST 特性；
            //这里选第一种
            TreeNode t = root.left;
            while (t.right != null) {
                t = t.right;
            }
            t.right=root.right;
            //确保替代后仍然满珠BST特性
            return root.left;
        } else {
            //向下找key
            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                root.left = deleteNode(root.left, key);
            }
        }
        return root;
    }
}

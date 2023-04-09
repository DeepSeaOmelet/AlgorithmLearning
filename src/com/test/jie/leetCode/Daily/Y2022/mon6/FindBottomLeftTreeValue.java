package com.test.jie.leetCode.Daily.Y2022.mon6;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 */
public class FindBottomLeftTreeValue {
    public static void main(String[] args) {

    }
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        int ans = 0;
        while (!d.isEmpty()){
            int sz = d.size();
            //最左
            ans=d.peek().val;
            while (sz-->0){
                //遍历一层
                TreeNode poll = d.pollFirst();
                if (poll.left!=null){
                    d.addLast(poll.left);
                }
                if (poll.right!=null){
                    d.addLast(poll.right);
                }
            }
        }
        return ans;
    }

}
package com.test.jie.leetCode.Daily.Y2022.mon6;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.*;

/**
 *  515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 */
public class FindLargestValueInEachTreeRow {
    public static void main(String[] args) {

    }
    public List<Integer> largestValues(TreeNode root) {
        //BFS题
        Deque<TreeNode> d = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        d.addLast(root);
        while (!d.isEmpty()){
            int max = d.peek().val;
            int size = d.size();
            while (size-->0){
                TreeNode node = d.pollFirst();
                max= Math.max(max,node.val);
                if (node.left!=null) d.addLast(node.left);
                if (node.right!=null) d.addLast(node.right);
            }
            ans.add(max);
        }
        return ans;
    }
}
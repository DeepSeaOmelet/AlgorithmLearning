package com.test.jie.leetCode.Daily.Y2022.mon9;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/21
 * https://www.programmercarl.com/0106.%E4%BB%8E%E4%B8%AD%E5%BA%8F%E4%B8%8E%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.html
 * 第一步：如果数组大小为零的话，说明是空节点了。
 * <p>
 * 第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
 * <p>
 * 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
 * <p>
 * 第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）
 * <p>
 * 第五步：切割后序数组，切成后序左数组和后序右数组
 * <p>
 * 第六步：递归处理左区间和右区间
 * <p>
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * 中序：左中右   后序：左右中
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return findTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode findTree(int[] inorder, int il, int ir, int[] postorder, int pl, int pr) {
        if (il >= ir || pl >= pr) {//左闭右开
            return null;
        }
        int rootIdx = map.get(postorder[pr-1]);
        TreeNode root = new TreeNode(inorder[rootIdx]);
        //切割
        int leftCnt = rootIdx - il;//中序左子树的个数
        root.left = findTree(inorder, il, rootIdx, postorder, pl, pl + leftCnt);
        root.right = findTree(inorder, rootIdx + 1, ir, postorder, pl + leftCnt, pr - 1);
        return root;
    }
}
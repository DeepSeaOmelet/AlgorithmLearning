package com.test.jie.leetCode.Daily.Y2022.mon3;

import com.test.jie.leetCode.tool.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 2022年3月10日
 * <p>
 * N 叉树的前序遍历
 * 给定一个 n叉树的根节点 root，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * 提示：
 * 节点总数在范围[0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NAryTreePreorderTraversal {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return ans;
        }
        ans.add(root.val);
        for (Node node : root.children) {
            preorder(node);
        }
        return ans;
    }

    //非递归用法
    //针对本题，使用「栈」模拟递归过程。
    //
    //迭代过程中记录 (node = 当前节点, cnt = 当前节点遍历过的子节点数量) 二元组，每次取出栈顶元素，
    // 如果当前节点是首次出队（当前遍历过的子节点数量为 cnt = 0），则将当前节点的值加入答案，
    // 然后更新当前元素遍历过的子节点数量，并重新入队，即将 (node, cnt + 1) 入队，
    // 以及将下一子节点 (node.children[cnt], 0)进行首次入队。
    //
    public List<Integer> preorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        Deque<Object[]> d = new ArrayDeque<>();
        //先取root
        d.addLast(new Object[]{root, 0});
        while (!d.isEmpty()) {
            //取出栈顶
            Object[] poll = d.pollLast();
            Node t = (Node) poll[0];
            Integer cnt = (Integer) poll[1];
            if (t == null) {
                continue;
            }
            //如果当前节点是首次出队（当前遍历过的子节点数量为 cnt = 0），则将当前节点的值加入答案
            if (cnt == 0) ans.add(t.val);
            if (cnt < t.children.size()) {
                //指向下一个节点
                d.addLast(new Object[]{t, cnt + 1});
                //指向子节点
                d.addLast(new Object[]{t.children.get(cnt), 0});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NAryTreePreorderTraversal traversal = new NAryTreePreorderTraversal();
        traversal.preorder(new Node());
    }

}

package com.test.jie.leetCode.Daily.Y2022.mon3;

import com.test.jie.leetCode.tool.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * N 叉树的后序遍历
 * <p>
 * 给定一个 n叉树的根节点root，返回 其节点值的 后序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * <p>
 * 提示：
 * 节点总数在范围 [0, 104] 内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NAryTreePostorderTraversal {
    List ans = new ArrayList();

    public List<Integer> postorder(Node root) {
        if (root == null) {
            return ans;
        }
        for (Node node : root.children) {
            postorder(node);
        }
        ans.add(root.val);
        return ans;
    }

    public List<Integer> preorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        Deque<Object[]> d = new ArrayDeque<>();
        d.addLast(new Object[]{root, 0});
        while (!d.isEmpty()) {
            Object[] poll = d.pollLast();
            Node t = (Node) poll[0];
            Integer cnt = (Integer) poll[1];
            if (t == null) {//节点为空
                continue;
            }
            if (cnt==t.children.size()){
                ans.add(t.val);
            }
            if (cnt<t.children.size()){
                d.addLast(new Object[]{t,cnt+1});
                d.addLast(new Object[]{t.children.get(cnt),0});
            }
        }
        return ans;
    }
}

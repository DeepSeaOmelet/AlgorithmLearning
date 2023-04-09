package com.test.jie.leetCode;

import com.test.jie.leetCode.tool.Node;
import com.test.jie.leetCode.tool.TreeNode;

import java.util.*;

public class TreeNodeDemo {
    //中序遍历
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root);
        return list;
    }

    //中序 左中右
    public void inorder(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(ans, root.left);
        ans.add(root.val);
        inorder(ans, root.right);
    }

    //前序遍历 中左右
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }

    public void preorder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        preorder(root.left, ans);
        preorder(root.right, ans);
    }

    //前序遍历 中左右  非递归
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            ans.add(cur.val);
        }
        return ans;
    }


    //后序遍历 非递归
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(list, root);
        return list;
    }

    //后序 递归 左右中
    public void postorder(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(ans, root.left);
        postorder(ans, root.right);
        ans.add(root.val);
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     * 提示：
     * 树中节点数目在范围 [0, 2000] 内
     * -1000 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = d.size();
            while (size-- > 0) {
                TreeNode node = d.pollFirst();
                list.add(node.val);
                if (node.left != null) {
                    d.addLast(node.left);
                }
                if (node.right != null) {
                    d.addLast(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[15,7],[9,20],[3]]
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     * 提示：
     * 树中节点数目在范围 [0, 2000] 内
     * -1000 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int n = d.size();
            while (n-- > 0) {
                TreeNode cur = d.pollFirst();
                list.add(cur.val);
                if (cur.left != null) {
                    d.addLast(cur.left);
                }
                if (cur.right != null) {
                    d.addLast(cur.right);
                }
            }
            result.add(list);
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
     * 因此返回 [3, 14.5, 11] 。
     * 示例 2:
     * 输入：root = [3,9,20,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     * 提示：
     * 树中节点数量在 [1, 104] 范围内
     * -231 <= Node.val <= 231 - 1
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     * 示例 2：
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     * 提示：
     * 树中节点数的范围在 [0, 105] 内
     * -1000 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) {
            return Math.max(left, right) + 1;
        }
        return Math.min(left, right) + 1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return depth;
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        //因为根节点的高度就是二叉树的最大深度，所以直接求高度->后序遍历
        if (root == null) {
            return 0;
        }
        //左右中->先计算左右子节点再计算该节点，然后再返回，下面简化了
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return depth;
    }

    /**
     * 199. 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 示例 1:
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1,3,4]
     * 示例 2:
     * 输入: [1,null,3]
     * 输出: [1,3]
     * 示例 3:
     * 输入: []
     * 输出: []
     * 提示:
     * 二叉树的节点个数的范围是 [0,100]
     * -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        ArrayDeque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                TreeNode node = d.pollFirst();
                if (size == 0) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    d.addLast(node.left);
                }
                if (node.right != null) {
                    d.addLast(node.right);
                }
            }
        }
        return list;
    }

    class ATreeNode {
        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }

        /**
         * 116. 填充每个节点的下一个右侧节点指针
         * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
         * struct Node {
         * int val;
         * Node *left;
         * Node *right;
         * Node *next;
         * }
         * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
         * 初始状态下，所有 next 指针都被设置为 NULL。
         * 示例 1：
         * 输入：root = [1,2,3,4,5,6,7]
         * 输出：[1,#,2,3,#,4,5,6,7,#]
         * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
         * 示例 2:
         * 输入：root = []
         * 输出：[]
         * 提示：
         * 树中节点的数量在 [0, 212 - 1] 范围内
         * -1000 <= node.val <= 1000
         * 进阶：
         * 你只能使用常量级额外空间。
         * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
         *
         * @param root
         * @return
         */
        public Node connect(Node root) {
            if (root == null || root.left == null) {
                return root;
            }
            // 当前节点左孩子的next指向其右孩子
            root.left.next = root.right;
            // 当前节点右孩子指向其兄弟节点的左孩子
            if (root.next != null && root.right != null) {
                root.right.next = root.next.left;
            }
            connect(root.left);
            connect(root.right);
            return root;
        }

        public Node connect2(Node root) {
            if (root == null) {
                return root;
            }
            ArrayDeque<Node> d = new ArrayDeque<>();
            d.addLast(root);
            while (!d.isEmpty()) {
                int s = d.size();
                while (s-- > 0) {
                    Node cur = d.pollFirst();
                    if (s > 0) {
                        cur.next = d.peekFirst();
                    }
                    if (cur.left != null) {
                        d.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        d.addLast(cur.right);
                    }
                }
            }
            return root;
        }

        /**
         * 117. 填充每个节点的下一个右侧节点指针 II
         * 中等
         * 给定一个二叉树
         * struct Node {
         * int val;
         * Node *left;
         * Node *right;
         * Node *next;
         * }
         * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
         * 初始状态下，所有 next 指针都被设置为 NULL。
         * 进阶：
         * 你只能使用常量级额外空间。
         * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
         * 示例：
         * 输入：root = [1,2,3,4,5,null,7]
         * 输出：[1,#,2,3,#,4,5,7,#]
         * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
         * 提示：
         * 树中的节点数小于 6000
         * -100 <= node.val <= 100
         *
         * @param root
         * @return
         */
        public Node connectII(Node root) {
            if (root == null) {
                return root;
            }
            ArrayDeque<Node> d = new ArrayDeque<>();
            d.addLast(root);
            while (!d.isEmpty()) {
                int s = d.size();
                while (s-- > 0) {
                    Node cur = d.pollFirst();
                    if (s > 0) {
                        cur.next = d.peekFirst();
                    }
                    if (cur.left != null) {
                        d.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        d.addLast(cur.right);
                    }
                }
            }
            return root;
        }
    }


    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * 示例 2：
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     * 示例 3：
     * 输入：root = []
     * 输出：true
     * 提示：
     * 树中的节点数在范围 [0, 5000] 内
     * -104 <= Node.val <= 104
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedHeight(root) >= 0;
    }

    public int isBalancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //后序遍历
        //先获取左右结点的
        int left = isBalancedHeight(root.left);
        int right = isBalancedHeight(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


    class maxDepthSolution {
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        /**
         * 559. N 叉树的最大深度
         * 给定一个 N 叉树，找到其最大深度。
         * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
         * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
         * 示例 1：
         * 输入：root = [1,null,3,2,4,null,5,6]
         * 输出：3
         * 示例 2：
         * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
         * 输出：5
         * 提示：
         * 树的深度不会超过 1000 。
         * 树的节点数目位于 [0, 104] 之间。
         *
         * @param root
         * @return
         */
        public int maxDepth(Node root) {
            //用求高度的方法求深度
            if (root == null) {
                return 0;
            }
            int max = 0;
            for (Node child : root.children) {
                max = Math.max(max, maxDepth(child));
            }
            return max + 1;
        }

        /**
         * 429. N 叉树的层序遍历
         * 中等
         * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
         * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
         * 示例 1：
         * 输入：root = [1,null,3,2,4,null,5,6]
         * 输出：[[1],[3,2,4],[5,6]]
         * 示例 2：
         * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
         * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
         * 提示：
         * 树的高度不会超过 1000
         * 树的节点总数在 [0, 10^4] 之间
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            ArrayDeque<Node> d = new ArrayDeque<>();
            d.addLast(root);
            while (!d.isEmpty()) {
                int size = d.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    Node cur = d.pollFirst();
                    for (Node child : cur.children) {
                        if (child != null) {
                            d.addLast(child);
                        }
                    }
                    list.add(cur.val);
                }
                ans.add(list);
            }
            return ans;
        }
    }

    /**
     * 515. 在每个树行中找最大值
     * 中等
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * 示例1：
     * 输入: root = [1,3,2,5,3,null,9]
     * 输出: [1,3,9]
     * 示例2：
     * 输入: root = [1,2,3]
     * 输出: [1,3]
     * 提示：
     * 二叉树的节点个数的范围是 [0,104]
     * -231 <= Node.val <= 231 - 1
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            int n = d.size();
            int max = d.peekFirst().val;
            while (n-- > 0) {
                TreeNode t = d.pollFirst();
                max = Math.max(t.val, max);
                if (t.left != null) {
                    d.addLast(t.left);
                }
                if (t.right != null) {
                    d.addLast(t.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * 示例 2：
     * 输入：root = [2,1,3]
     * 输出：[2,3,1]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     * 提示：
     * 树中节点数目范围在 [0, 100] 内
     * -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 101. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     * 示例 2：
     * 输入：root = [1,2,2,null,3,null,3]
     * 输出：false
     * 提示：
     * 树中节点数目在范围 [1, 1000] 内
     * -100 <= Node.val <= 100
     * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        //用栈模拟迭代
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if (right == null && left == null) {
                continue;
            }
            if (right == null || left == null || right.val != left.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return dfsIsSymmetric(root.left, root.right);
    }

    public boolean dfsIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return dfsIsSymmetric(left.left, right.right) && dfsIsSymmetric(left.right, right.left);
    }

    //https://leetcode.cn/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //https://leetcode.cn/problems/subtree-of-another-tree/
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return checkIsSubtree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean checkIsSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return root == subRoot;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return checkIsSubtree(root.left, subRoot.left) && checkIsSubtree(root.right, subRoot.right);
    }

    /**
     * 222. 完全二叉树的节点个数
     * 中等
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
     * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * 示例 1：
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     * 示例 2：
     * 输入：root = []
     * 输出：0
     * 示例 3：
     * 输入：root = [1]
     * 输出：1
     * 提示：
     * 树中节点的数目范围是[0, 5 * 104]
     * 0 <= Node.val <= 5 * 104
     * 题目数据保证输入的树是 完全二叉树
     * <p>
     * <p>
     * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     *
     * @return
     */
    int res = 0;

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //判断以当前结点为根节点是否为满二叉树
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0;
        while (left != null) {
            leftDepth++;
            left = left.left;
        }
        int rightDepth = 0;
        while (right != null) {
            rightDepth++;
            right = right.right;
        }
        if (leftDepth == rightDepth) {
            //左右结点高度一致
            return (2 << leftDepth) - 1;
        }
        //不是满二叉树
        int leftNodeNums = countNodes(root.left);
        int rightNodeNums = countNodes(root.right);
        return leftNodeNums + rightNodeNums + 1;
    }


    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    /**
     * 257. 二叉树的所有路径
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     * 示例 1：
     * 输入：root = [1,2,3,null,5]
     * 输出：["1->2->5","1->3"]
     * 示例 2：
     * 输入：root = [1]
     * 输出：["1"]
     * 提示：
     * 树中节点的数目在范围 [1, 100] 内
     * -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    class binaryTreePathsSolution {
        List<String> res = new ArrayList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfsBinaryTreePaths(root, sb);
            return res;
        }

        public void dfsBinaryTreePaths(TreeNode root, StringBuilder sb) {
            int len = sb.length();
            if (root.left == null && root.right == null) {
                sb.append(root.val);
                res.add(sb.toString());
                sb.delete(len, sb.length());
                return;
            }
            //前序
            sb.append(root.val).append("->");
            if (root.left != null) {
                dfsBinaryTreePaths(root.left, sb);
            }
            if (root.right != null) {
                dfsBinaryTreePaths(root.right, sb);
            }
            sb.delete(len, sb.length());
        }
    }

    /**
     * 404. 左叶子之和·简单
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
     * 示例 1：
     * 输入: root = [3,9,20,null,null,15,7]
     * 输出: 24
     * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     * 示例 2:
     * 输入: root = [1]
     * 输出: 0
     * 提示:
     * 节点数在 [1, 1000] 范围内
     * -1000 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //后序
        int res = 0;
        //判断是否为左叶子
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res = root.left.val;
        } else {
            res += sumOfLeftLeaves(root.left);
        }
        res += sumOfLeftLeaves(root.right);
        return res;
    }

    /**
     * 513. 找树左下角的值
     * 中等
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点。
     * 示例 1:
     * 输入: root = [2,1,3]
     * 输出: 1
     * 示例 2:
     * 输入: [1,2,3,4,null,5,6,null,null,7]
     * 输出: 7
     * 提示:
     * 二叉树的节点个数的范围是 [1,10^4]
     * -231 <= Node.val <= 231 - 1
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        dfsFindBottomLeftValue(root, 1);
        return findBottomLeftValueDepthAns;
    }

    int findBottomLeftValueDepth = 0;
    int findBottomLeftValueDepthAns = 0;

    public void dfsFindBottomLeftValue(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth > findBottomLeftValueDepth) {
            findBottomLeftValueDepth = depth;
            findBottomLeftValueDepthAns = root.val;
        }
        dfsFindBottomLeftValue(root.left, depth + 1);
        dfsFindBottomLeftValue(root.right, depth + 1);
    }

    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        ArrayDeque<TreeNode> d = new ArrayDeque<>();
        int ans = root.val;
        d.addLast(root);
        while (!d.isEmpty()) {
            int size = d.size();
            ans = d.peekFirst().val;
            while (size-- > 0) {
                TreeNode node = d.pollFirst();
                if (node.left != null) {
                    d.addLast(node.left);
                }
                if (node.right != null) {
                    d.addLast(node.right);
                }
            }
        }
        return ans;
    }

    /**
     * 112. 路径总和
     * 简单
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
     * * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点。
     * <p>
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     * 解释：树中存在两条根节点到叶子节点的路径：
     * (1 --> 2): 和为 3
     * (1 --> 3): 和为 4
     * 不存在 sum = 5 的根节点到叶子节点的路径。
     * 示例 3：
     * 输入：root = [], targetSum = 0
     * 输出：false
     * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     * <p>
     * 提示：
     * 树中节点的数目在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //根节点到叶子节点
        if (root == null) {
            return false;
        }
        return dfsHasPathSum(root, targetSum);
    }

    public boolean dfsHasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return target - root.val == 0;
        }
        return dfsHasPathSum(root.left, target - root.val) ||
                dfsHasPathSum(root.right, target - root.val);
    }

    /**
     * 113. 路径总和 II
     * 中等
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     * <p>
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：[]
     * 示例 3：
     * 输入：root = [1,2], targetSum = 0
     * 输出：[]
     * 提示：
     * 树中节点总数在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     *
     * @param root
     * @param targetSum
     * @return
     */
    class pathSumSolution {
        List<List<Integer>> ans;

        public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
            ans = new ArrayList<>();
            dfsPathSum(root, targetSum, new ArrayList<Integer>());
            return ans;
        }

        public void dfsPathSum(TreeNode root, int targetSum, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            if (root.left == null && root.right == null && targetSum - root.val == 0) {
                //叶子结点
                ans.add(new ArrayList<>(list));
            } else {
                dfsPathSum(root.left, targetSum - root.val, list);
                dfsPathSum(root.right, targetSum - root.val, list);
            }
            list.remove(list.size() - 1);
        }

        /**
         * 437. 路径总和 III
         * 中等
         * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
         * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
         * 示例 1：
         * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
         * 输出：3
         * 解释：和等于 8 的路径有 3 条，如图所示。
         * 示例 2：
         * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
         * 输出：3
         * <p>
         * 提示:
         * 二叉树的节点个数的范围是 [0,1000]
         * -109 <= Node.val <= 109
         * -1000 <= targetSum <= 1000
         */
        Map<Long, Integer> map = new HashMap<>();
        int res, target;

        public int pathSum(TreeNode root, int targetSum) {
            //树的遍历 + 前缀和 + 回溯 + 哈希
            if (root == null) {
                return 0;
            }
            res = 0;
            target = targetSum;
            map.put(0L, 1);
            dfsPathSum1(root, root.val);
            return res;
        }

        public void dfsPathSum1(TreeNode root, long sum) {
            if (map.containsKey(sum - target)) {
                res += map.get(sum - target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            if (root.left != null) {
                dfsPathSum1(root.left, sum + root.left.val);
            }
            if (root.right != null) {
                dfsPathSum1(root.right, sum + root.right.val);
            }
            map.put(sum, map.getOrDefault(sum, 0) - 1);
        }

    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 中等
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * 示例 1:
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     * 示例 2:
     * 输入：inorder = [-1], postorder = [-1]
     * 输出：[-1]
     * <p>
     * 提示:
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder 和 postorder 都由 不同 的值组成
     * postorder 中每一个值都在 inorder 中
     * inorder 保证是树的中序遍历
     * postorder 保证是树的后序遍历
     *
     * @param inorder
     * @param postorder
     * @return
     */
    class buildTreeClass {
        Map<Integer, Integer> map;

        public TreeNode buildTree2(int[] inorder, int[] postorder) {
            map = new HashMap<>();
            //将中序下标保存到map中
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            //左闭右开
            return buildTreeHelper2(inorder, 0, inorder.length, postorder, 0, postorder.length);
        }

        public TreeNode buildTreeHelper2(int[] inorder, int inStart, int inEnd, int[] postorder, int poStart, int poEnd) {
            //检验是否为空
            if (inStart >= inEnd || poStart >= poEnd) {
                return null;
            }
            //后序中找出根节点(最后一位)，同时在中序找出对应的下标
            int rootIdx = map.get(postorder[poEnd - 1]);
            TreeNode root = new TreeNode(inorder[rootIdx]);
            //切割,中序左子树的数量
            int lenOfLeft = rootIdx - inStart;
            //递归,inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
            root.left = buildTreeHelper2(inorder, inStart, rootIdx, postorder, poStart, poStart + lenOfLeft);
            root.right = buildTreeHelper2(inorder, rootIdx + 1, inEnd, postorder, poStart + lenOfLeft, poEnd - 1);
            return root;
        }

        /**
         * 105. 从前序与中序遍历序列构造二叉树
         * 中等
         * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
         * <p>
         * 示例 1:
         * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         * 输出: [3,9,20,null,null,15,7]
         * 示例 2:
         * 输入: preorder = [-1], inorder = [-1]
         * 输出: [-1]
         * <p>
         * 提示:
         * 1 <= preorder.length <= 3000
         * inorder.length == preorder.length
         * -3000 <= preorder[i], inorder[i] <= 3000
         * preorder 和 inorder 均 无重复 元素
         * inorder 均出现在 preorder
         * preorder 保证 为二叉树的前序遍历序列
         * inorder 保证 为二叉树的中序遍历序列
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //前序：中左右，中序：左中右
            map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            //左闭右开
            return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
        }

        private TreeNode buildTreeHelper(int[] preorder, int prStart, int prEnd, int[] inorder, int inStart, int inEnd) {
            //空指针判断
            if (prStart >= prEnd || inStart >= inEnd) {
                return null;
            }
            //前序中找出根节点，再到中序找出对应下标
            int rootIndex = map.get(preorder[prStart]);
            TreeNode root = new TreeNode(inorder[rootIndex]);
            //切割
            int lenOfRight = inEnd - rootIndex - 1;
            //递归,preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
            root.left = buildTreeHelper(preorder, prStart + 1, prEnd - lenOfRight, inorder, inStart, rootIndex);
            root.right = buildTreeHelper(preorder, prEnd - lenOfRight, prEnd, inorder, rootIndex + 1, inEnd);
            return root;
        }
    }

    /**
     * 654. 最大二叉树
     * 中等
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * 创建一个根节点，其值为 nums 中的最大值。
     * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * 示例 1：
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     * - 空数组，无子节点。
     * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     * - 空数组，无子节点。
     * - 只有一个元素，所以子节点是一个值为 1 的节点。
     * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     * - 只有一个元素，所以子节点是一个值为 0 的节点。
     * - 空数组，无子节点。
     * 示例 2：
     * 输入：nums = [3,2,1]
     * 输出：[3,null,2,null,1]
     * <p>
     * 提示：
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * nums 中的所有整数 互不相同
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfsConstructMaximumBinaryTree(nums, 0, nums.length);
    }

    public TreeNode dfsConstructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int maxIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = dfsConstructMaximumBinaryTree(nums, start, maxIndex);
        root.right = dfsConstructMaximumBinaryTree(nums, maxIndex + 1, end);
        return root;
    }

    /**
     * 617. 合并二叉树
     * 给你两棵二叉树： root1 和 root2 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * 注意: 合并过程必须从两个树的根节点开始。
     * 示例 1：
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * 输出：[3,4,5,5,4,null,7]
     * 示例 2：
     * 输入：root1 = [1], root2 = [1,2]
     * 输出：[2,2]
     * 提示：
     * 两棵树中的节点数目在范围 [0, 2000] 内
     * -104 <= Node.val <= 104
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val = root2.val + root1.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 简单
     * 355
     * 相关企业
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
     * 示例 1:
     * 输入：root = [4,2,7,1,3], val = 2
     * 输出：[2,1,3]
     * 示例 2:
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[]
     * 提示：
     * 数中节点数在 [1, 5000] 范围内
     * 1 <= Node.val <= 107
     * root 是二叉搜索树
     * 1 <= val <= 107
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 98. 验证二叉搜索树
     * 中等
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1：
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     * 提示：
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     *
     * @param root
     * @return
     */
    TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        if (root.left != null) {
            if (!isValidBST(root.left)) {
                return false;
            }
        }
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        if (root.right != null) {
            if (!isValidBST(root.right)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 简单
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * 示例 1：
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * 示例 2：
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     * 提示：
     * 树中节点的数目范围是 [2, 104]
     * 0 <= Node.val <= 105
     *
     * @param root
     * @return
     */
    class getMinimumDifferenceClass {
        int prev = -1;
        int min = Integer.MAX_VALUE;

        public int getMinimumDifference(TreeNode root) {
            if (root == null) {
                return -1;
            }
            //中序遍历，遍历的结点组成的数组是有序的
            if (root.left != null) {
                if (getMinimumDifference(root.left) == 1) {
                    return 1;
                }
            }
            if (prev != -1) {
                min = Math.min(min, root.val - prev);
            }
            prev = root.val;
            if (root.right != null) {
                if (getMinimumDifference(root.right) == 1) {
                    return 1;
                }
            }
            return min;
        }
    }

    /**
     * 501. 二叉搜索树中的众数
     * 简单
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     * 假定 BST 满足如下定义：
     * 结点左子树中所含节点的值 小于等于 当前节点的值
     * 结点右子树中所含节点的值 大于等于 当前节点的值
     * 左子树和右子树都是二叉搜索树
     * 示例 1：
     * 输入：root = [1,null,2,2]
     * 输出：[2]
     * 示例 2：
     * 输入：root = [0]
     * 输出：[0]
     * 提示：
     * 树中节点的数目在范围 [1, 104] 内
     * -105 <= Node.val <= 105
     * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     *
     * @param root
     * @return
     */
    class findModeClass {
        TreeNode prev;
        int cnt;
        int maxCnt;

        public int[] findMode(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            dfs(root, list);
            int[] res = new int[list.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        public void dfs(TreeNode root, List<Integer> list) {
            //中序
            //左
            if (root.left != null) {
                dfs(root.left, list);
            }
            //中
            if (prev != null) {
                if (prev.val == root.val) {
                    cnt++;
                } else {
                    cnt = 0;
                }
            }
            prev = root;
            if (cnt > maxCnt) {
                list.clear();//重要步骤
                maxCnt = cnt;
                list.add(root.val);
            } else if (cnt == maxCnt) {
                list.add(root.val);
            }
            //右
            if (root.right != null) {
                dfs(root.right, list);
            }
        }
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 中等
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 示例 1：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     * 示例 2：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出：5
     * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
     * 示例 3：
     * 输入：root = [1,2], p = 1, q = 2
     * 输出：1
     * 提示：
     * 树中节点数目在范围 [2, 105] 内。
     * -109 <= Node.val <= 109
     * 所有 Node.val 互不相同 。
     * p != q
     * p 和 q 均存在于给定的二叉树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * 中等
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <p>
     * 示例 1:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * 中等
     * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     * <p>
     * 示例 1：
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[4,2,7,1,3,5]
     * 解释：另一个满足题目要求可以通过的树是：
     * 示例 2：
     * 输入：root = [40,20,60,10,30,50,70], val = 25
     * 输出：[40,20,60,10,30,50,70,null,null,25]
     * 示例 3：
     * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * 输出：[4,2,7,1,3,5]
     * <p>
     * 提示：
     * 树中的节点数将在 [0, 104]的范围内。
     * -108 <= Node.val <= 108
     * 所有值 Node.val 是 独一无二 的。
     * -108 <= val <= 108
     * 保证 val 在原始BST中不存在。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode newRoot = root;
        TreeNode pre = root;
        while (root != null) {
            pre = root;
            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (pre.val > val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }
        return newRoot;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * 中等
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * <p>
     * 示例 1:
     * 输入：root = [5,3,6,2,4,null,7], key = 3
     * 输出：[5,4,6,2,null,null,7]
     * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,7], key = 0
     * 输出: [5,3,6,2,4,null,7]
     * 解释: 二叉树不包含值为 0 的节点
     * 示例 3:
     * 输入: root = [], key = 0
     * 输出: []
     * 提示:
     * 节点数的范围 [0, 104].
     * -105 <= Node.val <= 105
     * 节点值唯一
     * root 是合法的二叉搜索树
     * -10^5 <= key <= 10^5
     * <p>
     * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            //左右节点不为空
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            root.val = cur.val;
            root.right = deleteNode(root.right, cur.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 669. 修剪二叉搜索树
     * 中等
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
     * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     * <p>
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     * <p>
     * 示例 1：
     * 输入：root = [1,0,2], low = 1, high = 2
     * 输出：[1,null,2]
     * 示例 2：
     * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
     * 输出：[3,2,null,1]
     * <p>
     * 提示：
     * 树中节点数在范围 [1, 104] 内
     * 0 <= Node.val <= 104
     * 树中每个节点的值都是 唯一 的
     * 题目数据保证输入是一棵有效的二叉搜索树
     * 0 <= low <= high <= 104
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.right = trimBST(root.right, low, high);
        root.left = trimBST(root.left, low, high);
        return root;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     * 示例 1：
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     * 示例 2：
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     * <p>
     * 提示：
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 按 严格递增 顺序排列
     *
     * @param nums
     * @return
     */
    class sortedArrayToBSTClass {
        public TreeNode sortedArrayToBST(int[] nums) {
            //分治法
            return helper(nums, 0, nums.length);
        }

        public TreeNode helper(int[] nums, int left, int right) {
            if (left >= right) {
                return null;
            }
            //分治法
            int mid = (left + right) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = helper(nums, left, mid);
            node.right = helper(nums, mid + 1, right);
            return node;
        }
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 中等
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * <p>
     * 提醒一下，二叉搜索树满足下列约束条件：
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     * <p>
     * 示例 1：
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * 示例 2：
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     * 示例 3：
     * 输入：root = [1,0,2]
     * 输出：[3,3,2]
     * 示例 4：
     * 输入：root = [3,2,4,1]
     * 输出：[7,9,4,10]
     * <p>
     * <p>
     * 提示：
     * 树中的节点数介于 0 和 104 之间。
     * 每个节点的值介于 -104 和 104 之间。
     * 树中的所有值 互不相同 。
     * 给定的树为二叉搜索树。
     *
     * @param root
     * @return
     */
    class convertBSTClass {
        int pre = 0;

        public TreeNode convertBST(TreeNode root) {
            //右中左
            if (root == null) {
                return null;
            }
            convertBST(root.right);
            root.val += pre;
            pre = root.val;
            convertBST(root.left);
            return root;
        }
    }

    class findSubsequencesClass {
        /**
         * 491. 递增子序列
         * 中等
         * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
         * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
         * 示例 1：
         * 输入：nums = [4,6,7,7]
         * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
         * 示例 2：
         * 输入：nums = [4,4,3,2,1]
         * 输出：[[4,4]]
         * 提示：
         * 1 <= nums.length <= 15
         * -100 <= nums[i] <= 100
         */
        List<List<Integer>> ans;

        public List<List<Integer>> findSubsequences(int[] nums) {
            ans = new ArrayList<>();
            dfs(nums, 0, new ArrayList<>());
            return ans;
        }

        public void dfs(int[] nums, int startIndex, List<Integer> list) {
            if (list.size() > 1) {
                ans.add(new ArrayList<>(list));
            }
            if (nums.length == startIndex) {
                return;
            }
            int[] used = new int[201];
            for (int i = startIndex; i < nums.length; i++) {
                if ((startIndex > 0 && nums[i] < nums[startIndex - 1]) || used[nums[i] + 100] > 0) {
                    continue;
                }
                used[nums[i] + 100]++;
                list.add(nums[i]);
                dfs(nums, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    class permuteClass {
        /**
         * 46. 全排列
         * 中等
         * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
         * <p>
         * 示例 1：
         * 输入：nums = [1,2,3]
         * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
         * 示例 2：
         * 输入：nums = [0,1]
         * 输出：[[0,1],[1,0]]
         * 示例 3：
         * 输入：nums = [1]
         * 输出：[[1]]
         * <p>
         * 提示：
         * 1 <= nums.length <= 6
         * -10 <= nums[i] <= 10
         * nums 中的所有整数 互不相同
         *
         * @param nums
         * @return
         */
        List<List<Integer>> ans;

        public List<List<Integer>> permute(int[] nums) {
            ans = new ArrayList<>();
            dfs(nums, new ArrayList<>(), new int[nums.length]);
            return ans;
        }

        public void dfs(int[] nums, List<Integer> list, int[] used) {
            if (list.size() == nums.length) {
                ans.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i] > 0) {
                    continue;
                }
                used[i]++;
                list.add(nums[i]);
                dfs(nums, list, used);
                list.remove(list.size() - 1);
                used[i]--;
            }
        }
    }

    class permuteUniqueClass {
        /**
         * 47. 全排列 II
         * 中等
         * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
         * <p>
         * 示例 1：
         * 输入：nums = [1,1,2]
         * 输出：
         * [[1,1,2],
         * [1,2,1],
         * [2,1,1]]
         * 示例 2：
         * 输入：nums = [1,2,3]
         * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
         * <p>
         * 提示：
         * 1 <= nums.length <= 8
         * -10 <= nums[i] <= 10
         *
         * @param nums
         * @return
         */
        List<List<Integer>> ans;

        public List<List<Integer>> permuteUnique(int[] nums) {
            ans = new ArrayList<>();
            Arrays.sort(nums);
            dfs(nums, new ArrayList<>(), new int[nums.length]);
            return ans;
        }

        public void dfs(int[] nums, List<Integer> list, int[] used) {
            if (list.size() == nums.length) {
                ans.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                    continue;
                }
                if (used[i] > 0) {
                    continue;
                }
                used[i]++;
                list.add(nums[i]);
                dfs(nums, list, used);
                list.remove(list.size() - 1);
                used[i]--;
            }
        }

        /**
         * 332. 重新安排行程
         * 困难
         * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
         * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
         * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
         * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
         * <p>
         * 示例 1：
         * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
         * 输出：["JFK","MUC","LHR","SFO","SJC"]
         * 示例 2：
         * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
         * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
         * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
         * <p>
         * <p>
         * 提示：
         * 1 <= tickets.length <= 300
         * tickets[i].length == 2
         * fromi.length == 3
         * toi.length == 3
         * fromi 和 toi 由大写英文字母组成
         * fromi != toi
         *
         * @param tickets
         * @return
         */
        class findItinerary {
            public List<String> findItinerary(List<List<String>> tickets) {
                List<String> ans = new LinkedList<>();
                if (tickets == null || tickets.size() == 0) {
                    return ans;
                }
                Map<String, List<String>> map = new HashMap<>();
                for (List<String> t : tickets) {
                    List<String> list = map.computeIfAbsent(t.get(0), k -> new LinkedList<>());
                    list.add(t.get(1));
                }
                //排序
                map.values().forEach(x -> x.sort(String::compareTo));
                dfs(map, "JFK", ans);
                return ans;
            }

            public void dfs(Map<String, List<String>> map, String from, List<String> ans) {
                List<String> list = map.get(from);
                while (list != null && list.size() > 0) {
                    String dest = list.remove(0);
                    dfs(map, dest, ans);
                }
                ans.add(0, from);
            }
        }

        class solveNQueensClass {
            /**
             * 51. N 皇后
             * 困难
             * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
             * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
             * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
             * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
             * <p>
             * 示例 1：
             * 输入：n = 4
             * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
             * 解释：如上图所示，4 皇后问题存在两个不同的解法。
             * 示例 2：
             * 输入：n = 1
             * 输出：[["Q"]]
             * <p>
             * 提示：
             * 1 <= n <= 9
             *
             * @param n
             * @return
             */
            List<List<String>> ans;

            public List<List<String>> solveNQueens(int n) {
                ans = new ArrayList<>();
                if (n < 1 || n > 9) {
                    return ans;
                }
                int[] board = new int[n];
                Arrays.fill(board, -1);
                dfs(n, 0, new boolean[3][2 * n + 1], board);
                return ans;
            }

            public void dfs(int n, int row, boolean[][] used, int[] board) {
                if (row == n) {
                    ans.add(transform(board));
                    return;
                }
                for (int i = 0; i < n; i++) {
                    if (used[0][i] || used[1][i - row + n] || used[2][i + row]) {
                        continue;
                    }
                    board[row] = i;
                    used[0][i] = used[1][i - row + n] = used[2][i + row] = true;
                    dfs(n, row + 1, used, board);
                    board[row] = -1;
                    used[0][i] = used[1][i - row + n] = used[2][i + row] = false;
                }
            }

            public List<String> transform(int[] board) {
                List<String> list = new ArrayList<>();
                int len = board.length;
                for (int i = 0; i < len; i++) {
                    char[] cs = new char[len];
                    Arrays.fill(cs, '.');
                    cs[board[i]] = 'Q';
                    list.add(new String(cs));
                }
                return list;
            }
        }
    }

    class solveSudokuClass {
        /**
         * 37. 解数独
         * 困难
         * 编写一个程序，通过填充空格来解决数独问题。
         * <p>
         * 数独的解法需 遵循如下规则：
         * <p>
         * 数字 1-9 在每一行只能出现一次。
         * 数字 1-9 在每一列只能出现一次。
         * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
         * 数独部分空格内已填入了数字，空白格用 '.' 表示。
         * 示例 1：
         * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
         * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
         * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
         * <p>
         * 提示：
         * board.length == 9
         * board[i].length == 9
         * board[i][j] 是一位数字或者 '.'
         * 题目数据 保证 输入数独仅有一个解
         *
         * @param board
         */
        public void solveSudoku(char[][] board) {
            //行，列，3x3宫
            boolean[][] row = new boolean[9][9];
            boolean[][] col = new boolean[9][9];
            boolean[][] box = new boolean[9][9];
            //初始化
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        int k = (i / 3) * 3 + j / 3;//box
                        row[i][num] = true;
                        col[j][num] = true;
                        box[k][num] = true;
                    }
                }
            }
            dfs(board, row, col, box, 0, 0);
        }

        public boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] box, int y, int x) {
            if (x == 9) {
                y++;
                x = 0;
            }
            if (x == 0 && y == 9) {
                return true;
            }
            if (board[y][x] != '.') {
                return dfs(board, row, col, box, y, x + 1);
            }
            int k = (y / 3) * 3 + x / 3;//box
            for (int i = 0; i < 9; i++) {
                if (row[y][i] || col[x][i] || box[k][i]) {
                    continue;
                }
                board[y][x] = (char) ('1' + i);
                row[y][i] = col[x][i] = box[k][i] = true;
                if (dfs(board, row, col, box, y, x + 1)) {
                    return true;
                }
                board[y][x] = '.';
                row[y][i] = col[x][i] = box[k][i] = false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print((i / 3) * (j / 3)  +"  ");
                if ((j+1)%3==0){
                    System.out.println();
                }
            }
        }

    }

}

package com.test.jie.leetCode.Daily.Y2022.mon10;

import com.test.jie.leetCode.tool.TreeNode;

/**
 * 2022/10/31
 * 481. 神奇字符串
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 * <p>
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。
 * * 每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 * <p>
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 * <p>
 * 示例 1：
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 105
 */
public class MagicalString {
    public static void main(String[] args) {
        MagicalString m = new MagicalString();
        System.out.println(m.magicalString(1000));
    }

    public int magicalString(int n) {
        //模拟打表
        int[] magic = new int[n + 1];
        magic[0] = 1;//初始化
        int p = 1, cur = 1, ans = 1;
        int count = 2, val = 1;
        while (cur < n) {
            val = val ^ 3;//1和2转换
            while (count-- > 0 && cur < n) {//根据p指针所指示，循环创建"count"个元素，每个元素都是"value"
                magic[cur++] = val;
                if (val == 1) ans++;//依题目计算1的数量
            }
            count = magic[++p]; // 创建完第"p"组所有元素之后，获得下一组(即："p+1")需要创建的数字个数"count"
        }
        return ans;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            //以下两个包括子节点都为空的情况
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            //两个都不为空
            TreeNode t = root.right;
            while (t.left != null) {
                t = t.left;
            }
            t.left=root.left;
            return root.right;
        } else {
            //寻找
            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                root.left = deleteNode(root.left, key);
            }
        }
        return root;
    }
}
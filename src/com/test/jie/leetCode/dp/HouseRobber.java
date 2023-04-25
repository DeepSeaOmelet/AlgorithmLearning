package com.test.jie.leetCode.dp;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 中等
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * *  示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //1
        int[] dp = new int[n + 1];
        //2
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        //3
        for (int i = 2; i < n; i++) {
            //4
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}

class HouseRobber2 {
    //https://leetcode.cn/problems/house-robber-ii/
    //213. 打家劫舍 II
    //中等
    //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
    //这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
    //如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
    //
    //示例 1：
    //输入：nums = [2,3,2]
    //输出：3
    //解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    //示例 2：
    //输入：nums = [1,2,3,1]
    //输出：4
    //解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
    //     偷窃到的最高金额 = 1 + 3 = 4 。
    //示例 3：
    //输入：nums = [1,2,3]
    //输出：3
    //
    //提示：
    //1 <= nums.length <= 100
    //0 <= nums[i] <= 1000
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robAction(nums, 0, nums.length - 2), robAction(nums, 1, nums.length - 1));
    }

    public int robAction(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(dp[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[end];
    }

}

class HouseRobber3 {
    /**
     * 337. 打家劫舍 III    https://leetcode.cn/problems/house-robber-iii/
     * 中等
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     * <p>
     * 示例 1:
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     * 示例 2:
     * 输入: root = [3,4,5,1,3,null,1]
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
     * <p>
     * 提示：
     * 树的节点数在 [1, 104] 范围内
     * 0 <= Node.val <= 104
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        return robAction(root)[0];
    }

    public int[] robAction(TreeNode root) {
        int[] ans = new int[2];
        if (root == null) {
            return ans;
        }
        int[] left = robAction(root.left);
        int[] right = robAction(root.right);
        ans[0] = Math.max(left[0] + right[0], left[1] + right[1] + root.val);
        ans[1] = left[0] + right[0];
        return ans;
    }

}
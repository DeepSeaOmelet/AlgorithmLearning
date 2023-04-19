package com.test.jie.leetCode.Daily.Y2023.mon4;

import java.util.Arrays;

/**
 * 2023/04/19
 * 1043. 分隔数组以得到最大和    https://leetcode.cn/problems/partition-array-for-maximum-sum/
 * 中等
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 * <p>
 * 示例 1：
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * 输入：arr = [1], k = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 */
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        PartitionArrayForMaximumSum test = new PartitionArrayForMaximumSum();
        System.out.println(test.maxSumAfterPartitioning1(new int[]{1, 15, 7, 9, 2, 5, 10}, 3));
    }

    //回溯    超时
    public int maxSumAfterPartitioning1(int[] arr, int k) {
        return dfs(arr.length - 1, arr, k);
    }

    private int dfs(int idx, int[] arr, int k) {
        if (idx < 0) {
            return 0;
        }
        int ans = 0;
        int max = 0;
        for (int i = idx; i >= 0 && idx - i < k; i--) {
            max = Math.max(max, arr[i]);
            ans = Math.max(ans, dfs(i - 1, arr, k) + max * (idx - i + 1));
        }
        return ans;
    }

    //回溯+记忆= 记忆化搜索
    public int maxSumAfterPartitioning2(int[] arr, int k) {
        int[] memory = new int[arr.length];
        //-1 表示没有计算过
        Arrays.fill(memory, -1);
        return dfs2(arr.length - 1, arr, k, memory);
    }

    private int dfs2(int idx, int[] arr, int k, int[] memory) {
        //由于递归函数没有副作用，同样的入参无论计算多少次，算出来的结果都是一样的
        if (idx < 0) {
            return 0;
        }
        if (memory[idx] != -1) {
            return memory[idx];
        }
        int ans = 0;
        int max = 0;
        for (int i = idx; i >= 0 && idx - i < k; i--) {
            max = Math.max(max, arr[i]);    // 一边枚举 j，一边计算子数组的最大值
            ans = Math.max(ans, dfs2(i - 1, arr, k, memory) + max * (idx - i + 1));
        }
        memory[idx] = ans;
        return memory[idx];//记忆化
    }

    //动态规划
    public int maxSumAfterPartitioning3(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = i, max = 0; j > i - k && j >= 0; j--) {
                max = Math.max(max, arr[j]);//计算子数组的最大值
                dp[i + 1] = Math.max(dp[i + 1], dp[j] + max * (i - j + 1));
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[len];
    }
}
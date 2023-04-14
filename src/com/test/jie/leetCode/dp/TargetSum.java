package com.test.jie.leetCode.dp;

import java.util.Arrays;

public class TargetSum {
    /**
     * https://leetcode.cn/problems/target-sum/description/
     * 494. 目标和
     * 动态规划01背包题
     */
    //递归搜索 + 保存计算结果 = 记忆化搜索
    class Solution1 {
        private int[] nums;
        private int[][] cache;

        public int findTargetSumWays(int[] nums, int target) {
            for (int x : nums) {
                target += x;
            }
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            target /= 2;
            this.nums = nums;
            int n = nums.length;
            cache = new int[n][target + 1];
            for (int i = 0; i < n; i++) {
                Arrays.fill(cache[i], -1);
            }
            return dfs(n - 1, target);
        }

        private int dfs(int i, int t) {
            if (i < 0) {
                return t == 0 ? 1 : 0;
            }
            if (cache[i][t] != -1) {
                return cache[i][t];
            }
            if (nums[i] > t) {
                return cache[i][t] = dfs(i - 1, t);
            }
            return cache[i][t] = dfs(i - 1, t) + dfs(i - 1, t - nums[i]);
        }
    }

    class Solution2 {
        //1:1 翻译成递推
        public int findTargetSumWays(int[] nums, int target) {
            for (int x : nums) {
                target += x;
            }
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            target /= 2;

            int n = nums.length;
            //dfs->数组
            //递归->循环
            //递归边界->数组初始值
            int[][] f = new int[n + 1][target + 1];
            f[0][0] = 1;//边界
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= target; j++) {
                    if (nums[i] > j) {
                        f[i + 1][j] = f[i][j];
                    } else {
                        f[i + 1][j] = f[i][j] + f[i][j - nums[i]];
                    }
                }
            }
            return f[n][target];
        }
    }

    class Solution3 {
        //空间优化
        public int findTargetSumWays(int[] nums, int target) {
            for (int x : nums) {
                target += x;
            }
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            target /= 2;

            int n = nums.length;
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = target; j >= nums[i]; j--) {
                    f[j] = f[j] + f[j - nums[i]];
                }
            }
            return f[target];
        }
    }
}

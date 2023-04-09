package com.test.jie.leetCode.Daily.Y2022.mon8;

import java.util.Arrays;

/**
 * 2022/08/18
 * hard
 * https://leetcode.cn/problems/maximum-equal-frequency/*
 * 1224. 最大相等频率
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 * <p>
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 * <p>
 * 示例 1：
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 * 示例 2：
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 * <p>
 * 提示：
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class MaximumEqualFrequency {
    public static void main(String[] args) {

    }

    int[] cnt = new int[100010];//记录每个数的出现次数
    int[] sum = new int[100010];//记录出现次数为某个值的数有多少个

    public int maxEqualFreq(int[] nums) {
        Arrays.fill(cnt, 0);
        Arrays.fill(sum, 0);
        int max = 0;//记录最大出现次数 cnt[i]
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int cur = ++cnt[n];
            int len = i + 1;
            sum[cur]++;
            sum[cur - 1]--;
            max = Math.max(max, cur);
            if (max == 1) {//所以有数字均只出现一次
                ans = len;
            }
            if (max * sum[max] + 1 == len) {//所有数字都是最大出现次数
                ans = len;
            }
            if ((max - 1) * (sum[max - 1] + 1) + 1 == len) {//说明出现次数为 max 的数值只有一个，其余出现次数均为 max - 1
                ans = len;
            }
        }
        return ans;
    }
}
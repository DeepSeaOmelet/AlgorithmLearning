package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.Arrays;

/**
 * 2022/09/26
 * https://leetcode.cn/problems/missing-two-lcci/*
 * 面试题 17.19. 消失的两个数字
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。
 * 你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * <p>
 * 以任意顺序返回这两个数字均可。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * <p>
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * <p>
 * nums.length <= 30000
 */
public class MissingTwoLcci {
    public static void main(String[] args) {
        MissingTwoLcci m = new MissingTwoLcci();
        System.out.println(Arrays.toString(m.missingTwo(new int[]{1})));
        System.out.println(Arrays.toString(m.missingTwo(new int[]{2, 3})));
    }

    public int[] missingTwo2(int[] nums) {
        boolean[] ints = new boolean[30100];
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            ints[nums[i]] = true;
        }
        for (int i = 0, j = 1; i < 2; ) {
            if (!ints[j++]) {
                ans[i++] = j - 1;
            }
        }
        return ans;
    }

    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;//数组原本的长度
        int cur = n * (1 + n) / 2;//数组原本的总和，根据等差数列求和公式得出
        for (int x : nums) {//缺失数值之和
            cur -= x;
        }
        int sum = cur;
        int t = cur / 2;//缺失数值之和平均值
        cur = t * (1 + t) / 2;//求数组[1, t]的理论总和
        for (int x : nums) {//求数组[1, t]的实际总和，理论与实际之差即缺失两数之一
            if (x <= t) {
                cur -= x;
            }
        }
        return new int[]{cur, sum - cur};
    }
}
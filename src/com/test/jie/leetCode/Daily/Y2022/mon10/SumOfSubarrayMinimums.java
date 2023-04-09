package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.List;

/**
 * 2022/10/28
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * <p>
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * <p>
 * 示例 1：
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * <p>
 * 提示：
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 */
public class SumOfSubarrayMinimums {
    public static void main(String[] args) {

    }

    int MOD = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        int[] stack = new int[arr.length];
        int head = 0, tail = head, mod = (int) (1e9 + 7);
        for (int i = 0; i <= arr.length; i++) {
            int num = (i == arr.length) ? 0 : arr[i];
            while (head != tail && arr[stack[tail - 1]] > num) {
                int n = stack[--tail];
                int h = (head != tail) ? stack[tail - 1] : -1;
                int t = i;
                ans = (ans + (long) (n - h) * (t - n) * arr[n]) % mod;
            }
            stack[tail++] = i;
        }
        return (int) ans;
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        int idx = ruleKey.charAt(0) == 't' ? 0 : ruleKey.charAt(0) == 'c' ? 1 : 2;
        for (List<String> item : items) {
            if (item.get(idx).equals(ruleValue)) {
                ans++;
            }
        }
        return ans;
    }
}
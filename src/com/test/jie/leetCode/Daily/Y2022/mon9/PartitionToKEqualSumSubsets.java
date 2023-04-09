package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.Arrays;

/**
 * 2022/09/20
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 * <p>
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        PartitionToKEqualSumSubsets p = new PartitionToKEqualSumSubsets();
        System.out.println(p.canPartitionKSubsets(new int[]{4, 4, 6, 2, 3, 8, 10, 2, 10, 7}, 4));
    }

    int[] nums;
    int n, avg, k;
    public boolean canPartitionKSubsets(int[] _nums, int _k) {
        int sum = 0;
        for (int i : _nums) {
            sum += i;
        }
        if (sum % _k != 0) {
            return false;
        }
        avg = sum / _k;
        n = _nums.length;
        k = _k;
        nums = _nums;
        return dfs(n - 1, 0, 0, new boolean[n]);
    }

    /**
     * @param idx 是搜索关键，其含义为搜索空间的分割点。即对于当前正在搜索的集合，
     *            我们不会每次都扫描整个 nums 来找添加到该集合的下一个元素，
     *            而是能够明确下一个元素必然在 idx 的左边或右边。
     * @param cur 为当前集合的元素和（初始值为 0）；
     * @param cnt 是已凑成多少个总和为t 的集合（初始值为 0，当 cnt = k时，我们搜索到了合法方案，
     *            返回 true，否则对 cnt 进行加一操作，并将 cur 置零，搜索下个集合）；
     * @param vis 用于记录哪些 nums[i]nums[i] 已被使用；
     * @return
     */
    boolean dfs(int idx, int cur, int cnt, boolean[] vis) {
        if (cnt == k) return true;
        if (cur == avg) return dfs(n - 1, 0, cnt + 1, vis);
        for (int i = idx; i >= 0; i--) {//顺序性剪枝
            if (vis[i] || cur + nums[i] > avg) continue;
            vis[i] = true;
            if (dfs(i - 1, cur + nums[i], cnt, vis)) return true;
            vis[i] = false;
            if (cur == 0) return false;//意味着这首个数字不能凑成集合，即无效
        }
        return false;
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        //0.数组总和sum，之后sum/k -> avg即非空子集的总和？
        //1.首先求和
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;
        Arrays.sort(nums);
        return false;
//        //下面的方案不适合
//        //从大到小找到满足子集组合,双指针
//        int len = nums.length;
//        for (int i = len - 1, j = 0; i > j; i--) {
//            if (nums[i] > avg) {
//                return false;
//            } else if (nums[i] == avg) {
//                k--;
//                continue;
//            } else {
//                int cur = nums[i];
//                while (cur < avg && j < i) {//问题出现在这里，不能单纯的小大匹配，这里应该用栈或者
//                    cur += nums[j];
//                    j++;
//                }
//                if (cur > avg) {
//                    return false;
//                }
//                k--;
//            }
//        }
//        if (k!=0){
//            return false;
//        }
//        return true;
    }
}
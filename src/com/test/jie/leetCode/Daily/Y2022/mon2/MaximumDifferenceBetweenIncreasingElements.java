package com.test.jie.leetCode.Daily.Y2022.mon2;

/**
 * 增量元素之间的最大差值
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值，
 * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * <p>
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [7,1,5,4]
 * 输出：4
 * 解释：
 * 最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
 * 注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 > 4 ，但 i > j 不满足题面要求，所以 6 不是有效的答案。
 * 示例 2：
 * 输入：nums = [9,4,3,2]
 * 输出：-1
 * 解释：
 * 不存在同时满足 i < j 和 nums[i] < nums[j] 这两个条件的 i, j 组合。
 * 示例 3：
 * 输入：nums = [1,5,2,10]
 * 输出：9
 * 解释：
 * 最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
 * <p>
 * 提示：
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 109
 */
public class MaximumDifferenceBetweenIncreasingElements {
    public static void main(String[] args) {
        MaximumDifferenceBetweenIncreasingElements elements = new MaximumDifferenceBetweenIncreasingElements();
        System.out.println(elements.maximumDifference(new int[]{7, 1, 5, 4}));
        System.out.println(elements.maximumDifference(new int[]{9, 4, 3, 2}));
        System.out.println(elements.maximumDifference(new int[]{1, 5, 2, 10}));
    }

    //模拟方法
    //利用我们目的是找到能够取得最大差值的数对，对于每个数对中的 nums[i]nums[i] 而言，
    // 对应的 nums[j]nums[j] 必然第是坐标 ii 左侧的最小值，因此可以通过边遍历边维护最小值 minmin 的做法，从而将复杂度降到 O(n)O(n)。
    //
    public int maximumDifference(int[] nums) {
        int ans = -1, n = nums.length;
        for (int i = 0, min = nums[0]; i < n; i++) {
            if (nums[i] > min) {
                ans = Math.max(ans, nums[i] - min);
            }
            min = Math.min(min, nums[i]);
        }
        return ans;
    }
}

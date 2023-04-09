package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Arrays;

/**
 * 719. 找出第 K 小的数对距离
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
 * 返回 所有数对距离中 第 k 小的数对距离。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 * 示例 2：
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 * 示例 3：
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 * <p>
 * 提示：
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 */
public class FindKThSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = (int) 1e6;
        while (l < r) {
            //找出右边界
            int mid = l + r >> 1;
            if ((check(nums, mid) >= k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    int check(int[] nums, int mid) {
        int n = nums.length, ans = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j < n && nums[j] - nums[i] <= mid) j++;
            ans += j - i - 1;
        }
        return ans;
    }
}

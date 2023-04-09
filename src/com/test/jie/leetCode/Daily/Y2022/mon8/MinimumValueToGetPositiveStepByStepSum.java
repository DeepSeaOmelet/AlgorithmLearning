package com.test.jie.leetCode.Daily.Y2022.mon8;

/**
 * 2022/08/09
 * 1413. 逐步求和得到正数的最小值
 * 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
 * <p>
 * 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
 * <p>
 * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-3,2,-3,4,2]
 * 输出：5
 * 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
 * 累加求和
 * startValue = 4 | startValue = 5 | nums
 * (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
 * (1 +2 ) = 3  | (2 +2 ) = 4    |   2
 * (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
 * (0 +4 ) = 4  | (1 +4 ) = 5    |   4
 * (4 +2 ) = 6  | (5 +2 ) = 7    |   2
 * 示例 2：
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最小的 startValue 需要是正数。
 * 示例 3：
 * 输入：nums = [1,-2,-3]
 * 输出：5
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class MinimumValueToGetPositiveStepByStepSum {
    public static void main(String[] args) {
        MinimumValueToGetPositiveStepByStepSum m = new MinimumValueToGetPositiveStepByStepSum();
        System.out.println(m.minStartValue(new int[]{-3, 2, -3, 4, 2}));
        System.out.println(m.minStartValue(new int[]{1, 2}));
        System.out.println(m.minStartValue(new int[]{1, -2, -3}));
    }

    public int minStartValue(int[] nums) {
        int min = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            min = Math.min(min, sum);
        }
        return min < 1 ? 1 - min : 1;
    }

    public int minStartValue2(int[] nums) {
        int sum = 0;
        int ans = 0;
        for (int i : nums) {
            sum += i;
            if (sum < 1) {
                ans += 1 - sum;
                sum = 1;
            }
        }
        return ans == 0 ? 1 : ans;
    }
}
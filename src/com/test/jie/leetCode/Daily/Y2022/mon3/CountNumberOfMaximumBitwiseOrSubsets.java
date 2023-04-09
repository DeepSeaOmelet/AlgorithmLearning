package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * 2044. 统计按位或能得到最大值的子集数目
 * <p>
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * 对数组 a 执行 按位或，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1]
 * 输出：2
 * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
 * - [3]
 * - [3,1]
 * 示例 2：
 * 输入：nums = [2,2,2]
 * 输出：7
 * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2^3 - 1 = 7 个子集。
 * 示例 3：
 * 输入：nums = [3,2,1,5]
 * 输出：6
 * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 * <p>
 * 提示：
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNumberOfMaximumBitwiseOrSubsets {
    public static void main(String[] args) {
        CountNumberOfMaximumBitwiseOrSubsets number = new CountNumberOfMaximumBitwiseOrSubsets();
        System.out.println(number.countMaxOrSubsets(new int[]{3, 1}));
        System.out.println(number.countMaxOrSubsets(new int[]{2, 2, 2}));
        System.out.println(number.countMaxOrSubsets(new int[]{3, 2, 1, 5}));
    }

    //二进制枚举
    //令 n 为 nums 的长度，利用 n 不超过 16，我们可以使用一个 int 数值来代指 nums 的使用情况（子集状态）。
    //假设当前子集状态为 state，state为一个仅考虑低 n 位的二进制数，当第 k位为 1，代表nums[k] 参与到当前的按位或运算，当第k 位为 0，代表 nums[i] 不参与到当前的按位或运算。
    //在枚举这 2^n2 个状态过程中，我们使用变量 max 记录最大的按位或得分，使用 ans 记录能够取得最大得分的状态数量。
    //
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length, mask = 1 << n;
        int max = 0, ans = 0;
        //利用s和i来模拟nums的子集状态，进行二进制枚举
        for (int s = 0; s < mask; s++) {
            int cur = 0;
            for (int i = 0; i < n; i++) {
                if ((((s >> i) & 1)) == 1) {
                    cur |= nums[i];
                }
            }
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }
        }
        return ans;
    }
}

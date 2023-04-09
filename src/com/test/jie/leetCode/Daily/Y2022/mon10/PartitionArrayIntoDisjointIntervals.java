package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/10/24
 * 915. 分割数组
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * <p>
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 * <p>
 * 用例可以保证存在这样的划分方法。
 * 示例 1：
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 * 示例 2：
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 * <p>
 * 提示：
 * 2 <= nums.length <= 105
 * 0 <= nums[i] <= 106
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
 */
public class PartitionArrayIntoDisjointIntervals {
    public static void main(String[] args) {
        PartitionArrayIntoDisjointIntervals p = new PartitionArrayIntoDisjointIntervals();
        System.out.println(p.partitionDisjoint(new int[]{1, 1}));
        System.out.println(p.partitionDisjoint(new int[]{26, 51, 40, 58, 42, 76, 30, 48, 79, 91}));
        System.out.println(p.partitionDisjoint(new int[]{3, 1, 8, 4, 9, 7, 12, 0, 0, 12, 6, 12, 6, 19, 24, 90, 87, 54, 92, 60, 31, 59, 75, 90, 20, 38, 52, 51, 74, 70, 86, 20, 27, 91, 55, 47, 54, 86, 15, 16, 74, 32, 68, 27, 19, 54, 13, 22, 34, 74, 76, 50, 74, 97, 87, 42, 58, 95, 17, 93, 39, 33, 22, 87, 96, 90, 71, 22, 48, 46, 37, 18, 17, 65, 54, 82, 54, 29, 27, 68, 53, 89, 23, 12, 90, 98, 42, 87, 91, 23, 72, 35, 14, 58, 62, 79, 30, 67, 44, 48}));
    }

    public int partitionDisjoint(int[] nums) {
        //分割点的「左边的子数组的最大值」小于等于「右边的子数组的最小值」。
        int index = 0, max = nums[0], leftMax = max;
        for (int i = 1; i < nums.length; i++) {
            if (leftMax > nums[i]) {
                index = i;
                leftMax = max;
            } else {
                max = Math.max(nums[i], max);
            }
        }
        return index+1;
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/10/10
 * 801. 使序列递增的最小交换次数
 * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
 * <p>
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
 * <p>
 * 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。
 * <p>
 * 注意：
 * 用例保证可以实现操作。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 * 示例 2:
 * 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * 输出: 1
 * <p>
 * 提示:
 * 2 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 105
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    public static void main(String[] args) {

    }

    //https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/solution/by-ac_oier-fjhp/
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] f = new int[n][2];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i][1] = n + 10;
        }
        f[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1] + 1;
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                f[i][0] = Math.min(f[i][0], f[i - 1][1]);
                f[i][1] = Math.min(f[i][1], f[i - 1][0] + 1);
            }
        }
        return Math.min(f[n - 1][0], f[n - 1][1]);
    }
}
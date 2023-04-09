package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.*;

/**
 * 2022/09/19
 * 1636. 按照频率将数组升序排序
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 * <p>
 * 请你返回排序后的数组。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {
    public static void main(String[] args) {

    }

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ArrayList<int[]> list = new ArrayList<>();
        for (int key : map.keySet()) {
            list.add(new int[]{key, map.get(key)});
        }
        Collections.sort(list, (a, b) -> {
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });
        int[] ans = new int[nums.length];
        int idx = 0;
        for (int[] l : list) {
            while (l[1]-- > 0) {
                ans[idx++] = l[0];
            }
        }
        return ans;
    }
}
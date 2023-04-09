package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 2022/10/08
 * 870. 优势洗牌
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 * <p>
 * 示例 1：
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 * <p>
 * 提示：
 * 1 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 109
 */
public class AdvantageShuffle {
    public static void main(String[] args) {

    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> tset = new TreeSet<>();
        for (int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) == 1) {
                tset.add(x);
            }
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            Integer cur = tset.ceiling(nums2[i] + 1);
            if (cur == null) {
                cur = tset.ceiling(-1);
            }
            ans[i] = cur;
            map.put(cur, map.get(cur) - 1);
            if (map.get(cur) == 0) {
                tset.remove(cur);
            }
        }
        return ans;
    }
}
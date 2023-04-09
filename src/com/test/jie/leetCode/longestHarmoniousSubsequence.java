package com.test.jie.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 最长和谐子序列
 * 
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 * 示例 1：
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：2
 * 示例 3：
 * 输入：nums = [1,1,1,1]
 * 输出：0
 * 
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 *
 */
public class longestHarmoniousSubsequence {
    public static void main(String[] args) {
        longestHarmoniousSubsequence lhs = new longestHarmoniousSubsequence();
        System.out.println(lhs.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(lhs.findLHS(new int[]{1,2,3,4}));
        System.out.println(lhs.findLHS(new int[]{1,1,1,1}));
        System.out.println(lhs.findLHS(new int[]{1,2,2,1}));
    }

    public int findLHS(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key:map.keySet()){
            if (map.containsKey(key-1)){
                ans=Math.max(ans,map.get(key)+ map.get(key-1));
            }
        }
        return ans;
    }

}

package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *  2022/10/24
 *  78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class MergeStringsAlternately {
    public static void main(String[] args) {

    }
    //回溯
    public List<List<Integer>> ans = new ArrayList<>();
    public Deque<Integer> cur = new ArrayDeque<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0,nums);
        return ans;
    }
    void dfs(int start,int[] nums){
        ans.add(new ArrayList<>(cur));
        if (start>= nums.length){
            return;
        }
        for (int i = start; i < nums.length; i++) {
            cur.addLast(nums[i]);
            dfs(i+1,nums);
            cur.removeLast();
        }
    }
}
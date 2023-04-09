package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.*;

/**
 * 2022/10/27
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsIi {
    public static void main(String[] args) {

    }

    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> list = new ArrayDeque<>();
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        dfs(0, nums);
        return ans;
    }

    void dfs(int index, int[] nums) {
        ans.add(new ArrayList<>(list));
        if (index >= nums.length) return;
        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i] && !used[i-1]) {
                continue;
            }
            list.addLast(nums[i]);
            used[i] = true;
            dfs(i + 1, nums);
            list.removeLast();
            used[i] = false;
        }
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.*;

/**
 * 2022/10/27
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSumIi {
    public static void main(String[] args) {
        CombinationSumIi c = new CombinationSumIi();
        System.out.println(c.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> d = new ArrayDeque<>();
    int target;
    boolean[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int _target) {
        Arrays.sort(candidates);
        target = _target;
        used = new boolean[candidates.length];
        Arrays.fill(used, false);
        dfs(candidates, 0, 0);
        return ans;
    }

    private void dfs(int[] candidates, int sum, int start) {
        if (sum == target) {
            ans.add(new ArrayList<>(d));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            //去重
            if (i > 0 && candidates[i - 1] == candidates[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            d.addLast(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            dfs(candidates, sum + candidates[i], i + 1);
            used[i] = false;
            d.pollLast();
        }
    }
}
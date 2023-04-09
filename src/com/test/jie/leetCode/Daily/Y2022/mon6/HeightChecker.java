package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Arrays;

/**
 * 1051. 高度检查器
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 * <p>
 * 示例：
 * 输入：heights = [1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度：[1,1,4,2,1,3]
 * 预期：[1,1,1,2,3,4]
 * 下标 2 、4 、5 处的学生高度不匹配。
 * 示例 2：
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 解释：
 * 高度：[5,1,2,3,4]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都不匹配。
 * 示例 3：
 * 输入：heights = [1,2,3,4,5]
 * 输出：0
 * 解释：
 * 高度：[1,2,3,4,5]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都匹配。
 * <p>
 * 提示：
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class HeightChecker {
    //要求你对比排序后和排序前位置不一样的个数
    public static void main(String[] args) {

    }

    public int heightChecker2(int[] heights) {
        int[] cnts = new int[110];
        for (int i : heights) cnts[i]++;
        int len = heights.length, ans = 0;
        int[] temp = new int[len];
        //计数排序
        for (int i = 0, j = 0; i < 110; i++) {
            while (cnts[i]-- > 0) temp[j++] = i;
        }
        for (int i = 0; i < len; i++) {
            if (heights[i] != temp[i]) {
                ans++;
            }
        }
        return ans;

    }

    //粗暴方式
    public int heightChecker(int[] heights) {
        int cnt = 0;
        int[] exp = heights.clone();
        Arrays.sort(exp);
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            if (exp[i] != heights[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}

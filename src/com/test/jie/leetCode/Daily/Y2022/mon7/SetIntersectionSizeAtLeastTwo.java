package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.Arrays;

/**
 * 2022/07/23
 * 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 * <p>
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 * <p>
 * 输出这个最小集合S的大小。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 * <p>
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 * <p>
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 * https://leetcode.cn/problems/set-intersection-size-at-least-two/
 */
public class SetIntersectionSizeAtLeastTwo {
    public static void main(String[] args) {

    }

    public int intersectionSizeTwo(int[][] ins) {
        Arrays.sort(ins, (a, b) -> {
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });
        int a = -1, b = -1, ans = 0;
        for (int[] i : ins) {
            if (i[0] > b) {
                a = i[1] - 1;
                b = i[1];
                ans += 2;
            } else if (i[0] > a) {
                a = b;
                b = i[1];
                ans++;
            }
        }
        return ans;
    }
}
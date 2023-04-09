package com.test.jie.leetCode.Daily.Y2022.mon6;

/**
 * 1037. 有效的回旋镖
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 * <p>
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 * <p>
 * 示例 1：
 * 输入：points = [[1,1],[2,3],[3,2]]
 * 输出：true
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * points.length == 3
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 */
public class ValidBoomerang {
    public static void main(String[] args) {
        ValidBoomerang validBoomerang = new ValidBoomerang();
        System.out.println(validBoomerang.isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
    }

    public boolean isBoomerang(int[][] points) {
        int[] a = points[0];
        int[] b = points[1];
        int[] c = points[2];
        int[] ab = new int[]{b[0] - a[0], b[1] - a[1]};
        int[] ac = new int[]{c[0] - a[0], c[1] - a[1]};
        return (ab[0] * ac[1]) != (ab[1] * ac[0]);
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/29
 * 593. 有效的正方形
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * <p>
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 * <p>
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * 示例 1:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 * <p>
 * 提示:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 104
 */
public class ValidSquare {
    public static void main(String[] args) {

    }

    long len = -1;

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return cal(p1, p2, p3) && cal(p1, p2, p4) && cal(p1, p3, p4) && cal(p2, p3, p4);
    }

    public boolean cal(int[] a, int[] b, int[] c) {
        //这里是边长，与入参无关a^2 + b^2 = c^2
        //两点距离：(x1,y1),(x2,y2) (()+())
        long x = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        long y = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
        long z = (b[0] - c[0]) * (b[0] - c[0]) + (b[1] - c[1]) * (b[1] - c[1]);
        boolean isOK = (x == y && x + y == z) || (x == z && z + x == y) || (y == z && y + z == x);
        if (!isOK) return false;
        if (len == -1) {
            len = Math.min(x, y);
        } else if (len != Math.min(x, y) || len == 0) {
            return false;
        }
        return true;
    }
}
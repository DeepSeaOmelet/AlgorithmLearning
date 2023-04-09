package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Arrays;

/**
 * 498. 对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */
public class DiagonaTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        //右上方是[x-1,y+1],左下方是[x+1,y-1]
        //若未遍历完全，
        // 右上方若是上方越界后是[x+1,y]，右方或右上方越界是[x,y+1]
        // 左下方若是左方越界后是[x,y+1]，左下方或者下方越界则[x+1,y]
        //用change代表1为右上，-1为左下
        //用cnt表示总格子数，用cur表示遍历格子的计数
        int change = 1, x = 0, y = 0;
        int cnt = m * n, cur = 0;
        int[] ans = new int[cnt];
        while (cnt > cur) {
            ans[cur++] = mat[x][y];
            int tempX , tempY ;
            if (change == 1) {
                tempX = x - 1;
                tempY = y + 1;
            } else {
                tempX = x + 1;
                tempY = y - 1;
            }
            //判断越界
            if (cur < cnt && (tempX < 0 || tempX >= n || tempY < 0 || tempY >= m)) {
                if (change == 1) {
                    tempX = y + 1 < m ? x : x + 1;
                    tempY = y + 1 < m ? y + 1 : y;
                } else {
                    tempX = x + 1 < n ? x + 1 : x;
                    tempY = x + 1 < n ? y : y + 1;
                }
                change *= -1;
            }
            x = tempX;
            y = tempY;
        }
        return ans;
    }

    public static void main(String[] args) {
        DiagonaTraverse d = new DiagonaTraverse();
        System.out.println(Arrays.toString(d.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));

    }
}

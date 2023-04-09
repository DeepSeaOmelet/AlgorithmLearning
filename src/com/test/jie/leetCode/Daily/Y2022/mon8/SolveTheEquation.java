package com.test.jie.leetCode.Daily.Y2022.mon8;

/**
 * 2022/08/10
 * https://leetcode.cn/problems/solve-the-equation/
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * <p>
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 * <p>
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 * <p>
 * 示例 1：
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 * <p>
 * 提示:
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。
 */
public class SolveTheEquation {
    public static void main(String[] args) {

    }

    public String solveEquation(String equation) {
        //拆分x系数x,num为数值部分
        int x = 0, nums = 0;
        int len = equation.length();
        char[] cs = equation.toCharArray();
        for (int i = 0, op = 1; i < len; ) {//op指当前数字是正还是负
            if (cs[i] == '+') {
                op = 1;
                i++;
            } else if (cs[i] == '-') {
                op = -1;
                i++;
            } else if (cs[i] == '=') {
                x *= -1;
                nums *= -1;
                op = 1;
                i++;
            } else {
                int j = i;
                while (j < len && cs[j] != '+' && cs[j] != '-' && cs[j] != '=') {
                    j++;
                }
                if (cs[j - 1] == 'x') {
                    //是否只有一个x
                    x += (i < j - 1 ? Integer.parseInt(equation.substring(i, j - 1)) : 1) * op;
                } else {
                    nums += Integer.parseInt(equation.substring(i, j)) * op;
                }
                i = j;
            }
        }
        if (x == 0) {
            return nums == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + nums / x;
    }
}
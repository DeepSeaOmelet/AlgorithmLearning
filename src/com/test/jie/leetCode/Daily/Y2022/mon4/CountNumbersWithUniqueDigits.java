package com.test.jie.leetCode.Daily.Y2022.mon4;

/**
 * 统计各位数字都不同的数字个数
 * <p>
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 * <p>
 * 提示：
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        CountNumbersWithUniqueDigits digits = new CountNumbersWithUniqueDigits();

        for (int i = 1; i <= 8; i++) {
            System.out.println(digits.countNumbersWithUniqueDigits(i));
        }
    }

    static int[] ans = new int[]{1, 10, 91, 739, 5275, 32491, 168571, 712891, 2345851};

    public int countNumbersWithUniqueDigits(int n) {
        return ans[n];
    }

    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10;
        int last = 9;
        for (int i = 2; i <= n; i++) {
            int cur = last * (10 - i + 1);
            ans += cur;
            last = cur;
        }
        return ans;
    }

}

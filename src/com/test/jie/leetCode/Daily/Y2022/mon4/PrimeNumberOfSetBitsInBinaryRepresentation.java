package com.test.jie.leetCode.Daily.Y2022.mon4;

/**
 * 二进制表示中质数个计算置位
 * <p>
 * 给你两个整数left和right ，在闭区间 [left, right]范围内，统计并返回 计算置位位数为质数 的整数个数。
 * 计算置位位数 就是二进制表示中 1 的个数。
 * 例如， 21的二进制表示10101有 3 个计算置位。
 * <p>
 * 示例 1：
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 * 示例 2：
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 * <p>
 * 提示：
 * 1 <= left <= right <= 106
 * 0 <= right - left <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(6) + ":" + Integer.toBinaryString(-6));
        System.out.println(6 & (-6));
    }

    static boolean[] hash = new boolean[40];

    static {
        //int 二进制不超过32位，可以将32内的打表
        int[] nums = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int i : nums) {
            hash[i] = true;
        }
    }

    //利用lowbit统计x共有多少位1，记为cnt
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int a = i, cnt = 0;
            while (a != 0 && ++cnt >= 0) {
                a -= (a & -a);
            }
            if (hash[cnt]){
                ans++;
            }
        }
        return ans;
    }

}

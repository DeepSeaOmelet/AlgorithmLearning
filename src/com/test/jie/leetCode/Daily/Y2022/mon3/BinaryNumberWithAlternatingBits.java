package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * 交替位二进制数
 * <p>
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {
        BinaryNumberWithAlternatingBits bits = new BinaryNumberWithAlternatingBits();
        System.out.println(bits.hasAlternatingBits(5));
        System.out.println(bits.hasAlternatingBits(7));
        System.out.println(bits.hasAlternatingBits(11));
        System.out.println(bits.hasAlternatingBits(10));//true
    }

    public boolean hasAlternatingBits2(int n) {
        boolean isOne = n % 2 == 1;
        while (n > 0) {
            if (isOne) {
                if ((n & 1) == 1) {
                    isOne = false;
                    n >>= 1;
                    continue;
                }
            } else if ((n & 1) == 0) {
                isOne = true;
                n >>= 1;
                continue;
            }
            return false;

        }
        return true;
    }

    public boolean hasAlternatingBits(int n) {
        int cur = -1;
        while (n != 0) {
            int u = n & 1;
            if ((cur ^ u) == 0) return false;
            cur = u;
            n >>= 1;
        }
        return true;
    }
    public boolean hasAlternatingBits3(int n) {
        int x = n ^ (n >> 1);
        return (x & (x + 1)) == 0;
    }

}

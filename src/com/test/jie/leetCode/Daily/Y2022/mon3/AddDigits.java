package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * 示例 1:
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于2 是一位数，所以返回 2。
 * 示例 1:
 * 输入: num = 0
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= num <= 231- 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddDigits {
    public static void main(String[] args) {
        AddDigits digits = new AddDigits();
        System.out.println(digits.addDigits(38));
        System.out.println(digits.addDigits(0));
    }

    //同余定理
    //数论中的重要概念。给定一个正整数m，如果两个整数a和b满足a-b能够被m整除，
    // 即(a-b)/m得到一个整数，那么就称整数a与b对模m同余，记作a≡b(mod m)。对模m同余是整数的一个等价关系。
    // 有s1 = 100a + 10b + c ,则有s2 = a + b + c，那么(s1-s2)=99a+9b => (99a+9b)/9=整数 => s1≡s2(mod 9)
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

    public int addDigits2(int num) {
        while (num > 9) {
            int cnt = 0;
            while (num > 0) {
                cnt += num % 10;
                num /= 10;
            }
            num = cnt;
        }
        return num;
    }
}

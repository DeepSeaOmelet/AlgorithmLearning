package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * 七进制数
 * <p>
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * 提示：
 * -107<= num <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Base7 {
    public static void main(String[] args) {
        Base7 base7 = new Base7();
//        System.out.println(base7.convertToBase7(100));
//        System.out.println(base7.convertToBase7(-7));
        System.out.println(base7.convertToBase7(-8));
    }

    public String convertToBase7_2(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 7 || num <= -7) {
            sb.append(Math.abs(num % 7));
            num /= 7;
        }
        return sb.reverse().insert(0, num).toString();
    }

    public String convertToBase7(int num) {
        boolean flag = num < 0;
        if (flag) {
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(num%7);
            num/=7;
        }while (num!=0);
        sb.reverse();
        return flag?"-"+ sb :sb.toString();
    }
}

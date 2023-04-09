package com.test.jie.leetCode.Daily.Y2022.mon4;

/**
 * 最大回文数乘积
 * 给定一个整数 n ，返回 可表示为两个 n位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 * <p>
 * 示例 1:
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * 示例 2:
 * 输入： n = 1
 * 输出： 9
 * <p>
 * 提示:
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-palindrome-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestPalindromeProduct {
    public static void main(String[] args) {
        LargestPalindromeProduct product = new LargestPalindromeProduct();
        System.out.println(product.largestPalindrome(2));
    }

    //利用回文串的特性，我们只需枚举回文串的前半部分即可（后半部分唯一确定），我们只要在枚举前半部分时按照「从大到小」进行，即可确保找到的第一个合法值为最大数，对于一个数位为 nn 的最大数为 10^n - 110
    //n
    // −1。
    //
    //作者：AC_OIer
    //链接：https://leetcode-cn.com/problems/largest-palindrome-product/solution/by-ac_oier-t8j7/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int max = (int) Math.pow(10, n) - 1;
        for (int i = max; i >= 0; i--) {
            //获取两乘积可得的最小回文
            long num = i;
            int c = i;
            while (c != 0) {
                num = num * 10 + (c % 10);
                c /= 10;
            }
            for (long j = max; j * j >= num; j--) {
                if (num % j == 0) {
                    return (int) (num % 1337);
                }
            }
        }
        return -1;
    }
}

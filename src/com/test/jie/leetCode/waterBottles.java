package com.test.jie.leetCode;

/**
 * 换酒问题
 * <p>
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 示例 1：
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 * 示例 2：
 * 输入：numBottles = 15, numExchange = 4
 * 输出：19
 * 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
 * 示例 3：
 * 输入：numBottles = 5, numExchange = 5
 * 输出：6
 * 示例 4：
 * 输入：numBottles = 2, numExchange = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <=numBottles <= 100
 * 2 <=numExchange <= 100
 */
public class waterBottles {
    public static void main(String[] args) {
        System.out.println(numWaterBottles(9, 3));//13
        System.out.println(numWaterBottles(15, 4));//19
        System.out.println(numWaterBottles(5, 5));//6
        System.out.println(numWaterBottles(2, 3));//2
    }

    /**
     * 模拟
     * @param numBottles
     * @param numExchange
     * @return
     */
    static int numWaterBottles2(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            ans += numBottles / numExchange;
            numBottles = numBottles / numExchange + numBottles % numExchange;
        }
        return ans + numBottles / numExchange;
    }

    /**
     * 数学
     * @param numBottles
     * @param numExchange
     * @return
     */
    static int numWaterBottles(int numBottles, int numExchange) {
        int cnt = numBottles / (numExchange - 1);
        return (numBottles % (numExchange - 1) == 0) ? numBottles + cnt - 1 : numBottles + cnt;
    }
}

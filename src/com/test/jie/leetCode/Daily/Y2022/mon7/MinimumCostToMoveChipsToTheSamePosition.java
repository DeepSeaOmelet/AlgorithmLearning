package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.Arrays;
import java.util.Random;

/**
 * 2022/07/09
 * <p>
 * 1217. 玩筹码
 * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
 * <p>
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
 * <p>
 * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
 * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 * <p>
 * 示例 1：
 * 输入：position = [1,2,3]
 * 输出：1
 * 解释：第一步:将位置3的筹码移动到位置1，成本为0。
 * 第二步:将位置2的筹码移动到位置1，成本= 1。
 * 总成本是1。
 * 示例 2：
 * 输入：position = [2,2,2,3,3]
 * 输出：2
 * 解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
 * 示例 3:
 * 输入：position = [1,1000000000]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 */
public class MinimumCostToMoveChipsToTheSamePosition {
    public static void main(String[] args) {
        MinimumCostToMoveChipsToTheSamePosition cost = new MinimumCostToMoveChipsToTheSamePosition();
//        System.out.println(cost.minCostToMoveChips(new int[]{2, 2, 2, 3, 3}));
        Random ran = new Random();
        for (int k = 0; k < 10; k++) {
            int len = ran.nextInt(20) + 1;
            int[] ints = new int[len];
            for (int i = 0; i < len; i++) {
                ints[i] = ran.nextInt(100);
            }
            System.out.println(Arrays.toString(ints));
            System.out.println(cost.minCostToMoveChips(ints));
        }
    }

    public int minCostToMoveChips(int[] ps) {
        int len = ps.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int pos = ps[i];
            int count = 0;
            for (int j = 0; j < len; j++) {
                int cur = ps[j];
                count += Math.abs(pos - cur) % 2;
            }
            ans = Math.min(count, ans);
        }
        return ans;
    }

    public int minCostToMoveChip2(int[] ps) {
        int a = 0, b = 0;
        for (int p : ps) {
            if (p % 2 == 0) a++;
            else b++;
        }
        return Math.min(a, b);
    }
}
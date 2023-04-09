package com.test.jie.leetCode.Daily.Y2022.mon6;

/**
 * 473. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
 * 你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * <p>
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * <p>
 * 示例 1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * <p>
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 * <p>
 * 提示:
 * <p>
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 */
public class MatchsticksToSquare {
    public static void main(String[] args) {
        MatchsticksToSquare toSquare = new MatchsticksToSquare();
        System.out.println(toSquare.makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(toSquare.makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));
    }

    public boolean makesquare(int[] matchsticks) {
        int cnt = 0;
        for (int mt : matchsticks) {
            cnt += mt;
        }
        cnt /= 4;
        int cur = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            if (cur + matchsticks[i] < cnt) {
                cur += matchsticks[i];
            }else if (cur + matchsticks[i] == cnt){
                cur = 0;
            }else {
                return false;
            }
        }
        return true;
    }
}

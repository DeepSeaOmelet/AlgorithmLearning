package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.ArrayList;
import java.util.List;

/**
 * 适合打劫银行的日子
 * <p>
 * 你和一群强盗准备打劫银行。给你一个下标从 0开始的整数数组security，其中security[i]是第 i天执勤警卫的数量。日子从 0开始编号。同时给你一个整数time。
 * <p>
 * 如果第 i天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * <p>
 * 第 i天前和后都分别至少有 time天。
 * 第 i天前连续 time天警卫数目都是非递增的。
 * 第 i天后连续 time天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
 * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * <p>
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0开始）。返回的日子可以 任意顺序排列。
 * <p>
 * 示例 1：
 * 输入：security = [5,3,3,3,5,6,2], time = 2
 * 输出：[2,3]
 * 解释：
 * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
 * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
 * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
 * 示例 2：
 * 输入：security = [1,1,1,1,1], time = 0
 * 输出：[0,1,2,3,4]
 * 解释：
 * 因为 time 等于 0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
 * 示例 3：
 * 输入：security = [1,2,3,4,5,6], time = 2
 * 输出：[]
 * 解释：
 * 没有任何一天的前 2 天警卫数目是非递增的。
 * 所以没有适合打劫银行的日子，返回空数组。
 * 示例 4：
 * 输入：security = [1], time = 5
 * 输出：[]
 * 解释：
 * 没有日子前面和后面有 5 天时间。
 * 所以没有适合打劫银行的日子，返回空数组。
 * <p>
 * 提示：
 * 1 <= security.length <= 105
 * 0 <= security[i], time <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindGoodDaysToRobTheBank {
    public static void main(String[] args) {
        FindGoodDaysToRobTheBank palindrome = new FindGoodDaysToRobTheBank();
        System.out.println(palindrome.goodDaysToRobBank(new int[]{1, 1, 1, 1, 1}, 0));
        System.out.println(palindrome.goodDaysToRobBank(new int[]{5, 3, 3, 3, 5, 6, 2}, 2));
        System.out.println(palindrome.goodDaysToRobBank(new int[]{1, 2, 3, 4, 5, 6}, 2));
        System.out.println(palindrome.goodDaysToRobBank(new int[]{1}, 5));
        System.out.println(palindrome.goodDaysToRobBank(new int[]{1,2,5,4,1,0,2,4,5,3,1,2,4,3,2,4,8}, 2));//[5,10,14]

    }

    //前缀和
    //需要满足左非递增，右非递减
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] g = new int[n];
        //计算i相对i-1是递增
        for (int i = 1; i < n; i++) {
            if (security[i] == security[i - 1]) {
                continue;
            }
            g[i] = security[i] > security[i - 1] ? 1 : -1;
        }
        //计算递增/递减的次数，其他不变
        int[] a = new int[n+1], b = new int[n+1];
        for (int i = 1; i <= n; i++) {
            a[i] = a[i - 1] + (g[i - 1] == 1 ? 1 : 0);
        }
        for (int i = 1; i <= n; i++) {
            b[i] = b[i - 1] + (g[i - 1] == -1 ? 1 : 0);
        }
        List<Integer> ans = new ArrayList<>();
        //前time个，后time个，
        for (int i = time; i < n - time; i++) {
            int c1 = a[i + 1] - a[i + 1 - time];//非递增
            int c2 = b[i + 1 + time] - b[i + 1];//非递减
            if (c1 == 0 && c2 == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.Arrays;

/**
 * 蜡烛之间的盘子
 * <p>
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0开始的字符串s，它只包含字符'*' 和'|'，其中'*'表示一个 盘子，'|'表示一支蜡烛。
 * <p>
 * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示 子字符串s[lefti...righti]（包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中在 两支蜡烛之间的盘子的 数目。如果一个盘子在 子字符串中左边和右边 都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
 * <p>
 * 比方说，s = "||**||**|*"，查询[3, 8]，表示的是子字符串"*||**|"。子字符串中在两支蜡烛之间的盘子数目为2，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组answer，其中answer[i]是第i个查询的答案。
 * <p>
 * 示例 1:
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 * <p>
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 * <p>
 * 提示：
 * <p>
 * 3 <= s.length <= 105
 * s只包含字符'*' 和'|'。
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 * 通过次数13,805提交次数32,759
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plates-between-candles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlatesBetweenCandles {
    public static void main(String[] args) {
        PlatesBetweenCandles candles = new PlatesBetweenCandles();
        System.out.println(Arrays.toString(candles.platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
//        System.out.println(Arrays.toString(candles.platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
    }

    //前缀和
    public int[] platesBetweenCandles(String s, int[][] qs) {
        char[] cs = s.toCharArray();
        int n = cs.length, m = qs.length;
        int[] l = new int[n], r = new int[n];
        int[] sum = new int[n + 1];
        for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
            if (cs[i] == '|') p = i;
            if (cs[j] == '|') q = j;
            l[i] = p;
            r[j] = q;
            sum[i + 1] = sum[i] + (cs[i] == '*' ? 1 : 0);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = qs[i][0], b = qs[i][1];
            int c = r[a], d = l[b];
            if (c != -1 && c <= d) ans[i] = sum[d + 1] - sum[c];
        }
        return ans;
    }

    public int[] platesBetweenCandles2(String s, int[][] queries) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int m = queries.length;
        int[] sum = new int[n + 1];
        int[] l = new int[n], r = new int[n];
        for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
            if (cs[i] == '|') p = i;//左到右
            if (cs[j] == '|') q = j;//右到左
            l[i] = p;
            r[j] = q;
            sum[i + 1] = sum[i] + (cs[i] != '*' ? 0 : 1);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int c = r[a],d=l[b];
            if (c!=-1&&c<=d) {
                ans[i]=sum[d+1]-sum[c];
            }
        }
        return ans;
    }
}

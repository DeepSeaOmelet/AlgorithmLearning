package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.HashMap;
import java.util.Map;

/**
 * 2022/07/09
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * - n >= 3
 * - 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * <p>
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），
 * 而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 * <p>
 * 示例 1：
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 * <p>
 * 提示：
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 */
public class LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {

    }

    public int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        //定义 f[i][j] 为使用 arr[i] 为斐波那契数列的最后一位，使用 arr[j] 为倒数第二位（即 arr[i] 的前一位）时的 最长数列长度。
        int[][] cnt = new int[len][len];
        //arr的严格单调递增，不失一般性考虑 f[i][j]该如何计算，首先根据斐波那契数列的定义，我们可以直接算得 arr[j] 前一位的值为 arr[i] - arr[j]，
        // 而快速得知 arr[i] - arr[j] 值的坐标 t，使用「哈希表」对坐标进行转存，若坐标 t 存在，并且符合 t < j，说明此时至少凑成了长度为 3 的斐波那契数列，
        // 同时结合状态定义，可以使用 f[j][t] 来更新 f[i][j]，即有状态转移方程：
        //f[i][j]=max(3,f[j][t]+1)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }
        //同时，当我们「从小到大」枚举 i，并且「从大到小」枚举 j 时，我们可以进行如下的剪枝操作：
        //可行性剪枝：当出现 arr[i] - arr[j] >= arr[j]，说明即使存在值为 arr[i] - arr[j] 的下标 t，根据 arr 单调递增性质，也不满足 t < j < i 的要求，
        // 且继续枚举更小的 j，仍然有 arr[i] - arr[j] >= arr[j]，仍不合法，直接 break 掉当前枚举 j 的搜索分支；
        //最优性剪枝：假设当前最大长度为 ans，只有当 j + 2 > ans，我们才有必要往下搜索，j + 2 的含义为以 arr[j] 为斐波那契数列倒数第二个数时的理论最大长度。
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0 && j + 2 > ans; j--) {
                //可行性剪枝
                if (arr[i] - arr[j] >= arr[j]) break;
                //递推公式
                //找出t
                int t = map.getOrDefault(arr[i] - arr[j], -1);
                if (t == -1) {
                    //t不存在arr数组中
                    continue;
                }
                cnt[i][j] = Math.max(3, cnt[j][t] + 1);
                ans = Math.max(ans,cnt[i][j]);
            }
        }
        return ans;
    }
}
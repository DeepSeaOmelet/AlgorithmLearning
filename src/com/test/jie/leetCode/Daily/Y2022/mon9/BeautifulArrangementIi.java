package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.Arrays;

/**
 * 2022/09/08
 * 667. 优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 * 示例 2：
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 * <p>
 * 提示：
 * 1 <= k < n <= 104
 */
public class BeautifulArrangementIi {
    public static void main(String[] args) {
        BeautifulArrangementIi b = new BeautifulArrangementIi();
        System.out.println(Arrays.toString(b.constructArray(10, 4)));
        System.out.println(Arrays.toString(b.constructArray(10, 5)));
        System.out.println(Arrays.toString(b.constructArray(3, 2)));
        System.out.println(Arrays.toString(b.constructArray(3, 1)));

    }

    //[1, n, 2, n - 1, 3, ...]
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int t = n - k - 1;
        for (int i = 0; i < t; i++) {
            ans[i] = i + 1;
        }
        //之后是[1, n, 2, n - 1, 3, ...]的构造，所以a=n-k,b=n
        for (int i = t, a = n - k, b = n; i < n; ) {
            ans[i++] = a++;
            if (i < n) ans[i++] = b--;
        }
        return ans;
    }

    public int[] constructArray2(int n, int k) {
        int[] ans = new int[n];
        boolean isBig = false;
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (--k >= 0) {
                if (!isBig) {
                    ans[i] = i / 2 + 1;
                } else {
                    ans[i] = n - (i / 2);
                }
                isBig = !isBig;
            } else {
                if (k == -1) {
                    ans[i] = isBig ? i / 2 + 2 : n - (i / 2);
                    add = isBig ? 1 : -1;
                } else {
                    ans[i] = ans[i - 1] + add;
                }
            }
        }
        return ans;
    }
}
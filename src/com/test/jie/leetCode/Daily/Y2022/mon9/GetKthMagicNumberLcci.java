package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.*;

/**
 * 2022/09/28
 * 面试题 17.09. 第 k 个数
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
 * * 而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 5
 * <p>
 * 输出: 9
 */
public class GetKthMagicNumberLcci {
    public static void main(String[] args) {
        GetKthMagicNumberLcci g = new GetKthMagicNumberLcci();
        System.out.println(g.getKthMagicNumber(5));
        System.out.println(Arrays.toString(g.solution(new int[]{2, 6, 3, 8, 10, 9})));
    }

    public int getKthMagicNumber(int k) {
        int[] nums = new int[]{3, 5, 7};
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        Set<Long> set = new HashSet<>();
        set.add(1L);
        while (!queue.isEmpty()) {
            long cur = queue.poll();
            if (--k == 0) return (int) cur;
            for (int i : nums) {
                if (!set.contains(i * cur)) {
                    set.add(i * cur);
                    queue.add(i * cur);
                }
            }
        }
        return -1;
    }

    public int getKthMagicNumber2(int k) {
        int[] ans = new int[k + 1];
        ans[1] = 1;
        for (int i3 = 1, i5 = 1, i7 = 1, idx = 2; idx <= k; idx++) {
            int a = ans[i3] * 3, b = ans[i5] * 5, c = ans[i7] * 7;
            int min = Math.min(a, Math.min(b, c));
            if (min == a) i3++;
            if (min == b) i5++;
            if (min == c) i7++;
            ans[idx] = min;
        }
        return ans[k];
    }

    //数组元素随机无序的，要求打印出所有元素右边第一个大于该元素的值。
    /*
     *示例：
     *给定数组：[2，6，3，8，10，9]
     *返回数组：[6，8，8，10，-1，-1]
     *n
     *
     */
    public int[] solution(int[] arr) {
        if (arr==null || arr.length==0) return null;
        int len = arr.length;
        int[] ans = new int[len];
        Deque<Integer> d = new ArrayDeque<Integer>();
        for (int i = 0; i < len; i++) {
            ans[i]=-1;
            while (!d.isEmpty() && arr[d.peekLast()]<arr[i]){
                int idx = d.pollLast();
                ans[idx]=arr[i];
            }
            d.push(i);
        }
        return ans;

    }
}
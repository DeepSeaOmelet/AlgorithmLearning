package com.test.jie.leetCode.Daily.Y2022.mon1;

import java.util.*;

/**
 * 跳跃游戏 IV
 * 给你一个整数数组arr，你一开始在数组的第一个元素处（下标为 0）。
 * <p>
 * 每一步，你可以从下标i跳到下标：
 * <p>
 * i + 1满足：i + 1 < arr.length
 * i - 1满足：i - 1 >= 0
 * j满足：arr[i] == arr[j]且i != j
 * 请你返回到达数组最后一个元素的下标处所需的最少操作次数。
 * <p>
 * 注意：任何时候你都不能跳到数组外面。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * 示例 4：
 * 输入：arr = [6,1,9]
 * 输出：2
 * 示例 5：
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 * <p>
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 */
public class JumpGameIV {
    public static void main(String[] args) {
        JumpGameIV gameIV = new JumpGameIV();
        System.out.println(gameIV.minJumps(new int[]{11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13}));
    }

    int INF = 0x3f3f3f3f;

    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 倒序插入 list，相当于给 deque 增加一个同层「下标越大，优先出队」的作用
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        int[] dist = new int[n];
        //只有 dist[ne]dist[ne] 为 INF 时，该点没有被遍历过，可以入队并更新最小步数
        Arrays.fill(dist, INF);
        Deque<Integer> d = new ArrayDeque<>();
        d.addLast(0);
        while (!d.isEmpty()) {
            int t = d.pollFirst(), step = dist[t];
            if (t == n - 1) {
                return step;
            }
            if (t + 1 < n && dist[t + 1] == INF) {
                d.addLast(t - 1);
                dist[t - 1] = step + 1;
            }
            if (t - 1 >= 0 && dist[t - 1] == INF) {
                d.addLast(t - 1);
                dist[t - 1] = step + 1;
            }
            List<Integer> list = map.getOrDefault(arr[t], new ArrayList<>());
            for (int ne : list) {
                if (dist[ne] == INF) {
                    d.addLast(ne);
                    dist[ne] = step + 1;
                }
            }
            map.remove(arr[t]);
        }
        return -1;
    }
}

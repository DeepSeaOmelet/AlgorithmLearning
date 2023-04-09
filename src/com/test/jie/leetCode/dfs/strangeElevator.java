package com.test.jie.leetCode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 奇怪的电梯
 * 输入
 * 第一行为三个用空格隔开的正整数，表示 N, A, B
 * N是层数，A是所在层数，B是目标层数
 * 第二行为 N 个用空格隔开的非负整数，表示 K_i
 */
public class strangeElevator {
    public static void main(String[] args) {
        strangeElevator strangeElevator = new strangeElevator();
        System.out.println(strangeElevator.bfs(new int[]{5, 1, 5}, new int[]{3, 3, 1, 2, 5}));
    }

    int bfs(int[] params, int[] ele) {
        Deque<int[]> d = new ArrayDeque<>();
        d.addLast(new int[]{params[1], 0});
        int target = params[2];
        int high = params[0];
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
//            System.out.println(poll[0]);
            if (poll[0] == target) {
                return poll[1];
            }
            int cur = poll[0];
            if (cur + ele[cur-1] <= high) {
                d.addLast(new int[]{cur + ele[cur-1], poll[1] + 1});
            }
            if (cur - ele[cur-1] >= 0) {
                d.addLast(new int[]{cur - ele[cur-1], poll[1] + 1});
            }
        }
        return -1;
    }
}

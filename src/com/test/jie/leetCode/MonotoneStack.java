package com.test.jie.leetCode;

import java.util.*;

//单调栈
public class MonotoneStack {
}

/**
 * 739. 每日温度
 * 中等
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
class MonotoneStackSolution1 {
    public static void main(String[] args) {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        s.addLast(4);
        System.out.println(s);
        s.pollLast();
        System.out.println(s.peekLast());
        System.out.println(s);
    }

    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        //递增 单调栈
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addLast(0);
        for (int i = 1; i < n; i++) {
            while (!s.isEmpty() && temperatures[i] > temperatures[s.peekLast()]) {
                ans[s.peekLast()] = i - s.peekLast();
                s.pollLast();
            }
            s.addLast(i);
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        //递增 单调栈
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addLast(0);
        for (int i = 1; i < n; i++) {
            if (temperatures[s.peekLast()] > temperatures[i]) {
                s.addLast(i);
            } else if (temperatures[s.peekLast()] == temperatures[i]) {
                s.addLast(i);
            } else {
                while (!s.isEmpty() && temperatures[i] > temperatures[s.peekLast()]) {
                    ans[s.peekLast()] = i - s.peekLast();
                    s.pollLast();
                }
                s.addLast(i);
            }
        }
        return ans;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        //超时
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }
}
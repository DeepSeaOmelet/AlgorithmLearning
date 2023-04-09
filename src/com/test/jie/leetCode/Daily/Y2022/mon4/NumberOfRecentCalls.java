package com.test.jie.leetCode.Daily.Y2022.mon4;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 933. 最近的请求次数 简单
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * <p>
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，
 * 并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * <p>
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 * <p>
 * 提示：
 * 1 <= t <= 109
 * 保证每次对 ping 调用所使用的 t 值都 严格递增
 * 至多调用 ping 方法 104 次
 */
public class NumberOfRecentCalls {
    public static void main(String[] args) {
        NumberOfRecentCalls calls = new NumberOfRecentCalls();
        boolean solution = calls.Solution();
    }

    public boolean Solution() {
        RecentCounter counter = new RecentCounter();

        return true;
    }

    class RecentCounter {
        private final int RANGE = 3000;
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<Integer>();
        }

        public int ping(int t) {
            while (!queue.isEmpty() && queue.peek()<t-RANGE){
                queue.poll();
            }
            queue.offer(t);
            return queue.size();
        }
    }
}

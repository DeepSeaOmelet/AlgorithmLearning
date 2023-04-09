package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2022/10/22
 * 1235. 规划兼职工作
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 * <p>
 * 示例 1：
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 * 示例 2：
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 * 示例 3：
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 * <p>
 * 提示：
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 */
public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {

    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        //初始化数据
        int n = startTime.length;
        PartTime[] work = new PartTime[n];
        for (int i = 0; i < n; i++) {
            work[i] = new PartTime(startTime[i], endTime[i], profit[i]);
        }
        //根据endTime排序
        Arrays.sort(work);
        //dp转移方程
        int[] dp = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            int pre = 0;
            //找出与当前兼职接上的上一份工作
            for (int j = i - 1; j >= 0; j--) {
                if (work[j].endTime <= work[i - 1].startTime) {
                    pre = j + 1;
                    break;
                }
            }
            //和上一个最大总工资比较
            dp[i] = Math.max(dp[i - 1], dp[pre] + work[i - 1].profit);
        }
        return dp[n];
    }

    class PartTime implements Comparable<PartTime> {
        int startTime, endTime, profit;

        public PartTime(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        @Override
        public int compareTo(PartTime o) {
            return Integer.compare(this.endTime, o.endTime);
        }
    }

    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        //序列dp+二分
        int n = startTime.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{startTime[i], endTime[i], profit[i]});
        }
        Collections.sort(list, (a, b) -> a[1] - b[1]);
        //转移方程,记录当前总收入
        int[] f = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            int[] info = list.get(i - 1);
            int a = info[0];
            int b = info[1];
            int c = info[2];
            f[i] = Math.max(f[i - 1], c);
            // 向前寻找“最近的”“已完成的"兼职工作
            int l = 0;
            int r = i - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid)[1] <= a) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (list.get(r)[1] <= a) {
                f[i] = Math.max(f[i], f[r + 1] + c);
            }
        }
        return f[n];
    }
}
package com.test.jie.leetCode.Daily.Y2023;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.Arrays;

public class mon4 {
    class Solution17 {
        //2409. 统计共同度过的日子数
        //简单    前缀树
        //Alice 和 Bob 计划分别去罗马开会。
        //给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
        //请你返回 Alice和 Bob 同时在罗马的天数。
        //你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
        //
        //示例 1：
        //输入：arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
        //输出：3
        //解释：Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
        //示例 2：
        //输入：arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
        //输出：0
        //解释：Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
        //
        //提示：
        //所有日期的格式均为 "MM-DD" 。
        //Alice 和 Bob 的到达日期都 早于或等于 他们的离开日期。
        //题目测试用例所给出的日期均为 非闰年 的有效日期。
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int[] daySum = new int[monthDay.length + 1];
            for (int i = 0; i < monthDay.length; i++) {
                daySum[i + 1] = daySum[i] + monthDay[i];
            }
            System.out.println(Arrays.toString(daySum));
            int arriveAliceDay = transferDateToDay(daySum, arriveAlice);
            int leaveAliceDay = transferDateToDay(daySum, leaveAlice);
            int arriveBobDay = transferDateToDay(daySum, arriveBob);
            int leaveBobDay = transferDateToDay(daySum, leaveBob);
            return Math.max(Math.min(leaveAliceDay, leaveBobDay) - Math.max(arriveAliceDay, arriveBobDay) + 1, 0);
        }

        private int transferDateToDay(int[] daySum, String date) {
            int mon = Integer.parseInt(date.substring(0, 2));
            int day = Integer.parseInt(date.substring(3));
            System.out.println(daySum[mon - 1] + day);
            return daySum[mon - 1] + day;
        }
    }

    class Solution18 {
        //1026. 节点与其祖先之间的最大差值
        //中等
        //给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
        //（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
        //示例 1：
        //输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
        //输出：7
        //解释：
        //我们有大量的节点与其祖先的差值，其中一些如下：
        //|8 - 3| = 5
        //|3 - 7| = 4
        //|8 - 1| = 7
        //|10 - 13| = 3
        //在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
        //示例 2：
        //输入：root = [1,null,2,null,0,3]
        //输出：3
        //
        //提示：
        //树中的节点数在 2 到 5000 之间。
        //0 <= Node.val <= 105
        int ans = 0;

        public int maxAncestorDiff(TreeNode root) {
            dfs(root, root.val, root.val);
            return ans;
        }

        public void dfs(TreeNode root, int max, int min) {
            if (root == null) {
                ans = Math.max(ans, max - min);
                return;
            }
            max = Math.max(root.val, max);
            min = Math.min(root.val, min);
            dfs(root.left, max, min);
            dfs(root.right, max, min);
        }
    }
}

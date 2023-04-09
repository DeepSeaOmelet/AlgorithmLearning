package com.test.jie.leetCode.Daily.Y2022.mon2;

/**
 * 最多可达成的换楼请求数目
 *
 * 我们有n栋楼，编号从0到n - 1。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 *
 * 给你一个数组 requests，其中requests[i] = [fromi, toi]，表示一个员工请求从编号为fromi的楼搬到编号为toi的楼。
 *
 * 一开始所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0。
 * 意思是每栋楼 离开的员工数目 等于该楼 搬入的员工数数目。比方说n = 3且两个员工要离开楼0，一个员工要离开楼1，一个员工要离开楼 2，
 * 如果该请求列表可行，应该要有两个员工搬入楼0，一个员工搬入楼1，一个员工搬入楼2。
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 *
 * 示例 1：
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 * 示例 2：
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 * 示例 3：
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= requests.length <= 16
 * requests[i].length == 2
 * 0 <= fromi, toi < n
 *
 * 困难
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests
 */
public class MaximumNumberOfAchievableTransferRequests {
    public static void main(String[] args) {
        MaximumNumberOfAchievableTransferRequests requests = new MaximumNumberOfAchievableTransferRequests();
//        System.out.println(requests.maximumRequests(5, new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}}));
//        System.out.println(requests.maximumRequests(3, new int[][]{{0, 0}, {1, 2}, {2, 1}}));
//        System.out.println(requests.maximumRequests(4, new int[][]{{0, 3}, {3, 1}, {1, 2}, {2, 0}}));
//        System.out.println(requests.maximumRequests(3, new int[][]{{0, 3}, {3, 1}, {1, 2}, {2, 0}}));
        System.out.println(requests.maximumRequests(3, new int[][]{{0,0},{1,1},{0,0},{2,0},{2,2},{1,1},{2,1},{0,1},{0,1}}));
    }

    /**
     * 二进制枚举
     * 为了方便，我们令 requests 的长度为 m。
     * 数据范围很小，n 的范围为 20，而 m 的范围为 16。
     * 根据每个 requests[i] 是否选择与否，共有 2^m种状态（不超过 70000 种状态）。
     * 我们可以采用「二进制枚举」的思路来求解，使用二进制数 state 来表示对 requests[i] 的选择情况，
     * 当 state 的第 k 位为 1，代表 requests[k] 被选择。
     * 我们枚举所有的 state 并进行合法性检查，从中选择出包含请求数的最多（二进制表示中包含 1 个数最多）的合法 state，其包含的请求数量即是答案。
     * 其中统计 state 中 1 的个数可以使用 lowbit，复杂度为 O(m)，
     * 判断合法性则直接模拟即可（统计每座建筑的进出数量，最后判定进出数不相等的建筑数量是为 0），复杂度为O(m)，整体计算量为不超过 2*10^6，可以过。
     *
     */
    int[][] rs;
    public int maximumRequests(int n, int[][] requests) {
        rs = requests;
        int m = rs.length, ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            int cnt = getCnt(i);
            if (cnt <= ans) continue;
            if (check(i)) ans = cnt;
        }
        return ans;
    }
    boolean check(int s) {
        int[] cnt = new int[20];
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (((s >> i) & 1) == 1) {
                int a = rs[i][0], b = rs[i][1];
                if (++cnt[a] == 1) sum++;
                if (--cnt[b] == 0) sum--;
            }
        }
        return sum == 0;
    }
    int getCnt(int s) {
        int ans = 0;
        for (int i = s; i > 0; i -= (i & -i)) ans++;
        return ans;
    }

}

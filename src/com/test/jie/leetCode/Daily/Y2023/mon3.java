package com.test.jie.leetCode.Daily.Y2023;

import java.awt.geom.Area;
import java.util.HashMap;
import java.util.Map;

public class mon3 {
    /**
     * [前缀和]
     * 1590. 使数组和能被 P 整除
     * 中等
     * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
     * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
     * 子数组 定义为原数组中连续的一组元素。
     * <p>
     * 示例 1：
     * 输入：nums = [3,1,4,2], p = 6
     * 输出：1
     * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
     * 示例 2：
     * 输入：nums = [6,3,5,2], p = 9
     * 输出：2
     * 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
     * 示例 3：
     * 输入：nums = [1,2,3], p = 3
     * 输出：0
     * 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
     * 示例  4：
     * 输入：nums = [1,2,3], p = 7
     * 输出：-1
     * 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
     * 示例 5：
     * 输入：nums = [1000000000,1000000000,1000000000], p = 3
     * 输出：0
     * <p>
     * 提示：
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 109
     * 1 <= p <= 109
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length, ans = n;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = (s[i] + nums[i]) % p;
        }
        int x = s[n];
        if (x == 0) return 0;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            last.put(s[i], i);
            int j = last.getOrDefault((s[i] - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }

    /**
     * 2383. 赢得比赛需要的最少训练时长
     * 简单
     * 你正在参加一场比赛，给你两个 正 整数 initialEnergy 和 initialExperience 分别表示你的初始精力和初始经验。
     * 另给你两个下标从 0 开始的整数数组 energy 和 experience，长度均为 n 。
     * 你将会 依次 对上 n 个对手。第 i 个对手的精力和经验分别用 energy[i] 和 experience[i] 表示。当你对上对手时，需要在经验和精力上都 严格 超过对手才能击败他们，然后在可能的情况下继续对上下一个对手。
     * 击败第 i 个对手会使你的经验 增加 experience[i]，但会将你的精力 减少  energy[i] 。
     * 在开始比赛前，你可以训练几个小时。每训练一个小时，你可以选择将增加经验增加 1 或者 将精力增加 1 。
     * 返回击败全部 n 个对手需要训练的 最少 小时数目。
     * <p>
     * 示例 1：
     * 输入：initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
     * 输出：8
     * 解释：在 6 小时训练后，你可以将精力提高到 11 ，并且再训练 2 个小时将经验提高到 5 。
     * 按以下顺序与对手比赛：
     * - 你的精力与经验都超过第 0 个对手，所以获胜。
     * 精力变为：11 - 1 = 10 ，经验变为：5 + 2 = 7 。
     * - 你的精力与经验都超过第 1 个对手，所以获胜。
     * 精力变为：10 - 4 = 6 ，经验变为：7 + 6 = 13 。
     * - 你的精力与经验都超过第 2 个对手，所以获胜。
     * 精力变为：6 - 3 = 3 ，经验变为：13 + 3 = 16 。
     * - 你的精力与经验都超过第 3 个对手，所以获胜。
     * 精力变为：3 - 2 = 1 ，经验变为：16 + 1 = 17 。
     * 在比赛前进行了 8 小时训练，所以返回 8 。
     * 可以证明不存在更小的答案。
     * 示例 2：
     * 输入：initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
     * 输出：0
     * 解释：你不需要额外的精力和经验就可以赢得比赛，所以返回 0 。
     * <p>
     * 提示：
     * n == energy.length == experience.length
     * 1 <= n <= 100
     * 1 <= initialEnergy, initialExperience, energy[i], experience[i] <= 100
     *
     * @param initialEnergy
     * @param initialExperience
     * @param energy
     * @param experience
     * @return
     */
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int curEnergy = initialEnergy;
        int curExperience = initialExperience;
        int ans = 0;
        for (int i = 0; i < energy.length; i++) {
            curEnergy -= energy[i];
            if (curExperience <= experience[i]) {
                int t = experience[i] - curExperience + 1;
                ans += t;
                curExperience += t;
            }
            curExperience += experience[i];

        }
        return curEnergy > 0 ? ans : ans - curEnergy + 1;
    }

    /**
     * 1615. 最大网络秩
     * 中等
     * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
     * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
     * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
     * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
     * <p>
     * 示例 1：
     * 输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
     * 输出：4
     * 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
     * 示例 2：
     * 输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
     * 输出：5
     * 解释：共有 5 条道路与城市 1 或 2 相连。
     * 示例 3：
     * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
     * 输出：5
     * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
     * <p>
     * 提示：
     * 2 <= n <= 100
     * 0 <= roads.length <= n * (n - 1) / 2
     * roads[i].length == 2
     * 0 <= ai, bi <= n-1
     * ai != bi
     * 每对城市之间 最多只有一条 道路相连
     *
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] cnt = new int[n];
        boolean[][] connect = new boolean[n][n];
        for (int i = 0; i < roads.length; i++) {
            cnt[roads[i][0]]++;
            cnt[roads[i][1]]++;
            connect[roads[i][1]][roads[i][0]] = true;
            connect[roads[i][0]][roads[i][1]] = true;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.max(cnt[i] + cnt[j] + ((!connect[i][j]) ? 0 : -1), ans);
            }
        }
        return ans;
    }

    /**
     * 2488. 统计中位数为 K 的子数组*
     * https://leetcode.cn/problems/count-subarrays-with-median-k/description/*
     *
     * @param nums
     * @param k
     * @return
     */
    public int countSubarrays(int[] nums, int k) {
        //「左侧小于 − 左侧大于 == 右侧大于 − 右侧小于」
        int pos = 0, n = nums.length;
        while (nums[pos] != k) {
            pos++;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        //向左遍历
        for (int i = pos - 1, x = 0; i >= 0; i--) {
            x += nums[i] < k ? 1 : -1;
            cnt.merge(x, 1, Integer::sum);
        }
        //这里的-1是保证如果子数组长度是偶数，右边的数量可以比左边的数量多一
        int ans = cnt.get(0) + cnt.getOrDefault(-1, 0);
        //向右遍历
        for (int i = pos + 1, x = 0; i < n; i++) {
            x += nums[i] < k ? -1 : 1;
            ans += cnt.getOrDefault(x, 0) + cnt.getOrDefault(x - 1, 0);
        }
        return ans;
    }
}

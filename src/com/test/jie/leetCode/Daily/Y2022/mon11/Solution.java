package com.test.jie.leetCode.Daily.Y2022.mon11;

import com.test.jie.leetCode.tool.ListNode;
import com.test.jie.leetCode.tool.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(1,new ArrayList<>());
        List<Integer> list = map.get(1);
        list.add(4);
        list.add(4);
        list.add(4);
        System.out.println(map);
    }

    /**
     * 895. 最大频率栈
     * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
     * 实现 FreqStack 类:
     * FreqStack() 构造一个空的堆栈。
     * void push(int val) 将一个整数 val 压入栈顶。
     * int pop() 删除并返回堆栈中出现频率最高的元素。
     * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
     * 示例 1：
     * 输入：
     * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
     * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
     * 输出：[null,null,null,null,null,null,null,5,7,5,4]
     * 解释：
     * FreqStack = new FreqStack();
     * freqStack.push (5);//堆栈为 [5]
     * freqStack.push (7);//堆栈是 [5,7]
     * freqStack.push (5);//堆栈是 [5,7,5]
     * freqStack.push (7);//堆栈是 [5,7,5,7]
     * freqStack.push (4);//堆栈是 [5,7,5,7,4]
     * freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
     * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
     * freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
     * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
     * freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
     * 提示：
     * 0 <= val <= 109
     * push 和 pop 的操作数不大于 2 * 104。
     * 输入保证在调用 pop 之前堆栈中至少有一个元素。
     */
    class FreqStack {
        Map<Integer, Integer> cnts = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = 0;

        public FreqStack() {

        }

        public void push(int val) {
            //更新map，cnts，max
            cnts.put(val, cnts.getOrDefault(val, 0) + 1);
            int cnt = cnts.get(val);
            List<Integer> list = map.getOrDefault(cnt, new ArrayList<>());
            list.add(val);
            map.put(cnt, list);
            max = Math.max(max, cnt);
        }

        public int pop() {
            //更新map，cnts，max
            //map
            List<Integer> list = map.get(max);
            int popVal = list.remove(list.size() - 1);
            //cnts
            cnts.put(popVal, max - 1);
            //是否需要更新max
            if (list.isEmpty()) {
                max--;
            }
            return popVal;
        }
    }

    /**
     * 1758. 生成交替二进制字符串的最少操作数
     * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
     * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
     * 返回使 s 变成 交替字符串 所需的 最少 操作数。
     * 示例 1：
     * 输入：s = "0100"
     * 输出：1
     * 解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
     * 示例 2：
     * 输入：s = "10"
     * 输出：0
     * 解释：s 已经是交替字符串。
     * 示例 3：
     * 输入：s = "1111"
     * 输出：2
     * 解释：需要 2 步操作得到 "0101" 或 "1010" 。
     * 提示：
     * 1 <= s.length <= 104
     * s[i] 是 '0' 或 '1'
     */
    public int minOperations(String s) {
        //1010,0101
        int len = s.length();
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt += (s.charAt(i) - '0') ^ (i & 1);
        }
        return Math.min(cnt, len - cnt);
    }

    /**
     * 813. 最大平均值和的分组
     * 给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
     * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
     * 返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
     * 示例 1:
     * 输入: nums = [9,1,2,3,9], k = 3
     * 输出: 20.00000
     * 解释:
     * nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
     * 我们也可以把 nums 分成[9, 1], [2], [3, 9].
     * 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
     * 示例 2:
     * 输入: nums = [1,2,3,4,5,6,7], k = 4
     * 输出: 20.50000
     * 提示:
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] sum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] += sum[i - 1] + nums[i - 1];
        }
        double[][] dp = new double[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    dp[i][1] = sum[i] / i;
                } else {
                    for (int m = 2; m <= i; m++) {
                        dp[i][j] = Math.max(dp[i][j], dp[m - 1][j - 1] + (sum[i] - sum[m - 1]) / (i - m + 1));
                    }
                }
            }
        }
        return dp[n][k];
    }

    /**
     * 2488. 统计中位数为 K 的子数组
     * https://leetcode.cn/problems/count-subarrays-with-median-k/description/*
     * @param nums
     * @param k
     * @return
     */
    public int countSubarrays(int[] nums, int k) {
        //展开成一个数学式子
        //中位数 => （奇数长度） 小于 = 大于     （偶数） 小于+1 = 大于
        // 左侧小于 + 右侧小于 = 左侧大于 + 右侧大于
        // +左侧小于 - 左侧大于 = +右侧大于 - 右侧小于
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                pos = i;
                break;
            }
        }
        int cnt = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = pos + 1; i < nums.length; i++) {
            cnt += nums[i] > k ? 1 : -1;
            map.put(cnt, map.getOrDefault(cnt, 0) + 1);
        }
        ans += map.get(0) + map.getOrDefault(1, 0);
        cnt = 0;
        for (int i = pos - 1; i >= 0; i--) {
            cnt += nums[i] > k ? -1 : 1;
            ans += map.getOrDefault(cnt, 0) + map.getOrDefault(cnt + 1, 0);
        }
        return ans;
    }

    public ListNode removeNodes(ListNode head) {
        //可以倒过来遍历
        //可以递归
        if (head.next == null) {
            return head;
        }
        ListNode res = removeNodes(head.next);
        if (res.val > head.val) {
            return res;
        }
        head.next = res;
        return head;
    }

    public int appendCharacters(String s, String t) {
        int cnt = 0;
        int i = 0;
        while (cnt < t.length() && i < s.length()) {
            if (s.charAt(i++) == t.charAt(cnt)) {
                cnt++;
            }
            if (cnt == t.length()) return 0;
        }
        return t.length() - cnt;
    }

    //https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/
    public boolean check(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[(i + 1) % nums.length]) {
                ans++;
            }
        }
        return ans <= 1;
    }

    /**
     * 809. 情感丰富的文字
     * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
     * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。
     * 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = s。
     * 输入一组查询单词，输出其中可扩张的单词数量。
     * 示例：
     * 输入：
     * s = "heeellooo"
     * words = ["hello", "hi", "helo"]
     * 输出：1
     * 解释：
     * 我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
     * 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
     * 提示：
     * 1 <= s.length, words.length <= 100
     * 1 <= words[i].length <= 100
     * s 和所有在 words 中的单词都只由小写字母组成。
     *
     * @param s
     * @param words
     * @return
     */
    public int expressiveWords(String s, String[] words) {
        if (words.length == 0 || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0, j = 0;
            String cur = words[i];
            while (j < cur.length() && l < cs.length) {
                if (cur.charAt(j) != cs[l]) {
                    System.out.println("j" + ":" + "l");
                    break;
                } else {
                    while (++r < cs.length && cs[r] == cs[l]) ;
                    int cnt = r - l;
                    l = r;
                    int idx = j;
                    while (++idx < cur.length() && cur.charAt(idx) == cur.charAt(j)) ;
                    if (cnt < (idx - j) || (cnt < 3 && idx - j == cnt)) {
                        break;
                    }
                    j = idx;
                }
            }
            if (j == cur.length() && l == cs.length) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 795. 区间子数组个数
     * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
     * 生成的测试用例保证结果符合 32-bit 整数范围。
     * 示例 1：
     * 输入：nums = [2,1,4,3], left = 2, right = 3
     * 输出：3
     * 解释：满足条件的三个子数组：[2], [2, 1], [3]
     * 示例 2：
     * 输入：nums = [2,9,2,5,6], left = 2, right = 8
     * 输出：7
     * 提示：
     * 1 <= nums.length <= 105
     * 0 <= nums[i] <= 109
     * 0 <= left <= right <= 109
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        //连续,其中最大元素在范围 [left, right] 内的子数组
        int a = -1;
        int b = -1;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                a = i;
            }
            if (nums[i] >= left) {
                b = i;
            }
            ans += b - a;
        }
        return ans;
    }

    /**
     * 1742. 盒子中小球的最大数量
     * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
     * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
     * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
     * 示例 1：
     * 输入：lowLimit = 1, highLimit = 10
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
     * 编号 1 的盒子放有最多小球，小球数量为 2 。
     * 示例 2：
     * 输入：lowLimit = 5, highLimit = 15
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
     * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
     * 示例 3：
     * 输入：lowLimit = 19, highLimit = 28
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
     * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
     * 编号 10 的盒子放有最多小球，小球数量为 2 。
     * 提示：
     * 1 <= lowLimit <= highLimit <= 105
     *
     * @param
     * @return
     */
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnts = new int[46];
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int cur = i;
            int sum = 0;
            while (cur > 0) {
                sum += cur % 10;
                cur /= 10;
            }
            if (++cnts[sum] > ans) {
                ans = cnts[sum];
            }
        }
        return ans;
    }

    //https://leetcode.cn/problems/last-stone-weight/
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        Arrays.sort(stones);
        while (stones[stones.length - 2] != 0) {
            stones[stones.length - 1] = stones[stones.length - 1] - stones[stones.length - 2];
            stones[stones.length - 2] = 0;
            Arrays.sort(stones);
        }
        return stones[stones.length - 1];
    }

    /**
     * 878. 第 N 个神奇数字
     * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
     * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
     * 示例 1：
     * 输入：n = 1, a = 2, b = 3
     * 输出：2
     * 示例 2：
     * 输入：n = 4, a = 2, b = 3
     * 输出：6
     * 提示：
     * 1 <= n <= 109
     * 2 <= a, b <= 4 * 104
     *
     * @param n
     * @param a
     * @param b
     * @return
     */
    class NthMagicalNumber {
        int n, a, b, c;

        //最大公约数
        public int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public int nthMagicalNumber(int n, int a, int b) {
            this.n = n;
            this.a = a;
            this.b = b;
            c = a * b / gcd(a, b);//最小公倍数
            long l = 0, r = (long) 1e18;
            //二分查找
            while (l < r) {
                //左闭右开
                long mid = l + r >> 1;
                if (check(mid) >= n) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return (int) (r % 1000000007);
        }

        //容斥原理
        long check(long x) {
            return x / a + x / b - x / c;
        }
    }

    /**
     * 808. 分汤
     * 有 A 和 B 两种类型 的汤。一开始每种类型的汤有 n 毫升。有四种分配操作：
     * 提供 100ml 的 汤A 和 0ml 的 汤B 。
     * 提供 75ml 的 汤A 和 25ml 的 汤B 。
     * 提供 50ml 的 汤A 和 50ml 的 汤B 。
     * 提供 25ml 的 汤A 和 75ml 的 汤B 。
     * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 0.25 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
     * 注意 不存在先分配 100 ml 汤B 的操作。
     * 需要返回的值： 汤A 先分配完的概率 +  汤A和汤B 同时分配完的概率 / 2。返回值在正确答案 10-5 的范围内将被认为是正确的。
     * 示例 1:
     * 输入: n = 50
     * 输出: 0.62500
     * 解释:如果我们选择前两个操作，A 首先将变为空。
     * 对于第三个操作，A 和 B 会同时变为空。
     * 对于第四个操作，B 首先将变为空。
     * 所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
     * 示例 2:
     * 输入: n = 100
     * 输出: 0.71875
     * 提示:
     * 0 <= n <= 10^9
     *
     * @param n
     * @return
     */
    public double soupServings(int n) {
        //定义 f[i][j] 为 汤A 剩余 i 毫升，汤B 剩余 j 毫升时的最终概率（概率=汤A先分配完的概率+汤A和汤B同时分配完的概率×0.5）。
        n = Math.min(200, (int) Math.ceil(n / 25.0));
        double[][] dp = new double[n + 10][n + 10];
        //递推公式：dp[i][j] = 0.25*(dp[i-4][j]+dp[i-3][i-1]+dp[i-2][i-2]+dp[i-1][i-3])
        //初始化
        dp[0][0] = 0.5;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }
        //递推顺序，从0到n
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > 4 && j > 4) {
                    dp[i][j] = 0.25 * (dp[i - 4][j] + dp[i - 3][j - 1] + dp[i - 2][j - 2] + dp[i - 1][j - 3]);
                } else {
                    double a = dp[Math.max(0, i - 4)][j];
                    double b = dp[Math.max(0, i - 3)][Math.max(0, j - 1)];
                    double c = dp[Math.max(0, i - 2)][Math.max(0, j - 2)];
                    double d = dp[Math.max(0, i - 1)][Math.max(0, j - 3)];
                    dp[i][j] = 0.25 * (a + b + c + d);
                }
            }
        }
        return dp[n][n];
    }

    /**
     * 799. 香槟塔
     * 我们把玻璃杯摆成金字塔的形状，其中 第一层 有 1 个玻璃杯， 第二层 有 2 个，依次类推到第 100 层，每个玻璃杯 (250ml) 将盛有香槟。
     * 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）
     * 例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，第二层的香槟满了 - 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟，如下图所示。
     * 现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（ i 和 j 都从0开始）。
     * 示例 1:
     * 输入: poured(倾倒香槟总杯数) = 1, query_glass(杯子的位置数) = 1, query_row(行数) = 1
     * 输出: 0.00000
     * 解释: 我们在顶层（下标是（0，0））倒了一杯香槟后，没有溢出，因此所有在顶层以下的玻璃杯都是空的。
     * 示例 2:
     * 输入: poured(倾倒香槟总杯数) = 2, query_glass(杯子的位置数) = 1, query_row(行数) = 1
     * 输出: 0.50000
     * 解释: 我们在顶层（下标是（0，0）倒了两杯香槟后，有一杯量的香槟将从顶层溢出，位于（1，0）的玻璃杯和（1，1）的玻璃杯平分了这一杯香槟，所以每个玻璃杯有一半的香槟。
     * 示例 3:
     * 输入: poured = 100000009, query_row = 33, query_glass = 17
     * 输出: 1.00000
     * 提示:
     * 0 <= poured <= 109
     * 0 <= query_glass <= query_row < 100
     *
     * @param poured      倾倒香槟总杯数
     * @param query_row   杯子的位置数
     * @param query_glass 行数
     * @return
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = poured;
        //转移方程：dp[i+1][j] = (dp[i][j-1]-1)/2 + (dp[i][j]-1)/2
        for (int i = 1; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double a = j == 0 ? 0 : dp[i - 1][j - 1] < 1 ? 0 : (dp[i - 1][j - 1] - 1) / 2;
                double b = dp[i - 1][j] < 1 ? 0 : (dp[i - 1][j] - 1) / 2;
                dp[i][j] = a + b;
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }

    public double champagneTower2(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = poured;
        //转移方程：dp[i+1][j] = dp[i][j-1] + dp[i][j]
        //也就是说 dp[i+1][j] += 1/2  * (dp[i][j] - 1 ) , dp[i+1][j+1] +=1/2 * (dp[i][j]-1)
        for (int i = 0; i < query_row; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] < 1) {
                    continue;
                }
                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }

    /**
     * 1732. 找到最高海拔
     * 有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。自行车手从海拔为 0 的点 0 开始骑行。
     * 给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
     * 示例 1：
     * 输入：gain = [-5,1,5,0,-7]
     * 输出：1
     * 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
     * 示例 2：
     * 输入：gain = [-4,-3,-2,-1,4,3,2]
     * 输出：0
     * 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
     * 提示：
     * n == gain.length
     * 1 <= n <= 100
     * -100 <= gain[i] <= 100
     */
    public int largestAltitude(int[] gain) {
        int ans = 0;
        int cur = 0;
        for (int i = 0; i < gain.length; i++) {
            cur += gain[i];
            if (cur > ans) {
                ans = cur;
            }
        }
        return ans;
    }

    /**
     * 891. 子序列宽度之和
     * 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
     * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
     * 子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
     * 示例 1：
     * 输入：nums = [2,1,3]
     * 输出：6
     * 解释：子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
     * 相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
     * 宽度之和是 6 。
     * 示例 2：
     * 输入：nums = [2]
     * 输出：0
     * 提示：
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     *
     * @param nums 整数数组
     * @return
     */
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, MOD = (int) 1e9 + 7;
        long[] p = new long[n];
        long ans = 0;
        p[0] = 1;
        for (int i = 1; i < n; i++) {
            p[i] = p[i - 1] * 2 % MOD;
        }
        for (int i = 0; i < n; i++) {
            ans = (ans + (p[i] - p[n - i - 1]) * nums[i] % MOD) % MOD;
        }
        return (int) ans;
    }

    /**
     * 792. 匹配子序列的单词数
     * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
     * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
     * 例如， “ace” 是 “abcde” 的子序列。
     * 示例 1:
     * 输入: s = "abcde", words = ["a","bb","acd","ace"]
     * 输出: 3
     * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
     * Example 2:
     * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
     * 输出: 2
     * 提示:
     * 1 <= s.length <= 5 * 104
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 50
     * words[i]和 s 都只由小写字母组成。
     */
    public int numMatchingSubseq(String s, String[] words) {
        //尝试自己想一下，怎么跳过多余的步骤
        //将s逐个拆分，同一字符用list保存对应的下标
        int n = s.length(), ans = 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<>());
            list.add(i);
            map.put(s.charAt(i), list);
        }
        for (String w : words) {
            int len = w.length();
            int idx = -1;//对应主序列
            boolean ok = true;
            for (int i = 0; i < len; i++) {
                List<Integer> list = map.getOrDefault(w.charAt(i), new ArrayList<>());
                if (list.size() == 0) {
                    ok = false;
                    break;
                }
                //二分查找
                int l = 0, r = list.size() - 1;
                while (l < r) {
                    //左闭右开
                    int mid = l + r >> 1;
                    if (list.get(mid) <= idx) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                if (list.get(r) <= idx) {
                    ok = false;
                } else {
                    idx = list.get(r);
                }
            }
            if (ok) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 775. 全局倒置与局部倒置
     * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
     * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
     * 0 <= i < j < n
     * nums[i] > nums[j]
     * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
     * 0 <= i < n - 1
     * nums[i] > nums[i + 1]
     * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：nums = [1,0,2]
     * 输出：true
     * 解释：有 1 个全局倒置，和 1 个局部倒置。
     * 示例 2：
     * 输入：nums = [1,2,0]
     * 输出：false
     * 解释：有 2 个全局倒置，和 1 个局部倒置。
     * 提示：
     * n == nums.length
     * 1 <= n <= 105
     * 0 <= nums[i] < n
     * nums 中的所有整数 互不相同
     * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
     */
    public boolean isIdealPermutation(int[] nums) {
        //1,0,2
        //由“局部倒置”组成的集合为由“全局倒置”组成的集合的子集
        //若存在坐标 j 和 i，满足 nums[j] > nums[i] 且 j + 1 < i
        //nums 是范围 [0, n - 1] 内所有数字组成的一个排列
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1710. 卡车上的最大单元数
     * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
     * numberOfBoxesi 是类型 i 的箱子的数量。
     * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
     * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
     * 返回卡车可以装载 单元 的 最大 总数。
     * 示例 1：
     * 输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
     * 输出：8
     * 解释：箱子的情况如下：
     * - 1 个第一类的箱子，里面含 3 个单元。
     * - 2 个第二类的箱子，每个里面含 2 个单元。
     * - 3 个第三类的箱子，每个里面含 1 个单元。
     * 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
     * 单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
     * 示例 2：
     * 输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
     * 输出：91
     * <p>
     * 提示：
     * 1 <= boxTypes.length <= 1000
     * 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
     * 1 <= truckSize <= 106
     *
     * @param boxTypes
     * @param truckSize
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (int i = 0, cnt = 0; i < boxTypes.length && cnt < truckSize; i++) {
            int num = boxTypes[i][0];
            int unit = boxTypes[i][1];
            int remain = Math.min(truckSize - cnt, num);
            ans += remain * unit;
            cnt += remain;
        }
//        int idx = 0;
//        while (truckSize > 0 && idx < boxTypes.length) {
//            int num = boxTypes[idx][0];
//            int unit = boxTypes[idx][1];
//            while (num-- > 0 && truckSize-- > 0) {
//                ans += unit;
//            }
//            idx++;
//        }
        return ans;
    }

    /**
     * 791. 自定义字符串排序
     * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
     * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
     * 返回 满足这个性质的 s 的任意排列 。
     * 示例 1:
     * 输入: order = "cba", s = "abcd"
     * 输出: "cbad"
     * 解释:
     * “a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
     * 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
     * 示例 2:
     * 输入: order = "cbafg", s = "abcd"
     * 输出: "cbad"
     * 提示:
     * 1 <= order.length <= 26
     * 1 <= s.length <= 200
     * order 和 s 由小写英文字母组成
     * order 中的所有字符都 不同
     *
     * @param order
     * @param s
     * @return
     */
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < order.toCharArray().length; i++) {
            cnt[order.charAt(i) - 'a'] = i;
        }
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        Collections.sort(list, (a, b) -> cnt[a - 'a'] - cnt[b - 'a']);
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public String customSortString2(String order, String s) {
        //order是唯一的，但s不是唯一的
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i]-- > 0) {
                sb.append('a' + i);
            }
        }
        return sb.toString();
    }

    /**
     * 790. 多米诺和托米诺平铺
     * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
     * <p>
     * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。返回对 109 + 7 取模 的值。
     * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
     * 示例 1:
     * 输入: n = 3
     * 输出: 5
     * 解释: 五种不同的方法如上所示。
     * 示例 2:
     * 输入: n = 1
     * 输出: 1
     * 提示：
     * 1 <= n <= 1000
     *
     * @param n
     * @return
     */
    private static final long MOD = (long) 1e9 + 7;

    public int numTilings(int n) {
        //f[n] = 2 * f[n - 1] + f[n - 3](n >= 4)
        if (n == 1) return 1;
        long[] f = new long[n + 1];
        f[0] = f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = (f[i - 1] * 2 + f[i - 3]) % MOD;
        }
        return (int) f[n];
    }

    /**
     * * 1704. 判断字符串的两半是否相似
     * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
     * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
     * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：s = "book"
     * 输出：true
     * 解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
     * 示例 2：
     * 输入：s = "textbook"
     * 输出：false
     * 解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
     * 注意，元音 o 在 b 中出现两次，记为 2 个。
     * 提示：
     * 2 <= s.length <= 1000
     * s.length 是偶数
     * s 由 大写和小写 字母组成
     *
     * @param s
     * @return
     */
    public boolean halvesAreAlike(String s) {
        int len = s.length();
        boolean[] cs = new boolean[100];
        for (char c : "aeiouAEIOU".toCharArray()) {
            cs[c - 'A'] = true;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (cs[s.charAt(i) - 'A']) {
                ans += len / 2 <= i ? -1 : +1;
            }
        }
        return ans == 0;
    }

    /**
     * * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * 示例 1：
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     * 示例 2：
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     * 提示：
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 按 严格递增 顺序排列
     *
     * @param nums
     * @return
     */
    class sortedArrayToBSTClass {
        public TreeNode sortedArrayToBST(int[] nums) {
            //1.选择中间节点
            //2.分治法+递归，本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间。
            return sortedArrayToBST(nums, 0, nums.length);
        }

        public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
            //递归结束
            if (left >= right) {
                return null;
            }
            if (right - left == 1) {
                return new TreeNode(nums[left]);
            }
            int mid = left + right >> 1;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = sortedArrayToBST(nums, left, mid);//左闭右开 所以是mid，而不是mid+1
            node.right = sortedArrayToBST(nums, mid + 1, right);//左闭右开 左闭，下一个递归不考虑当前mid
            return node;
        }
    }


    /**
     * * 764. 最大加号标志
     * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
     * <p>
     * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
     * <p>
     * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，
     * * 由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
     * 示例 1：
     * 输入: n = 5, mines = [[4, 2]]
     * 输出: 2
     * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
     * 示例 2：
     * 输入: n = 1, mines = [[0, 0]]
     * 输出: 0
     * 解释: 没有加号标志，返回 0 。
     * 提示：
     * 1 <= n <= 500
     * 1 <= mines.length <= 5000
     * 0 <= xi, yi < n
     * 每一对 (xi, yi) 都 不重复
     *
     * @param n
     * @param mines
     * @return
     */
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] g = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], 1);
        }
        for (int i = 0; i < mines.length; i++) {
            g[mines[i][0] + 1][mines[i][1] + 1] = 0;
        }
        int[][] up = new int[n + 2][n + 2];
        int[][] left = new int[n + 2][n + 2];
        int[][] right = new int[n + 2][n + 2];
        int[][] down = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (g[i][j] == 1) {
                    right[i][j] = right[i - 1][j] + 1;
                    down[i][j] = down[i][j - 1] + 1;
                }
                if (g[n + 1 - i][n + 1 - j] == 1) {
                    up[n + 1 - i][n + 1 - j] = up[n + 1 - i][n + 2 - j] + 1;
                    left[n + 1 - i][n + 1 - j] = left[n + 2 - i][n + 1 - j] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans = Math.max(ans, Math.min(Math.min(down[i][j], up[i][j]), Math.min(left[i][j], right[i][j])));
            }
        }
        return ans;
    }
//    public int orderOfLargestPlusSign(int n, int[][] mines) {
//        boolean[][] grid = new boolean[n][n];
//        int ans = 0;
//        for (int i = 0; i < mines.length; i++) {
//            grid[mines[i][0]][mines[i][1]] = true;
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                ans = Math.max(ans, findBigPlus(i, j, grid));
//            }
//        }
//        return ans;
//    }
//
//    public int findBigPlus(int x, int y, boolean[][] grid) {
//        if (grid[x][y]) return 0;
//        int s = 1;
//        while (true) {
//            if (x - s < 0 || x + s >= grid.length || y - s < 0 || y + s >= grid.length || grid[x - s][y] || grid[x + s][y] || grid[x][y - s] || grid[x][y + s]) {
//                break;
//            }
//            s++;
//        }
//        return s;
//    }

    /**
     * * 93. 复原 IP 地址
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * <p>
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
     * 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * 示例 1：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * 示例 2：
     * 输入：s = "0000"
     * 输出：["0.0.0.0"]
     * 示例 3：
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     * <p>
     * 提示：
     * 1 <= s.length <= 20
     * s 仅由数字组成
     *
     * @param s
     * @return
     */
    class Solution2 {
        List<String> ans = new ArrayList<>();
        StringBuilder sb;

        public List<String> restoreIpAddresses(String s) {
            if (s.length() > 12) return ans;
            sb = new StringBuilder(s);
            backTracking(sb, 0, 0);
            return ans;
        }

        public void backTracking(StringBuilder sb, int start, int pointSum) {
            //注意下标
            if (pointSum == 3) {//判断点是否够3个
                if (isValid(sb.substring(start, sb.length()))) {//判断第四段是否符合
                    ans.add(sb.toString());
                    return;
                }
            }
            for (int i = start; i < sb.length() && i < start + 3; i++) {
                if (isValid(sb.substring(start, i + 1))) {
                    sb.insert(i + 1, ".");
                    backTracking(sb, i + 2, pointSum + 1);
                    sb.deleteCharAt(i + 1);
                } else {
                    break;
                }
            }
        }

        private boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return false;
            }
            //排除前导0
            if (s.charAt(0) == '0' && s.length() > 1) {
                return false;
            }
            int num = 0;
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    //s 仅由数字组成
                    return false;
                }
                int cur = c - '0';
                num = num * 10 + cur;
            }
            //每个整数位于 0 到 255 之间组成
            return num <= 255;
        }
    }

    /**
     * 1684. 统计一致字符串的数目
     * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
     * 请你返回 words 数组中 一致字符串 的数目。
     * 示例 1：
     * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
     * 输出：2
     * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
     * 示例 2：
     * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
     * 输出：7
     * 解释：所有字符串都是一致的。
     * 示例 3：
     * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
     * 输出：4
     * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
     * 提示：
     * 1 <= words.length <= 104
     * 1 <= allowed.length <= 26
     * 1 <= words[i].length <= 10
     * allowed 中的字符 互不相同 。
     * words[i] 和 allowed 只包含小写英文字母。
     *
     * @param allowed
     * @param words
     * @return
     */
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] w = new boolean[26];
        int ans = 0;
        for (char c : allowed.toCharArray()) {
            w[c - 'a'] = true;
        }
        //标签 out
        out:
        for (String s : words) {
            for (char c : s.toCharArray()) {
                if (!w[c - 'a']) {
                    continue out;
                }
            }
            ans++;
        }
        return ans;
    }

    /**
     * 37. 解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * 数独的解法需 遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * 示例 1：
     * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
     * <p>
     * 提示：
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字或者 '.'
     * 题目数据 保证 输入数独仅有一个解
     */
    class Sudoku {
        public void solveSudoku(char[][] board) {
            //数字 1-9 在每一行只能出现一次。
            //数字 1-9 在每一列只能出现一次。
            //数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
            if (board == null || board.length != 9 || board[0].length != 9) return;
            boolean[][] row = new boolean[9][9];
            boolean[][] col = new boolean[9][9];
            boolean[][] box = new boolean[9][9];
            //初始化状态
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        int k = (i / 3) * 3 + j / 3;//box
                        row[i][num] = col[num][j] = box[k][num] = true;
                    }
                }
            }
            backTracking(board, 0, row, col, box);
        }

        public boolean backTracking(char[][] board, int n, boolean[][] row, boolean[][] col, boolean[][] box) {
            if (n == 81) return true;
            int i = n / 9, j = n % 9;
            if (board[i][j] != '.') return backTracking(board, n + 1, row, col, box);
            int k = (i / 3) * 3 + j / 3;
            for (int num = 0; num < 9; num++) {
                if (row[i][num] || col[num][j] || box[k][num]) {
                    continue;
                }
                board[i][j] = (char) (num + '1');
                row[i][num] = col[num][j] = box[k][num] = true;
                if (backTracking(board, n + 1, row, col, box)) return true;
                row[i][num] = col[num][j] = box[k][num] = false;
            }
            board[i][j] = '.';
            return false;
        }

        public boolean isValid(int row, int col, char val, char[][] board) {
//            if (!res[i][0][k - '1'] && !res[j][1][k - '1']) {
//                int n = i / 3 + (j / 3) * 3;
//                return !res[n][2][k - '1'];
//            }
//            return false;
            // 同行是否重复
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == val) {
                    return false;
                }
            }
            // 同列是否重复
            for (int j = 0; j < 9; j++) {
                if (board[j][col] == val) {
                    return false;
                }
            }
            // 9宫格里是否重复
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    if (board[i][j] == val) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * 1678. 设计 Goal 解析器
     * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。
     * Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。
     * * 然后，按原顺序将经解释得到的字符串连接成一个字符串。
     * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
     * 示例 1：
     * 输入：command = "G()(al)"
     * 输出："Goal"
     * 解释：Goal 解析器解释命令的步骤如下所示：
     * G -> G
     * () -> o
     * (al) -> al
     * 最后连接得到的结果是 "Goal"
     * 示例 2：
     * 输入：command = "G()()()()(al)"
     * 输出："Gooooal"
     * 示例 3：
     * 输入：command = "(al)G(al)()()G"
     * 输出："alGalooG"
     * <p>
     * 提示：
     * 1 <= command.length <= 100
     * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成*
     */
    public String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    /**
     * 1106. 解析布尔表达式
     * 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
     * 有效的表达式需遵循以下约定：
     * <p>
     * "t"，运算结果为 True
     * "f"，运算结果为 False
     * "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
     * "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
     * "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
     * 示例 1：
     * 输入：expression = "!(f)"
     * 输出：true
     * 示例 2：
     * 输入：expression = "|(f,t)"
     * 输出：true
     * 示例 3：
     * 输入：expression = "&(t,f)"
     * 输出：false
     * 示例 4：
     * 输入：expression = "|(&(t,f,t),!(t))"
     * 输出：false
     * 提示：
     * 1 <= expression.length <= 20000
     * expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。
     * expression 是以上述形式给出的有效表达式，表示一个布尔值。
     *
     * @param expression
     * @return
     */
    public boolean parseBoolExpr(String expression) {
        Deque<Character> bool = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        for (char c : expression.toCharArray()) {
            //要处理的'(', ')', '&', '|', '!', 't', 'f', ','
            //','跳过，'t'，'f'保存到bool，'&', '|', '!'保存到ops，'('作为标志符号 以'('保存到bool，')'作为结束标志，作为计算括号之间的bool的结束
            if (c == ',') continue;
            if (c == 't' || c == 'f' || c == '(') bool.addLast(c);
            if (c == '&' || c == '|' || c == '!') ops.addLast(c);
            if (c == ')') {
                char res = ' ';
                char op = ops.pollLast();
                while (!bool.isEmpty() && bool.peekLast() != '(') {
                    char cur = bool.pollLast();
                    res = res == ' ' ? cur : calcBool(res, cur, op);
                }
                if (op == '!') {
                    res = res == 't' ? 'f' : 't';
                }
                bool.pollLast();
                bool.addLast(res);
            }
        }
        return bool.peekLast() == 't';
    }

    private char calcBool(char res, char cur, char op) {
        //& |
        boolean a = res == 't';
        boolean b = cur == 't';
        boolean ans = op == '&' ? a & b : a | b;
        return ans ? 't' : 'f';
    }


    /**
     * 777. 在LR字符串中交换相邻字符
     * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
     * 一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，
     * 当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
     * 示例 :
     * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
     * 输出: True
     * 解释:
     * 我们可以通过以下几步将start转换成end:
     * RXXLRXRXL ->
     * XRXLRXRXL ->
     * XRLXRXRXL ->
     * XRLXXRRXL ->
     * XRLXXRRLX
     * 提示：
     * 1 <= len(start) = len(end) <= 10000。
     * start和end中的字符串仅限于'L', 'R'和'X'。*
     *
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        //一次移动操作指将"RX"替换成"XR"，"XL"替换成"LX"
        //RX -> XR  R向右移动
        //XL -> LX  L向左移动
        //R 和 L 不能碰撞
        //因此在 start 和 end 中序号相同的 L 和 R 必然满足坐标性质：
        //序号相同的 L : start 的下标不小于 end 的下标（即 L 不能往右移动）
        //序号相同的 R : start 的下标不大于 end 的下标（即 R 不能往左移动）
        //start = "RXXLRXRXL", end = "XRLXXRRLX"
        int len = start.length(), i = 0, j = 0;
        while (i < len || j < len) {
            while (i < len && start.charAt(i) == 'X') i++;
            while (j < len && end.charAt(j) == 'X') j++;
            if (i == len || j == len) return i == j;
            if (start.charAt(i) != end.charAt(j)) return false;//RL出现顺序不一样，就不符合题意
            if (start.charAt(i) == 'R' && i > j) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            i++;
            j++;
        }
        return i == j;
    }

    /**
     * 754. 到达终点数字
     * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
     * 你可以做一些数量的移动 numMoves :
     * <p>
     * 每次你可以选择向左或向右移动。
     * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
     * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
     * <p>
     * 示例 1:
     * 输入: target = 2
     * 输出: 3
     * 解释:
     * 第一次移动，从 0 到 1 。
     * 第二次移动，从 1 到 -1 。
     * 第三次移动，从 -1 到 2 。
     * 示例 2:
     * 输入: target = 3
     * 输出: 2
     * 解释:
     * 第一次移动，从 0 到 1 。
     * 第二次移动，从 1 到 3 。
     * <p>
     * 提示:
     * -109 <= target <= 109
     * target != 0*
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = (int) Math.sqrt(target * 2), dis = k * (k + 1) / 2;
        while (dis < target || (dis - target) % 2 != 0) {
            k++;
            dis += k;
        }
        return k;
    }

    /*51. N 皇后
        按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
        n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
        给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
        每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

        示例 1：
        输入：n = 4
        输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        解释：如上图所示，4 皇后问题存在两个不同的解法。
        示例 2：
        输入：n = 1
        输出：[["Q"]]

        提示：
        1 <= n <= 9
        通过次数262,330提交次数353,880
     */
    class N_Queens {
        List<List<String>> ans = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            int[] cur = new int[n];
            Arrays.fill(cur, -1);
            int[][] res = new int[3][2 * n + 1];
            backTracking(n, 0, cur, res);
            return ans;
        }

        public void backTracking(int n, int row, int[] cur, int[][] res) {
            if (row == n) {
                List<String> list = arrays2String(cur);
                ans.add(list);
                return;
            }
            for (int i = 0; i < n; i++) {
                //判断是否有占用
                //res[0]列，res[1]左对角线，res[2]右对角线(只有行与列之差才会在同一有对角线一样，同时防止符号要'+n')
                if (res[0][i] == 0 && res[1][row + i] == 0 && res[2][n + row - i] == 0) {
                    res[0][i] = res[1][row + i] = res[2][n + row - i] = 1;
                    cur[row] = i;
                    //递归
                    backTracking(n, row + 1, cur, res);
                    //回溯
                    res[0][i] = res[1][row + i] = res[2][n + row - i] = 0;
                    cur[row] = -1;
                }
            }
        }

        public List<String> arrays2String(int[] cur) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < cur.length; i++) {
                char[] cs = new char[cur.length];
                Arrays.fill(cs, '.');
                cs[cur[i]] = 'Q';
                list.add(new String(cs));
            }
            return list;
        }

    }

    /*https://leetcode.cn/problems/maximum-repeating-substring/
    1668. 最大重复子字符串
    给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。
    单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。

    给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。

    示例 1：
        输入：sequence = "ababc", word = "ab"
        输出：2
        解释："abab" 是 "ababc" 的子字符串。
    示例 2：
        输入：sequence = "ababc", word = "ba"
        输出：1
        解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
    示例 3：
        输入：sequence = "ababc", word = "ac"
        输出：0
        解释："ac" 不是 "ababc" 的子字符串。

    提示：
    1 <= sequence.length <= 100
    1 <= word.length <= 100
    sequence 和 word 都只包含小写英文字母。
     */
    public int maxRepeating(String ss, String pp) {
        int n = ss.length(), m = pp.length(), ans = 0;
        int[] dp = new int[n + 10];//状态转移方程：f[i]=f[i−m]+1。
        for (int i = 0; i <= n; i++) {
            if (i - m < 0) continue;
            if (ss.substring(i - m, i).equals(pp)) {
                dp[i] = dp[i - m] + 1;
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int maxRepeating2(String sequence, String word) {
        int ans = 0;
        StringBuilder sb = new StringBuilder(word);
        while (sequence.contains(sb)) {
            ans++;
            sb.append(word);
        }
        return ans;
    }

    /*
    https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
    1620. 网络信号最好的坐标    中等
    给你一个数组 towers 和一个整数 radius 。
    数组  towers  中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi] 表示第 i 个网络信号塔的坐标是 (xi, yi) 且信号强度参数为 qi 。
    所有坐标都是在  X-Y 坐标系内的 整数 坐标。两个坐标之间的距离用 欧几里得距离 计算。
    整数 radius 表示一个塔 能到达 的 最远距离 。如果一个坐标跟塔的距离在 radius 以内，那么该塔的信号可以到达该坐标。
    在这个范围以外信号会很微弱，所以 radius 以外的距离该塔是 不能到达的 。
    如果第 i 个塔能到达 (x, y) ，那么该塔在此处的信号为 ⌊qi / (1 + d)⌋ ，其中 d 是塔跟此坐标的距离。一个坐标的 信号强度 是所有 能到达 该坐标的塔的信号强度之和。
    请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点 (cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。

    注意：
        坐标 (x1, y1) 字典序比另一个坐标 (x2, y2) 小，需满足以下条件之一：
        要么 x1 < x2 ，
        要么 x1 == x2 且 y1 < y2 。
        ⌊val⌋ 表示小于等于 val 的最大整数（向下取整函数）。

    示例 1：
        输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
        输出：[2,1]
        解释：
        坐标 (2, 1) 信号强度之和为 13
        - 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
        - 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
        - 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
        没有别的坐标有更大的信号强度。
    示例 2：
        输入：towers = [[23,11,21]], radius = 9
        输出：[23,11]
        解释：由于仅存在一座信号塔，所以塔的位置信号强度最大。
    示例 3：
        输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
        输出：[1,2]
        解释：坐标 (1, 2) 的信号强度最大。

        提示：
        1 <= towers.length <= 50
        towers[i].length == 3
        0 <= xi, yi, qi <= 50
        1 <= radius <= 50
     */
    public int[] bestCoordinate(int[][] towers, int radius) {
        int[][] m = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] tower : towers) {
            int tx = tower[0], ty = tower[1], tq = tower[2];
            //tower辐射范围遍历
            for (int i = Math.max(0, tx - radius); i <= tx + radius; i++) {
                for (int j = Math.max(0, ty - radius); j <= ty + radius; j++) {
                    double d = Math.sqrt((i - tx) * (i - tx) + (j - ty) * (j - ty));
                    if (d > radius) continue;
                    //向下取整函数,信号为 [qi / (1 + d)]
                    m[i][j] += Math.floor(tq / (1 + d));
                    if (m[i][j] > val) {
                        x = i;
                        y = j;
                        val = m[i][j];
                    } else if (m[i][j] == val && (i < x || (x == i && j < y))) {
                        //如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }
}

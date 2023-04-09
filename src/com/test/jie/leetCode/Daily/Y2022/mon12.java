package com.test.jie.leetCode.Daily.Y2022;

import java.util.*;

public class mon12 {
    public static void main(String[] args) {
        int[] domino1 = new int[6];
        int[] domino2 = new int[6];
        Random ran = new Random();
        for (int i = 0; i < domino1.length; i++) {
            domino1[i] = ran.nextInt(10) + 1;
            domino2[i] = ran.nextInt(10) + 1;
        }
        String[] ans = new String[12];
        for (int i = 0; i < domino1.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                sb.append("a").append(j + 1).append("*").append("b").append(i - j + 1);
                if (j != i) {
                    sb.append("+");
                }
            }
            ans[i] = sb.toString();
            sb.delete(0, sb.length());
            for (int j = domino1.length - 1; j >= i; j--) {
                sb.append("a").append(i + domino1.length - j).append("*").append("b").append(j + 1);
                if (j != i) {
                    sb.append("+");
                }
            }
            ans[domino1.length - 1 + i] = sb.toString();
        }
        for (String s : ans) {
            System.out.println(s);
        }
    }

    /**
     * 1802. 有界数组中指定下标处的最大值
     * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
     * nums.length == n
     * nums[i] 是 正整数 ，其中 0 <= i < n
     * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
     * nums 中所有元素之和不超过 maxSum
     * nums[index] 的值被 最大化
     * 返回你所构造的数组中的 nums[index] 。
     * <p>
     * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
     * 示例 1：
     * 输入：n = 4, index = 2,  maxSum = 6
     * 输出：2
     * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
     * 示例 2：
     * 输入：n = 6, index = 1,  maxSum = 10
     * 输出：3
     * 提示：
     * 1 <= n <= maxSum <= 109
     * 0 <= index < n
     *
     * @param n
     * @param index
     * @param maxSum
     * @return
     */
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (calMaxValue(mid - 1, index) + calMaxValue(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //进行计算
    private long calMaxValue(long num, int length) {
        //判断能否直接用等差求和公式计算
        //计算左右侧之和
        if (num >= length) {
            return (num + num - length + 1) * length / 2;
        } else {
            return (num + 1) * num / 2 + length - num;
        }
    }

    /**
     * 1785. 构成特定和需要添加的最少元素
     * 中等
     * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
     * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
     * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
     * 示例 1：
     * 输入：nums = [1,-1,1], limit = 3, goal = -4
     * 输出：2
     * 解释：可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
     * 示例 2：
     * 输入：nums = [1,-10,9,1], limit = 100, goal = 0
     * 输出：1
     * 提示：
     * 1 <= nums.length <= 105
     * 1 <= limit <= 106
     * -limit <= nums[i] <= limit
     * -109 <= goal <= 109
     *
     * @param nums
     * @param limit
     * @param goal
     * @return
     */
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int x : nums) {
            sum += x;
        }
        long diff = Math.abs(goal - sum);
        return (int) ((diff + limit - 1) / limit);
    }

    /**
     * 1945. 字符串转化后的各位数字之和
     * 给你一个由小写字母组成的字符串 s ，以及一个整数 k 。
     * 首先，用字母在字母表中的位置替换该字母，将 s 转化 为一个整数（也就是，'a' 用 1 替换，'b' 用 2 替换，... 'z' 用 26 替换）。接着，将整数 转换 为其 各位数字之和 。共重复 转换 操作 k 次 。
     * 例如，如果 s = "zbax" 且 k = 2 ，那么执行下述步骤后得到的结果是整数 8 ：
     * 转化："zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
     * 转换 #1：262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
     * 转换 #2：17 ➝ 1 + 7 ➝ 8
     * 返回执行上述操作后得到的结果整数。
     * 示例 1：
     * 输入：s = "iiii", k = 1
     * 输出：36
     * 解释：操作如下：
     * - 转化："iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
     * - 转换 #1：9999 ➝ 9 + 9 + 9 + 9 ➝ 36
     * 因此，结果整数为 36 。
     * 示例 2：
     * 输入：s = "leetcode", k = 2
     * 输出：6
     * 解释：操作如下：
     * - 转化："leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
     * - 转换 #1：12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
     * - 转换 #2：33 ➝ 3 + 3 ➝ 6
     * 因此，结果整数为 6 。
     * 提示：
     * 1 <= s.length <= 100
     * 1 <= k <= 10
     * s 由小写英文字母组成
     *
     * @param s
     * @param k
     * @return
     */
    public int getLucky(String s, int k) {
        char[] cs = s.toCharArray();
        int sum = 0;
        for (char c : cs) {
            int cur = c - 'a' + 1;
            System.out.println(cur);
            System.out.println(sum);
            if (cur >= 10) {
                sum += cur / 10;
            }
            sum += cur % 10;
        }
        while (--k > 0) {
            int cur = 0;
            while (sum > 0) {
                cur += sum % 10;
                sum /= 10;
            }
            sum = cur;
        }
        return sum;
    }

    /**
     * 1832. 判断句子是否为全字母句
     * 全字母句 指包含英语字母表中每个字母至少一次的句子。
     * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
     * 如果是，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：sentence = "thequickbrownfoxjumpsoverthelazydog"
     * 输出：true
     * 解释：sentence 包含英语字母表中每个字母至少一次。
     * 示例 2：
     * 输入：sentence = "leetcode"
     * 输出：false
     * 提示：
     * 1 <= sentence.length <= 1000
     * sentence 由小写英语字母组成
     *
     * @param sentence
     * @return
     */
    public boolean checkIfPangram(String sentence) {
        int state = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            state |= 1 << (c - 'a');
        }
        return state == (1 << 26) - 1;
    }

    /**
     * 1827. 最少操作使数组递增
     * 简单
     * 54
     * 相关企业
     * 给你一个整数数组 nums （下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加 1 。
     * 比方说，如果 nums = [1,2,3] ，你可以选择增加 nums[1] 得到 nums = [1,3,3] 。
     * 请你返回使 nums 严格递增 的 最少 操作次数。
     * 我们称数组 nums 是 严格递增的 ，当它满足对于所有的 0 <= i < nums.length - 1 都有 nums[i] < nums[i+1] 。一个长度为 1 的数组是严格递增的一种特殊情况。
     * 示例 1：
     * 输入：nums = [1,1,1]
     * 输出：3
     * 解释：你可以进行如下操作：
     * 1) 增加 nums[2] ，数组变为 [1,1,2] 。
     * 2) 增加 nums[1] ，数组变为 [1,2,2] 。
     * 3) 增加 nums[2] ，数组变为 [1,2,3] 。
     * 示例 2：
     * 输入：nums = [1,5,2,4,1]
     * 输出：14
     * 示例 3：
     * 输入：nums = [8]
     * 输出：0
     * 提示：
     * 1 <= nums.length <= 5000
     * 1 <= nums[i] <= 10^4
     *
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int ans = 0;
        int pre = nums[0] - 1;
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + 1, nums[i]);
            ans += pre - nums[i];
        }
        return ans;
    }

    /**
     * 1780. 判断一个数字是否可以表示成三的幂的和
     * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
     * 对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
     * 示例 1：
     * 输入：n = 12
     * 输出：true
     * 解释：12 = 31 + 32
     * 示例 2：
     * 输入：n = 91
     * 输出：true
     * 解释：91 = 30 + 32 + 34
     * 示例 3：
     * 输入：n = 21
     * 输出：false
     * 提示：
     * 1 <= n <= 107
     */
    public boolean checkPowersOfThree(int n) {
        //n可能是有3^0或者没有，所以n%3==0或者1，其他情况不可能
        while (n != 0) {
            if (n % 3 == 1 || n % 3 == 0) {
                n /= 3;
            } else return false;
        }
        return true;
    }

    /**
     * 1812. 判断国际象棋棋盘中一个格子的颜色
     * 简单
     * 37
     * 相关企业
     * 给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。
     * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
     * 给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。
     * 示例 1：
     * 输入：coordinates = "a1"
     * 输出：false
     * 解释：如上图棋盘所示，"a1" 坐标的格子是黑色的，所以返回 false 。
     * 示例 2：
     * 输入：coordinates = "h3"
     * 输出：true
     * 解释：如上图棋盘所示，"h3" 坐标的格子是白色的，所以返回 true 。
     * 示例 3：
     * 输入：coordinates = "c7"
     * 输出：false
     * 提示：
     * coordinates.length == 2
     * 'a' <= coordinates[0] <= 'h'
     * '1' <= coordinates[1] <= '8'
     */
    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) - 'a' + coordinates.charAt(1) - '1') % 2 == 1;
    }

    /**
     * 1775. 通过最少操作次数使数组的和相等
     * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
     * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
     * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
     * 示例 1：
     * 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
     * 输出：3
     * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
     * - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
     * - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
     * - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
     * 示例 2：
     * 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
     * 输出：-1
     * 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
     * 示例 3：
     * 输入：nums1 = [6,6], nums2 = [1]
     * 输出：3
     * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
     * - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
     * - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
     * - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
     * 提示：
     * 1 <= nums1.length, nums2.length <= 105
     * 1 <= nums1[i], nums2[i] <= 6
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int minOperations(int[] nums1, int[] nums2) {
        //无法使两个数组的和相等,即最短的都是6，最长的都是1
        if (nums1.length * 6 < nums2.length || nums1.length > nums2.length * 6) {
            return -1;
        }
        //这里要nums1大于nums2,计算之间差值
//        int sum = Arrays.stream(nums1).sum() - Arrays.stream(nums2).sum();
        int sum = 0;
        for (int i : nums1) {
            sum += i;
        }
        for (int i : nums2) {
            sum -= i;
        }
        // 交换，统一让 nums2 的数变大，nums1 的数变小
        if (sum < 0) {
            sum = -sum;
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // 统计每个数的最大变化量
        int[] cnt = new int[6];
        for (int i : nums2) {
            // nums2 的变成 6
            cnt[6 - i]++;
        }
        for (int i : nums1) {
            // nums1 的变成 1
            cnt[i - 1]++;
        }
        int ans = 0;
        for (int i = 5; i > 0; i--) {
            // 从大到小枚举最大变化量 5 4 3 2 1
            //i不会等于0
            // 可以让差值 sum 变为 0
            if (cnt[i] * i >= sum) {
                return ans + (sum + i - 1) / i;
            }
            // 需要所有最大变化量为 i 的数
            ans += cnt[i];
            sum -= cnt[i] * i;
        }
        return ans;
    }

    /**
     * 1805. 字符串中不同整数的数目
     * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
     * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
     * 返回对 word 完成替换后形成的 不同 整数的数目。
     * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
     * 示例 1：
     * 输入：word = "a123bc34d8ef34"
     * 输出：3
     * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
     * 示例 2：
     * 输入：word = "leet1234code234"
     * 输出：2
     * 示例 3：
     * 输入：word = "a1b01c001"
     * 输出：1
     * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
     * 提示：
     * 1 <= word.length <= 1000
     * word 由数字和小写英文字母组成
     *
     * @param word
     * @return
     */
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        for (int i = 0, j = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                j = i;
                while (i < word.length() && Character.isDigit(word.charAt(i))) {
                    i++;
                }
                while (j < i && word.charAt(j) == '0') {
                    j++;
                }
                String cur = word.substring(j, i);
                if (!set.contains(cur)) {
                    set.add(cur);
                }
            }
        }
        return set.size();
    }

    /**
     * 1774. 最接近目标价格的甜点成本
     * 你打算做甜点，现在需要购买配料。目前共有 n 种冰激凌基料和 m 种配料可供选购。而制作甜点需要遵循以下几条规则：
     * 必须选择 一种 冰激凌基料。
     * 可以添加 一种或多种 配料，也可以不添加任何配料。
     * 每种类型的配料 最多两份 。
     * 给你以下三个输入：
     * baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。
     * toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
     * target ，一个整数，表示你制作甜点的目标价格。
     * 你希望自己做的甜点总成本尽可能接近目标价格 target 。
     * 返回最接近 target 的甜点成本。如果有多种方案，返回 成本相对较低 的一种。
     * 示例 1：
     * 输入：baseCosts = [1,7], toppingCosts = [3,4], target = 10
     * 输出：10
     * 解释：考虑下面的方案组合（所有下标均从 0 开始）：
     * - 选择 1 号基料：成本 7
     * - 选择 1 份 0 号配料：成本 1 x 3 = 3
     * - 选择 0 份 1 号配料：成本 0 x 4 = 0
     * 总成本：7 + 3 + 0 = 10 。
     * 示例 2：
     * 输入：baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
     * 输出：17
     * 解释：考虑下面的方案组合（所有下标均从 0 开始）：
     * - 选择 1 号基料：成本 3
     * - 选择 1 份 0 号配料：成本 1 x 4 = 4
     * - 选择 2 份 1 号配料：成本 2 x 5 = 10
     * - 选择 0 份 2 号配料：成本 0 x 100 = 0
     * 总成本：3 + 4 + 10 + 0 = 17 。不存在总成本为 18 的甜点制作方案。
     * 示例 3：
     * 输入：baseCosts = [3,10], toppingCosts = [2,5], target = 9
     * 输出：8
     * 解释：可以制作总成本为 8 和 10 的甜点。返回 8 ，因为这是成本更低的方案。
     * 示例 4：
     * 输入：baseCosts = [10], toppingCosts = [1], target = 1
     * 输出：10
     * 解释：注意，你可以选择不添加任何配料，但你必须选择一种基料。
     * 提示：
     * n == baseCosts.length
     * m == toppingCosts.length
     * 1 <= n, m <= 10
     * 1 <= baseCosts[i], toppingCosts[i] <= 104
     * 1 <= target <= 104
     *
     * @param baseCosts
     * @param toppingCosts
     * @param target
     * @return
     */
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        //获取基料的最小值
        int min = Arrays.stream(baseCosts).min().getAsInt();
        if (min >= target) {
            return min;
        }
        // 如果 “开销超过 target ”的值取到超过 ans, 那么比 min 距离 target 还远， 一定不成立
        int ans = 2 * target - min;
        // 表示每个价位是否存在合法的方案, 从 0 到 target
        boolean[] can = new boolean[target + 1];
        for (int x : baseCosts) {
            if (x <= target) {
                can[x] = true;
            } else {
                //比target大，但是接近target的
                ans = Math.min(ans, x);
            }
        }
        for (int t : toppingCosts) {
            // 每种类型的配料 最多两份
            for (int j = 0; j < 2; j++) {
                for (int i = target; i >= 0; i--) {
                    if (can[i] && i + t > target) {
                        //比target大，但是接近target的
                        ans = Math.min(ans, i + t);
                    }
                    //转移方程，这里和01背包的差不多思路了，
                    // 因为任意一个合法方案加上 一份配料一定也是合法方案，所以 can 数组的状态转义方程是 can[i] = can[i] | can[i - t]
                    if (i - t > 0) {
                        can[i] = can[i] | can[i - t];
                    }
                }
            }
        }
        // 先找比 target 小的方案 （且注意范围，遍历 ans - target + 1 次，保证 Math.abs(target - i) <= Math.abs(target - ans) ）
        for (int i = 0; i <= ans - target; i++) {
            if (can[target - i]) {
                return target - i;
            }
        }
        // 若在范围内没找到比 target 小的，就返回“成本相对较低的”的 比 target 大的情况
        return ans;
    }

    public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
        int len = baseCosts.length;
        this.target = target;
        for (int i = 0; i < len; i++) {
            closestCostDFS(baseCosts[i], toppingCosts, 0);
        }
        return ans;
    }

    int target;
    int ans = Integer.MAX_VALUE;

    public void closestCostDFS(int sum, int[] toppingCosts, int index) {
        int dis = Math.abs(ans - target) - Math.abs(target - sum);
        if (dis > 0 || (dis == 0 && sum < ans)) {
            ans = sum;
        }
        if (sum > target || index == toppingCosts.length) {
            return;
        }
        //配料0,1,2，不选或最多两份
        for (int i = 0; i < 3; i++) {
            sum += i * toppingCosts[index];
            closestCostDFS(sum, toppingCosts, index + 1);
            sum -= i * toppingCosts[index];
        }
    }

    /**
     * 1796. 字符串中第二大的数字
     * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
     * 混合字符串 由小写英文字母和数字组成。
     * 示例 1：
     * 输入：s = "dfa12321afd"
     * 输出：2
     * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
     * 示例 2：
     * 输入：s = "abc1111"
     * 输出：-1
     * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
     * 提示：
     * 1 <= s.length <= 500
     * s 只包含小写英文字母和（或）数字。
     *
     * @param s
     * @return
     */
    public int secondHighest(String s) {
        int max = -1;
        int ans = -1;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                if (num > max) {
                    ans = max;
                    max = num;
                } else if (num != max && num > ans) {
                    ans = num;
                }
            }
        }
        return ans;
    }

    /**
     * 1769. 移动所有球到每个盒子所需的最小操作数
     * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
     * <p>
     * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
     * <p>
     * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
     * <p>
     * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
     * 示例 1：
     * 输入：boxes = "110"
     * 输出：[1,1,3]
     * 解释：每个盒子对应的最小操作数如下：
     * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
     * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
     * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
     * 示例 2：
     * 输入：boxes = "001011"
     * 输出：[11,8,5,4,3,4]
     * 提示：
     * n == boxes.length
     * 1 <= n <= 2000
     * boxes[i] 为 '0' 或 '1'
     */
    public int[] minOperations(String boxes) {
        int left = boxes.charAt(0) - '0';//表示左边的小球数量
        int right = 0;//表示右边的小球数量
        int operation = 0;//表示到当前盒子需要的操作数
        for (int i = 1; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                operation += i;
            }
        }
        int[] ans = new int[boxes.length()];
        ans[0] = operation;
        for (int i = 1; i < boxes.length(); i++) {
            operation += left - right;
            ans[i] = operation;
            if (boxes.charAt(i) == '1') {
                left++;
                right--;
            }
        }
        return ans;
    }

    /**
     * 1779. 找到最近的有相同 X 或 Y 坐标的点
     * 给你两个整数 x 和 y ，表示你在一个笛卡尔坐标系下的 (x, y) 处。同时，在同一个坐标系下给你一个数组 points ，
     * * 其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点。当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时，我们称这个点是 有效的 。
     * 请返回距离你当前位置 曼哈顿距离 最近的 有效 点的下标（下标从 0 开始）。如果有多个最近的有效点，请返回下标 最小 的一个。如果没有有效点，请返回 -1 。
     * 两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 abs(x1 - x2) + abs(y1 - y2) 。
     * 示例 1：
     * 输入：x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
     * 输出：2
     * 解释：所有点中，[3,1]，[2,4] 和 [4,4] 是有效点。有效点中，[2,4] 和 [4,4] 距离你当前位置的曼哈顿距离最小，都为 1 。[2,4] 的下标最小，所以返回 2 。
     * 示例 2：
     * 输入：x = 3, y = 4, points = [[3,4]]
     * 输出：0
     * 提示：答案可以与你当前所在位置坐标相同。
     * 示例 3：
     * 输入：x = 3, y = 4, points = [[2,3]]
     * 输出：-1
     * 解释：没有 有效点。
     * 提示：
     * 1 <= points.length <= 104
     * points[i].length == 2
     * 1 <= x, y, ai, bi <= 104
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int len = points.length;
        int ans = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            if (x1 == x || y1 == y) {
                int dis = Math.abs(x1 - x) + Math.abs(y1 - y);
                if (dis < min) {
                    min = dis;
                    ans = i;
                }
            }
        }
        return ans;
    }

    /**
     * 2180. 统计各位数字之和为偶数的整数个数
     * 简单
     * 给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。
     * 正整数的 各位数字之和 是其所有位上的对应数字相加的结果。
     * <p>
     * 示例 1：
     * 输入：num = 4
     * 输出：2
     * 解释：
     * 只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。
     * 示例 2：
     * 输入：num = 30
     * 输出：14
     * 解释：
     * 只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是：
     * 2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
     * 提示：
     * 1 <= num <= 1000
     *
     * @param num
     * @return
     */
    public int countEven(int num) {
        int cnt = 0;
        while (num > 0) {
            int cur = num;
            int sum = 0;
            while (cur > 0) {
                sum += cur % 10;
                cur /= 10;
            }
            if (sum % 2 == 0) {
                cnt++;
            }
            num--;
        }
        return cnt;
    }

    /**
     * 1658. 将 x 减到 0 的最小操作数
     * 中等
     * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
     * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
     * 示例 1：
     * 输入：nums = [1,1,4,2,3], x = 5
     * 输出：2
     * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
     * 示例 2：
     * 输入：nums = [5,6,7,8,9], x = 4
     * 输出：-1
     * 示例 3：
     * 输入：nums = [3,2,20,1,1,3], x = 10
     * 输出：5
     * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
     * 提示：
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 104
     * 1 <= x <= 109
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int target = -x;
        for (int i : nums) {
            target += i;
        }
        if (target < 0) {
            return -1;
        }
        int ans = -1;
        int left = 0;
        int sum = 0;
        int n = nums.length;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left++];
            }
            if (sum == target) {
                ans = Math.max(ans, right - left + 1);
            }
        }
        return ans < 0 ? -1 : n - ans;
    }
}

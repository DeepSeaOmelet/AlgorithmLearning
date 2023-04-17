package com.test.jie.leetCode.dp;

import java.util.Arrays;

/**
 * 大问题分解成小问题
 */
public class dpDemo {
    public static void main(String[] args) {
        dpDemo dp = new dpDemo();
//        dp._01bagProblemDemo(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
//        dp._01bagProblemDemo_1D(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
        testCompletePack();
        System.out.println();
        testCompletePack2();
    }

    /**
     * * 完全背包测试 代码
     */
    private static void testCompletePack() {
        //二维的滚动数组
        int[] weight = {2, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[][] dp = new int[weight.length + 1][bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                if (j < weight[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j - weight[i]] + value[i]);
                }
            }
        }
    }

    private static void testCompletePack2() {
        //一维的滚动数组
        int[] weight = {2, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = weight[i]; j <= bagWeight; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
    }

    /**
     * 474. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     * 示例 1：
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     * 示例 2：
     * 输入：strs = ["10", "0", "1"], m = 1, n = 1
     * 输出：2
     * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
     * 提示：
     * 1 <= strs.length <= 600
     * 1 <= strs[i].length <= 100
     * strs[i] 仅由 '0' 和 '1' 组成
     * 1 <= m, n <= 100
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //多维度的01背包，但不是多重背包
        //背包容量有两种，那么作为之前01背包滚动数组的一维也应成为二维
        int[][] dp = new int[m + 10][n + 10];
        for (int i = 0; i < strs.length; i++) {
            //这里是处理转化为01背包的该物品的重量
            String cur = strs[i];
            int a = 0;
            int b = 0;
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) == '1') {
                    a++;
                } else b++;
            }
            //依然是类似于01背包滚动数组的后到前的遍历，不过是二维
            for (int j = m; j >= b; j--) {
                for (int k = n; k >= a; k--) {
                    //递推公式，经典01背包递推，这里的value是根据题意的求出最大子集的长度
                    dp[j][k] = Math.max(dp[j][k], dp[j - b][k - a] + 1);
                }
            }
        }
        return dp[m][n];
    }

    class findTargetSumWaysClass {
        /**
         * 494. 目标和
         * 给你一个整数数组 nums 和一个整数 target 。
         * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
         * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
         * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
         * 示例 1：
         * 输入：nums = [1,1,1,1,1], target = 3
         * 输出：5
         * 解释：一共有 5 种方法让最终目标和为 3 。
         * -1 + 1 + 1 + 1 + 1 = 3
         * +1 - 1 + 1 + 1 + 1 = 3
         * +1 + 1 - 1 + 1 + 1 = 3
         * +1 + 1 + 1 - 1 + 1 = 3
         * +1 + 1 + 1 + 1 - 1 = 3
         * 示例 2：
         * 输入：nums = [1], target = 1
         * 输出：1
         * 提示：
         * 1 <= nums.length <= 20
         * 0 <= nums[i] <= 1000
         * 0 <= sum(nums[i]) <= 1000
         * -1000 <= target <= 1000
         *
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {
//            int sum = 0;
            for (int x : nums) {
                target += x;
            }
            //target会是负数，(sum + target)%2==1说明筹不齐负数
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            //求出正数集合，负数集合，target=正数-负数，负数+正数=sum
//            int bagWeight = (sum + target)/2;
            target /= 2;
            //现在问题变成nums中有多少种和为 bagWeight的子集合
            //1  01背包 bagWeight，nums是物品
            int[] dp = new int[target + 1];
            //2 怎么初始化？
            dp[0] = 1;
            //3 j表示背包容量
            for (int i = 0; i < nums.length; i++) {
                for (int j = target; j >= nums[i]; j--) {
                    //4 递推公式？ dp[j]表示对于容量为j的背包有j-nums[i]种方法装满
                    dp[j] += dp[j - nums[i]];
                }
            }
            System.out.println(Arrays.toString(dp));
            return dp[target];
        }

        public int findTargetSumWays_2(int[] nums, int target) {
            //从数组中的每个元素有2中状态，加上是统计数量，基本上可以转换成01背包
            //怎么转换成01背包，假如加法的总和为x，那么减法对应的总和就是sum-x。题目要求的是x-(sum-x)=target，那么x=(sum+target)/2
            //这样题目就转换为，装满容量为x背包类似于「分割等和子集」
            //考虑(sum+S)/2的是否需要向下取整，本题不需要
            //之前的题目是求最多能装多少，这次变成装满有多少种
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
                //此时没有方案,target超出sum的范围，正数和不为整数
                return 0;
            }
            int bagSize = (sum + target) / 2;
            //dp数组
            int[] dp = new int[bagSize + 1];
            //初始化
            dp[0] = 1;
            //遍历顺序
            for (int i = 0; i < nums.length; i++) {
                for (int j = bagSize; j >= nums[i]; j--) {
                    //递推公式
                    dp[j] += dp[j - nums[i]];
                }
            }
            return dp[bagSize];
        }

        //dfs
        public int findTargetSumWays2(int[] nums, int target) {
            dfs(nums, target, 0, 0);
            return ans;
        }

        int ans = 0;

        public void dfs(int[] nums, int target, int idx, int sum) {
            if (idx == nums.length) {
                if (sum == target) {
                    ans++;
                }
                return;
            }
            dfs(nums, target, idx + 1, sum + nums[idx]);
            dfs(nums, target, idx + 1, sum - nums[idx]);
        }
    }

    /**
     * 1049. 最后一块石头的重量 II
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     * 示例 1：
     * 输入：stones = [2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
     * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
     * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
     * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     * 示例 2：
     * 输入：stones = [31,26,33,21,40]
     * 输出：5
     * 提示：
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int avg = sum / 2;
        int[] dp = new int[avg + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = avg; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[avg] - dp[avg];
    }

    public int lastStoneWeightII2(int[] stones) {
        //怎么转换成背包问题：
        //切入点：最小的可能重量，因为两个石头会合并成两个石头的绝对值，保证最小可能，就需要将石头组分成两组，使得两个数组的元素和尽量相等
        // 然后每一组各一块组成相似的一对，进行粉碎
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        //target向下取整
        int target = sum / 2;
        //dp数组
        int[] dp = new int[target + 1];
        //初始化--初始为非负整数的最小值
        //遍历顺序，和分割等和子集差不多
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                //递推公式
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        //返回结果是大数组和-小数组和，就是(sum-dp[target])-dp[target]
        return sum - 2 * dp[target];
    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     * 提示：
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {//类似物品
            for (int j = sum; j >= nums[i]; j--) {//类似重量
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
            if (dp[sum] == sum) {
                return true;
            }
        }
        return dp[sum] == sum;
    }

    public boolean canPartition2(int[] nums) {
        //01背包问题    --这里的重量和价值直接重叠了
        //滚动数组版
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        //dp数组，dp[i]表示用nums中数字所能组成的最大的和
        int[] dp = new int[sum + 1];
        //初始化--初始为非负整数的最小值
        //遍历顺序
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                //递推公式  --一维数组版，因为相对于背包问题，这里重量和价值重叠了，所以是dp[j - nums[i]] + nums[i]
                //二维为dp[j] = Math.max(dp[i-1][j], dp[i-1][j - nums[i]] + nums[i]);
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (dp[sum] == sum) {
                    return true;
                }
            }
        }
        return dp[sum] == sum;
    }

    /**
     * 01背包--一维
     */
    public void _01bagProblemDemo_1D(int[] weight, int[] value, int bagWeight) {
        //1
        int[] dp = new int[bagWeight + 1];
        //2
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[i] = value[0];
        }
        //3
        for (int i = 1; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
    }

    public void _01bagProblemDemo_1D2(int[] weight, int[] value, int bagWeight) {
        //dp数组
        int[] dp = new int[bagWeight + 1];
        //初始化--默认为0
        for (int i = 0; i < weight.length; i++) {//遍历物品
            //后到前遍历
            for (int j = bagWeight; j >= weight[i]; j--) {//遍历背包
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
    }

    /**
     * 01背包--二维*
     */
    public void _01bagProblemDemo(int[] weight, int[] value, int bagWeight) {
        //1
        int[][] dp = new int[weight.length][bagWeight + 1];
        //2 初始化
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[0][i] = value[0];
        }
        //3遍历顺序
        //先物品再背包
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                if (weight[i] < j) {
                    //可放
                    //4
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                } else {
                    //不放
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //5
        for (int[] f : dp) {
            System.out.println(Arrays.toString(f));
        }
//        return dp[weight.length-1][bagWeight-1];
    }

    public void _01bagProblemDemo2(int[] weight, int[] value, int bagWeight) {
        //dp数组
        int[][] dp = new int[weight.length + 1][bagWeight + 1];
        //初始化
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[0][i] = value[0];
        }
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        for (int[] f : dp) {
            System.out.println(Arrays.toString(f));
        }
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * 示例 1：
     * 输入：n = 3
     * 输出：5
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 提示：
     * 1 <= n <= 19
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        //1
        int[] dp = new int[n + 1];
        //2
        dp[0] = 1;
        dp[1] = 1;
        //3
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }
        return dp[n];
    }

    public int numTrees2(int n) {
        //dp[i]为i个节点的二叉搜索树数量
        //递推公式：dp[i] += dp[j - 1] * dp[i - j]; ，j-1 为j为头结点左⼦树节点数量，i-j 为以j为头结点右⼦树节点数量
        //  dp[i] 为 以x(1<=x<=i)为结点的二叉搜索树数量之和，每个以x为结点的计算公式为 x = dp[x - 1] * dp[i - x]
        //初始化，只需要初始化dp[0]就可以了
        //确定遍历顺序 因为dp[i]依赖前面的状态来遍历，所以从前往后遍历
        //  ⾸先⼀定是遍历节点数，从递归公式：dp[i] += dp[j - 1] * dp[i - j]可以看出，节点数为i的状态是依靠 i之前节点数的状态。
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //分别枚举以j为根节点的数量
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        //5.打印数组
        //i:    1   2   3   4   5
        //dp[i] 1   1   2   5   14
        return dp[n];
    }

    /**
     * 343. 整数拆分
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     * 示例 1:
     * 输入: n = 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     * 输入: n = 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * 提示:
     * 2 <= n <= 58
     */
    public int integerBreak(int n) {
        //贪心
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        ans *= n;
        return ans;
    }

    public int integerBreak2(int n) {
        //定义
        //初始化
        //遍历顺序
        //递推公式
        //dp[i] = Math.max(dp[i-x] * dp[x],dp[i])?
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
//                dp[i] = Math.max(Math.max((i - j) * j, dp[i]), dp[i - j] * j);
                dp[i] = Math.max(dp[i - j] * j, dp[i]);
            }
//            System.out.println(dp[i]);
        }
        return dp[n];
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     * 提示：
     * m ==obstacleGrid.length
     * n ==obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] 为 0 或 1
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        //dp[i][j]表示到该位置有多少路径
        int[][] dp = new int[n][m];
        //初始化
        for (int i = 0; i < n; i++) {
            //向下
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else break;
        }
        for (int i = 0; i < m; i++) {
            //向右
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else break;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //1.确定dp数组，dp[i][j]代表到该点的路径总数
        int[][] dp = new int[m + 10][n + 10];
        //2.初始化，dp[i][0]和dp[0][i]都只有1次路径，当该点有障碍，结束往下遍历，因为不会到达那里
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] != 0) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 0) {
                break;
            }
            dp[0][i] = 1;
        }
        //4.遍历顺序，从上往下，从左到右
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                //3.递推公式，因为只能从上方和左方过去
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //5.返回结果
        return dp[m - 1][n - 1];
    }

    /**
     * https://leetcode.cn/problems/unique-paths/
     * 62. 不同路径
     */
    public int uniquePaths(int m, int n) {
        //2023年3月16日
        //dp[i][j]表示到这里有多少条路径
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n) {
        //1.确定dp数组，dp[i][j]代表到该点的路径总数
        int[][] dp = new int[m + 10][n + 10];
        //2.初始化，dp[i][0]和dp[0][i]都只有1次路径
        Arrays.fill(dp[0], 1);
        //4.遍历顺序，从上往下，从左到右
        for (int i = 1; i < m; i++) {
            //2.初始化
            dp[i][0] = 1;
            for (int j = 1; j < n; j++) {
                //3.递推公式，因为只能从上方和左方过去
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //5.返回结果
        return dp[m - 1][n - 1];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     * 示例 1：
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     * 示例 2：
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     * 提示：
     * 2 <= cost.length <= 1000
     * 0 <= cost[i] <= 999
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 2];
        dp[0] = 0;
        //你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            System.out.println(dp[i]);
        }
        return dp[len];
    }

    public int minCostClimbingStairs2(int[] cost) {
        //5步
        int n = cost.length;
        //1.确定dp数组，dp[i]:到i阶的最小总消耗
        int[] dp = new int[n + 10];
        //2.不用初始化，因为int数组默认为0，dp[0]=dp[1]=0
        //3.递推公式：dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2])
        //3.1因为第i阶只能从第i-1阶和i-2阶上去，所以再取两之间最小就是当前i阶的最小。而之前就这样类推
        //4.遍历顺序，从前往后
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
        }
        //5.返回结果
        return dp[n];
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     * 提示：
     * 1 <= n <= 45
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //只能走1或2个台阶
        //到当前楼梯 所需要的方法次数为，上一阶的次数+上二阶的次数。
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs2(int n) {
        //因为只有两种步伐，一次1阶，一次2阶，所以到当前n阶，就是到n-1阶和到n-2阶之和
        //这里可以将n阶分解成n-1阶要多少...1阶需要多少步，之后遍历顺序就是从1阶遍历到n阶
        //就是1阶要1种，2阶是2种，3阶就是1阶和2阶的基础上计算
        //递推公式就是F[n] = F[n-1] + F[n-2]  **难点**
        //dp[i]:达到i阶有dp[i]种方法
        int[] dp = new int[n + 10];
        //初始化
        dp[1] = 1;
        dp[2] = 2;
        //遍历顺序
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 509. 斐波那契数
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     * 输入：n = 3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     * 输入：n = 4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     * 提示：
     * 0 <= n <= 30
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            int cur = dp[1] + dp[0];
            dp[1] = dp[0];
            dp[0] = cur;
        }
        return dp[0];
//        int[] dp = new int[n + 2];
//        dp[0] = 0;
//        dp[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
    }

    public int fib2(int n) {
        //1递推公式：F(n) = F(n - 1) + F(n - 2)，其中 n > 1
        //2确定dp[i]含义,如上
        if (n < 1) {
            return n == 0 ? 0 : 1;
        }
        int[] dp = new int[2];
        //3dp数组如何初始化
        dp[0] = 1;
        //4遍历顺序--从前向后
        for (int i = 2; i <= n; i++) {
            int cur = dp[0] + dp[1];
            dp[1] = dp[0];
            dp[0] = cur;
        }
        return dp[0];
    }
}

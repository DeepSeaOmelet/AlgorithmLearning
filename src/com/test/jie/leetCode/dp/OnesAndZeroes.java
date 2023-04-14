package com.test.jie.leetCode.dp;

/**
 * 474. 一和零 2023年4月13日  https://leetcode.cn/problems/ones-and-zeroes/
 * 中等
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * <p>
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        //二维01背包，需要填充m和n，那么strs就是物品，里面的01数量就是物品价值
        //按照01背包的写法
        int zeroNum, oneNum;
        //1
        int[][] f = new int[m + 1][n + 1];
        //2
        f[0][0] = 0;
        //3
        for (int i = 0; i < strs.length; i++) {//每个物品遍历
            zeroNum = 0;
            oneNum = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else oneNum++;
            }
            //4
            for (int j = m; j >= zeroNum; j--) {
                for (int k = n; k >= oneNum; k--) {
                    //物品价值为1，指当前元素符合条件
                    f[j][k] = Math.max(f[j][k], f[j - zeroNum][k - oneNum] + 1);
                }
            }
        }
        return f[m][n];
    }
}

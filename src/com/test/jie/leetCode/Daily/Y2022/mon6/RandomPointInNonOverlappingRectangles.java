package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Random;

/**
 * 497. 非重叠矩形中的随机点
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 * <p>
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 * <p>
 * 请注意 ，整数点是具有整数坐标的点。
 * <p>
 * 实现 Solution 类:
 * <p>
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * 输出:
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 * <p>
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 * <p>
 * 提示：
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -109 <= ai < xi <= 109
 * -109 <= bi < yi <= 109
 * xi - ai <= 2000
 * yi - bi <= 2000
 * 所有的矩形不重叠。
 * pick 最多被调用 104 次。
 */
public class RandomPointInNonOverlappingRectangles {
    class Solution {
        //利用前缀和 和 二分法来保证->[先随机使用哪个矩形，再随机该矩形内的点]这个做法前者随机使用哪个矩形，是等概率的
        int[][] rs;
        int[] sum;
        int len;
        Random ran = new Random();

        public Solution(int[][] rects) {
            rs = rects;
            len = rs.length;
            sum = new int[len + 1];
            for (int i = 1; i <= len; i++) {
                //这里的 +1 是为了包括没面积但是有周长的图 如[0,0,0,0]
                sum[i] = sum[i - 1] + (rs[i - 1][2] - rs[i - 1][0] + 1) * (rs[i - 1][3] - rs[i - 1][1] + 1);
            }
        }

        public int[] pick() {
            //先随机使用哪个矩形,+1应该是考虑random函数的右开区间
            int val = ran.nextInt(sum[len]) + 1;
            //二分定位矩形
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= val) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            //再随机该矩形内的点
            int[] cur = rs[r - 1];
            int x = ran.nextInt(cur[2] - cur[0] + 1) + cur[0];
            int y = ran.nextInt(cur[3] - cur[1] + 1) + cur[1];
            return new int[]{x,y};
        }
        /**
         * Your Solution object will be instantiated and called as such:
         * Solution obj = new Solution(rects);
         * int[] param_1 = obj.pick();
         */
    }
}

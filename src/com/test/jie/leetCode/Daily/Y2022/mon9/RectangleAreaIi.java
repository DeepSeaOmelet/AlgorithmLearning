package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2022/09/16
 * `850. 矩形面积 II
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，
 * 其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 * <p>
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 * <p>
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 * <p>
 * 示例 1：
 * 输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示，三个矩形覆盖了总面积为6的区域。
 * 从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
 * 从(1,0)到(2,3)，三个矩形都重叠。
 * 示例 2：
 * <p>
 * 输入：rectangles = [[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 10^18 对 (10^9 + 7) 取模的结果， 即 49 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= xi1, yi1, xi2, yi2 <= 10^9
 * 矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
 */
public class RectangleAreaIi {
    public static void main(String[] args) {

    }

    //扫描线
    int MOD = (int) 1e9 + 7;

    public int rectangleArea(int[][] rectangles) {
        List<Integer> list = new ArrayList<>();
        //x轴排序
        for (int[] re : rectangles) {
            list.add(re[0]);
            list.add(re[2]);
        }
        Collections.sort(list);
        long ans = 0;
        for (int i = 1; i < list.size(); i++) {
            int a = list.get(i - 1);
            int b = list.get(i);
            int len = b - a;
            if (len == 0) continue;
            //根据排序好的x轴获得占用该x轴区间的长方形
            List<int[]> line = new ArrayList<>();
            for (int[] re : rectangles) {
                if (re[0] <= a && re[2] >= b) {
                    line.add(new int[]{re[1], re[3]});
                }
            }
            Collections.sort(line, (l1, l2) -> {
                return l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1];
            });
            long tot = 0;
            long l = -1, r = -1;
            //计数该x轴区间的矩形面积
            for (int[] cur : line) {
                if (r < cur[0]) {
                    //断层
                    tot += r - l;
                    l = cur[0];
                    r = cur[1];
                } else if (r < cur[1]) {
                    //重叠
                    r = cur[1];
                }
            }
            tot += r - l;
            ans += tot * len;
            ans %= MOD;
        }
        return (int) ans;
    }
}
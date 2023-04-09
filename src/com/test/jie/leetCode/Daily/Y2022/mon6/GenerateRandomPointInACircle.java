package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Random;

/**
 * 478. 在圆内随机生成点
 * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
 * <p>
 * 实现 Solution 类:
 * Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
 * randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 * <p>
 * 示例 1：
 * 输入:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 * 解释:
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint ();//返回[-0.02493，-0.38077]
 * solution.randPoint ();//返回[0.82314,0.38945]
 * solution.randPoint ();//返回[0.36572,0.17248]
 * <p>
 * 提示：
 * <p>
 * 0 < radius <= 108
 * -107 <= x_center, y_center <= 107
 * randPoint 最多被调用 3 * 104 次
 */
public class GenerateRandomPointInACircle {
    public static void main(String[] args) {
        GenerateRandomPointInACircle generateRandomPointInACircle = new GenerateRandomPointInACircle();
        generateRandomPointInACircle.Solution(1.0, 0.0, 0.0);
        System.out.println(Math.random());
    }

    //不能使用x,y随机的方式，因为这是会返回矩形的坐标
    //思考：既然是圆，可以以点到圆心的距离len[0,r]中进行随机，然后是夹角ang[0,2π]中随机
    //然后根据这两个元素计算出对应x,y坐标，
    // 这样可以确保随机出来的点在圆内，但并非【等概率】
    //在不考虑夹角的情况下，我们本质是在 [0, r] 范围内随机，这在「一维」上「等概率」是成立的，
    // 因为满足「任意连续段中点被抽到的次数与总次数的比例」与「该连续段长度与总长度的比例」。
    //但在圆中并非如此，不考虑夹角时，「任意连续段 len 与总长度 r 的比例」和「len 对应面积与总面积比例」并不相等。
    //例如 len 有 1/2 的概率取到小于等于r/2的值，而半径为 r/2 扫过的面积仅为总面积的1/4
    //因此我们的 len 不能直接在 [0, r] 范围内随机，为了消除这种一维转圆导致的「等概率」失效，
    // 我们可以从 [0, r^2]内随机再开平方，从而确保距离与面积比例一致。
    //https://leetcode.cn/problems/generate-random-point-in-a-circle/solution/by-ac_oier-btkm/
    Random ran = new Random();
    double r;
    double x;
    double y;

    public void Solution(double radius, double x_center, double y_center) {
        this.r = radius;
        this.x = x_center;
        this.y = y_center;
    }

    public double[] randPoint() {
        double len = Math.sqrt(ran.nextDouble() * r * r);
        double ang = ran.nextDouble() * 2 * Math.PI;
        double nx = x + len * Math.cos(ang);
        double ny = y + len * Math.sin(ang);
        return new double[]{nx, ny};
    }
}

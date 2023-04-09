package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2022/07/13
 * 735. 行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * <p>
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * <p>
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * <p>
 * 提示：
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {
    public static void main(String[] args) {

    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int ast : asteroids) {
            boolean ok = true;
            //模拟碰撞
            //[1,-2]会碰撞 [-2,1]不会碰撞，[-1,2,3,4] <-栈入的方向
            //什么情况不会发现碰撞，1.向右的星球右方 没有的方向向左的星球 2.星球没了 3.下一个星球不是向左的
            while (ok && !deque.isEmpty() && deque.peekLast() > 0 && ast < 0) {
                int a = deque.peekLast();
                int b = -ast;
                if (a <= b) {
                    deque.pollLast();
                }
                if (a >= b) {
                    //该次行星已销毁，不加入
                    ok = false;
                }
            }
            if (ok) {
                deque.addLast(ast);
            }
        }
        int[] ans = new int[deque.size()];
        int i = 0;
        while (!deque.isEmpty()){
            ans[i++] = deque.pollFirst();
        }
        return ans;
    }
}
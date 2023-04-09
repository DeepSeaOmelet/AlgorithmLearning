package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 * <p>
 * 自除数是指可以被它包含的每一位数整除的数。
 * 例如，128 是一个 自除数 ，因为128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * <p>
 * 给定两个整数left和right ，返回一个列表，列表的元素是范围[left, right]内所有的 自除数 。
 * <p>
 * 示例 1：
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 示例 2:
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 * <p>
 * 提示：
 * <p>
 * 1 <= left <= right <= 104
 */
public class SelfDividingNumbers {
    public static void main(String[] args) {
        SelfDividingNumbers numbers = new SelfDividingNumbers();
        System.out.println(numbers.selfDividingNumbers(1, 22));
        System.out.println(numbers.selfDividingNumbers(47, 85));
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            boolean ok = true;
            int cur = i;
            while (cur != 0) {
                int k = cur % 10;
                if (k == 0 || i % k != 0) {
                    ok = false;
                    break;
                }
                cur /= 10;
            }
            if (ok) list.add(i);
        }
        return list;
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/09/13
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * <p>
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * <p>
 * 给定数字的范围是 [0, 10^8]
 */
public class MaximumSwap {
    public static void main(String[] args) {
        MaximumSwap m = new MaximumSwap();
        System.out.println(m.maximumSwap(2736));
        System.out.println(m.maximumSwap(9973));
        System.out.println(m.maximumSwap(98368));
    }

    public int maximumSwap(int num) {
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        int n = list.size();
        int ans = 0;
        int[] idx = new int[n];//存下标
        for (int i = 0, maxIdx = 0; i < n; i++) {
            //低位到高位
            if (list.get(i) > list.get(maxIdx)) {
                maxIdx = i;//意味着[0,i]maxIdx为最大
            }
            idx[i] = maxIdx;
        }
        for (int i = n - 1; i >= 0; i--) {
            //当有数值相同的多个大数时，选择低位的数字
            if (list.get(idx[i]) != list.get(i)) {
                int t = list.get(i);
                list.set(i, list.get(idx[i]));
                list.set(idx[i], t);
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            ans *=10;
            ans +=  list.get(i);
        }
        return ans;
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/10/18
 * 902. 最大为 N 的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。
 * 例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 * <p>
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 * <p>
 * 示例 1：
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * 示例 2：
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 * 示例 3:
 * 输入：digits = ["7"], n = 8
 * 输出：1
 * <p>
 * 提示：
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i] 是从 '1' 到 '9' 的数
 * digits 中的所有值都 不同
 * digits 按 非递减顺序 排列
 * 1 <= n <= 10^9
 */
public class NumbersAtMostNGivenDigitSet {
    public static void main(String[] args) {

    }

    int[] nums;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        int len = digits.length;
        nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(digits[i]);
        }
        return dp(n);
    }

    private int dp(int x) {
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }
        int n = list.size();
        int m = nums.length;
        int ans = 0;
        //我们将组成 [1, x] 的合法数分成三类：
        //
        //位数和 x 相同，且最高位比 x 最高位要小的，这部分统计为 res1；
        //位数和 x 相同，且最高位与 x 最高位相同的，这部分统计为 res2；
        //位数比 x 少，这部分统计为 res3。
        //res3和res1容易解决
        //位数和x相同的
        for (int i = n - 1, p = 1; i >= 0; i--, p++) {
            int cur = list.get(i);
            int l = 0, r = m - 1;//二分用
            //二分找最高位
            while (r > l) {
                int mid = l + r + 1 >> 1;
                if (nums[mid] <= cur) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (nums[r] > cur) {
                break;
            } else if (nums[r] == cur) {
                //不包括nums[r]本身
                //当前位置有r种选择，而后的每个位置都可选择nums数组的所有数字，有m个，共有n-p个位置
                ans += r * (int) Math.pow(m, (n - p));
                if (i == 0) {
                    ans++;//到末尾，包括与所给n相同的数字
                }
            } else if (nums[r] < cur) {
                //当前位置有r+1种选择，而后的每个位置都可选择nums数组的所有数字，有m个，共有n-p个位置
                ans += (r + 1) * (int) Math.pow(m, (n - p));
                break;//后面不用讨论
            }
        }
        //位数不和x相同的
        for (int i = 1, tot = 1; i < n; i++) {
            int cur = tot * m;
            ans += cur;
            tot = cur;
        }
        return ans;
    }
}
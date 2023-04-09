package com.test.jie.leetCode.Daily.Y2022.mon4;

/**
 * 旋转函数
 * <p>
 * 给定一个长度为 n 的整数数组nums。
 * <p>
 * 假设arrk是数组nums顺时针旋转 k 个位置后的数组，我们定义nums的 旋转函数F为：
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回F(0), F(1), ..., F(n-1)中的最大值。
 * <p>
 * 生成的测试用例让答案符合32 位 整数。
 * <p>
 * 示例 1:
 * 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * 示例 2:
 * 输入: nums = [100]
 * 输出: 0
 * <p>
 * 提示:
 * <p>
 * n == nums.length
 * 1 <= n <= 105
 * -100 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-function
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotateFunction {
    public static void main(String[] args) {
        RotateFunction function = new RotateFunction();
        System.out.println(function.maxRotateFunction(new int[]{4, 3, 2, 6}));
    }

    /**
     * SUM = A0 + A1 + A2 + A3
     * nums = [A0,A1,A2,A3]
     * F0 = 0*A0+ 1*A1+ 2*A2+ 3*A3
     * F1 = 0*A3+ 1*A0+ 2*A1+ 3*A2
     * = F0 + A0 + A1 +A2 -A3*3
     * = F0 + SUM - A3*4
     * F2 = 0*A2+ 1*A3+ 2*A0+ 3*A1
     * = F1 + A0 + A1 + A3 - 3*A2
     * = F1 + SUM - 4*A2
     * F3 = F2 + SUM - 4*A1
     * <p>
     * F(i) = F(i-1) + sum - n * A(n-i)
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int sum = 0;
        int f = 0;
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            f += nums[i] * i;//计算f(0)
        }
        ans = f;
        for (int i = 1; i < len; i++) {
            f = f + sum - len * nums[len - i];//迭代f
            ans = Math.max(ans, f);
        }
        return ans;
    }

    /**
     * 前缀和 + 滑动窗口
     * 为了方便，我们将nums的长度记为n。
     * 题目要对[旋转数组]做逻辑，容易想到将nums进行复制拼接，得到长度为 2*n 的新数组，在新数组上任意一个长度为n的滑动窗口都对应了一个旋转数组。
     * 然后考虑在窗口的滑动过程中，计算结果会如何变化，假设当前我们处理到下标为[i，i+n-1]的滑动窗口，根据题意，当前结果为：
     * cur=nums[i]*0+nums[i+1]*1+..+nums[i+n-1]*（n-1）
     * 当窗口往后移动一位，也就是窗口的右端点来到i+n的位置，左端点来到i+1的位置。
     * 我们需要增加[新右端点」的值，即增加nums[i+n]*（n-1），同时减去[旧左端点]的值，即减少nums[i]*0（固定为0），然后更新新旧窗口的公共部分[i+1，i+n-1]。
     * 不难发现，随着窗口的逐步右移，每一位公共部分的权值系数都会进行减一。
     * nums[i+1]*1+nums[i+2]*2+.+nums[i+n-1]*（n-1）
     * 变为
     * nums[i+1]*0+nums[i+2]*1+..+nums[i+n-1]*（n-2）
     * 因此，公共部分的差值为[公式]，nums[idx]，这引导我们可以使用前缀和进行优化。
     * 至此，我们从旧窗口到新窗口的过渡，都是O（1），整体复杂度为O（n）。
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction2(int[] nums) {
        int n = nums.length;
        int[] sum = new int[2 * n + 10];
        //1.预处理滑动窗口需要减掉的中间部分
        for (int i = 1; i < 2 * n; i++) {
            sum[i] = sum[i - 1] + nums[(i - 1) % n];
        }
        int ans = 0;
        //2.预处理，计算滑动窗口初始数组的和
        for (int i = 0; i < n; i++) {
            ans += nums[i] * (i);
        }
        //3.开始滑动窗口，从第二个开始(所以是n+1)，减去前面和中间差值，加上后面
        for (int i = n + 1, cur = ans; i < 2 * n; i++) {
            cur -= sum[i - 1] - sum[i - n];
            cur += sum[(i - 1) % n] * (n - 1);
            if (cur > ans) ans = cur;
        }
        return ans;
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/07/03
 * <p>
 * 556. 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，
 * 其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。
 * 如果不存在这样的正整数，则返回 -1 。
 * <p>
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * <p>
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class NextGreaterElementIii {
    public static void main(String[] args) {

    }

    public int nextGreaterElement(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int len = nums.size();
        int idx = -1;
        //找到第一个「降序」的位置 idx
        for (int i = 0; i < len - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                idx = i + 1;
                break;
            }
        }
        if (idx == -1) {
            return -1;
        }
        //然后从 [0, idx)(不是升序就是不变 ，所以是从最小位往前找出升序的最后，或者如果都是不变，那就是最后一位) 范围内找一个比 nums[idx] 要大的最小数与其进行互换
        for (int i = 0; i < idx; i++) {
            if (nums.get(i) > nums.get(idx)) {
                swap(nums, i, idx);
                break;
            }
        }
        //之后就是  最小整数
        for (int l = 0, r = idx - 1; l < r; l++, r--) {
            swap(nums, l, r);
        }
        //转换成数字，超出32位返回-1
        long ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            ans = ans * 10 + nums.get(i);
        }
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    void swap(List<Integer> nums, int a, int b) {
        int t = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, t);
    }
}
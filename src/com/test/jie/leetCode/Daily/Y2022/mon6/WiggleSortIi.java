package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.Arrays;

/**
 * 324. 摆动排序 II
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * <p>
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class WiggleSortIi {
    public static void main(String[] args) {
        WiggleSortIi sortIi = new WiggleSortIi();
        int[] ints = {4, 5, 5, 6};
        sortIi.wiggleSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public void wiggleSort2(int[] nums) {
        // 排序 + 复制 + 穿插写入
        Arrays.sort(nums);
        int len = nums.length;
        int[] clone = nums.clone();
        int half = (len + 1) / 2;
        //小
        for (int i = 0; i < half; i++) {
            nums[2 * i] = clone[half - 1 - i];
        }
        //大
        for (int i = 0; i < len - half; i++) {
            nums[2 * i + 1] = clone[len - 1 - i];
        }
    }

    int[] nums;
    int n;

    public void wiggleSort(int[] nums) {

    }

    public int quickSelect(int l, int r, int k) {
        if (l == r) return nums[l];
        int x = nums[l + r >> 1], i = l - 1, j = r + 1;
        while (i<j){
            do i++; while (nums[i]<x);
            do j--; while (nums[j]>x);
            if (i<j) swap(i,j);
        }
        int cnt = j-l+1;
        if(k<=cnt) return quickSelect(l,j,k);
        else return quickSelect(j+1,r,k-cnt);
    }

    public void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
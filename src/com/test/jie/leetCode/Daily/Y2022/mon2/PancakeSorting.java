package com.test.jie.leetCode.Daily.Y2022.mon2;

import java.util.ArrayList;
import java.util.List;

/**
 * 煎饼排序
 * <p>
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * <p>
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
 * <p>
 * 示例 1：
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 * <p>
 * 提示：
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 */
public class PancakeSorting {
    public static void main(String[] args) {
        PancakeSorting sorting = new PancakeSorting();
        System.out.println(sorting.pancakeSort(new int[]{3, 2, 4, 1}));
        System.out.println(sorting.pancakeSort(new int[]{1, 2, 3}));
    }

    public List<Integer> pancakeSort(int[] arr) {
        //获取最大的值，将其往后排(翻转)，类似冒泡
        int n = arr.length;
        int[] indexs = new int[n + 1];
        //获取数值对应下标
        for (int i = 0; i < n; i++) {
            indexs[arr[i]] = i;
        }
        List<Integer> ans = new ArrayList<>();
        //i为数值 ，idx为数值对应的下标
        for (int i = n; i >= 1; i--) {
            int idx = indexs[i];
            //若已在对应的位置，跳过
            if (idx == i - 1) {
                continue;
            }
            //若该数不在下标为0的位置,使其转移到下标0的位置
            if (idx != 0) {
                ans.add(idx + 1);
                reverse(arr, 0, idx, indexs);
            }
            ans.add(i);
            reverse(arr, 0, i - 1, indexs);
        }
        return ans;
    }

    //反转同时调整下标数组
    public void reverse(int[] arr, int i, int j, int[] indexs) {
        while (i < j) {
            indexs[arr[i]] = j;
            indexs[arr[j]] = i;
            int tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
    }
}

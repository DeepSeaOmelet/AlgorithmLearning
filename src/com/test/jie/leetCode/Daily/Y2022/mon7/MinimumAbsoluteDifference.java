package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2022/07/04
 * 1200. 最小绝对差
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * <p>
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 * <p>
 * <p>
 * 提示：
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int len = arr.length;
        for (int i = 1; i < len ; i++) {
            if (arr[i] - arr[i - 1] < min) {
                min = arr[i] - arr[i - 1];
                ans.clear();
            }
            if (arr[i] - arr[i - 1] == min){
            List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                list.add(arr[i]);
                ans.add(list);
            }
        }
        return ans;
    }


}
package com.test.jie.leetCode.Daily.Y2022.mon8;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/08/25
 * https://leetcode.cn/problems/find-k-closest-elements/
 * 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 */
public class FindKClosestElements {
    public static void main(String[] args) {

    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //双指针+二分
        int len = arr.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid] > x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        // 校准，二分法会得到一个小于等于 x 的数的下标，但是需要取得的中间数必然是和 x 的差值最小的那个数
        // 所以需要判断是否二分法得到的右侧数与 x 的差值更小，如果更小则将下标加一
        r = r + 1 < len && Math.abs(arr[r + 1] - x) < Math.abs(arr[r] - x) ? r + 1 : r;
        int i = r - 1, j = r + 1;
        while (j - i - 1 < k) {
            if (i >= 0 && j < len) {
                //比较哪个数更加接近x
                if (Math.abs(arr[j] - x) < Math.abs(arr[i] - x)) j++;
                else i--;
            } else if (i >= 0) {
                i--;
            } else {
                j++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int p = i + 1; p <= j - 1; p ++) {
            ans.add(arr[p]);
        }
        return ans;
    }
}
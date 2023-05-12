package com.test.jie.leetCode;

import java.util.*;

//单调栈
public class MonotoneStack {
}

/**
 * 739. 每日温度
 * 中等
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
class MonotoneStackSolution1 {
    public static void main(String[] args) {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        s.addLast(4);
        System.out.println(s);
        s.pollLast();
        System.out.println(s.peekLast());
        System.out.println(s);
    }

    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && temperatures[i] > temperatures[s.peekLast()]) {
                ans[s.peekLast()] = i - s.peekLast();
                s.pollLast();
            }
            s.add(i);
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        //递增 单调栈
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addLast(0);
        for (int i = 1; i < n; i++) {
            if (temperatures[s.peekLast()] > temperatures[i]) {
                s.addLast(i);
            } else if (temperatures[s.peekLast()] == temperatures[i]) {
                s.addLast(i);
            } else {
                while (!s.isEmpty() && temperatures[i] > temperatures[s.peekLast()]) {
                    ans[s.peekLast()] = i - s.peekLast();
                    s.pollLast();
                }
                s.addLast(i);
            }
        }
        return ans;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        //超时
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }
}

/**
 * 496. 下一个更大元素 I
 * 简单
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * 示例 2：
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 * <p>
 * 提示：
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 */
class MonotoneStackSolution2 {
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        //单调栈+hash
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> s = new LinkedList<>();
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
            ans[i] = -1;
        }
        for (int i = 0; i < nums2.length; i++) {
            while (!s.isEmpty() && nums2[i] > s.peekLast()) {
                int idx = map.getOrDefault(s.peekLast(), -1);
                if (idx != -1) {
                    ans[idx] = nums2[i];
                }
                s.pollLast();
            }
            s.add(nums2[i]);
        }
        return ans;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //暴力+哈希
        int n = nums1.length;
        int m = nums2.length;
        int[] cnts = new int[1001];
        int[] ans = new int[n];
        for (int i = 0; i < m; i++) {
            cnts[nums2[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
            System.out.println(cnts[nums1[i]]);
            for (int j = cnts[nums1[i]] + 1; j < m; j++) {
                if (nums2[j] > nums1[i]) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }
}
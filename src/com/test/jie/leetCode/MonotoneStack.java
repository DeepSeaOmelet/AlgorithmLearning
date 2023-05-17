package com.test.jie.leetCode;

import com.test.jie.leetCode.tool.ListNode;

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
                int cur = s.pollLast();
                if (map.containsKey(cur)) {
                    ans[map.get(cur)] = nums2[i];
                }
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

/**
 * 503. 下一个更大元素 II
 * 中等
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * <p>
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1 。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 示例 2:
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
class MonotoneStackSolution3 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return new int[]{-1};
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < n * 2; i++) {
            while (!s.isEmpty() && nums[i % n] > nums[s.peek()]) {
                ans[s.pop()] = nums[i % n];
            }
            s.push(i % n);
        }
        return ans;
    }
}

/**
 * 42. 接雨水  https://leetcode.cn/problems/trapping-rain-water/
 * 困难
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 */
class MonotoneStackSolution4 {
    public int trap3(int[] height) {
        //暴力
        //计算 每个当前列最高的水位
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            //第一个柱子和最后一个不接水
//            if (i==0||i==height.length-1){
//                continue;
//            }
            int rHeight = height[i];
            int lHeight = height[i];
            //用左右夹逼来计算当前水位，最高水位由最短柱子高度决定
            for (int j = i - 1; j >= 0; j--) {
                lHeight = Math.max(lHeight, height[j]);
            }
            for (int j = i + 1; j < height.length; j++) {
                rHeight = Math.max(rHeight, height[j]);
            }
            int curHeight = Math.min(rHeight, lHeight) - height[i];
            if (curHeight > 0) {
                sum += curHeight;
            }
        }
        return sum;
    }

    public int trap4(int[] height) {
        //暴力 带记忆优化
        if (height.length <= 2) {
            return 0;
        }
        int sum = 0;
        int[] lHeight = new int[height.length];
        lHeight[0] = height[0];
        //记录每列左边最高
        for (int i = 1; i < height.length; i++) {
            lHeight[i] = Math.max(lHeight[i - 1], height[i]);
        }
        int[] rHeight = new int[height.length];
        rHeight[height.length - 1] = height[height.length - 1];
        //记录每列右边最高
        for (int i = height.length - 2; i >= 0; i--) {
            rHeight[i] = Math.max(rHeight[i + 1], height[i]);
        }
        //计算高度
        for (int i = 0; i < height.length; i++) {
            int curHeight = Math.min(rHeight[i], lHeight[i]) - height[i];
            if (curHeight > 0) {
                sum += curHeight;
            }
        }
        return sum;
    }


    public int trap2(int[] height) {
        //单调栈   按行计算水位
        int ans = 0;
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        //s存储的是height下标
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && height[i] > height[s.peek()]) {
                int mid = s.pop();
                if (!s.isEmpty()) {
                    int w = i - s.peek() - 1;
                    int h = Math.min(height[i], height[s.peek()]) - height[mid];
                    ans += w * h;
                    //System.out.println(ans);
                }
            }
            s.push(i);
        }
        return ans;
    }

    public int trap(int[] height) {
        int size = height.length;

        if (size <= 2) return 0;

        // in the stack, we push the index of array
        // using height[] to access the real height
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        int sum = 0;
        for (int index = 1; index < size; index++) {
            int stackTop = stack.peek();
            if (height[index] < height[stackTop]) {
                stack.push(index);
            } else if (height[index] == height[stackTop]) {
                // 因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的index, push当前的index
                stack.pop();
                stack.push(index);
            } else {
                //pop up all lower value
                int heightAtIdx = height[index];
                while (!stack.isEmpty() && (heightAtIdx > height[stackTop])) {
                    int mid = stack.pop();

                    if (!stack.isEmpty()) {
                        int left = stack.peek();

                        int h = Math.min(height[left], height[index]) - height[mid];
                        int w = index - left - 1;
                        int hold = h * w;
                        if (hold > 0) sum += hold;
                        stackTop = stack.peek();
                    }
                }
                stack.push(index);
            }
        }
        return sum;
    }
}

/**
 * 84. 柱状图中最大的矩形
 * 困难
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
class MonotoneStackSolution5 {

    public int largestRectangleArea(int[] heights) {
        //暴力 超时
        int max = 0;
        if (heights.length == 1) {
            return heights[0];
        }
        for (int i = 0; i < heights.length; i++) {
            int leftIdx = i;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[i] > heights[j]) {
                    break;
                }
                leftIdx = j;
            }
            int rightIdx = i;
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[i] > heights[j]) {
                    break;
                }
                rightIdx = j;
            }
            int cur = heights[i] * (rightIdx - leftIdx + 1);
            max = Math.max(max, cur);
        }
        return max;
    }

    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] minLeftIndex = new int[n];
        int[] minRightIndex = new int[n];

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && heights[j] >= heights[i]) {
                j = minLeftIndex[j];
            }
            minLeftIndex[i] = j;
        }
        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && heights[j] >= heights[i]) {
                j = minRightIndex[j];
            }
            minRightIndex[i] = j;
        }
        //求和
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = (minRightIndex[i] - minLeftIndex[i] - 1) * heights[i];
            max = Math.max(max, cur);
        }
        return max;
    }

//    public int largestRectangleArea3(int[] heights) {
//        int n = heights.length;
//        int[] newHeights = new int[n + 2];
//        newHeights[0] = 0;
//        newHeights[n + 1] = 0;
//        for (int i = 0; i < n; i++) {
//            newHeights[i + 1] = heights[i];
//        }
//        int max = 0;
//        LinkedList<Integer> s = new LinkedList<>();
//        for (int i = 0; i < n + 2; i++) {
//            while (!s.isEmpty() && newHeights[i] < newHeights[s.peek()]) {
//                int cur = s.pop();
//                if (!s.isEmpty()) {
//                    //长x宽
//                    int curSize = newHeights[cur] * (i - s.peek() - 1);
//                    max = Math.max(max, curSize);
//                }
//            }
//            s.push(i);
//        }
//        return max;
//    }


    public int largestRectangleArea3_1(int[] heights) {
        //单调栈 递减
        int n = heights.length;
        if (n <= 1) {
            return heights[0];
        }
        //需要头尾加0，用以计算没有被左右小于的柱子夹住的柱子
        int[] newHeights = new int[n + 2];
        for (int i = 0; i < n; i++) {
            newHeights[i + 1] = heights[i];
        }
        newHeights[0] = 0;
        newHeights[n + 1] = 0;
        int max = 0;
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < n + 2; i++) {
            while (!s.isEmpty() && newHeights[i] < newHeights[s.peek()]) {
                int cur = s.pop();
                int width = i - s.peek() - 1;
                max = Math.max(max, width * newHeights[cur]);
            }
            s.push(i);
        }
        return max;
    }
}
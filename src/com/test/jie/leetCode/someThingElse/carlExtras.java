package com.test.jie.leetCode.someThingElse;

import java.util.*;

public class carlExtras {
    /**
     * 1365. 有多少小于当前数字的数字
     * 简单
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     * <p>
     * 以数组形式返回答案。
     * <p>
     * 示例 1：
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * 示例 2：
     * 输入：nums = [6,5,4,8]
     * 输出：[2,1,0,3]
     * 示例 3：
     * 输入：nums = [7,7,7,7]
     * 输出：[0,0,0,0]
     * <p>
     * 提示：
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //哈希
        int n = nums.length;
        int[] ans = Arrays.copyOf(nums, n);
        Arrays.sort(ans);
        int[] idxs = new int[501];
        for (int i = n - 1; i >= 0; i--) {
            idxs[ans[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            ans[i] = idxs[nums[i]];
        }
        return ans;
    }

    /**
     * 941. 有效的山脉数组
     * 简单
     * 给定一个整数数组 arr，如果它是有效的山脉数组就返回 true，否则返回 false。
     * 让我们回顾一下，如果 arr 满足下述条件，那么它是一个山脉数组：
     * <p>
     * arr.length >= 3
     * 在 0 < i < arr.length - 1 条件下，存在 i 使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * <p>
     * 示例 1：
     * 输入：arr = [2,1]
     * 输出：false
     * 示例 2：
     * 输入：arr = [3,5,5]
     * 输出：false
     * 示例 3：
     * 输入：arr = [0,3,2,1]
     * 输出：true
     * <p>
     * 提示：
     * 1 <= arr.length <= 104
     * 0 <= arr[i] <= 104
     *
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {
        //双指针
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        int l = 0;
        int r = n - 1;
        while (arr[l] < arr[l + 1] && l < n - 2) {
            l++;
        }
        while (arr[r] < arr[r - 1] && r > 1) {
            r--;
        }
        return l == r;
    }

    /**
     * 1207. 独一无二的出现次数
     * 简单
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * <p>
     * 示例 1：
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     * <p>
     * 提示：
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        //哈希
        int[] cnts = new int[2002];
        for (int i = 0; i < arr.length; i++) {
            cnts[arr[i] + 1000]++;
        }
        //记录当前次数是否唯一
        boolean[] isUnique = new boolean[arr.length + 1];
        for (int i = 0; i <= 2000; i++) {
            if (cnts[i] > 0) {
                if (isUnique[cnts[i]]) {
                    return false;
                }
                isUnique[cnts[i]] = true;
            }
        }
        return true;
    }

    public boolean uniqueOccurrences2(int[] arr) {
        //哈希
        Arrays.sort(arr);
        int[] cnt = new int[arr.length];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                cnt[count]++;
                if (cnt[count] > 1) {
                    return false;
                }
                count = 0;
            }
            count++;
        }
        return cnt[count] < 1;
    }

    /**
     * 283. 移动零
     * 简单
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * <p>
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 示例 2:
     * 输入: nums = [0]
     * 输出: [0]
     * <p>
     * 提示:
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     * 进阶：你能尽量减少完成的操作次数吗？
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }

    /**
     * 189. 轮转数组
     * 中等
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右轮转 1 步: [99,-1,-100,3]
     * 向右轮转 2 步: [3,99,-1,-100]
     * <p>
     * 提示：
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     * <p>
     * 进阶：
     * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
     * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        Random ran = new Random(System.currentTimeMillis());
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        int nextKill = 0;
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 600; j++) {
                list.add(j + 1);
            }
            while (list.size() > 1) {
                while (nextKill % 2 != 0) {
                    nextKill = ran.nextInt(list.size());
                }
//                System.out.println(list.get(nextKill));
                list.remove(nextKill);
                nextKill = 1;
            }
            //System.out.println(list.get(0));
            map.put(list.get(0), map.getOrDefault(list.get(0), 1) + 1);
            list.clear();
        }
        int cnt = 0;
        List<Map.Entry<Integer, Integer>> ans = new ArrayList<>(map.entrySet());
        Collections.sort(ans, (a, b) -> b.getValue() - a.getValue());
        for (int i = 0; i < ans.size(); i++) {
            if (i % 15 == 0) {
                System.out.println();
            }
            System.out.print(ans.get(i) + " ");
        }
    }
}

package com.test.jie.leetCode.Daily.Y2024;

import java.util.*;

public class interview150 {
    //88. 合并两个有序数组  2024年3月30日
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m + n - 1, j = n - 1, k = m - 1; i >= 0; i--) {
            if (j >= 0 && k >= 0) {
                if (nums1[k] > nums2[j]) {
                    nums1[i] = nums1[k--];
                } else {
                    nums1[i] = nums2[j--];
                }
            } else if (k >= 0) {
                nums1[i] = nums1[k];
                k--;
            } else {
                nums1[i] = nums2[j];
                j--;
            }
        }
    }

    //27. 移除元素  2024年3月30日
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int ans = n;
        for (int i = 0, j = n - 1; i < n; i++) {
            if (nums[i] == val) {
                nums[i] = nums[j];
                nums[j] = -1;
                j--;
                i--;
                ans--;
            }
        }
        return ans;
    }

    //26. 删除有序数组中的重复项   2024年3月30日
    public int removeDuplicates(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[ans] != nums[i]) {
                nums[++ans] = nums[i];
            }
        }
        return ans + 1;
    }

    //参考 80. 删除有序数组中的重复项 II 2024年3月30日
    public int removeDuplicates2(int[] nums) {
        int ans = 0;
        int dup = 2;
        for (int i = 0; i < nums.length; i++) {
            if (ans < dup || nums[ans - dup] != nums[i]) {
                nums[ans++] = nums[i];
            }
        }
        return ans;
    }

    //169. 多数元素 2024年3月30日
    public int majorityElement(int[] nums) {
        //2
        Arrays.sort(nums);
        return nums[nums.length >> 1];
        //1
//        int n = nums.length;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//        }
//        int ans = 0;
//        int cnt = 0;
//        for (int key : map.keySet()) {
//            if (map.get(key) > cnt) {
//                ans = key;
//                cnt = map.get(key);
//            }
//        }
//        return ans;
    }

    //189. 轮转数组 2024年3月31日
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

    //121. 买卖股票的最佳时机    2024年3月31日
    public int maxProfit(int[] prices) {
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }

    //122. 买卖股票的最佳时机 II 2024年3月31日
    public int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }
        return ans;

//        int len = prices.length;
//        //[0]不持有，[1]持有
//        int[][] dp = new int[len + 1][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        for (int i = 1; i < len; i++) {
//            dp[i][0] = Math.max(prices[i] + dp[i - 1][1], dp[i - 1][0]);
//            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
//        }
//        return dp[len - 1][0];
    }

    //55. 跳跃游戏  2024年4月1日
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (max >= i) {
                max = Math.max(max, nums[i] + i);
            }
        }
        return max >= nums.length - 1;
//        boolean[] jump = new boolean[nums.length];
//        jump[0] = true;
//        for (int i = 0; i < nums.length; i++) {
//            if (jump[i]) {
//                for (int j = 1; j + i < nums.length && j <= nums[i]; j++) {
//                    jump[j + i] = true;
//                }
//            }
//        }
//        return jump[nums.length - 1];
    }

    //45. 跳跃游戏 II   2024年4月2日
    public int jump(int[] nums) {
        int ans = 0;
        int curDistance = 0;
        int nextDistance = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextDistance = Math.max(nextDistance, nums[i] + i);
            if (i == curDistance) {
                curDistance = nextDistance;
                ans++;
            }
        }
        return ans;
//        int[] jumpTimes = new int[nums.length];
//        for (int i = 0; i < nums.length - 1; i++) {
//            int time = jumpTimes[i];
//            for (int j = 1; j + i < nums.length && j <= nums[i]; j++) {
//                if (jumpTimes[j + i] == 0) {
//                    jumpTimes[j + i] = time + 1;
//                }
//            }
//        }
//        return jumpTimes[nums.length - 1];
    }

    //274. H 指数 2024年4月2日
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = 0;
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (n - i <= citations[i]) {
                ans = Math.max(ans, n - i);
            }
        }
        return ans;
    }

    //参考 380. O(1) 时间插入、删除和获取随机元素 2024年4月2日
    class RandomizedSet {
        private Map<Integer, Integer> map = new HashMap<>();
        private Random random = new Random();
        private int[] nums = new int[200010];
        private int lastIndex = -1;

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            nums[++lastIndex] = val;
            map.put(val, lastIndex);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int valIdx = map.remove(val);
            //将删除的val与数组末尾的的值替换，还要更新map
            if (valIdx != lastIndex) {
                map.put(nums[lastIndex], valIdx);
            }
            nums[valIdx] = nums[lastIndex];
            lastIndex--;
            return true;
        }

        public int getRandom() {
            return nums[random.nextInt(lastIndex + 1)];
        }
    }

    //238. 除自身以外数组的乘积   2024年4月3日
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int back = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = back * ans[i];
            back *= nums[i];
        }
        return ans;
    }

    //参考 134. 加油站 2024年4月6日
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ans = 0;
        int sum = 0;
        int curSum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];
            if (curSum < 0) {
                //不适合
                ans = i + 1;
                curSum = 0;
            }
        }
        return sum < 0 ? -1 : ans;
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
    }
}

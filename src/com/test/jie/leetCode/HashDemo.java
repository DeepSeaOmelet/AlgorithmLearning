package com.test.jie.leetCode;

import com.test.jie.leetCode.tool.ListNode;

import java.util.*;

public class HashDemo {
    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 提示:
     * 1 <= s.length, t.length <= 5 * 104
     * s 和 t 仅包含小写字母
     * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnt[c - 'a']--;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 349. 两个数组的交集
     * 简单
     * 682
     * 相关企业
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * 解释：[4,9] 也是可通过的
     * 提示：
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] cnt = new int[1001];
        for (int x : nums1) {
            cnt[x]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums2) {
            if (cnt[x] > 0) {
                list.add(x);
                cnt[x] = -1;
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    //https://leetcode.cn/problems/intersection-of-two-arrays-ii/
    //350. 两个数组的交集 II
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] cnt = new int[1001];
        for (int x : nums1) {
            cnt[x]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                list.add(x);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 383. 赎金信
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false 。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     * 示例 1：
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     * 提示：
     * 1 <= ransomNote.length, magazine.length <= 10^5
     * ransomNote 和 magazine 由小写英文字母组成
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnts = new int[26];
        for (char c : magazine.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int u = ransomNote.charAt(i) - 'a';
            if (cnts[u] <= 0) {
                return false;
            }
            cnts[u]--;
        }
        return true;
    }

    /**
     * 49. 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     * 提示：
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            int[] cnt = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                cnt[strs[i].charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cnt.length; j++) {
                if (cnt[j] > 0) {
                    sb.append(cnt[j]).append((char) ('a' + j));
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }
        for (String cnt : map.keySet()) {
            ans.add(map.get(cnt));
        }
        return ans;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     * 示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     * 提示:
     * 1 <= s.length, p.length <= 3 * 10^4
     * s 和 p 仅包含小写字母
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] cnt = new int[26];
        int a = 0;
        int b = 0;
        int pLen = p.length();
        int sLen = s.length();
        for (int i = 0; i < pLen; i++) {
            cnt[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                a++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0; r < sLen; r++) {
            if (--cnt[s.charAt(r) - 'a'] == 0) {
                b++;
            }
            if (r - l + 1 > pLen && ++cnt[s.charAt(l++) - 'a'] == 1) {
                b--;
            }
            if (b == a) {
                ans.add(l);
            }
        }
        return ans;
    }

    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」 定义为：
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     * 示例 1：
     * 输入：n = 19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * 示例 2：
     * 输入：n = 2
     * 输出：false
     * 提示：
     * 1 <= n <= 231 - 1
     *
     * @param n
     */
    public boolean isHappy(int n) {
        Map<Integer, Boolean> map = new HashMap<>();
        while (n != 1) {
            if (map.getOrDefault(n, false)) {
                return false;
            }
            map.put(n, true);
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return true;
    }

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * 提示：
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    /**
     * 454. 四数相加 II
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * 示例 1：
     * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
     * 输出：2
     * 解释：
     * 两个元组如下：
     * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
     * 示例 2：
     * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
     * 输出：1
     * 提示：
     * n == nums1.length
     * n == nums2.length
     * n == nums3.length
     * n == nums4.length
     * 1 <= n <= 200
     * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //4个数组拆分成两个大数组
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                if (map.containsKey(-sum)) {
                    ans += map.get(-sum);
                }
            }
        }
        return ans;
    }

    /**
     * 15. 三数之和
     * 中等
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     * * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     * 示例 2：
     * 输入：nums = [0,1,1]
     * 输出：[]
     * 解释：唯一可能的三元组和不为 0 。
     * 示例 3：
     * 输入：nums = [0,0,0]
     * 输出：[[0,0,0]]
     * 解释：唯一可能的三元组和为 0 。
     * 提示：
     * 3 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //去重是关键
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return ans;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (r > l) {
                if (nums[r] + nums[l] + nums[i] > 0) {
                    r--;
                } else if (nums[r] + nums[l] + nums[i] < 0) {
                    l++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ans.add(list);
                    //去重
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }

    /**
     * 18. 四数之和
     * 中等
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
     * * （若两个四元组元素一一对应，则认为两个四元组重复）：
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     * 示例 1：
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * 示例 2：
     * 输入：nums = [2,2,2,2,2], target = 8
     * 输出：[[2,2,2,2]]
     * 提示：
     * 1 <= nums.length <= 200
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //去重是关键
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //剪枝
            if (nums[i] > target && nums[i] > 0) {
                return ans;
            }
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] + nums[j] + nums[i] > target) {
                        r--;
                    } else if (nums[l] + nums[r] + nums[j] + nums[i] < target) {
                        l++;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        ans.add(list);
                        //去重
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long t = 0;
        for (int i = 0; i < 4; i++) {
            t += 1000000000;
            System.out.println(t);
        }
    }
}

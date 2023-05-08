package com.test.jie.leetCode.dp;

import java.util.Arrays;

public class Subsequences {
    /**
     * 300. 最长递增子序列
     * 中等
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * <p>
     * 提示：
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     * <p>
     * 进阶：
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}

class SubsequencesSolution2 {
    /**
     * 674. 最长连续递增序列    https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
     * 简单
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * <p>
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
     * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     * 示例 2：
     * 输入：nums = [2,2,2,2,2]
     * 输出：1
     * 解释：最长连续递增序列是 [2], 长度为1。
     * <p>
     * 提示：
     * 1 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else dp[i] = 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int findLengthOfLCIS2(int[] nums) {
        int ans = 1;
        int size = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                size++;
            } else size = 1;
            ans = Math.max(ans, size);
        }
        return ans;
    }
}

/**
 * 718. 最长重复子数组
 * 中等
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
class SubsequencesSolution3 {
    public int findLength2(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int ans = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    ans = Math.max(ans, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return ans;
    }

    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int ans = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}

/**
 * 1143. 最长公共子序列
 * 中等
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * <p>
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
class SubsequencesSolution4 {
    public int longestCommonSubsequence(String text1, String text2) {
        int ans = 0;
        int[] dp = new int[text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            char t1 = text1.charAt(i - 1);
            int pre = dp[0];
            for (int j = 1; j <= text2.length(); j++) {
                int cur = dp[j];
                char t2 = text2.charAt(j - 1);
                if (t1 == t2) {
                    dp[j] = pre + 1;
                    ans = Math.max(ans, dp[j]);
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = cur;
            }
            System.out.println(Arrays.toString(dp));
        }
        return ans;
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int ans = 0;
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            char t1 = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char t2 = text2.charAt(j - 1);
                if (t1 == t2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    //这里表示：dp[i-1][j]是对text1字段范围为[0,i-2]和tex2字段范围为[0,j-1]的结果，
                    // 而dp[i][j-1]是对text1字段范围为[0,i-1]和tex2字段范围为[0,j-2]的结果
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return ans;
    }
}

/**
 * 1035. 不相交的线
 * 中等
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 1    4   2
 * |      \
 * 1    2   4
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * 示例 2：
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * 示例 3：
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 */
class SubsequencesSolution5 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int ans = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(dp[i][j], ans);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return ans;
    }
}

/**
 * 53. 最大子数组和
 * 中等
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
class SubsequencesSolution6 {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

/**
 * 392. 判断子序列
 * 简单
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 * <p>
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
class SubsequencesSolution7 {
    public boolean isSubsequence2(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            char s1 = s.charAt(i - 1);
            for (int j = 1; j <= t.length(); j++) {
                char t1 = t.charAt(j - 1);
                if (s1 == t1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[s.length()][t.length()] == s.length();
    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        boolean[] dp = new boolean[s.length()];
        int idx = 0;
        for (int i = 0; i < t.length() && idx < s.length(); i++) {
            if (s.charAt(idx) == t.charAt(i)) {
                dp[idx++] = true;
            }
        }
        return dp.length == 0 || dp[s.length() - 1];
    }
}

/**
 * 115. 不同的子序列
 * 困难
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * ---- --
 * rabbbit
 * ---- --
 * rabbbit
 * --- ---
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * -- -
 * babgbag
 * --    -
 * babgbag
 * -    --
 * babgbag
 * -  --
 * babgbag
 * ---
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 */
class SubsequencesSolution8 {
    public int numDistinct(String s, String t) {
        //动规可以用回溯的思维去考虑
        //1
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        //2
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        //3
        for (int i = 1; i <= s.length(); i++) {
            char s1 = s.charAt(i - 1);
            for (int j = 1; j <= t.length(); j++) {
                char t1 = t.charAt(j - 1);
                //4
                if (s1 == t1) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //dp[i][j]只有一部分组成，不用s[i - 1]来匹配（就是模拟在s中删除这个元素），即：dp[i - 1][j]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}

/**
 * 583. 两个字符串的删除操作  https://leetcode.cn/problems/delete-operation-for-two-strings/
 * 中等
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 * <p>
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
class SubsequencesSolution9 {
    public int minDistance(String word1, String word2) {
        //1 dp[i][j]表示需要删除的个数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        //3 初始
        for (int i = 1; i <= word1.length(); i++) {
            //word1需要删除的个数
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            char w1 = word1.charAt(i - 1);
            for (int j = 1; j <= word2.length(); j++) {
                char w2 = word2.charAt(j - 1);
                //2
                if (w1 == w2) {
                    //表示不用删除元素
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //表示需要删除元素，删除w1或w2
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[word1.length()][word2.length()];
    }
}
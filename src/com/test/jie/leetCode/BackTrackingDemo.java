package com.test.jie.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法 学习 力扣题目*
 */
public class BackTrackingDemo {
    /**
     * https://leetcode.cn/problems/combinations/
     * 77. 组合
     * 中等
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     * <p>
     * 提示：
     * 1 <= n <= 20
     * 1 <= k <= n*
     */
    class combineClass {
        int maxNum = 0;
        int maxCnt = 0;
        List<List<Integer>> res = null;

        public List<List<Integer>> combine(int n, int k) {
            maxNum = n;
            maxCnt = k;
            res = new ArrayList<>();
            combineBackTracking(0, 0, new ArrayList<>());
            return res;
        }

        public void combineBackTracking(int cur, int cnt, List<Integer> list) {
            //终止条件
            if (cnt == maxCnt) {
                res.add(new ArrayList<>(list));
                return;
            }
            //最大起始位置
            int max = maxNum - maxCnt + cnt + 1;
            //回溯过程
            for (int i = cur + 1; i <= max; i++) {
                list.add(i);
                combineBackTracking(i, cnt + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 216. 组合总和 III
     * 中等
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     * <p>
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     * 示例 3:
     * 输入: k = 4, n = 1
     * 输出: []
     * 解释: 不存在有效的组合。
     * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     * <p>
     * 提示:
     * 2 <= k <= 9
     * 1 <= n <= 60
     *
     * @param k
     * @param n
     * @return
     */
    class combinationSum3Class {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> link = new LinkedList<>();

        public List<List<Integer>> combinationSum3(int k, int n) {
            backTracking(k, n, 1, 0);
            return res;
        }

        public void backTracking(int k, int n, int num, int sum) {
            if (link.size() == k && sum == n) {
                res.add(new ArrayList<>(link));
                return;
            }
            //剪枝
            int max = 9 - k + link.size() + 1;
            for (int i = num; i <= max; i++) {
                link.add(i);
                sum += i;
                if (sum > n) {
                    sum -= i;
                    link.removeLast();
                    return;
                }
                backTracking(k, n, i + 1, sum);
                sum -= i;
                link.removeLast();
            }
        }
    }

    class letterCombinationsClass {
        /**
         * 17. 电话号码的字母组合
         * 中等
         * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
         * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
         * <p>
         * 示例 1：
         * 输入：digits = "23"
         * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
         * 示例 2：
         * 输入：digits = ""
         * 输出：[]
         * 示例 3：
         * 输入：digits = "2"
         * 输出：["a","b","c"]
         * <p>
         * 提示：
         * 0 <= digits.length <= 4
         * digits[i] 是范围 ['2', '9'] 的一个数字。
         *
         * @param digits
         * @return
         */
        List<String> res = new ArrayList<>();
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<>();
            }
            res.clear();
            backTracking(digits, 0, new StringBuilder());
            return res;
        }

        public void backTracking(String digits, int idx, StringBuilder sb) {
            if (idx == digits.length()) {
                res.add(sb.toString());
                return;
            }
            //取字母
            String cur = numString[digits.charAt(idx) - '0'];
            for (int i = 0; i < cur.length(); i++) {
                sb.append(cur.charAt(i));
                backTracking(digits, idx + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    class combinationSumClass {
        /**
         * 39. 组合总和
         * 中等
         * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
         * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
         * <p>
         * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
         * <p>
         * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
         * <p>
         * 示例 1：
         * 输入：candidates = [2,3,6,7], target = 7
         * 输出：[[2,2,3],[7]]
         * 解释：
         * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
         * 7 也是一个候选， 7 = 7 。
         * 仅有这两种组合。
         * 示例 2：
         * 输入: candidates = [2,3,5], target = 8
         * 输出: [[2,2,2,2],[2,3,3],[3,5]]
         * 示例 3：
         * 输入: candidates = [2], target = 1
         * 输出: []
         * <p>
         * 提示：
         * 1 <= candidates.length <= 30
         * 2 <= candidates[i] <= 40
         * candidates 的所有元素 互不相同
         * 1 <= target <= 40
         *
         * @param candidates
         * @param target
         * @return
         */
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            res.clear();
            list.clear();
            Arrays.sort(candidates);
            backTracking(0, target, candidates, 0);
            return res;
        }

        public void backTracking(int sum, int target, int[] candidates, int startIndex) {
            if (sum == target) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = startIndex; i < candidates.length; i++) {
                //剪枝
                if (sum + candidates[i] > target) {
                    break;
                }
                list.add(candidates[i]);
                backTracking(sum + candidates[i], target, candidates, i);
                list.remove(list.size() - 1);
            }
        }
    }

    class combinationSum2Class {
        /**
         * 40. 组合总和 II
         * 中等
         * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
         * <p>
         * candidates 中的每个数字在每个组合中只能使用 一次 。
         * 注意：解集不能包含重复的组合。
         * *
         * 示例 1:
         * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
         * 输出:
         * [
         * [1,1,6],
         * [1,2,5],
         * [1,7],
         * [2,6]
         * ]
         * 示例 2:
         * 输入: candidates = [2,5,2,1,2], target = 5,
         * 输出:
         * [
         * [1,2,2],
         * [5]
         * ]
         * <p>
         * 提示:
         * 1 <= candidates.length <= 100
         * 1 <= candidates[i] <= 50
         * 1 <= target <= 30
         *
         * @param candidates
         * @param target
         * @return
         */
        private List<List<Integer>> res = new ArrayList<>();//答案
        private List<Integer> list = new ArrayList<>();//路径搜索

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //剪枝
            Arrays.sort(candidates);
            this.res.clear();
            this.list.clear();
            backTracking(candidates, target, 0, 0);
            return res;
        }

        public void backTracking(int[] candidates, int target, int sum, int idx) {
            if (target == sum) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = idx; i < candidates.length; i++) {
                //剪枝
                if (sum + candidates[i] > target) {
                    return;
                }
                //去重
                if (i > idx && candidates[i - 1] == candidates[i]) {
                    continue;
                }
                list.add(candidates[i]);
                backTracking(candidates, target, sum + candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    class partitionClass {
        /**
         * 131. 分割回文串
         * 中等
         * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
         * <p>
         * 回文串 是正着读和反着读都一样的字符串。
         * 示例 1：
         * 输入：s = "aab"
         * 输出：[["a","a","b"],["aa","b"]]
         * 示例 2：
         * 输入：s = "a"
         * 输出：[["a"]]
         * <p>
         * 提示：
         * 1 <= s.length <= 16
         * s 仅由小写英文字母组成
         *
         * @param s
         * @return
         */
        private List<List<String>> ans;
        private List<String> list;

        public List<List<String>> partition(String s) {
            ans = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return ans;
            }
            list = new ArrayList<>();
            backTracking(s, 0);
            return ans;
        }

        public void backTracking(String s, int idx) {
            //终止
            if (idx == s.length()) {
                ans.add(new ArrayList<>(list));
                return;
            }
            //循环
            for (int i = idx; i < s.length(); i++) {
                String cur = s.substring(idx, i + 1);
                if (check(cur)) {
                    list.add(cur);
                    backTracking(s, i + 1);
                    list.remove(list.size() - 1);
                }
            }
        }

        public boolean check(String s) {
            int right = s.length() - 1;
            int left = 0;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    class restoreIpAddressesClass {
        /**
         * 93. 复原 IP 地址
         * 中等
         * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
         * <p>
         * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
         * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
         * <p>
         * 示例 1：
         * 输入：s = "25525511135"
         * 输出：["255.255.11.135","255.255.111.35"]
         * 示例 2：
         * 输入：s = "0000"
         * 输出：["0.0.0.0"]
         * 示例 3：
         * 输入：s = "101023"
         * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
         * <p>
         * 提示：
         * 1 <= s.length <= 20
         * s 仅由数字组成
         *
         * @param s
         * @return
         */
        private List<String> ans;

        public List<String> restoreIpAddresses(String s) {
            ans = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return ans;
            }
            backTracking(s, 0, new StringBuilder(), 0);
            return ans;
        }

        /**
         * 回溯
         *
         * @param s          原字符串
         * @param startIndex 开始分割的下标
         * @param sb         结果
         * @param cnt        ip地址中第cnt + 1 个整数
         */
        public void backTracking(String s, int startIndex, StringBuilder sb, int cnt) {
            //剪枝
            // 1.ip地址不能超过四个整数   2.每次递归后后续IP地址长度必须 大于或等于 s长度
            if (cnt > 4 || (s.length() - startIndex) > (3 * (4 - cnt))) {
                return;
            }
            //终止条件
            if (cnt == 4) {
                if (startIndex == s.length()) {
                    ans.add(sb.toString());
                }
                return;
            }
            //开始回溯
            for (int i = startIndex; i < s.length(); i++) {
                String cur = s.substring(startIndex, i + 1);
                int num = Integer.parseInt(cur);
                int len = sb.length();
                if (num > 255) {
                    break;
                }
                if (cnt == 3) {
                    sb.append(num);
                } else {
                    sb.append(num).append(".");
                }
                backTracking(s, i + 1, sb, cnt + 1);
                sb.delete(len, sb.length());
                if (num == 0) {//不能有前导 0
                    break;
                }
            }
        }

    }

    class subsetsClass {
        /**
         * 78. 子集
         * 中等
         * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
         * <p>
         * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
         * <p>
         * 示例 1：
         * 输入：nums = [1,2,3]
         * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
         * 示例 2：
         * 输入：nums = [0]
         * 输出：[[],[0]]
         * <p>
         * 提示：
         * 1 <= nums.length <= 10
         * -10 <= nums[i] <= 10
         * nums 中的所有元素 互不相同
         *
         * @param nums
         * @return
         */
        List<List<Integer>> ans;

        public List<List<Integer>> subsets(int[] nums) {
            ans = new ArrayList<>();
            dfs(nums, 0, new ArrayList<>());
            return ans;
        }

        public void dfs(int[] nums, int startIndex, List<Integer> list) {
            ans.add(new ArrayList<>(list));
            if (startIndex == nums.length) {
                return;
            }
            for (int i = startIndex; i < nums.length; i++) {
                list.add(nums[i]);
                dfs(nums, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    class subsetsWithDupClass {
        /**
         * 90. 子集 II
         * 中等
         * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
         * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
         * 示例 1：
         * 输入：nums = [1,2,2]
         * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
         * 示例 2：
         * 输入：nums = [0]
         * 输出：[[],[0]]
         * 提示：
         * 1 <= nums.length <= 10
         * -10 <= nums[i] <= 10
         *
         * @param nums
         * @return
         */
        List<List<Integer>> ans;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            ans = new ArrayList<>();
            Arrays.sort(nums);
            dfs(new ArrayList<>(), 0, nums);
            return ans;
        }

        public void dfs(List<Integer> list, int startIndex, int[] nums) {
            ans.add(new ArrayList<>(list));
            if (startIndex == nums.length) {
                return;
            }
            for (int i = startIndex; i < nums.length; i++) {
                if (i > startIndex && nums[i] == nums[i - 1]) {
                    continue;
                }
                list.add(nums[i]);
                dfs(list, i + 1, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("0123456");
        int len = sb.length();
        System.out.println(len);
        sb.append("78910");
        System.out.println(sb.toString());
        sb.delete(len, sb.length());
        System.out.println(sb.toString());
    }
}

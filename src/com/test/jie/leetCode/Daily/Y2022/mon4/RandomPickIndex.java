package com.test.jie.leetCode.Daily.Y2022.mon4;

import java.util.*;

/**
 * 随机数索引
 * 给你一个可能含有 重复元素 的整数数组nums ，请你随机输出给定的目标数字target 的索引。
 * 你可以假设给定的数字一定存在于数组中。
 *
 * 实现 Solution 类：
 * Solution(int[] nums) 用数组 nums 初始化对象。
 * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。
 * 如果存在多个有效的索引，则每个索引的返回概率应当相等。
 *
 * 示例：
 * 输入
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * 输出
 * [null, 4, 0, 2]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
 * solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。
 * solution.pick(3); // 随机返回索引 2, 3 或者 4 之一。每个索引的返回概率应该相等。
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * target 是 nums 中的一个整数
 * 最多调用 pick 函数 104 次
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RandomPickIndex {
    public static void main(String[] args) {
        RandomPickIndex index = new RandomPickIndex();
        Solution solution = index.new Solution(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
    }
    class Solution {
        Map<Integer, List<Integer>> map;
        public Solution(int[] nums) {
            int len = nums.length;
            map = new HashMap();
            for (int i = 0; i < len; i++) {
                List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
                list.add(i);
                map.put(nums[i],list);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(new Random().nextInt(list.size()));
        }
    }
}

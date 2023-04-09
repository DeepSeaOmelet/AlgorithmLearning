package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/18
 * 565. 数组嵌套
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。
 * 找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 * <p>
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，
 * 之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 * <p>
 * 示例 1:
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * <p>
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] < nums.length
 * A中不含有重复的元素。
 * https://leetcode.cn/problems/array-nesting/
 */
public class ArrayNesting {
    public static void main(String[] args) {
        ArrayNesting a = new ArrayNesting();
        System.out.println(a.arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2}));
    }

    public int arrayNesting(int[] nums) {
        //最大的闭环？
        //既然是闭环，经历过的就可以去掉
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int idx = i;
            int cnt = 0;
            while (nums[idx] != -1) {
                int cur = idx;
                idx = nums[cur];
                nums[cur] = -1;
                cnt++;
            }
            ans = Math.max(cnt, ans);
        }
        return ans;
    }

}
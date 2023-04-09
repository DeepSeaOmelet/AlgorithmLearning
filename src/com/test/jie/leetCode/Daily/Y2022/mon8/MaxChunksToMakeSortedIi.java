package com.test.jie.leetCode.Daily.Y2022.mon8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/08/13
 * 768. 最多能完成排序的块 II
 * 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
 * <p>
 * arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 * <p>
 * 我们最多能将数组分成多少块？
 * <p>
 * 示例 1:
 * 输入: arr = [5,4,3,2,1]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。
 * 示例 2:
 * <p>
 * 输入: arr = [2,1,3,4,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
 * 然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。
 * 注意:
 * arr的长度在[1, 2000]之间。
 * arr[i]的大小在[0, 10**8]之间。
 */
public class MaxChunksToMakeSortedIi {
    public static void main(String[] args) {

    }

    public int maxChunksToSorted(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        //使用哈希表进行计数，同时维护当前计数不为 0 的数值数量 tot，具体的，当我们处理 arr[i] 时，
        // 我们在哈希表中对 arr[i] 进行计数加一，而在处理 clone[i] 时，对 clone[i] 进行计数减一。
        for (int i = 0, tot = 0; i < arr.length; i++) {
            int a = arr[i];
            if (map.getOrDefault(a, 0) == -1) {
                tot--;
            } else if (map.getOrDefault(a, 0) == 0) {
                tot++;
            }
            map.put(a, map.getOrDefault(a, 0) + 1);

            int b = clone[i];
            if (map.getOrDefault(b, 0) == 1) {
                tot--;
            } else if (map.getOrDefault(b, 0) == 0) {
                tot++;
            }
            map.put(b, map.getOrDefault(b, 0) - 1);
            if (tot == 0) {
                ans++;
            }
        }
        return ans;
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.Arrays;

/**
 * 得分最高的最小轮调
 * <p>
 * 给你一个数组nums，我们可以将它按一个非负整数 k 进行轮调，
 * 这样可以使数组变为[nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]的形式。
 * 此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为nums = [2,4,1,3,0]，我们按k = 2进行轮调后，它将变成[1,3,0,2,4]。
 * 这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择k = 3，得分最高。
 * 示例 2：
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] < nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestRotationWithHighestScore {
    public static void main(String[] args) {
        SmallestRotationWithHighestScore score = new SmallestRotationWithHighestScore();
        System.out.println(score.bestRotation(new int[]{2, 3, 1, 4, 0}));
        System.out.println(score.bestRotation(new int[]{1, 3, 0, 2, 4}));
    }

    static int N = 100010;
    static int[] c = new int[N];

    //差分
    void add(int l, int r) {
        c[l] += 1;
        c[r + 1] -= 1;
    }

    public int bestRotation(int[] nums) {
        Arrays.fill(c, 0);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //nums[i] 能够得分的 k 的取值范围为[i−(n−1),i−nums[i]]。
            int a = (i - (n - 1) + n) % n;//上界
            int b = (i - nums[i] + n) % n;//下界
            if (a <= b) {
                add(a, b);
            } else {
                add(0, b);
                add(a, n - 1);
            }
        }
        for (int i = 1; i <= n; i++) {
            c[i] += c[i - 1];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (c[i] > c[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}

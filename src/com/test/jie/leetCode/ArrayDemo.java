package com.test.jie.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayDemo {
    class QuickSortArray {
        //快速排序，快排
        public int[] sortArray(int[] nums) {
            randomizedQuickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void randomizedQuickSort(int[] nums, int l, int r) {
            if (l < r) {
                int pos = randomizedPartition(nums, l, r);
                //左闭右闭
                randomizedQuickSort(nums, l, pos - 1);
                randomizedQuickSort(nums, pos + 1, r);
            }
        }

        private int randomizedPartition(int[] nums, int l, int r) {
            int i = new Random().nextInt(r - l + 1) + l;
            swap(nums, i, r);
            return partition(nums, l, r);
        }
        //核心处理数组
        private int partition(int[] nums, int l, int r) {
            int p = nums[r];
            int slow = l;
            //快慢指针，慢指针指向存放下一个比p小的数的指针，快指针一直遍历区间数组
            for (int quick = l; quick < r; quick++) {
                if (nums[quick] <= p) {
                    swap(nums, slow, quick);
                    slow++;
                }
            }
            swap(nums, slow, r);
            return slow;
        }
        //交换
        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        //一圈一圈旋转
        for (int i = 0; i < len / 2; i++) {
            for (int j = i; j < len - i - 1; j++) {
                //当前圈的边界
                int m = len - j - 1;
                int n = len - i - 1;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }
        }
    }

    //https://leetcode.cn/problems/fruit-into-baskets/
    public int totalFruit(int[] fruits) {
        int len = fruits.length;
        int ans = 0;
        int fruitPieces = 0;
        int[] fruitCnt = new int[len];
        for (int i = 0, j = 0; i < len; i++) {
            if (fruitCnt[fruits[i]]++ == 0) {
                fruitPieces++;
            }
            if (fruitPieces > 2) {
                while (--fruitCnt[fruits[j++]] > 0) ;
                fruitPieces--;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    //https://leetcode.cn/problems/backspace-string-compare/
    public boolean backspaceCompare(String s, String t) {
        int sIdx = s.length() - 1;
        int tIdx = t.length() - 1;
        int sSkip = 0, tSkip = 0;
        while (sIdx >= 0 || tIdx >= 0) {
            while (sIdx >= 0) {
                if (s.charAt(sIdx) == '#') {
                    sSkip++;
                    sIdx--;
                } else if (sSkip > 0) {
                    sSkip--;
                    sIdx--;
                } else {
                    break;
                }
            }
            while (tIdx >= 0) {
                if (t.charAt(tIdx) == '#') {
                    tSkip++;
                    tIdx--;
                } else if (tSkip > 0) {
                    tSkip--;
                    tIdx--;
                } else break;
            }
            if (sIdx >= 0 && tIdx >= 0) {
                if (s.charAt(sIdx) != t.charAt(tIdx)) {
                    return false;
                }
            } else if (sIdx >= 0 || tIdx >= 0) {
                return false;
            }
            sIdx--;
            tIdx--;
        }
        return true;
    }

    //https://leetcode.cn/problems/move-zeroes/
    public void moveZeroes(int[] nums) {
        //不移动0，移动非0，后面补0
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[idx++] = nums[i];
            }
        }
        for (int i = idx; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 判题标准:
     * 系统会用下面的代码来测试你的题解:
     * int[] nums = [...]; // 输入数组
     * int[] expectedNums = [...]; // 长度正确的期望答案
     * int k = removeDuplicates(nums); // 调用
     * assert k == expectedNums.length;
     * for (int i = 0; i < k; i++) {
     * assert nums[i] == expectedNums[i];
     * }
     * 如果所有断言都通过，那么您的题解将被 通过。
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按 升序 排列
     */
    public int removeDuplicates(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[cnt] != nums[i]) {
                nums[++cnt] = nums[i];
            }
        }
        return cnt + 1;
    }

    /**
     * 59. 螺旋矩阵 II
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     * 提示：
     * 1 <= n <= 20
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int cnt = 1;
        int end = n * n;
        //遍历终点
        int right = n - 1;
        int bottom = n - 1;
        int left = 0;
        int top = 0;
        while (cnt <= end) {
            for (int i = left; i <= right && cnt <= end; i++) {
                ans[top][i] = cnt++;
            }
            top++;
            for (int i = top; i <= bottom && cnt <= end; i++) {
                ans[i][right] = cnt++;
            }
            right--;
            for (int i = right; i >= left && cnt <= end; i--) {
                ans[bottom][i] = cnt++;
            }
            bottom--;
            for (int i = bottom; i >= top && cnt <= end; i--) {
                ans[i][left] = cnt++;
            }
            left++;
        }
        return ans;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ans;
        int cnts = matrix.length * matrix[0].length;
        int right = matrix[0].length - 1;
        int left = 0;
        int top = 0;
        int bottom = matrix.length - 1;
        while (cnts > 0) {
            for (int i = left; i <= right && cnts > 0; i++) {
                ans.add(matrix[top][i]);
                cnts--;
            }
            top++;
            for (int i = top; i <= bottom && cnts > 0; i++) {
                ans.add(matrix[i][right]);
                cnts--;
            }
            right--;
            for (int i = right; i >= left && cnts > 0; i--) {
                ans.add(matrix[bottom][i]);
                cnts--;
            }
            bottom--;
            for (int i = bottom; i >= top && cnts > 0; i--) {
                ans.add(matrix[i][left]);
                cnts--;
            }
            left++;
        }
        return ans;
    }

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 示例 2：
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     * 提示：
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     * 进阶：
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            sum += nums[i];
            while (j <= i && sum >= target) {
                ans = Math.min(ans, (i - j + 1));
                sum -= nums[j++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * 你可以想象内部操作如下:
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     * 示例 2：
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     * 提示：
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */
    public int removeElement(int[] nums, int val) {
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
                ans++;
            }
        }
        return ans;
    }

    //https://leetcode.cn/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        int[] ans = new int[len];
        int idx = len - 1;
        while (idx >= 0) {
            if (nums[l] > 0 || (nums[r] >= 0 && -nums[l] < nums[r])) {
                ans[idx--] = nums[r] * nums[r];
                r--;
            } else {
                ans[idx--] = nums[l] * nums[l];
                l++;
            }
        }
        return ans;
    }

    public int[] sortedSquares2(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= 0) {
                r = mid;
            } else l = mid + 1;
        }
        if (nums[r] <= 0) {
            l = r;
            r = r + 1;
        } else {
            l = r - 1;
        }
        int[] ans = new int[len];
        int idx = 0;
        while (idx < len) {
            if (r == len || (l >= 0 && -nums[l] < nums[r])) {
                ans[idx++] = nums[l] * nums[l];
                l--;
            } else {
                ans[idx++] = nums[r] * nums[r];
                r++;
            }
        }
        return ans;
    }
}

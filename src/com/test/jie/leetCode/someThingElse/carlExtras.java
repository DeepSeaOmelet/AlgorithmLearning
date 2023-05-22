package com.test.jie.leetCode.someThingElse;

import com.test.jie.leetCode.tool.ListNode;

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

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] clone = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % n] = clone[i];
        }
    }

    /**
     * 724. 寻找数组的中心下标
     * 简单
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     * <p>
     * 示例 1：
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心下标是 3 。
     * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
     * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
     * 示例 2：
     * 输入：nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心下标。
     * 示例 3：
     * 输入：nums = [2, 1, -1]
     * 输出：0
     * 解释：
     * 中心下标是 0 。
     * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
     * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
     * <p>
     * 提示：
     * 1 <= nums.length <= 104
     * -1000 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int m = nums.length;
        //rightSum[i]等于nums[i+1,m)之和
        int[] rightSum = new int[m + 1];
        rightSum[m - 1] = nums[m - 1];
        for (int i = m - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }
        //System.out.println(Arrays.toString(rightSum));
        int leftSum = 0;
        for (int i = 0; i < m; i++) {
            if (leftSum == (rightSum[i] - nums[i])) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 中等
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * <p>
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * <p>
     * 提示：
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        //情况一：target 在数组范围的右边或者左边，例如数组{3, 4, 5}，target为2或者数组{3, 4, 5},target为6，此时应该返回{-1, -1}
        //情况二：target 在数组范围中，且数组中不存在target，例如数组{3,6,7},target为5，此时应该返回{-1, -1}
        //情况三：target 在数组范围中，且数组中存在target，例如数组{3,6,7},target为6，此时应该返回{1, 1}
        int rightBorder = getRightBorder(nums, target);
        int leftBorder = getLeftBorder(nums, target);
        //System.out.println(leftBorder+":"+rightBorder);
        if (rightBorder == -2 || leftBorder == -2) {
            return new int[]{-1, -1};

        }
        if (rightBorder - leftBorder > 1) {
            return new int[]{leftBorder + 1, rightBorder - 1};
        }
        return new int[]{-1, -1};
    }

    public int getRightBorder(int[] nums, int target) {
        // 二分查找，寻找target的右边界（不包括target）
        // 如果rightBorder为没有被赋值（即target在数组范围的左边，例如数组[3,3]，target为2），为了处理情况一
        int l = 0;
        int r = nums.length - 1;// 定义target在左闭右闭的区间里，[left, right]
        int rightBorder = -2;   // 记录一下rightBorder没有被赋值的情况
        //二分法
        while (l <= r) {
            int mid = l + r >> 1;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {// 当nums[middle] == target的时候，更新left，这样才能得到target的右边界
                l = mid + 1;
                rightBorder = l;
            }
            System.out.println(l + ":" + r);
        }
        return rightBorder;
    }

    public int getLeftBorder(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;//左闭右闭
        int leftBorder = -2;// 记录一下leftBorder没有被赋值的情况
        while (l <= r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                // 寻找左边界，就要在nums[middle] == target的时候更新right
                r = mid - 1;
                leftBorder = r;
            } else {
                l = mid + 1;
            }
        }
        return leftBorder;
    }

    /**
     * 922. 按奇偶排序数组 II
     * 简单
     * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
     * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
     * 你可以返回 任何满足上述条件的数组作为答案 。
     * <p>
     * 示例 1：
     * 输入：nums = [4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     * 示例 2：
     * 输入：nums = [2,3]
     * 输出：[2,3]
     * <p>
     * 提示：
     * 2 <= nums.length <= 2 * 104
     * nums.length 是偶数
     * nums 中一半是偶数
     * 0 <= nums[i] <= 1000
     * <p>
     * 进阶：可以不使用额外空间解决问题吗？
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityII(int[] nums) {
        //双指针
        int odd = 1;//奇数
        //偶数
        for (int even = 0; even < nums.length; even += 2) {
            if (nums[even] % 2 == 1) {
                while (nums[odd] % 2 != 0) {
                    odd += 2;
                }
                int temp = nums[odd];
                nums[odd] = nums[even];
                nums[even] = temp;
            }
        }
        return nums;
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

    /**
     * 35. 搜索插入位置
     * 简单
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * <p>
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     * <p>
     * 提示:
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 为 无重复元素 的 升序 排列数组
     * -104 <= target <= 104
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + right >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        System.out.println(left + ":" + right);
        return left;
    }

    /**
     * 24. 两两交换链表中的节点
     * 中等
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     * * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     * <p>
     * 提示：
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;
        dummyHead.next = head;

        while (head != null && head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            pre.next = next;
            pre = head;
            head = head.next;

        }
        return dummyHead.next;
    }

    /**
     * 234. 回文链表
     * 简单
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,2,1]
     * 输出：true
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：false
     * <p>
     * 提示：
     * 链表中节点数目在范围[1, 10^5] 内
     * 0 <= Node.val <= 9
     * <p>
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        //进阶？
        //递归,通过递归的回退和链表遍历进行比较
        isPalindromePreListNode = head;
        return checkIsPalindrome(head);
    }

    ListNode isPalindromePreListNode;

    public boolean checkIsPalindrome(ListNode curHead) {
        if (curHead == null) {
            return true;
        }
        if (!checkIsPalindrome(curHead.next)) {
            return false;
        }
        if (isPalindromePreListNode.val == curHead.val) {
            isPalindromePreListNode = isPalindromePreListNode.next;
            return true;
        } else return false;
    }

    public boolean isPalindrome2(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i <= j; i++, j--) {
            if (list.get(i).val != list.get(j).val) {
                return false;
            }
        }
        return true;

    }

    public boolean isPalindrome3(ListNode head) {
        //双指针
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;//走一步
        ListNode fast = head;//走两步
        ListNode pre = head;//作为slow的前一个链表
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //切割链表
        pre.next = null;
        ListNode cur1 = head;
        ListNode cur2 = reverseList(slow);
        //比较回文
        while (cur1 != null && cur2 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        //若链表数量为奇数，cur2可能会多一个节点
        return true;
    }

    public ListNode reverseList(ListNode head) {
        //反转链表
        ListNode temp = null;
        ListNode pre = null;
        //3->2->1  => 1->2->3
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    //143. 重排链表
    //中等
    //给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    //L0 → L1 → … → Ln - 1 → Ln
    //请将其重新排列后变为：
    //L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    //不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    //
    //示例 1：
    //输入：head = [1,2,3,4]
    //输出：[1,4,2,3]
    //示例 2：
    //输入：head = [1,2,3,4,5]
    //输出：[1,5,2,4,3]
    //
    //提示：
    //链表的长度范围为 [1, 5 * 104]
    //1 <= node.val <= 1000
    public void reorderList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        LinkedList<ListNode> linkedList = new LinkedList<>();
        while (head != null) {
            linkedList.add(head);
            head = head.next;
        }
        head = dummyHead;
        boolean isBack = false;
        while (head != null && !linkedList.isEmpty()) {
            if (!isBack) {
                head.next = linkedList.removeFirst();
            } else {
                head.next = linkedList.removeLast();
            }
            isBack = !isBack;
            head = head.next;
        }
    }

    /**
     * 141. 环形链表
     * 简单
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     * <p>
     * 提示：
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引 。
     * <p>
     * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.val == slow.val) {
                return true;
            }
        }
        return false;
    }

    /**
     * 205. 同构字符串
     * 简单
     * 给定两个字符串 s 和 t ，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     * <p>
     * 示例 1:
     * 输入：s = "egg", t = "add"
     * 输出：true
     * 示例 2：
     * 输入：s = "foo", t = "bar"
     * 输出：false
     * 示例 3：
     * 输入：s = "paper", t = "title"
     * 输出：true
     * <p>
     * 提示：
     * 1 <= s.length <= 5 * 104
     * t.length == s.length
     * s 和 t 由任意有效的 ASCII 字符组成
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int[] cnt1 = new int[256];
        int[] cnt2 = new int[256];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int c1 = s.charAt(i);
            int c2 = t.charAt(i);
            if (cnt1[c1] > 0 || cnt2[c2] > 0) {
                if (cnt1[c1] != (c2 + 1) ||
                        cnt2[c2] != (c1 + 1)) {
                    return false;
                }
            } else {
                cnt1[c1] = c2 + 1;
                cnt2[c2] = c1 + 1;
            }
        }
        return true;
    }
}


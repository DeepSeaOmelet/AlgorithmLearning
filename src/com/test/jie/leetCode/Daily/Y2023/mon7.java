package com.test.jie.leetCode.Daily.Y2023;

import com.test.jie.leetCode.tool.ListNode;
import com.test.jie.leetCode.tool.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class mon7 {
    class pathSum {
        List<List<Integer>> res;

        /**
         * 剑指 Offer 34. 二叉树中和为某一值的路径
         * 中等
         * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
         * 叶子节点 是指没有子节点的节点。
         * 示例 1：
         * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
         * 输出：[[5,4,11,2],[5,8,4,5]]
         * 示例 2：
         * 输入：root = [1,2,3], targetSum = 5
         * 输出：[]
         * 示例 3：
         * 输入：root = [1,2], targetSum = 0
         * 输出：[]
         * 提示：
         * 树中节点总数在范围 [0, 5000] 内
         * -1000 <= Node.val <= 1000
         * -1000 <= targetSum <= 1000
         *
         * @param root
         * @param target
         * @return
         */
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            res = new ArrayList<>();
            dfs(root, new ArrayList<Integer>(), target, 0);
            return res;
        }

        public void dfs(TreeNode root, List<Integer> list, int target, int sum) {
            if (root == null) {
                return;
            }
            //判断叶子节点
            if (root.left == null && root.right == null) {
                if (sum + root.val == target) {
                    list.add(root.val);
                    res.add(new ArrayList<>(list));
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(root.val);
                dfs(root.left, list, target, sum + root.val);
                dfs(root.right, list, target, sum + root.val);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 2544. 交替数字和
     * 简单
     * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
     * 最高有效位 上的数字分配到 正 号。
     * 剩余每位上数字的符号都与其相邻数字相反。
     * 返回所有数字及其对应符号的和。
     * 示例 1：
     * 输入：n = 521
     * 输出：4
     * 解释：(+5) + (-2) + (+1) = 4
     * 示例 2：
     * 输入：n = 111
     * 输出：1
     * 解释：(+1) + (-1) + (+1) = 1
     * 示例 3：
     * 输入：n = 886996
     * 输出：0
     * 解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
     * 提示：
     * 1 <= n <= 109
     *
     * @param n
     * @return
     */
    public int alternateDigitSum(int n) {
//        int sum  = 0;
//        int sign = 1;
//        Stack<Integer> stack = new Stack<>();
//        while (n>0){
//            stack.push(n%10);
//            n/=10;
//        }
//        while (!stack.isEmpty()){
//            sum+=stack.pop()*sign;
//            sign*=-1;
//        }
//        return sum;
        int sum = 0;
        int sign = 1;
        while (n > 0) {
            sum += n % 10 * sign;
            sign *= -1;
            n /= 10;
        }
        return sum * -sign;
    }

    /**
     * 874. 模拟行走机器人
     * 中等
     * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands ：
     * <p>
     * -2 ：向左转 90 度
     * -1 ：向右转 90 度
     * 1 <= x <= 9 ：向前移动 x 个单位长度
     * 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。
     * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
     * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
     * <p>
     * 注意：
     * 北表示 +Y 方向。
     * 东表示 +X 方向。
     * 南表示 -Y 方向。
     * 西表示 -X 方向。
     * <p>
     * 示例 1：
     * 输入：commands = [4,-1,3], obstacles = []
     * 输出：25
     * 解释：
     * 机器人开始位于 (0, 0)：
     * 1. 向北移动 4 个单位，到达 (0, 4)
     * 2. 右转
     * 3. 向东移动 3 个单位，到达 (3, 4)
     * 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
     * 示例 2：
     * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
     * 输出：65
     * 解释：机器人开始位于 (0, 0)：
     * 1. 向北移动 4 个单位，到达 (0, 4)
     * 2. 右转
     * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
     * 4. 左转
     * 5. 向北走 4 个单位，到达 (1, 8)
     * 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
     * <p>
     * 提示：
     * 1 <= commands.length <= 104
     * commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
     * 0 <= obstacles.length <= 104
     * -3 * 104 <= xi, yi <= 3 * 104
     * 答案保证小于 231
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int ans = 0;
        //0北{0,1}，1东{1,0}，2南{0,-1}，3西{-1,0}
        int dir = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //机器人当前位置
        int[] cur = {0, 0};
        //机器人判断可走的步数
        int[] pre = {0, 0};
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < obstacles.length; i++) {
            List<Integer> list = map.getOrDefault(obstacles[i][0], new ArrayList<>());
            list.add(obstacles[i][1]);
            map.put(obstacles[i][0], list);
        }
        int len = commands.length;
        for (int i = 0; i < len; i++) {
            int curCom = commands[i];
            boolean stop = false;
            if (curCom > 0) {
                pre[0] = cur[0];
                pre[1] = cur[1];
                for (int j = curCom; j > 0 && !stop; j--) {
                    pre[0] += dirs[dir][0];
                    pre[1] += dirs[dir][1];
                    List<Integer> list = map.get(pre[0]);
                    if (list != null) {
                        for (int y : list) {
                            if (pre[1] == y) {
                                stop = true;
                                pre[0] -= dirs[dir][0];
                                pre[1] -= dirs[dir][1];
                                break;
                            }
                        }
                    }
                }
                cur[0] = pre[0];
                cur[1] = pre[1];
                System.out.println(Arrays.toString(cur));
            } else if (curCom == -1) {
                dir = (dir + 1) % 4;
            } else {
                dir = (dir - 1 + 4) % 4;
            }
            ans = Math.max(ans, cur[0] * cur[0] + cur[1] * cur[1]);
        }
        return ans;
    }

    /**
     * 918. 环形子数组的最大和
     * 中等
     * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
     * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
     * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     * <p>
     * 示例 1：
     * 输入：nums = [1,-2,3,-2]
     * 输出：3
     * 解释：从子数组 [3] 得到最大和 3
     * 示例 2：
     * 输入：nums = [5,-3,5]
     * 输出：10
     * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
     * 示例 3：
     * 输入：nums = [3,-2,2,-3]
     * 输出：3
     * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 3 * 104
     * -3 * 104 <= nums[i] <= 3 * 104
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int maxS = Integer.MIN_VALUE;
        int minS = 0;
        int maxDP = 0;
        int minDP = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            // 以 nums[i-1] 结尾的子数组选或不选（取 max）+ x = 以 x 结尾的最大子数组和
            maxDP = Math.max(maxDP + nums[i], nums[i]);
            maxS = Math.max(maxS, maxDP);
            // 以 nums[i-1] 结尾的子数组选或不选（取 min）+ x = 以 x 结尾的最大子数组和
            minDP = Math.min(minDP + nums[i], nums[i]);
            minS = Math.min(minS, minDP);
            sum += nums[i];
        }
        return sum == minS ? maxS : Math.max(maxS, sum - minS);
    }

    //860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int[] change = new int[2];//bills[i] 不是 5 就是 10 或是 20
        for (int m : bills) {
            if (m == 5) {
                change[0]++;
            } else if (m == 10) {
                change[1]++;
                if (change[0] <= 0) {
                    return false;
                }
                change[0]--;
            } else {
                if (change[1] > 0 && change[0] > 0) {
                    change[1]--;
                    change[0]--;
                } else if (change[0] > 2) {
                    change[0] -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    //42. 接雨水
    public int trap(int[] height) {
        //左右扫
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i], left[i - 1]);
        }
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }
//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));
        for (int i = 0; i < n; i++) {
            int curHeight = Math.min(left[i], right[i]);
            ans += curHeight > height[i] ? curHeight - height[i] : 0;
        }
        return ans;
    }

    //2500. 删除每行中的最大值
    public int deleteGreatestValue(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            Arrays.sort(grid[i]);
        }
        for (int k = m - 1; k >= 0; k--) {
            int curMax = 0;
            for (int i = 0; i < n; i++) {
                curMax = Math.max(curMax, grid[i][k]);
            }
            ans += curMax;
        }
        return ans;
    }

    //2050. 并行课程 III
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        //使用一个数组 indeg 存储每个节点的入度
        int[] indeg = new int[n];
        //构建有向无环图g
        for (int[] r : relations) {
            int a = r[0] - 1;
            int b = r[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        //队列 q 存储所有入度为 0 的节点；
        Deque<Integer> q = new ArrayDeque<>();
        //数组 dp 存储每个节点的最早完成时间，初始时 dp[i]=0；
        int[] dp = new int[n];
        int ans = 0;//结果
        for (int i = 0; i < n; i++) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                dp[i] = t;
                ans = Math.max(ans, t);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                dp[j] = Math.max(dp[j], dp[i] + time[j]);
                ans = Math.max(ans, dp[j]);
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            //链路长度链表头部到链表入口 有 a 个节点，当slow与fast相遇，
            // slow还没有走完一整圈，比如最坏情况，slow进入，fast在前面，用相对速度比较slow不走，fast走一步，fast也要走环长-1步才会遇上slow，即slow走不满环长就和fast相遇了。
            // 环长=b+c,c 的定义是相遇点向前走到入口的距离
            //相遇点slow=a+b，fast=a+b+k(b+c)
            //快指针比慢指针快两倍：2(a+b)=a+b+k(b+c) => a-c=(k-1)(b+c)，这意味着当点a从开头走了c步，剩下到环头的步数是环长的倍数
            //而相遇点点b向前走距离环头也是只有c步，所以当a和b继续走就一定相遇
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return head;
            }
        }
        return null;
    }

    //143. 重排链表
    public void reorderList(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        head = dummyHead.next;
        while (!stack.isEmpty()) {
            ListNode curNode = stack.pop();
            curNode.next = head.next;
            head.next = curNode;
            head = curNode.next;
            if (curNode == head) {
                break;
            }
        }
        head.next = null;
    }

    public int sumOfPower(int[] nums) {
        final long MOD = (long) 1e9 + 7;
        long sum = 0, ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            ans = (ans + (long) nums[i] * nums[i] % MOD * (nums[i] + sum)) % MOD;
            sum = (sum * 2 + nums[i]) % MOD;
        }
        return (int) ans;
    }

    //822. 翻转卡片游戏
    public int flipgame(int[] fronts, int[] backs) {
        int ans = Integer.MAX_VALUE;
        int[] map = new int[2001];
        int len = backs.length;
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                map[fronts[i]]++;
            }
        }
        for (int i = 0; i < len; i++) {
            if (map[fronts[i]] == 0) {
                ans = Math.min(ans, fronts[i]);
            }
            if (map[backs[i]] == 0) {
                ans = Math.min(ans, backs[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (head != null && head.next != null) {
            ListNode cur = head.next;
            pre.next = cur;
            head.next = cur.next;
            cur.next = head;
            pre = head;
            head = head.next;
        }
        return dummyHead.next;
    }

    //21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode(-1);
        pre.next = list1;
        list1 = pre;
        while (list2 != null) {
            if (pre.next != null) {
                if (pre.next.val > list2.val) {
                    ListNode next = pre.next;
                    pre.next = list2;
                    list2 = list2.next;
                    pre.next.next = next;
                }
                pre = pre.next;
            } else {
                pre.next = list2;
                break;
            }
        }
        return list1.next;
    }

    //722. 删除注释
    public List<String> removeComments(String[] source) {
        return Arrays.stream(String.join("\n", source).replaceAll("//.*|/\\*(.|\n)*?\\*/", "")
                .split("\n")).filter(s -> (s.length() > 0)).collect(Collectors.toList());
    }

    //1749. 任意子数组和的绝对值的最大值
    public int maxAbsoluteSum(int[] nums) {
        int len = nums.length;
        int ans = 0;
        int max = 0;
        int min = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max + nums[i], nums[i]);
            min = Math.min(min + nums[i], nums[i]);
            ans = Math.max(ans, Math.max(-min, max));
        }
        return ans;
    }

    //1281. 整数的各位积和之差
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int multiSum = 1;
        while (n > 0) {
            sum += n % 10;
            multiSum *= n % 10;
            n /= 10;
        }
        return multiSum - sum;
    }

    //1289. 下降路径最小和 II
    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length;
        //相对于之前那个，状态转移的时候依赖的第i-1行中的状态（min(dp[i-1][k]）有很多是相同的。
        //所以firstMinSum保存第i-1行的最小值，secondMinSum为次最小值，firstMinIdx为最小值下标
        int firstMinSum = 0;
        int secondMinSum = 0;
        int firstMinIdx = -1;
        for (int i = 0; i < n; i++) {
            //当前行
            int curFirstMinSum = Integer.MAX_VALUE;
            int curSecondMinSum = Integer.MAX_VALUE;
            int curFirstMinIdx = -1;
            for (int j = 0; j < n; j++) {
                int curSum = (j != firstMinIdx ? firstMinSum : secondMinSum) + grid[i][j];
                if (curSum < curFirstMinSum) {
                    curSecondMinSum = curFirstMinSum;
                    curFirstMinSum = curSum;
                    curFirstMinIdx = j;
                } else if (curSum < curSecondMinSum) {
                    curSecondMinSum = curSum;
                }
            }
            //迭代
            firstMinSum = curFirstMinSum;
            secondMinSum = curSecondMinSum;
            firstMinIdx = curFirstMinIdx;
        }
        return firstMinSum;
    }

    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i - 1][k] + grid[i][j], dp[i][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }
        return ans;
    }

    //1572. 矩阵对角线元素的和
    public int diagonalSum(int[][] mat) {
        int ans = 0;
        int left = 0;
        int len = mat.length;
        int right = len - 1;
        for (int i = 0; i < len; i++) {
            if (left == right) {
                ans -= mat[i][left];
            }
            ans += mat[i][left] + mat[i][right];
            left++;
            right--;
        }
        return ans;
    }

    //88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = m - 1;
        int k = n - 1;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (j >= 0 && k >= 0) {
                if (nums1[j] > nums2[k]) {
                    nums1[i] = nums1[j];
                    j--;
                } else {
                    nums1[i] = nums2[k];
                    k--;
                }
            } else if (j < 0) {
                nums1[i] = nums2[k--];
            } else {
                nums1[i] = nums1[j--];
            }
        }
    }

    //617
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
//        if (root1 == null && root2 == null) {
//            return null;
//        }
//        if (root1 != null && root2 != null) {
//            root1.val += root2.val;
//            root1.left = mergeTrees(root1.left, root2.left);
//            root1.right = mergeTrees(root1.right, root2.right);
//            return root1;
//        } else if (root1 == null) {
//            return root2;
//        } else {
//            return root1;
//        }
    }

    //833. 字符串中的查找与替换
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int[] afterSort = new int[s.length()];
        Arrays.fill(afterSort, -1);
        for (int i = 0; i < indices.length; i++) {
            afterSort[indices[i]] = i;
        }
        int i = 0;
        for (int k = 0; k < len && i < s.length(); k++) {
            if (afterSort[k] == -1) {
                continue;
            }
            int index = indices[afterSort[k]];
            String source = sources[afterSort[k]];
            String target = targets[afterSort[k]];
            int start = i;
            while (i < index) {
                i++;
            }
            if (start != i) {
                sb.append(s.substring(start, i));
            }
            start = i;
            boolean flag = true;
            for (int j = 0; j < source.length(); j++) {
                if (s.charAt(start) != source.charAt(j)) {
                    flag = false;
                    break;
                }
                start++;
            }
            if (flag) {
                i = start;
                sb.append(target);
            }
        }
        if (i < s.length()) {
            sb.append(s.substring(i));
        }
        return sb.toString();
    }

    //2235. 两整数相加
    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    //1388. 3n 块披萨
    class maxSizeSlices {
        public int maxSizeSlices(int[] slices) {
            return Math.max(slicePizza(slices, 0, slices.length - 2), slicePizza(slices, 1, slices.length - 1));
        }

        public int slicePizza(int[] slices, int start, int end) {
            int len = slices.length;
            int N = len / 3;
            //dp[i][j] 表示在前 i 个数中选择了 j 个不相邻的数的最大和
            int[][] dp = new int[len][N + 1];
            dp[start][0] = 0;
            dp[start][1] = slices[start];
            dp[start + 1][0] = 0;
            dp[start + 1][1] = Math.max(slices[start], slices[start + 1]);
            for (int i = start + 2; i < len; i++) {
                dp[i][0] = 0;
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 2][j - 1] + slices[i]);
                }
            }
            return dp[end][N];
        }
    }

    //1444. 切披萨的方案数
//    public int ways(String[] pizza, int k) {
//        int m = pizza.length;
//        int n = pizza[0].length();
//        int[][] pizzaHub = new int[m][n];
//        for (int j = 0; j < m; j++) {
//            for (int i = 0; i < n; i++) {
//                if (pizza[j].charAt(i) == 'A') {
//                    pizzaHub[j][i] = 1;
//                }
//            }
//        }
//
//    }

    //2236. 判断根结点是否等于子结点之和
    public boolean checkTree(TreeNode root) {
        return root.val == (root.left.val + root.right.val);
    }

    //2337. 移动片段得到字符串
    public boolean canChange(String start, String target) {
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) {
            return false;
        }
        int startIdx = 0;
        int len = start.length();
        for (int i = 0; i < len; i++) {
            if (target.charAt(i) == '_') {
                continue;
            }
            while (startIdx < len && start.charAt(startIdx) == '_') {
                startIdx++;
            }
//            System.out.println((char)target.charAt(i)+":"+start.charAt(startIdx));
//            if ((start.charAt(startIdx) == 'L' && i > startIdx) || (start.charAt(startIdx) == 'R' && i < startIdx)) {
//                return false;
//            }
            if (i != startIdx && (start.charAt(startIdx) == 'L') == (startIdx < i)) {
                return false;
            }
            startIdx++;
        }
        return true;
    }

    //849. 到最近的人的最大距离
    public int maxDistToClosest(int[] seats) {
        int ans = 0;
        for (int right = 0, left = -1; right < seats.length; right++) {
            while (right < seats.length && seats[right] == 0) {
                right++;
            }
            if (left == -1 || right == seats.length) {
                ans = Math.max(ans, right - left - 1);
            } else {
                ans = Math.max(ans, (right - left) / 2);
            }
            left = right;
        }
        return ans;
    }

    //1782. 统计点对的数目 困难 无向图
//    public int[] countPairs(int n, int[][] edges, int[] queries) {
//
//    }

    //1267. 统计参与通信的服务器
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] exist = new int[2][n + m];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sum++;
                    exist[0][i]++;
                    exist[1][j]++;
                }
            }
        }

        int ans = sum;
        for (int i = 0; i < m; i++) {
            if (exist[0][i] == 1) {
                for (int j = 0; j < n; j++) {
                    if (exist[1][j] == 1 && grid[i][j] == 1) {
                        ans--;
                    }
                }
            }
        }
        return ans;
    }

    //1448. 统计二叉树中好节点的数目
    class goodNodes {
        public int goodNodes(TreeNode root) {
            return dfs(root.left, root.val) + dfs(root.right, root.val) + 1;
        }

        public int dfs(TreeNode root, int max) {
            if (root == null) {
                return 0;
            }
            int ans = 0;
            if (root.val >= max) {
                ans++;
                max = root.val;
            }
            ans += dfs(root.left, max);
            ans += dfs(root.right, max);
            return ans;
        }
    }

    //228. 汇总区间
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (nums.length == 0) {
            return ans;
        }
        sb.append(nums[0]);
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                if (pre != nums[i - 1]) {
                    sb.append("->").append(nums[i - 1]);
                }
                ans.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(nums[i]);
                pre = nums[i];
            }
        }
        if (pre != nums[nums.length - 1]) {
            sb.append("->").append(nums[nums.length - 1]);
        }
        ans.add(sb.toString());

        return ans;
    }

    //2279. 装满石头的背包的最大数量
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int ans = 0;
        int len = rocks.length;
        int[] remains = new int[len];
        for (int i = 0; i < len; i++) {
            int remain = capacity[i] - rocks[i];
            remains[i] = remain;
        }
        Arrays.sort(remains);
        for (int i = 0; i < len; i++) {
            int curRemain = remains[i];
            if (curRemain <= additionalRocks) {
                additionalRocks -= curRemain;
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    //57. 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        boolean place = false;
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[0] > intervals[i][1]) {
                list.add(intervals[i]);
            } else if (newInterval[1] < intervals[i][0]) {
                if (!place) {
                    place = true;
                    list.add(newInterval);
                }
                list.add(intervals[i]);
            } else {
                //有交集，更新区间
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }
        if (!place) {
            list.add(newInterval);
        }
        int[][] ints = new int[list.size()][2];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    //823. 带因子的二叉树
    public int numFactoredBinaryTrees2(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        long ans = 0;
        long[] dp = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                int x = arr[j];
                if (cur % x == 0 && map.containsKey(cur / x)) {
                    // 另一个因子 val/x 必须在 arr 中
                    dp[i] += dp[j] * dp[map.get(cur / x)];
                }
            }
            ans += dp[i];
        }
        return (int) (ans % MOD);
    }

    //1654. 到家的最少跳跃次数

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        HashSet<Integer> visitSet = new HashSet<>();
        // 坐标，方向(1为前进, -1为后退)，步数
        deque.offer(new int[]{0, 1, 0});
        visitSet.add(0);
        // 定义左右边界 右边界为 max(f + a + b, x + b)
        int lower = 0, upper = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, x) + b;
        HashSet<Integer> forbiddenSet = new HashSet<>();
        for (int position : forbidden) {
            forbiddenSet.add(position);
        }
        //广度优先搜索
        while (!deque.isEmpty()) {
            int[] arr = deque.poll();
            int position = arr[0], direction = arr[1], step = arr[2];
            //成功到达目标
            if (position == x) {
                return step;
            }

            //假设下一次方向为前进；下一次前进后的坐标
            int nextDirection = 1;
            int nextPosition = position + a;
            // 没有超出边界 && 没有前进到达过这个位置 && 这个坐标不是不可到达点
            if (lower <= nextPosition && nextPosition <= upper &&
                    !visitSet.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                //visited 标记这个点(nextPosition)可以前进到达
                visitSet.add(nextPosition * nextDirection);
                //在队列中加入前进后到达的点
                deque.offer(new int[]{nextPosition, nextDirection, step + 1});
            }

            // 如果本次方向是前进，假设下一次是后退
            if (direction == 1) {
                nextPosition = position - b;
                nextDirection = -1;
                if (lower <= nextPosition && nextPosition <= upper &&
                        !visitSet.contains(nextPosition * nextDirection) && !forbiddenSet.contains(nextPosition)) {
                    // visited标记 且 加入queue中
                    visitSet.add(nextPosition * nextDirection);
                    deque.offer(new int[]{nextPosition, nextDirection, step + 1});
                }
            }
        }
        return -1;
    }


    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        final long MOD = (long) 1e9 + 7;
        HashMap<Integer, Long> map = new HashMap<>();
        for (int x : arr) {
            map.put(x, 1L);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (1L * arr[i] * arr[j] <= arr[arr.length - 1]) {
                    if (map.containsKey(arr[i] * arr[j])) {
                        if (i == j) {
                            map.put(arr[i] * arr[j], (map.get(arr[i]) * map.get(arr[j]) + map.get(arr[i] * arr[j])) % MOD);
                        } else {
                            map.put(arr[i] * arr[j], (2 * map.get(arr[i]) * map.get(arr[j]) + map.get(arr[i] * arr[j])) % MOD);
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
//            ans = (ans + map.get(arr[i])) % MOD;
            ans += map.get(arr[i]);
            ans %= MOD;
        }
        return (int) ans;
    }

    //2240. 买钢笔和铅笔的方案数
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int ans = 0;
        for (int i = 0; i <= total; i += cost1) {
            ans += (total - i) / cost2 + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        mon7 mon7 = new mon7();
        System.out.println(mon7.waysToBuyPensPencils(20, 10, 5));
    }

}

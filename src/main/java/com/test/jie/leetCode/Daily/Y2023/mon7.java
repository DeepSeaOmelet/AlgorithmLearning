package com.test.jie.leetCode.Daily.Y2023;

import com.test.jie.leetCode.tool.ListNode;
import com.test.jie.leetCode.tool.Node;
import com.test.jie.leetCode.tool.TreeNode;

import javax.swing.text.Segment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        long ans = 0;
        for (int i = 0; i <= total; i += cost1) {
            ans += (total - i) / cost2 + 1;
        }
        return ans;
    }

    //2511. 最多可以摧毁的敌人城堡数目
    public int captureForts(int[] forts) {
        int ans = 0;
        for (int i = 0, j = 0; i < forts.length; i++) {
            if (forts[i] == 0) {
                continue;
            }
            if (forts[i] * forts[j] == -1) {
                ans = Math.max(ans, i - j - 1);
            }
            j = i;
        }
        return ans;
    }

    //1921. 消灭怪物的最大数量
    public int eliminateMaximum(int[] dist, int[] speed) {
        int ans = 1;
        int n = dist.length;
        int[] deadLine = new int[n];
        for (int i = 0; i < n; i++) {
            deadLine[i] = dist[i] / speed[i];
        }
        Arrays.sort(deadLine);
        for (int i = 1; i < n; i++) {
            if (deadLine[i] < i) {
                return ans;
            }
            ans++;
        }
        return ans;
    }

    //449. 序列化和反序列化二叉搜索树
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            //后序遍历填充数据
            postOrder(root, list);
            String str = list.toString();
            return str.substring(1, str.length() - 1);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] dataArr = data.split(",");
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < dataArr.length; i++) {
                deque.push(Integer.parseInt(dataArr[i]));
            }
            return build(Integer.MIN_VALUE, Integer.MAX_VALUE, deque);
        }

        //后序遍历
        private void postOrder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }

        private TreeNode build(int lower, int upper, Deque<Integer> deque) {
            if (deque.isEmpty() || deque.peek() < lower || deque.peek() > upper) {
                return null;
            }
            int val = deque.pop();
            TreeNode root = new TreeNode(val);
            root.right = build(val, upper, deque);
            root.left = build(lower, val, deque);
            return root;
        }
    }

    //2605. 从两个数字数组里生成最小数字
    public int minNumber(int[] nums1, int[] nums2) {
        int[] cnts = new int[10];
        // Initialize min and sameMin to 10
        int min = 10;
        // Iterate through nums1
        for (int i = 0; i < nums1.length; i++) {
            // Increase count of nums1[i] in cnts
            cnts[nums1[i]]++;
            // Update min if nums1[i] is less than min
            min = Math.min(min, nums1[i]);
        }
        // Initialize min2 and sameMin to 10
        int min2 = 10;
        int sameMin = 10;
        // Iterate through nums2
        for (int i = 0; i < nums2.length; i++) {
            // If count of nums2[i] is greater than 0
            if (cnts[nums2[i]] > 0) {
                // Update sameMin if nums2[i] is less than sameMin
                sameMin = Math.min(sameMin, nums2[i]);
            }
            // Update min2 if nums2[i] is less than min2
            min2 = Math.min(min2, nums2[i]);
        }
        // If sameMin is less than 10, return sameMin
        if (sameMin < 10) {
            return sameMin;
            // Else, return min > min2? (min2 * 10 + min) : (min * 10 + min2)
        } else {
            return min > min2 ? (min2 * 10 + min) : (min * 10 + min2);
        }
    }

    class lcaDeepestLeaves {
        private int maxDepth = 0;
        private TreeNode ans = null;

        //1123. 最深叶节点的最近公共祖先
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            dfs(root, 0);
            return ans;
        }

        private int dfs(TreeNode root, int depth) {
            if (root == null) {
                //维护节点最大深度
                maxDepth = Math.max(maxDepth, depth - 1);
                return depth - 1;
            }
            int left = dfs(root.left, depth + 1);
            int right = dfs(root.right, depth + 1);
            //左右子节点最大深度一样且都是最大深度，该节点则为目前最近公共祖先
            if (left == right && left == maxDepth) {
                ans = root;
            }

            return Math.max(left, right);
        }
    }

    //2594. 修车的最少时间
    //假设总时间为time，每个修车个修车数量为xi，那么time=max{rank[i]*xi^2}，这里目标指向time，那么这道题就转换成【求最小的最大值】问题，一般都是二分法
    public long repairCars(int[] ranks, int cars) {
        long left = 0;
        long right = ranks[0] * cars * cars;
        while (left < right) {
            long mid = (left + right) / 2;
            long nums = 0;
            for (int r : ranks) {
                //算出该时间下修车的最大数量
                nums += Math.sqrt(mid / r);
            }
            if (nums >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //207. 课程表  拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //构建邻接表
        List<List<Integer>> edges = new ArrayList<>();
        int[] indeg = new int[numCourses];//入度数组
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //构建邻接表
        for (int[] require : prerequisites) {
            edges.get(require[1]).add(require[0]);
            indeg[require[0]]++;
        }
        Queue<Integer> d = new ArrayDeque<>();
        //将所有入度为0的节点入队
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                d.offer(i);
            }
        }
        //遍历队列，将队首元素出队，并将队首元素指向的节点的入度减1，如果入度为0，
        // 则入队，直到队列为空
        int visits = 0;
        while (!d.isEmpty()) {
            int cur = d.poll();
            visits++;
            for (int next : edges.get(cur)) {
                indeg[next]--;
                if (indeg[next] == 0) {
                    d.offer(next);
                }
            }
        }
        return visits == numCourses;
    }

    //2651. 计算列车到站时间
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }

    //210. 课程表 II   拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //入度数组
        int[] indegs = new int[numCourses];
        for (int[] preCou : prerequisites) {
            edges.get(preCou[1]).add(preCou[0]);
            indegs[preCou[0]]++;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (indegs[i] == 0) {
                deque.offer(i);
            }
        }
        int cnt = 0;
        while (!deque.isEmpty()) {
            int cur = deque.poll();
            ans[cnt++] = cur;
            for (int next : edges.get(cur)) {
                indegs[next]--;
                if (indegs[next] == 0) {
                    deque.offer(next);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }

    //630. 课程表 III
    //这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
    // 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
    // 并且必须在不晚于 lastDayi 的时候完成。
    //
    //你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
    //
    //返回你最多可以修读的课程数目。
    //
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);//升序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int day = 0;
        for (int[] c : courses) {
            int duration = c[0];
            int lastDay = c[1];
            if (day + duration <= lastDay) {
                day += duration;
                pq.offer(duration);
            } else if (!pq.isEmpty() && pq.peek() > duration) {
                day -= pq.poll() - duration;
                pq.offer(duration);
            }
        }
        return pq.size();

    }

    //1462. 课程表 IV
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //构建邻接表
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //入度表
        int[] indegs = new int[numCourses];
        for (int[] p : prerequisites) {
            edges.get(p[0]).add(p[1]);
            indegs[p[1]]++;
        }
        //课程之间的关系
        boolean[][] isPre = new boolean[numCourses][numCourses];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegs[i] == 0) {
                deque.offer(i);
            }
        }
        //拓扑排序
        while (!deque.isEmpty()) {
            int cur = deque.poll();
            for (int u : edges.get(cur)) {
                //构建关系,如果isPre[i][cur]是true，那么isPre[i][u]也是
                isPre[cur][u] = true;
                for (int i = 0; i < numCourses; i++) {
                    isPre[i][u] = isPre[i][cur] || isPre[i][u];
                }
                indegs[u]--;
                if (indegs[u] == 0) {
                    deque.offer(u);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(isPre[query[0]][query[1]]);
        }
        return ans;
    }

    //2596. 检查骑士巡视方案
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int x = 0;
        int y = 0;
        int fullStep = grid.length * grid.length - 1;
        int len = grid.length;
        //坐标轴原点为左上角，横轴为x，竖轴为y，{x,y}
        int[][] steps = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
        while (grid[x][y] < fullStep) {
            int cur = grid[x][y];
            for (int i = 0; i < steps.length; i++) {
                int nextX = x + steps[i][0];
                int nextY = y + steps[i][1];
                if (nextX >= 0 && nextY >= 0 && nextX < len && nextY < len && grid[nextX][nextY] == cur + 1) {
                    x = nextX;
                    y = nextY;
                    break;
                }
            }
            if (cur == grid[x][y]) {
                System.out.println(grid[x][y]);
                return false;
            }
        }
        return true;
    }

    //1222. 可以攻击国王的皇后
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        final int EIGHT = 8;
        boolean[][] hasQueen = new boolean[EIGHT][EIGHT];
        for (int[] queen : queens) {
            hasQueen[queen[0]][queen[1]] = true;
        }
        //从king找queen
        int[][] directions = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        for (int[] direction : directions) {
            int x = king[0];
            int y = king[1];
            while (x >= 0 && y >= 0 && x < EIGHT && y < EIGHT) {
                if (hasQueen[x][y]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    ans.add(list);
                    break;
                }
                x += direction[0];
                y += direction[1];
            }
        }
        return ans;
    }

    //LCP 50. 宝石补给
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            gem[operation[1]] += gem[operation[0]] / 2;
            gem[operation[0]] -= gem[operation[0]] / 2;
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < gem.length; i++) {
            max = Math.max(max, gem[i]);
            min = Math.min(min, gem[i]);
        }
        return max - min;
    }

    //198. 打家劫舍
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    //213. 打家劫舍 II
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int front = rob2Circle(nums, 0, len - 2);
        int back = rob2Circle(nums, 1, len - 1);
        return Math.max(front, back);
    }

    public int rob2Circle(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(dp[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }

    //337. 打家劫舍 III
    public int rob3(TreeNode root) {
        return rob3TreeAction(root)[0];
    }

    public int[] rob3TreeAction(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        //后序
        int[] left = rob3TreeAction(root.left);
        int[] right = rob3TreeAction(root.right);
        //ans[0]这个， ans[1]上一个
        int[] ans = new int[2];
        ans[0] = Math.max(root.val + left[1] + right[1], left[0] + right[0]);
        ans[1] = left[0] + right[0];
        return ans;
    }

    //2560. 打家劫舍 IV
    public int minCapability(int[] nums, int k) {
        //        if (nums.length == 1) {
        //            return nums[0];
        //        }
        int left = 0, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);
        }
        //左闭右开[left,right)
        while (left < right) {
            int mid = (left + right) / 2;
            if (checkMinCapability(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean checkMinCapability(int[] nums, int k, int mid) {
        //定义 f[i] 表示从nums[0] 到 nums[i] 中偷金额不超过 mid 的房屋，最多能偷多少间房屋。
        //        int[] dp = new int[nums.length];
        //        dp[0] = nums[0] > mid ? 0 : 1;
        //        dp[1] = nums[1] > mid ? dp[0] : 1;
        //        for (int i = 2; i < nums.length; i++) {
        //            if (nums[i] > mid) {
        //                dp[i] = dp[i - 1];
        //            } else {
        //                dp[i] = Math.max(dp[i - 1], dp[i - 2] + 1);
        //            }
        //        }
        //        return dp[nums.length - 1] >= k;

        //贪心
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= mid) {
                cnt++;
                i++;//跳过
            }
        }
        return cnt >= k;
    }

    //LCP 06. 拿硬币
    public int minCount(int[] coins) {
        int ans = 0;
        for (int x : coins) {
            ans += (x + 1) / 2;
        }
        return ans;
    }

    /* 2603. 收集树中金币
     * 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
     *  其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。再给你一个长度为 n 的数组 coins ，
     *  其中 coins[i] 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。
     *
     * 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
     *
     * 收集距离当前节点距离为 2 以内的所有金币，或者
     * 移动到树中一个相邻节点。
     * 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
     *
     * 如果你多次经过一条边，每一次经过都会给答案加一。
     *
     * 输入：coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 解释：从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
     *
     * 输入：coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
     * 输出：2
     * 解释：从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
     */
    public int collectTheCoins(int[] coins, int[][] edges) {
        return 0;
    }

    //2591. 将钱分给最多的儿童
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        int tmp = children * 8 - money;
        if (tmp == 4) {
            return children - 2;
        }
        if (tmp < 0) {
            return children - 1;
        }
        return (money - children) / 7;

    }

    /**
     * 1993. 树上的操作
     * 给你一棵 n 个节点的树，编号从 0 到 n - 1 ，以父节点数组 parent 的形式给出，其中 parent[i] 是第 i 个节点的父节点。
     * 树的根节点为 0 号节点，所以 parent[0] = -1 ，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，
     * 解锁和升级操作。
     * <p>
     * 数据结构需要支持如下函数：
     * <p>
     * Lock：指定用户给指定节点 上锁 ，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。
     * Unlock：指定用户给指定节点 解锁 ，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。
     * Upgrade：指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁 。只有如下 3 个条件 全部 满足时才能执行升级操作：
     * *指定节点当前状态为未上锁。
     * *指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
     * *指定节点没有任何上锁的祖先节点。
     * 请你实现 LockingTree 类：
     * <p>
     * LockingTree(int[] parent) 用父节点数组初始化数据结构。
     * lock(int num, int user) 如果 id 为 user 的用户可以给节点 num 上锁，那么返回 true ，否则返回 false 。
     * * 如果可以执行此操作，节点 num 会被 id 为 user 的用户 上锁 。
     * unlock(int num, int user) 如果 id 为 user 的用户可以给节点 num 解锁，那么返回 true ，否则返回 false 。
     * * 如果可以执行此操作，节点 num 变为 未上锁 状态。
     * upgrade(int num, int user) 如果 id 为 user 的用户可以给节点 num 升级，那么返回 true ，否则返回 false 。
     * * 如果可以执行此操作，节点 num 会被 升级 。
     * <p>
     * 提示：
     * n == parent.length
     * 2 <= n <= 2000
     * 对于 i != 0 ，满足 0 <= parent[i] <= n - 1
     * parent[0] == -1
     * 0 <= num <= n - 1
     * 1 <= user <= 104
     * parent 表示一棵合法的树。
     * lock ，unlock 和 upgrade 的调用 总共 不超过 2000 次。*
     */
    class LockingTree {
        private final List<Integer>[] children;
        private final int[] nodeLocker;//-1为未上锁，其余为用户上锁
        private final int n;
        private final int[] parent;

        public LockingTree(int[] parent) {
            this.parent = parent;
            n = parent.length;
            nodeLocker = new int[n];
            Arrays.fill(nodeLocker, -1);
            this.children = new List[n];
            for (int i = 0; i < n; i++) {
                this.children[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n; i++) {
                int cur = parent[i];
                if (cur != -1) {
                    children[cur].add(i);
                }
            }
        }

        public boolean lock(int num, int user) {
            if (num < 0 || num >= n || nodeLocker[num] != -1) {
                return false;
            } else {
                nodeLocker[num] = user;
                return true;
            }
        }

        public boolean unlock(int num, int user) {
            if (num < 0 || num >= n || nodeLocker[num] != user) {
                return false;
            } else {
                nodeLocker[num] = -1;
                return true;
            }
        }

        //指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁
        public boolean upgrade(int num, int user) {
            if (num < 0 || num >= n) {
                return false;
            }
            //指定节点当前状态为未上锁。
            if (nodeLocker[num] == -1) {
                //指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。这个只要是有一个上锁就可以马上解锁，因为满足条件
                //指定节点没有任何上锁的祖先节点。
                if (checkLockedAncestor(num) && checkLockedChildren(num)) {
                    nodeLocker[num] = user;
                    return true;
                }
            }
            return false;
        }

        //检查节点没有任何上锁的祖先节点。
        private boolean checkLockedAncestor(int num) {
            int ancestor = parent[num];
            while (ancestor != -1) {
                if (nodeLocker[ancestor] != -1) {
                    return false;
                }
                ancestor = parent[ancestor];
            }
            return true;
        }

        //指定节点至少有一个上锁状态的子孙节点
        private boolean checkLockedChildren(int num) {
            boolean ans = false;
            for (int child : children[num]) {
                if (nodeLocker[child] != -1) {
                    nodeLocker[child] = -1;
                    ans = true;
                }
                ans |= checkLockedChildren(child);
            }
            return ans;
        }
    }

    //146. LRU 缓存
    //请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    //实现 LRUCache 类：
    //LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    //int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    //void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    //函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    class LRUCache {
        private class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int k, int v) {
                this.key = k;
                this.value = v;
            }
        }

        private final int capacity;
        private final Node dummyNode = new Node(0, 0);
        private final Map<Integer, Node> mapNode = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            dummyNode.next = dummyNode;
            dummyNode.prev = dummyNode;
        }

        //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
        public int get(int key) {
            Node node = getNode(key);
            return node == null ? -1 : node.value;
        }

        //如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
        // 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
        public void put(int key, int value) {
            Node node = getNode(key);
            if (node != null) {//key已存在
                node.value = value;
                return;
            }
            //不存在，插入新的kv组
            node = new Node(key, value);
            //加入到链表和map
            pushFront(node);
            mapNode.put(key, node);
            //capacity溢出检查和处理
            if (mapNode.size() > capacity) {
                mapNode.remove(dummyNode.prev.key);
                remove(dummyNode.prev);//逐出 最久未使用的关键字。
            }
        }

        private Node getNode(int key) {
            if (!mapNode.containsKey(key)) {
                //没有该key
                return null;
            }
            //有该key，同时更新使用频率
            Node node = mapNode.get(key);
            //把该key拿出放到链表开头，表示是最新使用的key
            remove(node);
            pushFront(node);
            return node;
        }

        //删除该key
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        //在链表头添加一个节点（表示是最新使用的key）
        private void pushFront(Node node) {
            node.prev = dummyNode;
            node.next = dummyNode.next;
            //        dummyNode.next.prev = node;
            //        dummyNode.next = node;
            node.prev.next = node;
            node.next.prev = node;
        }
    }

    //460. LFU 缓存
    //请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
    //
    //实现 LFUCache 类：
    //
    //LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
    //int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
    //void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
    //为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
    //
    //当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
    //
    //函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    class LFUCache {
        private class Node {
            int key;
            int value;
            int freq = 1;//使用次数
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> keyNodeMap = new HashMap<>();//通过key找Node
        private final Map<Integer, Node> freqNodeMap = new HashMap<>();//通过使用次数归类Node，通过次数存放链表
        private int minFreq = 1;

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        //get会影响使用次数
        public int get(int key) {
            Node node = getNode(key);
            return node == null ? -1 : node.value;
        }

        //如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，
        // 则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，
        // 应该去除 最久未使用 的键。
        //为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
        //当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，
        // 使用计数器的值将会递增。
        public void put(int key, int value) {
            Node node = getNode(key);
            if (node != null) {
                //如果键 key 已存在，则变更其值
                node.value = value;
            } else {
                //如果键不存在，请插入键值对。
                node = new Node(key, value);
                //判断是否满capacity
                if (keyNodeMap.size() == capacity) {
                    //则应该在插入新项之前，移除最不经常使用的项。
                    Node dummyHead = freqNodeMap.get(minFreq);
                    Node removeNode = dummyHead.prev;
                    remove(removeNode);
                    keyNodeMap.remove(removeNode.key);
                    //处理当前使用次数的链表
                    if (dummyHead.next == dummyHead) {
                        //说明该使用次数的链表是空
                        freqNodeMap.remove(minFreq);
                    }
                }
                //更新keyNodeMap，freqNodeMap，minFreq
                keyNodeMap.put(key, node);
                pushAhead(node, 1);
                minFreq = 1;
            }
        }

        private Node getNode(int key) {
            if (!keyNodeMap.containsKey(key)) {
                //不存在key
                return null;
            }
            //存在key
            Node node = keyNodeMap.get(key);
            //刷新使用次数（计数器），删除该次数链表的key，key放到次数+1的链表
            remove(node);
            Node dummyHead = freqNodeMap.get(node.freq);
            if (dummyHead.next == dummyHead) {
                //说明该使用次数的链表是空
                freqNodeMap.remove(node.freq);
                //更新minFreq
                if (node.freq == minFreq) {
                    minFreq++;
                }
            }
            //key放到次数+1的链表
            node.freq++;
            pushAhead(node, node.freq);
            return node;
        }

        //删除节点
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 在链表头添加一个节点
        private void pushAhead(Node node, int freq) {
            Node dummyNode = freqNodeMap.getOrDefault(freq, newLinkedNode());
            freqNodeMap.put(freq, dummyNode);
            node.next = dummyNode.next;
            node.prev = dummyNode;
            dummyNode.next.prev = node;
            dummyNode.next = node;

        }

        //新建一个双向链表
        private Node newLinkedNode() {
            Node dummy = new Node(0, 0);//哨兵节点
            dummy.next = dummy;
            dummy.prev = dummy;
            return dummy;
        }
    }

    //2582. 递枕头
    public int passThePillow(int n, int time) {
        int remain = time % (n - 1);
        int reverse = time / (n - 1);
        return reverse % 2 == 0 ? 1 + remain : n - remain;
    }

    //1333. 餐厅过滤器
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ans = Arrays.stream(restaurants).filter(r -> (r[3] <= maxPrice && r[4] <= maxDistance && !(veganFriendly == 1 && r[2] == 0))).sorted((a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        }).map(r -> r[0]).collect(Collectors.toList());
        return ans;
    }

    //2251. 花期内花的数目
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 0; i < people.length; i++) {
            //在 starts中二分查找大于 people[i] 的下一个数的下标，就是小于等于people[i]花的数目
            //在 ends 中二分查找大于等于 people[i] 的下一个数的下标（若不存在则为 n）
            people[i] = lowerBoundFlowers(starts, people[i] + 1) - lowerBoundFlowers(ends, people[i]);
        }
        return people;
    }

    // 返回 >= x 的第一个数的下标
    // 如果不存在（所有元素都小于 x），则返回 nums.length
    private int lowerBoundFlowers(int[] nums, int x) {
        int left = 0;
        int right = nums.length;//左闭右开
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;// 根据循环不变量，此时 right 就是满足 nums[right] >= x 的最小值
    }

    //605. 种花问题
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; ) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }

    //2136. 全部开花的最早一天
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        //播种总时间是固定的，考虑的是播种后生长时间，这样安排生长时间最短的在最后就可以了
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> growTime[b] - growTime[a]);
        int ans = 0;
        int curDays = 0;
        for (int i = 0; i < idx.length; i++) {
            //累计播种时间
            curDays += plantTime[idx[i]];
            //最后再统计生长天数，生长天数可重叠，所以按照最长的计算
            ans = Math.max(ans, curDays + growTime[idx[i]]);
        }
        return ans;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        //只能买一次股票
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            //持有
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            //不持有
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[n - 1][1];
    }

    //122. 买卖股票的最佳时机 II
    public int maxProfit2(int[] prices) {
        //可以买多次股票
        //动态规划
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            //持有，要保存之前买卖的结果，需要从之前不持有的状态继承
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            //不持有
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[n - 1][1];
        //贪心
        //        int ans = 0;
        //        for (int i = 1; i < prices.length; i++) {
        //            if (prices[i] > prices[i - 1]) {
        //                ans += prices[i] - prices[i - 1];
        //            }
        //        }
        //        return ans;
    }

    //123. 买卖股票的最佳时机 III
    public int maxProfit3(int[] prices) {
        //动态规划
        //只能买卖两次
        //空间优化版
        int[] dp = new int[4];
        dp[0] = -prices[0];
        dp[2] = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            //顺序重要
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            dp[2] = Math.max(dp[2], dp[1] - prices[i]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i]);
        }
        return dp[3];
        //没有优化
        //        int n = prices.length;
        //        int[][] dp = new int[n][4];
        //        //0持有1不持有2二次持有3二次不持有
        //        dp[0][0] = -prices[0];
        //        dp[0][2] = -prices[0];
        //        for (int i = 1; i < n; i++) {
        //            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
        //            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        //            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
        //            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        //        }
        //        return dp[n-1][3];
    }

    //188. 买卖股票的最佳时机 IV
    public int maxProfit4(int k, int[] prices) {
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i < k + 1; i++) {
            //0持有1不持有
            dp[i][0] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < k + 1; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
            }
        }
        return dp[k][1];
    }

    //309. 买卖股票的最佳时机含冷冻期
    public int maxProfit309(int[] prices) {
        //可多次购买，但是有cd（缓一天）
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][2];
        dp[1][0] = -prices[0];
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }
        return dp[n][1];
        //        int[][] dp = new int[n][2];
        //        //0持有，1不持有
        //        dp[0][0] = -prices[0];
        //        dp[1][0] = Math.max(dp[0][0], -prices[1]);
        //        dp[1][1] = Math.max(dp[0][1], dp[0][0] + prices[1]);
        //        for (int i = 2; i < n; i++) {
        //            //卖出后必须等一天才能买
        //            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
        //            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        //        }
        //        return dp[n - 1][1];
    }

    //901. 股票价格跨度
    class StockSpanner {
        private ArrayDeque<Stock> deque;

        public StockSpanner() {
            deque = new ArrayDeque<Stock>();
        }

        public int next(int price) {
            int day = 1;
            while (!deque.isEmpty()) {
                if (deque.peekLast().price <= price) {
                    Stock pollStock = deque.pollLast();
                    day += pollStock.day;
                } else break;
            }
            deque.offer(new Stock(price, day));
            return day;
        }

        class Stock {
            int price;
            int day;

            public Stock(int price, int day) {
                this.day = day;
                this.price = price;
            }
        }
    }

    //714. 买卖股票的最佳时机含手续费
    public int maxProfit714(int[] prices, int fee) {
        int n = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }

    //2034. 股票价格波动
    class StockPrice {
        private int latest;
        private Map<Integer, Integer> map;
        private TreeMap<Integer, Integer> tm;

        public StockPrice() {
            map = new HashMap<>();
            tm = new TreeMap<>();

        }

        //在时间点 timestamp 更新股票价格为 price
        public void update(int timestamp, int price) {
            latest = Math.max(timestamp, latest);
            if (map.containsKey(timestamp)) {
                int old = map.get(timestamp);
                int cnt = tm.get(old);
                if (cnt == 1) {
                    tm.remove(old);
                } else {
                    tm.put(old, cnt - 1);
                }
            }
            map.put(timestamp, price);
            tm.put(price, tm.getOrDefault(price, 0) + 1);
        }

        public int current() {
            //返回股票 最新价格
            return map.get(latest);
        }

        public int maximum() {
            //返回股票 最高价格
            return tm.lastKey();
        }

        public int minimum() {
            //返回股票 最低价格
            return tm.firstKey();
        }
    }

    //2578. 最小和分割
    public int splitNum(int num) {
        String str = String.valueOf(num);
        char[] nums = str.toCharArray();
        Arrays.sort(nums);
        int sum = 0;
        int rate = 1;
        for (int i = nums.length - 1; i >= 0; i += -2) {
            sum += (nums[i] - '0') * rate;
            if (i - 1 >= 0) {
                sum += (nums[i - 1] - '0') * rate;
            }
            rate *= 10;
        }
        return sum;
    }

    //2731. 移动机器人
    public int sumDistance(int[] nums, String s, int d) {
        final long MOD = (long) 1e9 + 7;
        int n = nums.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = (long) nums[i] + (s.charAt(i) == 'R' ? 1 : -1) * d;
        }
        Arrays.sort(res);
        long ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + res[i] * i - sum) % MOD;
            sum += res[i];
        }
        return (int) ans;
    }

    //2512. 奖励最顶尖的 K 名学生
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : positive_feedback) {
            map.put(str, 3);
        }
        for (String str : negative_feedback) {
            map.put(str, -1);
        }
        int len = report.length;
        int[][] ans = new int[len][2];
        for (int i = 0; i < len; i++) {
            String[] ss = report[i].split(" ");
            ans[i][0] = student_id[i];
            for (String word : ss) {
                ans[i][1] += map.getOrDefault(word, 0);
            }
        }
        Arrays.sort(ans, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } else return b[1] - a[1];
        });
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < ans.length && k > 0; i++, k--) {
            res.add(ans[i][0]);
        }
        return res;
    }

    //2562. 找出数组的串联值
    public long findTheArrayConcVal(int[] nums) {
        int ans = 0;
        int i = 0, j = nums.length - 1;
        for (; i < j; i++, j--) {
            ans += Integer.parseInt(nums[i] + "" + nums[j]);
        }
        if (i == j) {
            ans += nums[i];
        }
        return ans;
    }

    //137. 只出现一次的数字 II
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    //1488. 避免洪水泛滥
    public int[] avoidFlood(int[] rains) {
        int len = rains.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (rains[i] == 0) {
                set.add(i);
            } else {
                ans[i] = -1;
                if (map.containsKey(rains[i])) {
                    Integer cl = set.ceiling(map.get(rains[i]));
                    if (cl == null) {
                        return new int[0];
                    }
                    ans[cl] = rains[i];
                    set.remove(cl);
                }
                map.put(rains[i], i);
            }
        }
        return ans;
    }

    //260. 只出现一次的数字 III
    public int[] singleNumber3(int[] nums) {
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], 1);
            }
        }
        int i = 0;
        for (Integer x : map.keySet()) {
            ans[i++] = x;
        }
        return ans;
    }

    //2652. 倍数求和
    public int sumOfMultiples(int n) {
        return sumOfMultiplesCal(3, n) + sumOfMultiplesCal(5, n) + sumOfMultiplesCal(7, n)
                - sumOfMultiplesCal(3 * 5, n) - sumOfMultiplesCal(3 * 7, n) - sumOfMultiplesCal(5 * 7, n)
                + sumOfMultiplesCal(3 * 5 * 7, n);
    }

    private int sumOfMultiplesCal(int x, int n) {
        int m = n / x;
        return (x + m * x) * m / 2;
    }

    //2530. 执行 K 次操作后的最大分数
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int x : nums) {
            queue.offer(x);
        }
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int cur = queue.poll();
            sum += cur;
            queue.offer((cur + 2) / 3);
        }
        return sum;
    }

    //1726. 同积元组
    public int tupleSameProduct(int[] nums) {
        //a*b=c*d，如果存在，那么
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int cur = nums[i] * nums[j];
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }
        int ans = 0;
        for (int key : map.keySet()) {
            int val = map.get(key);
            if (val >= 2) {
                ans += val * (val - 1) * 4;
            }
        }
        return ans;
    }

    //2525. 根据规则将箱子分类
    public String categorizeBox(int length, int width, int height, int mass) {
        long v = (long) length * width * height;
        boolean isBulky = false;
        boolean isHeavy = mass >= 100;
        if (length >= 10000 || width >= 10000 || height >= 10000 || v >= 1e9) {
            isBulky = true;
        }
        if (isBulky) {
            if (isHeavy) {
                return "Both";
            } else {
                return "Bulky";
            }
        } else {
            if (isHeavy) {
                return "Heavy";
            } else {
                return "Neither";
            }
        }
    }

    //1402. 做菜顺序
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0;
        int sum = 0;
        int len = satisfaction.length;
        for (int i = len - 1; i >= 0; i--) {
            int cur = satisfaction[i];
            if (sum < -cur) {
                break;
            }
            sum += cur;
            ans += sum;
        }
        return ans;
    }

    //2678. 老人的数目
    public int countSeniors(String[] details) {
        int sum = 0;
        for (int i = 0; i < details.length; i++) {
            sum += Integer.parseInt(details[i].substring(11, 13)) > 60 ? 1 : 0;
        }
        return sum;
    }

    //1155. 掷骰子等于目标和的方法数
    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;//不能构成
        }
        //可以分割成多个子问题，所以可以用动态规划
        int MOD = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                for (int z = 1; z <= k && z <= j; z++) {
                    if (j - z >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - z]) % MOD;
                    }
                }
            }
        }
        return dp[n][target];
    }

    class punishmentNumber {
        int[] nums = new int[1000];
        int MAX = 0;

        //2698. 求一个整数的惩罚数
        public int punishmentNumber(int n) {
            int ans = 0;
            for (int i = 1; i <= MAX && i <= n; i++) {
                ans += nums[i];
            }

            for (int i = MAX + 1; i <= n; i++) {
                int cur = i * i;
                if (checkPunishmentNumber(i, 0, cur)) {
                    nums[i] = cur;
                    ans += nums[i];
                }
            }
            MAX = Math.max(MAX, n);
            return ans;
        }

        private boolean checkPunishmentNumber(int n, int sum, int num) {
            if (sum + num == n) {
                return true;
            }
            for (int i = 10; i <= 1000 && num >= i; i *= 10) {
                if (checkPunishmentNumber(n, sum + num % i, num / i)) {
                    return true;
                }
            }
            return false;
        }
    }

    boolean check(int num, int n) {
        System.out.println(num + ":" + n);
        if (num == n) return true;
        int i = 10;
        while (num >= i && num % i <= n) {
            if (check(num / i, n - (num % i))) return true;
            i *= 10;
        }
        return false;
    }

    //2520. 统计能整除数字的位数
    public int countDigits(int num) {
        int ans = 0;
        int[] cnt = new int[10];
        int n = num;
        while (n > 0) {
            int cur = n % 10;
            if (num % (cur) == 0 && cnt[cur] == 0) {
                cnt[cur]++;
                ans++;
            }
            n /= 10;
        }
        return ans;
    }

    //1465. 切割后面积最大的蛋糕
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int MOD = 1_000_000_000 + 7;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long maxH = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        long maxW = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxH = Math.max(maxH, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            maxW = Math.max(maxW, verticalCuts[i] - verticalCuts[i - 1]);
        }
        System.out.println(maxH + ":" + maxW);
        return (int) (maxH * maxW) % MOD;
    }

    //2558. 从数量最多的堆取走礼物
    public long pickGifts(int[] gifts, int k) {
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int gift : gifts) {
            pq.offer(gift);
        }
        while (k > 0) {
            k--;
            int maxGift = pq.poll();
            pq.offer((int) Math.sqrt(maxGift));
        }
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }

    //274. H 指数
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= ans) {
                return ans;
            }
            ans--;
        }
        return 0;
    }

    //275. H 指数 II
    public int hIndex2(int[] citations) {
        //二分，左闭右开，区间代表h指数
        int left = 1;
        int right = citations.length + 1;
        while (left < right) {//区间不为空
            int mid = left + right >> 1;
            int num = citations.length - mid;//下标
            if (citations[num] >= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    //2003. 每棵子树内缺失的最小基因值
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                node = i;
                break;
            }
        }
        //如果没有1的基因值
        if (node == -1) {
            return ans;
        }
        //建树,parents = [-1,0,0,2], nums = [1,2,3,4]
        List<Integer>[] tree = new ArrayList[n];
        Arrays.setAll(tree, t -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            tree[parents[i]].add(i);
        }
        //开始从节点的基因值为1开始向上遍历，直到根节点
        Set<Integer> numsSet = new HashSet<>();
        int missingVal = 2;
        while (node >= 0) {
            smallestMissingValueSubtreeDFS(numsSet, node, nums, tree);
            while (numsSet.contains(missingVal)) {
                missingVal++;
            }
            ans[node] = missingVal;
            node = parents[node];//往上遍历
        }
        return ans;
    }

    private void smallestMissingValueSubtreeDFS(Set<Integer> numsSet, int node, int[] nums, List<Integer>[] tree) {
        //遍历该节点下子树
        numsSet.add(nums[node]);//标记基因
        for (int son : tree[node]) {
            if (!numsSet.contains(nums[son])) {
                smallestMissingValueSubtreeDFS(numsSet, son, nums, tree);
            }
        }
    }

    //2103. 环和杆
    public int countPoints(String rings) {
        int[][] cnt = new int[10][3];
        int len = rings.length();
        for (int i = 0; i < len; i += 2) {
            char color = rings.charAt(i);
            int coin = rings.charAt(i + 1) - '0';
            if (color == 'R') {
                cnt[coin][0]++;
            } else if (color == 'G') {
                cnt[coin][1]++;
            } else {
                cnt[coin][2]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < cnt.length; i++) {
            int[] cur = cnt[i];
            if (cur[0] > 0 && cur[1] > 0 && cur[2] > 0) {
                ans++;
            }
        }
        return ans;
    }

    class connect {
        //117. 填充每个节点的下一个右侧节点指针 II
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            ArrayDeque<Node> deque = new ArrayDeque<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                Node pre = deque.peekFirst();
                while (size-- > 0) {
                    Node cur = deque.pollFirst();
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                    pre.next = cur;
                    pre = cur;
                    pre.next = null;
                }
            }
            return root;
        }

        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }
    }

    //318. 最大单词长度乘积
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] masks = new int[len];
        for (int i = 0; i < len; i++) {
            int t = 0;
            for (int j = 0; j < words[i].length(); j++) {
                int dit = words[i].charAt(j) - 'a';
                t |= (1 << dit);
            }
            masks[i] = t;
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    //2586. 统计范围内的元音字符串数
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            char start = words[i].charAt(0);
            char end = words[i].charAt(words[i].length() - 1);
            if ((start == 'a' || start == 'e' || start == 'i' || start == 'o' || start == 'u') &&
                    (end == 'a' || end == 'e' || end == 'i' || end == 'o' || end == 'u')) {
                ans++;
            }
        }
        return ans;
    }

    //2609. 最长平衡子字符串
    public int findTheLongestBalancedSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            int zeroCnt = 0;
            while (i < s.length() && s.charAt(i) == '0') {
                i++;
                zeroCnt++;
            }
            int temp = zeroCnt;
            while (i < s.length() && s.charAt(i) == '1') {
                i++;
                if (zeroCnt > 0) {
                    zeroCnt--;
                }
            }
            ans = Math.max(ans, temp - zeroCnt);
        }
        return ans * 2;
    }

    //2300. 咒语和药水的成功对数  排序+二分，时间复杂度mlogm+nLogm
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];
        int m = potions.length;
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int left = 0;
            int right = m;
            //二分 左闭右开
            while (left < right) {
                int mid = left + right >> 1;
                if ((long) potions[mid] * spells[i] < success) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            ans[i] = m - right;
        }
        return ans;
    }

    //187. 重复的DNA序列
    public List<String> findRepeatedDnaSequences(String s) {
        //滑动窗口+hash
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i + 10 <= len; i++) {
            String sub = s.substring(i, i + 10);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (map.get(sub) == 2) {
                list.add(sub);
            }
        }
        return list;
    }

    //421. 数组中两个数的最大异或值
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int highBit = 31 - Integer.numberOfLeadingZeros(max);
        int ans = 0;
        int mask = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = highBit; i >= 0; i--) {
            set.clear();
            mask |= 1 << i;//只关注当前的位
            int newAns = ans | (1 << i);//假设这为新的答案
            for (int x : nums) {//查找有没有匹配新的答案
                x &= mask;
                if (set.contains(x ^ newAns)) {
                    ans = newAns;
                    break;
                }
                set.add(x);
            }
        }
        return ans;
    }

    //765. 情侣牵手
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] cache = new int[n];
        for (int i = 0; i < n; i++) {
            //保存第i个人所在的下标
            cache[row[i]] = i;
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i += 2) {
            int a = row[i];
            int b = a ^ 1;
            if (row[i + 1] != b) {
                //情侣不匹配，进行交换
                int bIndex = cache[b];
                int target = i + 1;
                cache[row[bIndex]] = target;
                cache[row[target]] = bIndex;
                minSwapsCouplesSwap(row, bIndex, target);
                ans++;
            }
        }
        return ans;
    }

    private void minSwapsCouplesSwap(int[] row, int a, int b) {
        int t = row[a];
        row[a] = row[b];
        row[b] = t;
    }

    /**
     * @Description: 线段树（动态开点）
     * @Date 2023年11月22日
     * 给出的模版基于求「区间和」以及对区间进行「加减」的更新操作，且为「动态开点」
     **/
    class SegmentTreeLearn {
        class Node {
            // 左右孩子节点
            Node left, right;
            // 当前节点值
            int val;
            //懒惰标记
            int add;
        }

        int[] arr;

        //线段树的建立，如果一开始就给出整个数组
        private void buildTree(Node node, int start, int end) {
            //到达叶子节点
            if (start == end) {
                node.val = arr[start];
                return;
            }
            int mid = (start + end) >> 1;
            buildTree(node.left, start, mid);
            buildTree(node.right, mid, end);
            //向上更新
            pushUp(node);
        }

        private int N = (int) 1e9;
        private Node root = new Node();

        //向上更新
        private void pushUp(Node node) {
            node.val = node.left.val + node.right.val;
        }

        // 下推懒惰标记方法
        // leftNum 和 rightNum 表示左右孩子区间的叶子节点数量
        // 因为如果是「加减」更新操作的话，需要用懒惰标记的值 * 叶子节点的数量
        private void pushDown(Node node, int leftNum, int rightNum) {
            //动态开点
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            //如果add 为 0 ，表示没有标记
            if (node.add == 0) {
                return;
            }
            //注意：当前节点加上标记值 * 该子树所有叶子节点的数量
            node.left.val += node.add * leftNum;
            node.right.val += node.add * rightNum;
            //把标记下推给孩子节点
            //对区间进行[加减]的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
            node.left.add += node.add;
            node.right.add += node.add;
            //取消当前节点标记
            node.add = 0;
        }

        // 更新方法
        // 在区间 [start, end] 中更新区间 [l, r] 的值，将区间 [l, r] + val
        // 对于上面的例子，应该这样调用该函数：update(root, 0, 4, 2, 4, 1)
        public void update(Node node, int start, int end, int l, int r, int val) {
            //找到满足要求的区间
            if (l <= start && end <= r) {
                //区间节点加上更新值
                //注意：需要 *该子树所有叶子节点
                node.val += (end - start + 1) * val;
                //添加懒惰标记
                //对区间进行[加减]的更新操作，懒惰标记需要累加，不能直接覆盖
                node.add += val;
                return;
            }
            int mid = (start + end) >> 1;
            //下推标记
            // mid - start + 1：表示左孩子区间叶子节点数量
            // end - mid：表示右孩子区间叶子节点数量
            pushDown(node, mid - start + 1, end - mid);
            // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }
            if (r > mid) {
                update(node.right, mid + 1, end, l, r, val);
            }
            //向上更新
            pushUp(node);
        }

        // 在区间 [start, end] 中查询区间 [l, r] 的结果，即 [l ,r] 保持不变
        // 对于上面的例子，应该这样调用该函数：query(root, 0, 4, 2, 4)
        public int query(Node node, int start, int end, int l, int r) {
            // 区间 [l ,r] 完全包含区间 [start, end]
            // 例如：[2, 4] = [2, 2] + [3, 4]，当 [start, end] = [2, 2] 或者 [start, end] = [3, 4]，直接返回
            if (l <= start && end <= r) {
                return node.val;
            }
            // 把当前区间 [start, end] 均分得到左右孩子的区间范围
            // node 左孩子区间 [start, mid]
            // node 左孩子区间 [mid + 1, end]
            int mid = (start + end) >> 1;
            int ans = 0;
            // 下推标记
            pushDown(node, mid - start + 1, end - mid);
            // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
            if (l <= mid) {
                ans += query(node.left, start, mid, l, r);
            }
            // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
            if (r > mid) {
                ans += query(node.right, mid + 1, end, l, r);
            }
            // ans 把左右子树的结果都累加起来了，与树的后续遍历同理
            return ans;
        }
    }

    /**
     * 729. 我的日程安排表 I
     * 线段树
     */
    class MyCalendar {

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            //查询该区间是否结果为0
            if (query(root, 0, N, start, end - 1) != 0) {
                return false;
            }
            //更新
            update(root, 0, N, start, end - 1, 1);
            return true;
        }

        class Node {
            Node left, right;
            int val, add;
        }

        private int N = (int) 1e9;
        private Node root = new Node();

        public void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                node.val += val;
                //添加懒惰标记
                node.add += val;
                return;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            //如果存在交集则继续向下遍历孩子区间
            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }
            if (r > mid) {
                update(node.right, mid + 1, end, l, r, val);
            }
            pushUp(node);
        }

        private int query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return node.val;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            int ans = 0;
            if (l <= mid) {
                ans = query(node.left, start, mid, l, r);
            }
            if (mid < r) {
                ans += query(node.right, mid + 1, end, l, r);
            }
            // if (l <= mid) ans = query(node.left, start, mid, l, r);
            // if (r > mid) ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
            return ans;
        }

        private void pushUp(Node node) {
            // 每个节点存的是当前区间的最大值
            node.val = node.left.val + node.right.val;
            // node.val = Math.max(node.left.val, node.right.val);
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.add == 0) {
                return;
            }
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }
    }

    /**
     * 731. 我的日程安排表 II
     * 线段树
     */
    class MyCalendarTwo {

        public MyCalendarTwo() {

        }

        public boolean book(int start, int end) {
            if (query(root, 0, N, start, end - 1) >= 2) {
                return false;
            }
            update(root, 0, N, start, end - 1, 1);
            return true;
        }

        class Node {
            Node left, right;
            int val, add;
        }

        int N = (int) 1e9;
        private Node root = new Node();

        private void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                node.val += val;
                node.add += val;
                return;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }
            if (mid <= r) {
                update(node.right, mid + 1, end, l, r, val);
            }
            pushUp(node);
        }

        private int query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return node.val;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            int ans = 0;
            if (l <= mid) {
                ans = query(node.left, start, mid, l, r);
            }
            if (mid <= r) {
                ans += query(node.right, mid + 1, end, l, r);
            }
            return ans;
        }

        private void pushUp(Node node) {
            node.val = node.left.val + node.right.val;
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            if (node.add == 0) {
                return;
            }
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }
    }

    //307. 区域和检索 - 数组可修改
    class NumArray {
        private int[] nums;
        private int[] tree;

        public NumArray(int[] nums) {
            int n = nums.length;// 使 update 中算出的 delta = nums[i]
            this.nums = new int[n];
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        private int prefixSum(int i) {
            int s = 0;
            for (; i > 0; i &= i - 1) {// i -= i & -i 的另一种写法
                s += tree[i];
            }
            return s;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }
    }

    //树状数组模板
    class NumArray2 {

        int[] tree;
        int[] nums;

        int lowbit(int x) {
            return x & -x;
        }

        //查询前缀和的方法
        int query(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ans += tree[i];
            }
            return ans;
        }

        //在树状数组 x 位置中增加值 u
        void add(int x, int u) {
            for (int i = x; i <= nums.length; i += lowbit(i)) {
                tree[i] += u;
            }
        }

        public NumArray2(int[] nums) {
            this.nums = nums;
            tree = new int[nums.length + 1];
            // 初始化「树状数组」，要默认数组是从 1 开始
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int i, int val) {
            // 原有的值是 nums[i]，要使得修改为 val，需要增加 val - nums[i]
            add(i + 1, val - nums[i]);
            nums[i] = val;
        }

        public int sumRange(int l, int r) {
            return query(r + 1) - query(l);
        }
    }

    //2656. K 个元素的最大和
    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        return max * k + (k - 1) * k / 2;
    }

    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //2342. 数位和相等数对的最大和
    public int maximumSum(int[] nums) {
        int ans = -1;
        int[] map = new int[100];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int sum = 0;
            while (cur > 0) {
                sum += cur % 10;
                cur /= 10;
            }
            if (map[sum] != 0) {
                ans = Math.max(ans, map[sum] + nums[i]);
            }
            map[sum] = Math.max(map[sum], nums[i]);
        }
        return ans;
    }

    //2760. 最长奇偶子数组
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int right = 0;
        int ans = 0;
        for (int left = 0; left < nums.length; left++) {
            if (nums[left] <= threshold && nums[left] % 2 == 0) {
                right = left + 1;
                while (right < nums.length && nums[right] <= threshold && nums[right] % 2 != nums[right - 1] % 2) {
                    right++;
                }
                ans = Math.max(ans, right - left);
                left = right + 1;
            }
        }
        return ans;
    }

    //2216. 美化数组的最少删除数
    public int minDeletion(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i - cnt) % 2 == 0 && i + 1 < nums.length && nums[i] == nums[i + 1]) {
                cnt++;
            }
        }
        //nums.length 为偶数
        return (nums.length - cnt) % 2 == 0 ? cnt + 1 : cnt;
    }

    //1410. HTML 实体解析器
    public String entityParser(String text) {
        Map<String, String> map = new HashMap() {{
            put("&quot;", "\"");
            put("&apos;", "\'");
            put("&gt;", ">");
            put("&lt;", "<");
            put("&frasl;", "/");
            put("&amp;", "&");
        }};
        int len = text.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == '&') {
                int end = i + 1;
                while (end < len && text.charAt(end) != ';') {
                    end++;
                }
                String sub = text.substring(i, Math.min(end + 1, len));
                if (map.containsKey(sub)) {
                    sb.append(map.get(sub));
                    i = end;
                    continue;
                }
            }
            sb.append(text.charAt(i));
        }
        return sb.toString();
        //        text = text.replaceAll("&quot;","\"");
        //        text = text.replaceAll("&apos;","\'");
        //        text = text.replaceAll("&gt;",">");
        //        text = text.replaceAll("&lt;","<");
        //        text = text.replaceAll("&frasl;","/");
        //        text = text.replaceAll("&amp;","&");
        //        return text;
    }

    //2824. 统计和小于目标的下标对数目
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int n = nums.size();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums.get(i) + nums.get(mid) < target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (nums.get(i) + nums.get(r) < target) {
                sum += r - i + 1;
            }
        }
        return sum;
        //        for (int i = 0; i < n; i++) {
        //            for (int j = i+1; j < n; j++) {
        //                if (nums.get(i)+nums.get(j)<target){
        //                    sum++;
        //                }
        //            }
        //        }
        //        return sum;
    }

    //907. 子数组的最小值之和
    public int sumSubarrayMins(int[] arr) {
        int len = arr.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.push(-1);
        for (int i = 0; i < len; i++) {
            //单调栈进去
            while (deque.size() > 1 && arr[deque.peek()] >= arr[i]) {
                right[deque.pop()] = i;
            }
            left[i] = deque.peek();
            deque.push(i);
        }
        long MOD = (long) 1e9 + 7;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += (long) arr[i] * (i - left[i]) * (right[i] - i);
        }
        return (int) (ans % MOD);
    }

    //1670. 设计前中后队列
    class FrontMiddleBackQueue {
        ArrayDeque<Integer> left = new ArrayDeque<>();
        ArrayDeque<Integer> right = new ArrayDeque<>();

        //保证right.size() - left.size() <= 1
        public FrontMiddleBackQueue() {

        }

        //将 val 添加到队列的 最前面 。
        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        //将 val 添加到队列的 正中间 。
        public void pushMiddle(int val) {
            if (left.size() == right.size()) {
                right.addFirst(val);
            } else {
                left.addLast(val);
            }
        }

        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        public int popFront() {
            if (right.isEmpty()) {
                return -1;
            }
            int pop = left.isEmpty() ? right.pollFirst() : left.pollFirst();
            balance();
            return pop;
        }

        public int popMiddle() {
            if (right.isEmpty()) {
                return -1;
            }
            if (left.size() == right.size()) {
                return left.pollLast();
            }
            return right.pollFirst();
        }

        public int popBack() {
            if (right.isEmpty()) {
                return -1;
            }
            int pop = right.pollLast();
            balance();
            return pop;
        }

        private void balance() {
            if (left.size() > right.size()) {
                right.addFirst(left.pollLast());
            } else if (left.size() < right.size() - 1) {
                left.addLast(right.pollFirst());
            }
        }
    }

    //732. 我的日程安排表 III  线段树
    class MyCalendarThree {

        public MyCalendarThree() {

        }

        public int book(int startTime, int endTime) {
            update(root, 0, N, startTime, endTime - 1, 1);
            return root.val;
        }

        class Node {
            Node left, right;
            int val, add;
        }

        private int N = (int) 1e9;
        private Node root = new Node();

        private void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                node.val += val;
                node.add += val;
                return;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            if (l <= mid) {
                update(node.left, start, mid, l, r, val);
            }
            if (mid < r) {
                update(node.right, mid + 1, end, l, r, val);
            }
            pushUp(node);
        }

        private int query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return node.val;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            int ans = 0;
            if (l <= mid) {
                ans = query(node.left, start, mid, l, r);
            }
            if (mid < r) {
                ans = Math.max(query(node.right, mid + 1, end, l, r), ans);
            }
            return ans;
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }

        private void pushUp(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }
    }

    //2336. 无限集中的最小数字
    class SmallestInfiniteSet {
        int idx = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        boolean[] exist = new boolean[1010];

        public SmallestInfiniteSet() {
            Arrays.fill(exist, true);
        }

        public int popSmallest() {
            int ans = 0;
            if (!pq.isEmpty()) {
                ans = pq.poll();
            } else {
                ans = idx;
                idx++;
            }
            exist[ans] = false;
            return ans;
        }

        public void addBack(int num) {
            if (!exist[num]) {
                pq.add(num);
                exist[num] = true;
            }
        }
    }

    //2661. 找出叠涂元素
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, int[]> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }
        int[] mCnt = new int[m];
        int[] nCnt = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int[] cur = map.get(arr[i]);
            if (++mCnt[cur[0]] == n || ++nCnt[cur[1]] == m) {
                return i;
            }
        }
        return -1;
    }

    //1094. 拼车
    public boolean carPooling(int[][] trips, int capacity) {
        int[] travel = new int[1010];
        int curNums = 0;
        for (int i = 0; i < trips.length; i++) {
            int[] stop = trips[i];
            travel[stop[1]] += stop[0];
            travel[stop[2]] -= stop[0];
        }
        for (int i = 0; i < 1001; i++) {
            curNums += travel[i];
            if (curNums > capacity) {
                return false;
            }
        }
        return true;
    }

    //1423. 可获得的最大点数
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int size = len - k;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += cardPoints[i];
        }
        int min = sum;
        int total = sum;
        for (int i = size; i < len; i++) {
            sum += cardPoints[i];
            total += cardPoints[i] - cardPoints[i - size];
            min = Math.min(min, total);
        }
        return sum - min;
    }

    //1457. 二叉树中的伪回文路径  能形成回文的只有出现的数字都是偶数次数，或者只有1个奇数次数
    class pseudoPalindromicPaths {
        public int pseudoPalindromicPaths(TreeNode root) {
            int[] p = new int[10];
            return dfs(root, p);
        }

        private int dfs(TreeNode node, int[] p) {
            if (node == null) {
                return 0;
            }
            int ans = 0;
            p[node.val] ^= 1;//改变奇偶
            if (node.left == node.right) {//叶子
                ans = count(p) <= 1 ? 1 : 0;
            } else {
                ans = dfs(node.right, p) + dfs(node.left, p);
            }
            p[node.val] ^= 1;
            return ans;
        }

        private int count(int[] p) {
            int ans = 0;
            for (int x : p) {
                ans += x;
            }
            return ans;
        }
    }

    //1038. 从二叉搜索树到更大和树
    class bstToGst {
        int sum = 0;

        public TreeNode bstToGst(TreeNode root) {
            dfs(root);
            return root;
        }

        public void dfs(TreeNode root) {
            //后序
            if (root == null) {
                return;
            }
            dfs(root.right);
            sum += root.val;
            root.val = sum;
            dfs(root.left);
        }
    }

    //1657. 确定两个字符串是否接近
    public boolean closeStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sCnt = new int[26];
        int[] tCnt = new int[26];
        int sMask = 0, tMask = 0;
        for (char c : s.toCharArray()) {
            sMask |= 1 << (c - 'a');
            sCnt[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            tMask |= 1 << (c - 'a');
            tCnt[c - 'a']++;
        }
        Arrays.sort(tCnt);
        Arrays.sort(sCnt);
        return sMask == tMask && Arrays.equals(tCnt, sCnt);
    }

    class minimumFuelCost {
        public long minimumFuelCost(int[][] roads, int seats) {
            //双向图
            //建图
            int len = roads.length + 1;
            List<Integer>[] grep = new ArrayList[len];
            Arrays.setAll(grep, e -> new ArrayList());
            for (int[] r : roads) {
                int x = r[0];
                int y = r[1];
                grep[x].add(y);
                grep[y].add(x);
            }
            dfs(0, -1, grep, seats);
            return ans;
        }

        private long ans;

        private int dfs(int x, int from, List<Integer>[] g, int seats) {
            //深搜，经过该节点人数/座位 = 车辆数(向上取整)
            int size = 1;
            for (int y : g[x]) {
                if (y != from) {
                    size += dfs(y, x, g, seats);
                }
            }
            if (x > 0) {// x 不是根节点
                ans += (size + seats - 1) / seats;
            }
            return size;
        }
    }

    //2008. 出租车的最大盈利
    public long maxTaxiEarnings(int n, int[][] rides) {
        //首先排序，对rides
        List<int[]>[] roads = new ArrayList[n + 1];
        for (int[] r : rides) {
            int start = r[0];
            int end = r[1];
            int tip = r[2];
            if (roads[end] == null) {
                roads[end] = new ArrayList<>();
            }
            roads[end].add(new int[]{start, end - start + tip});
        }
        //然后进行动态规划
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (roads[i] != null) {
                for (int[] r : roads[i]) {
                    dp[i] = Math.max(dp[i], dp[r[0]] + r[1]);
                }
            }
        }
        return dp[n];
    }

    //2697. 字典序最小回文串
    public String makeSmallestPalindrome(String s) {
        int len = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            if (cs[i] != cs[len - i - 1]) {
                int l = cs[i] - 'a';
                int r = cs[len - i - 1] - 'a';
                if (l < r) {
                    cs[len - i - 1] = cs[i];
                } else {
                    cs[i] = cs[len - i - 1];
                }
            }
        }
        return new String(cs);
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //2415. 反转二叉树的奇数层
    public TreeNode reverseOddLevels(TreeNode root) {
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                    deque.addLast(cur.right);
                }
                if (depth % 2 == 1) {
                    list.add(cur);
                }
            }
            if (depth % 2 == 1) {
                for (int i = 0, j = size - 1; i < j; i++, j--) {
                    int temp = list.get(i).val;
                    list.get(i).val = list.get(j).val;
                    list.get(j).val = temp;
                }
            }
            depth++;
        }
        return root;
    }

    //746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = cost[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[len];
    }

    //162. 寻找峰值
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    //1901. 寻找峰值 II
    public int[] findPeakGrid(int[][] mat) {
        int left = 0;
        int right = mat.length - 2;
        while (left <= right) {
            int i = left + right >> 1;
            int j = findPeakGridFindMax(mat[i]);
            if (mat[i][j] > mat[i + 1][j]) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        return new int[]{left, findPeakGridFindMax(mat[left])};
    }

    private int findPeakGridFindMax(int[] a) {
        int ans = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[ans] < a[i]) {
                ans = i;
            }
        }
        return ans;
    }

    //2828. 判别首字母缩略词
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        int len = words.size();
        for (int i = 0; i < len; i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    //2866. 美丽塔 II
    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        //要形成山峰，所以要有一个递减和一个递增的数组
        int[] mh = maxHeights.stream().mapToInt(a -> a).toArray();
        int len = mh.length;
        long[] suf = new long[len + 1];//后缀
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(len);//哨兵
        long sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            int cur = mh[i];
            while (stack.size() > 1 && mh[stack.peek()] >= cur) {
                int pop = stack.pop();
                sum -= (long) mh[pop] * (stack.peek() - pop);//撤销之前加到sum当中的
            }
            sum += (long) cur * (stack.peek() - i);//若有撤销，则将stack.peek()-1到i且都是cur加回去；没有加cur
            suf[i] = sum;
            stack.push(i);
        }
        long ans = sum;//若像例子1那样是递减的数组，那么递增的 数组就没有必要了
        stack.clear();
        stack.push(-1);//哨兵
        long pre = 0;
        for (int i = 0; i < len; i++) {
            int cur = mh[i];
            while (stack.size() > 1 && cur <= mh[stack.peek()]) {
                int pop = stack.pop();
                pre -= (long) mh[pop] * (pop - stack.peek());//撤销之前加到pre当中的
            }
            pre += (long) cur * (i - stack.peek());
            ans = Math.max(ans, pre + suf[i + 1]);
            stack.push(i);
        }
        return ans;
    }

    //1276. 不浪费原料的汉堡制作方案
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        //鸡兔同笼
        //giant = (tomato - 2*cheese)/2，king = (4*cheese-tomato)/2
        List<Integer> ans = new ArrayList<>();
        int giant = (tomatoSlices - 2 * cheeseSlices) / 2;
        int king = (4 * cheeseSlices - tomatoSlices) / 2;
        ans.add(giant);
        ans.add(king);
        if (giant > 0 && king > 0 && giant * 4 + king * 2 == tomatoSlices && giant + king == cheeseSlices) {
            return ans;
        } else {
            return new ArrayList<>();
        }
    }

    //1962. 移除石子使总数最小
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int x : piles) {
            pq.add(x);
        }
        for (int i = 0; i < k; i++) {
            int poll = pq.poll();
            //移除 floor(piles[i] / 2) 颗石子
            poll -= poll / 2;
            pq.add(poll);
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }

    //1954. 收集足够苹果的最小花园周长
    public long minimumPerimeter(long neededApples) {
        long n = (long) Math.cbrt(neededApples / 4.0);
        if (2 * n * (n + 1) * (2 * n + 1) < neededApples) {
            n++;
        }
        return 8 * n;
    }

    //2660. 保龄球游戏的获胜者
    public int isWinner(int[] player1, int[] player2) {
        int p1 = 0;
        int p2 = 0;
        int sum = 0;
        int len = player1.length;
        for (int i = 0; i < len; i++) {
            sum += p1 > 0 ? player1[i] * 2 : player1[i];
            sum -= p2 > 0 ? player2[i] * 2 : player2[i];
            p1 = player1[i] == 10 ? 2 : p1 - 1;
            p2 = player2[i] == 10 ? 2 : p2 - 1;
        }
        return (sum >= 0) ? ((sum > 0) ? 1 : 0) : 2;
    }

    //2735. 收集巧克力
    public long minCost(int[] nums, int x) {

        //取每次操作的最小成本，也就是说第一次不旋转，然后看小于旋转一次的成本，然后第一次旋转，看小于旋转两次的成本,
        //最多旋转nums.length次
        int len = nums.length;
        long[] costs = new long[len];
        for (int i = 0; i < len; i++) {
            costs[i] = (long) x * i;
        }
        int step = 0;
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            for (int j = 0; j < len; j++) {
                cur = Math.min(cur, nums[(j + i) % len]);
                costs[j] += cur;//累加操作 j 次的花费
            }
        }
        long ans = Long.MAX_VALUE;
        for (long c : costs) {
            ans = Math.min(ans, c);
        }
        return ans;
    }

    //1185. 一周中的第几天
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        LocalDate date = LocalDate.of(year, month, day);
        return week[date.getDayOfWeek().getValue()];
    }

    //1154. 一年中的第几天
    public int dayOfYear(String date) {
        //        String[] split = date.split("-");
        //        int year = Integer.parseInt(split[0]);
        //        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        //        int month = Integer.parseInt(split[1]);
        //        int day = Integer.parseInt(split[2]);
        //        int[] dayOfMonth = {0, 31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //        for (int i = 1; i < month; i++) {
        //            day+=dayOfMonth[i];
        //        }
        //        return day;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dtf).getDayOfYear();
    }

    //1599. 经营摩天轮的最大利润
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int profit = 0;
        int maxProfit = 0;
        int ans = -1;
        int len = customers.length;
        int remain = 0;
        for (int i = 0; ; i++) {
            if (i < len) {
                remain = remain + customers[i];
            }
            if (remain <= 4) {
                profit += remain * boardingCost - runningCost;
                remain = 0;
            } else {
                profit += boardingCost * 4 - runningCost;
                remain -= 4;
            }
            if (profit > maxProfit) {
                ans = i + 1;
                maxProfit = profit;
            }
            if (remain == 0 && i >= len) {
                break;
            }
        }
        return ans;
    }

    //2706. 购买两块巧克力
    public int buyChoco(int[] prices, int money) {
        int min1 = Integer.MAX_VALUE;
        int min2 = min1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min1) {
                min2 = min1;
                min1 = prices[i];
            } else if (prices[i] < min2) {
                min2 = prices[i];
            }
        }

        return money > (min1 + min2) ? (money - min1 - min2) : money;
    }

    //2487. 从链表中移除节点
    public ListNode removeNodes(ListNode head) {
        //单调栈
        //        ArrayDeque<Integer> deque = new ArrayDeque<>();
        //        deque.offer(Integer.MAX_VALUE);
        //        while (head != null) {
        //            while (deque.peekLast() < head.val) {
        //                deque.pollLast();
        //            }
        //            deque.offer(head.val);
        //            head = head.next;
        //        }
        //        ListNode ans = new ListNode(deque.pop());
        //        head = ans;
        //        for (int x : deque) {
        //            head.next = new ListNode(x);
        //            head = head.next;
        //        }
        //        return ans.next;
        if (head.next == null) {
            return null;
        }
        ListNode node = removeNodes(head.next);
        if (node.val > head.val) {
            return node;
        } else {
            head.next = node;
            return head;
        }
    }

    //2397. 被列覆盖的最多行数
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i] |= matrix[i][j] << j;
            }
        }
        int ans = 0;
        //二进制枚举
        for (int subset = 0; subset < (1 << n); subset++) {
            if (Integer.bitCount(subset) == numSelect) {
                int coveredRows = 0;
                for (int row : mask) {
                    if ((row & subset) == row) {
                        //通过与来判断覆盖
                        coveredRows++;
                    }
                }
                ans = Math.max(coveredRows, ans);
            }
        }
        return ans;
    }

    //1944. 队列中可以看到的人数
    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length;
        int[] ans = new int[len];
        //栈写法
        //        ArrayDeque<Integer> deque = new ArrayDeque<>();
        //        for (int i = len-1; i >= 0; i--) {
        //            while (!deque.isEmpty()&&deque.peek()<heights[i]){
        //                //将所有他看到比他矮的人弹出
        //                deque.pop();
        //                ans[i]++;
        //            }
        //            if (!deque.isEmpty()){
        //                //说明还能看到一个比他自身高的人
        //                ans[i]++;
        //            }
        //            deque.push(heights[i]);
        //        }
        //        return ans;
        // 数组写法
        int[] stack = new int[len];
        int top = -1;
        for (int i = len - 1; i >= 0; i--) {
            while (top >= 0 && stack[top] < heights[i]) {
                top--;
                ans[i]++;
            }
            if (top >= 0) {
                ans[i]++;
            }
            stack[++top] = heights[i];
        }
        return ans;
    }

    //2807. 在链表中插入最大公约数 2024年1月6日
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node.next != null) {
            int a = node.val;
            int b = node.next.val;
            //辗转相除法
            int c = a % b;
            while (c != 0) {
                a = b;
                b = c;
                c = a % b;
            }
            ListNode insert = new ListNode(b);
            insert.next = node.next;
            node.next = insert;
            node = node.next.next;
        }
        return head;
    }

    //383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnts = new int[26];
        int len = magazine.length();
        for (int i = 0; i < len; i++) {
            cnts[magazine.charAt(i) - 'a']++;
        }
        int n = ransomNote.length();
        for (int i = 0; i < n; i++) {
            if (--cnts[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    //447. 回旋镖的数量
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return 0;
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.clear();
            for (int j = 0; j < len; j++) {
                int distance = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                int cnt = map.getOrDefault(distance, 0);
                ans += cnt * 2;
                map.put(distance, cnt + 1);
            }
        }
        return ans;
    }

    //2707. 字符串中的额外字符
    public int minExtraChar(String s, String[] dictionary) {
        //互不重叠
        int len = dictionary.length;
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (String str : dictionary) {
            set.add(str);
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + 1;
            for (int j = 0; j <= i; j++) {
                if (set.contains(s.substring(j, i + 1))) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j]);
                }
            }
        }
        return dp[n];
    }

    //2696. 删除子串后的字符串最小长度
    public int minLength(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == 'A' && s.charAt(i) == 'B') {
                    stack.pop();
                } else if (stack.peek() == 'C' && s.charAt(i) == 'D') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            } else {
                stack.push(s.charAt(i));
            }

        }
        return stack.size();
    }

    //2645. 构造有效字符串的最少插入数
    public int addMinimum(String word) {
        char[] cs = word.toCharArray();
        int ans = 1;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] <= cs[i - 1]) {
                ans++;
            }
        }
        return ans * 3 - cs.length;
    }

    //2085. 统计出现过一次的公共字符串
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words1.length; i++) {
            map.put(words1[i], map.getOrDefault(words1[i], 0) + 1);
        }
        for (int i = 0; i < words2.length; i++) {
            map.put(words2[i], map.getOrDefault(words2[i], 0) + 1000);
        }
        int ans = 0;
        for (String s : map.keySet()) {
            if (map.get(s) == 1001) {
                ans++;
            }
        }
        return ans;
    }

    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates2(ListNode head) {
        int[] cnt = new int[1001];
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode node = head;
        while (node != null) {
            if (cnt[node.val + 100] > 0) {
                pre.next = node.next;
            } else {
                cnt[node.val + 100]++;
                pre = node;
            }
            node = node.next;
        }
        return head;
    }

    //49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(-1, head);
        ListNode node = head;
        head = pre;
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                int x = node.val;
                while (node != null && node.val == x) {
                    node = node.next;
                }
            } else {
                pre = pre.next;
                node = node.next;
            }
            pre.next = node;
        }
        return head.next;
    }

    //2182. 构造限制重复的字符串
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnts = new int[27];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            cnts[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int preIdx = 26;
        for (int i = 25; i >= 0; ) {
            int limit = repeatLimit;
            //1.preIdx还有
            while (cnts[preIdx] > 0 && limit > 0) {
                sb.append((char) ('a' + preIdx));
                cnts[preIdx]--;
                limit--;
            }
            while (i >= 0 && (cnts[i] == 0 || i == preIdx)) {
                i--;
            }
            if (i >= 0 && cnts[preIdx] > 0) {
                cnts[i]--;
                sb.append((char) ('a' + i));
            } else {
                preIdx = i;
            }
        }
        return sb.toString();
    }

    //2744. 最大字符串配对数目
    public int maximumNumberOfStringPairs(String[] words) {
        int ans = 0;
        int len = words.length;
        boolean[] isUsed = new boolean[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (!isUsed[j] && words[i].charAt(0) == words[j].charAt(1) && words[i].charAt(1) == words[j].charAt(0)) {
                    ans++;
                    isUsed[j] = true;
                    break;
                }
            }
        }
        return ans;
    }

    //2171. 拿出最少数目的魔法豆
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int n = beans.length;
        long ans = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += beans[i];
            ans = Math.max(ans, (long) (n - i) * beans[i]);
        }
        return sum - ans;
    }

    //670. 最大交换
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        Collections.sort(list, Collections.reverseOrder());
        //2736  7632  1993  9931
        //7236  9913
        int swapA = -1;
        int swapB = -1;
        int b = -1;
        for (int i = 0; i < s.length(); i++) {
            //找到第一个待交换的数
            if (swapA == -1 && (list.get(i) + '0') != s.charAt(i)) {
                swapA = i;
                b = list.get(i) + '0';
            }
            if (s.charAt(i) == b) {
                swapB = i;
            }

        }
        if (swapA != -1) {
            char[] cs = s.toCharArray();
            char temp = cs[swapA];
            cs[swapA] = cs[swapB];
            cs[swapB] = temp;
            s = String.valueOf(cs);
        }
        return Integer.parseInt(s);
    }

    //2765. 最长交替子数组
    public int alternatingSubarray(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            nums[i] = nums[i] - nums[i - 1];
        }

        int ans = 0;
        for (int i = 1, j = 1; j < len; j++) {
            if (!(nums[j] == nums[j - 1] * -1)) {
                while (j < len && nums[j] != 1) {
                    j++;
                }
                if (j >= len) {
                    break;
                }
                i = j - 1;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    //2865. 美丽塔 I
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long ans = 0;
        int[] mhs = maxHeights.stream().mapToInt(a -> a).toArray();
        int len = mhs.length;
        for (int i = 0; i < len; i++) {
            long cur = mhs[i];
            ;
            int[] temp = mhs.clone();
            for (int j = i + 1; j < len; j++) {
                temp[j] = Math.min(temp[j], temp[j - 1]);
                cur += temp[j];
            }
            for (int j = i - 1; j >= 0; j--) {
                temp[j] = Math.min(temp[j], temp[j + 1]);
                cur += temp[j];
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    //2859. 计算 K 置位下标对应元素的和
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (Integer.bitCount(i) == k) {
                ans += nums.get(i);
            }
        }
        return ans;
    }

    //2788. 按分隔符拆分字符串
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            String[] sp = word.split(separator + "");
            System.out.println(Arrays.toString(sp));
            for (String s : sp) {
                if (s.length() > 0) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    //365. 水壶问题
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }

    //2808. 使循环数组所有元素相等的最少秒数
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int len = nums.size();
        int ans = len;
        for (List<Integer> list : map.values()) {
            int dis = list.get(0) + len - list.get(list.size() - 1);
            for (int i = 1; i < list.size(); i++) {
                dis = Math.max(dis, list.get(i) - list.get(i - 1));
            }
            ans = Math.min(ans, dis / 2);
        }
        return ans;
    }

    //514. 自由之路
    public int findRotateSteps(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int n = s.length;
        int m = t.length;

        // 先算出每个字母的最后一次出现的下标
        // 由于 s 是环形的，循环结束后的 pos 就刚好是 left[0]
        int[] pos = new int[26];
        for (int i = 0; i < n; i++) {
            s[i] -= 'a';
            pos[s[i]] = i;
        }
        // 计算每个 s[i] 左边 a-z 的最近下标（左边没有就从 n-1 往左找）
        int[][] left = new int[n][26];
        for (int i = 0; i < n; i++) {
            System.arraycopy(pos, 0, left[i], 0, 26);
            pos[s[i]] = i;
        }
        // 先算出每个字母的首次出现的下标
        // 由于 s 是环形的，循环结束后的 pos 就刚好是 right[n-1]
        for (int i = n - 1; i >= 0; i--) {
            pos[s[i]] = i;
        }
        // 计算每个 s[i] 右边 a-z 的最近下标（左边没有就从 0 往右找）
        int[][] right = new int[n][26];
        for (int i = n - 1; i >= 0; i--) {
            System.arraycopy(pos, 0, right[i], 0, 26);
            pos[s[i]] = i;
        }
        int[][] dp = new int[m + 1][n];
        for (int j = m - 1; j >= 0; j--) {
            int c = t[j] - 'a';
            for (int i = 0; i < n; i++) {
                if (s[i] == c) {//不用旋转
                    dp[j][i] = dp[j + 1][i];
                } else {
                    int l = left[i][c];
                    int r = right[i][c];
                    dp[j][i] = Math.min(dp[j + 1][l] + (l > i ? n - l + i : i - l), dp[j + 1][r] + (r < i ? n - i + r : r - i));
                }
            }
        }
        return dp[0][0] + m;
    }

    //2670. 找出不同元素数目差数组
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        int[] diff = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            set.add(nums[i]);
            diff[i] = set.size();
        }
        set.clear();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            res[i] = set.size() - diff[i + 1];
        }
        return res;
    }

    //1686. 石子游戏 VI
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        Arrays.sort(ids, (i, j) -> aliceValues[j] + bobValues[j] - bobValues[i] - aliceValues[i]);
        int diff = 0;
        for (int i = 0; i < n; i++) {
            diff += i % 2 == 0 ? aliceValues[ids[i]] : -bobValues[ids[i]];
        }
        return Integer.compare(diff, 0);
    }

    //1690. 石子游戏 VII
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] += s[i] + stones[i];
        }
        int[] f = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[j] = Math.max(s[j + 1] - s[i + 1] - f[j], s[j] - s[i] - f[j - 1]);
            }
        }
        return f[n - 1];
    }

    //1696. 跳跃游戏 VI
    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.MIN_VALUE;
            if (q.peekFirst() < i - k) {
                q.pollFirst();
            }
            dp[i] = dp[q.peekFirst()] + nums[i];
            while (!q.isEmpty() && dp[i] >= dp[q.peekLast()]) {
                q.pollLast();
            }
            q.add(i);
        }

        return dp[len - 1];
    }

    //LCP 30. 魔塔游戏
    public int magicTower(int[] nums) {
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum < 0) {
            return -1;
        }
        PriorityQueue<Integer> rooms = new PriorityQueue();
        long hp = 1;
        int times = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                rooms.add(nums[i]);
            }
            hp += nums[i];
            if (hp < 1 && !rooms.isEmpty()) {
                times++;
                hp -= rooms.poll();
            }
        }
        return times;
    }



    //2476. 二叉搜索树最近节点查询
    class closestNodes {
        public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
            //中序+二分
            List<Integer> list = new ArrayList<>();
            dfs(root, list);
            int n = list.size();
            //转换成数组，更易二分
            int[] treeNodeArr = new int[n];
            for (int i = 0; i < n; i++) {
                treeNodeArr[i] = list.get(i);
            }
            List<List<Integer>> ans = new ArrayList<>(queries.size());
            for (int query : queries) {
                int res = lowerBound(treeNodeArr, query);//此时treeNodeArr[res]>=query
                int max = -1;
                int min = -1;
                if (res != n) {
                    max = treeNodeArr[res];
                    if (treeNodeArr[res] == query) {
                        min = query;
                    } else {
                        min = res == 0 ? -1 : treeNodeArr[res - 1];
                    }
                } else {
                    min = treeNodeArr[res - 1];
                }
                ans.add(Arrays.asList(min, max));
            }
            return ans;
        }

        private int lowerBound(int[] treeNodeArr, int target) {
            int left = 0;
            int right = treeNodeArr.length;
            while (left < right) {
                int mid = left + right >> 1;
                if (treeNodeArr[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }

        private void dfs(TreeNode root, List<Integer> list) {
            //中序 左中右
            if (root == null) {
                return;
            }
            dfs(root.left, list);
            list.add(root.val);
            dfs(root.right, list);
        }

    }

    //2583. 二叉树中的第 K 大层和
    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> list = new ArrayList<>();
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            long sum = 0;
            while (size-- > 0) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                sum += node.val;
            }
            list.add(sum);
        }
        if (list.size() < k) {
            return -1;
        }
        Collections.sort(list);
        return list.get(list.size() - k);
    }

    class constructFromPrePost {
        //889. 根据前序和后序遍历构造二叉树
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            //中左右   左右中
            int n = postorder.length;
            int[] index = new int[n + 1];//1 <= preorder[i] <= preorder.length
            for (int i = 0; i < n; i++) {
                index[postorder[i]] = i;
            }
            return dfs(preorder, 0, n, postorder, 0, n, index);
        }

        public TreeNode dfs(int[] preorder, int prL, int prR, int[] postorder, int poL, int poR, int[] index) {
            if (prL >= prR || poL >= poR) {
                return null;
            }
            if (prL + 1 == prR) {//越界解决
                return new TreeNode(preorder[prL]);
            }
            int leftIndex = index[preorder[prL + 1]];
            int leftSize = leftIndex - poL + 1;
            TreeNode left = dfs(preorder, prL + 1, prL + leftSize + 1, postorder, poL, leftIndex + 1, index);
            TreeNode right = dfs(preorder, prL + leftSize + 1, prR, postorder, leftIndex + 1, prR - 1, index);
            return new TreeNode(preorder[prL], left, right);
        }
    }

    //106从中序与后序遍历序列构造二叉树
    class constructBinaryYreeFromInorderAndPostorderTraversal {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            //左中右   左右中
            int n = inorder.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }
            return dfs(inorder, 0, n, postorder, 0, n, map);
        }

        public TreeNode dfs(int[] inorder, int inL, int inR, int[] postorder, int poL, int poR, Map<Integer, Integer> map) {
            //不存在
            if (inL >= inR || poL >= poR) {
                return null;
            }
            int rootIndex = map.get(postorder[poR - 1]);
            int leftSize = rootIndex - inL;
            TreeNode left = dfs(inorder, inL, rootIndex, postorder, poL, poL + leftSize, map);
            TreeNode right = dfs(inorder, rootIndex + 1, inR, postorder, poL + leftSize, poR - 1, map);
            return new TreeNode(postorder[poR - 1], left, right);
        }
    }

    class isCousins {
        private TreeNode father;
        private int deep = -1;
        private boolean ans;

        //993. 二叉树的堂兄弟节点
        public boolean isCousins(TreeNode root, int x, int y) {
            dfsIsCousins(root, x, y, 0, null);
            return ans;
        }

        public boolean dfsIsCousins(TreeNode root, int x, int y, int depth, TreeNode curFather) {
            if (root == null || deep > 0 && depth > deep) {
                return false;
            }
            if (root.val == x || root.val == y) {
                if (deep > 0) {
                    ans = depth == deep && curFather != father;
                    return true;
                } else {
                    deep = depth;
                    father = curFather;
                }
            }
            return dfsIsCousins(root.left, x, y, depth + 1, root) || dfsIsCousins(root.right, x, y, depth + 1, root);
        }
    }

    //105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //左闭右开
        return prInBuildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    public TreeNode prInBuildTree(int[] preorder, int prL, int prR, int[] inorder, int inL, int inR, Map<Integer, Integer> map) {
        //该子树不存在
        if (prR <= prL || inR <= inL) {
            return null;
        }
        int rootIndex = map.get(preorder[prL]);//找到当前节点在中序的位置
        int leftSize = rootIndex - inL;//计算左子树的长度
        TreeNode left = prInBuildTree(preorder, prL + 1, prL + leftSize + 1, inorder, inL, rootIndex, map);
        TreeNode right = prInBuildTree(preorder, prL + leftSize + 1, prR, inorder, rootIndex + 1, inR, map);
        return new TreeNode(preorder[prL], left, right);
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> dq = new ArrayDeque<>();
        dq.addLast(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = dq.pollFirst();
                if (node.left != null) {
                    dq.addLast(node.left);
                }
                if (node.right != null) {
                    dq.addLast(node.right);
                }
                list.add(node.val);
            }
            ans.add(list);
        }
//        Collections.reverse(ans);
        return ans;
    }

    //103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        boolean isEven = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                list.add(node.val);
            }
            if (!isEven) {
                Collections.reverse(list);
            }
            isEven = !isEven;
            ans.add(list);
        }
        return ans;
    }

    //429. N 叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                Node node = deque.pollFirst();
                list.add(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        deque.addLast(child);
                    }
                }
            }
            ans.add(list);
        }
        return ans;
    }

    //589. N 叉树的前序遍历
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        preorderDFS(root, ans);
        return ans;
    }

    public void preorderDFS(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        List<Node> children = root.children;
        if (children != null) {
            for (Node child : children) {
                preorderDFS(child, ans);
            }
        }
    }

    //590. N 叉树的后序遍历
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        postorderDFS(root, ans);
        return ans;
    }

    public void postorderDFS(Node root, List<Integer> ans) {
        //左右中
        if (root == null) {
            return;
        }
        List<Node> children = root.children;
        if (children != null) {
            for (Node child : children) {
                postorderDFS(child, ans);
            }
        }
        ans.add(root.val);
    }

    //107. 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> dq = new ArrayDeque<>();
        dq.addLast(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = dq.pollFirst();
                if (node.left != null) {
                    dq.addLast(node.left);
                }
                if (node.right != null) {
                    dq.addLast(node.right);
                }
                list.add(node.val);
            }
            ans.add(list);
        }
        Collections.reverse(ans);
        return ans;
    }

    //145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode prev = null;
        //左右中
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right != null && prev != node.right) {
                stack.push(node);
                node = node.right;
            } else {
                prev = node;
                ans.add(node.val);//记录
                node = null;
            }
            //System.out.println(ans);
        }
        return ans;
    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        //左中右
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }


    //236. 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        //左右中 后序
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        mon7 mon7 = new mon7();
        System.out.println(mon7.gcd(2, 6));
    }

}

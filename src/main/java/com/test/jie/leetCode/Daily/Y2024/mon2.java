package com.test.jie.leetCode.Daily.Y2024;

import com.test.jie.leetCode.tool.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class mon2 {
    public static void main(String[] args) {
        mon2 mon2 = new mon2();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.merge(1, 12, Integer::sum);
        map.merge(1, 12, (a, b) -> {
            return 999;

        });
        System.out.println(map);
    }

    //331. 验证二叉树的前序序列化
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        int counts = 1;
        while (i < n) {
            if (counts == 0) {
                return false;
            }
            if (preorder.charAt(i) == '#') {
                counts--;
                i++;
            } else if (preorder.charAt(i) == ',') {
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                counts++;
            }
        }
        return counts == 0;
    }

    //2952. 需要添加的硬币的最小数量
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int ans = 0;
        int sum = 1;
        int i = 0;
        while (sum <= target) {
            if (i < coins.length && sum >= coins[i]) {
                sum += coins[i];
                i++;
            } else {
                sum = sum * 2;
                ans++;
            }
        }
        return ans;
    }

    //2908. 元素和最小的山形三元组 I
    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        int[] left = new int[n];
        int min = 100;
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i - 1], min);
            left[i] = min;
        }
        min = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min = Math.min(min, nums[i + 1]);
            if (left[i] < nums[i] && nums[i] > min) {
                ans = Math.min(left[i] + min + nums[i], ans);
                System.out.println(left[i] + ":" + nums[i] + ":" + min);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //1997. 访问完所有房间的第一天
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        final long mod = (long) 1e9 + 7;
        int n = nextVisit.length;
        long[] s = new long[n];
        for (int i = 0; i < n - 1; i++) {
            int j = nextVisit[i];
            s[i + 1] = (s[i] * 2 - s[j] + 2 + mod) % mod;
        }
        return (int) s[n - 1];
    }

    //560. 和为 K 的子数组    前缀和
    public int subarraySum(int[] nums, int k) {
        //哈希+前缀和（前缀和保存在哈希），key：前缀和，value：同为该值的前缀和次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            //计算
            preSum += nums[i];
            //preSum + "key" = k
            count += map.getOrDefault(preSum - k, 0);
            //同为该值的前缀和次数+1
            map.merge(preSum, 1, Integer::sum);
        }
        return count;
//        int n = nums.length;
//        int[] sum = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            sum[i] = sum[i - 1] + nums[i - 1];
//        }
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j <= n; j++) {
//                if (sum[j] - sum[i] == k) {
//                    ans++;
//                }
//            }
//        }
//        return ans;
    }

    //2580. 统计将重叠区间合并成组的方案数
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int len = ranges.length;
        int ans = 1;
        int mod = (int) 1e9 + 7;
        int maxR = -1;
        for (int i = 0; i < len; i++) {
            if (ranges[i][0] > maxR) {//有新的区间
                ans = ans * 2 % mod;
            }
            maxR = Math.max(maxR, ranges[i][1]);
        }

        return ans;
    }

    //518. 零钱兑换 II
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    //322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    //2671. 频率跟踪器
    class FrequencyTracker {
        private final Map<Integer, Integer> nums;
        private final Map<Integer, Integer> freq;

        public FrequencyTracker() {
            nums = new HashMap<>();
            freq = new HashMap<>();
        }

        public void update(int number, int delta) {
            int res = nums.merge(number, delta, Integer::sum);
            freq.merge(res - delta, -1, Integer::sum);
            freq.merge(res, 1, Integer::sum);
        }

        public void add(int number) {
            update(number, 1);
        }

        public void deleteOne(int number) {
            if (nums.getOrDefault(number, 0) > 0) {
                update(number, -1);
            }
        }

        public boolean hasFrequency(int frequency) {
            return freq.getOrDefault(frequency, 0) > 0;
        }
    }

    //1969. 数组元素的最小非零乘积
    class minNonZeroProduct {
        private static final int MOD = 1_000_000_007;

        private long pow(long x, int p) {
            x %= MOD;
            long res = 1;
            while (p-- > 0) {
                res = res * x % MOD;
                x = x * x % MOD;
            }
            return res;
        }

        public int minNonZeroProduct(int p) {
            long k = (1L << p) - 1;
            return (int) (k % MOD * pow(k - 1, p - 1) % MOD);
        }
    }

    //1793. 好子数组的最大分数
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int max = nums[k];
        int minHigh = nums[k];
        int left = k;
        int right = k;
        for (int t = 0; t < n - 1; t++) {
            if (right == n - 1 || left > 0 && nums[left - 1] > nums[right + 1]) {
                minHigh = Math.min(minHigh, nums[--left]);
            } else {
                minHigh = Math.min(minHigh, nums[++right]);
            }
            max = Math.max(max, minHigh * (right - left + 1));
        }
        return max;
//        int[] left = new int[n];
//        left[0] = -1;
//        for (int i = 1; i < n; i++) {
//            int j = i - 1;
//            while (j >= 0 && nums[j] >= nums[i]) {
//                j = left[j];
//            }
//            left[i] = j;
//        }
//        int[] right = new int[n];
//        right[n - 1] = n;
//        for (int i = n - 2; i >= 0; i--) {
//            int j = i + 1;
//            while (j < n && nums[j] >= nums[i]) {
//                j = right[j];
//            }
//            right[i] = j;
//        }
//        int max = 0;
//        for (int i = 0; i < n; i++) {
//            int high = nums[i];
//            int leftIdx = left[i];
//            int rightIdx = right[i];
//            //判断是否包括k下标
//            if (leftIdx < k && k < rightIdx) {
//                max = Math.max(max, high * (rightIdx - leftIdx - 1));
//            }
//        }
//        return max;
    }

    //2602. 使数组元素全部相等的最少操作次数
    public List<Long> minOperations(int[] nums, int[] queries) {
        List<Long> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        long[] sum = new long[n + 1];
        //前缀和
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int left = 0;
            int right = n;
            while (left < right) {
                int mid = left + right >> 1;
                if (nums[mid] >= query) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            long operationTimes = sum[n] - sum[right] * 2 + query * (2L * right - n);
            ans.add(operationTimes);
        }
        return ans;
    }

    //303. 区域和检索 - 数组不可变
    class NumArray {
        private int[] sum;

        public NumArray(int[] nums) {
            int len = nums.length;
            sum = new int[len + 1];
            for (int i = 0; i < len; i++) {
                sum[i + 1] = nums[i] + sum[i];
            }
        }

        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }

    //310. 最小高度树
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            ans.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                ans.add(cur);
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return ans;
    }

    //2684. 矩阵中移动的最大次数
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] canMove = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            canMove[i][0] = true;
        }
        int ans = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                if (canMove[i][j]) {
                    if ((i > 0 && grid[i - 1][j + 1] > grid[i][j])) {
                        canMove[i - 1][j + 1] = true;
                        ans = j + 1;
                    }
                    if (grid[i][j + 1] > grid[i][j]) {
                        canMove[i][j + 1] = true;
                        ans = j + 1;
                    }
                    if (i < m - 1 && grid[i + 1][j + 1] > grid[i][j]) {
                        canMove[i + 1][j + 1] = true;
                        ans = j + 1;
                    }
                }
            }
            if (ans < j + 1) {
                break;
            }
        }
        return ans;
    }

    //2312. 卖木头块
    public long sellingWood(int m, int n, int[][] prices) {
        int[][] ps = new int[m + 1][n + 1];
        for (int[] price : prices) {
            ps[price[0]][price[1]] = price[2];
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = ps[i][j];
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }

    //2789. 合并后数组中的最大元素
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long ans = nums[n - 1];
        for (int i = n - 1; i > 0; i--) {
            if (ans >= nums[i - 1]) {
                ans += nums[i - 1];
            } else {
                ans = nums[i - 1];
            }
        }
        return ans;
    }

    //1642. 可以到达的最远建筑
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int len = heights.length;
        for (int i = 1; i < len; i++) {
            if (heights[i] > heights[i - 1]) {
                int bricksCost = heights[i] - heights[i - 1];
                if (bricks >= bricksCost) {
                    //直接用砖块
                    pq.offer(bricksCost);
                    bricks -= bricksCost;
                    //如果
                } else if (ladders > 0) {
                    //砖块不够，尝试梯子跨过之前高度差最大的楼层，或者用梯子代替砖块并补充当前砖块
                    if (!pq.isEmpty() && pq.peek() > bricksCost) {
                        pq.offer(bricksCost);
                        bricks += pq.poll() - bricksCost;
                    }
                    ladders--;
                } else {
                    //砖块和梯子不够
                    return i - 1;
                }
            }
        }
        return len - 1;
    }

    //2864. 最大二进制奇数
    public String maximumOddBinaryNumber(String s) {
        int len = s.length();
        int oneCnt = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                oneCnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - 1; i++) {
            if (oneCnt-- > 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        sb.append('1');
        return sb.toString();
    }

    //1261. 在受污染的二叉树中查找元素
    class FindElements {
        private TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
        }

        public boolean find(int target) {
            target++;
            TreeNode cur = root; // 从根节点出发
            for (int i = 30 - Integer.numberOfLeadingZeros(target); i >= 0; i--) { // 从次高位开始枚举
                int bit = (target >> i) & 1; // target 第 i 位的比特值
                cur = bit == 0 ? cur.left : cur.right;
                if (cur == null) { // 走到空节点，说明 target 不在二叉树中
                    return false;
                }
            }
            return true; // 没有走到空节点，说明 target 在二叉树中
        }
    }

    //2129. 将标题首字母大写
    public String capitalizeTitle(String title) {
        StringBuilder ans = new StringBuilder();
        for (String s : title.split(" ")) {
            if (ans.length() > 0) {
                ans.append(' ');
            }
            if (s.length() > 2) {
                ans.append(s.substring(0, 1).toUpperCase());
                s = s.substring(1);
            }
            ans.append(s.toLowerCase());
        }
        return ans.toString();
    }

    //299. 猜数字游戏
    public String getHint(String secret, String guess) {
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        int n = secret.length();
        int A = 0;
        int B = 0;
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                cntS[secret.charAt(i) - '0']++;
                cntG[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            B += Math.min(cntG[i], cntS[i]);
        }
        return A + "A" + B + "B";
    }

    //2386. 找出数组的第 K 大和
    public long kSum(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>((a, b) -> Long.compare(a.getKey(), b.getKey()));
        pq.offer(new Pair<>(0L, 0));
        while (--k > 0) {
            Pair<Long, Integer> p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < nums.length) {
                // 在子序列的末尾添加 nums[i], 下一个添加/替换的元素下标为 i+1
                pq.offer(new Pair<>(s + nums[i], i + 1));
                if (i > 0) {// 替换子序列的末尾元素为 nums[i]
                    pq.offer(new Pair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return sum - pq.poll().getKey();
    }

    //2834. 找出美丽数组的最小和
    public int minimumPossibleSum(int n, int target) {
        final int MOD = (int) 1e9 + 7;
        long pre = target / 2;
        if (pre >= n) {
            return (int) ((long) (n + 1) * n / 2 % MOD);
        } else {
            return (int) (((long) (pre + 1) * pre / 2 + ((long) 2 * target + n - pre - 1) * (n - pre) / 2) % MOD);
        }
    }//(n-pre)*(2*t+n-pre-1)

    //2575. 找出字符串的可整除数组
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        long num = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            //(a + b) mod m = (a mod m + b mod m) mod m
            num = (num * 10 + (word.charAt(i) - '0')) % m;
            if (num == 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    //2917. 找出数组中的 K-or 值
    public int findKOr(int[] nums, int k) {
        if (k > nums.length) {
            return 0;
        }
        int ans = 0;
        for (int j = 0; j < 31; j++) {
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                cnt += (nums[i] >> j) & 1;
            }
            if (cnt >= k) {
                ans |= 1 << j;
            }
        }
        return ans;
    }

    public int countPaths(int n, int[][] roads) {
        long[][] g = new long[n][n]; // 邻接矩阵
        for (long[] row : g) {
            Arrays.fill(row, Long.MAX_VALUE / 2); // 防止溢出
        }
        for (int[] r : roads) {
            int x = r[0];
            int y = r[1];
            int d = r[2];
            g[x][y] = d;
            g[y][x] = d;
        }

        long[] dis = new long[n];
        Arrays.fill(dis, 1, n, Long.MAX_VALUE / 2);
        int[] f = new int[n];
        f[0] = 1;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x == n - 1) {
                // 不可能找到比 dis[n-1] 更短，或者一样短的最短路了（注意本题边权都是正数）
                return f[n - 1];
            }
            done[x] = true; // 最短路长度已确定（无法变得更小）
            for (int y = 0; y < n; y++) { // 尝试更新 x 的邻居的最短路
                long newDis = dis[x] + g[x][y];
                if (newDis < dis[y]) {
                    // 就目前来说，最短路必须经过 x
                    dis[y] = newDis;
                    f[y] = f[x];
                } else if (newDis == dis[y]) {
                    // 和之前求的最短路一样长
                    f[y] = (f[y] + f[x]) % 1_000_000_007;
                }
            }
        }
    }

    class verticalTraversal {
        //987. 二叉树的垂序遍历
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            Map<Integer, List<int[]>> map = new TreeMap<>();
            dfs(root, map, 0, 0);
            for (List<int[]> value : map.values()) {
                List<Integer> list = new ArrayList<>();
                value.sort((a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
                for (int[] nums : value) {
                    list.add(nums[1]);
                }
                ans.add(list);
            }
            return ans;
        }

        public void dfs(TreeNode root, Map<Integer, List<int[]>> map, int row, int col) {
            if (root == null) {
                return;
            }
            dfs(root.left, map, row + 1, col - 1);
            dfs(root.right, map, row + 1, col + 1);
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{row, root.val});
        }
    }

    //232. 用栈实现队列
    class MyQueue {
        ArrayDeque<Integer> stack;
        ArrayDeque<Integer> stack2;

        public MyQueue() {
            stack = new ArrayDeque<Integer>();
        }

        public void push(int x) {
            stack.addLast(x);
        }

        public int pop() {
            if (empty()) {
                return -1;
            }
            return find(true);
        }

        public int peek() {
            if (empty()) {
                return -1;
            }
            return find(false);
        }

        public int find(boolean needPop) {
            int size = stack.size();
            stack2 = new ArrayDeque<>();
            while (!stack.isEmpty() && size-- > 0) {
                stack2.addLast(stack.pollLast());
            }
            int pop = stack2.peekLast();
            if (needPop) {
                stack2.pollLast();
            }
            size = stack2.size();
            while (!stack2.isEmpty() && size-- > 0) {
                stack.addLast(stack2.pollLast());
            }
            return pop;
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

    //2368. 受限条件下可到达节点的数目
    class reachableNodes {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            boolean[] isRestricted = new boolean[n];
            for (int r : restricted) {
                isRestricted[r] = true;
            }
            List<Integer>[] graph = new ArrayList[n];
            Arrays.setAll(graph, g -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0];
                int y = e[1];
                if (!isRestricted[x] && !isRestricted[y]) {
                    graph[x].add(y);
                    graph[y].add(x);
                }
            }
            return dfs(graph, 0, -1);
        }

        public int dfs(List<Integer>[] graph, int node, int father) {
            int cnt = 1;
            for (int num : graph[node]) {
                if (num != father) {
                    cnt += dfs(graph, num, node);
                }
            }
            return cnt;
        }
    }

    //2369. 检查数组是否存在有效划分
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        //数组切成小段，由前面各种不同段为true为基础推断后面的接着的小段，[4,4,4,5,6]可以看[4,4]为true再接着看后面的[4,5,6],
        //[4,4,4]为true再看后面的[5,6]
        dp[0] = true;//初始化
        //这里true表示可以从该下标开始判断
        for (int i = 1; i < n; i++) {
            if ((dp[i - 1] && nums[i - 1] == nums[i]) ||
                    i > 1 && dp[i - 2] && ((nums[i - 2] == nums[i - 1] && nums[i - 1] == nums[i]) ||
                            (nums[i - 2] == nums[i - 1] - 1 && nums[i - 1] == nums[i] - 1))) {
                dp[i + 1] = true;
            }
        }
        return dp[n];
    }

    //2641. 二叉树的堂兄弟节点 II
    public TreeNode replaceValueInTree(TreeNode root) {
        ArrayDeque<TreeNode> fa = new ArrayDeque<>();
        fa.addLast(root);
        root.val = 0;
        while (!fa.isEmpty()) {
            int sum = 0;
            ArrayDeque<TreeNode> tmpFa = new ArrayDeque<>();
            //存子节点，计算下一深度节点和
            for (TreeNode father : fa) {
                if (father.left != null) {
                    sum += father.left.val;
                    tmpFa.addLast(father.left);
                }
                if (father.right != null) {
                    sum += father.right.val;
                    tmpFa.addLast(father.right);
                }
            }
            for (TreeNode father : fa) {
                int childSum = (father.left != null ? father.left.val : 0)
                        + (father.right != null ? father.right.val : 0);
                //其他节点的总和
                childSum = sum - childSum;
                if (father.left != null) {
                    father.left.val = childSum;
                }
                if (father.right != null) {
                    father.right.val = childSum;
                }
            }
            fa = tmpFa;
        }
        return root;
    }

    //2673. 使二叉树所有路径值相等的最小代价
    public int minIncrements(int n, int[] cost) {
        //[1,5,1,1,1,5,5]
        int ans = 0;
        for (int i = n / 2; i > 0; i--) {
            ans += Math.abs(cost[i * 2] - cost[i * 2 - 1]);
            cost[i - 1] += Math.max(cost[i * 2], cost[i * 2 - 1]);
        }
        return ans;
    }
//2867. 统计树中的合法路径数目
//    public long countPaths(int n, int[][] edges) {}

    //938. 二叉搜索树的范围和
    class rangeSumBST {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }
            if (root.val < low) {
                return rangeSumBST(root.right, low, high);
            }
            if (root.val > high) {
                return rangeSumBST(root.left, low, high);
            }
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }

    }

    //235. 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }


}

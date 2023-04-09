package com.test.jie.leetCode.Daily.Y2023;

import com.test.jie.leetCode.tool.TreeNode;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class mon1 {
    /**
     * 743. 网络延迟时间
     * 中等
     * 有 n 个网络节点，标记为 1 到 n。
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     * 示例 1：
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * 输出：2
     * 示例 2：
     * 输入：times = [[1,2,1]], n = 2, k = 1
     * 输出：1
     * 示例 3：
     * 输入：times = [[1,2,1]], n = 2, k = 2
     * 输出：-1
     * 提示：
     * 1 <= k <= n <= 100
     * 1 <= times.length <= 6000
     * times[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 0 <= wi <= 100
     * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
     *
     * @return
     */
    class networkDelayTimeClass {
        int N = 110, M = 6010;
        // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
        int[][] w = new int[N][N];
        int INF = 0x3f3f3f3f;
        int n, k;

        public int networkDelayTime(int[][] times, int _n, int _k) {
            n = _n;
            k = _k;
            // 初始化邻接矩阵
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    w[i][j] = w[j][i] = i == j ? 0 : INF;
                }
            }
            //存图
            for (int[] t : times) {
                int u = t[0], v = t[1], c = t[2];
                w[u][v] = c;
            }
            //最短路
            floyd();
            //遍历答案
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = Math.max(ans, w[k][i]);
            }
            return ans >= INF / 2 ? -1 : ans;
        }

        private void floyd() {
            // floyd 基本流程为三层循环：
            // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
            for (int p = 1; p <= n; p++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        w[i][j] = Math.min(w[i][j], w[i][p] + w[p][j]);
                    }
                }
            }
        }
    }

    //2185. 统计包含给定前缀的字符串
    // https://leetcode.cn/problems/counting-words-with-a-given-prefix/
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for (String s : words) {
            if (s.startsWith(pref)) {
                cnt++;
            }
        }
        return cnt;
    }

    //https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/description/
    //1806. 还原排列的最少操作步数
    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        int[] arr = new int[n];
        int cnt = 0;
        boolean flag = false;
        while (!flag) {
            flag = true;
            cnt++;
            for (int i = 0; i < n; i++) {
                int idx = i % 2 == 0 ? i / 2 : n / 2 + (i - 1) / 2;
                arr[i] = perm[idx];
                if (flag) {
                    flag = arr[i] == i;
                }
            }
            perm = arr;
            arr = new int[n];
        }
        return cnt;
    }

    //1807. 替换字符串中的括号内容 https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                int start = i + 1;
                while (i < len && s.charAt(i) != ')') {
                    i++;
                }
                String sub = s.substring(start, i);
                sb.append(map.getOrDefault(sub, "?"));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 维护堆的性质
     *
     * @param arr 存储堆的数组
     * @param n   数组长度
     * @param i   待维护结点的下标
     */
    public void heapify(int[] arr, int n, int i) {
        int largest = i;
        int lson = i * 2 + 1;
        int rson = i * 2 + 2;
        if (lson < n && arr[lson] > arr[largest]) {
            largest = lson;
        }
        if (rson < n && arr[rson] > arr[largest]) {
            largest = rson;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }
    }

    public void heapSort(int[] arr, int n) {
        //建堆
        //子树的父节点：n/2-1
        for (int i = n / 2 - 1; i >= 0; i--) {
            //维护堆性质
            heapify(arr, n, i);
        }
        //排序
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        mon1 solution = new mon1();
        Random ran = new Random();
        Scanner sc = new Scanner(System.in);
        char input='a';
        int arrLen = 10;
        long startTime=0;
        Date date = new Date();
        while (arrLen<=10000000) {
            int[] arr = new int[arrLen];
            int[] cnt = new int[arrLen];
            for (int i = 0; i < arr.length; ) {
                int cur = ran.nextInt(arrLen);
                if (cnt[cur] == 0) {
                    arr[i] = cur;
                    cnt[cur]++;
                    i++;
                }
            }
//            System.out.println(Arrays.toString(arr));
            startTime=System.currentTimeMillis();
            solution.heapSort(arr, arr.length);
            startTime=System.currentTimeMillis()-startTime;
            System.out.println("程序运行时间为："+ startTime);
//            System.out.println(Arrays.toString(arr));
//            System.out.print("输入'q'退出，其他任意键继续：");
//            input= sc.next().charAt(0);
            System.out.println("数组长度："+arrLen);
            arrLen*=10;
        }
        System.out.println("结束");
    }
}



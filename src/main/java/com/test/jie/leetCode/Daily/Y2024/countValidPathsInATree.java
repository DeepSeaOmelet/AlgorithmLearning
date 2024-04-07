package com.test.jie.leetCode.Daily.Y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//2867. 统计树中的合法路径数目
public class countValidPathsInATree {
    private final static int MAX = (int) 1e5;
    //// 质数=false 非质数=true
    private final static boolean[] NP = new boolean[MAX + 1];

    static {
        //找出1到1e5内所有质数
        NP[1] = true;
        for (int i = 2; i * i <= MAX; i++) {
            if (!NP[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    NP[j] = true;
                }
            }
        }
    }

    public long countPaths(int n, int[][] edges) {
        //建图
        List<Integer>[] g = new ArrayList[n+1];
        Arrays.setAll(g,a->new ArrayList<>());
        for (int[] e : edges) {
            int x= e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        long ans = 0;
        int[] size = new int[n+1];//用于保存该非质数下所在连通块，在不经过质数的前提下，统计有多少个非质数
        List<Integer> nodes = new ArrayList<>();
        for (int x = 1; x <= n; x++) {
            if (NP[x]){//跳过非质数
                continue;
            }
            int sum = 0;
            for (int y : g[x]) {// 尚未计算过
                if (!NP[y]){//只能有一个质数
                    continue;
                }
                if (size[y]==0){
                    nodes.clear();
                    dfs(y,-1,g,nodes);
                    for (int node : nodes) {
                        size[node]=nodes.size();
                    }
                }
                // 这 size[y] 个非质数与之前遍历到的 sum 个非质数，两两之间的路径只包含质数 x
                ans+=(long)size[y]*sum;
                sum+=size[y];
            }
            ans+=sum;// 从 x 出发的路径
        }
        return ans;
    }

    private void dfs(int x, int fa, List<Integer>[] g, List<Integer> nodes) {
        nodes.add(x);
        for (int y : g[x]) {
            if (y!=fa&&NP[y]){
                dfs(y,x,g,nodes);
            }
        }
    }
}

package com.test.jie.leetCode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EightQueens {
    public static void main(String[] args) {
        EightQueens queens = new EightQueens();
        long startTIme = System.currentTimeMillis();
        List<List<String>> ans = queens.solveNQueens(8);
        long runTime = System.currentTimeMillis()-startTIme;
        int cnt = 0;
        for (List<String> ls:ans){
            for (String s:ls){
                System.out.println(s);
            }
            System.out.println();
            cnt++;
        }
        System.out.println("共消耗"+runTime+"毫秒");
        System.out.println("有 "+cnt+" 种结果");
    }

    List<List<String>> ans2 = new ArrayList<List<String>>();
    public List<List<String>> solveNQueens2(int n) {
        int[][] res = new int[3][2 * n + 1];
        int[] cur = new int[n];
        Arrays.fill(cur,-1);
        dfs2(0,res,cur,n);
        return ans2;
    }

    /**
     *
     * @param rows  层数
     * @param res   引用
     * @param cur   当前排序
     * @param n     n皇后
     */
    void dfs2(int rows,int[][] res,int[] cur,int n){
        if (rows==n){
            List<String> string = getString2(cur);
            ans2.add(string);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (res[0][i]==0 && res[1][i+rows]==0 &&res[2][i-rows+n]==0){
                //修改辅助变量
                cur[rows]=i;
                res[0][i]=res[1][i+rows]=res[2][i-rows+n]=1;
                dfs2(rows+1,res,cur,n);
                //恢复变量
                cur[rows]=0;
                res[0][i]=res[1][i+rows]=res[2][i-rows+n]=0;
            }
        }
    }

    List<String> getString2(int[] cur){
        List<String> list = new ArrayList<>(cur.length);
        for (int i:cur){
            char[] cs = new char[cur.length];
            Arrays.fill(cs,'.');
            cs[i]='Q';
            list.add(String.valueOf(cs));
        }
        return list;
    }






    List<List<String>> ans = new ArrayList<List<String>>();
    public List<List<String>> solveNQueens(int n) {
        int[] cur = new int[n];
        //cur数组表示0到 n-1行 中每行'Q'所处的列数
        //例如：cur[i]=j表示第i行的'Q'在第j列
        Arrays.fill(cur,-1);

        //三行，分别表示列冲突，左、右斜对角线冲突
        //因为是逐行放置的，没有行冲突了
        int[][] res = new int[3][2*n+1];
        dfs(res,0,cur,n);
        return ans;
    }

    void dfs(int[][] res, int row, int[] cur, int n) {
        //row表示行，row==n表示逐行放置全部放完，没有冲突即满足题目要求
        if (row==n){
            List<String> list = getString(cur);
            ans.add(list);
            return;
        }
        //这里可以用树来理解，一层一层的，n个分支
        for (int i = 0; i < n; i++) {
            //这里res[2][i-row+n]之所以加n是防止出现负数，不好判断
            //这里i+row的地方是指左对角线，在该对角线上，i+row的数值都是相同的
            //而i-row是指右对角线，在该对角线上，i-row的数值都是相同的，+n是防止出现负数
            //而且同时不用二维，一维则可以表示在该点上有数值，也就是在该对角线或列也是有数值的，不用考虑行，因为是逐行向下的
            if (res[0][i]==0 && res[1][i+row]==0 && res[2][i-row+n]==0){

            //修改辅助变量
                cur[row]=i;
                res[0][i]=res[1][i+row]=res[2][i-row+n]=1;
            //向下递归
                dfs(res,row+1,cur,n);
            //将修改过的辅助变量更改回去
                cur[row]=-1;
                res[0][i]=res[1][i+row]=res[2][i-row+n]=0;
            }
        }
    }

    //将满足要求的集合返回
    public List<String> getString(int[] cur) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cur.length; i++) {
            char[] chars = new char[cur.length];
            Arrays.fill(chars,'.');
            chars[cur[i]]='Q';
            list.add(String.valueOf(chars));
        }
        return list;
    }
}

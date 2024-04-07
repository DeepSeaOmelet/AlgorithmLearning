package com.test.jie.leetCode.Daily.Y2024;

import com.test.jie.leetCode.tool.TreeNode;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class mon4 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
    }

    //1600. 王位继承顺序
    class ThroneInheritance {
        private String king;
        private Map<String, List<String>> map = new HashMap<>();
        private Set<String> deathList = new HashSet<>();

        public ThroneInheritance(String kingName) {
            king = kingName;
            map.put(king, new ArrayList<>());
        }

        public void birth(String parentName, String childName) {
            map.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
        }

        public void death(String name) {
            deathList.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            dfs(ans, king);
            return ans;
        }

        //计算继承顺序
        private void dfs(List<String> orderList, String parent) {
            if (!deathList.contains(parent)) {
                orderList.add(parent);
            }
            //如果 x 没有孩子,返回 Successor(x 的父亲, curOrder)
            if (!map.containsKey(parent)) {
                return;
            }
            List<String> children = map.get(parent);
            for (String child : children) {
                dfs(orderList, child);
            }
        }
    }

    //1483. 树节点的第 K 个祖先 2024年4月6日
    class TreeAncestor {
        private int[][] ancestor;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n);
            ancestor = new int[n][m];
            for (int i = 0; i < n; i++) {
                ancestor[i][0] = parent[i];
            }
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int anc = ancestor[x][i];
                    ancestor[x][i + 1] = anc < 0 ? -1 : ancestor[anc][i];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k);
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) > 0) {
                    node = ancestor[node][i];
                    if (node < 0) {
                        break;
                    }
                }
            }
            return node;
        }

    }

    //1026. 节点与其祖先之间的最大差值 2024年4月5日
    public int maxAncestorDiff(TreeNode root) {
        return maxAncestorDiffDFS(root, root.val, root.val);
    }

    public int maxAncestorDiffDFS(TreeNode root, int max, int min) {
        if (root == null) {
            return 0;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        int res = max - min;
        res = Math.max(maxAncestorDiffDFS(root.left, max, min), res);
        res = Math.max(maxAncestorDiffDFS(root.right, max, min), res);
        return res;
    }

    //2192. 有向无环图中一个节点的所有祖先 2024年4月4日
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] grep = new ArrayList[n];
        Arrays.setAll(grep, i -> new ArrayList<>());
        for (int[] edge : edges) {
            grep[edge[1]].add(edge[0]);//反向有向图
        }
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[n];//表示这个祖先是否已经访问过
        for (int i = 0; i < n; i++) {
            Arrays.fill(visit, false);
            getAncestorsDFS(grep, i, visit);
            visit[i] = false;//自身不是祖先
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (visit[j]) {
                    list.add(j);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public void getAncestorsDFS(List<Integer>[] grep, int cur, boolean[] visit) {
        //往回递归就是祖先，则成true
        visit[cur] = true;
        for (int parent : grep[cur]) {
            if (!visit[parent]) {
                getAncestorsDFS(grep, parent, visit);
            }
        }
    }

    //1379. 找出克隆二叉树中的相同节点   2024年4月3日
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left == null) {
            return getTargetCopy(original.right, cloned.right, target);
        } else {
            return left;
        }
    }


    //2810. 故障键盘    2024年4月1日
    public String finalString(String s) {
        Deque<Character> q = new ArrayDeque<>();
        boolean isTail = true;
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                isTail = !isTail;
            } else if (isTail) {
                q.addLast(c);
            } else {
                q.addFirst(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : q) {
            sb.append(c);
        }
        if (!isTail) {
            sb.reverse();
        }
        return sb.toString();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == 'i') {
//                sb.reverse();
//            }else {
//                sb.append(s.charAt(i));
//            }
//        }
//        return sb.toString();
    }
}

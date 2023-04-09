package com.test.jie.leetCode.bfs;

import java.util.*;

/**
 *      B   ---  D
 *   /  |      / |  \
 * A    |    /   |    F
 *   \  |  /     |
 *      C   ---  E
 *
 *   这里是图的结构，对图进行BFS和DFS
 */
public class BFSLearn {

    static Map<String, String[]> graph;

    static {
        graph = new HashMap<>();
        graph.put("A", new String[]{"B", "C"});
        graph.put("B", new String[]{"A", "C", "D"});
        graph.put("C", new String[]{"A", "B", "D", "E"});
        graph.put("D", new String[]{"B", "C", "E", "F"});
        graph.put("E", new String[]{"C", "D"});
        graph.put("F", new String[]{"D"});
    }

    public static void BFS(Map<String, String[]> graph, String s) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast(s);
        Set<String> set = new HashSet<>();
        set.add(s);
        while (!deque.isEmpty()) {
            String cur = deque.pollFirst();
            String[] ss = graph.get(cur);
            for (String str : ss) {
                if (!set.contains(str)) {
                    deque.addLast(str);
                    set.add(str);
                }
            }
            System.out.print(cur+" ");

        }
        System.out.println();
    }
    public static void DFS(Map<String, String[]> graph, String s) {
        Stack<String> stack = new Stack<>();
        stack.push(s);
        Set<String> set = new HashSet<>();
        set.add(s);
        while (!stack.isEmpty()) {
            String cur = stack.pop();
            String[] ss = graph.get(cur);
            for (String str : ss) {
                if (!set.contains(str)) {
                    stack.push(str);
                    set.add(str);
                }
            }
            System.out.print(cur+" ");
        }
        System.out.println();
    }
    //BFS
    // 图转树
    //最小生成子树,最小路径，dijkstra算法
    public static Map<String,String> graphTransTree(Map<String, String[]> graph, String s){
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast(s);
        Set<String> set = new HashSet<>();
        Map<String, String> parents = new HashMap<>();
        parents.put(s,null);
        set.add(s);
        while (!deque.isEmpty()) {
            String cur = deque.pollFirst();
            String[] ss = graph.get(cur);
            for (String str : ss) {
                if (!set.contains(str)) {
                    deque.addLast(str);
                    set.add(str);
                    parents.put(str,cur);
                }
            }
            System.out.print(cur+" ");
        }
        System.out.println();
        return parents;
    }

    public static void main(String[] args) {
        BFS(graph,"E");
        DFS(graph,"E");
        Map<String, String> parents = graphTransTree(graph, "E");
        for (String key:parents.keySet()){
            System.out.println(key+" "+parents.get(key));
        }
        String v = "B";
        while (v!=null){
            System.out.print(v+" ");
            v=parents.get(v);
        }
    }
}

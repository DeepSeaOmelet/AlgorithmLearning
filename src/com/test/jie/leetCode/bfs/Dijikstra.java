package com.test.jie.leetCode.bfs;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;

public class Dijikstra {

    static Comparator<Node> com = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.val - o2.val;
        }
    };

    static String[] places = new String[]{"A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        // 二维数组每一行分别是 A、B、C、D、E、F 各点到其余点的距离,
        // A -> A 距离为0, 常量M 为正无穷
        int[][] graph = {
                {0, 5, 1, 0, 0, 0},
                {5, 0, 2, 1, 0, 0},
                {1, 2, 0, 4, 8, 0},
                {0, 1, 4, 0, 3, 6},
                {0, 0, 8, 3, 0, 0},
                {0, 0, 0, 6, 0, 0}

        };
        dijikstra(graph, 0);
        System.out.println(parents);
        System.out.println(distance);
        String key = "F";
        while (parents.get(key) != null) {
            System.out.print(parents.get(key) + "->");
            key = parents.get(key);
        }
    }

    static Map<String, String> parents;
    static Map<String, Integer> distance;

    public static void init() {
        parents = new HashMap<>();
        distance = new HashMap<>();
        for (String p:places){
            distance.put(p,Integer.MAX_VALUE);
        }
    }

    public static void dijikstra(int[][] graph, int start) {
        init();
        Queue<Node> pq = new PriorityQueue<>(com);
        pq.add(new Node(0, 0));
        parents.put(places[start], null);
        Set<Integer> set = new HashSet<>();
        set.add(start);
        distance.put(places[start], 0);
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            int vertex = n.side;
            set.add(vertex);
            //i表示各个字母
            for (int i = 0; i < graph[vertex].length; i++) {
                //1.graph为0，即为没有连接
                //2.排除已经算好的
                //3.发现距离过小，覆盖
                if (graph[vertex][i] != 0 && !set.contains(i) && distance.get(places[i]) > n.val + graph[vertex][i]) {
                    pq.add(new Node(i, n.val + graph[vertex][i]));
                    parents.put(places[i], places[vertex]);
                    distance.put(places[i], n.val + graph[vertex][i]);
                }
            }
        }

    }

    static class Node {
        public int side;
        public int val;

        public Node(int side, int val) {
            this.side = side;
            this.val = val;
        }
    }
}

package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2022/07/30
 * 952. 按公因数计算最大组件大小
 * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
 * <p>
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,6,15,35]
 * 输出：4
 * 示例 2：
 * 输入：nums = [20,50,9,63]
 * 输出：2
 * 示例 3：
 * 输入：nums = [2,3,6,7,4,12,21,39]
 * 输出：8
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 105
 * nums 中所有值都 不同
 */
public class LargestComponentSizeByCommon7Factor {
    public static void main(String[] args) {
        // 创建一个 HashMap
        HashMap<String, String> countries = new HashMap<>();

        // 往HashMap插入映射项
        countries.put("Washington", "America");
        countries.put("Canberra", "Australia");
        countries.put("Madrid", "Spain");
        System.out.println("HashMap: " + countries);

        //合并 key为 Washington的映射
        String returnedValue = countries.merge("Washington", "USA", (oldValue, newValue) -> oldValue + "/" + newValue);
        System.out.println("Washington: " + returnedValue);

        //输出更新后的HashMap
        System.out.println("Updated HashMap: " + countries);
    }

    /**
     * 并查集数组构建：
     */
    static int N = 20010;
    static int[] p = new int[N], sz = new int[N];
    int ans = 1;

    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        //枚举因数,因数分解
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            for (int j = 2; j * j <= cur; j++) {
                if (cur % j == 0) {
                    add(map, j, i);
                }
                while (cur % j == 0) {
                    cur /= j;
                }
            }
            if (cur > 1) add(map, cur, i);
        }
        //构建并查集
        for (int i = 0; i < n; i++) {
            p[i] = i;
            sz[i] = 1;
        }
        //通过map的映射关系，用并查集构建连通块(图)
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = 1; i < list.size(); i++) {
                //只需遍历到一遍list,list中的元素就全部联通了
                union(list.get(0), list.get(i));
            }
        }
        return ans;
    }

    //找到当前下标所属的连通块的开始下标
    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    //连通两个下标，规定总是以较小的下标作为开始下标
    private void union(int a, int b) {
        if (find(a) == find(b)) return;
        sz[find(a)] += sz[find(b)];//计算 连通块的大小
        p[find(b)] = p[find(a)];//都指向同一个父
        ans=Math.max(ans,sz[find(a)]);
    }

    /*
    构建以因数为key，与nums数组相关的数的集合
     */
    private void add(Map<Integer, List<Integer>> map, int key, int val) {
        List<Integer> list = map.getOrDefault(key, new ArrayList<>());
        list.add(val);
        map.put(key, list);
    }
}
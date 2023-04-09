package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.*;

/**
 *  两个列表的最小索引总和
 *
 *  假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 *
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 *
 * 
 *
 * 示例 1:
 *
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 示例 2:
 *
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * 
 *
 * 提示:
 *
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] 和 list2[i] 由空格' '和英文字母组成。
 * list1 的所有字符串都是 唯一 的。
 * list2 中的所有字符串都是 唯一 的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumIndexSumOfTwoLists {
    public static void main(String[] args) {
        MinimumIndexSumOfTwoLists twoLists = new MinimumIndexSumOfTwoLists();
        System.out.println(Arrays.toString(twoLists.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})));
    }
    //哈希表
    //为了快速判断某个字符串是否在另外一个数组中出现，我们可以先使用「哈希表」对 list1 中的字符串进行处理，以 (list1[i]: i)(list1[i]:i) 键值对形式进行存储。
    public String[] findRestaurant(String[] list1, String[] list2) {
        int n = list1.length,m= list2.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(list1[i],i );
        }
        List<String> ans = new ArrayList<>();
        int min =3000;
        for (int i = 0; i < m; i++) {
            String s = list2[i];
            if (!map.containsKey(s)){
                continue;
            }
            if (i+ map.get(s)<min){
                min=i+map.get(s);
                ans.clear();
                ans.add(s);
            }else if (i+map.get(s)==min){
                ans.add(s);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}

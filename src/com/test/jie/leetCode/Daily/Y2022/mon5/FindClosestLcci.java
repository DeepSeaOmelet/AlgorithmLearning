package com.test.jie.leetCode.Daily.Y2022.mon5;

/**
 * 面试题 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 *
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 *
 * words.length <= 100000
 */
public class FindClosestLcci {
    public static void main(String[] args) {
        FindClosestLcci lcci = new FindClosestLcci();
        System.out.println(lcci.findClosest(
                new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"},
                "a", "student"));
    }
    public int findClosest(String[] ws, String a, String b) {
        int len = ws.length;
        int ans = len;
        int left = -1;
        int right = -1;
        for (int i = 0; i < len; i++) {
            if (ws[i].equals(a)){
                left = i;
            }
            if (ws[i].equals(b)){
                right=i;
            }
            if (left!=-1 && right!=-1){
                ans=Math.min(ans,Math.abs(left-right));
            }
        }
        return ans;
    }

}

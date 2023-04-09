package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/10/20
 * 779. 第K个语法符号
 * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。
 * 接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <p>
 * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
 * 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
 * <p>
 * 示例 1:
 * 输入: n = 1, k = 1
 * 输出: 0
 * 解释: 第一行：0
 * 示例 2:
 * 输入: n = 2, k = 1
 * 输出: 0
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 示例 3:
 * 输入: n = 2, k = 2
 * 输出: 1
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * <p>
 * 提示:
 * 1 <= n <= 30
 * 1 <= k <= 2n - 1
 */
public class KThSymbolInGrammar {
    public static void main(String[] args) {
        KThSymbolInGrammar k = new KThSymbolInGrammar();
        System.out.println(k.kthGrammar(30, 5));
    }

    //行奇列奇0->0 行偶列偶1->0
    //行奇列偶0->1 行奇列偶1->1
    public int kthGrammar(int n, int k) {
        return dfs(n, k, 1) == 0 ? 1 : 0;
    }

    int dfs(int r, int c, int cur) {
        if (r == 1) return cur;
        if ((c % 2 == 1 && cur == 0) || (c % 2 == 0 && cur == 1)) {
            return dfs(r - 1, (c + 1) / 2, 0);
        } else {
            return dfs(r - 1, (c + 1) / 2, 1);
        }
    }
}
//       01
//      0110
//    01101001
//0110100110010110

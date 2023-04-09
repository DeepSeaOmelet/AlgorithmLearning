package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * 2038. 如果相邻两个颜色均相同则删除当前颜色
 * <p>
 * 总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，其中colors[i]表示第i个颜色片段的颜色。
 * <p>
 * Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。
 * <p>
 * 如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。Alice不可以删除任何颜色'B'片段。
 * 如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。Bob 不可以删除任何颜色 'A'片段。
 * Alice 和 Bob 不能从字符串两端删除颜色片段。
 * 如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。
 * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false。
 * <p>
 * 示例 1：
 * 输入：colors = "AAABABB"
 * 输出：true
 * 解释：
 * AAABABB -> AABABB
 * Alice 先操作。
 * 她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
 * 现在轮到 Bob 操作。
 * Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
 * 因此，Alice 获胜，返回 true 。
 * 示例 2：
 * 输入：colors = "AA"
 * 输出：false
 * 解释：
 * Alice 先操作。
 * 只有 2 个 'A' 且它们都在字符串的两端，所以她无法执行任何操作。
 * 因此，Bob 获胜，返回 false 。
 * 示例 3：
 * 输入：colors = "ABBBBBBBAAA"
 * 输出：false
 * 解释：
 * ABBBBBBBAAA -> ABBBBBBBAA
 * Alice 先操作。
 * 她唯一的选择是删除从右数起第二个 'A' 。
 * ABBBBBBBAA -> ABBBBBBAA
 * 接下来轮到 Bob 操作。
 * 他有许多选择，他可以选择任何一个 'B' 删除。
 * 然后轮到 Alice 操作，她无法删除任何片段。
 * 所以 Bob 获胜，返回 false 。
 * <p>
 * <p>
 * 提示：
 * 1 <=colors.length <= 105
 * colors只包含字母'A'和'B'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {
    public static void main(String[] args) {
        RemoveColoredPiecesIfBothNeighborsAreTheSameColor sameColor = new RemoveColoredPiecesIfBothNeighborsAreTheSameColor();
        long time = System.currentTimeMillis();
        System.out.println(sameColor.winnerOfGame("AAABABB"));
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(sameColor.winnerOfGame("ABBBBBBBAAA"));
    }

    //脑筋急转弯
    //根据删除规则，删除任意一个 A 不会影响可被删删除的 B 的数量，反之亦然。
    //
    //因此直接统计「可删除的 A 的数量」和「可删除的 B 的数量」，分别记为 a 和 b，比较 a 和 b 的大小即可得到答案（只有 a > b 时，先手获胜）。
    //
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int n = cs.length;
        int a=0;
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == 'A' && cs[i - 1] == 'A' && cs[i + 1] == 'A') {
                a++;
            }
            if (cs[i] == 'B' && cs[i - 1] == 'B' && cs[i + 1] == 'B') {
                a--;
            }
        }
        return a > 0;
    }
}

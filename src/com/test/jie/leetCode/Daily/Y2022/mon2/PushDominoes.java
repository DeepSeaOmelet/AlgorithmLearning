package com.test.jie.leetCode.Daily.Y2022.mon2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 推多米诺
 * https://leetcode-cn.com/problems/push-dominoes/
 * <p>
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * <p>
 * 示例 1：
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * <p>
 * 提示：
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 * L 76 R 82 . 46
 */
public class PushDominoes {
    public static void main(String[] args) {
        PushDominoes dominoes = new PushDominoes();
        System.out.println(dominoes.pushDominoes("RR.L"));//"RR.L"
        System.out.println(dominoes.pushDominoes(".L.R...LR..L.."));//"LL.RR.LLRRLL.."
        System.out.println(dominoes.pushDominoes("."));
        System.out.println(dominoes.pushDominoes(".L.R...LR..L.."));//"LL.RR.LLRRLL.."
        System.out.println(dominoes.pushDominoes(".L.R."));//"LL.RR"
        System.out.println(dominoes.pushDominoes("R."));//"RR"
        System.out.println(dominoes.pushDominoes("RL"));//"RL"
        System.out.println(dominoes.pushDominoes("..R.."));//"..RRR"
    }

    /**
     * BFS
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        char[] cs = dominoes.toCharArray();
        int n = cs.length;
        int[] g = new int[n];
        Deque<int[]> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (cs[i] == '.') continue;
            int dire = cs[i] == 'L' ? -1 : 1;
            d.add(new int[]{i, 1, dire});
            g[i] = 1;
        }
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int loc = info[0], time = info[1], dire = info[2];
            int ne = loc + dire;//定位'.'
            if (cs[loc] == '.' || (ne < 0 || ne >= n)) continue;//排除多次受力和首尾
            if (g[ne] == 0) {
                //首次受力
                d.addLast(new int[]{ne, time + 1, dire});
                g[ne] = time + 1;
                cs[ne] = dire == -1 ? 'L' : 'R';
            } else if (g[ne] == time + 1) {
                //多次受力
                cs[ne] = '.';
            }
        }
        return String.valueOf(cs);
    }

    /**
     * 预处理 + 双指针
     */
    static int N = 100010;
    static int[][] l = new int[N][2], r = new int[N][2];

    public String pushDominoes2(String dominoes) {
        char[] cs = dominoes.toCharArray();
        int n = cs.length;
        //left
        for (int i = 0, j = -1; i < n; i++) {
            if (cs[i] != '.') j = i;
            l[i] = new int[]{j, j != -1 ? cs[j] : '.'};
        }
        //right
        for (int i = 0, j = -1; i >= 0; i--) {
            if (cs[i] != '.') j = i;
            r[i] = new int[]{j, j != -1 ? cs[j] : '.'};
        }
        for (int i = 0; i < n;) {
            if (cs[i] != '.' && ++i >= 0) continue;
            int j = i;
            while (j < n && cs[j] == '.') j++;
            j--;
            int[] a = l[i], b = r[j];
            int loc1 = a[0], dire1 = a[1], loc2 = b[0], dire2 = b[1];
            if (loc1 == -1 && loc2 == -1) { // 两侧无力
            } else if (loc1 == -1) { // 只有右侧有力，且力的方向向左
                if (dire2 == 'L') update(cs, i, j, 'L', 'L');
            } else if (loc2 == -1) { // 只有左侧有力，且力的方向向右
                if (dire1 == 'R') update(cs, i, j, 'R', 'R');
            } else { // 两侧有力，且两力方向「不同时」反向
                if (!(dire1 == 'L' && dire2 == 'R')) update(cs, i, j, (char)dire1, (char)dire2);
            }
            i = j + 1;
        }
        return String.valueOf(cs);
    }
    void update(char[] cs, int l, int r, char c1, char c2) {
        for (int p = l, q = r; p <= q; p++, q--) {
            if (p == q && c1 != c2) continue;
            cs[p] = c1; cs[q] = c2;
        }
    }
}

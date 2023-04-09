package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/27
 * 592. 分数加减运算
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。
 * <p>
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 * 示例 1:
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 * 示例 2:
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 * <p>
 * 提示:
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 */
public class FractionAdditionAndSubtraction {

    public static void main(String[] args) {

    }

    //分数以带符号形式存在
    public String fractionAddition(String expression) {
        int n = expression.length();
        char[] cs = expression.toCharArray();
        String sum = "";
        for (int i = 0; i < n; ) {
            int j = i + 1;
            //截取整个分数 如+1/2
            while (j < n && cs[j] != '+' && cs[j] != '-') j++;
            String num = expression.substring(i, j);
            //判断首个分数是否带符号，不带符号要补充
            if (cs[i] != '+' && cs[i] != '-') num = "+" + num;
            sum = sum.equals("") ? num : calc(num, sum);
            i = j;
        }
        return sum.charAt(0) == '+' ? sum.substring(1) : sum;
    }

    public String calc(String a, String b) {
        //必须a为正
        boolean fa = a.charAt(0) == '+';
        boolean fb = b.charAt(0) == '+';
        if (!fa && fb) {
            return calc(b, a);
        }
        long[] pa = parse(a);
        long[] pb = parse(b);
        long pam = pa[0] * pb[1];
        long pbm = pb[0] * pa[1];
        //判断运算符
        if (fa && fb) {
            long sum = pam + pbm;
            long de = pa[1] * pb[1];
            long gcd = gcd(sum, de);
            return "+" + (sum / gcd) + "/" + (de / gcd);
        } else if (!fa && !fb) {
            long sum = pam + pbm;
            long de = pa[1] * pb[1];
            long gcd = gcd(sum, de);
            return "-" + (sum / gcd) + "/" + (de / gcd);
        } else {
            //a正b负
            long sum = pam - pbm;
            long de = pa[1] * pb[1];
            long gcd = gcd(Math.abs(sum), de);
            String ans = (sum / gcd) + "/" + (de / gcd);
            return pam >= pbm ? "+" + ans : ans;
        }
    }

    //将±x/y转换为[x,t]
    long[] parse(String s) {
        int n = s.length();
        int idx = 1;
        while (idx < n && s.charAt(idx) != '/') {
            idx++;
        }
        long a = Long.parseLong(s.substring(1, idx));
        long b = Long.parseLong(s.substring(idx + 1));
        return new long[]{a, b};
    }

    //最大公约数
    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.HashMap;
import java.util.Map;

/**
 * 2022/07/06
 * 736. Lisp 语法解析
 * 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 * <p>
 * 表达式语法如下所示:
 * <p>
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 * let 表达式采用 "(let v1 e1 v2 e2 ... vn en expr)" 的形式，其中 let 总是以字符串 "let"来表示，
 * 接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量 v1被分配为表达式 e1 的值，第二个变量 v2 被分配为表达式 e2 的值，依次类推；最终 let 表达式的值为 expr表达式的值。
 * <p>
 * add 表达式表示为 "(add e1 e2)" ，其中 add 总是以字符串 "add" 来表示，该表达式总是包含两个表达式 e1、e2 ，
 * 最终结果是 e1 表达式的值与 e2 表达式的值之 和 。
 * <p>
 * mult 表达式表示为 "(mult e1 e2)" ，其中 mult 总是以字符串 "mult" 表示，
 * 该表达式总是包含两个表达式 e1、e2，最终结果是 e1 表达式的值与 e2 表达式的值之 积 。
 * <p>
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。
 * 为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * <p>
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），
 * 然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))"
 * 输出：14
 * 解释：
 * 计算表达式 (add x y), 在检查变量 x 值时，
 * 在变量的上下文中由最内层作用域依次向外检查。
 * 首先找到 x = 3, 所以此处的 x 值是 3 。
 * 示例 2：
 * <p>
 * 输入：expression = "(let x 3 x 2 x)"
 * 输出：2
 * 解释：let 语句中的赋值运算按顺序处理即可。
 * 示例 3：
 * <p>
 * 输入：expression = "(let x 1 y 2 x (add x y) (add x y))"
 * 输出：5
 * 解释：
 * 第一个 (add x y) 计算结果是 3，并且将此值赋给了 x 。
 * 第二个 (add x y) 计算结果是 3 + 2 = 5 。
 * <p>
 * 提示：
 * <p>
 * 1 <= expression.length <= 2000
 * exprssion 中不含前导和尾随空格
 * expressoin 中的不同部分（token）之间用单个空格进行分隔
 * 答案和所有中间计算结果都符合 32-bit 整数范围
 * 测试用例中的表达式均为合法的且最终结果为整数
 */
public class ParseLispExpression {
    public static void main(String[] args) {

    }

    String s;
    char[] cs;

    public int evaluate(String expression) {
        s = expression;
        cs = expression.toCharArray();
        return dfs(0, cs.length - 1, new HashMap<>());
    }

    public int dfs(int l, int r, Map<String, Integer> map) {
        if (cs[l] == '(') {//是表达式,即下一个词为关键词
            int idx = l;
            //获取下一个词
            while (cs[idx] != ' ') idx++;
            String op = s.substring(l + 1, idx);
            r--;//跳过后面的括号
            if (op.equals("let")) {
                //(let v1 e1 v2 e2 ... vn en expr)-->v1=e1
                for (int i = idx + 1; i <= r; ) {
                    //当前的是vn 或者是 expr
                    int right = getRight(i, r);
                    String key = s.substring(i, right);
                    //(right >= r) -> expr    最终会走到这里
                    if (right >= r) return dfs(i, right - 1, new HashMap<>(map));
                    //后面的是en
                    right++;
                    i = right;
                    right = getRight(right, r);
                    int value = dfs(i, right - 1, new HashMap<>(map));
                    map.put(key, value);
                    //下一个 vi ei
                    i = right + 1;
                }
                return -1;//错误的表达式
            } else {
                //(add e1 e2) 或 (mult e1 e2)
                int right = getRight(idx + 1, r);
                int a = dfs(idx + 1, right - 1, new HashMap<>(map));
                int b = dfs(right + 1, r, new HashMap<>(map));
                return op.equals("add") ? a + b : a * b;
            }
        } else {
            //不是表达式,即ei数值
            String cur = s.substring(l, r + 1);
            if (map.containsKey(cur)) return map.get(cur);
            return Integer.parseInt(cur);
        }
    }

    /**
     * 含义为从 left 出发，一直往右找（不超过 end），直到取得合法的「子表达式」或「对应的值」。
     *
     * @param left
     * @param end
     * @return
     */
    private int getRight(int left, int end) {
        //score判断经过多少个括号
        int right = left, score = 0;
        while (right <= end) {
            if (cs[right] == '(') {
                score++;
                right++;
            } else if (cs[right] == ')') {
                score--;
                right++;
            } else if (cs[right] == ' ') {
                if (score == 0) break;//即一个完整的表达式或一个完整的值
                right++;
            } else {
                right++;
            }
        }
        //当right > end，即为最后一个表达式或一个完整的值
        return right;
    }
}
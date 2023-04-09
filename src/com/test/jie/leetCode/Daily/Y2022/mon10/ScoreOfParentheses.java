package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.*;

/**
 *  2022/10/09
 *  856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 * 输入： "(()(()))"
 * 输出： 6
 *
 * 提示：
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {
    public static void main(String[] args) {
        ScoreOfParentheses sc = new ScoreOfParentheses();
        System.out.println(sc.scoreOfParentheses("(())"));
        System.out.println(sc.scoreOfParentheses("()()"));
        System.out.println(sc.scoreOfParentheses("(()(()))"));
    }
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (char c : s.toCharArray()) {
            if (c=='('){
                stack.add(0);
            }else {
                //获取当前匹配的'('的值
                Integer cur = stack.pop();
                //计算该层括号的值
                stack.add(stack.pop()+ Math.max(2*cur,1));
            }
        }
        return stack.peek();
    }
}
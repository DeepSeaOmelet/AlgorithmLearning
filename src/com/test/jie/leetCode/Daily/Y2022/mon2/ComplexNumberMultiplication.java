package com.test.jie.leetCode.Daily.Y2022.mon2;

/**
 * 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 * 示例 1：
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * <p>
 * 提示：
 * num1 和 num2 都是有效的复数表示。
 */
public class ComplexNumberMultiplication {
    public static void main(String[] args) {
        ComplexNumberMultiplication numberMultiplication = new ComplexNumberMultiplication();
        System.out.println(numberMultiplication.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(numberMultiplication.complexNumberMultiply("1+-1i", "1+-1i"));
        System.out.println(numberMultiplication.complexNumberMultiply2("1+1i", "1+1i"));;
    }

    //自己
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf('+')));
        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf('+')));
//        System.out.println(num1.substring(num1.indexOf('+')+1, num1.length() - 1));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf('+')+1, num1.length() - 1));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf('+')+1, num2.length() - 1));
        int ansA = a1 * a2;
        int ansB = a1 * b2 + a2 * b1;
        int ansC = (b1 * b2);
        return ansA - ansC + "+" + ansB + "i";
    }
    //大佬
    public String complexNumberMultiply2(String num1, String num2) {
        String[] ss1 = num1.split("[+|i]");
        String[] ss2 = num2.split("[+|i]");
        int a = parse(ss1[0]), b = parse(ss1[1]);
        int c = parse(ss2[0]), d = parse(ss2[1]);
        int A = a * c - b * d, B = b * c + a * d;
        return A + "+" + B + "i";
    }
    int parse(String s){
        return Integer.parseInt(s);
    }
}

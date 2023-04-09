package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * UTF-8 编码验证
 * <p>
 * 给定一个表示数据的整数数组data，返回它是否为有效的 UTF-8 编码。
 * <p>
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1：
 * 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列:11000101 10000010 00000001。
 * 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 * 示例 2：
 * 输入：data = [235,140,4]
 * 输出：false
 * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 * <p>
 * <p>
 * 提示:
 * 1 <= data.length <= 2 * 104
 * 0 <= data[i] <= 255
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/utf-8-validation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Utf8Validation {
    public static void main(String[] args) {
        Utf8Validation utf8Validation = new Utf8Validation();
        System.out.println(utf8Validation.validUtf8(new int[]{197, 130, 1}));
        System.out.println(utf8Validation.validUtf8(new int[]{235, 140, 4}));
    }

    //排除错误的，剩下的就是正确的
    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int t = data[i], j = 7;
            //判断是多少位的
            while (j >= 0 && (((t >> j) & 1) == 1)) {
                j--;
            }
            int cnt = 7 - j;
            //如果 cnt 为 1 或者大于 4 均违反编码规则
            if (cnt == 1 || cnt > 4) {
                return false;
            }
            //位置 i 后面不足cnt−1 也返回 False
            if (cnt + i - 1 >= n) {
                return false;
            }
            //否则检查下标范围为[i+1,i+cnt−1] 的数是否满足前两位为 10 的要求，若不满足返回 False。
            for (int k = i + 1; k < i + cnt; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) {
                    continue;
                }
                return false;
            }
            //下一个编码
            if (cnt == 0) {
                i++;
            } else {
                i += cnt;
            }
        }
        return true;
    }
}

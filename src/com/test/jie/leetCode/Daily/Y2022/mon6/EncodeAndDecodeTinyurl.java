package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. TinyURL 的加密与解密
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
 * <p>
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 * <p>
 * <p>
 * 示例：
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 * <p>
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 * <p>
 * 提示：
 * 1 <= url.length <= 104
 * 题目数据保证 url 是一个有效的 URL
 */
public class EncodeAndDecodeTinyurl {

    /**
     * 关于使用「自增 id」的答疑
     * 为啥要使用「哈希存储映射关系」的方式，而不是用「自增 id」的方式，感觉可能是一部分同学的共同疑惑，这里统一回答一下。
     *
     * 其中两点较为致命的弊端是：
     *
     * 使用「自增 id」更容易被枚举；
     * （再补充，提出原问题的好奇宝宝又问了另外一个问题）关于被枚举的坏处：粗略来说会导致安全性下降，被攻击的可能性大大增强。
     * 因为可枚举意味着，不能光靠 URL 特定发放来确保安全。
     *
     * 结合具体场景来说，假设某个活动日期生成了短 URL 的活动链接，只有特定用户可以访问，
     * 可枚举意味着竞品只需要大概知道你自增 id 当前在什么范围即可拿到活动链接，即使活动链接做了鉴权，
     * 也能通过攻击手段导致服务请求数量突增，影响真实的活动用户使用。
     *
     * 或许这个场景并不致命，竞品要拿到活动链接有很多方式，未必是通过枚举短 URL 来做到。
     * 但另外一个场景则是致命的，就是可枚举会导致使用短 URL 服务的「验证服务」失效。
     *
     * 例如某个服务会通过「邮件/短信」发送短 URL 链接，让用户通过点击短 URL 来验证是否为某个「邮箱/手机号」的拥有者，
     * 短 URL 可枚举，意味着只需要知道当前自增 id 大概范围，就可通过枚举的方式访问到真实的验证地址，从而实现「不收到某个邮件/短信」也可以通过验证的目的。
     *
     * 相比于使用「哈希存储映射关系」的方式，不好重用：
     *
     * 举个🌰，例如映射关系都是 77 天过期，每天会产生了 1010 个短 URL，当进行到第 88 天，此时 id 已经自增到 7171，
     * 同时第一天产生的短 URL 已过期，但是实现上，我们不好将 id 进行回拨（需要考虑当重用数字用完后要回到 7171 的位置）。
     * 也就是说，发现而且重用某些数字段（过期的 id）会为“自增”带来困扰，而引入「重用池」则违背了选择自增方案的初衷。
     *
     * 但「哈希存储映射关系」要实现重用则是简单的，如果随机产生的短 URL 发生冲突，只需要直接拒绝进行重试即可，
     * 一旦产生短 URL 无冲突，则可以直接使用。同时由于随机通常服从某种分布，我们无须引入「过期时间戳」等信息，
     * 即可达到「某个短 URL 在使用后的一段时间后，都不会被随机到（不会在过期后就被迅速重用）」的作用，这一点可以通过调大 kk 值来进一步保证。
     *
     * 作者：AC_OIer
     * 链接：https://leetcode.cn/problems/encode-and-decode-tinyurl/solution/by-ac_oier-ca6o/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    String prefix = "http://tinyurl.com/";
    Map<String, String> longToTiny = new HashMap<>();
    Map<String, String> tinyToLong = new HashMap<>();
    Random ran = new Random();
    int TinyUrlSize = 6;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (!longToTiny.containsKey(longUrl)) {
            char[] encode = new char[6];
            for (int i = 0; i < TinyUrlSize; i++) {
                encode[i] = str.charAt(ran.nextInt(str.length()));
            }
            String tinyUrl = prefix + String.valueOf(encode);
            if (tinyToLong.containsKey(tinyUrl)){
                continue;
            }
            longToTiny.put(longUrl,tinyUrl);
            tinyToLong.put(tinyUrl,longUrl);
        }
        return longToTiny.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return tinyToLong.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
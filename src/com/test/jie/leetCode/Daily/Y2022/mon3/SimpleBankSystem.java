package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.ArrayList;
import java.util.List;

/**
 * 2043. 简易银行系统
 * <p>
 * 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
 * 银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。
 * 请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
 * 指定的账户数量在 1 和 n 之间，且
 * 取款或者转账需要的钱的总数 小于或者等于 账户余额。
 * 实现 Bank 类：
 * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
 * boolean transfer(int account1, int account2, long money) 从编号为account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
 * boolean deposit(int account, long money) 向编号为account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
 * [[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
 * 输出：
 * [null, true, true, true, false, false]
 * <p>
 * 解释：
 * Bank bank = new Bank([10, 100, 20, 50, 30]);
 * bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
 * // 账户 3 余额为 $20 - $10 = $10 。
 * bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
 * // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
 * bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
 * // 账户 5 的余额为 $10 + $20 = $30 。
 * bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
 * // 所以无法转账 $15 。
 * bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
 * <p>
 * 提示：
 * n == balance.length
 * 1 <= n, account, account1, account2 <= 105
 * 0 <= balance[i], money <= 1012
 * transfer, deposit, withdraw 三个函数，每个 最多调用 104 次
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simple-bank-system
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SimpleBankSystem {
    public static void main(String[] args) {
        SimpleBankSystem system = new SimpleBankSystem();
//        system.Solution(new String[]{"Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"}, new long[][]{{10, 100, 20, 50, 30}, {3, 10}, {5, 1, 20}, {5, 20}, {3, 4, 15}, {10, 50}});
        //[null,true,true,false,false,true,true,false,false,true,false,false,true]
        List solution1 = system.Solution(new String[]{"Bank", "deposit", "transfer", "transfer", "transfer", "deposit", "transfer", "withdraw", "withdraw", "transfer", "transfer", "deposit", "withdraw", "deposit", "withdraw", "transfer", "withdraw", "transfer", "withdraw", "transfer", "deposit", "deposit", "transfer", "deposit", "withdraw", "deposit", "deposit", "transfer", "withdraw", "deposit", "transfer", "withdraw", "deposit", "deposit", "transfer", "deposit", "withdraw", "withdraw", "deposit", "withdraw", "deposit", "deposit", "deposit", "transfer", "withdraw", "transfer", "withdraw", "transfer", "transfer", "deposit", "transfer", "transfer", "withdraw", "withdraw", "transfer", "transfer", "deposit", "withdraw", "deposit", "transfer", "transfer", "withdraw", "deposit", "deposit", "transfer", "withdraw", "withdraw", "withdraw", "withdraw", "withdraw", "transfer", "transfer", "transfer", "transfer", "withdraw", "deposit", "withdraw", "deposit", "withdraw", "withdraw", "deposit", "withdraw", "transfer", "withdraw", "withdraw", "withdraw", "deposit", "deposit", "transfer", "deposit", "deposit", "transfer", "transfer", "transfer", "transfer", "deposit", "transfer", "deposit", "deposit", "withdraw", "transfer", "deposit", "deposit", "deposit", "withdraw", "withdraw", "withdraw", "transfer", "deposit", "transfer", "deposit", "withdraw", "withdraw", "transfer", "transfer", "transfer", "deposit", "withdraw", "withdraw", "withdraw", "deposit", "withdraw", "transfer", "transfer", "withdraw", "transfer", "withdraw", "transfer", "deposit", "transfer", "transfer", "withdraw", "deposit", "transfer", "withdraw", "withdraw", "withdraw", "withdraw", "transfer", "deposit", "withdraw", "transfer", "deposit", "transfer", "withdraw", "withdraw", "transfer", "deposit", "withdraw", "deposit", "withdraw", "transfer", "transfer", "withdraw", "transfer", "transfer", "transfer", "transfer", "deposit", "deposit", "deposit", "transfer", "transfer", "withdraw", "deposit", "deposit", "deposit", "deposit", "transfer", "transfer", "withdraw", "transfer", "transfer", "transfer", "deposit", "transfer", "deposit", "withdraw", "deposit", "transfer", "withdraw", "transfer", "withdraw", "withdraw", "withdraw", "deposit", "deposit", "transfer", "withdraw", "deposit", "withdraw", "withdraw", "withdraw", "deposit", "withdraw", "deposit", "withdraw", "deposit", "transfer", "transfer", "withdraw", "transfer", "withdraw", "transfer", "transfer", "withdraw", "deposit", "transfer", "transfer", "transfer", "deposit", "transfer", "withdraw", "transfer", "deposit", "withdraw", "transfer", "withdraw", "transfer", "transfer", "withdraw", "transfer", "deposit", "transfer", "deposit", "withdraw", "transfer", "deposit", "deposit", "withdraw", "transfer", "deposit", "transfer", "transfer", "deposit", "transfer", "withdraw", "deposit", "transfer", "withdraw", "deposit", "transfer", "transfer", "deposit", "deposit", "withdraw", "withdraw", "transfer", "withdraw", "withdraw", "withdraw", "transfer", "deposit", "transfer", "withdraw", "transfer", "transfer", "transfer", "withdraw", "transfer", "withdraw", "transfer", "deposit", "transfer", "withdraw", "transfer", "withdraw", "deposit", "transfer", "transfer", "withdraw", "transfer", "deposit", "deposit", "transfer", "withdraw", "transfer", "transfer", "deposit", "withdraw", "withdraw", "transfer", "deposit", "deposit", "transfer", "withdraw"},
                new long[][]{{7070, 4948, 770, 1881, 5988, 6120, 4221, 1430, 1809, 3438, 9381, 5440, 5847, 1145, 5751, 1431, 5036, 3461, 5095, 4098, 9157, 3252, 491, 9758, 9726, 1691, 2488, 4390, 2948, 9658, 6823, 9956, 8917, 877, 8233, 5084, 4772, 8928, 8359, 1196, 7864, 9764, 6035, 2528, 5792, 5247, 6134, 4763, 9544, 6948, 8429, 2183, 9358, 7959, 4631, 1487, 1936, 8825, 7822, 2167, 2087, 505, 5703, 6796, 850, 7041, 6712, 6548, 1379, 3357, 3833, 7026, 7354, 3397, 8665, 9995, 9532, 4483, 9994, 9954, 50, 2071, 4131, 7921, 2386, 5226, 2470, 7628, 6232, 4175, 1595, 7684, 5434, 2062, 6864, 4101, 6122, 7620, 7918, 6374, 4660, 1268, 4260, 2406, 1496, 8782, 2690, 7944, 8579, 1494, 5992, 5937, 7341, 8824, 3124, 663, 7036, 8447, 8749, 7618, 5926, 4504, 7069, 1484, 8133, 3965, 8894, 7487, 1786, 932, 4081, 6096, 51, 5822, 2529, 8903, 3052, 2924, 8605, 6922, 8684, 4565, 9170, 7514, 404, 510, 4328, 1322, 6424, 8457, 7456, 136, 4472, 868, 9562, 1527, 4561, 6750, 4247, 8948, 2183, 6956, 8742, 245, 3211, 1612, 793, 9706, 335, 2952, 3663, 347, 1084, 6718, 2500, 1173, 4484, 2908, 2114, 7774, 3777, 1908, 8392, 4356, 4934, 7958, 5475, 3855, 1266, 5037, 4447, 4692, 7497, 9881, 1263, 8126, 5411, 429, 7878, 8199, 6346, 781, 9130, 80, 5329, 8888, 6028, 4460, 5164, 8174, 2374, 3773, 584, 4170, 8063, 4447, 9168, 4967, 2323, 4414, 9896, 9778, 7131, 7791, 1851, 3483, 7543, 5412, 636, 9977, 9870, 585, 5990, 4534, 708, 42, 3624, 4733, 9023, 6220, 2270, 1874, 3001, 7135, 1088, 1678, 5679, 3768, 4340, 2972, 4888, 9809, 2790, 4427, 2064, 3552, 5825, 7431, 2312, 3925, 6487, 6036, 3936, 1196, 9597, 4765, 1248, 9126, 905, 8252, 8300, 9696, 6699, 6293, 6811, 9159, 7804, 7295, 2563, 3028, 3861, 4490, 6009, 6011, 7916, 9028, 9354, 3367, 6038, 8693, 7135, 6025, 7243, 9326, 6842, 701, 4520, 7018, 8162, 9699, 4016, 6568, 386, 494, 274, 2695, 5837, 1278, 227, 6679, 5519, 8855, 2771, 4380, 3369, 4025, 8199, 75, 2803, 8862, 75, 8627, 802, 194, 4303, 4192, 6952, 9012, 9321, 1834, 4823, 2085, 8093, 4029, 9464, 1669, 6564, 3998, 9838, 9617, 8929, 5848, 348, 6949, 5726, 4650, 9693, 1639, 8182, 9924, 9505, 8230, 7299, 4315, 7936, 8073, 2258, 2013, 7189, 5957, 5757, 2439, 4249, 8121, 6268, 3557, 6029, 4110, 5467, 9661, 2788, 9318, 2569, 651, 7504, 3382, 3312, 8642, 9736, 9235, 4095, 5151, 445, 468, 744, 25, 3182, 8242, 180, 1554, 5387, 4529, 2874, 9909, 1577, 929, 263, 7638, 9603, 5360, 4395, 6319, 9897, 5073, 1954, 426, 4667, 943, 3582, 3646, 2727, 6466, 8036, 5248, 6364, 6800, 1615, 6413, 1081, 3640, 1201, 4139, 9323, 8404, 1286, 2514, 1415, 2861, 8853, 8963, 8454, 6507, 4639, 3779, 2639, 2943, 8864, 8737, 491, 9473, 4165, 1927, 3257, 7435, 6843, 5507, 512, 9278, 7776, 1677, 1771, 6602, 6933, 6793, 2826, 6864, 8414, 736, 940, 925, 9004, 3154, 568, 4746, 218, 3138, 7136, 15, 1583, 9683, 8953, 9389, 5504, 9032, 6713, 8125, 3723, 9986, 5799, 4368, 5407, 6649, 4440, 2449, 8929, 3931, 8572, 897, 1183, 8700, 6302, 4220, 7079, 606, 1997, 3320, 3972, 1665, 7040, 5498, 183, 9913, 7578, 7960, 2693, 7350, 2695, 4119, 8687, 9768, 9750, 3194, 8094, 4217, 2685, 517, 5405, 643, 8330, 709, 5094, 1785, 7373, 9959, 9744, 8258, 9817, 9125, 2108, 2399, 2829, 7452, 7959, 7445, 691, 8593, 2607, 9654, 6818, 1229, 9627, 8351, 6534, 2638, 1404, 4351, 5570, 2592, 2449, 6346, 9682, 8385, 3301, 3446, 9827, 3892, 7905, 7993, 9453, 9130, 3539, 1329, 5509, 1480, 3923, 4281, 2023, 2831, 4094, 325, 2525, 298, 4585, 1504, 9083, 8083, 2549, 8935, 6434, 8693, 8390, 8946, 3759, 6957, 1920, 9872, 3330, 2548, 7869, 9895, 4396, 3427, 9233, 3454, 8025, 1223, 645, 2683, 839, 6973, 6312, 3088, 6144, 5787, 4069, 7490, 6861, 8064, 3078, 6062, 29, 8112, 9105, 9435, 6905, 1194, 7444, 2481, 375, 7504, 8987, 5733, 6958, 8267, 8278, 6778, 6425, 566, 2751, 9991, 4960, 5293, 6992, 9922, 7566, 4263, 5549, 1869, 3827, 6242, 9912, 3500, 2993, 89, 2724, 4124, 6707, 9892, 6036, 9428, 1764, 1366, 7572, 7006, 6514, 6895, 5714, 501, 6816, 2610, 8596, 6374, 8250, 1172, 8538, 7761, 4758, 4823, 5837, 4523, 9108, 6070, 9439, 3384, 6145, 1840, 3848, 1181, 9191, 7958, 1016, 4590, 2825, 9447, 6935, 6521, 3495, 1705, 8347, 62, 4941, 9374, 5294, 3799, 3905, 1225, 3139, 4678, 2254, 2912, 4717, 6936, 4152, 2818, 4106, 7612, 9055, 7652, 8776, 2090, 5871, 507, 510, 9518, 103, 4866, 745, 8437, 4663, 8084, 8304, 1019, 6603, 1102, 4566, 9744, 5208, 8751, 4109, 7437, 7145, 6275, 7022, 4829, 855, 9119, 547, 8207, 2321, 481, 9566, 6055, 8689, 370, 1331, 4270, 6673, 7286, 7907, 1837, 5802, 2954, 4798, 345, 8622, 7509, 6777, 2609}, {105, 2521}, {577, 1522, 4860}, {537, 736, 786}, {271, 751, 5424}, {3699, 1787}, {618, 264, 4576}, {359, 9799}, {745, 1872}, {544, 254, 2431}, {105, 506, 8013}, {316, 3418}, {159, 2086}, {7110, 4636}, {163, 750}, {555, 437, 5182}, {574, 9525}, {157, 16, 6920}, {438, 7096}, {73, 110, 5373}, {450, 9750}, {723, 5896}, {234, 663, 4644}, {29, 2422}, {578, 2788}, {36, 79}, {6617, 8457}, {43, 381, 1204}, {717, 1248}, {388, 7963}, {6928, 509, 7737}, {269, 3335}, {418, 4941}, {415, 8507}, {229, 441, 2502}, {576, 4194}, {537, 3530}, {303, 8354}, {300, 4481}, {536, 5215}, {2428, 3520}, {4008, 305}, {100, 3090}, {713, 728, 6266}, {607, 1494}, {5, 108, 6353}, {3088, 317}, {139, 252, 5342}, {577, 586, 8540}, {661, 2707}, {181, 485, 4610}, {551, 669, 1966}, {1366, 3623}, {528, 408}, {346, 544, 8159}, {410, 4866, 5453}, {4628, 8709}, {739, 8710}, {2205, 3037}, {195, 654, 1413}, {216, 310, 5510}, {5331, 5770}, {2024, 7988}, {227, 4206}, {339, 259, 2664}, {294, 9499}, {300, 3357}, {435, 4028}, {297, 6820}, {287, 6946}, {2428, 367, 7941}, {45, 533, 9400}, {5576, 13, 9574}, {162, 488, 4542}, {1212, 1233}, {482, 1242}, {242, 2394}, {120, 823}, {65, 6326}, {160, 9970}, {306, 8084}, {138, 138}, {580, 574, 6087}, {3209, 6697}, {371, 4400}, {410, 5786}, {443, 4679}, {84, 2782}, {302, 106, 3966}, {678, 4355}, {449, 6200}, {172, 55, 2458}, {2002, 517, 8581}, {284, 655, 9687}, {27, 317, 4065}, {631, 5980}, {7392, 200, 8164}, {3569, 3316}, {295, 1383}, {419, 9239}, {711, 5521, 4137}, {684, 6161}, {180, 5872}, {331, 864}, {493, 6746}, {22, 903}, {124, 3401}, {386, 713, 2501}, {624, 2737}, {183, 384, 2133}, {173, 113}, {1741, 8860}, {2683, 6035}, {200, 2087, 4027}, {526, 334, 9027}, {546, 668, 9897}, {263, 473}, {445, 2789}, {535, 3877}, {148, 4441}, {230, 2964}, {594, 4193}, {171, 172, 4597}, {221, 26, 1830}, {76, 1442}, {361, 176, 9552}, {250, 1000}, {649, 682, 1004}, {2315, 2519}, {416, 295, 1823}, {123, 3106, 5544}, {212, 4779}, {757, 4703}, {697, 167, 8776}, {7198, 2348}, {584, 124}, {692, 8668}, {495, 7826}, {647, 1906, 1553}, {895, 468}, {228, 5485}, {174, 401, 4937}, {322, 1594}, {609, 716, 8212}, {415, 7837}, {168, 8504}, {77, 219, 5177}, {127, 8890}, {721, 2164}, {522, 8999}, {412, 8933}, {309, 205, 1855}, {252, 668, 7980}, {140, 4189}, {464, 20, 9853}, {699, 484, 6697}, {740, 169, 4991}, {518, 5520, 5300}, {624, 5403}, {405, 2494}, {7308, 1643}, {209, 459, 3939}, {725, 337, 5402}, {714, 9814}, {453, 3080}, {389, 2620}, {570, 497}, {450, 5995}, {476, 749, 7669}, {4764, 381, 1599}, {708, 8506}, {433, 289, 6414}, {255, 6, 8361}, {584, 201, 8241}, {422, 1375}, {562, 479, 928}, {232, 1642}, {139, 775}, {6438, 8845}, {372, 148, 5504}, {684, 8918}, {6127, 379, 5584}, {41, 3253}, {630, 8987}, {561, 3281}, {235, 262}, {513, 2626}, {28, 356, 7169}, {39, 9406}, {535, 3530}, {230, 3681}, {561, 3269}, {291, 4699}, {278, 6823}, {680, 7782}, {4370, 9209}, {159, 3316}, {721, 9690}, {489, 424, 4622}, {580, 587, 7448}, {288, 9348}, {184, 482, 2772}, {442, 1308}, {441, 157, 7970}, {326, 15, 4755}, {24, 8155}, {470, 2203}, {3771, 650, 3844}, {2900, 201, 6523}, {650, 4571, 6258}, {34, 2888}, {442, 705, 54}, {422, 72}, {91, 138, 6743}, {681, 9547}, {169, 385}, {482, 208, 2682}, {383, 9970}, {433, 516, 413}, {684, 171, 1656}, {718, 67}, {6193, 488, 7165}, {552, 2415}, {172, 79, 3665}, {25, 7459}, {709, 3386}, {752, 223, 6550}, {1667, 3231}, {150, 2522}, {391, 4435}, {283, 356, 3037}, {530, 2749}, {1095, 433, 7743}, {117, 492, 4348}, {27, 3484}, {3369, 52, 5683}, {709, 9325}, {689, 6085}, {6290, 189, 1415}, {102, 3198}, {10, 2009}, {125, 740, 8766}, {244, 185, 5227}, {549, 9273}, {259, 4881}, {489, 1520}, {243, 2585}, {651, 599, 1374}, {243, 2564}, {459, 4245}, {540, 3649}, {308, 724, 1518}, {615, 186}, {2014, 601, 1581}, {689, 9416}, {743, 616, 8179}, {7302, 357, 4397}, {55, 117, 3141}, {5898, 6685}, {663, 515, 2602}, {1, 586}, {251, 19, 1229}, {338, 9584}, {421, 648, 3121}, {103, 6697}, {428, 1139, 1847}, {253, 6165}, {239, 877}, {288, 523, 4221}, {449, 697, 3316}, {51, 8014}, {1118, 667, 4519}, {747, 3908}, {74, 2034}, {226, 511, 1870}, {6030, 8622}, {352, 264, 7412}, {519, 231, 6898}, {676, 3839}, {385, 824}, {271, 1228}, {300, 413, 7271}, {549, 593}, {584, 7050}, {157, 321, 109}, {5771, 6178}});
        system.check(solution1, new Boolean[]{null, true, false, true, true, false, false, false, false, true, false, true, true, false, true, true, false, false, true, true, true, true, false, true, true, true, false, true, false, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, false, false, true, false, true, false, true, false, true, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, true, false, false, false, true, false, true, false, true, false, false, true, true, true, false, false, false, true, true, true, true, true, false, false, false, false, true, false, false, true, false, false, true, true, true, true, true, false, false, true, true, true, false, false, false, false, false, true, true, false, false, true, false, false, true, true, false, true, true, false, true, false, false, true, false, false, true, true, false, false, false, false, true, true, false, true, true, true, true, true, true, false, false, true, true, false, false, false, false, true, true, false, true, false, false, true, true, true, true, true, false, false, false, false, false, true, true, true, true, false, true, true, false, true, true, true, true, true, false, false, true, true, false, true, true, true, false, false, true, false, false, false, true, true, false, false, true, true, false, false, false, true, true, true, false, true, false, true, false, true, true, true, false, true, false, true, true, false, false, true, true, true, true, false, true, true, false, false, true, false, false, true, false, true, true, true, false, true, true, false, true, false, false, true, false, true, true, false, true, false, true, true, true, true, false, false, false, false, true, false, true, true, false, true, true, true, false, true, true, true, false, true, true, true, true, true, false});

    }

    public List Solution(String[] ss, long[][] ls) {
        Bank bank = new Bank(ls[0]);
        List<Boolean> ans = new ArrayList<>();
        ans.add(null);
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "transfer": {
                    ans.add(bank.transfer((int) ls[i][0], (int) ls[i][1], ls[i][2]));
                    break;
                }
                case "deposit": {
                    ans.add(bank.deposit((int) ls[i][0], ls[i][1]));
                    break;
                }
                case "withdraw": {
                    ans.add(bank.withdraw((int) ls[i][0], ls[i][1]));
                    break;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    public void check(List<Boolean> ans1, Boolean[] ans) {
        List<Integer> checkAns = new ArrayList<>();
        System.out.println(ans1.size() + ":" + ans.length);
        try {
            for (int i = 1; i < ans1.size(); i++) {
                if (!ans1.get(i).equals(ans[i])) {
                    checkAns.add(i + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(checkAns);
    }

    class Bank {

        long[] accounts;

        public Bank(long[] balance) {
            accounts = balance;
        }

        boolean check(int account) {
            return 1 <= account && account <= accounts.length;
        }

        //转账
        public boolean transfer(int account1, int account2, long money) {
            if (check(account1) && check(account2)) {
                if (accounts[account1 - 1] >= money) {
                    accounts[account1 - 1] -= money;
                    accounts[account2 - 1] += money;
                    return true;
                }
            }
            return false;
        }

        //存款
        public boolean deposit(int account, long money) {
            if (check(account)) {
                accounts[account - 1] += money;
                return true;
            }
            return false;
        }

        //取款
        public boolean withdraw(int account, long money) {

            if (check(account) && accounts[account - 1] >= money) {
                accounts[account - 1] -= money;
                return true;
            }
            return false;
        }
    }

}


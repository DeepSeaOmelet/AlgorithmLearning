package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2022/10/21
 * 901. 股票价格跨度
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * <p>
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * <p>
 * 示例：
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 * <p>
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 * <p>
 * 提示：
 * 调用 StockSpanner.next(int price) 时，将有 1 <= price <= 10^5。
 * 每个测试用例最多可以调用  10000 次 StockSpanner.next。
 * 在所有测试用例中，最多调用 150000 次 StockSpanner.next。
 * 此问题的总时间限制减少了 50%。
 */
public class OnlineStockSpan {
    public static void main(String[] args) {

    }

}

class StockSpanner {
    Deque<Stock> deque;

    public StockSpanner() {
        deque = new ArrayDeque<>();
    }

    public int next(int price) {
        int day = 1;
        while (!deque.isEmpty()) {
            if (deque.peekLast().price <= price) {
                day += deque.pollLast().day;
            }else break;
        }
        deque.addLast(new Stock(price, day));
        return day;
    }

    class Stock {
        public int price;
        public int day;

        public Stock(int price, int day) {
            this.price = price;
            this.day = day;
        }
    }
}
//class StockSpanner {
//    // 存储每个股票的值和跨度
//    List<int[]> list;
//
//    public StockSpanner() {
//        list = new ArrayList<>();
//    }
//
//    public int next(int price) {
//        //1->2->3
//        int res = list.size() - 1;
//        int count = 1;
//        int[] cur;
//        // 根据每个值的坐标跨度快速统计
//        while (res >= 0) {
//            cur = list.get(res);
//            if (price >= cur[1]) {
//                res -= cur[0];
//                count += cur[0];
//            } else break;
//        }
//        list.add(new int[]{count, price});
//        return count;
//    }
//}

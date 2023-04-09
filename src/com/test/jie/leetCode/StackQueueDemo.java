package com.test.jie.leetCode;

import java.util.*;

public class StackQueueDemo {
    /**
     * 232. 用栈实现队列
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * 实现 MyQueue 类：
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * 说明：
     * <p>
     * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * 示例 1：
     * 输入：
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 1, 1, false]
     * 解释：
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     * 提示：
     * 1 <= x <= 9
     * 最多调用 100 次 push、pop、peek 和 empty
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
     * 进阶：
     * <p>
     * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
     */
    class MyQueue {
        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        public MyQueue() {
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }

        public void push(int x) {
            stackIn.push(x);
        }

        public int pop() {
            if (peek() != -1) {
                return stackOut.pop();
            } else {
                return -1;
            }
        }

        public int peek() {
            if (!stackOut.isEmpty()) {
                return stackOut.peek();
            } else if (stackIn.isEmpty()) {
                return -1;
            }
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
            return stackOut.peek();
        }

        public boolean empty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }
    }

    /**
     * 225. 用队列实现栈
     * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
     * 实现 MyStack 类：
     * void push(int x) 将元素 x 压入栈顶。
     * int pop() 移除并返回栈顶元素。
     * int top() 返回栈顶元素。
     * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     * 注意：
     * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
     * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     * 示例：
     * 输入：
     * ["MyStack", "push", "push", "top", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 2, 2, false]
     * <p>
     * 解释：
     * MyStack myStack = new MyStack();
     * myStack.push(1);
     * myStack.push(2);
     * myStack.top(); // 返回 2
     * myStack.pop(); // 返回 2
     * myStack.empty(); // 返回 False
     * 提示：
     * 1 <= x <= 9
     * 最多调用100 次 push、pop、top 和 empty
     * 每次调用 pop 和 top 都保证栈不为空
     * <p>
     * 进阶：你能否仅用一个队列来实现栈。
     */
    class MyStack {
        Deque<Integer> queue;

        public MyStack() {
            queue = new ArrayDeque<>();
        }

        public void push(int x) {
            queue.addLast(x);
        }

        public int pop() {
            int cur = getLast();
            if (cur == -1) {
                return -1;
            }
            queue.removeFirst();
            return cur;
        }

        public int top() {
            int ans = getLast();
            if (ans == -1) return -1;
            queue.addLast(queue.pollFirst());
            return ans;
        }

        private int getLast() {
            int size = queue.size();
            if (size == 0) {
                return -1;
            }
            while (--size > 0) {
                queue.addLast(queue.pollFirst());
            }
            int ans = queue.peekFirst();
            return ans;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * 输入：s = "(]"
     * 输出：false
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     */
    public boolean isValid(String s) {
        Deque<Character> d = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                d.addLast(')');
            } else if (cs[i] == '{') {
                d.addLast('}');
            } else if (cs[i] == '[') {
                d.addLast(']');
            } else {
                if (d.isEmpty()) {
                    return false;
                }
                char cur = d.pollLast();
                if (cur != cs[i]) {
                    return false;
                }
            }
        }
        return d.isEmpty();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
     * 在 S 上反复执行重复项删除操作，直到无法继续删除。
     * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
     * 示例：
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
     * * 之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     * 提示：
     * 1 <= S.length <= 20000
     * S 仅由小写英文字母组成。
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != cur) {
                sb.append(cur);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     * <p>
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 注意 两个整数之间的除法只保留整数部分。
     * <p>
     * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * 示例 1：
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：该算式转化为常见的中缀算术表达式为：
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * 提示：
     * 1 <= tokens.length <= 104
     * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
     * <p>
     * 逆波兰表达式：
     * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
     * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
     * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
     * 逆波兰表达式主要有以下两个优点：
     * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
     * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            if (t.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (t.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (t.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (t.equals("/")) {
                Integer pop = stack.pop();
                stack.push(stack.pop() / pop);
            } else {
                stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }

    /**
     * 239. 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     * 示例 1：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     * 输入：nums = [1], k = 1
     * 输出：[1]
     * 提示：
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> d = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            //维护一个滑动窗口
            //出，超出滑动窗口范围就清除
            while (!d.isEmpty() && d.peekFirst() < i - k + 1) {
                d.pollFirst();
            }
            //入，维护一个单调队列
            while (!d.isEmpty() && nums[d.peekLast()] < nums[i]) {
                d.pollLast();
            }
            d.addLast(i);
            //结果
            if (!d.isEmpty() && i >= k - 1) {
                res[idx++] = nums[d.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * 提示：
     * 1 <= nums.length <= 105
     * k 的取值范围是 [1, 数组中不相同的元素的个数]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //大顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            //依次从队头弹出k个,就是出现频率前k高的元素
            ans[i]=pq.poll()[0];
        }
        return ans;
    }
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int[] ans = new int[k];
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            list.add(new int[]{key, count});
        }
        Collections.sort(list, (a, b) -> b[1] - a[1]);
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i)[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "-10";
        System.out.println(Integer.parseInt(str));
    }
}

import java.util.LinkedList;
import java.util.Queue;

// 使用队列实现栈
public class QueueStack {

    // 使用 Java 内置的 Queue。Queue 是 Java 中的一个接口，具体实例化它，需要选择一个数据结构
    // 这里选择使用 LinkedList，链表来实现
    private Queue<Integer> queue;
    private int curTop; // 用于追踪记录当前栈顶元素（即队尾元素）

    // 使用队列实现栈
    public QueueStack() {
        queue = new LinkedList<>();
    }

    //===== 队尾作为栈顶 =====//
    public void push1(int x) {
        // 入栈直接使用多列的 add 即可
        // 关键在于出栈，即如何拿到当前队列的最后一个元素
        queue.add(x);
        curTop = x;
    }

    public int pop1() {
        // 由于队列我们只能取出队首元素，而栈需要的是取出栈顶元素
        // 所以要循环取出队列的队首元素，直到队列只剩一个元素，最后那个元素也就是栈顶元素
        // 需要将依次出队的元素保存起来，可以使用一个新的队列进行缓存，在每次 pop 后给 queue 重新赋值
        Queue<Integer> tempQueue = new LinkedList<>();

        while (queue.size() > 1) {
            // 每从 queue 中取出一个元素，都重新给 curTop 赋值
            // curTop 最后存储的就是 queue 中除了队尾元素以外的最后一个元素
            // 即新的栈顶元素
            curTop = queue.peek();
            tempQueue.add(queue.remove());
        }
        int stackTop = queue.remove();
        queue = tempQueue;
        return stackTop;
    }

    public int peek1() {
        // 查看栈顶元素，可以利用实现好的 pop 方法
        // 先取出栈顶，再将栈顶入队，最后取出的栈顶作为返回值即可
//        int ret = pop();
//        push(ret);
//        return ret;
        // 通过当前队尾元素的记录，将 top() 方法复杂度 O(n) 优化为 O(1)
        return curTop;
    }

    //===== 队首作为栈顶 =====//
    public void push2(int x) {
        // 开辟额外的队列用于缓存当前队列
        Queue<Integer> tempQueue = new LinkedList<>();
        tempQueue.add(x);
        while (!queue.isEmpty()) {
            tempQueue.add(queue.remove());
        }
        queue = tempQueue;

        // 不开辟额外的队列，在原队列基础上，实现 push
        queue.add(x);
        // 遍历队列，进行出队再入队，即可在当前队列自己基础上，实现元素的后移（复制）
        for (int i = 0; i < queue.size(); i++) {
            queue.add(queue.remove());
        }
    }

    public int pop2() {
        return queue.remove();
    }

    public int peek2() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

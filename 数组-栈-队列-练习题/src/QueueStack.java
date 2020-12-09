import java.util.LinkedList;
import java.util.Queue;

public class QueueStack {

    // 使用 Java 内置的 Queue。Queue 是 Java 中的一个接口，具体实例化它，需要选择一个数据结构
    // 这里选择使用 LinkedList，链表来实现
    private Queue<Integer> queue;
    private int curTop; // 用于追踪记录当前栈顶元素（即队尾元素）

    // 使用队列实现栈
    public QueueStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        // 入栈直接使用多列的 add 即可
        // 关键在于出栈，即如何拿到当前队列的最后一个元素
        queue.add(x);
        curTop = x;
    }

    public int pop() {
        // 由于队列我们只能取出队首元素，而栈需要的是取出栈顶元素
        // 所以要循环取出队列的队首元素，直到队列只剩一个元素，最后那个元素也就是栈顶元素
        // 需要将依次出队的元素保存起来，可以使用一个新的队列进行缓存，在每次 pop 后给 queue 重新赋值
        Queue<Integer> tempQueue = new LinkedList<>();

        while (queue.size() > 1) {
            // 每从 queue 中取出一个元素，都重新给 top 赋值
            // top 最后存储的就是 queue 中除了队尾元素以外的最后一个元素
            // 即新的栈顶元素
            curTop = queue.peek();
            tempQueue.add(queue.remove());
        }
        int stackTop = queue.remove();
        queue = tempQueue;
        return stackTop;
    }

    public int top() {
        // 查看栈顶元素，可以利用实现好的 pop 方法
        // 先取出栈顶，再将栈顶入队，最后取出的栈顶作为返回值即可
//        int ret = pop();
//        push(ret);
//        return ret;
        // 通过当前队尾元素的记录，将 top() 方法复杂度 O(n) 优化为 O(1)
        return curTop;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

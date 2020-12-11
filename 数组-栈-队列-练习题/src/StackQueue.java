import java.util.Stack;

// 使用栈实现队列
public class StackQueue {
    private Stack<Integer> stack;
    private int curFront; // 用于追踪记录当前队尾元素（即栈顶元素）

    public StackQueue() {
        stack = new Stack<>();
    }

    //===== 栈顶作为队首 =====//
    public void enqueue1(int x) {
        // 开辟额外的栈用于缓存当前栈
        Stack<Integer> tempStack = new Stack<>();

        // 遍历 stack，将其元素复制到 tempStack 中，此时 tempStack 的顺序和 stack 是相反的
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        tempStack.push(x);
        // 再将 tempStack 复制给 stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public int dequeue1() {
        return stack.pop();
    }

    public int front1() {
        return stack.peek();
    }

    //===== 栈顶作为队尾 =====//
    public void enqueue2(int x) {
        if (stack.isEmpty()) {
            // 队首是第一次入栈的元素，之后只要不出栈，则不会改变
            curFront = x;
        }
        stack.push(x);
    }

    public int dequeue2() {
        // 开辟额外的栈用于缓存当前栈
        Stack<Integer> tempStack = new Stack<>();
        // 遍历 stack，将其元素除了第一个元素外，其他复制到 tempStack 中
        while (stack.size() > 1) {
            curFront = stack.peek(); // 维护新的队首
            tempStack.push(stack.pop());
        }
        // 此时 stack 只有最后一个元素，将这个元素作为队尾出队即可
        int ret = stack.pop();
        // 再将 tempStack 复制给 stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        return ret;
    }

    public int front2() {
        return curFront;
    }

    public boolean empty() {
        return stack.isEmpty();
    }

}

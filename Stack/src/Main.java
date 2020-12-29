import java.util.Random;

public class Main {

    // 测试使用 stack 运行 opCount 个 push 和 pop 操作所需要的时间，单位：秒
    private static double testStack(Stack<Integer> stack, int optCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 10000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + "s"); // ArrayStack, time: 3.161739291s

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + "s"); // LinkedListStack, time: 0.014820219s

        // 当 opCount 较小时，LinkedListStack 耗时会更少，因为 ArrayStack 会涉及到扩容、缩容的操作
        // 当 opCount 很大时，LinkedListStack 耗时会更多，因为 LinkedListStack 中包含更多的 new 操作
        // 但两种实现方式在复杂度上是一个级别的
    }
}

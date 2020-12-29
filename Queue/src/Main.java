import java.util.Random;

public class Main {

    // 测试使用 q 运行 opCount 个 enqueue 和 dequeue 操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int optCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 1000000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + "s"); // ArrayQueue, time: 3.161739291s

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + "s"); // LoopQueue, time: 0.014820219s

        // 时间复杂度差别主要在于出队操作，数组队列出栈需要将队首后的所有元素前移，
        // 而循环队列出队不需要移动元素，只需要向前赋值即可，而 testQueue 对于数组队列，其复杂度是 O(n^2)，
        // 对于循环队列，其复杂度是 O(n)，所以两者时间性能相差在 100 倍的数量级

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + "s"); // LoopQueue, time: 0.011571071s

    }
}

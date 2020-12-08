public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front; // 队首索引
    private int tail; // 队尾索引，即下一次元素入队的位置
    private int size;

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1]; // 循环队列会浪费一个空间
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        // 队首和队尾指向同一位置，表示队列为空
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {

        // 判断队列是否已满
        // (tail + 1) % data.length 可以以钟表的循环来理解
        // 每当到 12 点，表示新的循环又开始了
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        return data[front];
    }

    private void resize(int newCapacity) {

        E[] newData = (E[])new Object[newCapacity + 1];
        // 循环队列的遍历方式一，0~size，每次遍历加上 front 的偏移
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        // 循环队列的遍历方式二，front~tail
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            newData[i - front] = data[i];
        }
        data = newData;
        // 重新指定队首和队尾索引
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        // 循环队列的遍历方式一，0~size，每次遍历加上 front 的偏移
        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length]);
            // 判断是否是最后一个元素
            if (i != size - 1) {
                res.append(", ");
            }
        }
        // 循环队列的遍历方式二，front~tail
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            // 判断是否是最后一个元素
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);

            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}

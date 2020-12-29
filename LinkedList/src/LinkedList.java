public class LinkedList<E> {

    // 定义内部类
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 定义虚拟头结点，使对链表的添加元素操作逻辑统一
    // 否则在 index == 0 的位置添加元素和 index != 0 的位置添加元素的逻辑会不一致
    // 但虚拟头结点不纳入 size 的计算，且外部永远都不会访问虚拟头结点
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表的 index 位置添加新的元素 e
    // 在链表中不是一个常用的操作，仅做练习使用：）
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        // 找到待插入节点位置的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // 将新节点插入指定节点
        prev.next = new Node(e, prev.next);
        size++;
    }

    // 在链表头添加新的元素 e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表末尾添加新的元素 e
    public void addLast(E e) {
        add(size, e);
    }

    // 获得链表的第 index 位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第 index 位置元素为 e
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否存在元素 e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e == e) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除 index 位置的元素，返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        // 找到待删除节点之前的节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    // 从链表中删除第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String  toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
          // for 循环遍历写法
//        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
//            res.append(cur + "->");
//        }
        res.append("NULL");

        return res.toString();
    }

}

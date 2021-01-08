import javafx.util.Pair;

// 链表的递归实现
public class LinkedListWithRecursion<E> {
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

    private Node head;
    private int size;

    public LinkedListWithRecursion() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表的 index(0-base) 位置添加新的元素 e
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        head = add(head, index, e);
        size++;
    }

    // 在以 node 为头结点的链表的 index 位置插入元素 e，递归算法
    private Node add(Node node, int index, E e) {
        if (index == 0) return new Node(e, node);
        // index - 1 的原因是：
        // 通过递归遍历链表时，当前的头结点相对初始 index 的偏移量在减小
        // 所以需要相应的 -1，当 index 减至 0 时，表示此时就是真正需要执行插入操作的时机
        node.next = add(node.next, index - 1, e);
        return node;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 获得链表的第 index(0-base) 个位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return get(head, index);
    }

    // 在以 node 为头结点的链表中，找到第 index 个元素，递归算法
    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return get(node.next, index - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第 index(0-base) 个位置的元素为 e
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Update failed. Illegal index.");
        }
        set(head, index, e);
    }

    // 修改以 node 为头结点的链表中，第 index(0-base) 个位置的元素为 e，递归算法
    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return;
        }
        // 当 index 减至 0 时，表示当前的头结点就是需要被执行修改的节点
        set(node.next, index - 1, e);
    }

    // 查找链表中是否存在元素 e
    public boolean contains(E e) {
        return contains(head, e);
    }

    // 在以 node 为头结点的链表中，查找是否存在元素 e，递归算法
    private boolean contains(Node node, E e) {
        if (node == null) return false;
        if (node.e.equals(e)) return true;
        return contains(node.next, e);
    }

    // 从链表中删除 index(0-base) 位置的元素，返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }

    // 从以 node 为头结点的链表中，删除第 index 位置的元素，递归算法
    // 返回值包含两个元素：删除后的链表头结点和删除的值
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) return new Pair<>(node.next, node.e);
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e) {
        if (node == null) return null;
        node.next = removeElement(node.next, e);
        if (node.e.equals(e)) {
            size--;
            return node.next;
        }
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListWithRecursion<Integer> list = new LinkedListWithRecursion<>();
        for(int i = 0 ; i < 10 ; i ++)
            list.addFirst(i);

        while(!list.isEmpty())
            System.out.println("removed " + list.removeLast());
    }
}

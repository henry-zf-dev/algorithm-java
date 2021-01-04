// 删除链表中等于给定值 val 的所有节点。
// 示例:
// 输入: 1->2->6->3->4->5->6, val = 6
// 输出: 1->2->3->4->5

// Definition for singly-linked list.
class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    // 链表节点的构造函数
    // 使用 arr 为参数，创建一个链表，当前的 ListNode 为链表头结点
    // 用于在提交 LeetCode 前，在本地做测试用例
    public ListNode(int [] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}

// 不适用虚拟头结点
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 删除链表开始部分所有需要被删除的节点
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 如果全部都是需要删除的节点，则直接 return
        if (head == null) {
            return null;
        }

        // 删除链表中间需要删除的节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // 删除 prev.next 后不用将指针后移，因为 prev.next.next 节点可能也是需要被删除的节点
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }
}

// 使用虚拟头结点
class SolutionWithDummyHead {
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 删除链表中间需要删除的节点
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // 删除 prev.next 后不用将指针后移，因为 prev.next.next 节点可能也是需要被删除的节点
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new SolutionWithDummyHead()).removeElements(head, 6);
        System.out.println(res);
    }
}



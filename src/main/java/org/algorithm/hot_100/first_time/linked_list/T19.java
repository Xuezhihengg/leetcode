package org.algorithm.hot_100.first_time.linked_list;

//题目: 19.删除链表的倒数第 N个结点

public class T19 {

    //思路: 先找到总节点个数，再找到要删除节点的前驱节点
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        // 第一遍循环找到总节点个数
        ListNode temp = head;
        int count = 0;
        while (temp!=null){
            temp = temp.next;
            count++;
        }

        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        temp = preHead;
        // 第二遍循环找到要删除的节点的前驱节点
        for (int i = 0; i <count-n; i++) {
            temp = temp.next;
        }

        // 删除节点
        temp.next = temp.next.next;

        return preHead.next;
    }

    //思路: 双指针法，先让右指针移动n步，再同时移动左右指针，当右指针的next为null时左指针到达要删除的节点的前驱节点
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode left = preHead;
        ListNode right = preHead;

        // 先让右指针移动n步
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        // 同时移动左右指针，直到右指针的next为null
        while (right.next != null){
            left = left.next;
            right = right.next;
        }

        // 此时左指针到达要删除的节点的前驱节点
        left.next = left.next.next;

        return preHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        int[] nodes = new int[]{1, 2, 3, 4, 5};

        ListNode head = new ListNode(nodes[0]);
        ListNode point = head;

        for (int i = 1; i < nodes.length; i++) {
            point.next = new ListNode(nodes[i]);
            point = point.next;
        }

        T19 t19 = new T19();
        ListNode listNode = t19.removeNthFromEnd2(head, 5);
    }
}

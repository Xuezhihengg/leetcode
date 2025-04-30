package org.algorithm.hot_100.first_time.linked_list;

//题目: 206.反转链表
//思路: 遍历链表，并在访问各节点时修改 next 引用指向

public class T206 {
    public ListNode reverseList(ListNode head) {

        ListNode frontTemp = null;
        ListNode backTemp = head;
        // 循环遍历链表并修改节点的next指针
        while (head != null) {
            backTemp = backTemp.next;
            head.next = frontTemp;
            frontTemp = head;
            head = backTemp;
        }
        return frontTemp;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
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

        T206 t206 = new T206();
        ListNode newHead = t206.reverseList(head);
    }
}

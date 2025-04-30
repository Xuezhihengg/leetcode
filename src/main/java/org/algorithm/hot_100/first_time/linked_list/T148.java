package org.algorithm.hot_100.first_time.linked_list;

//题目: 148. 排序链表
//思路: 递归归并排序，时间复杂度O(nlogn)，空间复杂度O(logn)

//TODO: 进阶->自底向上归并排序，时间复杂度O(nlogn)，空间复杂度O(1)

// !*! 2025/4/21 看了题解会写

public class T148 {

    public ListNode sortList(ListNode head) {

        if(head == null) return null;
        // 如果只有一个节点则直接返回
        if(head.next == null) return head;

        // 找到链表中点（快慢指针）
        ListNode mid = findMid(head);

        ListNode right = mid.next;
        // 切分链表
        mid.next = null;

        // 对两部分各自递归使用归并排序
        ListNode sortedLeft = sortList(head);
        ListNode sortedRight = sortList(right);

        // 将各自有序的两部分合并，使结果有序
        return merge(sortedLeft, sortedRight);
    }

    // 双指针法归并排序
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        // 当其中一个为null时，把另一个剩余的部分直接加入temp尾部
        temp.next = list1 != null ? list1 : list2;

        return dummy.next;
    }

    private ListNode findMid(ListNode head) {

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
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

        int[] nodes = new int[]{3, 2, 4, 6, 5, 1, 7, 8};

        ListNode head = new ListNode(nodes[0]);
        ListNode point = head;

        for (int i = 1; i < nodes.length; i++) {
            point.next = new ListNode(nodes[i]);
            point = point.next;
        }

        T148 t148 = new T148();
        ListNode mid = t148.sortList(head);
    }
}

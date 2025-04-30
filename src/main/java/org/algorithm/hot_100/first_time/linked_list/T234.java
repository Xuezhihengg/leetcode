package org.xjtu_learner.hot_100.first_time.linked_list;

//题目: 234.回文链表
//思路:
//      -通过快慢指针找到中间节点
//      -反转后半部分链表
//      -循环比较

// !*! 2025/4/21 看了题解会，这个题综合两前面做的几题

public class T234 {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 快慢指针找到链表中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 反转后半部分链表
        ListNode reversed = reverseList(slow);

        // 比较反转后的链表与前半部分链表
        while (reversed != null) {
            if (reversed.val != head.val) {
                return false;
            }
            reversed = reversed.next;
            head = head.next;
        }
        return true;
    }

    static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        // 循环遍历节点并修改next
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            // 重置cur与pre
            pre = cur;
            cur = temp;
        }
        return pre;
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
        int[] nodes = new int[]{1, 2, 2, 1};

        ListNode head = new ListNode(nodes[0]);
        ListNode point = head;

        for (int i = 1; i < nodes.length; i++) {
            point.next = new ListNode(nodes[i]);
            point = point.next;
        }

        T234 t234 = new T234();
        System.out.println(t234.isPalindrome(head));
    }
}

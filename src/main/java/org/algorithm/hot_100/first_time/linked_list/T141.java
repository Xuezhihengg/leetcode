package org.xjtu_learner.hot_100.first_time.linked_list;

//题目: 141. 环形链表
//思路: 如果快慢指针相遇说明有环

public class T141 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 如果快慢指针相遇
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}

package org.algorithm.hot_100.first_time.linked_list;

//题目: 142.环形链表2
//思路:
//          -先用快慢指针找到在环中相遇的位置
//          -将快指针置与头节点再次运行，本轮两指针都每次前进一步
//          -本轮两指针相遇处即为环入口节点
//题解: https://leetcode.cn/problems/linked-list-cycle-ii/solutions/12616/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/?envType=study-plan-v2&envId=top-100-liked

public class T142 {

    public ListNode detectCycle(ListNode head) {
        ListNode point1 = hasCycle(head);

        if(point1 == null) return null;

        // 再次循环，将fast再次置到头部，现在快慢指针都每次一步
        ListNode point2 = head;
        while (point2 != point1){
            point2 = point2.next;
            point1 = point1.next;
        }
        // 快慢指针相遇的位置即环的起点
        return point2;
    }

    static ListNode hasCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                // 此时fast与slow都指向相遇的位置
                return fast;
            }
        }
        return null;
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

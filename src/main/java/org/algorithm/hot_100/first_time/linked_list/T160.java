package org.xjtu_learner.hot_100.first_time.linked_list;

//题目: 160.相交链表
//思路: 双指针
//题解: https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/12624/intersection-of-two-linked-lists-shuang-zhi-zhen-l/?envType=study-plan-v2&envId=top-100-liked

public class T160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode point1 = headA;
        ListNode point2 = headB;

        while (point1 != point2) {
            point1 = point1 != null ? point1.next : headB;
            point2 = point2 != null ? point2.next : headA;
        }
        return point1;
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

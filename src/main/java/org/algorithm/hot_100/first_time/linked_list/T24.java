package org.xjtu_learner.hot_100.first_time.linked_list;

//题目: 24.两两交换链表中的节点
//思路: 哑节点下的指针变更操作，这类问题带入哑节点都会好做很多
//题解: https://leetcode.cn/problems/swap-nodes-in-pairs/solutions/444474/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/?envType=study-plan-v2&envId=top-100-liked

public class T24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null && temp.next.next != null){
            ListNode left = temp.next;
            ListNode right = temp.next.next;

            // 交换节点
            temp.next = right;
            left.next = right.next;
            right.next = left;

            // 后移临时节点
            temp = left;
        }
        return dummy.next;
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
        int[] nodes = new int[]{1, 2, 3, 4};

        ListNode head = new ListNode(nodes[0]);
        ListNode point = head;

        for (int i = 1; i < nodes.length; i++) {
            point.next = new ListNode(nodes[i]);
            point = point.next;
        }

        T24 t24 = new T24();
        ListNode listNode = t24.swapPairs(head);
    }
}

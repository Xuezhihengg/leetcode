package org.algorithm.hot_100.first_time.linked_list;

//题目: 21.合并两个有序链表


public class T21 {

    //思路: 将list1合并到list2上，就是简单的指针变更操作
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {

        if (list2 == null) {
            return list1;
        }

        // 全部向list2上合并
        ListNode temp1;
        ListNode temp2;

        while (list1 != null) {
            temp1 = list1.next;
            if (list1.val < list2.val) {
                // 把list1这个节点插到list2的前面
                list1.next = list2;
                list2 = list1;
            } else {
                // 找到比list1小的节点并插到其的后面
                ListNode temp = list2;
                while (temp.next != null && temp.next.val <= list1.val) {
                    temp = temp.next;
                }
                temp2 = temp.next;
                temp.next = list1;
                list1.next = temp2;
            }
            list1 = temp1;
        }
        return list2;
    }

    //思路: 设置哨兵节点，指向两个链表指针中较小者
    //题解: https://leetcode.cn/problems/merge-two-sorted-lists/solutions/226408/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/?envType=study-plan-v2&envId=top-100-liked
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = list1 == null ? list2 : list1;

        return prehead.next;
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
        int[] nodes1 = new int[]{1, 2, 4};
        int[] nodes2 = new int[]{1, 3, 4};

        ListNode list1 = new ListNode(nodes1[0]);
        ListNode point = list1;

        for (int i = 1; i < nodes1.length; i++) {
            point.next = new ListNode(nodes1[i]);
            point = point.next;
        }

        ListNode list2 = new ListNode(nodes2[0]);
        point = list2;

        for (int i = 1; i < nodes2.length; i++) {
            point.next = new ListNode(nodes2[i]);
            point = point.next;
        }


        T21 t21 = new T21();
        ListNode listNode = t21.mergeTwoLists2(list1, list2);
    }
}

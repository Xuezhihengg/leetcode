package org.xjtu_learner.hot_100.first_time.linked_list;

//题目: 23.合并K个升序链表
//思路: 根据T21实现的'合并两个有序链表'，现在只需要两两合并即可，但是这样的时间复杂度是O(nk)
//      可以使用分治法，使得合并更加平衡，时间复杂度为O(nlogk)

public class T23 {

    // 分治
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) return null;
        // 如果只有一个链表则直接返回
        if (lists.length == 1) return lists[0];

        int leftSize = lists.length / 2;
        int rightSize = lists.length - leftSize;
        ListNode[] leftPart = new ListNode[leftSize];
        ListNode[] rightPart = new ListNode[rightSize];

        // 将lists分成两部分
        for (int i = 0; i < lists.length; i++) {
            if (i < leftSize) {
                leftPart[i] = lists[i];
            } else {
                rightPart[i - leftSize] = lists[i];
            }
        }

        ListNode mergedLeft = mergeKLists(leftPart);
        ListNode mergedRight = mergeKLists(rightPart);

        return merge(mergedLeft, mergedRight);
    }

    static ListNode merge(ListNode list1, ListNode list2) {
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

        temp.next = list1 != null ? list1 : list2;
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
        int[][] nodes = new int[][]{
        };

        ListNode[] lists = new ListNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            ListNode dummy = new ListNode(-1);
            ListNode temp = dummy;
            for (int j = 0; j < nodes[i].length; j++) {
                temp.next = new ListNode(nodes[i][j]);
                temp = temp.next;
            }
            lists[i] = dummy.next;
        }

        T23 t23 = new T23();
        ListNode listNode = t23.mergeKLists(lists);
    }
}

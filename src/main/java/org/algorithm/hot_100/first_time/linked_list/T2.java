package org.algorithm.hot_100.first_time.linked_list;

//题目: 2. 两数相加

public class T2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode cur = preHead;

        boolean carry = false;
        while (l1 != null || l2 != null) {

            int val1 = 0;
            if (l1 != null) {
                val1 = l1.val;
            }
            int val2 = 0;
            if (l2 != null) {
                val2 = l2.val;
            }

            int sum = val1 + val2;
            if (carry) {
                sum++;
                carry = false;
            }

            if (sum < 10) {
                cur.next = new ListNode(sum);
                cur = cur.next;
            } else {
                cur.next = new ListNode(sum - 10);
                cur = cur.next;
                carry = true;
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // 如果还有进位则补1
        if(carry){
            cur.next = new ListNode(1);
        }

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
        int[] nodes1 = new int[]{9, 9, 9, 9, 9, 9, 9};
        int[] nodes2 = new int[]{9, 9, 9, 9};

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

        T2 t2 = new T2();
        ListNode listNode = t2.addTwoNumbers(list1, list2);
    }
}

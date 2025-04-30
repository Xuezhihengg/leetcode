package org.algorithm.hot_100.first_time.linked_list;

//题目: 25.K个一组翻转链表
//思路: 还是引入哑节点，然后考虑清楚每个循环做什么事情，指针变更步骤是怎样的

// !*! 2025/4/21 不太会

public class T25 {

    public ListNode reverseKGroup(ListNode head, int k) {

        if (k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;

        // 外循环每次处理一组
        while (true) {
            // 如果该组不完整则退出
            ListNode preTemp = temp;
            boolean flag = false;
            for (int i = 0; i < k; i++) {
                preTemp = preTemp.next;
                if(preTemp == null) {
                    flag = true;
                    break;
                }
            }

            if(flag) break;

            preTemp = preTemp.next;

            ListNode cur = temp.next;
            for (int i = 0; i < k; i++) {
                ListNode curTemp = cur.next;
                cur.next = preTemp;
                // 更新cur和temp
                preTemp = cur;
                cur = curTemp;
            }
            // 将temp的next指向反转后的链表头
            temp.next = preTemp;

            // 更新temp
            for (int i = 0; i < k-1; i++) {
                preTemp = preTemp.next;
            }
            temp = preTemp;
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
        int[] nodes = new int[]{1, 2, 3, 4, 5};

        ListNode head = new ListNode(nodes[0]);
        ListNode point = head;

        for (int i = 1; i < nodes.length; i++) {
            point.next = new ListNode(nodes[i]);
            point = point.next;
        }

        T25 t25 = new T25();
        ListNode listNode = t25.reverseKGroup(head, 3);
    }
}

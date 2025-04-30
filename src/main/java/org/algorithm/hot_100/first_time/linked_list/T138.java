package org.algorithm.hot_100.first_time.linked_list;

import java.util.HashMap;
import java.util.Map;

//题目: 138. 随机链表的复制
//思路: 先不考虑random指针的拷贝，并把原节点到新节点的拷贝存入map，然后再设置random值

public class T138 {

    public Node copyRandomList(Node head) {

        Map<Node, Node> map = new HashMap<>();
        // 先不考虑random指针的拷贝，并把原节点到新节点的拷贝存入map
        Node node = cloneNode(head, map);

        // 设置random值
        while (head != null){
            Node random = head.random;

            Node clone = map.get(head);
            clone.random = map.get(random);

            head = head.next;
        }

        return node;
    }

    Node cloneNode(Node head, Map<Node, Node> map){
        if(head == null){
            return null;
        }
        Node clone = new Node(head.val);
        clone.next = cloneNode(head.next, map);
        map.put(head, clone);
        return clone;
    }


    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {

        Node node0 = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = node0;
        node2.random = node4;
        node3.random = node2;
        node4.random = node0;

        T138 t138 = new T138();
        Node node = t138.copyRandomList(node0);
    }
}

package org.xjtu_learner.hot_100.first_time.linked_list;

import java.util.HashMap;
import java.util.Map;

//题目: 146.LRU 缓存
//思路: 哈希表+双向链表，双向链表越靠近头部即越新的缓存，当插入缓存或者查询时，将其放在或移动到链表头部
//     当容量满了后将链表尾部元素移除
//题解: https://leetcode.cn/problems/lru-cache/solutions/259678/lruhuan-cun-ji-zhi-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/21 对于双向链表实现不熟悉，不清楚这个双向链表需要实现什么方法
public class T146 {

    static class LRUCache {
        int size;
        int capacity;
        LinkedList linkedList;
        Map<Integer, Node> cache;


        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.linkedList = new LinkedList();
            this.cache = new HashMap<>();
        }

        public int get(int key) {
            // 先通过map查看是否存在key
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 通过map找到对应Node，并将其移动到链表头部
            linkedList.moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            Node cached = cache.get(key);
            // 如果键已存在，则移动到链表首部
            if (cached != null) {
                cached.value = value;
                linkedList.moveToHead(cached);
                return;
            }

            // 容量达到上限，删除链表尾部节点
            if (capacity <= size) {
                Node tail = linkedList.removeTail();
                cache.remove(tail.key);
                size--;
            }

            Node node = new Node(key, value);
            cache.put(key, node);
            // 把节点加入到链表头部
            linkedList.addToHead(node);
            size++;
        }

        // 双向链表
        static class LinkedList {
            Node head;
            Node tail;

            public LinkedList() {
                this.head = new Node();
                this.tail = new Node();
                head.next = tail;
                tail.prev = head;
            }

            public void addToHead(Node newNode) {
                newNode.prev = head;
                newNode.next = head.next;
                newNode.next.prev = newNode;
                head.next = newNode;
            }

            public void moveToHead(Node node) {
                removeNode(node);
                addToHead(node);
            }

            public Node removeTail() {
                Node temp = tail.prev;
                removeNode(temp);
                return temp;
            }

            public void removeNode(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }


        static class Node {
            int key;
            int value;
            Node prev = null;
            Node next = null;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
}

package org.algorithm.hot_100.first_time.graph;

//题目: 208. 实现 Trie（前缀树）
//思路: Trie树是一颗多叉树，每个节点有26个子节点
//题解: https://leetcode.cn/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/24 初次接触Trie树，值得重写

public class T208 {

    static class Trie {

        private final Node root;

        public Trie() {
            // 初始化根节点
            root = new Node();
        }

        public void insert(String word) {

            Node temp = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                // 查找next的对应的Node，如果有则走到这个Node，如果没有则新建
                if (temp.next[index] == null) {
                    temp.next[index] = new Node();
                }
                temp = temp.next[index];
            }
            // 走到最后一个，将其isEnd置为true
            temp.isEnd = true;
        }

        public boolean search(String word) {

            Node temp = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (temp.next[index] == null) return false;
                temp = temp.next[index];
            }

            return temp.isEnd;
        }

        public boolean startsWith(String prefix) {

            Node temp = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (temp.next[index] == null) return false;
                temp = temp.next[index];
            }

            return true;
        }

        private static class Node {
            boolean isEnd = false;
            Node[] next = new Node[26]; // 指向下一个26个字母
        }
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

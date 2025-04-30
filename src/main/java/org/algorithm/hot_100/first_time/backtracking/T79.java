package org.xjtu_learner.hot_100.first_time.backtracking;

//题目: 79.单词搜索
//思路: 网格深度优先搜索

public class T79 {

    public boolean exist(char[][] board, String word) {

        boolean[][] used = new boolean[board.length][board[0].length];

        char[] target = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == target[0]) {
                    boolean find = dfs(board, used, i, j, 0, target);
                    if(find){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, boolean[][] used, int r, int c, int index, char[] target) {

        if (index == target.length) {
            return true;
        }

        if (!inArea(board, r, c)) {
            return false;
        }

        if (used[r][c]) {
            return false;
        }

        if (board[r][c] != target[index]) {
            return false;
        }

        // 当前格子是正确的，向四个方向搜索
        used[r][c] = true;
        boolean upFind = dfs(board, used, r - 1, c, index + 1, target);
        boolean downFind = dfs(board, used, r + 1, c, index + 1, target);
        boolean leftFind = dfs(board, used, r, c - 1, index + 1, target);
        boolean rightFind = dfs(board, used, r, c + 1, index + 1, target);

        boolean find = upFind || downFind || leftFind || rightFind;

        if (!find) {
            used[r][c] = false;
        }
        return find;
    }

    boolean inArea(char[][] board, int r, int c) {
        return 0 <= r && r < board.length
                && 0 <= c && c < board[0].length;
    }

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                {'a'},
        };


        T79 t79 = new T79();
        boolean exist = t79.exist(chars, "a");
    }
}

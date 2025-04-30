package org.algorithm.interview.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] initial = new int[n];
        for (int i = 0; i < n; i++) {
            initial[i] = scanner.nextInt();
        }

        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[i] = scanner.nextInt();
        }
        System.out.println(min(initial, target));
        scanner.close();
    }

    static int min(int[] initial, int[] target) {
        int n = initial.length;
        int[] curState = Arrays.copyOf(initial, n);
        int count = 0;

        // 递归从根节点开始处理
        count = dfs(0, curState, target);

        return count;
    }

    static int dfs(int node, int[] curState, int[] target) {

        // 基本情况
        if (node >= curState.length || curState[node] == 0) {
            return 0;
        }

        int switchCount = 0;
        int curColor = curState[node];
        int switchNeeded = 0;
        int tempColor = curColor;
        while (tempColor != target[node]) {
            tempColor = (tempColor % 3) + 1;
            switchNeeded++;
        }
        if (switchNeeded > 0) {
            // 更新节点
            switchTree(node, switchNeeded, curState);
            switchCount += switchNeeded;
        }

        int leftChild = 2 * node + 1;
        if (leftChild < curState.length && curState[leftChild] != 0) {
            switchCount += dfs(leftChild, curState, target);
        }

        int rightChild = 2 * node + 2;
        if (rightChild < curState.length && curState[rightChild] != 0) {
            switchCount += dfs(rightChild, curState, target);
        }
        return switchCount;
    }


    static void switchTree(int node, int switches, int[] state) {
        if (node >= state.length || state[node] == 0) {
            return;
        }

        // 更新当前节点的颜色
        for (int i = 0; i < switches; i++) {
            state[node] = (state[node] % 3) + 1;
        }

        // 更新左子树
        switchTree(2 * node + 1, switches, state);

        // 更新右子树
        switchTree(2 * node + 2, switches, state);
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String[] board = new String[10];
        for(int i = 0; i < 10; i++){
            String s = br.readLine().replace(" ", "");
            board[i] = s;
        }

        Solution s = new Solution();
        System.out.println(s.solution(board));
    }
}

class Solution {
    public int solution(String[] board) {
        return solve(board);
    }

    private int solve(String[] board){
        boolean[][] booleanBoard = new boolean[10][10];
        for(int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                booleanBoard[i][k] = board[i].charAt(k) == '0' ? false : true;
            }
        }
        int[] remainPaper = {0, 5, 5, 5, 5, 5};

        int count = dfs(10, booleanBoard, remainPaper, 0);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private int dfs(int n, boolean[][] board, int[] remainPaper, int count){
        int y = -1;
        int x = -1;
        for(int i = 0; i < 10; i++){
            boolean flag = false;
            for(int k = 0; k < 10; k++){
                if(board[i][k]){
                    y = i;
                    x = k;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        //System.out.println(y + " " + x);
        if(y == -1){
            return count;
        }

        int ret = Integer.MAX_VALUE;

        for(int i = 1; i <= 5; i++){
            if(canReverseBoard(y, x, board, i)){
                if(remainPaper[i] > 0){
                    remainPaper[i] -= 1;
                    reverseBoard(y, x, board, i);
                    ret = Math.min(dfs(n, board, remainPaper, count + 1), ret);
                    remainPaper[i] += 1;
                    reverseBoard(y, x, board, i);
                }
            }
        }

        return ret;
    }

    private boolean canReverseBoard(int y, int x, boolean[][] board, int size){
        for(int i = y; i < y + size; i++){
            for(int k = x; k < x + size; k++){
                if(i <= 9 && k <= 9) {
                    if(!board[i][k]){
                        return false;
                    }

                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean reverseBoard(int y, int x, boolean[][] board, int size){
        for(int i = y; i < y + size; i++){
            for(int k = x; k < x + size; k++){
                board[i][k] = !board[i][k];
            }
        }

        return true;
    }
}

// 백트래킹
// 순회하다 1을 찾아, 색종이 차례대로 넣어봐
// 다 순회해보고 더 이상 1을 찾을 수 없다면 return
// 어떤 색종이도 넣을 수 없다면 return max_value

// https://programmers.co.kr/learn/courses/30/lessons/87694
// 문제를 해결하기 위해 좌표값을 2배 해야했던 문제 결국 못풀었지만 상당히 재밌는 문제였다.

import java.util.*;

class Solution {
    private int[][] board = new int[104][104];
    private int[] dx = {1, 0, -1, 0, 1, -1, -1, 1};
    private int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        initBoard(rectangle);

        Queue<Pair> q = new LinkedList<>();

        q.add(Pair.of(characterY * 2, characterX * 2, 0));
        board[characterY * 2][characterX * 2] = 2;

        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            int cost = q.peek().cost;
            q.poll();
            
            if(x == itemX * 2 && y == itemY * 2){
                answer = cost; break;
            }

            for(int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(0 <= ny && ny < 104 && 0 <= nx && nx < 104){
                    if(board[ny][nx] == 1){
                        for(int k = 0; k < 8; k++){
                            int ny2 = ny + dy[k];
                            int nx2 = nx + dx[k];
                            
                            if(board[ny2][nx2] == 0){
                                board[ny][nx] = -1;
                                q.add(Pair.of(ny, nx, cost+1));
                                break;
                            }
                        }
                    }
                }
            }
        }

        return answer/2;
    }

    private void initBoard(int[][] rectangle) {
        for (int i = 0; i < rectangle.length; i++) {
            for (int y = rectangle[i][1] * 2; y <= rectangle[i][3] * 2; y++) {
                for (int x = rectangle[i][0] * 2; x <= rectangle[i][2] * 2; x++) {
                    board[y][x] = 1;
                }
            }
        }
    }
}
    

class Pair{
    int x, y;
    int cost = 0;
    public Pair(int y, int x, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    Pair(){}

    public static Pair of(int y, int x, int cost){
        return new Pair(y, x, cost);
    }
}
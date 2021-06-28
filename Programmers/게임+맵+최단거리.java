//https://programmers.co.kr/learn/courses/30/lessons/1844

import java.util.*;

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int answer = 0;

        Queue<Pair> q = new LinkedList<>();

        Integer[][] checked = new Integer[maps.length][maps[0].length];

        for(int i = 0; i < maps.length; i++){
            for(int k = 0; k < maps[0].length; k++) checked[i][k] = -1;
        }

        q.add(new Pair(0, 0));
        checked[0][0] = 1;

        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(0 <= nx && nx < maps[0].length && 0 <= ny && ny < maps.length){

                    if(maps[ny][nx] == 1 && checked[ny][nx] == -1){
                        checked[ny][nx] = checked[y][x] + 1;
                        q.add(new Pair(ny, nx));
                    }
                }
            }
        }
        answer = checked[maps.length - 1][maps[0].length - 1];
        return answer;
    }
}

class Pair{
    public int x;
    public int y;
    
    public Pair(int y, int x){
        this.x = x;
        this.y = y;
    }
}
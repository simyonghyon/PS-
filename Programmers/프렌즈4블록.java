import java.util.*;

class Solution {

    private int[] dx = {1, 0, 1};
    private int[] dy = {0, 1, 1};

    public int solution(int n, int m, String[] board) {
        int answer = 0;

        

        while(true){
            
            boolean[][] check = new boolean[n][m];
            
            for(int i = 0; i < n - 1; i++){
                for(int k = 0; k < m - 1; k++){
                    if(board[i].charAt(k) != '*') bfs(i, k, board, check);
                }
            }

            int count = 0;
            char[][] tmp = new char[n][m];
            
            for(int i = 0; i < n; i++){
                
                for(int k = 0; k < m; k++){
                    if(check[i][k] == true){
                        count++;
                        tmp[i][k] = '*';
                    } else {
                        tmp[i][k] = board[i].charAt(k);
                    }
                }
            }
            
            if(count == 0) break;
            answer += count;
            
            for(int i = n - 1; i >= 0; i--){
                for(int k = 0; k < m; k++){
                    if(tmp[i][k] == '*'){
                        int index = i - 1;
                        while(index >= 0 && tmp[index][k] == '*'){
                            index--;
                        }
                        
                        if(index == -1) continue;
                        
                        tmp[i][k] = tmp[index][k];
                        tmp[index][k] = '*';
                    }
                }
            }
            
            for(int i = 0; i < n; i++){
                String s = "";
                
                for(int k = 0; k < m; k++){
                    s += String.valueOf(tmp[i][k]);
                }
                
                board[i] = s;
            }
        }
        
        return answer;
    }

    private void bfs(int y, int x, String[] board, boolean[][] check){
        char c = board[y].charAt(x);
        boolean flag = false;
        
        for(int i = 0; i < 3; i++){
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if(board[ny].charAt(nx) != c) {
                flag = true;
                break;
            }
        }

        if(!flag) check[y][x] = check[y][x + 1] = check[y + 1][x] = check[y + 1][x + 1] = true;
    }
}
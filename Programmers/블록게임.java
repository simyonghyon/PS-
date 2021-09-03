//https://programmers.co.kr/learn/courses/30/lessons/42894?language=java#

class Solution {
    int[][] dx = {
        {0, 0, 1, 2},
        {0, 0, 0, -1},
        {0, 0, 0, 1},
        {0, 0, -1, -2},
        {0, 0, 1, -1}
    };
    int[][] dy = {
        {0, 1, 1, 1},
        {0, 1, 2, 2},
        {0, 1, 2, 2},
        {0, 1, 1, 1},
        {0, 1, 1, 1}
    };
    
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        
        while(true){
            boolean flag = false;
        for(int i = 0; i < n; i++){
            for(int k = 0; k < board[0].length; k++){
                if(board[i][k] != 0){
                    int block = getBlockType(board, i, k);
                    if(block == -1) continue;
                    
                    if(isRemovable(board, i, k, block)) {
                        remove(board, i, k, block);
                        answer++;
                        flag = true;
                    }
                }
            }
        }
            if(!flag) break;
        }
        
        
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                System.out.print(board[i][k]);
            }
            System.out.println();
        }
        
        return answer;
    }
    
    private boolean isRemovable(int[][] board, int y, int x, int z){
        if(z == 0){
            if(check(board, y, x + 1) && check(board, y, x + 2)){
                return true;
            }
        } else if(z == 1){
            if(check(board, y + 1, x - 1)){
                return true;
            }
        } else if(z == 2){
            if(check(board, y + 1, x + 1)){
                return true;
            }
        } else if(z == 3){
            if(check(board, y, x - 1) && check(board, y, x - 2)){
                return true;
            }
        } else if(z == 4){
            if(check(board, y, x - 1) && check(board, y, x + 1)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean check(int[][] board, int y, int x){
        for(int i = 0; i <= y; i++){
            if(board[i][x] != 0) return false;
        }
        return true;
    }
    
    private void remove(int[][] board, int y, int x, int z){
        for(int i = 0; i < 4; i++){
            int nx = dx[z][i] + x;
            int ny = dy[z][i] + y;
            
            board[ny][nx] = 0;
        }
    }

    
    private int getBlockType(int[][] board, int y, int x){
        for(int i = 0; i < 5; i++){
            boolean flag = false;
            
            for(int k = 0; k < 4; k++){
                int nx = dx[i][k] + x;
                int ny = dy[i][k] + y;
                
                if(!isValid(board, nx, ny, board[y][x])){
                    flag = true; break;
                }
            }
            
            if(!flag) return i;
        }
        
        return -1;
    }
    
    private boolean isValid(int[][] board, int x, int y, int z){
        if(0 <= x && x < board[0].length && 0 <= y && y < board.length){
            if(board[y][x] == z) return true;
        }
        return false;
    }
}
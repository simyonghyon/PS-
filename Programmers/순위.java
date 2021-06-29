//https://programmers.co.kr/learn/courses/30/lessons/49191

// 다음은 내가 생각한 풀이이고 
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        char[][] board = new char[n][n];
        
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                board[i][k] = 'n';
                if(i == k) board[i][k] = 'x';
            }
        }
        
        for(var k : results){
            int w = k[0] - 1;
            int l = k[1] - 1;
            
            board[w][l] = 'w';
            board[l][w] = 'l';
            
            check(board, w, l, n);
        }
        
        answer = n;
        for(var i : board){
            for(var k : i){
                if(k == 'n'){
                    answer--;
                    break;
                }
            }
        }
        
        for(var i : board){
            for(var k : i){
                System.out.print(k + " ");
            }
            System.out.println();
        }
        
        return answer;
    }
    
    private void check(char[][] board, int w, int l, int n){
        for(int i = 0; i < n; i++){
            if(board[l][i] == 'w' && board[w][i] == 'n'){
                board[w][i] = 'w';
                board[i][w] = 'l';
                check(board, w, i, n);
            }
            if(board[w][i] == 'l' && board[l][i] == 'n'){
                board[l][i] = 'l';
                board[i][l] = 'w';
                check(board, i, l, n);
            }
        }
    }
}

// 이것은 플로이드 와샬을 이용한 풀이이다
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        char[][] board = new char[n][n];
        
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                board[i][k] = 'n';
                if(i == k) board[i][k] = 'x';
            }
        }
        
        
        
        for(var k : results){
            int w = k[0] - 1;
            int l = k[1] - 1;
            
            board[w][l] = 'w';
            board[l][w] = 'l';
        }
        
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                for(int j = 0; j < n; j++){
                    if(board[k][j] == 'n'){
                        if(board[k][i] == 'w' && board[i][j] == 'w') {
                            board[k][j] = 'w';
                            board[j][k] = 'l';
                        }
                        if(board[k][i] == 'l' && board[i][j] == 'l'){
                            board[k][j] = 'l';
                            board[j][k] = 'w';
                        }
                    }
                }
            }
        }
        
        answer = n;
        for(var i : board){
            for(var k : i){
                if(k == 'n'){
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
}
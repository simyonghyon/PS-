class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] board = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < columns; k++){
                board[i][k] = i * columns + k + 1;
            }
        }

        for(int i = 0; i < queries.length; i++){
            int ans = rotation(rows, columns, queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1, board);
            answer[i] = ans;
            //print(board);
        }

        return answer;
    }

    private int rotation(int rows, int columns, int y1, int x1, int y2, int x2, int[][] board){

        int start = 100001;

        int end = board[y1][x2];
        for(int i = x2; i > x1; i--){
            board[y1][i] = board[y1][i - 1];
        }
        start = end;
        //print(board);

        end = board[y2][x2];
        for(int i = y2; i > y1 + 1; i--){
            board[i][x2] = board[i - 1][x2];
        }
        board[y1 + 1][x2] = start;
        start = end;
        //print(board);

        end = board[y2][x1];
        for(int i = x1; i < x2 - 1; i++){
            board[y2][i] = board[y2][i + 1];
        }
        board[y2][x2 - 1] = start;
        start = end;
        //print(board);

        for(int i = y1; i < y2 - 1; i++){
            board[i][x1] = board[i + 1][x1];
        }
        board[y2 - 1][x1] = start;
        //print(board);
        int ans = getMin(board, y1, x1, y2, x2);
        return ans;
    }

    static void print(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[i].length; k++){
                System.out.print(board[i][k] + " ");
            }
            System.out.println();
        }
    }

    static int getMin(int[][] board, int y1, int x1, int y2, int x2){
        int ans = 100001;

        for(int i = x1; i <= x2; i++){
            ans = min(ans, board[y1][i]);
            ans = min(ans, board[y2][i]);
        }

        for(int i = y1; i <= y2; i++){
            ans = min(ans, board[i][x1]);
            ans = min(ans, board[i][x2]);
        }

        return ans;
    }
}
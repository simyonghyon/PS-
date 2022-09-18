class Solution {
    int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dx = {1, 0, -1, 0, 1, -1, -1, 1};
    final String MINE = "M";
    final String EMPTY = "B";
    boolean[][] check;

    public String[] solution(String[] board, int y, int x) {
        check = new boolean[board.length][board[0].length()];
        String[][] ans = new String[board.length][board[0].length()];
        for(int i = 0; i < board.length; i++) {
            for(int k = 0; k < board[0].length(); k++) {
                ans[i][k] = "E";
            }
        }
        openBoard(board, ans, y, x);

        for(int i = 0; i 
        < ans.length; i++) {
            for(int k = 0; k < ans[0].length; k++) {
                System.out.print(ans[i][k]);
            }
            System.out.println();
        }

        String[] answer = new String[board.length];

        for(int i = 0; i < answer.length; i++) {
            StringBuilder sb = new StringBuilder("");
            for(int k = 0; k < ans[0].length; k++) {
                sb.append(ans[i][k]);
            }
            answer[i] = sb.toString();
        }

        return answer;
    }

    private void openBoard(String[] board, String[][] ans, int y, int x) {
        if(board[y].charAt(x) == 'M') {
            for(int i = 0; i < board.length; i++) {
                for(int k = 0;k < board[0].length(); k++) {
                    ans[i][k] = board[i].charAt(k) == 'M' ? MINE : "E";
                }
            }
            ans[y][x] = "X";
            return;
        }

        int mineCount = countMine(board, y, x);

        if(mineCount == 0) {
            for(int i = 0; i < 8; i++) {
                int ny = dy[i] + y;
                int nx = dx[i] + x;

                if(isNotOutOfBoard(ny, nx, board) && !check[ny][nx]) {
                    ans[y][x] = EMPTY;
                    check[ny][nx] = true;
                    openBoard(board, ans, ny, nx);
                }
            }
        } else {
            ans[y][x] = String.valueOf(mineCount);
        }
    }

    private int countMine(String[] board, int y, int x) {
        int mineCount = 0;
        for(int i = 0; i < 8; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if(isNotOutOfBoard(ny, nx, board)) {
                mineCount += board[ny].charAt(nx) == 'M' ? 1 : 0;
            }
        }
        return mineCount;
    }

    private boolean isNotOutOfBoard(int y, int x, String[] board) {
        return 0 <= y && y < board.length && 0 <= x && x < board[0].length();
    }
}
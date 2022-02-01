//https://www.acmicpc.net/problem/2239

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;



public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);

//        st = new StringTokenizer(br.readLine());
//        int t = Integer.parseInt(st.nextToken());

        Solve solve = new Solve();
        int t = 1;

        while(t > 0){
            solve.solve();
            t--;
        }
    }


}

class Solve{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    boolean[][] rowCheck = new boolean[9][10];
    boolean[][] columnCheck = new boolean[9][10];
    boolean[][] areaCheck = new boolean[9][10];

    public void solve() throws IOException {

        int[][] board = new int[9][9];

        for(int i = 0; i < 9; i++){
            String s = br.readLine();
            for(int k = 0; k < 9; k++){
                board[i][k] = Integer.parseInt(s.substring(k, k+1));
                rowCheck[i][board[i][k]] = columnCheck[k][board[i][k]] = areaCheck[getArea(i, k)][board[i][k]] = true;
            }
        }

        if(recursive(board, 0, 0)){
            for(int i = 0 ; i < 9; i++){
                for(int k = 0; k < 9; k++){
                    System.out.print(board[i][k]);
                }
                System.out.println();
            }
        }
    }

    private boolean recursive(int[][] board, int y, int x) {

        if (y == 9) return true;
        if (x == 9) return recursive(board, y + 1, 0);
        if (board[y][x] != 0) return recursive(board, y, x + 1);

        for (int k = 1; k <= 9; k++) {
            if (!rowCheck[y][k] && !columnCheck[x][k] && !areaCheck[getArea(y, x)][k]) {
                rowCheck[y][k] = columnCheck[x][k] = areaCheck[getArea(y, x)][k] = true;
                board[y][x] = k;
                //System.out.println(y + " " + board[y][x] + " " + x);
                if (recursive(board, y, x + 1)) return true;
                rowCheck[y][k] = columnCheck[x][k] = areaCheck[getArea(y, x)][k] = false;
                board[y][x] = 0;
            }
        }
        
        return false;
    }

    private int getArea(int y, int x){
        return (y / 3) * 3 + x / 3;
    }
}
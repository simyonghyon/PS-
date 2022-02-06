//https://www.acmicpc.net/problem/11049
// 재귀적으로 생각하니 생각보다 풀기 쉬웠다, 나름 재밌었던 문제

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


class Solve {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    private int n;


    public void solve() throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());

        int[][] matrix = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            matrix[i][0] = Integer.valueOf(st.nextToken());
            matrix[i][1] = Integer.valueOf(st.nextToken());
        }

        Node[][] dp = new Node[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = new Node(matrix[i], 0);
        }

        System.out.println(calcMinMatrix(0, n - 1, dp, matrix).getMinValue());
    }

    private Node calcMinMatrix(int start, int end, Node[][] dp, int[][] matrix){
        if(dp[start][end] != null) return dp[start][end];

        int min = Integer.MAX_VALUE;
        int[] mat = new int[2];

        for(int i = start; i < end; i++){
            Node left = calcMinMatrix(start, i, dp, matrix);
            Node right = calcMinMatrix(i + 1, end, dp, matrix);

//            System.out.println(left.toString());
//            System.out.println(right.toString());

            int sum = left.getMinValue() + right.getMinValue() + left.calcMatrix(right.getMatrix());

            if(sum < min) {
                min = sum;
                mat[0] = left.getMatrix()[0];
                mat[1] = right.getMatrix()[1];
            }
        }

        dp[start][end] = new Node(mat, min);
        return dp[start][end];
    }
}
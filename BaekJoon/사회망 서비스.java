// https://www.acmicpc.net/problem/2533

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);

        int t = 1;

        //t = Integer.parseInt(br.readLine());

        Solve solve = new Solve();

        while(t > 0){
            solve.solve();
            t--;
        }
    }

}


class Solve<T> {

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    static BufferedReader br = Main.br;
    static BufferedWriter bw = Main.bw;
    static StringTokenizer st;

    private int n;
    private int m;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        //System.out.println(st.nextToken() + " " + st.nextToken());
        n = Integer.valueOf(st.nextToken());

        ArrayList<ArrayList<Integer>> relation = new ArrayList<>(n + 1);

        for(int i = 0; i <= n; i++){
            relation.add(new ArrayList<>());
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.valueOf(st.nextToken());
            int node2 = Integer.valueOf(st.nextToken());

            relation.get(node1).add(node2);
            relation.get(node2).add(node1);
        }

        int[][] dp = new int[n + 1][2];

        dfs(relation, 1, -1, dp);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // 본인이 얼리어답터인 경우 0, 와 그렇지 않은 경우 1 dp[node][1] = dp[자식][0] + dp[자식][0], dp[node][0] = min(dp[자식들][0], dp[자식들][1]
    private void dfs(ArrayList<ArrayList<Integer>> relation, int node, int parent, int[][] dp){

        for(int i = 0; i < relation.get(node).size(); i++){
            int next = relation.get(node).get(i);
            if(next != parent) {
                dfs(relation, next, node, dp);
                dp[node][1] += dp[next][0];
                dp[node][0] += Math.min(dp[next][0], dp[next][1]);
            }
        }

        dp[node][0] += 1;
    }
}


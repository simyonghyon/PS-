// https://www.acmicpc.net/problem/14942
// 높이가 최대 10000이라길래 그냥 트리 탐색으로 풀었는데
// 누적합 + 이분탐색 + dfs 로 푸는 아름다운 방법도 있다 보고 감탄했어

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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


class Solve {

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    static BufferedReader br = Main.br;
    static BufferedWriter bw = Main.bw;
    private StringTokenizer st;

    private int n;
    private int m;

    public void solve() throws IOException {
        int n = Integer.valueOf(br.readLine());

        int[] ants = new int[n + 1];

        for(int i = 0; i < n; i++){
            ants[i + 1] = Integer.valueOf(br.readLine());
        }

        List<List<int[]>> link = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            link.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            int cost = Integer.valueOf(st.nextToken());
        
            link.get(a).add(new int[]{b, cost});
            link.get(b).add(new int[]{a, cost});
        }

        boolean[] check = new boolean[n + 1];
        check[1] = true;
        int[][] tree = new int[n + 1][2];
        initTree(1, link, tree, check);
        int[] ans = new int[n + 1];

        for(int i = 1; i <= n; i++){
            int energy = ants[i];
            int currentRoom = i;
            while(currentRoom != 1){
                energy -= tree[currentRoom][1];

                if(energy < 0) break;

                currentRoom = tree[currentRoom][0];
            }

            ans[i] = currentRoom;
        }

        for(int i = 1; i <= n; i++){
            System.out.println(ans[i]);
        }
    }

    private void initTree(int parent, List<List<int[]>> link, int[][] tree, boolean[] check){

        for(int i = 0; i < link.get(parent).size(); i++){
            int child = link.get(parent).get(i)[0];

            if(!check[child]){
                check[child] = true;
                tree[child][0] = parent;
                tree[child][1] = link.get(parent).get(i)[1];
                initTree(child, link, tree, check);
            }
        }
    }
}
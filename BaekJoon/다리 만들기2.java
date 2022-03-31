//https://www.acmicpc.net/problem/17472

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {

        int t = 1;

        //t = Integer.parseInt(br.readLine());

        Solve solve = new Solve();

        while (t > 0) {
            solve.solve();
            t--;
        }
    }
}




class Solve {

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    private BufferedReader br = Main.br;
    private BufferedWriter bw = Main.bw;
    private StringTokenizer st;

    private int n;
    private int m;
    private final int MOD = 1000000007;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < m; k++) {
                board[i][k] = Integer.valueOf(st.nextToken());
            }
        }

        boolean[][] check = new boolean[n][m];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                if (board[i][k] != 0 && !check[i][k]) {
                    findIsland(board, i, k, count, check);
                    count++;
                }
            }
        }

        /*for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                System.out.print(board[i][k]);
            }
            System.out.println();
        }*/

        int[][] distance = new int[count][count];
        for (int i = 1; i < count; i++) {
            distance[i][i] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < m; k++) {
                connectIsland(board, distance, i, k);
            }
        }

        /*for (int i = 1; i < count; i++) {
            for (int k = 1; k < count; k++) {
                System.out.print(distance[i][k]);
            }
            System.out.println();
        }*/

        PriorityQueue<Connect> pq = new PriorityQueue<>(Comparator.comparingInt(Connect::getLength));
        for (int i = 1; i < count; i++) {
            for (int k = i; k < count; k++) {
                if (distance[i][k] > 0) {
                    pq.add(new Connect(i, k, distance[i][k]));
                }
            }
        }

        int answer = 0;
        int[] parent = new int[count];
        for(int i = 1; i < count; i++){
            parent[i] = i;
        }
        while (!pq.isEmpty()) {
            Connect connect = pq.poll();

            if(union(connect.island1, connect.island2, parent)){
                answer += connect.length;
            }
        }

        int tmp = find(parent, 1);
        for(int i = 1; i < count; i++){
            if(find(parent, i) != tmp) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }

    class Connect{
        int island1, island2, length;

        public Connect(int island1, int island2, int length) {
            this.island1 = island1;
            this.island2 = island2;
            this.length = length;
        }

        public int getIsland1() {
            return island1;
        }

        public int getIsland2() {
            return island2;
        }

        public int getLength() {
            return length;
        }
    }

    private boolean union(int island1, int island2, int[] parent){
        int parent1 = find(parent, island1);
        int parent2 = find(parent, island2);
        parent[parent1] = parent2;

        if(parent1 == parent2) return false;
        else return true;
    }

    private int find(int[] parent, int island){
        if(parent[island] == island) return island;
        return parent[island] = find(parent, parent[island]);
    }

    // 모든 섬끼리의 연결을 구한다
    // 다 해본다
    // 섬을 구별한다, 1,2,3,..., bfs
    // 모든 변에서 사방으로 다 확인하는 방식으로

    private void connectIsland(int[][] board, int[][] distance, int y, int x){
        for(int i = 0; i < 4; i++){
            int ny = y;
            int nx = x;
            int count = 0;
            while(true){
                ny = ny + dy[i];
                nx = nx + dx[i];

                if(!isOutOfBound(ny, nx, n, m)) break;
                else if(board[ny][nx] != 0){
                    if(count > 1) {
                        int island1 = board[ny][nx];
                        int island2 = board[y][x];
                        if(distance[island1][island2] == 0){
                            distance[island1][island2] = count;

                        } else{
                            distance[island1][island2] = Math.min(distance[island1][island2], count);
                        }
                    }
                    break;
                }
                count++;
            }

        }
    }

    private void findIsland(int[][] board, int i, int k, int count, boolean[][] check){
        board[i][k] = count;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, k));

        while(!q.isEmpty()){
            Node node = q.poll();
            //System.out.println(node.y + " " + node.x);
            for(int j = 0; j < 4; j++){
                int ny = node.y + dy[j];
                int nx = node.x + dx[j];

                if(isOutOfBound(ny, nx, n, m)){
                    if(!check[ny][nx] && board[ny][nx] == 1){
                        q.add(new Node(ny, nx));
                        board[ny][nx] = count;
                        check[ny][nx] = true;
                    }
                }
            }
        }
    }

    class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "(" + y + "," + x + ")";
        }
    }

    private boolean isOutOfBound(int ny, int nx, int n, int m){
        return 0 <= ny && ny < n && 0 <= nx && nx < m;
    }
}
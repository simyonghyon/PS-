//https://www.acmicpc.net/problem/19238

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
        int f = Integer.valueOf(st.nextToken());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int k = 0; k < n; k++) {
                board[i][k] = Integer.valueOf(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int startY = Integer.valueOf(st.nextToken()) - 1;
        int startX = Integer.valueOf(st.nextToken()) - 1;


        Passenger[] passengers = new Passenger[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int passY = Integer.valueOf(st.nextToken()) - 1;
            int passX = Integer.valueOf(st.nextToken()) - 1;
            int desY = Integer.valueOf(st.nextToken()) - 1;
            int desX = Integer.valueOf(st.nextToken()) - 1;

            passengers[i] = new Passenger(passY, passX, desY, desX);
            board[passY][passX] = i + 2;
        }

        for (int i = 0; i < m; i++) {
            Node node = searchNearestPassenger(startY, startX, board);

            if (node != null && node.cost <= f) {
                f -= node.cost;
                //System.out.println(f);
                Passenger p = passengers[board[node.y][node.x] - 2];
                board[node.y][node.x] = 0;
                startY = node.y;
                startX = node.x;

                node = dfs(startY, startX, p, board);
                if (node != null && node.cost <= f) {
                    f += node.cost;
                    //System.out.println(f);
                    startX = node.x;
                    startY = node.y;
                } else {
                    System.out.println("-1");
                    return;
                }

            } else {
                System.out.println("-1");
                return;
            }
        }

        System.out.println(f);
    }

    class Passenger {
        int startY, startX, desY, desX;

        public Passenger(int startY, int startX, int desY, int desX) {
            this.startY = startY;
            this.startX = startX;
            this.desY = desY;
            this.desX = desX;
        }
    }

    class Node {
        int y, x, cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "(" + y + "," + x + ")";
        }
    }

    private Node searchNearestPassenger(int startY, int startX, int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startY, startX, 0));
        boolean[][] check = new boolean[n][n];
        check[startY][startX] = true;
        Node nearestPassenger = null;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (board[node.y][node.x] > 1) {
                if(nearestPassenger == null) {
                    nearestPassenger = node;

                } else if(nearestPassenger.cost < node.cost){
                    break;

                } else if(node.y < nearestPassenger.y) {
                    nearestPassenger = node;

                } else if(nearestPassenger.y == node.y && node.x < nearestPassenger.x){
                    nearestPassenger = node;
                }
            }

            for (int k = 0; k < 4; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];

                if (0 <= ny && ny < n && 0 <= nx && nx < n) {
                    if (board[ny][nx] != 1 && check[ny][nx] == false) {
                        q.add(new Node(ny, nx, node.cost + 1));
                        check[ny][nx] = true;
                    }
                }
            }
        }

        return nearestPassenger;
    }

    private Node dfs(int startY, int startX, Passenger p, int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startY, startX, 0));
        boolean[][] check = new boolean[n][n];
        check[startY][startX] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.y == p.desY && node.x == p.desX) {
                return node;
            }

            for (int k = 0; k < 4; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];

                if (0 <= ny && ny < n && 0 <= nx && nx < n) {
                    if (board[ny][nx] != 1 && check[ny][nx] == false) {
                        q.add(new Node(ny, nx, node.cost + 1));
                        check[ny][nx] = true;
                    }
                }
            }
        }

        return null;
    }
}
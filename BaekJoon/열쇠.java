//https://www.acmicpc.net/problem/9328

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.logging.Filter;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);

        int t = 1;

        t = Integer.parseInt(br.readLine());

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
        String rs = br.readLine();

        st = new StringTokenizer(rs, " ");
        //System.out.println(st.nextToken() + " " + st.nextToken());
        n = Integer.valueOf(st.nextToken());

        m = Integer.valueOf(st.nextToken());

        char[][] board = new char[n][m];

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            board[i] = s.toCharArray();
        }

        Queue<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            if (board[i][0] != '*') {
                q.add(new Node(i, 0));
                check[i][0] = true;
            }

            if (board[i][m - 1] != '*') {
                q.add(new Node(i, m - 1));
                check[i][m - 1] = true;
            }
        }

        for(int i = 1; i < m - 1; i++){
            if(board[0][i] != '*'){
                q.add(new Node(0, i));
                check[0][i] = true;
            }
            if(board[n - 1][i] != '*'){
                q.add(new Node(n - 1, i));
                check[n - 1][i] = true;
            }
        }

        Map<Character, Boolean> keys = new HashMap<>();
        String key = br.readLine();
        for(int i = 0; i < key.length(); i++){
            if(key.charAt(i) != '0')
                keys.put(Character.toUpperCase(key.charAt(i)), true);
        }
        Map<Character, LinkedList<Node>> standBy = new HashMap<>();

        int answer = 0;
        while(!q.isEmpty()){
            Node node = q.poll();
            //System.out.println(node.y + " " + node.x + " " + board[node.y][node.x]);

            char c = board[node.y][node.x];
            if(c == '.'){
                search(board, q, node.y, node.x, check);

            } else if(c == '$'){
                answer++;
                search(board, q, node.y, node.x, check);

            } else if(Character.isAlphabetic(c)){
                if(Character.isUpperCase(c)){
                    if(keys.containsKey(c)){
                        search(board, q, node.y, node.x, check);
                    } else{
                        LinkedList<Node> list;
                        if(!standBy.containsKey(c)){
                            list = new LinkedList<>();
                            standBy.put(c, list);
                        } else{
                            list = standBy.get(c);
                        }

                        list.add(node);
                    }

                } else if(Character.isLowerCase(c)){
                    LinkedList<Node> list = standBy.get(Character.toUpperCase(c));
                    if(list != null) list.forEach(n->q.add(n));
                    search(board, q, node.y, node.x, check);
                    keys.put(Character.toUpperCase(c), true);
                }
            }
        }

        System.out.println(answer);
    }

    private void search(char[][] board, Queue<Node> q, int y, int x, boolean[][] check){
        for(int i = 0; i < 4; i++){
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if(0 <= ny && ny < n && 0 <= nx && nx < m){
                if(!check[ny][nx] && board[ny][nx] != '*'){
                    q.add(new Node(ny, nx));
                    check[ny][nx] = true;
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

    }
}
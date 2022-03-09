import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {

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

        String[] board = new String[n];

        for(int i = 0; i < n; i++){
            board[i] = br.readLine();
        }

        Node[][] cycleCheck = new Node[n][m];
        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int k = 0; k < m; k++){
                if(cycleCheck[i][k] != null) continue;

                cycleCheck[i][k] = new Node(i, k);
                int y = i, x = k;

                while(true){
                    if(board[y].charAt(x) == 'D'){
                        y += 1;
                    } else if(board[y].charAt(x) == 'L'){
                        x -= 1;
                    } else if(board[y].charAt(x) == 'U'){
                        y -= 1;
                    } else if(board[y].charAt(x) == 'R'){
                        x += 1;
                    }

                    if(cycleCheck[y][x] != null) {
                        union(cycleCheck[y][x], cycleCheck[i][k], cycleCheck);
                        break;
                    }
                    cycleCheck[y][x] = cycleCheck[i][k];
                    /*for(int j =0 ; j < n; j++){
                        for(int p = 0; p < m; p++){
                            System.out.print(cycleCheck[j][p]);
                        }
                        System.out.println();
                    }*/
                }
            }
        }
        boolean[][] check = new boolean[n][m];
        for(int i =0 ; i < n; i++){
            for(int k = 0; k < m; k++) {
                Node parent = find(cycleCheck, cycleCheck[i][k]);
                if(!check[parent.y][parent.x]){
                    answer++;
                    check[parent.y][parent.x] = true;
                }
            }
        }
        System.out.println(answer);
    }

    class Node{
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString(){
            return "(" + y + "," + x + ")";
        }
    }


    private int union(Node l, Node r, Node[][] cycleCheck){
        Node left = find(cycleCheck, l);
        Node right = find(cycleCheck, r);
        if(left == right) return 0;
        cycleCheck[left.y][left.x] = right;
        return 1;
    }

    private Node find(Node[][] cycleCheck, Node child){
        if(cycleCheck[child.y][child.x] == child) return child;
        return cycleCheck[child.y][child.x] = find(cycleCheck, cycleCheck[child.y][child.x]);
    }
}

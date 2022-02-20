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
    private final int MOD = 1000000007;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.valueOf(st.nextToken());

        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i =0; i < n; i++){
            int k = Integer.valueOf(br.readLine());
            if(k == 0) {
                if(q.isEmpty()) System.out.println(0);
                else System.out.println(q.poll());

            } else{
                q.add(k);
            }

        }

    }

    class Node{
        int left, right;
        long value;

        public Node(int left, int right, long value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

}
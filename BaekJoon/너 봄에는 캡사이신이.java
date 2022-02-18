// https://www.acmicpc.net/problem/15824
// Math.pow 썼다가 피봤다...

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

        long[] nums = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            nums[i] = Long.valueOf(st.nextToken());
        }

        long[] mods = new long[300001];
        mods[0] = 1;
        for(int i = 1; i <= 300000; i++){
            mods[i] = mods[i - 1] * 2 % MOD;
        }

        Arrays.sort(nums);
        long answer = 0;
        for(int i = 0; i < n; i++){
//            answer = ((answer % MOD) + mod(nums[i], mods[i])) % MOD;
//            answer = (answer % MOD - mod(nums[i], mods[n - i - 1]) + MOD) % MOD;
            answer += nums[i] * (mods[i] - mods[n - i  - 1]);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private long mod(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
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
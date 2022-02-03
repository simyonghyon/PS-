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

    private final int ALL;
    private int n;

    public Solve() {
        int tmp = 0;

        for(int i = 0; i < 10; i++){
            tmp += (1 << i);
        }

        this.ALL = tmp;
    }

    public void solve() throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());

        Node[][] dp = new Node[10][n];

        for(int i = 0; i < 10; i++){
            for(int k = 0; k < n; k++){
                dp[i][k] = new Node(0);
            }
        }

        int ans = 0;
        for(int i = 1; i <= 9; i++){
            ans = (ans + recursive(i, 1, (1<<i), dp)) % 1000000000;
        }

        System.out.println(ans);
    }

    private int recursive(int value, int depth, int bitmask, Node[][] dp){
        //System.out.println(value);
        if(depth == n){
            if(bitmask == ALL){
                return 1;
            }

            else return 0;
        }

        if(dp[value][depth].getBitCount(bitmask) != -1){
            return dp[value][depth].getBitCount(bitmask);
        }

        int count = 0;

        if(value == 0){
            count = recursive(1, depth+1, bitmask|(1<<1), dp);

        } else if(value == 9){
            count = recursive(8, depth+1, bitmask|(1<<8), dp);

        } else{
            count += recursive(value-1, depth+1, bitmask|(1<<(value-1)), dp);
            count = (count + recursive(value+1, depth+1, bitmask|(1<<(value+1)), dp)) % 1000000000;
        }

        dp[value][depth].increaseBitmaskCount(bitmask, count);
        return count;
    }
}

class Node {
    int value, depth;
    private Map<Integer, Integer> bitmask = new HashMap<>();
    long count;

    public Node(long count) {
        this.count = count;
    }

    public void increaseBitmaskCount(int bit, int count){
        if(bitmask.containsKey(bit)){
            bitmask.put(bit, (bitmask.get(bit) + count) % 1000000000);

        } else{
            bitmask.put(bit, count);
        }
    }

    public int getBitCount(int bit){
        if(bitmask.containsKey(bit)){
            return bitmask.get(bit);

        } else{
            return -1;
        }
    }
}
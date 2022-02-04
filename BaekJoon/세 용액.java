//https://solved.ac/search?query=in_class_essentials:5%20-solved_by:$me

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

        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            a[i] = Integer.valueOf(st.nextToken());
        }

        int low = 0;
        int high = n - 1;

        int ans = Integer.MAX_VALUE;
        int ansL = 0, ansH = 0;

        while(low < high){
            int sum = a[low] + a[high];

            if(Math.abs(sum) < ans) {
                ansL = low;
                ansH = high;
                ans = Math.abs(sum);
            }

            if(sum > 0) {
                high--;

            } else if(sum < 0){
                low++;

            } else{
                break;
            }
        }

        System.out.println(a[ansL] + " " + a[ansH]);
    }


}
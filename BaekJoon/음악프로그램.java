// 전형적인 위상정렬 문제

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
    private int m;


    public void solve() throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());

        int[] count = new int[n + 1];

        LinkedList<Integer>[] lists = new LinkedList[n + 1];
        for(int i = 1; i <= n; i++) lists[i] = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int next = Integer.valueOf(st.nextToken());
            while (st.hasMoreTokens()) {
                int now = Integer.valueOf(st.nextToken());
                lists[next].add(now);
                count[now]++;
                next = now;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        LinkedList<Integer> answer = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(count[i] == 0) {
                q.add(i);
                answer.add(i);
            }
        }

        while(!q.isEmpty()){
            int next = q.poll();

            lists[next].forEach(i->{
                count[i] -= 1;
                if(count[i] == 0) {
                    q.add(i);
                    answer.add(i);
                }
            });
        }
        //System.out.println(answer);
        if(answer.size() < n) System.out.println(0);
        else answer.forEach(a->System.out.println(a));
    }


}
// https://www.acmicpc.net/problem/12852

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

        Queue<Integer> q = new LinkedList<>();

        q.add(n);

        int[] check = new int[1000001];
        check[n] = -1;

        while(!q.isEmpty()){
            int x = q.poll();

            if(x == 1){
                Stack<Integer> l = new Stack<>();

                int tmp = 1;
                while(tmp != -1){
                    l.add(tmp);
                    tmp = check[tmp];
                }

                System.out.println(l.size() - 1);
                while (!l.isEmpty()){
                    System.out.print(l.pop() + " ");
                }
                break;
            }

            if(x % 2 == 0 && check[x / 2] == 0){
                q.add(x / 2);
                check[x / 2] = x;
            }

            if(x % 3 == 0 && check[x / 3] == 0){
                q.add(x / 3);
                check[x / 3] = x;
            }

            if(check[x - 1] == 0){
                q.add(x - 1);
                check[x - 1] = x;
            }
        }
    }


}
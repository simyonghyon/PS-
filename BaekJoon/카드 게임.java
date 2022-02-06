//https://www.acmicpc.net/problem/16566
// 이거 4000000 인데 정렬해도 되나??

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

        int m, k;
        st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        k = Integer.valueOf(st.nextToken());

        int[] cards = new int[m];
        int[] submit = new int[k];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; st.hasMoreTokens(); i++){
            cards[i] = Integer.valueOf(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; st.hasMoreTokens(); i++){
            submit[i] = Integer.valueOf(st.nextToken());
        }

        Arrays.sort(cards);

        int[] parent = new int[m + 1];
        for(int i = 0; i < m; i++) parent[i] = i;

        int[] answer = new int[k];
        for(int i = 0; i < k; i++){
            //int index = upperBound(cards, submit[i]);
            int index = Arrays.binarySearch(cards, submit[i]);
            //System.out.println(index);
            index = index < 0 ? -index-1 : index+1;
            answer[i] = findNextIndex(parent, index);
            //System.out.println(answer[i]);
            parent[answer[i]] = answer[i] + 1;
        }

        for(int i = 0; i < k; i++){
            System.out.println(cards[answer[i]]);
        }
    }

    private int findNextIndex(int[] parent, int find){
        if(find == parent[find]) return find;

        return parent[find] = findNextIndex(parent, parent[find]);
    }

    // 큰 것 중 가장 작은 것
    private int upperBound(int[] cards, int value){
        int low = 0;
        int high = cards.length;

        while(low < high){
            int mid = (low + high) / 2;

            if(cards[mid] <= value){
                low = mid + 1;

            } else if(cards[mid] > value){
                high = mid;
            }
        }

        return high;
    }

}
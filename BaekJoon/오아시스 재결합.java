// https://www.acmicpc.net/problem/3015
// 진짜 답이 int 넘어설 수 있다는 거 생각도 못했다 이야 왜틀린가 했네

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

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
    static StringTokenizer st;

    private int n;
    private int m;

    public void solve() throws IOException {
        int n = Integer.valueOf(br.readLine());

        int[] height = new int[n];
        for(int i = 0; i < n; i++){
            height[i] = Integer.valueOf(br.readLine().trim());
        }

        long answer = 0;
        List<Integer> canSee = new ArrayList<>();
        int start = 0, end = 0;

        for(int i = 0; i < n; i++){
            int now = height[i];

            int tmp = binarySearch2(canSee, start, end, now);
            //System.out.println(tmp);
            if(tmp == -1) answer += end - start;
            else answer += end - binarySearch2(canSee, start, end, now);

            int index = binarySearch(canSee, start, end, now);
            //System.out.println(index);
            if(index == end){
                if(end == canSee.size()) canSee.add(now);
                else canSee.set(end, now);
                end += 1;

            } else{
                end = index + 1;
                canSee.set(index, now);
            }
        }

        System.out.println(answer);
    }

    private int binarySearch2(List<Integer> canSee, int start, int end, int searchValue){
        int left = start;
        int right = end - 1;
        //System.out.println(canSee.toString());
        while(left <= right){
            int mid = (left + right) / 2;
            //System.out.println(mid);
            int value = canSee.get(mid);

            if(searchValue >= value){
                right = mid - 1;

            } else if(searchValue < value){ // 6 5 3 2 1
                left = mid + 1;

            }
        }

        return right;
    }

    private int binarySearch(List<Integer> canSee, int start, int end, int searchValue){
        int left = start;
        int right = end - 1;
        //System.out.println(canSee.toString());
        while(left <= right){
            int mid = (left + right) / 2;
            //System.out.println(mid);
            int value = canSee.get(mid);

            if(searchValue > value){
                right = mid - 1;

            } else if(searchValue <= value){ // 6 5 3 2 1
                left = mid + 1;

            }
        }

        return left;
    }

    class Node{
        private int start, end;
        Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        public int getStart(){
            return this.start;
        }

        public int getEnd(){
            return this.end;
        }
    }
}
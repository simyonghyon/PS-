//https://www.acmicpc.net/problem/13334
// 우선순위 큐의 존재 자체를 까먹고 있었다...

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

        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            nodes[i] = new Node(Math.min(start, end), Math.max(end, start));
            //nodes.add(new Node(Math.min(start, end), Math.max(end, start)));
        }

        final int scope = Integer.valueOf(br.readLine());

        Arrays.sort(nodes, Comparator.comparingInt(Node::getEnd).thenComparing(Node::getEnd));

        //nodes.forEach(node->System.out.println(node.getStart() + " " + node.getEnd()));
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getStart));

        int answer = 0;

        for(int i = 0; i < nodes.length; i++){
            int end = nodes[i].getEnd();
            int start = end - scope;

            pq.add(nodes[i]);

            while(!pq.isEmpty()){
                if(pq.peek().start >= start) break;
                pq.poll();
            }

            answer = Math.max(pq.size(), answer);
        }

        System.out.println(answer);
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
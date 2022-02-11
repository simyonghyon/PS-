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

        List<Node> nodes = new ArrayList<>();

        Node[] node1 = new Node[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            node1[i] = new Node(Math.min(start, end), Math.max(end, start));
            //nodes.add(new Node(Math.min(start, end), Math.max(end, start)));
        }

        final int scope = Integer.valueOf(br.readLine());


        for(int i = 0; i < n; i++){
            if(node1[i].end - node1[i].start <= scope) nodes.add(node1[i]);
        }

        nodes.sort(Comparator.comparingInt(Node::getStart));

        //nodes.forEach(node->System.out.println(node.getStart() + " " + node.getEnd()));
        PriorityQueue<Node> notInclude = new PriorityQueue<>(Comparator.comparingInt(Node::getEnd));

        int answer = 0;
        int searchIndex = 0;
        int count = 0;
        for(int i = 0; i < nodes.size(); i++){
            int start = nodes.get(i).getStart();
            int end = start + scope;

            if(i > 0){
                if(nodes.get(i - 1).start == nodes.get(i).start){
                    count--;
                    continue;
                }
            }

            while(!notInclude.isEmpty()){
                if(notInclude.peek().start < start) {
                    notInclude.poll();
                    continue;
                }

                if(notInclude.peek().end > end) break;
                else {
                    notInclude.poll();
                    count++;
                }
            }
            /*
            for(int k = notInclude.size() - 1; k >= 0; k--){
                if(notInclude.get(k).)
                if(notInclude.get(k).end > end) break;
                else {
                    notInclude.remove(k);
                    count++;
                }
            }*/

            while(searchIndex < nodes.size() && nodes.get(searchIndex).start <= end){
                if(nodes.get(searchIndex).end <= end){
                    count++;

                } else{
                    notInclude.add(nodes.get(searchIndex));
                }
                searchIndex++;
            }

            answer = Math.max(count, answer);
            count--;
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
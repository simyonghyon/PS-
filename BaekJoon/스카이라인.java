// https://www.acmicpc.net/problem/1933
// 너무 막 푼 느낌이 있다, 예외적인 상황을 많이 생각해줘야 했는데
// 처음부터 그럴일 없는 풀이를 생각하는게 중요할 듯

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {

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

    private BufferedReader br = Main.br;
    private BufferedWriter bw = Main.bw;
    private StringTokenizer st;

    private int n;
    private int m;
    private final int MOD = 1000000007;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.valueOf(st.nextToken());

        Node[] nodes = new Node[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            nodes[i] = new Node(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
        }

        Arrays.sort(nodes, Comparator.comparingInt(Node::getL).thenComparing((n)->n.getH()*-1));
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getH).reversed());
        Node currentNode = null;
        StringBuilder ans = new StringBuilder("");
        for(int i = 0; i < n; i++){
            while(currentNode != null && currentNode.r < nodes[i].l){
                pq.poll();
                // 다음으로 큰게 지금이 끝나도 남아있을때까지
                while(!pq.isEmpty() && pq.peek().r <= currentNode.r){
                    pq.poll();
                }

                if(!pq.isEmpty()){
                    if(currentNode.h != pq.peek().h)
                        ans.append(currentNode.r + " " + pq.peek().h + " ");
                    currentNode = pq.peek();

                } else{
                    ans.append(currentNode.r + " " + "0" + " ");
                    currentNode = null;
                }
            }

            pq.add(nodes[i]);

            if(currentNode == null || currentNode.h < pq.peek().h){
                currentNode = pq.peek();
                ans.append(currentNode.l + " " + currentNode.h + " ");
            }
        }

        while(!pq.isEmpty()){
            pq.poll();
            while(!pq.isEmpty() && pq.peek().r <= currentNode.r){
                pq.poll();
            }

            if(!pq.isEmpty()) {
                if(currentNode.h != pq.peek().h)
                    ans.append(currentNode.r + " " + pq.peek().h + " ");
                currentNode = pq.peek();
            }
        }
        ans = ans.append(currentNode.r + " 0");
        System.out.println(ans);
    }

    class Node{
        int l, r, h;

        public Node(int l, int h, int r) {
            this.l = l;
            this.r = r;
            this.h = h;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }

        public int getH() {
            return h;
        }


        @Override
        public String toString(){
            return l + " " + h + " " + r;
        }
    }


}
//https://www.acmicpc.net/problem/14725

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

    public void solve() throws IOException {
        int n = Integer.valueOf(br.readLine());

        List<List<String>> house = new ArrayList<>();

        for(int i = 0; i < n; i++){
            house.add(new ArrayList<>());
            StringTokenizer line = new StringTokenizer(br.readLine(), " ");
            int m = Integer.valueOf(line.nextToken());

            for(int k = 0; k < m; k++){
                house.get(i).add(line.nextToken());
            }
        }

        house.sort((l1, l2)->{
            for(int i = 0; i < Math.min(l1.size(), l2.size()); i++){
                if(l1.get(i).equals(l2.get(i))) continue;

                return l1.get(i).compareTo(l2.get(i));
            }
            return 0;
        });

        //house.forEach(System.out::println);

        Node root = new Node("root");
        for(int i = 0; i < n; i++){
            root.addChild(new Node(house.get(i).get(0)));
        }

        for(int i = 0; i < n; i++){
            initTree(house.get(i), root);
        }

        search(root, "");
        System.out.println(sb.toString());
    }

    StringBuilder sb = new StringBuilder("");
    private void search(Node parent, String depth){
        Map<String, Node> child = parent.getChild();

        List<String> keys = child.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        for(String s : keys){
            sb.append(depth).append(child.get(s).getName()).append("\n");
            search(child.get(s), depth + "--");
        }
    }

    private void initTree(List<String> branch, Node parent){
        for(String name : branch){
            Node child = new Node(name);
            parent = parent.addChild(child);
        }
    }


    class Node{
        private String name;
        private int depth;
        private Map<String, Node> child = new HashMap<>();
        
        Node(String name){
            this.name = name;
        }
        
        public Node addChild(Node child){
            if(this.child.containsKey(child.getName())) return this.child.get(child.getName());
            else {
                this.child.put(child.getName(), child);
                return child;
            }
        }

        public String getName() {
            return name;
        }

        public Map<String, Node> getChild() {
            return child;
        }
    }
}

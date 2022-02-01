//https://www.acmicpc.net/problem/7579

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

class Solve{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public void solve() throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        int[] memories = new int[n];
        int[] costs = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            memories[i] = Integer.valueOf(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            costs[i] = Integer.valueOf(st.nextToken());
        }

        App[] apps = new App[n];
        for(int i = 0; i < n; i++){
            apps[i] = App.of(memories[i], costs[i]);
        }

        Arrays.sort(apps, Comparator.comparingInt(x -> x.cost));

        // 인덱스는 비용, 비용에 따른 확보된 최대 메모리
        int[] maxMemoryByCost = new int[10000001];

        // 현재까지 든 비용들의 리스트
        Set<Integer> costList = new TreeSet<>(Comparator.reverseOrder());
        List<Integer> add = new LinkedList<>();
        costList.add(0);

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            add.clear();

            for(int c : costList){
                if(maxMemoryByCost[c] + apps[i].memory > maxMemoryByCost[c + apps[i].cost]){
                    if(maxMemoryByCost[c+apps[i].cost] == 0) add.add(c + apps[i].cost);
                    maxMemoryByCost[c + apps[i].cost] = maxMemoryByCost[c] + apps[i].memory;
                    if(maxMemoryByCost[c + apps[i].cost] >= m) answer = answer > c + apps[i].cost ? c + apps[i].cost : answer;
                }
            }

            costList.addAll(add);
            //costList.forEach(c->System.out.println(c + " " + maxMemoryByCost[c]));
            //System.out.println();
        }

        //costList.forEach(c->System.out.println(c + " " + maxMemoryByCost[c]));
        System.out.println(answer);
    }

}

class App {
    int memory, cost;

    App(){}

    public static App of(int memory, int cost){
        App pair = new App();
        pair.memory = memory;
        pair.cost = cost;
        return pair;
    }

}

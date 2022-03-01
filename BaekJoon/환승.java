package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;


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
        m = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());

        List<List<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= n + k + 1; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            while(st.hasMoreTokens()){
                int tmp = Integer.valueOf(st.nextToken());
                a.get(tmp).add(i + n + 1);
                a.get(i + n + 1).add(tmp);
            }
        }
        //System.out.println(a.toString());
        Queue<Integer> q = new LinkedList<>();
        int[] checked = new int[n + 1 + k];

        q.add(1);
        checked[1] = 1;
        while (!q.isEmpty()) {
            int node = q.poll();
            //System.out.println(node);
            if (node == n) {
                break;
            }

            for(int i : a.get(node)){
                if (checked[i] == 0) {
                    if(i > n){
                        checked[i] = checked[node];
                    } else {
                        checked[i] = checked[node] + 1;
                    }
                    q.add(i);
                }
            }
        }
        System.out.println(checked[n] == 0 ? -1 : checked[n]);
    }

    class Node{
        int parent, value, depth;
        LinkedList<Integer> child = new LinkedList<>();

        public Node(int parent, int value) {
            this.parent = parent;
            this.value = value;
        }
    }

}


class Solution {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

}

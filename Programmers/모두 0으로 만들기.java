// https://programmers.co.kr/learn/courses/30/lessons/76503#

import java.util.*;

class Solution {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public long solution(int[] a, int[][] edges) {

        // 가중치 0 불가
        long sum = 0;

        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        if(sum != 0) return -1;

        ArrayList<Integer>[] edge = new ArrayList[300000];

        // 간선 연결
        for(var i : edges){
            if(edge[i[0]] == null) edge[i[0]] = new ArrayList<>();
            if(edge[i[1]] == null) edge[i[1]] = new ArrayList<>();

            edge[i[0]].add(i[1]);
            edge[i[1]].add(i[0]);
        }

        // 트리 생성
        Node tmp = Node.of(null, 0, 0, 0);
        Node root = Node.of(tmp, 0, 0, a[0]);

        Queue<Node> queue = new LinkedList<>();
        Stack<Node> s = new Stack<>();
        queue.add(root);
        s.add(root);

        boolean[] check = new boolean[300000];
        check[0] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i = 0; i < edge[node.a].size(); i++){
                int nextA = edge[node.a].get(i);
                if(!check[nextA]) {
                    Node child = Node.of(node, node.height + 1, edge[node.a].get(i), a[edge[node.a].get(i)]);
                    queue.add(child);
                    s.add(child);
                    check[nextA] = true;
                }
            }
        }

        // 계산
        long answer = 0;

        while(!s.isEmpty()){
            Node node = s.pop();
            
            answer += Math.abs(node.value);
            node.parent.value += node.value;
        }

        return answer;
    }

}

/*
 * 1. node 생성
 * 2. edges 로 간선 연결 리스트? 로
 * 3. 큐로 노드 초기화하며 스택에 넣기
 * 4. 스택에 있는 것 쭉 계산
 */

class Node{
    Node parent;
    int height;
    long value;
    int a;

    Node(){}

    public static Node of(Node parent, int height, int a, long value){
        Node pair = new Node();
        pair.parent = parent;
        pair.height = height;
        pair.a = a;
        pair.value = value;
        return pair;
    }
}
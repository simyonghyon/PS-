//https://school.programmers.co.kr/learn/courses/30/lessons/92343#
// barrrrrrrrrrrrrkingdog 가라사데 틀린 풀이라지만 
// 그러면 tc를 빡세게 잡던가!!! 통과했으면 됐지ㅋㅋ

import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;

        int size = info.length;
        Node[] nodes = new Node[size];
        for(int i = 0; i < size; i++) {
            nodes[i] = new Node(i, info[i]);
        }
        for(int i = 0; i < edges.length; i++) {
            nodes[edges[i][0]].sub.add(nodes[edges[i][1]]);
        }

        List<Node> next = new LinkedList<>();
        next.add(nodes[0]);
        boolean[] check = new boolean[size];
        getMaxSheep(next, 1, 0, check);
        return maxSheep;
    }

    public boolean isSheep(int animal) {
        return animal == 0;
    }

    int maxSheep = 0;
    public void getMaxSheep(List<Node> next, int sheepCount, int wolfCount, boolean[] check) {
        maxSheep = Math.max(maxSheep, sheepCount);
        if(!canGo(sheepCount, wolfCount)) return;
        //system.out.println(sheepCount);

        for(int i = 0; i < next.size(); i++) {
            Node node = next.get(i);
            for(int k = 0; k < node.sub.size(); k++) {
                Node nextGo = node.sub.get(k);
                if(check[nextGo.num]) continue;
                check[nextGo.num] = true;
                next.add(nextGo);
                // if(sheepCount < 4) System.out.println(nextGo.num + " " + sheepCount);
                if(nextGo.animal == 0)
                    getMaxSheep(next, sheepCount + 1, wolfCount, check);
                else
                    getMaxSheep(next, sheepCount, wolfCount + 1, check);
                next.remove(next.size() - 1);
                check[nextGo.num] = false;
            }
         }
    }

    private boolean canGo(int sheep, int wolf) {
        return sheep > wolf;
    }
}

class Node {
    int num;
    int animal;
    List<Node> sub = new LinkedList();

    public Node(int num, int animal) {
        this.num = num;
        this.animal = animal;
    }

    public boolean isNext(boolean[] check) {
        for(Node n : sub) {
            if(!check[n.num]) return true;
        }
        return false;
    }
}
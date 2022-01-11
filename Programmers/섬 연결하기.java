// https://programmers.co.kr/learn/courses/30/lessons/42861
// 전형적인 union-find 문제였다. 오랜만에 쉬운거 푸니까 편-안 하다

class Solution {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (o, o2) -> Integer.compare(o[2], o2[2]));

        int[] parent = new int[n];
        int[] size = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        for(var cost : costs){
            if(union(cost[0], cost[1], parent, size)){
                answer += cost[2];
            }
        }
        
        return answer;
    }

    private boolean union(int n, int m, int[] parent, int[] size){
        int nParent = find(n, parent);
        int mParent = find(m, parent);
        
        if(nParent == mParent) return false;
        
        if(size[nParent] > size[mParent]){
            parent[mParent] = nParent;
            size[nParent] += size[mParent];
        
        } else{
            parent[nParent] = mParent;
            size[mParent] = size[nParent];
        }

        
        return true;
    }

    private int find(int n, int[] parent){
        if(n == parent[n]) return n;
        return parent[n] = find(parent[n], parent);
    }
}
// 다익스트라 문제였다. 같은 도시에 경로가 여러 개 있다고 하던데 도시가 최대 50개밖에 되지
// 않길래 오랜만에 인접 행렬을 사용해봤다. 이런거 풀때마다 느끼는 건데 c++ 처럼 pair을
// 지원해주면 좋겠다...

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[][] time = new int[N + 1][N + 1];
        
        for(int i = 0; i < road.length; i++){
            int y = road[i][0];
            int x = road[i][1];
            
            if(time[y][x] != 0){
                time[x][y] = time[y][x] = time[y][x] < road[i][2] ? time[y][x] : road[i][2];
            } else{
                time[x][y] = time[y][x] = road[i][2];
            }
        }

        PriorityQueue<viliage> pq = new PriorityQueue<>();
        int[] check = new int[N + 1];
        for(int i = 0; i <= N; i++){
            check[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new viliage(1, 0));
        check[1] = 0;
        
        while(!pq.isEmpty()){
            int current = pq.peek().current;
            int totalTime = pq.peek().totalTime;
            pq.poll();
            
            
            for(int i = 1; i <= N; i++){
                if(time[current][i] != 0 && check[i] > totalTime + time[current][i]){
                    if(K >= totalTime + time[current][i]){
                        check[i] = totalTime + time[current][i];
                        pq.add(new viliage(i, totalTime + time[current][i]));
                    }
                }
            }
        }
        
        for(var i : check){
            System.out.println(i);
            if(i != Integer.MAX_VALUE) answer++;
        }
        
        return answer;
    }
}

class viliage implements Comparable<viliage>{
    int current, totalTime;
    
    viliage(){}
    viliage(int current, int totalTime){
        this.current = current;
        this.totalTime = totalTime;
    }
    
    @Override
    public int compareTo(viliage v){
        return Integer.compare(totalTime, v.totalTime);
    }
}
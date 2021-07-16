//https://programmers.co.kr/learn/courses/30/lessons/62050

class Solution {
    
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] land, int height) {
        int answer = 0;

        Queue<pair> pq = new PriorityQueue<>();
        boolean[][] check = new boolean[land.length][land[0].length];

        pq.add(new pair(0, 0, 0));

        while(!pq.isEmpty()){
            int y = pq.peek().y;
            int x = pq.peek().x;
            int z = pq.peek().z;
            pq.poll();

            if(check[y][x]) continue;
            
            answer += z;
            check[y][x] = true;

            for(int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(0 <= ny && ny < check.length && 0 <= nx && nx < check[0].length && !check[ny][nx]){
                    if(Math.abs(land[ny][nx] - land[y][x]) <= height){
                        pq.add(new pair(ny, nx, 0));
                    }
                    else{
                        pq.add(new pair(ny, nx, Math.abs(land[ny][nx] - land[y][x])));
                    }
                }
            }
        }
        
        
        return answer;
    }

}



class pair implements Comparable<pair>{
    int y, x;
    int z = 0;
    public pair(int y, int x, int z){
        this.y = y;
        this.x = x;
        this.z = z;
    }

    @Override
    public int compareTo(pair o) {
        return Integer.compare(z, o.z);
    }
}
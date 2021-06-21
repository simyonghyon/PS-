class Solution {


    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Long> pq = new PriorityQueue<>();

        for(var k : scoville) pq.add(Integer.toUnsignedLong(k));
        Long k = Integer.toUnsignedLong(K);
        while(true){
            if(pq.peek() >= k) break;
            
            if(pq.size() == 1){
                answer = -1;
                break;
            }
            
            Long min = pq.poll();
            Long second_Min = pq.poll();

            if(second_Min == 0){
                answer = -1;
                break;
            }

            pq.add(min + second_Min * 2);
            answer++;
        }

        return answer;
    }

}
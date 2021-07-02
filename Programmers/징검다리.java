//https://programmers.co.kr/learn/courses/30/lessons/43236

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        int left = 0;
        int right = distance;
        
        Arrays.sort(rocks);
        
        while(left <= right){
            int mid = (left + right) / 2;
            
            int d = n;
            int prev = 0;
            for(int i = 0; i < rocks.length; i++){
                int gap = rocks[i] - prev;
                if(gap >= mid){
                    prev = rocks[i];
                }
                else d--;
            }
            
            if(d < 0) right = mid - 1;
            else if(d >= 0) {
                answer = mid;
                left = mid + 1;
            }
        }
        
        
        return answer;
    }
}
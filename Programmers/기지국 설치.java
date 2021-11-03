//https://programmers.co.kr/learn/courses/30/lessons/12979

    class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;
    
            int wi = w * 2 + 1;
            int start = 1;
            
            for(int i = 0; i < stations.length; i++){
                int b = stations[i] - w >= 1 ? stations[i] - w : 1;
                int e = stations[i] + w <= n ? stations[i] + w : n;
                
                if(start < b){ 
                    int wid = b - start;
                    answer += (wid + wi - 1) / wi;
                }
                
                //System.out.println(answer);
                start = e + 1;
            }
            
            int e = stations[stations.length - 1] + w <= n ? stations[stations.length - 1] + w : n;
            if(e < n){
                answer += (n - e + wi - 1) / wi;
            }
    
            return answer;
        }
    }


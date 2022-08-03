class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean[] lotto_num = new boolean[46];
        
        int zeroCount = 0;
        for(int i = 0; i < 6; i++){
            lotto_num[lottos[i]] = true;
            if(lottos[i] == 0) zeroCount++;
        }
        
        
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(lotto_num[win_nums[i]]){
                count++;
            }
        }    
    
        answer[1] = calcRank(count);
        answer[0] = calcRank(zeroCount + count);
        
        
        return answer;
    }
    
    private int calcRank(int count){
        int rank;
        
        if(count <= 1){
            rank = 6;
            
        } else{
            rank = 7 - count;
        }
        
        return rank;
    }
}

// 모르는건 0
// 0은 뭐든 될 수 있다
// 최고 최저를 구하라
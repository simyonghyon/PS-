//https://programmers.co.kr/learn/courses/30/lessons/42891#

import java.util.Arrays;

class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        
        int[] sorted_food = Arrays.copyOf(food_times, food_times.length);
        
        Arrays.sort(sorted_food);
        
        long remain_length = (long)food_times.length;
        long totalEat = 0;
        for(int i = 0; i < sorted_food.length; i++){
            long min = (long)sorted_food[i] - totalEat;
            if(min <= 0){
                remain_length--;
                continue;
            }
            
            if(k <= remain_length * min) {
                answer = getAns(totalEat, food_times, remain_length, k) + 1;
                break;
            }
            k = k - remain_length * min;
            totalEat += min;
            
            remain_length--;
        }
        
        if(answer == 0) return -1;
        return answer;
    }
    
    private int getAns(long totalEat, int[] food_times, long remain_length, long k){
        totalEat += k / remain_length;
        for(int i = 0; i < food_times.length; i++){
            food_times[i] = food_times[i] - (int)totalEat;
        }
        
        k = k % remain_length;
        //System.out.println(k);
        //for(var i : food_times) System.out.println(i);
        
        for(int i = 0; i < food_times.length; i++){
            if(food_times[i] > 0) {
                if(k == 0) return i;
                k--; 
            }
        }
        
        return -1;
    }
}
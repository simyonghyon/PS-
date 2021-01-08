//https://programmers.co.kr/learn/courses/30/lessons/42583?language=java
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        ArrayList<Integer> indexOfEnter = new ArrayList<Integer> (truck_weights.length);
        int sum = 0;
        int index = 0;
        
        for(int i = 0; i < truck_weights.length; i++){
            while(true){
                if(sum + truck_weights[i] <= weight) break;
                answer = bridge_length + indexOfEnter.get(index);
                sum -= truck_weights[index];
                index++;
            }
            indexOfEnter.add(i, answer);
            sum += truck_weights[i];
            answer++;
            
            if(indexOfEnter.get(index) + bridge_length== answer){
                sum -= truck_weights[index];
                index++;
            }
        }
        answer = indexOfEnter.get(truck_weights.length - 1) + bridge_length;
        return answer;
    }
}
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int max_day = 0;
        for(int i = 0; i < progresses.length; i++){
            int day = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
           if(max_day < day){
               ans.add(1);
               max_day = day;
           } 
            else{
                ans.set(ans.size() - 1, ans.get(ans.size() - 1) + 1);
            }   
        }
        answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
}
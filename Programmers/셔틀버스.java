//https://programmers.co.kr/learn/courses/30/lessons/17678

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        LocalTime localTime= LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"));
        
        PriorityQueue<LocalTime> pq = new PriorityQueue<>();
        
        for(var s : timetable){
            pq.add(LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm")));
        }
        
        for(int i = 0; i < n - 1; i++){
            int count = m;
            while(!pq.isEmpty()&& !pq.peek().isAfter(localTime) && count > 0){
                pq.poll();
                count--;
            }
            localTime = localTime.plusMinutes((long)t);
        }
        
        int count = m;
        while(!pq.isEmpty()&& !pq.peek().isAfter(localTime) && count > 0){
            count--;
            if(count == 0){
                answer = pq.peek().minusMinutes(1L).toString();
                break;
            }
            pq.poll();
        }
        
        if(count > 0) answer = localTime.toString();
        return answer;
    }
}

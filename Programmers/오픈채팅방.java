//https://programmers.co.kr/learn/courses/30/lessons/42888

import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        
        HashMap<String, String> map = new HashMap<>();
        int count = 0;
        for(var s : record){
            String[] ss = s.split(" ");
            
            if(ss[0].equals("Enter")) map.put(ss[1], ss[2]);
            else if(ss[0].equals("Change")) {
                map.put(ss[1], ss[2]); 
                count++;
            }
        }
        
        String[] answer = new String[record.length - count];
        
        int index = 0;
        for(int i = 0; i < record.length; i++){
            String[] ss = record[i].split(" ");
            String s ="";
            
            if(ss[0].equals("Change")) continue;
            
            if(ss[0].equals("Enter")) s = map.get(ss[1]) + "님이 들어왔습니다.";
            else if(ss[0].equals("Leave")) s = map.get(ss[1]) + "님이 나갔습니다.";
            answer[index++] = s;
        }
        return answer;
    }

}
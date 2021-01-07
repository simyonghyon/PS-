//https://programmers.co.kr/learn/courses/30/lessons/49993
import java.util.*;

class Solution {
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Character, Boolean> m = new HashMap<Character, Boolean>(26);
        for(int i = 0; i < 26; i++)
            m.put((char)('A' + i), false);
        for(int i = 0; i < skill.length(); i++){
            m.put(skill.charAt(i), true);
        }

        for(String i : skill_trees){
            int index = 0;
            boolean check = true;
            for(char k : i.toCharArray()){
                if(m.get(k) == true){
                    if(k != skill.charAt(index)){
                        check = false;
                        break;
                    }
                    index++;
                }
            }
            if(check == true) answer++;
        }
        return answer;
    }
}
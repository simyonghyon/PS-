// https://programmers.co.kr/learn/courses/30/lessons/77886#
// 생각못한 반례가 있어 결국 다른 사람의 반례를 보고 풀었다. 
// 참 문제부터 잘못읽고 시작했던지라... 나름 재밌었다 

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i = 0; i < s.length; i++){
            answer[i] = calcResult(s[i]);
        }
        
        return answer;
    }
    
    private String calcResult(String s){
        
        int one = 0;
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        
        while(true){
            
            one = getNextTripleOne(sb, one - 2 < 0 ? 0 : one - 2);
            if(one == -1) break;
            
            sb = sb.delete(one, one+3);
            count++;
        }
        
        int index = sb.lastIndexOf("0");
        if(index < 0) index = 0;
        else index += 1;
        
        for(int i = 0; i < count; i++) {
            sb.insert(index, "110");
            index += 3;
        }
        
        return sb.toString();
    }
    
    private int getNextTripleOne(StringBuilder s, int index){
        return s.indexOf("110", index);
    }
}

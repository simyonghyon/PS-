//https://programmers.co.kr/learn/courses/30/lessons/17687
// 설마 Integer 클래스에 진수변환이 있을거라 생각못했다....
// Integer.toStirng(n, radix) 하면 바로 풀린다...
// 밑에 첨부해 두었다

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int order = 1;
        StringBuilder ans = new StringBuilder("");
        int count = 0;
        
        for(int i = 0; ; i++){
            StringBuilder s = getN(n, i);
            
            for(int k = 0; k < s.length(); k++){
                if(order == p) {
                    ans.append(s.charAt(k));
                    count++;
                }
                
                order++;
                if(order > m) order = 1;
                if(count == t) break;
            }
            
            if(count == t) break;
        }
        
        answer = ans.toString();
        
        return answer;
    }
    
    private StringBuilder getN(int n, int m){
        if(m == 0) return new StringBuilder(String.valueOf(m));
        
        StringBuilder s = new StringBuilder("");
        while(m > 0){
            int num = m % n;
            
            if(num >= 10)
                s.append((char)('A' + (num % 10)));
            else 
                s.append(String.valueOf(num));
            
            m /= n;
        }
        return s.reverse();
    }
}

// 역시 자료구조에 대해 잘 알아야 한다!!!
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int order = 1;
        StringBuilder ans = new StringBuilder("");
        int count = 0;
        
        for(int i = 0; ; i++){
            String s = Integer.toString(i, n);
            
            for(int k = 0; k < s.length(); k++){
                if(order == p) {
                    ans.append(s.charAt(k));
                    count++;
                }
                
                order++;
                if(order > m) order = 1;
                if(count == t) break;
            }
            
            if(count == t) break;
        }
        
        answer = ans.toString().toUpperCase();
        
        return answer;
    }
}
import java.util.*;

class Solution {
    public String solution(String p) {
            
        return makeCorrect(p);
    }
    
    private String makeCorrect(String s) {
        if(isCorrect(s)) return s;
        int i;
        for(i = 1; i <= s.length(); i++) {
            if(isBalance(s.substring(0, i))) {
                break;
            }
        }
            
        String u = s.substring(0, i);
        String v = s.substring(i, s.length());
            
        if(isCorrect(u)) {
            v = makeCorrect(v);
            return u + v;
            
        } else {
            StringBuilder sb = new StringBuilder("(");
            sb.append(makeCorrect(v));
            sb.append(")");
            sb.append(reverseU(u));
            return sb.toString();
        }
    }
    
    private String reverseU(String s) {
        s = s.substring(1, s.length() - 1);
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) == ')' ? "(" : ")");
        }
        return sb.toString();
    }
    
    private boolean isBalance(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                count++;
            } else {
                count--;
            }
        }
        return count == 0;
    }
    
    private boolean isCorrect(String s) {
        Stack<Character> q = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == ')') {
                if(q.empty() || q.peek() != '(') {
                    return false;
                } else {
                    q.pop();
                }
            
            } else {
                q.push(c);
            }
        }
        
        return true;
    }
}
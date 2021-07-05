class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i = 0; i < s.length(); i++){
            Stack<Character> stack = new Stack();
            int index = i;
            boolean isvalid = false;
            for(int k = 0; k < s.length(); k++){
                if(index == s.length()) index = 0;
                char c = s.charAt(index);
                if(c == ']'){
                    if(stack.empty() || stack.peek() != '['){
                        isvalid = true;
                        break;
                    }
                    else stack.pop();
                }
                else if(c == '}'){
                    if(stack.empty() || stack.peek() != '{'){
                        isvalid = true;
                        break;
                    }
                    else stack.pop();
                }
                else if(c == ')'){
                    if(stack.empty() || stack.peek() != '('){
                        isvalid = true;
                        break;
                    }
                    else stack.pop();
                }
                else
                    stack.push(s.charAt(index));
                
                index++;
            }
            if(isvalid || !stack.empty()) answer--;
        }
        return answer;
    }
}
class Solution {
    String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if(Character.isAlphabetic(c)){
                for(int k = 0; k < 10; k++){
                    if(c == words[k].charAt(0)){
                        if(i + words[k].length() - 1 < s.length() && s.substring(i, i + words[k].length()).equals(words[k])){
                            answer *= 10;
                            answer += k;
                            i += words[k].length() - 1;
                            break;
                        }
                    }
                }
            } else {
                answer *= 10;
                answer += Integer.valueOf(s.charAt(i) - '0');
            }
        }
        
        return answer;
    }
}
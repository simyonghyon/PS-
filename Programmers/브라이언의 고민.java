// https://programmers.co.kr/learn/courses/30/lessons/1830?language=java#
// 다시는 처다도 안본다...

class Solution {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public static final String INVALID = "invalid";

    StringBuilder sb = new StringBuilder("");

    private boolean[] check = new boolean[27];

    public String solution(String sentence) {
        String answer = "";

        if(sentence.indexOf(" ") != -1) return INVALID;
        int len = sentence.length();
        for(int i = 0; i < len;){
            char c = sentence.charAt(i);
            //System.out.println(i);

            if(Character.isLowerCase(sentence.charAt(i))){

                if(check[sentence.charAt(i) - 'a']) return INVALID;
                check[sentence.charAt(i) - 'a'] = true;

                //int wordEnd = getWordRuleOne(sentence, i, sentence.charAt(i));

                int wordEnd = sentence.lastIndexOf(c);

                if(wordEnd == i + 1) return INVALID;
                if(wordEnd == i) return INVALID;

                String word = sentence.substring(i + 1, wordEnd);
                
                if(isAllUpper(word)) {
                    
                
                } else if(isRuleTwo(word)){
                    check[word.charAt(1) - 'a'] = true;
                    word = word.replace(String.valueOf(word.charAt(1)), "");
                    
                } else{
                    return INVALID;
                }
                
                sb.append(word + " ");

                i = wordEnd + 1;

            } else{
                // 대문자로 시작
                
                // 소문자 찾기
                int index = findLowerCase(sentence, i);
                
                // 다음 소문자가 없다면 다 같은 단어
                if(index == -1){
                    sb.append(sentence.substring(i));
                    sb.append(" ");
                    break;
                }
                
                // 이전에 썼는지 확인
                if(check[sentence.charAt(index) - 'a']) return INVALID;
                
                
                // 다음 소문자 중 가장 마지막에 있는 친구
                int lastWord = sentence.lastIndexOf(sentence.charAt(index));
                
                if(index + 1 == lastWord) return INVALID;
                
                // 소문자가 문장 가장 마지막에 있다면 rule 1 이다.
                if(lastWord == sentence.length() - 1){
                    // 현재꺼 붙이고 그냥 넘어감
                    sb.append(sentence, i, index);
                    sb.append(" ");
                    i = index;      
                
                } else if(Character.isLowerCase(sentence.charAt(lastWord + 1))){
                    // 마지막 소문자 다음께 소문자면 rule 1, 붙이고 넘어감
                    sb.append(sentence, i, index);
                    sb.append(" ");
                    i = index;
                
                } else {
                    // 마지막 소문자 다음이 대문자면 모름
                    
                    // rule 1 인지 확인
                    String word = sentence.substring(index, lastWord + 1);
                   
                    if(isRuleOne(word)){
                        sb.append(sentence, i, index);
                        sb.append(" ");
                        i = index;
                    
                    // 안되면 rule 2 되는지 확인
                    } else if(isRuleTwo(sentence.substring(index - 1, lastWord + 2))){

                        if(i + 1 < index){
                            sb.append(sentence, i, index - 1);
                            sb.append(" ");
                        }
                        
                        word = sentence.substring(index - 1, lastWord + 2);
                        word = word.replace(String.valueOf(sentence.charAt(index)), "");

                        sb.append(word + " ");
                        i = lastWord + 2;
                        
                        // 썼다고 체크
                        check[sentence.charAt(index) - 'a'] = true;
                        
                    } else{
                        sb.append(sentence, i, index);
                        sb.append(" ");
                        i = index;
                    }
              
                }
            }
            
            //System.out.println(sb);
        }

        
        return sb.charAt(sb.length() - 1) == ' ' ? sb.toString().substring(0, sb.length() - 1) : sb.toString();
    }

    // 단어 끝 반환
    public boolean isRuleTwo(String word){
        
        if(word.length() % 2 == 0) return false;

        char c = '-';

        boolean beforeUpper = false;

        for(int i = 0; i < word.length(); i++){
            if(beforeUpper && Character.isUpperCase(word.charAt(i))){
                return false;

            } else if(!beforeUpper && Character.isLowerCase(word.charAt(i))){
                return false;
            }

            if(Character.isLowerCase(word.charAt(i))){
                if(c == '-'){
                    c = word.charAt(i);

                } else if(c != word.charAt(i)) return false;
                
                if(check[c - 'a']) return false;
            }

            beforeUpper = !beforeUpper;
        }

        return true;
    }

    // 시작이 대문자일떄 다음 소문자 찾기 끝까지 못찾으면 -1 반환
    int findLowerCase(String sentence, int i){
        for(; i < sentence.length(); i++){
            if(Character.isLowerCase(sentence.charAt(i))) return i;
        }
        
        return -1;
    }
    
    private boolean isAllUpper(String word){
        for(int k = 0; k < word.length(); k++) 
            if(Character.isLowerCase(word.charAt(k))) return false;
        
        return true;
    }
    
    private boolean isRuleOne(String word){
        if(word.length() < 3) return false; 
        char c = word.charAt(0);
        
        if(check[c - 'a']) return false;

        word = word.substring(1, word.length() - 1);
        
        check[c - 'a'] = true;
        if(!isAllUpper(word) && !isRuleTwo(word)) {
            check[c - 'a'] = false;
            return false;
        }  
        check[c - 'a'] = false;
        return true;
    }
}
import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        ArrayList<Integer> score = new ArrayList<>();

        int lastScoreIndex = -1;
        for(int i = 0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);

            if('0' <= c && c <= '9'){
                String defaultScore = "";

                defaultScore += c;

                if('0' <= dartResult.charAt(i + 1) && dartResult.charAt(i + 1) <= '9'){
                    defaultScore += dartResult.charAt(i + 1);
                    i++;
                }

                score.add(Integer.valueOf(defaultScore));
                lastScoreIndex++;
            }
            else if(c == 'S'){}
            else if(c == 'D'){
                score.set(lastScoreIndex, (int)Math.pow((double)score.get(lastScoreIndex), 2));
            }
            else if(c == 'T'){
                score.set(lastScoreIndex, (int)Math.pow((double)score.get(lastScoreIndex), 3));
            }
            else if(c == '*'){
                score.set(lastScoreIndex, score.get(lastScoreIndex) * 2);

                if(lastScoreIndex != 0){
                    score.set(lastScoreIndex - 1, score.get(lastScoreIndex - 1) * 2);
                }
            } else if(c == '#'){
                score.set(lastScoreIndex, score.get(lastScoreIndex) * -1);
            }
        }

        for(var i : score){
            System.out.println(i);
            answer += i;
        }

        return answer;
    }
}
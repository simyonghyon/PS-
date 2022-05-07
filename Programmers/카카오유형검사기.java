import java.util.*;

class Solution {

        public String solution(String[] survey, int[] choices) {
            HashMap<String, Integer> point = new HashMap<>();
            point.put("R", 0);
            point.put("T", 0);
            point.put("C", 0);
            point.put("F", 0);
            point.put("J", 0);
            point.put("M", 0);
            point.put("A", 0);
            point.put("N", 0);

            for(int i = 0; i < survey.length; i++){
                if(choices[i] >= 5){
                    addPoint(point, survey[i].substring(1), choices[i] - 4);

                } else {
                    addPoint(point, survey[i].substring(0, 1), 4 - choices[i]);
                }
            }

            return calcFinalMBTI(point);
        }

        private void addPoint(HashMap<String, Integer> point, String friend, int choice){
            point.put(friend, point.get(friend) + choice);
        }

        private String calcFinalMBTI(HashMap<String, Integer> point){
            StringBuilder sb = new StringBuilder("");
            sb.append(comparePoint(point, "R", "T"));
            sb.append(comparePoint(point, "C", "F"));
            sb.append(comparePoint(point, "J", "M"));
            sb.append(comparePoint(point, "A", "N"));
            return sb.toString();
        }

        private String comparePoint(HashMap<String, Integer> point, String f1, String f2){
            if(point.get(f1) >= point.get(f2)){
                return f1;

            } else{
                return f2;
            }
        }


    class Node{
    }


}

// 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형
// 321 점수는 고정
// 성격 유형 점수가 같으면  사전순
// 설문지랑 답
// 첫번쨰까 비동의, 두번째가 동의
// 5<동의, 3>비동의

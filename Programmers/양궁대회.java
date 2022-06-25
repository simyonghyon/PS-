//https://programmers.co.kr/learn/courses/30/lessons/92342

class Solution {

    public int[] solution(int n, int[] info) {
        int[] ans = new int[1];
        ans[0] = -1;
        int[] lion = new int[info.length];

        int[] answer = dfs(ans, 0, lion, n, n, info);
        return answer;
    }

    int ansPointDifference = 0;
    private int[] dfs(int[] ans, int t, int[] lion, int remainArrow, int n, int[] info){
        if(t >= lion.length){
            int difference = getPointDifference(lion, info);
            if(remainArrow > 0) {
                lion[lion.length - 1] += remainArrow;
            }
            if(difference > 0 && difference >= ansPointDifference){
                if(ans[0] == -1){
                    ans = new int[lion.length];
                }
                if(!(difference == ansPointDifference && !isAns(ans, lion))){
                    for(int i = 0; i < lion.length; i++){
                        ans[i] = lion[i];
                    }
                    ansPointDifference = difference;
                }
            }
            lion[lion.length - 1] -= remainArrow;
            return ans;
        }

        // 해당 포인트 획득 실패
        ans = dfs(ans, t + 1, lion, remainArrow, n, info);
        if(remainArrow >= info[t] + 1){
            lion[t] = info[t] + 1;
            remainArrow = remainArrow - lion[t];
            // 해당 포인트 획득
            ans = dfs(ans, t + 1, lion, remainArrow, n, info);
            lion[t] = 0;
        }

        return ans;
    }

    private boolean isAns(int[] ans, int[] lion){
        for(int i = ans.length - 1; i >= 0; i--){
            if(ans[i] > lion[i]) return false;
            else if(ans[i] < lion[i]) return true;
        }
        return true;
    }

    private int getPointDifference(int[] lion, int[] info){
        int lionScore = 0, apacheScore = 0;
        for(int i = 0; i < lion.length; i++){
            if(lion[i] == 0 && info[i] == 0) continue;
            if(lion[i] > info[i]) lionScore += lion.length - i - 1;
            else if(lion[i] <= info[i]) apacheScore += lion.length - i - 1;
        }
        return lionScore - apacheScore;
    }
}
// 완탐
// 재귀
// 해당 점수를 먹었거나 안먹었거나
// 만약 남은 화살이 부족하면 리턴
// permu(크기 10 배열, 남은 화살, 누적점수)
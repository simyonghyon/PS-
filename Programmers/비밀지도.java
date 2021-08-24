//https://programmers.co.kr/learn/courses/30/lessons/17681?language=java

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i = 0; i < n; i++) {
        String s = "";
        
        for (int k = n - 1; k >= 0; k--) {
            if ((((1 << k & arr1[i]) | (1 << k & arr2[i]))) != 0) s += '#';
            else s += ' ';
        }

        answer[i] = s;
    }
        
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/42747

class Solution {
    public int solution(int[] array) {
        int answer = array.length;
        
        Arrays.sort(array);
        
        for(int i = 0; i < array.length; i++){
            if(array[i] < answer) answer--;
            else break;
        }
        return answer;
    }
}
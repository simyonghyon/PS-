//https://programmers.co.kr/learn/courses/30/lessons/12985

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        answer = tonurment(0, a, b);
        
        return answer;
    }
    
    private int tonurment(int round, int a, int b){
        if(a == b) return round;
        return tonurment(round + 1, (a + 1) / 2, (b + 1) / 2);
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/42628

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<Integer> q1 = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> q2 = new PriorityQueue<>();
        int count = 0;
        for(var a : operations){
            if(a.charAt(0) == 'I'){
                q1.add(Integer.valueOf(a.substring(2)));
                q2.add(Integer.valueOf(a.substring(2)));
                count++;
            }
            else{
                if(count > 0){
               if(a.charAt(2) == '-'){
                    int x = q2.poll();
                   q1.remove(x);
                   count--;
                   
                }
                else{
                    int x = q1.poll();
                    q2.remove(x);
                    count--;
                }
                }
            }
        }
        if(count == 0) answer[0] = answer[1] = 0;
        else{
            answer[0] = q1.peek();
            answer[1] = q2.peek();
        }
        return answer;
    }
}
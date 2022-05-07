import java.util.*;

class Solution {
//    private int[] dx = {1, 0, -1, 0};
 //   private int[] dy = {0, 1, 0, -1};

    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = 0;
        long sum2 = 0;
        int n = queue1.length;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int i = 0; i < n; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        int tmp = 0;
        while(true){
            if(sum1 == sum2) break;
            if(sum1 > sum2){
                int num = q1.poll();
                sum1 -= num;
                sum2 += num;
                q2.add(num);

            } else{
                int num = q2.poll();
                sum2 -= num;
                sum1 += num;
                q1.add(num);
            }
            tmp++;

            if(tmp > 3*n) return -1;
        }

        return tmp;
    }

}

// 두 개의 큐의 합이 같게
// 하나에서 뺴서 다른 큐에 넣음
// 필요한 최소 횟수
//

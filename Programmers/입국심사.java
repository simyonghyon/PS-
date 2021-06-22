//https://programmers.co.kr/learn/courses/30/lessons/43238

import static java.lang.Math.min;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        long right = times[0];
        for(var i : times){
            right = min(right, i);
        }
        right = right * n;

        while(left <= right){
            long mid = (left + right) / 2;
            long prcessedPerson = processedPerson(mid, times);
            if(prcessedPerson < n){
                left = mid + 1;
            }
            else if(prcessedPerson >= n){
                right = mid - 1;
            }
        }

        return answer = left;
    }

    private long processedPerson(long mid, int[] times){
        long processed = 0;

        for(var i : times){
            processed += mid / i;
        }
        return processed;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/86053#

import static java.lang.Math.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long highAns = (long) Math.pow(10, 15);
        long lowAns = 1l;
        while(lowAns <= highAns){
            long mid = (highAns + lowAns) / 2;
            //System.out.println(lowAns + " " + highAns);
            if(check(mid, a, b, g, s, w, t)) {
                highAns = mid - 1;

            } else{
                lowAns = mid + 1;
            }
        }

        answer = lowAns;
        return answer;
    }

    boolean check(long Mid, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long Total_Gold = 0;
        long Total_Silver = 0;
        long Total_Mineral = 0;

        for (int i = 0; i < g.length; i++) {
            long Time = t[i];

            long Round_Time = Time * 2;
            long Move_Cnt = Mid / Round_Time;
            if (Mid % Round_Time >= Time) Move_Cnt++;
            long Max_Take = w[i] * Move_Cnt;

            Total_Gold += min(g[i], Max_Take);
            Total_Silver += min(s[i], Max_Take);
            Total_Mineral += min(g[i] + s[i], Max_Take);
        }

        return (Total_Gold >= a && Total_Silver >= b && Total_Mineral >= a + b);
    }
}
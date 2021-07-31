class Solution {
    public int solution(String[] strs, String t) {
        int answer = 0;

        int[] dp = new int[t.length() + 1];

        dp[0] = 0;
        for(int i = 1; i <= t.length(); i++){
            dp[i] = 10000;
        }

        for(int i = 0; i < t.length(); i++){
            for(int k = 0; k < strs.length; k++){
                String s = strs[k];
                if(s.charAt(s.length() - 1) == t.charAt(i) && i + 1 >= s.length()){
                    //System.out.println(t.substring(i - s.length() + 1, i + 1));
                    if(s.equals(t.substring(i - s.length() + 1, i + 1))){
                        dp[i + 1] = Math.min(dp[i + 1], dp[i + 1 - s.length()] + 1);
                    }
                }
            }
        }
        //for(var i : dp) System.out.println(i);

        answer = dp[t.length()];
        if(answer == 10000) answer = -1;
        return answer;
    }
}

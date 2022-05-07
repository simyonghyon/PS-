class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        Problem[] problemList = new Problem[problems.length];

        // 필요한 능력치 최댓값
        int maxAlp = 0;
        int maxCop = 0;
        for(int i = 0; i < problems.length; i++){
            problemList[i] = new Problem(problems[i][0]
                    , problems[i][1], problems[i][2], problems[i][3], problems[i][4]);
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        // needTimeDp[alp][cop] = 시간
        int[][] needTimeDp = new int[151][151];
        // 편의를 위한 초기화
        for(int i = 0; i <= 150; i++){
            for(int k = 0; k <= 150; k++){
                needTimeDp[i][k] = 301;
            }
        }
        
        needTimeDp[alp][cop] = 0;
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(alp, cop));

        int ans = 301;

        while(!q.isEmpty()){
            Status status = q.poll();
            int currentAlp = status.getAlp();
            int currentCop = status.getCop();

            if(isGetEnoughStatus(maxAlp, maxCop, currentAlp, currentCop)){
                ans = Math.min(ans, needTimeDp[currentAlp][currentCop]);
                continue;
            }

            // alp 1증가
            if(currentAlp + 1 <= 150 && needTimeDp[currentAlp][currentCop] + 1 < needTimeDp[currentAlp + 1][currentCop]){
                needTimeDp[currentAlp + 1][currentCop] = needTimeDp[currentAlp][currentCop] + 1;
                q.add(new Status(currentAlp + 1, currentCop));
            }

            // cop 1증가
            if(currentCop + 1 <= 150 && needTimeDp[currentAlp][currentCop] + 1 < needTimeDp[currentAlp][currentCop + 1]){
                needTimeDp[currentAlp][currentCop + 1] = needTimeDp[currentAlp][currentCop] + 1;
                q.add(new Status(currentAlp, currentCop + 1));
            }

            for(int i = 0; i < problemList.length; i++){
                Problem problem = problemList[i];

                if(problem.canSolve(currentAlp, currentCop)) {
                    // 150이 넘는다면 150으로 변경
                    int next_alp = Math.min(currentAlp + problem.getAlp_rwd(), 150);
                    int next_cop = Math.min(currentCop + problem.getCop_rwd(), 150);
                    if (needTimeDp[currentAlp][currentCop] + problem.getCost() < needTimeDp[next_alp][next_cop]) {
                        needTimeDp[next_alp][next_cop] = needTimeDp[currentAlp][currentCop] + problem.getCost();
                        q.add(new Status(next_alp, next_cop));
                    }
                }
            }
        }

        return ans;
    }

    private boolean isGetEnoughStatus(int maxAlp, int maxCop, int currentAlp, int currentCop) {
        return currentAlp >= maxAlp && currentCop >= maxCop;
    }


}

class Status{
    private final int alp, cop;

    public Status(int alp, int cop) {
        this.alp = alp;
        this.cop = cop;
    }

    public int getAlp() {
        return alp;
    }

    public int getCop() {
        return cop;
    }
}

class Problem {
    private final int alp_req, cop_req, alp_rwd, cop_rwd, cost;

    Problem(int alp_req, int cop_req, int alp_rwd, int cop_rwd, int cost) {
        this.alp_req = alp_req;
        this.cop_req = cop_req;
        this.alp_rwd = alp_rwd;
        this.cop_rwd = cop_rwd;
        this.cost = cost;
    }

    public int getAlp_req() {
        return alp_req;
    }

    public int getCop_req() {
        return cop_req;
    }

    public int getAlp_rwd() {
        return alp_rwd;
    }

    public int getCop_rwd() {
        return cop_rwd;
    }

    public int getCost() {
        return cost;
    }

    public boolean canSolve(int alp, int cop){
        return alp_req <= alp && cop_req <= cop;
    }
}

// 알고력과 코딩력 모두 높아야 한다
// 1시간을 들여 알고력을 높인다
// 1시간을 들여 코딩력을 높인다
// 문제를 풀어 둘 중 하나 높인다, 정해져있음
// 문제푸는데 걸리는 시간은 정해져있다, 여러 번 풀기 가능
// 필요한 능력을 얻는 최단시간

// dp[alp][cop] = 시간
//https://programmers.co.kr/learn/courses/30/lessons/1832

class Solution {
    int MOD = 20170805;
    // m 세로, n 가로
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int [][][] p = new int[m][n][2];
        
        p[0][0][0] = p[0][0][1] = 1;
        
        for(int i = 1; i < m; i++){
            if(cityMap[i][0] != 1) p[i][0][0] = p[i - 1][0][0];
        }
        for(int k = 1; k < n; k++){
            if(cityMap[0][k] != 1) p[0][k][1] = p[0][k - 1][1];
        }
        
        for(int i = 1; i < m; i++){
            for(int k = 1; k < n; k++){
                if(cityMap[i][k] == 1) continue;
                
                calcu(p, cityMap, i, k);
            }
        }
        
        /*for(int i = 0; i < m; i++){
            for(int k = 0; k < n; k++){
                System.out.print(p[i][k][0] + p[i][k][1]);
            }
            System.out.println();
        }*/
        
        return (p[m - 1][n - 1][0] + p[m - 1][n - 1][1]) % 20170805;
    }
    
    private void calcu(int[][][] p, int[][] cityMap, int i, int k){
        int a, b;
        p[i][k][0] = p[i - 1][k][0];
        
        if(cityMap[i - 1][k] != 2){
            p[i][k][0] = (p[i][k][0] + p[i - 1][k][1]) % 20170805;    
        }
        
        p[i][k][1] = p[i][k - 1][1];
        
        if(cityMap[i][k - 1] != 2){
            p[i][k][1] = (p[i][k][1] + p[i][k - 1][0]) % 20170805;    
        }
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/43105

#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    vector<vector<int>> dp(triangle.size());
    dp[0].push_back(triangle[0][0]);
    
    for(int i = 1; i < triangle.size(); i++){
        for(int k = 0; k < triangle[i].size(); k++){
            int prev = 0;
            int next = 0;
            if(k - 1 >= 0) prev = dp[i - 1][k - 1];
            if(k < dp[i - 1].size()) next = dp[i - 1][k];
            dp[i].push_back(max(prev, next) + triangle[i][k]);
        }
    }
 
    vector<int> tmp = dp[triangle.size() - 1];
    return *max_element(tmp.begin(), tmp.end());
}
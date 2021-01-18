//https://programmers.co.kr/learn/courses/30/lessons/42627#qna

#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <queue>

using namespace std;

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    
    sort(jobs.begin(), jobs.end());
    
    vector<pair<int, int>> a;
    
    int end = 0;
    answer = 0;
    int i = 0;
   while(i < jobs.size()){
        a.push_back(make_pair(jobs[i][0], jobs[i][1]));
        end = jobs[i][0];
       i++;               
        while(!a.empty()){
            answer += ((end - a.back().first < 0) ? 0 : (end - a.back().first)) + a.back().second;
            end = end + a.back().second;
     
            a.pop_back();
            while(i < jobs.size() && jobs[i][0] <= end) {
                a.push_back(make_pair(jobs[i][0], jobs[i][1]));
                i++;  
            }
             sort(a.begin(), a.end(), [](pair<int, int> x, pair<int, int> y){
                    return x.second > y.second;
            });
        }
    }

    
    return answer / jobs.size();
}
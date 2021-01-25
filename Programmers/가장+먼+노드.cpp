//https://programmers.co.kr/learn/courses/30/lessons/49189

#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    priority_queue<pair<int, int>> q;
    vector<int> a(n + 1, 987654321);
    a[1] = 0;
    q.push(make_pair(1, 0));
    vector<vector<int>> b(n + 1);
    for(auto i : edge){
        b[i[0]].push_back(i[1]);
        b[i[1]].push_back(i[0]);
    }
    while(!q.empty()){
        int x = q.top().first;
        int dis = q.top().second;
        q.pop();
        
        for(int i = 0; i < b[x].size(); i++){
            if(dis + 1 < a[b[x][i]]) {
                a[b[x][i]] = dis + 1; 
                q.push(make_pair(b[x][i], dis + 1));
            }    
        }
    }
    int tmp = 0;
    for(int i = 1; i <= n; i++){
        //cout << a[i] << " ";
        if(a[i] > tmp){
            answer = 1;
            tmp = a[i];
        }
        else if(a[i] == tmp) answer++;
    }
    return answer;
}
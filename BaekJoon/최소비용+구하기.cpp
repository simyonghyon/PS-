// https://www.acmicpc.net/problem/1916

#include <iostream> 
#include <vector>
#include <algorithm>
#include <string>
#include <queue>
#include <deque>
#include <tuple>
#include <list>
#include <set>
#include <cmath>
#include <stack>
#include <map>
//#include<bits/stdc++.h>
#include<cstdio>
using namespace std;

#define For(i, n) for(int i = 0; i < n; i++)
#define IOS  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
#define endl  "\n";
typedef  long long ll;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };

int n, m;

int solution(int n, vector<vector<pair<int, int>>> edge, int start, int end) {
    int answer = 0;
   
    priority_queue<pair<int, int>> q;
    vector<int> a(n + 1, 1987654321);
    a[start] = 0;
    q.push(make_pair(0, start));
       
    while (!q.empty()) {
        int x = q.top().second;
        int dis = -1 * q.top().first;
        q.pop();

        for (int i = 0; i < edge[x].size(); i++) {
            if (dis + edge[x][i].second < a[edge[x][i].first]) {
                a[edge[x][i].first] = dis + edge[x][i].second;
                q.push(make_pair(-1 * (dis + edge[x][i].second), edge[x][i].first));
            }
            
        }
    }
    return a[end];
}

void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    
    vector<vector<pair<int, int>>> cost(n + 1);
    
    for (int i = 0; i < m; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        cost[x].push_back(make_pair(y, z));
    }

    int start, end;
    cin >> start >> end;
    cout << solution(n, cost, start, end);
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
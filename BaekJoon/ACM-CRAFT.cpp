// https://www.acmicpc.net/problem/1005

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

    
void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<int> period(n);
    For(i, n) cin >> period[i];
    vector<vector<int>> order(n);
    For(i, m) {
        int x, y;
        cin >> x >> y;
        order[y - 1].push_back(x - 1);
    }

    int root;
    cin >> root;
    root -= 1;
    
    vector<int> check(n, 0);
    queue<pair<int, int>> q;
    q.push(make_pair(root, 0));

    int answer = 0;

    while (!q.empty()) {
        int x = q.front().first;
        int periodX = q.front().second;
        q.pop();
        
        periodX += period[x];
        if (order[x].size() == 0) answer = max(answer, periodX);
        for (int i = 0; i < order[x].size(); i++) {
            if (check[order[x][i]] < periodX) {
                q.push(make_pair(order[x][i], periodX));
                check[order[x][i]] = periodX;
            }
        }
    }

    
    cout << answer << endl;
  
}


int main() {
    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
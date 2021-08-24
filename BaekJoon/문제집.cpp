//https://www.acmicpc.net/problem/1766

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
#include <unordered_map>
//#include<bits/stdc++.h>
#include<cstdio>

using namespace std;

#define For(i, n) for(int i = 0; i < n; i++)
#define IOS  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
#define endl  "\n";
#define IF(ny, nx, n, m) if(0 <= ny && ny < n && 0 <= nx && nx < m)
#define P pair<int, int> 
const int INF = 987654321;
typedef  long long ll;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


void solve() {
    IOS;
    int n, m;
    cin >> n >> m;

    vector<int> entryDegree(n + 1, 0);
    vector<vector<int>> degree(n + 1);

    For(i, m) {
        int x, y;
        cin >> x >> y;
        entryDegree[y]++;
        degree[x].push_back(y);
    }

    priority_queue<int> problem;
    
    for (int i = 1; i <= n; i++) {
        if (entryDegree[i] == 0) problem.push(-i);
    }

    while (!problem.empty()) {
        int index = -problem.top();
        problem.pop();

        cout << index << " ";

        for (auto i : degree[index]) {
            if(--entryDegree[i] == 0) problem.push(-i);
        }
    }
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
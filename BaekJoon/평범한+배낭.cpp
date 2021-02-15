//https://www.acmicpc.net/problem/12865

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
#define IF(ny, nx, n, m) if(0 <= ny && ny < n && 0 <= nx && nx < m)
#define P pair<int, int> 
typedef  long long ll;
int dx[] = { 1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2 };
int dy[] = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> a(n);
    For(i, n) {
        int x, y;
        cin >> x >> y;
        a[i] = P(x, y);
    }
    
    vector<vector<int>> dp(n, vector<int>(m + 1, 0));
    
    for (int i = a[0].first; i <= m; i++) {
        dp[0][i] = a[0].second;
    }
    
    for (int i = 1; i < n; i++) {
        
        for (int k = 0; k < a[i].first; k++) {
            dp[i][k] = dp[i - 1][k];
        }
        for (int k = a[i].first; k <= m; k++) {
            dp[i][k] = max(dp[i - 1][k - a[i].first] + a[i].second, dp[i - 1][k]);
        }
    }
    cout << dp[n - 1][m];
}

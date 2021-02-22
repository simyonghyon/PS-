//https://www.acmicpc.net/problem/1937

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

void panda(int n, int y, int x, vector<vector<int>>& a, vector<vector<int>>& dp) {
    if (dp[y][x] != 0) return;
    
    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];

        IF(ny, nx, n, n) {
            if (a[ny][nx] > a[y][x]) {
                panda(n, ny, nx, a, dp);
                dp[y][x] = max(dp[y][x], dp[ny][nx] + 1);
            }
        }
    }

    if (dp[y][x] == 0) dp[y][x] = 1;
    
    return;
}


void solve() {
    IOS;
    int n;
    cin >> n;
    vector<vector<int>> a(n, vector<int> (n));

    For(i, n)
        For(k, n) cin >> a[i][k];

    vector<vector<int>> dp(n, vector<int>(n, 0));

    int ans = 0;

    For(i, n)
        For(k, n) {
        panda(n, i, k, a, dp);
        ans = max(ans, dp[i][k]);
    }

    cout << ans;

}

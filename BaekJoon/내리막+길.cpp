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

int N, M;

int dfs(int y, int x, vector<vector<int>>& a, vector<vector<int>>& check) {
    
    if (check[y][x] != -1) return check[y][x];
    if (y == N - 1 && x == M - 1) return 1;

    check[y][x] = 0;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (0 <= nx && nx < M && 0 <= ny && ny < N) {
            if (a[ny][nx] < a[y][x]) check[y][x] += dfs(ny, nx, a, check);
        }
    }
    return check[y][x];
}
    
void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    N = n;
    M = m;
    vector<vector<int>> check(n, vector<int>(m, -1));
    vector<vector<int>> a(n, vector<int>(m));

    For(i, n)
        For(k, m)
        cin >> a[i][k];
   
    int answer = dfs(0, 0, a, check);
 
    cout << answer << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
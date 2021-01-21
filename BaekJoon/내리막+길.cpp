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
    vector<vector<int>> a(n, vector<int>(m));

    For(i, n)
        For(k, m)
        cin >> a[i][k];

    queue<pair<pair<int, int>, int>> q;
    q.push(make_pair(make_pair(0, 0), a[0][0]));
    int answer = 0;

    while (!q.empty()) {
        int y = q.front().first.first;
        int x = q.front().first.second;
        int height = q.front().second;
        q.pop();
        if (y == n - 1 && x == m - 1) answer++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                if (a[ny][nx] < height) q.push(make_pair(make_pair(ny, nx), a[ny][nx]));
            }
        }
    }
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
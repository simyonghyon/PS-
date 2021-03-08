//https://www.acmicpc.net/problem/17142

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
const int INF = 987654321;
typedef  long long ll;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

int n, m;

int que(vector<P>&virues, vector<int>& q1, vector<vector<int>>&a, int em) {
 
    vector<vector<int>> check(n, vector<int>(n, 0));
    
    int count = 0;

    queue<P> q;

    for (int i = 1; i < q1.size(); i++) {
        q.push(virues[q1[i]]);
        check[virues[q1[i]].first][virues[q1[i]].second] = 1;
    }

    int ans = INF;

    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        for (int j = 0; j < 4; j++) {
            int ny = y + dy[j];
            int nx = x + dx[j];

            IF(ny, nx, n, n) {
                if (check[ny][nx] == 0 && (a[ny][nx] == 0 || a[ny][nx] == 2)) {
                    q.push(P(ny, nx));
                    check[ny][nx] = check[y][x] + 1;
                    
                    if (a[ny][nx] == 0) {
                        ans = check[y][x];
                        count++;
                    }
                }
            }
        }
    }

	if(count != em) return INF;
    
    return ans;
}


int solved(int vi, vector<int>& q, vector<P>& virues, vector<vector<int>>& a, int em) {
    if (m == vi) {
        return que(virues, q, a, em);
    }

    int ret = INF;

    for (int i = q[vi] + 1; i < virues.size(); i++) {
        q.push_back(i);
        ret = min(ret, solved(vi + 1, q, virues, a, em));
        q.pop_back();
    }

    return ret;
}

void solve() {
    IOS;
   
    cin >> n >> m;
    
    vector<vector<int>> a(n, vector<int>(n));
    vector<P> virues;
    int em = 0;
    For(i, n)
        For(k, n) {
        cin >> a[i][k];
        if (a[i][k] == 0) em++;
        if (a[i][k] == 2) virues.push_back(P(i, k));
    }

    vector<int> q;
    q.push_back(-1);
	
    int ans = solved(0, q, virues, a, em);
    if (ans == INF) ans = -1;
	if(em == 0) ans = 0;
    cout << ans << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
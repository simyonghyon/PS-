//https://www.acmicpc.net/problem/1915

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
int dx[] = { 1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2 };
int dy[] = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };




void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    

    vector<string> vs(n);

    For(i, n) cin >> vs[i];

    vector<vector<int>> a(n, vector<int>(m, 0));

    int ans = 0;

    For(i, n) {
        For(k, m) {
            if (vs[i][k] == '0') a[i][k] = 0;
            else {
                if (i - 1 >= 0 && k - 1 >= 0) {
                    int mi = min(a[i - 1][k], a[i - 1][k - 1]);
                    mi = min(mi, a[i][k - 1]);
                    a[i][k] = mi + 1;    
                }
                else a[i][k] = 1;   
            }
            ans = max(ans, a[i][k]);
        }
    }

    cout << ans * ans;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
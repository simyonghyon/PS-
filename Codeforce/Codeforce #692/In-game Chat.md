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

    int n;
    string s;
    cin >> n >> s;

    for (int i = 0; i < n; i++) {
        if (s[n - i - 1] != ')') {
            if (i > n - i) {
                cout << "Yes\n";
                return;
            }
            else {
                break;
            }
        }
        if (i == n - 1) {
            cout << "Yes\n";
            return;
        }
    }

    cout << "No\n";
}


int main() {
    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
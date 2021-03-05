//https://judge.koreatech.ac.kr/problem.php?id=1171

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



void solve() {
    IOS;
    int n;
    cin >> n;
    vector<int> a(n);
    For(i, n) {
        cin >> a[i];
    }

    For(i, n) {
        int x;
        cin >> x;
        a[i] = a[i] - x;
    }
    
    int start = 0;
    int current = 0;
    int sum = 0;

    int ans = -1;

    vector<bool> check(n, false);
    check[0] = true;
    while (1) {

        sum += a[current];
        if (sum < 0) {
            start = current + 1;
            if (start == n) start = 0;
            if (check[start]) break;
            check[start] = true;
            current++;
            sum = 0;
        }
        else {
            current++;
            current = current == n ? 0 : current;
            if (start == current) {
                ans = start;
                break;
            }
        }
        if (current == n) {
            current = 0;
        }

    }

    cout << ans << endl;
}


int main() {
    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
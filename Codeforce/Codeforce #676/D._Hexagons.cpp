/*
* https://codeforces.com/contest/1421/problem/D
*/

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
int dy[] = { 0, -1, 0, 1 };

void solved() {
    IOS;
    ll n, m;
    cin >> n >> m;
    ll c1, c2, c3, c4, c5, c6;
    cin >> c1 >> c2 >> c3 >> c4 >> c5 >> c6;
    c1 = min(c1, c2 + c6);
    c2 = min(c2, c1 + c3);
    c3 = min(c3, c2 + c4);
    c4 = min(c4, c3 + c5);
    c5 = min(c5, c4 + c6);
    c6 = min(c6, c1 + c5);
    ll ans = 0;
    if (n >= 0 && m >= 0) {
        if (n <= m) {
            ans += n * c1 + (m - n) * c2;
        }
        else {
            ans += m * c1 + (n - m) * c6;
        }
    }
    else if (n <= 0 && m <= 0) {
        if (n >= m) {
            ans += -1 * n * c4 + (n - m) * c5;
        }
        else {
            ans += -1 * m * c4 + (m - n) * c3;
        }
    }
    else if (n >= 0 && m <= 0) {
        ans += n * c6 + -1 * m * c5;
    }
    else if (n <= 0 && m >= 0) {
        ans += -1 * n * c3 + m * c2;
    }
    cout << ans << endl;
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
//https://codeforces.com/contest/1440/problem/B

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

int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, -1, 0, 1 };

void solved() {
    IOS;
    IOS;
    int n, m;
    cin >> n >> m;
    vector<ll> a(n * m);
    For(i, n * m) cin >> a[i];
    int index = a.size();
    ll ans = 0;
    int b = (n + 2) / 2;
    for (int i = 0; i < m; i++) {
        index -= b;
        ans += a[index];
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


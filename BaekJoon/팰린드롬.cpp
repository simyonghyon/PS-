//https://www.acmicpc.net/problem/10942

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

void isPalin(int f, int l, vector<vector<int>>& dp, vector<int>& a) {
    if (f > l) {
        if (a[f] == a[l]) dp[f][l] = 1;
        else dp[f][l] = 2;
        return;
    }
    if (f == l) {
        dp[f][l] = 1;
        return;
    }

    if(dp[f + 1][l - 1] == 0) isPalin(f + 1, l - 1, dp, a);
    
    if(dp[f + 1][l - 1] == 1 && a[f] == a[l]) {
        dp[f][l] = 1;
    }
    else {
        dp[f][l] = 2;
    }

    return;
}


void solve() {
    IOS;
    int n;
    cin >> n;
    vector<int> a(n);

    For(i, n) cin >> a[i];

    int m;
    cin >> m;

    vector<vector<int>> dp(n, vector<int>(n, 0));
    
    while (m--) {
        int f, l;
        cin >> f >> l;
        f--; l--;

        if (dp[f][l] == 0) isPalin(f, l, dp, a);

        if (dp[f][l] == 1) cout << "1\n";
        else if (dp[f][l] == 2) cout << "0" << endl;
    }

}

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

int n;
int cost[16][16];
int dp[16][(1 << 16)];

int salesMan(int cur, int stat) {
    if (stat == (1 << n) - 1) {
        if (cost[cur][0] == 0) return INF;
        else return cost[cur][0];
    }

    int ref = dp[cur][stat];
    if (ref != 0) return ref;
    ref = INF;

    for (int i = 0; i < n; i++) {
        if (((stat & (1 << i)) == 0) && cost[cur][i] != 0) {
            ref = min(ref, salesMan(i, stat | (1 << i)) + cost[cur][i]);
        }
    }

    return dp[cur][stat] = ref;
}

void solve() {
    IOS;
    
    cin >> n;
    for (int i = 0; i < n; i++)
        for (int k = 0; k < n; k++) cin >> cost[i][k];

    
    cout << salesMan(0, 1);
}

//https://www.acmicpc.net/problem/1806

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
int dx[] = { 1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2 };
int dy[] = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<int> a(n, 0);
    For(i, n) cin >> a[i];

    int sum = 0;
    int l = 0;
    int length = 0;
    int ans = 1000000;

    For(i, n) {
        sum += a[i];
        length += 1;

        if (sum >= m) {
            while (sum - a[l] >= m) {
                sum -= a[l];
                l++;
                length--;
            }
            ans = min(ans, length);
        }
    }
    ans = ans == 1000000 ? 0 : ans;
    cout << ans;
}

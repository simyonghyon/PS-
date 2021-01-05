//[https://codeforces.com/contest/1440/problem/A

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
    int n, c0, c1, h;
    cin >> n >> c0 >> c1 >> h;
    string s;
    cin >> s;
    int cost_0 = min(c0, c1 + h);
    int cost_1 = min(c1, c0 + h);
    int ans = 0;
    for (auto i : s) {
        ans += (i == '1') ? cost_1 : cost_0;
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
 
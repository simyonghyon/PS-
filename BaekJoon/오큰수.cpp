//https://www.acmicpc.net/problem/17298

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
int dy[] = { 0, 1, 0, -1 };

void solve() {
    IOS;
    int n;
    cin >> n;
    stack<int> s;
    vector<int> a(n);
    vector<int> ans(n);
    For(i, n) cin >> a[i];

    for (int i = n - 1; i >= 0; i--) {
        while (!s.empty() && s.top() <= a[i]) {
            s.pop();
        }
        if (s.empty()) ans[i] = -1;
        else ans[i] = s.top();
        s.push(a[i]);
    }
    for (auto i : ans) cout << i << " ";
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
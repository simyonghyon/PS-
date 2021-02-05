//https://www.acmicpc.net/problem/1197

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
int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
int dy[] = { 0, 1, 0, -1, 1, -1, -1, 1 };

void solve() {
    IOS;
    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> a(n);

    For(i, m) {
        int x, y, z;
        cin >> x >> y >> z;
        a[x - 1].push_back(make_pair(y - 1, z));
        a[y - 1].push_back(make_pair(x - 1, z));
    }
    
    vector<bool> check(n, false);
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    q.push(make_pair(0, 0));
    int ans = 0;

    while (!q.empty()) {
        int sum = q.top().first;
        int edge = q.top().second;
        q.pop();

        if (check[edge]) continue;
        ans += sum;
        check[edge] = true;

        for (int i = 0; i < a[edge].size(); i++) {
            q.push(make_pair(a[edge][i].second, a[edge][i].first));
        }
    }
   
    cout << ans;
}

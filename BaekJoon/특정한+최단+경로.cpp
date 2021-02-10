//https://www.acmicpc.net/problem/1504

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
typedef  long long ll;
int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
int dy[] = { 0, 1, 0, -1, 1, -1, -1, 1 };

bool check = false;

int da(int n, int s, int t, vector<vector<pair<int, int>>>& a) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, less<pair<int, int>>> pq;
    vector<int> dis(n, 987654321);
    dis[s] = 0;
    pq.push(make_pair(0, s));

    while (!pq.empty()) {
        int edge = pq.top().second;
        int y = pq.top().first;
        pq.pop();

        for (int i = 0; i < a[edge].size(); i++) {
            if (dis[edge] + a[edge][i].second < dis[a[edge][i].first]) {
                dis[a[edge][i].first] = dis[edge] + a[edge][i].second;
                pq.push(make_pair(a[edge][i].second, a[edge][i].first));
            }
        }
    }
    if (dis[t] == 987654321) check = true;
    
    return dis[t];
}

void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<vector<pair<int, int>>> a(n);
    For(i, m) {
        int x, y, z;
        cin >> x >> y >> z;
        x -= 1; y -= 1;
        a[x].push_back(make_pair(y, z));
        a[y].push_back(make_pair(x, z));
    }

    int tar1, tar2;
    cin >> tar1 >> tar2;
    tar1--; tar2--;
    int ta = da(n, tar1, tar2, a);

    int ans1 = da(n, 0, tar1, a) + ta + da(n, tar2, n - 1, a);
    int ans2 = da(n, 0, tar2, a) + ta + da(n, tar1, n - 1, a);
    if (check) cout << "-1";
    else cout << min(ans1, ans2);
}

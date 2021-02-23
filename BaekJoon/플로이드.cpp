// https://www.acmicpc.net/problem/11404

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




void solve() {
    IOS;
    int n, m;
    cin >> n >> m;

    vector<vector<P>> a(n);

    For(i, m) {
        int x, y, z;
        cin >> x >> y >> z;
        x--; y--;
        a[x].push_back(P(y, z));
    }

    For(i, n) {
        vector<int> cost(n, INF);
        cost[i] = 0;

        priority_queue<int, vector<int>, less<int>> pq;
        pq.push(i);

        while (!pq.empty()) {
            int x = pq.top();
            pq.pop();

            for (int k = 0; k < a[x].size(); k++) {
                if (a[x][k].second + cost[x] < cost[a[x][k].first]) {
                    cost[a[x][k].first] = a[x][k].second + cost[x];
                    pq.push(a[x][k].first);
                }
            }
        }
        for (auto k : cost) {
            
            cout << (k == INF ? 0 : k) << " ";
        }
        cout << endl;
    }

}

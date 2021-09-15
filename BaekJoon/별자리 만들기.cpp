//https://www.acmicpc.net/problem/4386

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
#include <unordered_map>
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
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

int ans = INF;

struct dis {
    int a, b;
    double d;

    dis(int a, int b, double d) {
        this->a = a;
        this->b = b;
        this->d = d;
    }
};

struct cmp {
    bool operator()(dis a, dis b) {
        return a.d > b.d;
    }
};

int fi(vector<int>& parent, int tmp) {
    if (parent[tmp] == tmp) return tmp;

    return parent[tmp] = fi(parent, parent[tmp]);
}

double uni(vector<int>& size, vector<int>& parent, dis dis) {
    int ap = fi(parent, dis.a);
    int bp = fi(parent, dis.b);

    if (ap == bp) return 0;

    if (size[ap] > size[bp]) {
        parent[bp] = ap;
        size[ap] += size[bp];
    }
    else {
        parent[ap] = bp;
        size[bp] += size[ap];
    }

    return dis.d;
}



void solve() {
    IOS;
    int n;
    cin >> n;

    vector<pair<double, double>> a(n);

    For(i, n) {
        double x, y;
        cin >> x >> y;
        a[i] = pair<double, double>(x, y);
    }

    vector<vector<double>> distance(n, vector<double>(n, 0));
    priority_queue<dis, vector<dis>, cmp> pq;
    

    for (int i = 0; i < n; i++) {
        for (int k = i; k < n; k++) {
            if (i == k) continue;

            distance[i][k] = sqrt(pow(a[i].first - a[k].first, 2) + pow(a[i].second - a[k].second, 2));
            pq.push(dis(i, k, distance[i][k]));
        }
    }

    vector<int> parent(n);
    For(i, n) parent[i] = i;
    vector<int> size(n, 1);

    double ans = 0;
    while (!pq.empty()) {
        dis dis = pq.top();
        pq.pop();

        ans += uni(size, parent, dis);
    }

    cout << ans << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
//https://www.acmicpc.net/problem/1043

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
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

int fi(int x, vector<int>&parent) {
    if (x == parent[x]) return x;
    return parent[x] = fi(parent[x], parent);
}

void uni(int x, int y, vector<int>& parent, vector<int>& si) {
    int a = fi(x, parent);
    int b = fi(y, parent);

    if (a == b) return;

    if (si[a] > si[b]) {
        parent[b] = a;
        si[a] += si[b];
    }
    else {
        parent[a] = b;
        si[b] += si[a];
    }
}



void solve() {
    int n, m;
    cin >> n >> m;

    int tmp;
    cin >> tmp;

    vector<int> parent(n);
    vector<int> si(n, 1);
    
    For(i, n) parent[i] = i;


    int truth;
    if (tmp == 0) truth = -1;
    else {
        cin >> truth;
        truth--;
        parent[truth] = truth;
    }

    For(i, tmp - 1) {
        int x;
        cin >> x;
        parent[x - 1] = truth;
    }

    vector<vector<int>> party(m);

    For(i, m) {
        int j;
        cin >> j;
        

        vector<int> tmp(j);

        if (j != 0) {
            cin >> tmp[0];
            tmp[0]--;
            fi(tmp[0], parent);

            for (int k = 1; k < j; k++) {
                cin >> tmp[k];
                tmp[k]--;
                uni(tmp[0], tmp[k], parent, si);
            }
        }

        party[i] = tmp;
    }

    int ans = 0;

    int c = 0;
    for (auto i : party) {
        ans++;
        for (auto k : i) {
            if (truth != -1 && fi(k, parent) == fi(truth, parent)) {
          
                ans--;
                break;
            }
        }
        c++;
    }

    cout << ans;
}

int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
//https://www.acmicpc.net/problem/16235

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

void summer(int deadTree, vector<vector<int>>& nutri, int i, int k) {
    nutri[i][k] += deadTree;
}

void spring(vector<vector<vector<int>>>& treeOfArea, vector<vector<int>>& nutri, int n) {


    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            vector<int> s;
            int deadTree = 0;
            sort(treeOfArea[i][k].begin(), treeOfArea[i][k].end());
            for (auto j : treeOfArea[i][k]) {
                if (nutri[i][k] >= j) {
                    nutri[i][k] -= j;
                    s.push_back(j + 1);
                }
                else {
                    deadTree += j / 2;
                }
            }
            treeOfArea[i][k] = s;
            summer(deadTree, nutri, i, k);
        }
    }
}



void fall(int n, vector<vector<vector<int>>>& treeOfArea) {
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            for (auto j : treeOfArea[i][k]) {
                if (j % 5 == 0) {
                    for (int p = 0; p < 8; p++) {
                        int ny = i + dy[p];
                        int nx = k + dx[p];
                        if (0 <= ny && ny < n && 0 <= nx && nx < n) treeOfArea[ny][nx].push_back(1);
                    }
                }
            }
        }
    }
}

void winter(vector<vector<int>>& a, vector<vector<int>>& nutri, int n) {
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            nutri[i][k] += a[i][k];
        }
    }
}

void solve() {
    IOS;
    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> a(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }

    vector<vector<vector<int>>> treeOfArea(n, vector<vector<int>>(n));
    vector<vector<int>> nutri(n, vector<int>(n, 5));



    for (int i = 0; i < m; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        treeOfArea[x - 1][y - 1].push_back(z);
    }

    while (k--) {
        spring(treeOfArea, nutri, n);
        fall(n, treeOfArea);
        winter(a, nutri, n);
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (auto q : treeOfArea[i][j]) cout << q << ",";
                cout << " ";
            }
            cout << endl;
        }
        cout << endl;
        */
    }
    int answer = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            answer += treeOfArea[i][j].size();
    }
    cout << answer << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
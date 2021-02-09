//https://www.acmicpc.net/problem/2573

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

int n, m;

void melt(vector<vector<int>>& a) {
    vector<vector<int>> tmp = a;

    for (int i = 0; i < n; i++) {
        for (int k = 0; k < m; k++) {
            if (a[i][k] <= 0) continue;

            for (int j = 0; j < 4; j++) {
                int ny = i + dy[j];
                int nx = k + dx[j];
                
                IF(ny, nx, n, m) {
                    if (a[ny][nx] <= 0) tmp[i][k]--;
                }
            }
            
        }
    }

    a = tmp;
}

bool isSeperate(const vector<vector<int>>& a) {
    queue<pair<int, int>> q;
    vector<vector<int>> check(n, vector<int> (m, 0));

    int count = 0;
    For(i, n) {
        For(k, m) {
            if (a[i][k] > 0 && check[i][k] == 0) {
                count++;
                if (count > 1) break;
                q.push(make_pair(i, k));
                check[i][k] = count;

                while (!q.empty()) {
                    int y = q.front().first;
                    int x = q.front().second;
                    q.pop();

                    for (int j = 0; j < 4; j++) {
                        int ny = y + dy[j];
                        int nx = x + dx[j];

                        IF(ny, nx, n, m) {
                            if (check[ny][nx] == 0 && a[ny][nx] > 0) {
                                q.push(make_pair(ny, nx));
                                check[ny][nx] = count;
                            }
                        }
                    }
                }
            }
              
        }
    }
    return count > 1;
}

bool isMelt(const vector<vector<int>>& field) {
    For(i, n)
        For(k, m) if (field[i][k] > 0) return false;
    return true;
}

void solve() {
    IOS;
    
    cin >> n >> m;

    vector<vector<int>> field(n, vector<int> (m));

    For(i, n)
        For(k, m) cin >> field[i][k];
 
    int ans = 0;
    while (!isSeperate(field)) {
        if (isMelt(field)) {
            ans = 0;
            break;
        }
        melt(field);
        ans++;
    }

    cout << ans;
}
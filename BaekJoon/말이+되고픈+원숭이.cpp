//https://www.acmicpc.net/problem/1600

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
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

typedef struct {
    int y, x, count, horse;
}mon;

void solve() {
    IOS;
    int k, n, m;
    cin >> k >> m >> n;

    vector<vector<int>> a(n, vector<int>(m));
    For(i, n)
        For(j, m) cin >> a[i][j];

    vector<vector<vector<bool>>> check(n, vector<vector<bool>> (m, vector<bool>(k + 1, false)));

    check[0][0][0] = true;
    queue<mon> q;

    if (a[0][0] == 0) {
        mon m;
        m.y = 0; m.x = 0, m.count = 0, m.horse = 0;
        q.push(m);
    }

    while (!q.empty()) {
        int y = q.front().y;
        int x = q.front().x;
        int z = q.front().horse;
        int p = q.front().count;
        q.pop();

        if (y == n - 1 && x == m - 1) {
            cout << p;
            return;
        }

        if (z < k) {
            for (int i = 0; i < 8; i++) {
                int ny = y + hy[i];
                int nx = x + hx[i];

                IF(ny, nx, n, m) {
                    if (a[ny][nx] == 0) {
                        if (check[ny][nx][z + 1] == false) {
                            check[ny][nx][z + 1] = true;
                            mon m;
                            m.y = ny, m.x = nx, m.count = p + 1, m.horse = z + 1;
                            q.push(m);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            IF(ny, nx, n, m) {
                if (a[ny][nx] == 0) {
                    if (check[ny][nx][z] == false) {
                        check[ny][nx][z] = true;
                        mon m;
                        m.y = ny, m.x = nx, m.count = p + 1, m.horse = z;
                        q.push(m);
                    }
                }
            }
        }
    }

    cout << -1;
}

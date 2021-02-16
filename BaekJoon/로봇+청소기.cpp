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
int dx[] = { 1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2 };
int dy[] = { 0, 1, 0, -1, -1, -2, -2, -1, 1, 2, 2, 1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

int rx[] = { 0, 1, 0, -1 };
int ry[] = { -1, 0, 1, 0 };

void solve() {
    //IOS;
    int n, m;
    cin >> n >> m;
    vector<vector<int>> a(n, vector<int>(m));
    int y, x, z;
    cin >> y >> x >> z;
    int count = 1;

    For(i, n)
        For(k, m) cin >> a[i][k];

    a[y][x] = 2;

    while (1) {
        bool check = false;
        int tz = z;

        //cout << z << endl;

        /*For(i, n) {
            For(k, m) printf("%2d ", a[i][k]);
            cout << endl;
        }*/

        for (int i = 0; i < 4; i++) {
            tz--;
            if (tz == -1) tz = 3;
            int ny = y + ry[tz];
            int nx = x + rx[tz];
            if (a[ny][nx] == 0) {
                y = ny;
                x = nx;
                z = tz;
                a[ny][nx] = 2;
                check = true;
                count++;
                break;
            }
        }

        if (check) continue;

        tz--;
        if (tz == -1) tz = 3;
        tz--;
        if (tz == -1) tz = 3;

        int ny = y + ry[tz];
        int nx = x + rx[tz];

        if (a[ny][nx] == 1) break;

        y = ny;
        x = nx;
    }
    
    cout << count;
}

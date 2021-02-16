//https://www.acmicpc.net/problem/14499

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

int rx[] = { 1, -1, 0, 0 };
int ry[] = { 0, 0, -1, 1 };

void north(vector<int>& dice) {
    int tmp = dice[0];
    dice[0] = dice[1];
    swap(dice[4], tmp);
    swap(dice[5], tmp);
    swap(dice[1], tmp);
}

void south(vector<int>& dice) {
    int tmp = dice[4];
    dice[4] = dice[5];
    swap(dice[0], tmp);
    swap(dice[1], tmp);
    swap(dice[5], tmp);
}

void east(vector<int>& dice) {
    int tmp = dice[2];
    dice[2] = dice[5];
    swap(dice[0], tmp);
    swap(dice[3], tmp);
    swap(dice[5], tmp);
}

void west(vector<int>& dice) {
   
    int tmp = dice[0];
    dice[0] = dice[3];
    swap(dice[2], tmp);
    swap(dice[5], tmp);
    swap(dice[3], tmp);
}


void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<vector<int>> a(n, vector<int>(m));
    int y, x, z;
    cin >> y >> x >> z;
    For(i, n)
        For(k, m) cin >> a[i][k];

    vector<int> dice(6, 0);

    For(i, z) {
        int k;
        cin >> k;
        k--;
        int ny = y + ry[k];
        int nx = x + rx[k];

        IF(ny, nx, n, m) {
            if (k == 0) east(dice);
            if (k == 1) west(dice);
            if (k == 2) north(dice);
            if (k == 3) south(dice);

            if (a[ny][nx] == 0) {
                a[ny][nx] = dice[0];
            }
            else {
                dice[0] = a[ny][nx];
                a[ny][nx] = 0;
            }

            cout << dice[5] << endl;
            y = ny; x = nx;
          
        }
    }
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
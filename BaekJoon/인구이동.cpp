//https://www.acmicpc.net/problem/16234

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


void solve() {
    IOS;
    int n, l, r;

    cin >> n >> l >> r;

    vector<vector<int>> a(n, vector<int>(n));

    For(i, n)
        For(k, n) cin >> a[i][k];

    int ans = 0;

    while (1) {
        vector<vector<int>> tmp(n, vector<int>(n, -1));
        vector<int> value;
        int index = 0;
        bool check = false;
        For(i, n) {
            For(k, n) {

                if (tmp[i][k] == -1) {
                    int sum = a[i][k]; 
                    int size = 1;
                    tmp[i][k] = index;

                    queue<P> q;
                    q.push(P(i, k));

                    while (!q.empty()) {
                        int y = q.front().first;
                        int x = q.front().second;
                        //cout << y << " " << x << endl;
                        q.pop();
                        


                        for (int j = 0; j < 4; j++) {
                            int ny = y + dy[j];
                            int nx = x + dx[j];

                            IF(ny, nx, n, n) {
                                if (l <= abs(a[ny][nx] - a[y][x]) && abs(a[ny][nx] - a[y][x]) <= r && tmp[ny][nx] == -1) {
                                    q.push(P(ny, nx));
                                    sum += a[ny][nx];
                                    size++;
                                    tmp[ny][nx] = index;
                                    check = true;
                                }
                            }
                        }
                    }

                    value.push_back(sum / size);
                    index++;
                }

            }
        }

        For(i, n) {
            For(k, n) {
                a[i][k] = value[tmp[i][k]];
            }
        }
     

        if (!check) break;
        ans++;
    }

    cout << ans;
}

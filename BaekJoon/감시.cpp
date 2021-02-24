//https://www.acmicpc.net/problem/15683

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
int n, m;

class CCTV {


public:
    int y, x;
    int kind;
    vector<vector<int>> direction;

    CCTV(int k, int y, int x) {
        this->y = y;
        this->x = x;
        kind = k;
        if (k == 1) {
            direction = vector<vector<int>>(4, vector<int> (1));
            For(i, 4) direction[i][0] = i;
        }
        else if (k == 2) {
            direction = vector<vector<int>>(2, vector<int>(2));
            direction[0][0] = 0; direction[0][1] = 2;
            direction[1][0] = 1; direction[1][1] = 3;
        }
        else if (k == 3) {
            direction = vector<vector<int>>(4, vector<int>(2));
            for (int i = 0; i < 4; i++) {
                for (int k = 0; k < 2; k++) {
                    direction[i][k] = i + k;
                    if (direction[i][k] == 4) direction[i][k] = 0;
                }
            }
        }
        else if (k == 4) {
            direction = vector<vector<int>>(4, vector<int>(3));
            for (int i = 0; i < 4; i++) {
                for (int k = 0; k < 3; k++) {
                    direction[i][k] = (i + k) % 4;
                }
            }
        }
        else if (k == 5) {
            direction = vector<vector<int>>(1, vector<int>(4));
            for (int i = 0; i < 4; i++) direction[0][i] = i;
        }
    }
};


void fillRoom(vector<vector<int>>& a, int y, int x, int dir, vector<vector<int>>& room, int kindOfFill) {

    while (1) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        IF(ny, nx, n, m) {
            if (a[ny][nx] == 6) break;

            room[ny][nx] += kindOfFill;
            y = ny;
            x = nx;
        }
        else break;
    }
}

int count(vector<vector<int>>& room) {
    int coun = 0;
    For(i, n)
        For(k, m) if (room[i][k] == 0) coun++;
    return coun;
}

int asdf(vector<vector<int>>& a, int ret, vector<CCTV>& cctvInRoom, vector<vector<int>>& room) {
    
    if (ret == cctvInRoom.size()) {
        For(i, n) {
            For(k, m) cout << room[i][k] << " ";
            cout << endl;
        }
        cout << endl;
        return count(room);

    }

    int ans = INF;
    
    CCTV cctv = cctvInRoom[ret];
    for (int i = 0; i < cctv.direction.size(); i++) {
        room[cctv.y][cctv.x] -= 1;
        for (int k = 0; k < cctv.direction[i].size(); k++) {    
            fillRoom(a, cctv.y, cctv.x, cctv.direction[i][k], room, -1);        
        }
        ans = min(ans, asdf(a, ret + 1, cctvInRoom, room));
        room[cctv.y][cctv.x] += 1;
        for (int k = 0; k < cctv.direction[i].size(); k++) {    
            fillRoom(a, cctv.y, cctv.x, cctv.direction[i][k], room, 1);
        }
    }

    return ans;
}



void solve() {
    IOS;

    cin >> n >> m;
    
    vector<vector<int>> a(n, vector<int>(m));
    vector<CCTV> cctvInRoom;
    vector<vector<int>> room(n, vector<int>(m, 0));

    For(i, n) {
        For(k, m) {
            cin >> a[i][k];
            if (a[i][k] == 0) continue;
            if (a[i][k] == 6) room[i][k] = 6;
            else {
                CCTV cctv(a[i][k], i, k);
                cctvInRoom.push_back(cctv);
            }
        }
    }
    cout << asdf(a, 0, cctvInRoom, room);
}


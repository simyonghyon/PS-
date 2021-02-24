//https://www.acmicpc.net/problem/17143

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

struct Shark {
    int y, x, speed, direction, size;
    Shark(int y, int x, int speed, int direction, int size) {
        this->y = y;
        this->x = x;
        this->speed = speed;
        this->direction = direction;
        this->size = size;
    }
    Shark() {}
};

void sharkMove(vector<Shark>& sharkList, vector<vector<int>>& field, int i, vector<int>& r, vector<int>& c, vector<bool>& del)
{
    Shark shark = sharkList[i];
    int y = shark.y;
    int x = shark.x;


    if (shark.direction == 1) {
        y = ((y + r.size()) - (shark.speed % r.size())) % r.size();
    }
    else if (shark.direction == 2) {
        y = (y + shark.speed) % r.size();
    }
    else if (shark.direction == 3) {
        x = (x + shark.speed) % c.size();
    }
    else if (shark.direction == 4) {
        x = ((x + c.size()) - (shark.speed % c.size())) % c.size();
    }

    shark.y = y;
    shark.x = x;
 
    if (field[r[y]][c[x]] != -1) {
        if (sharkList[field[r[y]][c[x]]].size < shark.size) {
            del[field[r[y]][c[x]]] = true;
            field[r[y]][c[x]] = i;
        }
        else del[i] = true;
    }
    else field[r[y]][c[x]] = i;
    sharkList[i] = shark;
}

void solve() {
    IOS;
    int n, m, p;
    cin >> n >> m >> p;

    vector<Shark> sharkList(p);
    vector<vector<int>> field(n, vector<int>(m, -1));
    vector<bool> del(p, false);


    For(i, p) {
        Shark shark;
        cin >> shark.y >> shark.x >> shark.speed >> shark.direction >> shark.size;
        shark.y--; shark.x--;
        sharkList[i] = shark;

        field[shark.y][shark.x] = i;
    }

    vector<int> r(n * 2 - 2);
    For(i, n) r[i] = i;
    for (int i = 2; i < n; i++) r[n + i - 2] = n - i;

    vector<int> c(m * 2 - 2);
    For(i, m) c[i] = i;
    for (int i = 2; i < m; i++) c[m + i - 2] = m - i;

    int ans = 0;

    for (int i = 0; i < m; i++) {
        for (int k = 0; k < n; k++) {
            if (field[k][i] != -1) {
                ans += sharkList[field[k][i]].size;
                del[field[k][i]] = true;
                field[k][i] = -1;
                break;
            }
        }

        vector<vector<int>> tmpField(n, vector<int>(m, -1));

        for (int h = 0; h < sharkList.size(); h++) {
            if (del[h] == true)  continue;
            sharkMove(sharkList, tmpField, h, r, c, del);
        }

        field = tmpField;
        
    }
    
    cout << ans;
}

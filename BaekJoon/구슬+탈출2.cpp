//https://www.acmicpc.net/problem/13460

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
int Hx, Hy;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };

class Ball {
    int y, x;
    bool isHole = false;
public:
    Ball(int y, int x) : y{ y }, x{ x }{}

    int getY() { return y; }
    int getX() { return x; }
    bool getIsHole() { return isHole; }
    void setX(int x) { this->x = x; }
    void setY(int y) { this->y = y; }
    void setHole(bool hole) { this->isHole = hole; }


    bool isEqual(Ball b) {
        return x == b.getX() && y == b.getY();
    }
};

void moved(vector<string>& a, Ball& first, Ball& second, int i) {
    while (1) {
        int ax = dx[i] + first.getX();
        int ay = dy[i] + first.getY();
        if (a[ay][ax] == '#') break;
        if (ax == Hx && ay == Hy) first.setHole(true);
        first.setX(ax);
        first.setY(ay);
    }
    while (1) {
        int ax = dx[i] + second.getX();
        int ay = dy[i] + second.getY();
        if (a[ay][ax] == '#') break;
        if (ax == Hx && ay == Hy) second.setHole(true);
        if (ax == first.getX() && ay == first.getY()) break;
        second.setX(ax);
        second.setY(ay);
    }
}

void move(vector<string>& a, Ball& red, Ball& black, int i) {

    if (i == 0) {
        if (red.getX() > black.getX()) {
            moved(a, red, black, i);
        }
        else moved(a, black, red, i);
    }

    if (i == 1) {
        if (red.getY() > black.getY()) moved(a, red, black, i);
        else moved(a, black, red, i);
    }

    if (i == 2) {
        if (red.getX() < black.getX())    moved(a, red, black, i);
        else moved(a, black, red, i);
    }

    if (i == 3) {
        if (red.getY() < black.getY()) moved(a, red, black, i);
        else moved(a, black, red, i);
    }
    //cout << red.getY() << " " << red.getX() << " " << black.getX() << " " << black.getY() << endl;
}


int solve() {

    int n, m;
    cin >> n >> m;
    int ry = 0, rx = 0, bx = 0, by = 0;
    vector<string> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    for (int i = 0; i < n; i++)
        For(k, m) {
        if (a[i][k] == 'R') { ry = i; rx = k; }
        if (a[i][k] == 'B') { bx = k; by = i; }
        if (a[i][k] == 'O') { Hx = k; Hy = i; }
    }
    
    Ball red(ry, rx);
    Ball black(by, bx);

    queue < pair<pair<Ball, Ball>, int>> q;
    q.push(make_pair(make_pair(red, black), 0));
    
    while (!q.empty()) {
        Ball r = q.front().first.first;
        Ball b = q.front().first.second;
        int t = q.front().second;
        q.pop();
        //cout << r.getY() << " " << r.getX() << " " << b.getX() << " " << b.getY() << " " << t << endl;
        if (t == 10) continue;

        for (int i = 0; i < 4; i++) {
            red = r;
            black = b;
            move(a, red, black, i);
            if (red.isEqual(r) && black.isEqual(b)) continue;
            if (black.getIsHole()) continue;
            if (red.getIsHole()) return t + 1;
            //cout << red.getY() << " " << red.getX() << " " << black.getX() << " " << black.getY() << " " << i << " " << t << endl;
            q.push(make_pair(make_pair(red, black), t + 1));
        }
    }
    return -1;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        cout << solve();
    }
    return 0;
}
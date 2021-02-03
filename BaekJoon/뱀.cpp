//https://www.acmicpc.net/problem/3190

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

int n;

int whenEnd(int count, int direction, queue<pair<int, char>>& q, 
    vector<vector<int>>& board, int headY, int headX, queue<pair<int, int>>& tail) {

    headY = headY + dy[direction];
    headX = headX + dx[direction];
    count++;

    if (headY < 0 || headY >= n || headX < 0 || headX >= n || board[headY][headX] >= 2) {
        return count;
    }
    tail.push(make_pair(headY, headX));

    if (board[headY][headX] == 0) {
        board[tail.front().first][tail.front().second] = 0;
        tail.pop();
    }

    board[headY][headX] = 2;
    
    if (!q.empty() && count == q.front().first) {
        if (q.front().second == 'L') {
            direction -= 1;
            if (direction == -1) direction = 3;
        }
        else {
            direction += 1;
            if (direction == 4) direction = 0;
        }
        q.pop();
    }
    
    return whenEnd(count, direction, q, board, headY, headX, tail);
}

void solve() {
    IOS;
    int m;
    cin >> n >> m;
    vector<vector<int>> board(n, vector<int>(n, 0));

    For(i, m) {
        int y, x;
        cin >> y >> x;
        board[y - 1][x - 1] = 1;
    }

    int l;
    cin >> l;

    queue<pair<int, char>> q;
    For(i, l) {
        int x;
        char y;
        cin >> x >> y;
        q.push(make_pair(x, y));
    }
    queue<pair<int, int>> tail;
    tail.push(make_pair(0, 0));
    board[0][0] = 2;
    cout << whenEnd(0, 0, q, board, 0, 0, tail);
}

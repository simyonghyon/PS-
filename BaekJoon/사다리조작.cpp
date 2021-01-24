//https://www.acmicpc.net/problem/15684

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
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };

int n, m;

bool isAnswer(const vector<vector<int>>& letter) {
    for (int i = 0; i < n; i++) {
        int p = i;
        for (int k = 0; k < m; k++) {
            if (letter[p][k] != -1) p = letter[p][k];
        }
        if (p != i) return false;
    }
   
    return true;
}

bool drawLine(vector<vector<int>>& letter, int se, int ga) {
    if (letter[se][ga] != -1 || letter[se + 1][ga] != -1) return false;
    letter[se][ga] = se + 1;
    letter[se + 1][ga] = se;
    return true;
}

int btf(vector<vector<int>>& letter, int order, int se, int ga) {
    if (isAnswer(letter)) {
        //cout << " " << order << " " << se + 1 << " " << ga + 1 << endl;
        return order;
    }
    if (order == 3) return 4;

    int ans = 4;
    int k = ga + 1;
    
    //cout << order << " " << se + 1 << " " << ga  + 1 << endl;
    for (int i = se; i < n - 1; i++) {
        if (i != se) k = 0;
        for(k; k < m; k++) { // 이진 재귀할때 조심
            //cout << " " << order << " " << i + 1 << " " << k + 1<< endl;
            if (drawLine(letter, i, k)) {
                ans = min(ans, btf(letter, order + 1, i, k));
                letter[i][k] = -1;
                letter[i + 1][k] = -1;
            }
        }
    }
    
    return ans;
}

void solve() {
    IOS;
    int h;
    cin >> n >> h;
    cin >> m;
    // [세로][가로]
    vector<vector<int>> letter(n, vector<int> (m, -1));

    For(i, h) {
        int x, y;
        cin >> x >> y;
        x--; y--;
        letter[y][x] = y + 1;
        letter[y + 1][x] = y;
    }
    int ans = btf(letter, 0, 0, -1);
    cout << (ans > 3 ? -1 : ans);
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
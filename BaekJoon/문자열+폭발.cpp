//https://www.acmicpc.net/status?user_id=ds5hmi&problem_id=9935&from_mine=1

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
    string s;
    cin >> s;
    string boom;
    cin >> boom;


    string s2;
    for (int i = 0; i < s.size(); i++) {
        s2.push_back(s[i]);
        
        if (s2.back() == boom.back()) {
            if (s2.size() >= boom.size() && s2.substr(s2.size() - boom.size(), boom.size()) == boom) {
                for (int k = 0; k < boom.size(); k++) s2.pop_back();
            }
        }
    }

    s = s2;

    if (s.size() == 0) s = "FRULA";
    cout << s;
}

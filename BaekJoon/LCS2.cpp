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
#include <unordered_map>
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
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


void solve() {
    IOS;
    string s1, s2;
    cin >> s1 >> s2;

    vector<vector<string>> table(s1.size() + 1, vector<string>(s2.size() + 1, ""));

    for (int i = 1; i <= s1.size(); i++) {
        for (int k = 1; k <= s2.size(); k++) {
            table[i][k] = table[i][k - 1];
            if (s1[i - 1] == s2[k - 1]) {
                table[i][k] = table[i - 1][k - 1] + s1[i - 1];
            }
            table[i][k] = table[i][k].size() > table[i - 1][k].size() ? table[i][k] : table[i - 1][k];
        }
    }
    cout << table[s1.size()][s2.size()].size() << endl;
    if (table[s1.size()][s2.size()].size() == 0) return;
    cout << table[s1.size()][s2.size()] << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
/*
* https://codeforces.com/contest/1421/problem/B
*/

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
int dy[] = { 0, -1, 0, 1 };

void solved() {
    IOS;
    int n;
    cin >> n;
    vector<string> s(n);
    For(i, n) cin >> s[i];
    char a = s[0][1], b = s[1][0], c = s[n - 1][n - 2], d = s[n - 2][n - 1];
    
    vector<pair<int, int>> ans;
    if (a != '1') ans.push_back(make_pair(0, 1));
    if (b != '1') ans.push_back(make_pair(1, 0));
    if (c != '0') ans.push_back(make_pair(n - 1, n - 2));
    if (d != '0') ans.push_back(make_pair(n - 2, n - 1));
    if (ans.size() <= 2) {
        cout << ans.size() << endl;
        for (auto i : ans) {
            cout << i.first << " " << i.second << "\n";
        }
        return;
    }
    ans.clear();
    if (a != '0') ans.push_back(make_pair(0, 1));
    if (b != '0') ans.push_back(make_pair(1, 0));
    if (c != '1') ans.push_back(make_pair(n - 1, n - 2));
    if (d != '1') ans.push_back(make_pair(n - 2, n - 1));
    if (ans.size() <= 2) {
        cout << ans.size() << endl;
        for (auto i : ans) {
            cout << i.first << " " << i.second << "\n";
        }
        return;
    }
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
//https://codeforces.com/contest/1440/problem/C1

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
 
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, -1, 0, 1 };
char change(char c) {
    if (c == '1') return '0';
    else return '1';
}
void solved() {
    IOS;
    IOS;
    int n, m;
    cin >> n >> m;
    vector<string> s(n);
    For(i, n)
        cin >> s[i];
    vector<vector<pair<int, int>>> a;
    for (int i = 0; i < n - 1; i++) {
        for (int k = 0; k < m - 1; k++) {
            if (s[i][k] == '1') {
                vector<pair<int, int>> tem(3);
                tem[0] = make_pair(i + 1, k + 1);
                tem[1] = make_pair(i + 1, k + 2);
                tem[2] = make_pair(i + 2, k + 2);
                a.push_back(tem);
                s[i][k] = change(s[i][k]);
                s[i][k + 1] = change(s[i][k + 1]);
                s[i + 1][k + 1] = change(s[i + 1][k + 1]);
            }
        }
        if (s[i][m - 1] == '1') {
            vector<pair<int, int>> tem(3);
            tem[0] = make_pair(i + 1, m);
            tem[1] = make_pair(i + 2, m);
            tem[2] = make_pair(i + 2, m - 1);
            a.push_back(tem);
            s[i][m - 1] = change(s[i][m - 1]);
            s[i + 1][m - 1] = change(s[i + 1][m - 1]);
            s[i + 1][m - 2] = change(s[i + 1][m - 2]);
        }
    }
 
    for (int i = 0; i < m - 1; i++) {
        if (s[n - 1][i] == '1') {
            if (s[n - 1][i + 1] == '1') {
                vector<pair<int, int>> tem(3);
                tem[0] = make_pair(n, i + 1);
                tem[1] = make_pair(n - 1, i + 1);
                tem[2] = make_pair(n - 1, i + 2);
                a.push_back(tem);
                tem[0] = make_pair(n - 1 + 1, i + 1 + 1);
                tem[1] = make_pair(n - 2 + 1, i + 1 + 1);
                tem[2] = make_pair(n - 2 + 1, i + 1);
                a.push_back(tem);
                s[n - 1][i] = s[n - 1][i + 1] = 0;
            }
            else {
                vector<pair<int, int>> tem(3);
                tem[0] = make_pair(n - 1 + 1, i + 1);
                tem[1] = make_pair(n - 2 + 1, i + 1);
                tem[2] = make_pair(n - 2 + 1, i + 1 + 1);
                a.push_back(tem);
                tem[0] = make_pair(n - 2 + 1, i + 1);
                tem[1] = make_pair(n - 1 + 1, i + 1);
                tem[2] = make_pair(n - 1 + 1, i + 1 + 1);
                a.push_back(tem);
                tem[0] = make_pair(n - 2 + 1, i + 1 + 1);
                tem[1] = make_pair(n - 1 + 1, i + 1 + 1);
                tem[2] = make_pair(n - 1 + 1, i + 1);
                a.push_back(tem);
                s[n - 1][i] = 0;
            }
        }
    }
    if (s[n - 1][m - 1] == '1') {
        vector<pair<int, int>> tem(3);
        tem[0] = make_pair(n - 1 + 1, m - 1+ 1);
        tem[1] = make_pair(n - 2 + 1, m - 1 + 1);
        tem[2] = make_pair(n - 2 + 1, m - 2 + 1);
        a.push_back(tem);
        tem[0] = make_pair(n - 2 + 1, m - 2 + 1);
        tem[1] = make_pair(n - 1 + 1, m - 2 + 1);
        tem[2] = make_pair(n - 1 + 1, m - 1 + 1);
        a.push_back(tem);
        tem[0] = make_pair(n - 2 + 1, m - 1 + 1);
        tem[1] = make_pair(n - 1 + 1, m - 1 + 1);
        tem[2] = make_pair(n - 1 + 1, m - 2 + 1);
        a.push_back(tem);
    }
 
    cout << a.size() << "\n";
    for (int i = 0; i < a.size(); i++) {
        cout << a[i][0].first << " " << a[i][0].second << " ";
        cout << a[i][1].first << " " << a[i][1].second << " ";
        cout << a[i][2].first << " " << a[i][2].second << "\n";
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
 
 
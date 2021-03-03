//https://www.acmicpc.net/problem/1786

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

vector<int> getPi(string s) {
    vector<int> fi(s.size(), 0);

    int j = 0;

    for (int i = 1; i < s.size(); i++) {
        while (j > 0 && s[i] != s[j]) j = fi[j - 1];
        if (s[i] == s[j]) fi[i] = ++j;
    }

    return fi;
}

void solve() {
    IOS;
    string s1, s2;
    
    getline(cin, s1);
    getline(cin, s2);

    vector<int> fi = getPi(s2);
    
    vector<int> ans;

    int j = 0;
    for (int i = 0; i < s1.size(); i++) {
        while(j > 0 && s1[i] != s2[j]) j = fi[j - 1];
        if (s1[i] == s2[j]) {
            if (j == s2.size() - 1) {
                ans.push_back(i - j);
                j = fi[j];
            }
            else
                j++;
        }
    }

    cout << ans.size() << endl;
    for (auto i : ans) cout << i + 1 << " ";

}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    
    return 0;
}
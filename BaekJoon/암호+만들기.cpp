//https://www.acmicpc.net/problem/1759

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
typedef  long long ll;
int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
int dy[] = { 0, 1, 0, -1, 1, -1, -1, 1 };

bool check(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
    return false;
}

void solve() {
    IOS;
    int n, m;
    cin >> n >> m;
    vector<char> a(m);
    For(i, m) cin >> a[i];

    sort(a.begin(), a.end());

    vector<int> permu(m, 0);
    For(i, n) permu[i] = 1;

    do {
        string s = "";
        int mo = 0;
        int za = 0;
        For(i, m) {
            if (permu[i] == 1) {
                s += a[i];
                if (check(a[i])) mo++;
                else za++;
            }
        }
        if (mo >= 1 && za >= 2) cout << s << endl;

    } while (prev_permutation(permu.begin(), permu.end()));
}

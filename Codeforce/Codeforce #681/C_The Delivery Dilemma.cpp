/*
* https://codeforces.com/contest/1443/problem/C
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


void solved() {
    IOS;
    int n;
    cin >> n;
    vector<pair<int, int>> a(n);
    For(i, n) {
        int m;
        cin >> m;
        a[i].first = m;
    }
    For(i, n) {
        int m;
        cin >> m;
        a[i].second = m;
    }
    sort(a.begin(), a.end());
    reverse(a.begin(), a.end());

    int ans = a[0].first;
    int sum = 0;
    bool check = true;
    for (int i = 0; i < n; i++) {
        //cout << a[i].first << " " << a[i].second << endl;
        if (a[i].first > sum + a[i].second) {
            sum += a[i].second;
        }
        else {
            ans = max(sum, a[i].first);
            check = false;
            break;
        }
    }
    if (check) ans = min(sum, a[n - 1].first);
    cout << ans << endl;
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
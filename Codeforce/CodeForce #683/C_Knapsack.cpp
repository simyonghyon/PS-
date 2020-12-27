//https://codeforces.com/contest/1447/problem/C

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
    ll n, m;
    cin >> n >> m;
    set<ll> a;
    ll sum = 0;
    vector<ll> b(n);
    For(i, n) cin >> b[i];
    For(i, n) {
        ll x = b[i];
        if (x > m) continue;
        if (x >= (m + 1) / 2) {
            cout << 1 << "\n" << i + 1<< endl;
            return;
        }
        sum += x;
        a.insert(i);
        if (sum >= (m + 1) / 2) {
            cout << a.size() << endl;
            for (auto i : a) cout << i + 1 << " ";
            cout << endl;
            return;
        }
    }
    cout << -1 << endl;
}
 
int main() {
    IOS;
    int t = 1;
    cin >> t;
 
 
 
    while (t--) {
        solved();
    }
 
}
/*
* https://codeforces.com/contest/1401/problem/C
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
    vector<int> a(n);
    int mi = 1000000000;
    For(i, n) {
        cin >> a[i];
        mi = min(a[i], mi);
    }

    vector<int> b = a;
    sort(b.begin(), b.end());

    For(i, n) {
        if (b[i] != a[i]) {
            if (a[i] % mi != 0) {
                cout << "NO\n";
                return;
            }
        }
    }
    cout << "YES" << endl;
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
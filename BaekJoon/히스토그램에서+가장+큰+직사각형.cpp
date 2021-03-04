// https://www.acmicpc.net/problem/6549

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

ll getMiddleMax(int l, int r, vector<ll>& a) {
    ll mid = (l + r) / 2;
    
    ll left = mid; 
    ll right = mid + 1;
    ll height = min(a[left], a[right]);

    ll size = height * 2;

    while (l <= left && right <= r) {
        while (l < left) {
            if (a[left - 1] < height) break;
            left--;
        }
        while (right < r) {
            if (a[right + 1] < height) break;
            right++;
        }

        size = max(size, height * (right - left + 1));
        
        if (left == l && right == r) break;
        else if (left == l) height = a[right + 1];
        else if (right == r) height = a[left - 1];
        else height = max(a[left - 1], a[right + 1]);
    }

    return size;
}

ll merge(int l, int r, vector<ll>& a) {
    if (l == r) return a[l];
    int mid = (l + r) / 2;
    ll left = merge(l, mid, a);
    ll right = merge(mid + 1, r, a);

    ll size = max(left, right);
    size = max(size, getMiddleMax(l, r, a));
    return size;
}

void solve() {
    IOS;
    
    while (1) {
        int n;
        cin >> n;

        if (n == 0) break;


        vector<ll> a(n);

        For(i, n) cin >> a[i];

        cout << merge(0, n - 1, a) << endl;
    }
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
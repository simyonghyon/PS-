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


vector<int> getPrime(int n) {
    vector<int> prime;

    vector<bool> check(n + 1, false);
    
    for (int i = 2; i <= n; i++) {
        if (!check[i]) {
            prime.push_back(i);
            for (int k = i; k <= n; k += i) check[k] = true;
        }
    }

    return prime;
}

void solve() {
    IOS;
    int n;
    cin >> n;

    vector<int> prime = getPrime(n);

    int left = 0;
    int right = 0;

    int sum = 0;
    int ans = 0;

    while (left <= right && right < prime.size()) {
        sum += prime[right];

        while (sum > n) {
            sum -= prime[left];
            left++;
        }

        if (sum == n) ans++;
        right++;
    }

    cout << ans << endl;
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}

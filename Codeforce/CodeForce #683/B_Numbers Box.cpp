//https://codeforces.com/contest/1447/problem/B

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
    int n, m;
    cin >> n >> m;
    int sum = 0;
    int mi = 101;
    bool isZero = false;
    int count = 0;
    for (int i = 0; i < n * m; i++) {
        int x;
        cin >> x;
        sum += abs(x);
        mi = min(abs(x), mi);
        if (x == 0) isZero = true;
        else if (x < 0) count++;
    }
    if (isZero) {
        cout << sum << endl;
        return;
    }
    else if (count % 2 == 0)
        cout << sum << "\n";
    else cout << sum - mi * 2 << endl;
    return;
}
 
int main() {
    IOS;
    int t = 1;
    cin >> t;
 
 
 
    while (t--) {
        solved();
    }
 
}
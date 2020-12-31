// https://www.acmicpc.net/problem/1011

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

void solved() {
    IOS;
    int x, y;
    cin >> x >> y;
    int calc = y - x;
    int i = 1;
    int ans = 0;
    while (1) {
        if (calc - i < 0) break;
        ans++;
        calc -= i;
        if (calc - i < 0) break;
        ans++;
        calc -= i;
        i++;
    }
    if (calc > 0)
        cout << ans + 1 << "\n";
    else
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
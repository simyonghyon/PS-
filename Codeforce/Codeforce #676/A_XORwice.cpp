/*
*https://codeforces.com/contest/1421/problem/A
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
    int a, b;
    cin >> a >> b;
    int ans = 0;
    for (int i = 0; i < 32; i++) {
        if (((1 << i) & a) != ((1 << i) & b)) ans += (1 << i);
    }

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
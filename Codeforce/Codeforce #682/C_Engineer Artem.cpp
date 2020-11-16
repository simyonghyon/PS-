/*
* https://codeforces.com/contest/1438/problem/C
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
    int n, m;
    cin >> n >> m;
    vector<vector<int>> a(n, vector<int> (m));
    For(i, n)
        For(k, m)
        cin >> a[i][k];
    
    For(i, n)
        For(k, m) {
        if (i % 2 == 0) {
            if (k % 2 == 0) { if (a[i][k] % 2 != 0) a[i][k]++; }
            else { if (a[i][k] % 2 == 0) a[i][k]++; }
        }
        else {
            if (k % 2 == 0) { if (a[i][k] % 2 == 0) a[i][k]++; }
            else { if (a[i][k] % 2 != 0) a[i][k]++; }
        }
    }

    For(i, n) {
        For(k, m)
            cout << a[i][k] << " ";
        cout << endl;
    }
}
    


int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
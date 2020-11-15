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
    set<int> a;
    bool check = false;
    For(i, n) {
        int m;
        cin >> m;
        if (a.find(m) != a.end()) {
            check = true;
        }
        a.insert(m);
    }
    if (check)
        cout << "YES\n";
    else
        cout << "NO" << endl;
}
 
int main() {
    IOS;
    int t = 1;
    cin >> t;
 
    while (t--) {
        solved();
    }
 
}
/*
* https://codeforces.com/contest/1401/problem/B
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
 
using namespace std;
#define For(i, n) for(int i = 0; i < n; i++)
#define IOS  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
#define endl  "\n";
typedef  long long ll;
int check(int index, int k, vector<int> & a, vector<int> & b, int count) {
    int m;
    m = min(a[k], b[index]);
    a[k] -= m;
    b[index] -= m;
 
    return m;
}
 
void solved() {
    IOS;
    vector<int> a(3), b(3);
    For(i, 3) {
        cin >> a[i];
    }
    For(i, 3) {
        cin >> b[i];
    }
 
    int count = 0;
    int index = 1;
 
    count = 2 * check(1, 2, a, b, count);
    check(1, 1, a, b, count);
    check(0, 1, a, b, count);
    check(2, 0, a, b, count);
    check(2, 2, a, b, count);
    count -= 2 * check(2, 1, a, b, count);
 
    cout << count << endl;
    return;
}
 
int main() {
    IOS;
    int t;
    cin >> t;
    
    while (t--) {
        solved();
    }
    
}
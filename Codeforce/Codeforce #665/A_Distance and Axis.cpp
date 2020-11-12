/*
* https://codeforces.com/contest/1401/problem/A
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
 
void solved() {
    IOS;
    int n, k;
    cin >> n >> k;
    if (n <= k) {
        cout << k - n << endl;
        return;
    }
 
    if (k % 2 == 1) {
        if (n % 2 == 1) {
            cout << "0" << endl;
            return;
        }
        else {
            cout << "1" << endl;
            return;
        }
    }
    else {
        if (n % 2 == 1) {
            cout << "1" << endl;
            return;
        }
        else {
            cout << "0" << endl;
            return;
        }
    }
}
 
 
int main() {
    IOS;
    int t;
    cin >> t;
    while (t--) {
        solved();
    }
 
}
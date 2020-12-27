//https://codeforces.com/contest/1447/problem/A

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
    int n;
    cin >> n;
 
 
    cout << n << "\n";
    int s = 1;
    For (i, n) {
        cout << s++ << " ";
    }
    cout << endl;
}
 
int main() {
    IOS;
    int t = 1;
    cin >> t;
 
 
 
    while (t--) {
        solved();
    }
 
}
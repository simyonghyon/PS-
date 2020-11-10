/*
* https://codeforces.com/contest/1421/problem/C
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
    string s;
    cin >> s;
    cout << "3" << endl;
    cout << "L 2" << endl;
    cout << "R 2" << endl;
    int size = s.size();
    size++;
    size += size - 2;
    cout << "R " << size - 1 << endl;
}
 
int main() {
    IOS;
    int t = 1;
    //cin >> t;
 
    while (t--) {
        solved();
    }
 
}
/*
* https://codeforces.com/contest/1437/problem/D
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
    int n;
    cin >> n;
    vector<int> a(n);
    For(i, n)
        cin >> a[i];

    int size = 1;
    int f = 1;
    int count = 0;
    for (int i = 1; i < n; i++) {
        if (a[i] < a[i - 1]) {
            size--;
        }
        if (size == 0) {
            f++;
            size = count;
            count = 0;
        }
        count++;
    }



    cout << f << endl;
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
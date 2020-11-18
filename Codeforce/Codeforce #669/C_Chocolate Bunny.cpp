/*
* https://codeforces.com/contest/1407/problem/C
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
 
int mo(int a, int b) {
    cout << "? " << a + 1 << " " << b + 1 << endl;
    int k;
    cin >> k;
    return k;
}
 
void solved() {
}
 
 
int main() {
    /*int t;
    cin >> t;
    while (t--) {
        solved();
    }**/
 
    int n; 
    cin >> n;
    vector<int> a(n, -1);
 
    int index = 0;
    for (int i = 1; i < n; i++) {
        int x = mo(index, i);
        int y = mo(i, index);
        if (x > y) {
            a[index] = x;
            index = i;
        }
        else
            a[i] = y;
    }
    a[index] = n;
    cout << "! ";
    for(auto i : a)
        cout << i << " ";
    cout << endl;
}
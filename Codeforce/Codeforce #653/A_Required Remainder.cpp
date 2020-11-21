/*
* https://codeforces.com/contest/1374/problem/A
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
 
using namespace std;
#define For(i, n) for(int i = 0; i < n; i++)
 
int main() {
    int t;
    cin >> t;
    while (t--) {
        int x, y, n;
        cin >> x >> y >> n;
        int k = n / x;
        if (n % x < y)
            k--;
        int ans = k * x + y;
        cout << ans << "\n";
    }
}
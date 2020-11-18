/*
* https://codeforces.com/contest/1407/problem/D
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
#define IOS  ios::sync_with_stdio(0); cin.tie(0);
#define endl  "\n";
typedef  long long ll;
 
void solved() {
    IOS;
}
 
/*int main() {
    IOS;
    /*int t;
    cin >> t;
    while (t--) {
        solved();
    }
 
}*/
 
int main() {
    int n;
    cin >> n;
    vector<int> h(n);
    For(i, n)
        cin >> h[i];
    vector<int> dp(n, n);
    dp[0] = 0;
    vector<int> s{ 0 }, p{ 0 };
    for (int i = 1; i < n; i++) {
 
        dp[i] = dp[i - 1] + 1;
        /*for (auto k : s)
            cout << k << " ";
        cout << endl;*/
        //cout << h[i] << " " << h[p.back()] << endl;
        while (!s.empty()) {
            //cout << h[i] << " " << s.back() << " " << h[s.back()] << endl;
            if (h[i] > h[s.back()])
                dp[i] = min(dp[i], dp[s.back()] + 1);
            else {
                dp[i] = min(dp[i], dp[s.back()] + 1);
                if (h[i] == h[s.back()])
                    s.pop_back();
                break;
            }
            s.pop_back();
        }
        while (!p.empty()) {
            if (h[i] < h[p.back()])
                dp[i] = min(dp[i], dp[p.back()] + 1);
            else {
                dp[i] = min(dp[i], dp[p.back()] + 1);
                if (h[i] == h[p.back()])
                    p.pop_back();
                break;
            }
            p.pop_back();
        }
        //if(!s.empty() && !p.empty())
        //cout << s.back() << " " << p.back() << endl;
        s.push_back(i);
        p.push_back(i);
    }
    cout << dp[n - 1];
}
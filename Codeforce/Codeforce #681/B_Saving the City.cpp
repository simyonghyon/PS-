/*
* https://codeforces.com/contest/1443/submission/97455562
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
    int a, b;
    cin >> a >> b;
    string s;
    cin >> s;
    vector<int> dp(s.size() + 1, 0);
    dp[0] = 0;
    int index = 0;
    bool check = false;
    bool isbomb = true;
    for (int i = 1; i <= s.size(); i++) {
        if (s[i - 1] == '0') {
            check = false;
            dp[i] = dp[i - 1] + b;
        }
        else if (s[i - 1] == '1') {
            if (check) {
                dp[i] = dp[i - 1];
            }

            else {
                if (isbomb) {
                    dp[i] = a;
                    isbomb = false;
                }
                else dp[i] = min(dp[index] + a, dp[i - 1]);
                index = i;
                check = true;
            }
        }
        //cout << dp[i] << " " << index << " ";
    }
    cout << dp[index] << endl;
}

int main() {
    IOS;
    int t = 1;
    cin >> t;

    while (t--) {
        solved();
    }

}
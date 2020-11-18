/*
* https://codeforces.com/contest/1407/problem/A
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
    int n;
    cin >> n;
    vector<int> a(n);
    int count1 = 0;
    int sum = 0;
    For(i, n) {
        cin >> a[i];
        if (a[i] == 1)
            count1++;
        if (i % 2 == 0)
            sum += a[i];
        if (i % 2 == 1)
            sum -= a[i];
    }
    vector<int> ans;
    if (count1++ % 2 == 0) {
        if (sum == 0) {
            cout << n << endl;
            For(i, n)
                cout << a[i] << " ";
            cout << endl;
            return;
        }
        else {
            for (int i = 0; i < n; i++) {
                if (a[i] == 1) {
                    ans.push_back(1);
                    int count = 0;
                    int k = i + 1;
                    while (a[k] != 1) {
                        count++;
                        k++;
                        ans.push_back(0);
                    }
                    if (count % 2 == 1) {
                        ans.pop_back();
                    }
                    ans.push_back(1);
                    i = k;
                }
                else
                    ans.push_back(0);
            }
        }
    }
    else {
        bool check = false;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1 && check == false) {
                check = true;
                continue;
            }
            if (a[i] == 1) {
                ans.push_back(1);
                int count = 0;
                int k = i + 1;
                while (a[k] != 1) {
                    count++;
                    k++;
                    ans.push_back(0);
                }
                if (count % 2 == 1) {
                    ans.pop_back();
                }
                ans.push_back(1);
                i = k;
            }
            else
                ans.push_back(0);
        }
    }
    cout << ans.size() << endl;
    For(i, ans.size())
        cout << ans[i] << " ";
    cout << endl;
}
 
 
int main() {
    IOS;
    int t;
    cin >> t;
    while (t--) {
        solved();
    }
    
}
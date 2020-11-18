/*
* https://codeforces.com/contest/1407/problem/B
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
 
int gcd(int a, int b)
{
    int c;
    if (a < b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
    while (b != 0)
    {
        c = a % b;
        a = b;
        b = c;
    }
    return a;
}
 
void solved() {
    IOS;
    int n;
    cin >> n;
    vector<int> a(n);
    int max = 0;
    For(i, n) {
        cin >> a[i];
        if (a[max] < a[i])
            max = i;
    }
    bool check[3001];
    memset(check, false, 3001);
    vector<int> ans;
    ans.push_back(a[max]);
    check[max] = true;
    int buf = ans.back();
    while (ans.size() < n) {    
        int index = -1;
        int ma = 0;
        for (int i = 0; i < n; i++) {
            int gcc = 0;     
            if (check[i] == false) {
                gcc = gcd(buf, a[i]);
                if (ma < gcc) {
                    ma = gcc;
                    index = i;
                }
            }
        }
        buf = ma;
        ans.push_back(a[index]);
        check[index] = true;
    }
    For(i, n)
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
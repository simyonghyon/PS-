//https://www.acmicpc.net/problem/9466

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
int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
int dy[] = { 0, 1, 0, -1, 1, -1, -1, 1 };

void doCycle(int i, const vector<int>& a, vector<bool>& cycle) {
    int index = i;
    cycle[index] = true;
    while (a[index] != i) {
        index = a[index];
        cycle[index] = true;
    }
    
}

void isCycle(int n, int i, vector<bool>&check, const vector<int>&a, vector<bool>& cycle) {
    int index = i;
    
    vector<bool> visited(n, false);

    while (1) {
        if (check[index] == true) {
            if (visited[index]) {
                doCycle(index, a, cycle);
                return;
            }
            else return;
        }
        check[index] = true;
        visited[index] = true;
        index = a[index];
    }
}

void solve() {
    IOS;
    int n;
    cin >> n;

    vector<bool> check(n, false);
    vector<bool> cycle(n, false);
    vector<int> a(n);
    For(i, n){
        cin >> a[i];
        a[i]--;
    }

    

    For(i, n) {
        if (check[i] == true) continue;
        isCycle(n, i, check, a, cycle);
    }
    
    int ans = 0;
    for (auto i : cycle) {
        if (!i) ans++;
    }

    cout << ans << endl;
}

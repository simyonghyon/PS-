//https://www.acmicpc.net/problem/4195

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
#include <hash_map>
//#include<bits/stdc++.h>
#include<cstdio>

using namespace std;

#define For(i, n) for(int i = 0; i < n; i++)
#define IOS  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
#define endl  "\n";
#define IF(ny, nx, n, m) if(0 <= ny && ny < n && 0 <= nx && nx < m)
#define P pair<int, int> 
const int INF = 987654321;
typedef  long long ll;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


string fin(string s, map<string, string>&parent, map<string, int>&rank) {
    if (s == parent[s]) return s;

    return parent[s] = fin(parent[s], parent, rank);
}

int uni(string s1, string s2, map<string, string>& parent, map<string, int>& rank) {
    s1 = fin(s1, parent, rank);
    s2 = fin(s2, parent, rank);

    if (s1 == s2) return rank[s1];

    if (rank[s1] < rank[s2]) {
        parent[s1] = s2;
        rank[s2] += rank[s1];
      
    }
    else {
        parent[s2] = s1;
        rank[s1] += rank[s2];
 
    }
   
    return max(rank[s1], rank[s2]);
}


void solve() {
    IOS;
    int n;
    cin >> n;
    
    map<string, string> parent;
    map<string, int> rank;

    For(i, n) {
        string s1, s2;
        cin >> s1 >> s2;
        if (parent.find(s1) == parent.end()) {
            parent[s1] = s1;
            rank[s1] = 1;
        }
        if (parent.find(s2) == parent.end()) {
            parent[s2] = s2;
            rank[s2] = 1;
        }
        cout << uni(s1, s2, parent, rank) << endl;
        
    }

}


int main() {
    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
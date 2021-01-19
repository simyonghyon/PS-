// https://www.acmicpc.net/problem/2042

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
int dy[] = { 0, 1, 0, -1 };

struct Node{
    int start; 
    int end;
    ll value;
};

ll init(vector<ll>&a, vector<ll>& tree, int node, int start, int end) {
    if (start == end) return tree[node] = a[start];
    else {
        return tree[node] = init(a, tree, node * 2 + 1, start, (start + end) / 2) +
            init(a, tree, node * 2 + 2, (start + end) / 2 + 1, end);
    }
}

void update(vector<ll>& tree, int node, int changed_node, ll changed_value, int start, int end) {
    if (changed_node < start || changed_node > end) return;
    tree[node] += changed_value;
    if (start != end) {
        update(tree, node * 2 + 1, changed_node, changed_value, start, (start + end) / 2);
        update(tree, node * 2 + 2, changed_node, changed_value, (start + end) / 2 + 1, end);
    }
}

ll read(vector<ll>& tree, int node, int left, int right, int start, int end) {
    if (start > right || end < left) return 0;
    if (left <= start && end <= right) {
        return tree[node];
    }
    return read(tree, node * 2 + 1, left, right, start, (start + end) / 2) +
        read(tree, node * 2 + 2, left, right, (start + end) / 2 + 1, end);
}

    
void solve() {
    IOS;
    ll n, m, k;
    cin >> n >> m >> k;
    int h = (int)ceil(log2(n));
    int tree_size = (1 << (h + 1));
    vector<long long> value(n);
    vector<long long> tree(tree_size + 1);

    For(i, n) {
        cin >> value[i];
    }
    
    init(value, tree, 0, 0, n - 1);
  
    For(i, m + k) {
        ll a, b, c;
        cin >> a >> b >> c;
     
        if (a == 1) {
            update(tree, 0, b - 1, c - value[b - 1], 0, n - 1);
            value[b - 1] = c;
        }
        if (a == 2) cout << read(tree, 0, b - 1, c - 1, 0, n - 1) << endl;
    }
}


int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
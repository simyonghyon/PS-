//https://www.acmicpc.net/problem/14891

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
#include <unordered_map>
//#include<bits/stdc++.h>

using namespace std;

#define For(i, n) for(int i = 0; i < n; i++)
#define IOS  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
#define endl  "\n";
#define IF(ny, nx, n, m) if(0 <= ny && ny < n && 0 <= nx && nx < m)
#define P pair<int, int>
const int INF = 1987654321;
typedef  long long ll;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int hy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

class Gear{
public:
    string s;
    int order;

    Gear(){}
    Gear(string s, int order){
        this->s = s;
        this->order = order;
    }

    void rotate(int direction){
        // 시계
        if(direction == -1){
            char c = s.front();
            for(int i = 0; i < 7; i++){
                s[i] = s[i + 1];
            }
            s[7] = c;

        } else{
            char c = s.back();
            for(int i = 7; i > 0; i--){
                s[i] = s[i - 1];
            }
            s[0] = c;
        }

    }


};

void solve() {
    IOS;
    vector<Gear> a(4);

    for(int i = 0; i < 4; i++){
        string s;
        cin >> s;
        a[i] = Gear(s, i);
    }

    int n;
    cin >> n;

    For(i, n){
        int x, y;
        cin >> x >> y;
        x--;

        stack<P> stack;

        int k = x;
        int z = y * -1;

        while(k > 0){
            if(a[k].s[6] != a[k - 1].s[2]) stack.push(P(k - 1, z));
            else break;
            k--;
            z *= -1;
        }

        k = x;
        z = y * -1;
        while(k < 3){
            if(a[k].s[2] != a[k + 1].s[6]) stack.push(P(k + 1, z));
            else break;
            k++;
            z *= -1;
        }

        a[x].rotate(y);
        while(!stack.empty()){
            P p = stack.top();
            stack.pop();

            a[p.first].rotate(p.second);
        }
        //for(auto i : a) cout << i.s << endl;
    }

    int ans = 0;



    if(a[0].s[0] == '1') ans += 1;
    if(a[1].s[0] == '1') ans += 2;
    if(a[2].s[0] == '1') ans += 4;
    if(a[3].s[0] == '1') ans += 8;

    cout << ans << endl;
}




int main() {
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}


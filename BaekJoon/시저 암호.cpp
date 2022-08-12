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

char getOrderShiftAlpha(int shift, string order, char alpha){
    int index = order.find(alpha);
    index = (index + shift) % order.size();
    return order[index];
}

int findString(string ws, string ps) {
    int wsSize = ws.size();
    int psSize = ps.size();

    int wsHash = 0;
    int psHash = 0;
    int power = 1; // 제곱수
    int count = 0;

    for (int i = 0; i <= wsSize - psSize; i++) {
        if (i == 0) {
            for (int j = 0; j < psSize; j++) {
                wsHash += ws[psSize - 1 - j] * power;
                psHash += ps[psSize - 1 - j] * power;
                if (j < psSize - 1) power *= 3;
            }
        }
        else {
            wsHash = 3 * (wsHash - ws[i - 1] * power) + ws[psSize - 1 + i];
        }
        if (wsHash == psHash) {
            bool isFind = true;
            // 우연히 해시값이 겹친 경우 위해 문자열 일치 여부 검사
            for (int j = 0; j < psSize; j++) {
                if (ws[i + j] != ps[j]) {
                    isFind = false;
                    break;
                }
            }
            if (isFind) {
                count++;
                if(count >= 2) break;
            }
        }
    }

    return count;
}

bool matchUnique(string cizer, string s){
    return findString(cizer, s) == 1;
}


bool noSolution(vector<int> ans){
    return ans.size() == 0;
}

bool uniqueSolution(vector<int> ans){
    return ans.size() == 1;
}

void solve() {
    IOS;
    string order;
    string origin;
    string cizer;
    vector<int> answer;
    cin >> order >> origin >> cizer;

    for(int i = 0; i < order.size(); i++){
        string init = "";
        for(int k = 0; k < origin.size(); k++){
            init += (getOrderShiftAlpha(i, order, origin[k]));
        }

        if(matchUnique(cizer, init)){
            answer.push_back(i);
        }
    }

    if(noSolution(answer)){
        cout << "no solution" << endl;

    } else if(uniqueSolution(answer)){
        cout << "unique: " << answer[0] << endl;

    } else {
        cout << "ambiguous:";
        for(int i = 0; i < answer.size(); i++){
            cout << " " << answer[i];
        }
        cout << "\n";
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


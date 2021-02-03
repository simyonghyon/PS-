//https://www.acmicpc.net/problem/9251

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


void solve() {
    IOS;
    string s1, s2;
    cin >> s1 >> s2;
    string str1 = '0' + s1;
    string str2 = '0' + s2;
    int len1 = str1.size();
    int len2 = str2.size();
    vector<vector<int>> table(len1, vector<int>(len2, 0));
    for (int i = 1; i < len1; i++) {
        for (int k = 1; k < len2; k++) {
            
            if (str1[i] == str2[k])
                table[i][k] = table[i - 1][k - 1] + 1;
            else {
                table[i][k] = (table[i - 1][k] > table[i][k - 1]) ? table[i - 1][k] : table[i][k - 1];
            }
        }
    }
 

    cout << table[len1 - 1][len2 - 1];
}

#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int sum(int x, int y) {
    return x + y;
}

int minu(int x, int y) {
    return (x - y) < 0 ? 0 : x - y;
}

int multi(int x, int y) {
    return x * y;
}

int divide(int x, int y) {
    if (y == 0) return 0;
    return x / y;
}

int conti(int n, int i) {
    int tmp = n;
    i--;
    while (i--) {
        tmp = tmp * 10 + n;
    }
    return tmp;
}

void checked(set<int>&check, vector<vector<int>>&a, int x, int i) {
    if (check.find(x) == check.end()) {
        a[i].push_back(x);
        check.insert(x);
    }
}

// 입력 : check, a, x, y, i
// 숫자 2개를 받아 각 연산을 수행한 후 없으면 a에 넣어주기
void push(set<int>&check, vector<vector<int>>&a, int x, int y, int i) {
    checked(check, a, sum(x, y), i);
    checked(check, a, minu(x, y), i);
    checked(check, a, multi(x, y), i);
    checked(check, a, divide(x, y), i);
}


int solution(int N, int number) {
 
    int answer = 0;
    set<int> check;
    vector<vector<int>> a(9);
    a[1].push_back(N);
    for (int i = 1; i <= 8; i++) {
        for (int k = 1; k < i; k++) {
            for (int j = 0; j < a[k].size(); j++) {
                for (int l = 0; l < a[i - k].size(); l++) {
                    push(check, a, a[k][j], a[i - k][l], i);
                }
            }
        }
        a[i].push_back(conti(N, i));
       
        if (find(a[i].begin(), a[i].end(), number) != a[i].end()) return i;
    }
    return -1;
}
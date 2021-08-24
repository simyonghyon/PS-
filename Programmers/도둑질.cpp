//https://programmers.co.kr/learn/courses/30/lessons/42897

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

//처음에는 이런 형식으로 2차원 dp를 사용하여 풀었다. 문제의 조건에 집의 개수는 100000개라
//명시되어 있었고 충분히 시간복잡도 부분에서 통과가 될 줄 알았다. 하지만 효율성 부분에서
//패스되지 못했고 다시 한번 생각해보게 되었다...
int solution(vector<int> money) {

    vector<vector<int>> dpo(money.size(), vector<int>(2, -1));
    vector<vector<int>> dpx(money.size(), vector<int>(2, -1));

    dpo[0][0] = -INF;
    dpo[0][1] = money[0];
    dpo[1][0] = dpo[0][1];
    dpo[1][1] = -INF;

    dpx[0][0] = 0;
    dpx[0][1] = -INF;
    dpx[1][0] = 0;
    dpx[1][1] = money[1];

    int n = money.size() - 1;

    for (int i = 2; i < n + 1; i++) {
        dpo[i][0] = max(dpo[i - 1][0], dpo[i - 1][1]);
        dpo[i][1] = dpo[i - 1][0] + money[i];

        dpx[i][0] = max(dpx[i - 1][0], dpx[i - 1][1]);
        dpx[i][1] = dpx[i - 1][0] + money[i];
    }

    int ans = max(dpo[n][0], dpx[n][0]);
    ans = max(ans, dpx[n][1]);

    return ans;
}

//dp가 2차원일 필요가 없다는 사실을 깨닫고 이렇게 바꿔주었다.
//솔직히 훨신 깔끔하기는 하지만 크게 차이는 안나는거 같은데 너무 시간복잡도가 빡빡한 것 같다..
int solution(vector<int> money) {
    vector<int> dpo(money.size());
    vector<int> dpx(money.size());
    
    dpo[0] = money[0];
    dpo[1] = dpo[0];
    
    dpx[0] = 0;
    dpx[1] = money[1];

    int n = money.size() - 1;
    
    for(int i = 2; i < n + 1; i++){
        dpo[i] = max(dpo[i - 1], dpo[i - 2] + money[i]);
        dpx[i] = max(dpx[i - 1], dpx[i - 2] + money[i]);
    }
    
    int ans = max(dpo[n - 1], dpx[n]);

    return ans;
}

// https://programmers.co.kr/learn/courses/30/lessons/12900

#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    vector<long long> a(n);
    a[0] = 1; a[1] = 2;
    for(int i = 2; i < n; i++){
        a[i] = (a[i - 2] + a[i - 1]) % 1000000007;
    }
    return a[n - 1] % 1000000007;
}
// https://programmers.co.kr/learn/courses/30/lessons/42839

#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

bool issosu(int x){
    if(x <= 1) return false;
    for(int i = 2; i * i <= x; i++){
        if(x % i == 0) return false; 
    }
    return true;
}
// 몇 개인지 체크
int permu(string numbers, set<int>& check){
    int answer = 0;
    vector<int> a;
    for(int i = 0; i < numbers.size(); i++){
        a.push_back(i);
    }
    
    do{
        int k = 0;
        for(int i = 0; i < a.size(); i++){
            k *= 10;
            k += numbers[a[i]] - '0';
        }
        if(check.find(k) != check.end()) continue;
        check.insert(k);
        if(issosu(k)) answer++;
    }while(next_permutation(a.begin(), a.end()));
    
    return answer;
}

int solve(string numbers, string x, set<int>& check, int index){
    int ret = 0;
    for(int i = index; i < numbers.size(); i++){
        string s = x + numbers[i];
        ret += permu(s, check);
        ret += solve(numbers, s, check, i + 1);
    }
    return ret;
}

int solution(string numbers) {
    int answer = 0;
    set<int> check;
    answer = solve(numbers, "", check, 0);
    return answer;
}
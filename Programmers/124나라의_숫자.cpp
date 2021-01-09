//https://programmers.co.kr/learn/courses/30/lessons/12899

#include <iostream>
#include <string>
#include <vector>

using namespace std;

char c[3] = {'1', '2', '4'};

string solution(int n) {
    string answer = "";
    int tmp = 1;
    int x = 0;
    while(x + tmp * 3 < n){
        tmp *= 3;
        x += tmp;
    }
    
    int a = n - x;
    while(tmp > 0){
        answer.push_back(c[(a - 1) / tmp]);
        n -= tmp * ((a - 1) / tmp + 1);
        x -= tmp;
        tmp /= 3;
        a = n - x;
    }
    return answer;
}
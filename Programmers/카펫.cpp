//https://programmers.co.kr/learn/courses/30/lessons/42842

#include <string>
#include <vector>
#include <cmath>

using namespace std;

int getBrown(int yellow, int i){
    return 2 * (i + 2) + yellow / i * 2;
}

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    
    for(int i = 1; i <= sqrt(yellow); i++){
        if(yellow % i == 0){
            if(getBrown(yellow, i) == brown){
                answer.push_back(yellow / i + 2);
                answer.push_back(i + 2);
                break;
            }
        }
    }
    
    return answer;
}
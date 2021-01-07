//https://programmers.co.kr/learn/courses/30/lessons/42586?language=cpp

#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    int max_day = 0;
    for(int i = 0; i < speeds.size(); i++){
        int day = (((100 - progresses[i]) + speeds[i] - 1) / speeds[i]);
        if(max_day < day){
            answer.push_back(1);
            max_day = day;
        }
        else {
            answer.back()++;
        }
    }  
    
    return answer;
}
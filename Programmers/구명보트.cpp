//https://programmers.co.kr/learn/courses/30/lessons/42885

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    int l_index = 0;
    int h_index = people.size() - 1;
    sort(people.begin(), people.end());
    while(l_index < h_index){
        if(people[l_index]  + people[h_index] <= limit){
            answer++;
            l_index++;
            h_index--;
        }
        else{
            h_index--;
        }
    }
    answer += people.size() - answer * 2;
    return answer;
}
//https://programmers.co.kr/learn/courses/30/lessons/42587?language=cpp

#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    int index = 0;
    multiset<int, greater<int>> s;
    for (auto i : priorities) s.insert(i);

    auto ind = s.begin();
    while (1) {
        if (priorities[index] == *ind) {
            *ind++;
            answer++;
            if (index == location) break;
        }
        index++;
        if (index == priorities.size()) index = 0;
    }

    return answer;
}
//https://programmers.co.kr/learn/courses/30/lessons/17681

#include <string>
#include <vector>

using namespace std;

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    vector<string> answer;

    for(int i = 0; i < n; i++) {
        string s;
        
        for (int k = n - 1; k >= 0; k--) {
            if (((1 << k & arr1[i]) | (1 << k & arr2[i]))) s.push_back('#');
            else s.push_back(' ');
        }

        answer.push_back(s);
    }
    return answer;
}
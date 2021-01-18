//https://programmers.co.kr/learn/courses/30/lessons/42627#qna

#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    int p = 0;
    for (int i = 0; i < jobs.size(); i++) {
        p += jobs[i][1];
        jobs[i][1] += jobs[i][0];
    }
    sort(jobs.begin(), jobs.end());
    vector<vector<int>> a;
    a.push_back(jobs[0]);
    a[0].push_back(0);

    for (int i = 1; i < jobs.size(); i++) {
        int start = jobs[i][0];
        int end = jobs[i][1];
        int sum = 0;
        int index = i;
        for (int k = 0; k < a.size(); k++) {
            int a_st = a[k][0];
            int a_en = a[k][1];
            int x;
            
            // end - a_st : 바꿀경우 // 있던거 먼저
            if (end - a_st > a_en - start) {
                x = a_en - start < 0 ? 0 : a_en - start;
                start += x;
                end += x;
            }
            else {
                x = end - a_st < 0 ? 0 : end - a_st;
                a[k][0] = start;
                a[k][1] = end;
                start = a_st + x;
                end = a_en + x;
                swap(a[k][2], index);
            }

        }
        answer = sum;
        a.push_back({ start, end });
        a.back().push_back(index);
     
    }
    answer += p;

    for (int i = 0; i < a.size(); i++){
        answer += a[i][0] - jobs[a[i][2]][0];
    }
    return answer / jobs.size();
}
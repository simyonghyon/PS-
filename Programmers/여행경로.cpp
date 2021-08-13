//https://programmers.co.kr/learn/courses/30/lessons/43164?language=cpp#

#include <string>
#include <vector>
#include <map>
#include <set>
#include <algorithm>
#include <iostream>

using namespace std;

struct node{
    string des;
    bool check = false;

    node(string des) {
        this->des = des;
    }
};

vector<string> answer;

bool dfs(map<string, vector<node>>& m, string now, int remain) {
    if (remain == 0) {
        answer.push_back(now);
        return true;
    }


    for (int i = 0; i < m[now].size(); i++) {
        if (m[now][i].check == false) {
            m[now][i].check = true;
            if (dfs(m, m[now][i].des, remain - 1)) {
                answer.push_back(now);
                return true;
            }
            m[now][i].check = false;
        }
    }
    return false;
}

vector<string> solution(vector<vector<string>> tickets) {
    answer.clear();
    map<string, vector<node>> m;

    for (int i = 0; i < tickets.size(); i++) {
        m[tickets[i][0]].push_back(node(tickets[i][1]));
        sort(m[tickets[i][0]].begin(), m[tickets[i][0]].end(), [](node n1, node n2){
            return n1.des < n2.des;
        });
    }

    dfs(m, "ICN", tickets.size());
    reverse(answer.begin(), answer.end());
    return answer;
}
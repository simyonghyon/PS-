// https://programmers.co.kr/learn/courses/30/lessons/43163

#include <string>
#include <vector>
#include <queue>

using namespace std;

int countOfDifAlpa(string s1, string s2){
    if(s1.size() != s2.size()) return 0;
    int count = 0;
    for(int i = 0; i < s1.size(); i++){
        if(s1[i] != s2[i]) count++; 
    }
    return count;
}

int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    queue<pair<string, int>> q;
    q.push(make_pair(begin, 0));
    
    vector<bool> check(words.size(), false);
   
    while(!q.empty()){
        string word = q.front().first;
        int count = q.front().second;
        q.pop();
        
        for(int i = 0; i < words.size(); i++){
            if(check[i] == false && countOfDifAlpa(word, words[i]) == 1){
                if(words[i] == target) return count + 1;
                q.push(make_pair(words[i], count + 1));
                check[i] = true;
            }  
        }
    }

    return 0;
}
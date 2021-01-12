#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int alpha(char c){
    return min(c - 'A', 'Z' - c + 1);
}

int order(string name){
    int size = name.size();
    int ans = -1;
    int count = 0;
    int prev = 0;
    for(int i = 1; i < name.size(); i++){
        if(name[i] != 'A') {
            if(ans == -1){
                ans = name.size() - i + prev + count;
            }
            else ans = min(ans, size - i + prev + count);
            prev = i;
            count = i;
        }
    }
    ans = min(ans, count);
    return ans;
}

int solution(string name) {
    int size = name.size();
    int answer = 0;
    for(auto c : name) answer += alpha(c);
    int tmp = order(name);
    reverse(name.begin(), name.end());
    answer += min(order(name) + 1, tmp);
    return answer;
}
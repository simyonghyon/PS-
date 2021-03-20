#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int solution(vector<vector<int>> routes) {
    int answer = 1;
    sort(routes.begin(), routes.end());
    set<int> s;
    s.insert(routes[0][1]);
    int index = 1;
    
    while(index < routes.size()){
        int x = *s.begin();
        
        if(routes[index][0] > x){
            answer++;
            s.clear();
        }
        
        s.insert(routes[index][1]);
        index++;
    }
    return answer;
}
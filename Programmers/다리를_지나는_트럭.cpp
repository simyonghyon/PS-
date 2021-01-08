//https://programmers.co.kr/learn/courses/30/lessons/42583

#include <string>
#include <vector>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 1;
    vector<int> indexOfEnter(truck_weights.size());
    
    int sum = 0;
    int index = 0;
    for(int i = 0; i < truck_weights.size(); i++) {   
        while(1){
            if(sum + truck_weights[i] <= weight) break;
            
            answer = bridge_length + indexOfEnter[index];
            sum -= truck_weights[index];
            index++;
        }
        indexOfEnter[i] = answer;
        sum += truck_weights[i];
        answer++;
        
        if(indexOfEnter[index] + bridge_length == answer){
            sum -= truck_weights[index];
            index++;
        }
    }    
    answer = indexOfEnter[truck_weights.size() - 1] + bridge_length;
    return answer;
}
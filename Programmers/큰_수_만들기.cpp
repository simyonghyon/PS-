#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string number, int k) {
    
    for(int i = 0; i < number.size() - k; i++){
        auto iter = max_element(number.begin() + i, number.begin() + k + i + 1);
        if(iter != number.begin() + i) {
            k -= distance(number.begin() + i, iter);
            number.erase(number.begin() + i, iter);
        }   
    }
    number.erase(number.end() - k, number.end());
    return number;
}
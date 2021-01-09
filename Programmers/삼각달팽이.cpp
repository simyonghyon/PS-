//https://programmers.co.kr/learn/courses/30/lessons/68645

#include <string>
#include <vector>
#include <iostream>

using namespace std;
int dy[3] = {1, 0, -1};
int dx[3] = {-1, 2, -1};



vector<int> solution(int n) {
    vector<int> answer;
    vector<vector<int>> a(n, vector<int> (n * 2, -1));
    int dir = 0;
    int x = n;
    int y = 0;
    int value = 1;
    while(n > 0){
    	a[y][x] = value++;
        for(int i = 0; i < n - 1; i++){
        	x = x + dx[dir];
            y = y + dy[dir];
            a[y][x] = value++;
        }
        n--;
        dir++;
        if(dir == 3) dir = 0;
        x = x + dx[dir];
        y = y + dy[dir];
    }
    for(int i = 0; i < a.size(); i++){
        for(int k = 0; k < a[i].size(); k++){
        	//cout << a[i][k] << " ";
            if(a[i][k] != -1) answer.push_back(a[i][k]);
        }
   
    }
	 
    return answer;
}
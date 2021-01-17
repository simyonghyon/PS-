//https://programmers.co.kr/learn/courses/30/lessons/43162

#include <string>
#include <vector>
#include <set>
#include <iostream>

using namespace std;

int find(int x, vector<int>& parent){
    if(parent[x] == x) return x;
    else{
        int y = find(parent[x], parent);
        parent[x] = y;
        return y;
    }
}

void unioni(int x, int y, vector<int>& parent, vector<int>& size){
    x = find(x, parent);
    y = find(y, parent);
    if(x == y) return;
    
    if(size[x] >= size[y]){
        size[x] += size[y];
        parent[y] = x;
    }
    else {
        size[y] += size[x];
        parent[x] = y;
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    const int SIZE = computers.size();
    vector<int> parent(SIZE);
    vector<int> size(SIZE);
    for(int i = 0; i < SIZE; i++){
        parent[i] = i;
        size[i] = 1;
    }
    
    for(int i = 0; i < SIZE; i++){
        for(int k = 0; k < SIZE; k++){
            if(i == k) continue;
            if(computers[i][k] == 1){   
                unioni(i, k, parent, size);
            }
        }
    }
    set<int> a;
    for(int i = 0; i < parent.size(); i++){
        a.insert(find(parent[i], parent));
    }
    answer = a.size();
    return answer;
}
#include <vector>
#include <set>
#include <queue>

using namespace std;
int nx[4] = {1, 0, -1, 0};
int ny[4] = {0, -1, 0, 1};
// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
vector<int> solution(int m, int n, vector<vector<int>> picture) {
    int number_of_area = 0;
    int max_size_of_one_area = 0;

    queue<pair<int, int>> q;  
    vector<vector<bool>> check(m, vector<bool> (n, false));
    for(int i = 0; i < m; i++){
        for(int k = 0; k < n; k++){
            if(picture[i][k] != 0 && check[i][k] == false) {
                number_of_area++;
                q.push(make_pair(i, k));
                check[i][k] = true;
                int size_of_area = 1;
                while(!q.empty()){
                    int x = q.front().second;
                    int y = q.front().first;
                    q.pop();
                    for(int j = 0; j < 4; j++){
                        int dx = x + nx[j];
                        int dy = y + ny[j];    
                        
                        if(0 <= dy && dy < m && 0 <= dx && dx < n){
                            if(picture[dy][dx] == picture[i][k] && check[dy][dx] == false){
                                size_of_area++;
                                check[dy][dx] = true;
                                q.push(make_pair(dy, dx));
                            }
                        }
                    }
                }
                max_size_of_one_area = max(size_of_area, max_size_of_one_area);
            }
        }
    }
    
    vector<int> answer(2);
    answer[0] = number_of_area;
    answer[1] = max_size_of_one_area;
    return answer;
}
//https://programmers.co.kr/learn/courses/30/lessons/49993

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    map<char, bool> m;
    for (int i = 0; i < 26; i++)
        m['A' + i] = false;
    for (auto i : skill)
        m[i] = true;
    for (int i = 0; i < skill_trees.size(); i++) {
        int index = 0;
        bool check = true;
        for (auto k : skill_trees[i]) {
            if (m[k]) {
                if (k != skill[index]) {
                    check = false;
                    break;
                }
                index++;
            }
        }
        if (check) answer++;
    }
    return answer;
}
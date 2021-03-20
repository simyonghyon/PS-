// 못풀겠다........
#include <iostream>
#include <string>
#include <set>

using namespace std;

bool isLower(char c) {
    return 'a' <= c && c <= 'z';
}

/*
 * 단어 하나가 들어옴
 * 처음은 확실히 대문자
 * 크기는 모름 사이에 소문자인지도 모름
*/
bool between(string word, set<char>&check) {
    //cout << word << " ";
    if (word.size() == 1) return true;
    bool isB = false;
    char c;

    if (isLower(word[1])) {
        isB = true;
        c = word[1];

        if (check.find(c) != check.end()) return false;
        check.insert(c);
    }

    bool lower = true;
    for (int i = 0; i < word.size(); i++) {
        if (isB) {
            if ((lower && isLower(word[i])) || (!lower && !isLower(word[i]))) { // 사이사이 없으면 아웃
                return false;
            }
            if (isLower(word[i]) && word[i] != c) return false; // 다른 문자면 아웃
            lower = !lower;
        }
        else {
            if (isLower(word[i])) return false;
        }
    }
    return true;
}




string solution(string sentence) {
    string answer = "";
    bool isWord = false;
    set<char> check;
   
    for (int i = 0; i < sentence.size(); i++) {
        string word = "";
       
        if (!isWord) {

            if (isLower(sentence[i])) { // 처음이 소문자

                char c = sentence[i++];

                // 중복처리
                if (check.find(c) != check.end()) {
                    cout << "1"; ///
                    answer = "invalid";
                    break;
                }
                check.insert(c);

                for (i; i < sentence.size(); i++) {
                    if (sentence[i] == c) {
                        break;
                    }
                    word.push_back(sentence[i]);
                }
            }
            else { // 처음이 대문자
                if (i + 1 < sentence.size() && isLower(sentence[i + 1])) { // 사이사이 소문자
                    char c = sentence[i + 1];
                    bool lower = true;
                    int j = i;
                    int count = -1;
                    for (i; i < sentence.size(); i++) {
                        if (!lower && !isLower(sentence[i])) { // 사이사이 없으면 아웃
                        
                            if (count == 1) {
                               
                                for (int p = i; p < sentence.size(); p++) {
                                    if (isLower(sentence[p])) {
                                        if (sentence[p] == c) {
                                            
                                            i = j;
                                            word = sentence[j];
                                            break;
                                        }
                                        else {
                                            i--;
                                            break;
                                        }
                                    }
                                }
                            }
                            else i--;
                         
                            break;
                        }
                        if (lower && isLower(sentence[i])) break;
                        if (isLower(sentence[i]) && sentence[i] != c) {
                            i--;
                            break;
                        }
                        word.push_back(sentence[i]);
                        if (lower) count++;
                        lower = !lower;
                    }
                    cout << "\n";
                    cout << word << endl;
                    if (isLower(word.back())) { //끝이 소문자라면
                        i = j;
                        word = sentence[j];
                        cout << i << endl;
                    }
                    
                }
                else { // 그냥 대문자 단어 
                    word.push_back(sentence[i]);
                }
            }

            /*if (i == sentence.size()) {
                cout << "3"; /////
                answer = "invalid";
                break;
            }*/
           
            if (between(word, check)) { // 정상적 단어면 답에 넣기
                for (auto q : word) {
                    if (!isLower(q)) answer.push_back(q);
                }
                answer += " ";
            }
            else {
                cout << "4"; ////
                answer = "invalid";
                break;
            }

        }

    }
    if (answer.back() == ' ') answer.pop_back();
    return answer;
}
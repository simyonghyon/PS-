//https://programmers.co.kr/learn/courses/30/lessons/1836

import java.util.*;

class Solution {
    private HashMap<Character, ArrayList<Pair>> h = new HashMap<>();
    
    //m 세로 n 가로
    public String solution(int m, int n, String[] board) {
        String answer = "";
        
        SortedSet<Character> cs = new TreeSet<>();
        
        for(int i = 0; i < m; i++){
            for(int k = 0; k < n; k++){
                char c = board[i].charAt(k);
                if(board[i].charAt(k) != '.' && board[i].charAt(k) != '*'){
                    cs.add(c);
                    
                    ArrayList<Pair> ap = h.containsKey(c) ? h.get(c) : new ArrayList<Pair>();
                    ap.add(new Pair(i, k));
                    
                    h.put(c, ap);
                }
            }
        }
        
        while(!cs.isEmpty()){
            int count = 0;
            
            for(var i : cs){
                ArrayList<Pair> as = h.get(i);
                Pair p1 = as.get(0);
                Pair p2 = as.get(1);
                
                if(canConnect(p1, p2, board)){
                    answer += String.valueOf(i);
                            
                    board[p1.y] = board[p1.y].substring(0, p1.x) + "." + board[p1.y].substring(p1.x + 1);
                    board[p2.y] = board[p2.y].substring(0, p2.x) + "." + board[p2.y].substring(p2.x + 1);
                            
                    count++;
                    cs.remove(i);
                    break;
                }
            }
            
            if(count == 0){
                return "IMPOSSIBLE";
            }
        }

        return answer;
    }
    
    private boolean canConnect(Pair p1, Pair p2, String[] board){
        return checkPath(p1.x, p2.x, p1.y, p2.y, board) ||
            checkPath(p2.x, p1.x, p2.y, p1.y, board);
    }
    
    private boolean checkPath(int x, int x2, int y, int y2, String[] board){
        int start = x < x2 ? x : x2;
        int end = x < x2 ? x2 : x;
        char c = board[y].charAt(x);
        
        for(int i = start; i <= end; i++){
            if(board[y].charAt(i) != '.'){
                if(board[y].charAt(i) != c)
                    return false;
            }
        }
        
        start = y < y2 ? y : y2;
        end = y < y2 ? y2 : y;
        
        for(int k = start; k <= end; k++){
            if(board[k].charAt(x2) != '.'){
                if(board[k].charAt(x2) != c)
                    return false;
            }
        }

        
        return true;
    }
}

class Pair{
    int y, x;
    
    Pair(){};
    
    Pair(int y, int x){
        this.y = y;
        this.x = x;
    }
}

// 처음에 한번 살펴보고 뭐있는지랑 좌표 따놓기
// 세로 가로 두 경로에 뭐가 있는지 확인하는 함수
// 한바퀴 돌았는데 변화가 없다면 break
//https://programmers.co.kr/learn/courses/30/lessons/42890
// 비트마스크를 통해 풀수도 있다 
// 비트마스크 쓰는게 올바른 풀이같다

import java.util.*;

class Solution {
    private HashMap<String, Boolean> check = new HashMap();
    private int ans = 0;

    public int solution(String[][] relation) {
        for(int i = 1; i <= relation.length; i++){
            getColumn(0, i, -1, "", relation[0].length, relation);
        }
        return ans;
    }

    private boolean isValid(String index, String[][] relation){
        TreeSet<String> tree = new TreeSet<>();

        for(int k = 0; k < relation.length; k++){
            String s = "";
            for(int i = 0; i < index.length(); i++){
                //System.out.println(index.charAt(i) - '0');
                s += relation[k][index.charAt(i) - '0'];
            }
            tree.add(s);
        }

        return tree.size() == relation.length;
    }

    private boolean isAtumn(String index, int depth, String s){
        if(depth == index.length()){
            //System.out.println(s);
            return check.containsKey(s);
        }

        return isAtumn(index, depth + 1, s + Character.toString(index.charAt(depth))) || isAtumn(index, depth + 1, s);
    }


    private void getColumn(int depth, int tmp, int before, String index, int n,
                           String[][] relation){
        if(depth == tmp){
            if(!isAtumn(index, 0, "") && isValid(index, relation)){
                check.put(index, true);
                ans++;
            }
            return;
        }

        for(int i = before + 1; i < n; i++){
            if(check.containsKey(index)) return;

            getColumn(depth + 1, tmp, i, index + String.valueOf(i), n, relation);
        }
    }


}
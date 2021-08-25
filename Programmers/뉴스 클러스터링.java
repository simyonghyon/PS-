//https://programmers.co.kr/learn/courses/30/lessons/17677#

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public int solution(String str1, String str2) {
        double answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        HashMap<String, pair> word = new HashMap<>();

        for(int i = 0; i < str1.length() - 1; i++){
            String s = str1.substring(i, i + 2);
            putPair(s, word, "str1");
        }
        for(int i = 0; i < str2.length() - 1; i++){
            String s = str2.substring(i, i + 2);
            putPair(s, word, "str2");
        }
        

        double a = 0;
        double b = 0;

        for(var key : word.keySet()){
            pair pair = word.get(key);

            a += Math.min(pair.str1, pair.str2);
            b += Math.max(pair.str1, pair.str2);
        }

        if(b == 0) answer = 1;
        else{
            answer = a / b;
        }
        return (int)(answer * 65536);
    }

    private void putPair(String s, HashMap<String, pair> word, String type){
        if(Pattern.matches("^[A-Z]*$", s)){
            pair pair = null;

            if(word.containsKey(s)){
                pair = word.get(s);
            } else {
                pair = new pair(s);
            }

            if(type.equals("str1"))
                pair.str1 += 1;
            else
                pair.str2 += 1;

            word.put(s, pair);
        }
    }
}

class pair implements Comparable<pair>{

    String word;
    int str1 = 0;
    int str2 = 0;

    public pair(String word){
        this.word = word;
    }

    @Override
    public int compareTo(pair o) {
        return word.compareTo(o.word);
    }
}
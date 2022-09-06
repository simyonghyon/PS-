import java.util.*;

class Solution {
    private int count = 1;
    private Map<String, Integer> zip;
    
    public int[] solution(String msg) {
        zip = initZip();
        List<Integer> ans = new ArrayList<>();

        int i = 0;
        while(i < msg.length()){            
            int lastIndex = getIndexOfWordInDictionary(i, i + 1, msg);
            ans.add(zip.get(msg.substring(i, lastIndex - 1)));
            if(lastIndex <= msg.length()) {
                zip.put(msg.substring(i, lastIndex), count++);
            }
            i = lastIndex - 1;
        }
        
        int[] answer = new int[ans.size()];
        for(i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }

    private Map<String, Integer> initZip(){
        Map<String, Integer> zip = new HashMap<>();
        for(int i = 0; i < 26; i++){
            zip.put(String.valueOf(((char)('A' + i))), count++);
        }
        return zip;
    }
    
    private Integer getIndexOfWordInDictionary(int f, int l, String msg) {
        if(l <= msg.length()) {
            String word = msg.substring(f, l);
            if(zip.containsKey(word)) {
                return getIndexOfWordInDictionary(f, l + 1, msg);    
            } else {
                return l;
            }
            
        } else {
            return l;
        }
    }
}
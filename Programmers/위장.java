//https://programmers.co.kr/learn/courses/30/lessons/42578

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap();
        List<String> list = new ArrayList();
        
        for(var l : clothes){
            if(map.containsKey(l[1])) map.put(l[1], map.get(l[1]) + 1);
            else {
                map.put(l[1], 1);
                list.add(l[1]);
            }
        }
        
        for(var s : list){
            answer *= (map.get(s) + 1);
        }
        return answer - 1;
    }
}
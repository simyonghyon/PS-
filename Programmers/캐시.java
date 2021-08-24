//https://programmers.co.kr/learn/courses/30/lessons/17680

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0) return cities.length * 5;
        
        LinkedList<String> cache = new LinkedList<>();
        
        for(int i = 0; i < cities.length; i++){
            String s = cities[i].toUpperCase();
            
            if(cache.contains(s)) {
                answer += 1;
                cache.remove(s);
            } 
            else {
                answer += 5;
                if(cache.size() >= cacheSize) cache.remove(0);
            }
        
            cache.add(s);
        }
        
        return answer;
    }
}
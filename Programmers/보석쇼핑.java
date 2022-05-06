
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        HashMap<String, Integer> gemSet = new HashMap<>();
        
        for(int i = 0; i < gems.length; i++){
            if(gemSet.containsKey(gems[i])){
                gemSet.put(gems[i], gemSet.get(gems[i]) + 1);
            
            } else {
                getSet.put(gems[i], 1);
            }
        }
        
        int left = 0, right = 0;
        
        int ans = 100001;
        while(true){
            
        }
        return answer;
    }
}

// 투포인터?
// x y 
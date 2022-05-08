class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> gemSet = new HashMap<>();

        for(int i = 0; i < gems.length; i++){
            if(gemSet.containsKey(gems[i])){
                gemSet.put(gems[i], gemSet.get(gems[i]) + 1);

            } else {
                gemSet.put(gems[i], 1);
            }
        }

        HashMap<String, Integer> countOfContainKey = new HashMap<>();
        for(String s : gemSet.keySet()){
            countOfContainKey.put(s, 0);
        }
        int left = 0, right = 0;
        int getCount = countOfContainKey.keySet().size();

        int ansLeft = 0, ansRight = gems.length;
        int containKey = 0;
        for(;right < gems.length; right++){
            if(countOfContainKey.get(gems[right]) == 0){
                containKey += 1;
            }
            countOfContainKey.put(gems[right], countOfContainKey.get(gems[right]) + 1);
            while(left < right){
                String gem = gems[left];
                if(countOfContainKey.get(gem) > 1){
                    left++;
                    countOfContainKey.put(gem, countOfContainKey.get(gem) - 1);
                    if(countOfContainKey.get(gem) == 0){
                        containKey -= 1;
                    }

                } else {
                    break;
                }
            }

            if(containKey == getCount){
                if(right - left < ansRight - ansLeft){
                    ansLeft = left;
                    ansRight = right;
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = ansLeft + 1;
        answer[1] = ansRight + 1;
        return answer;
    }
}

// 투포인터?
// x y 
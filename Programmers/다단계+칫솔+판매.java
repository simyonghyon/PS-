//https://programmers.co.kr/learn/courses/30/lessons/77486
// 아오 .equlas 생각 못하고 시간 엄청 잡아먹었습니다
// c++하던 사고가 남아있어 생각을 못했네요...
// 앞으론 잘해봅시다

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        HashMap<String, Integer> profit = new HashMap();
        HashMap<String, String>member = new HashMap();

        for(int i = 0; i < referral.length; i++){
            member.put(enroll[i], referral[i]);
            profit.put(enroll[i], 0);
        }

        for(int i = 0; i < seller.length; i++){
            calculate(profit, member, amount[i] * 100, seller[i]);
        }

        for(int i = 0; i < enroll.length; i++){
            answer[i] = profit.get(enroll[i]);
        }
        return answer;
    }

    private void calculate(HashMap<String, Integer> profit, HashMap<String, String> member, int value, String parent){
        if(value <= 0) return;
        if(parent.equals("-")) return;

        int prof = value;

        prof = value - value / 10;
        value /= 10;
        
        profit.put(parent, profit.get(parent) + prof);
        parent = member.get(parent);

        calculate(profit, member, value, parent);
    }
}
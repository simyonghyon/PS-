class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = solved(numbers, 0, 0, target);
        return answer;
    }

    private int solved(int[] numbers, int depth, int sum, int target){
        if(depth == numbers.length){
            if(sum == target) return 1;
            else return 0;
        }

        int ret = 0;

        ret += solved(numbers, depth + 1, sum + numbers[depth], target);
ret += solved(numbers, depth + 1, sum - numbers[depth], target);
return ret;
}
}
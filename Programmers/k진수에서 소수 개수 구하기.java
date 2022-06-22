class Solution {

    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder("");

        while(n > 0){
            sb.insert(0, n % k);
            n = n / k;
        }

        String number = sb.toString();
        int answer = 0;

        int index = 0;

        sb = new StringBuilder("");
        while(index < number.length()){
            // System.out.println("index : " + index);
            // System.out.println("sb : " + sb);
            // System.out.println("char : " + number.charAt(index));
            if(number.charAt(index) == '0'){
                long num = sb.toString().equals("") ? 0 : Long.valueOf(sb.toString());
                if(isPrime(num)){
                    answer++;
                }
                sb = new StringBuilder("");

            } else {
                sb.append(number.charAt(index));
            }

            index++;
        }
        if(!sb.toString().equals("") && isPrime(Long.valueOf(sb.toString()))) answer++;
           
        return answer;
    }

    private boolean isPrime(long num){
        // System.out.println(num);
        if(num < 2) return false;
        
        for(long i = 2; i * i <= num; i++){
            if(num % i == 0) return false;
        }
        
        return true;
    }
}
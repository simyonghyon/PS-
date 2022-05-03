class Solution {
    public long solution(String expression) {
        long answer = 0;
        String[] dividedExpression = new String[110];
        int index = 0;
        int prev = 0;

        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            if(!Character.isDigit(c)) {
                dividedExpression[index] = expression.substring(prev, i);
                prev = i + 1;
                dividedExpression[index + 1] = expression.substring(i, i + 1);
                index += 2;
            }
        }
        dividedExpression[index] = expression.substring(prev, expression.length());
    

        String[] operator = new String[3];
        operator[0] = "*";
        operator[1] = "+";
        operator[2] = "-";
        

        long ans = 0;
        Deque<String> dq = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 3; k++){
                if(i == k) continue;
                for(int j = 0; j < 3; j++){
                    if(j == i || j == k) continue;
                    initDeque(dq, dividedExpression);
                    dq = calcAllByOperation(dq, operator[i]);
                    dq = calcAllByOperation(dq, operator[k]);
                    dq = calcAllByOperation(dq, operator[j]);

                    ans = Math.max(ans, Math.abs(Long.valueOf(dq.pop())));
                }
            }
        }

        return ans;
    }

    private void initDeque(Deque<String> dq, String[] ss){
        int i = 0;
        do{
            dq.addLast(ss[i]);
            i++;
        } while(ss[i] != null);
    }

    private Deque<String> calcAllByOperation(Deque<String> dq, String operation){
        Deque<String> next = new LinkedList<>();

        while(!dq.isEmpty()){
            String s = dq.pop();
            if(s.equals(operation)){
                String prevNum = next.pollLast();
                String nextNum = dq.pop();
                
                next.addLast(calcByOperation(prevNum, nextNum, s));
            
            } else{
                next.addLast(s);
            }
        }

        return next;
    }

    private String calcByOperation(String num1, String num2, String operation){
        if(operation.equals("*")){
            return String.valueOf(Long.valueOf(num1) * Long.valueOf(num2));

        } else if(operation.equals("+")){
            return String.valueOf(Long.valueOf(num1) + Long.valueOf(num2));

        } else {
            return String.valueOf(Long.valueOf(num1) - Long.valueOf(num2));
        }
    }
}
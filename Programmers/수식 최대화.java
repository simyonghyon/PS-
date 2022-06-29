// https://programmers.co.kr/learn/courses/30/lessons/67257
// 큐를 직접 구현해보고 싶어서 해봤습니다
// 솔직히 deque나 list 사용하면 훨씬 쉬웠을듯;;

class Solution {
    public long solution(String expression) {
        String[] calculator = {"+", "-", "*"};
        boolean[] check = new boolean[calculator.length];
        String[] priority = new String[calculator.length];
        
        return permutaion(0, check, calculator, priority, expression);
    }
    
    // 순열을 이용한 연산자 우선순위 결정
    private long permutaion(int n, boolean[] check, String[] calculator, String[] priority, String expression){
        if(n == check.length){
            // 우선순위에 따른 상금 계산
            return Math.abs(calcuPrize(expression, priority));
        }
        
        long ret = 0;
        
        for(int i = 0; i < calculator.length; i++){
            if(!check[i]){
                priority[n] = calculator[i];
                check[i] = true;
                ret = Math.max(ret, permutaion(n + 1, check, calculator, priority, expression));
                check[i] = false;
            }
        }
        
        return ret;
    }
    
    private long calcuPrize(String expression, String[] priority){
        CustomQueue<String> current = parseExpression(expression);
        CustomQueue<String> next = new CustomQueue<>();
        
        for(int i = 0; i < 3; i++){
            String pOperator = priority[i];
            
            String prev = current.poll();
            while(!current.isEmpty()){
                String operator = current.poll();
                
                if(pOperator.equals(operator)){
                    String num = calcu(prev, current.poll(), pOperator).toString();
                    prev = num;
                
                } else {
                    next.put(prev);
                    next.put(operator);
                    prev = current.poll();
                }
            }
            next.put(prev);
            current = next;
            next = new CustomQueue<String>();
        }
        
        return Long.valueOf(current.poll());
    }
    
    private String calcu(String num1, String num2, String operator){
        if(operator.equals("*")){
            return String.valueOf(Long.valueOf(num1) * Long.valueOf(num2));

        } else if(operator.equals("+")){
            return String.valueOf(Long.valueOf(num1) + Long.valueOf(num2));

        } else {
            return String.valueOf(Long.valueOf(num1) - Long.valueOf(num2));
        }
    }
    
    // 문자열 각 숫자와 연산자로 분할
    private CustomQueue<String> parseExpression(String expression){
        CustomQueue<String> parsedExpression = new CustomQueue<>();
        
        int k = 0;
        for(int i = 0; i < expression.length(); i++){
            if(!Character.isDigit(expression.charAt(i))){
                parsedExpression.put(expression.substring(k, i));
                parsedExpression.put(expression.substring(i, i + 1));
                k = i + 1;
            }
        }
        parsedExpression.put(expression.substring(k, expression.length()));
               
        return parsedExpression;
    }

}

class CustomQueue<T> {
    Node<T> root;
    Node<T> tail;
    int size;
    
    public CustomQueue(){
        this.root = null;
        this.tail = null;
        size = 0;
    }
    
    public void put(T value){
        Node node = new Node(value);
        
        if(tail == null){
            root = tail = node;
        
        } else {
            tail.next = node;
            tail = node;
        }
        
        size++;
    }
    
    public T peek(){
        return root.value;
    }
    
    public T poll(){
        T value = root.value;
        root = root.next;
        if(root == null){
            tail = null;
        }
        size -= 1;
        return value;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    class Node<T> {
        final T value;
        Node<T> next = null;
        
        Node(T value){
            this.value = value;
        }
        
        public void putNextNode(Node<T> next){
            this.next = next;
        }
    }
}

// 연산자 우선순위를 재조합
// 결과가 음수면 절대값
// 숫자를 가장 크게
// 연산자는 3가지만
// long

// 순회하며 최고 우선순위의 연산만 수행
// 그걸 3번 반복
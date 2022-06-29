//https://www.acmicpc.net/problem/16637

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException{
        Solution s = new Solution();
        System.out.println(s.solution());
    }
}

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public long solution() throws IOException, NumberFormatException{
        return (long)solve(Integer.valueOf(br.readLine()), br.readLine());
    }
    
    private int solve(int n, String expression){
        boolean[] selected = new boolean[expression.length() / 2];
        return search(0, expression, selected);
    }
    
    private int search(int m, String expression, boolean[] selectedCalculator){
        if(m >= expression.length() / 2){
            CustomDeque<String> parsedExpression = parseExpression(expression.length(), expression);
            return calculateBracket(parsedExpression, selectedCalculator);
        }
        
        int ret = Integer.MIN_VALUE;
        
        ret = Math.max(ret, search(m + 1, expression, selectedCalculator));
        selectedCalculator[m] = true;
        ret = Math.max(ret, search(m + 2, expression, selectedCalculator));
        selectedCalculator[m] = false;
        
        return ret;
    } 
    
    private int calculateBracket(CustomDeque<String> parsedExpression, boolean[] selectedCalculator){
        CustomDeque<String> next = new CustomDeque<>();
        
        int i = 0;
        String prev = parsedExpression.pollFront();
        while(!parsedExpression.isEmpty()){
            String calculator = parsedExpression.pollFront();
            String num = parsedExpression.pollFront();
            
            if(selectedCalculator[i]){
                prev = calcByCalculator(prev, num, calculator);
            
            } else {
                next.putBack(prev);
                next.putBack(calculator);
                prev = num;
            }
            i++;
        }
        next.putBack(prev);
        
        prev = next.pollFront();
        while(!next.isEmpty()){
            String calculator = next.pollFront();
            String num = next.pollFront();    
            prev = calcByCalculator(prev, num, calculator);
        }
        
        return Integer.valueOf(prev);
    }
    
    private String calcByCalculator(String num1, String num2, String calculator){
        if(calculator.equals("+")){
            return String.valueOf(Integer.valueOf(num1) + Integer.valueOf(num2));
        
        } else if(calculator.equals("-")){
            return String.valueOf(Integer.valueOf(num1) - Integer.valueOf(num2));
            
        } else {
            return String.valueOf(Integer.valueOf(num1) * Integer.valueOf(num2));
                
        }
    }
    
    private CustomDeque<String> parseExpression(int n, String expression){
        CustomDeque<String> parsedExpression = new CustomDeque<>();
        for(int i = 0; i < expression.length(); i++){
            parsedExpression.putBack(expression.substring(i, i + 1));       
        }
    
        return parsedExpression;
    }
}

class CustomDeque<T> {
    Node<T> root;
    Node<T> tail;
    int size;
    
    public CustomDeque(){
        this.root = null;
        this.tail = null;
        this.size = 0;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void putBack(T value){
        Node<T> node = new Node<>(value);
        
        if(tail == null){
            root = tail = node;
        
        } else {
            tail.setNextNode(node);
            tail = node;
        }
        
        size += 1;
    }
    
    public T pollFront(){
        T value = root.value;
        root = root.next;
        if(root == null){
            tail = null;
        }
        size -= 1;
        return value;
    }
    
    class Node<T> {
        final T value;
        Node<T> next;
        
        Node(T value){
            this.value = value;
            this.next = null;
        }
        
        public void setNextNode(Node<T> node){
            this.next = node;
        }
    }
}




// 길이가 n
// 괄호는 처음에는 없고, 추가하려면 추가
// 만들 수 있는 결과의 최댓값
// 괄호안에 괄호는 안됨
// 연산자 우선순위는 동일
// 괄호안에는 연산자가 하나만
// 1 + 1 + 1 + 1 + 1
// 연산자를 기준으로 완탐
// 최대 연산자 9개 있다 2^9

// 괄호붙은 연산자 인덱스 저장해놓고, 먼저 처리하고
// 안붙은거 처리
// 큐 2개 사용, curr, next
// 
// 1) 만약 괄호안붙었으면
// 1-1) 숫자 1, 연산자 1 next로 넘어감
// 2) 만약 괄호 붙었으면
// 2-1) 계산하고 넘어감
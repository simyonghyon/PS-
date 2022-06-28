import java.util.*;

class Solution {
    Node[] board = new Node[10];
    
    public String solution(int[] numbers, String hand) {
        
        board[0] = new Node(3, 1);
        for(int i = 0, node = 1; i < 3; i++){
            for(int k = 0; k < 3; k++){
                board[node++] = new Node(i, k);
            }
        }
        
        StringBuilder sb = new StringBuilder("");
        Node left = new Node(3, 0);
        Node right = new Node(3, 2);
        for(int i = 0; i < numbers.length; i++){
            String whichHand = getHand(numbers[i], left, right, hand);
            
            if(whichHand == "R"){
                right = board[numbers[i]];
            
            } else {
                left = board[numbers[i]];
            }
            
            sb.append(whichHand);
        }
        
        return sb.toString();
    }
    
    private String getHand(int i, Node left, Node right, String hand){
        switch(i){
            case 1: case 4: case 7:
                return "L";
            case 3: case 6: case 9:
                return "R";
            default:
                return getNearestHand(i, left, right, hand); 
         }
    }
    
    private String getNearestHand(int i, Node left, Node right, String hand){
        Node num = board[i];
        int leftDistance = getDistance(num, left);
        int rightDistance = getDistance(num, right);
        
        if(leftDistance > rightDistance){
            return "R";
        
        } else if(leftDistance < rightDistance){
            return "L";
        
        } else {
            return hand.equals("right") ? "R" : "L";
        }
    }
    
    private int getDistance(Node num, Node hand){
        return Math.abs(num.y - hand.y) + Math.abs(num.x - hand.x);
    }
}

class Node {
    final int y;
    final int x;
    
    Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}
// 1, 4, 7 왼손
// 3, 6. 9 오른손
// 2, 5, 8, 0 가까운 손
// 거리가 같으면 왼손잡이는 왼손 오른손잡이는 오른손
class Solution {
    
    public String solution(int[] numbers, String hand) {
        Node[] nums = new Node[10]; 
        
        for(int i = 1; i < 10; i++){
            nums[i] = new Node((i-1) / 3, (i-1) % 3);
        }
        nums[0] = new Node(3, 1);
        
        Node left = new Node(3, 0);
        Node right = new Node(3, 2);
        
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < numbers.length; i++){
            int num = numbers[i];
            if(num == 1 || num == 4 || num == 7){
                sb.append("L");
                left = nums[num];
            
            } else if(num == 3 || num == 6 || num == 9){
                sb.append("R");
                right = nums[num];
            
            } else{
            String handType = calcNearestHand(left, right, nums[numbers[i]], hand);
            sb.append(handType);
            
            if(handType.equals("L")){
                left = nums[numbers[i]];
            
            } else {
                right = nums[numbers[i]];
            }
        }
        }
        return sb.toString();
    }
    
    private String calcNearestHand(Node left, Node right, Node nums, String hand){
        int leftDistance = getDistance(left, nums);
        int rightDistance = getDistance(right, nums);
        if(leftDistance > rightDistance){
            return "R";
        
        } else if(leftDistance < rightDistance){
            return "L";
        
        } else {
            return hand.substring(0, 1).toUpperCase();
        }
    }
    
    private int getDistance(Node hand, Node num){
        return Math.abs(hand.y - num.y) + Math.abs(hand.x - num.x);
    }
    
    private class Node{
        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
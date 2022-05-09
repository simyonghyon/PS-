
class Solution {
    char[][] key = {{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'}
            , {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'}};

    public int[] solution(String line) {
        int[] answer = new int[line.length()];

        HashMap<Character, Node> keyboard = new HashMap<>();

        for(int i = 0; i < 2; i++){
            for(int k = 0; k < 10; k++){
                keyboard.put(key[i][k], new Node(i, k));
            }
        }

        Hand leftHand = new Hand(1, 0);
        Hand rightHand = new Hand(1, 9);

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            Node node = keyboard.get(c);

            int hand = whatHand(leftHand, rightHand, node);
            if(hand == 0){
                leftHand.changeHand(node.getY(), node.getX());

            } else {
                rightHand.changeHand(node.getY(), node.getX());
            }
            answer[i] = hand;
        }
        return answer;
    }

    private int whatHand(Hand left, Hand right, Node key){
        if(left.calcDistance(key.getY(), key.getX()) > right.calcDistance(key.getY(), key.getX())){
            return 1;

        } else if(left.calcDistance(key.getY(), key.getX()) < right.calcDistance(key.getY(), key.getX())){
            return 0;

        } else {
            if(Math.abs(left.getX() - key.getX()) > Math.abs(right.getX() - key.getX())){
                return 1;

            } else if(Math.abs(left.getX() - key.getX()) < Math.abs(right.getX() - key.getX())){
                return 0;

            } else {
                if(key.getX() >= 5){
                    return 1;

                } else {
                    return 0;
                }
            }
        }
    }
}

class Node{
    private int y, x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

class Hand {
    private int y, x;

    public Hand(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int calcDistance(int y, int x){
        return Math.abs(this.y - y) + Math.abs(this.x - x);
    }

    public void changeHand(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
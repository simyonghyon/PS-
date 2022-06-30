import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        int d = Integer.valueOf(st.nextToken());
        
        String[] board = new String[n];
        for(int i = 0; i < n; i++){
            String s = br.readLine().replace(" ", "");
            board[i] = s;
        }
        
        Solution s = new Solution();
        System.out.println(s.solution(n, m, d, board));
    }
}

class Solution {
    int[] dx = {-1, 0, 1};
    int[] dy = {0, -1, 0};
    
    public int solution(int n, int m, int d, String[] board) {
        int[] archer = new int[3];
        return search(0, 0, n, m, board, archer, d);
    }
    
    private int search(int i, int j, int n, int m, String[] board, int[] archer, int d){
        if(i == 3){
            return numberOfDeadlyEnemy(n, m, board, archer, d);
        }
        
        int ret = 0;
        for(int k = j; k < m; k++){
            archer[i] = k;
            ret = Math.max(search(i + 1, k + 1, n, m, board, archer, d), ret);
        }
        
        return ret;
    }
    
    private int numberOfDeadlyEnemy(int n, int m, String[] board, int[] archer, int d){
        int count = 0;
        Enemy[][] enemyBoard = parseBoardByEnemy(board);
        
        for(int i = board.length; i > 0; i--){
            CustomQueue<Enemy> dieEnemy = new CustomQueue<>();
            
            for(int k = 0; k < 3; k++){
                Enemy enemy = findNearestEnemy(n, m, enemyBoard, new Node(i, archer[k], d));
                
                if(enemy != null && !enemy.isHit()) {
                    count += 1;
                    enemy.hit();
                    dieEnemy.add(enemy);
                }
            }
            
            while(!dieEnemy.isEmpty()){
                dieEnemy.poll().die();
            }
        }
        
        return count;
    }
    
    // 궁수로부터 가장 가까운 적을 확인하는 함수
    private Enemy findNearestEnemy(int n, int m, Enemy[][] board, Node archer){
        CustomQueue<Node> q = new CustomQueue<>();
        q.add(new Node(archer.y - 1, archer.x, archer.d - 1));
        boolean[][] check = new boolean[board.length][board[0].length];
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int y = node.y;
            int x = node.x;
            int d = node.d;
            
            if(board[y][x] != null && !board[y][x].isDie()){
                return board[y][x];
            }
            
            for(int i = 0; i < 3; i++){
                int nx = dx[i] + x;
                int ny = y + dy[i];
                
                if(0 <= ny && ny < n && 0 <= nx && nx < m){
                    if(!check[ny][nx] && d > 0){
                        check[ny][nx] = true;
                        q.add(new Node(ny, nx, d - 1));
                    }
                }
            }
        }
        
        return null;
    }
    // 거리는 한 턴당 1씩 줄어든다 라고 생각하면 되나?
    // bfs, 왼쪽 위 오른쪽 순으로 탐색
    // 만나면 걔를 죽임, 거리 벗어날때까지 못만나면 종료
    // 이것을 반복
    // 만약 같은적을 동시에 쏠 경우?
    // 

    private Enemy[][] parseBoardByEnemy(String[] board){
        Enemy[][] enemyBoard = new Enemy[board.length][board[0].length()];
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[0].length(); k++){
                if(board[i].charAt(k) == '1'){
                    enemyBoard[i][k] = new Enemy(i, k);
                }
            }
        }
        
        return enemyBoard;
    }
    
    class Node {
        final int y;
        final int x;
        final int d;
    
        Node(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}

class Enemy {
    int y;
    int x;
    boolean isDie;
    boolean isHit;
    
    Enemy(int y, int x){
        this.y = y;
        this.x = x;
        this.isDie = false;
        this.isHit = false;
    }
    
    public boolean isHit(){
        return isHit;
    }
    
    public boolean hit(){
        return this.isHit = true;
    }
    
    public boolean isDie(){
        return this.isDie;
    }
    
    public boolean die(){
        return this.isDie = true;
    }
}






class CustomQueue<T> {
    Node<T> root;
    Node<T> tail;
    int size;
    
    CustomQueue(){
        root = tail = null;
        size = 0;
    }
    
    public void add(T value){
        Node<T> node = new Node(value);
        
        if(tail == null){
            tail = root = node;
        
        } else {
            tail.next = node;
            tail = node;
        }
        size += 1;
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
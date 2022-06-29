// https://www.acmicpc.net/problem/17070
// 사실 풀었다고 하기 조금 힘들다
// 테케 약해서 통과했다고 해야하나... c++이면 여유롭게 통과했을듯
 

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
    int[] dxCross = {1, -1, 1};
    int[] dyCross = {0, -1, 1};
    int[] dxVertical = {-1, 0, 1};
    int[] dyVertical = {-1, 1, 1};
    int[] dxDiagonal = {1, 0, 1};
    int[] dyDiagonal = {0, 1, 1};
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public int solution() throws NumberFormatException, IOException{
        int n = Integer.valueOf(br.readLine());
        String[] board = new String[n];
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            s = s.replace(" ", "");
            board[i] = s;
        }
        return solve(n, board);
    }
    
    private int solve(int n, String[] board){
        if(board[board.length - 1].charAt(board.length - 1) == '1')
            return 0;
        return bfs(n, board);
    }
    
    private int bfs(int n, String[] board){
        int count = 0;
        CustomQueue<Pipe> q = new CustomQueue<>();
        q.add(new Pipe(0, 1, 0));
        
        while(!q.isEmpty()){
            Pipe pipe = q.poll();
            int y = pipe.y;
            int x = pipe.x;
            //System.out.println(y + " " + x);
            int state = pipe.state;
            
            if(y == n - 1 && x == n - 1){
                count += 1;
                continue;
            }
            
            for(int i = 0; i < 3; i++){
                if((state == 0 && i == 1) || (state == 1 && i == 0)){
                    continue;
                }
                int ny, nx;
                
                if(state == 0){    
                    nx = x + dxCross[i];
                    ny = y + dyCross[i];
                
                } else if(state == 1){
                    nx = x + dxVertical[i];
                    ny = y + dyVertical[i];
                    
                } else {
                    nx = x + dxDiagonal[i];
                    ny = y + dyDiagonal[i];
                }
                
                if(0 <= ny && ny < n && 0 <= nx && nx < n){
                    //System.out.println(board[ny].charAt(nx));
                    if(board[ny].charAt(nx) == '0'){
                        if(i == 2 && (board[ny - 1].charAt(nx) == '1' || board[ny].charAt(nx - 1) == '1')){
                            continue;
                        }
                        
                        q.add(new Pipe(ny, nx, i));
                    } 
                }
            }
        }
        
        return count;
    }
}

class Pipe {
    final int y;
    final int x;
    final int state;
    
    Pipe(int y, int x, int state){
        this.y = y;
        this.x = x;
        this.state = state;
    }
}

class CustomQueue<T> {
    Node<T> root;
    Node<T> tail;
    int size;
    
    CustomQueue(){
        this.root = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void add(T value){
        Node<T> node = new Node(value);
        
        if(tail == null){
            tail = root = node;
        
        } else {
            tail.setNextNode(node);
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
        
        public void setNextNode(Node next){
            this.next = next;
        }
    }
}
// 정사각형
// 세로 가로
// 1부터 시작
// 빈 칸만 차지
// 45도로만 회전 가능
// 오른쪽, 아래, 아래 대각선으로만 이동 가능
// 회전 이동 한번에 수행하네
// 이동시키는 방법의 개수
// 완탐, bfs

// pipe(위치, 상태) 이동
// 가로{}, 세로{}, 대각{} 
// 
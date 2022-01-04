// https://programmers.co.kr/learn/courses/30/lessons/84021#
// 정말 비효율 그 자체로 푼 문제입니다...
// 이렇게 비교하는 경우 사각형으로 가공하면 된다는 생각을 못했어요 앞으로는 합시다
// 괜히 시간 낭비만 한 것 같기도 하고.. 다음부터는 좀 아니다 싶으면 답이나 봐야겠습니다

import java.util.*;



class Solution {
    private ArrayList<Shapes> shapes = new ArrayList<>();
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        LinkedList<Shapes> shapes = new LinkedList<>();

        for(int i = 0; i < table.length; i++){
            for(int k = 0; k < table[0].length; k++){
                if(table[i][k] == 1){
                    shapes.add(searchShape(table, i, k));
                }
            }
        }


        for(int i = 0; i < game_board.length; i++){
            for(int k = 0; k < game_board[0].length; k++){
                if(game_board[i][k] == 0) {
                    int size = 1;
                    LinkedList<Pair> linkedList = new LinkedList<>();

                    Queue<Pair> queue = new LinkedList<>();

                    // 좌표따기
                    queue.add(Pair.of(i, k));
                    linkedList.add(queue.peek());
                    game_board[i][k] = 2;

                    while (!queue.isEmpty()) {
                        Pair pair = queue.poll();

                        for (int j = 0; j < 4; j++) {
                            int ny = pair.y + dy[j];
                            int nx = pair.x + dx[j];

                            if (0 <= ny && ny < table.length && 0 <= nx && nx < table[0].length) {
                                if (game_board[ny][nx] == 0) {
                                    linkedList.add(Pair.of(ny, nx));
                                    size++; // 도형 크기
                                    game_board[ny][nx] = 2; // 확인
                                    queue.add(Pair.of(ny, nx)); // 큐
                                }
                            }
                        }
                    }

                    for(var p : linkedList){
                        if(isMatch(game_board, shapes, p.y, p.x, size)){
                            answer += size;
                            //System.out.println(answer);
                            break;
                        }
                    }
                    /*
                    for(int i1 = 0; i1 < game_board.length; i1++){
                        for(int k2 = 0; k2 < game_board.length; k2++){
                            System.out.print(game_board[i1][k2]);
                        }
                        System.out.println();
                    }
                    System.out.println();*/
                }
                


            }
        }
        return answer;
    }

    private boolean isMatch(int[][] game_board, LinkedList<Shapes> shapes, int y, int x, int size){

        for(var s : shapes){
            for(var a : s.shapeList){
                boolean flag = false;
                
                if(a.size() != size) continue;
                
                for(var p : a) {
                    int ny = y + p.y;
                    int nx = x + p.x;

                    if (!(0 <= ny && ny < game_board.length && 0 <= nx && nx < game_board[0].length)) {
                        flag = true;
                        break;
                    }

                    if (game_board[ny][nx] == 1) {
                        flag = true;
                        break;
                    }

                }

                if(!flag) {
                    shapes.remove(s);
                    return true;
                }
            }
        }

        return false;
    }

    private Shapes searchShape(int[][] table, int y, int x){
        Queue<Pair> queue = new LinkedList<>();
        Shapes shapes = new Shapes();
        ArrayList<Pair> shape = new ArrayList<>();

        queue.add(Pair.of(y, x));
        shapes.size = 1;
        table[y][x] = 0;
        shape.add(Pair.of(y - y, x - x));

        while(!queue.isEmpty()){
            Pair pair = queue.poll();

            for(int i = 0; i < 4; i++){
                int ny = pair.y + dy[i];
                int nx = pair.x + dx[i];

                if(0 <= ny && ny < table.length && 0 <= nx && nx < table[0].length){
                    if(table[ny][nx] == 1){
                        shapes.size++; // 도형 크기
                        table[ny][nx] = 0; // 확인
                        queue.add(Pair.of(ny, nx)); // 큐
                        shape.add(Pair.of(ny - y, nx - x)); // 도형상대좌표
                    }
                }
            }
        }

        shapes.shapeList.add(shape);

        circle(shapes);

        return shapes;
    } // 현재좌표 - 원래 좌표 = 상대좌표


    private void circle(Shapes shapes) {

        ArrayList<Pair> tmp = shapes.shapeList.get(0);
        ArrayList<Pair> shape = new ArrayList<>();
        for (int k = 0; k < tmp.size(); k++) {
            shape.add(Pair.of(-1 * tmp.get(k).x, tmp.get(k).y));
        }
        shapes.shapeList.add(shape);


        shape = new ArrayList<>();
        tmp = shapes.shapeList.get(1);

        for (int k = 0; k < tmp.size(); k++) {
            shape.add(Pair.of(-1 * tmp.get(k).x, tmp.get(k).y));
        }
        shapes.shapeList.add(shape);


        shape = new ArrayList<>();
        tmp = shapes.shapeList.get(2);

        for (int k = 0; k < tmp.size(); k++) {
            shape.add(Pair.of(-1 * tmp.get(k).x, tmp.get(k).y));
        }
        shapes.shapeList.add(shape);
    }
    // -1 곱한다음 x y 바꾸기, 넣기
}
// 사각형 안을 전부 체우기
// board 가 1이면 진행한다.
    // 확인 4방향중 최소 한방향은 0
    // 한방향도 0 아니면 아웃



class Shapes {
    ArrayList<ArrayList<Pair>> shapeList = new ArrayList<>();
    int size;


    Shapes(){}

    public static Shapes of(){
        Shapes shape = new Shapes();

        return shape;
    }

}



class Pair{
    int x, y;
    int cost = 0;
    public Pair(int y, int x, int cost) {
        this.x = x;
        this.y = y;
    }

    Pair(){}

    public static Pair of(int y, int x){
        Pair pair = new Pair();
        pair.x = x;
        pair.y = y;
        return pair;
    }
}
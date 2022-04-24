class Solve {

    private int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    private BufferedReader br = Main.br;
    private BufferedWriter bw = Main.bw;
    private StringTokenizer st;

    private int n;
    private int m;
    private final int MOD = 1000000007;

    public void solve() throws IOException {
        //st = new StringTokenizer(br.readLine(), " ");
        //n = Integer.valueOf(st.nextToken());

        int[][] board = new int[4][4];
        Fish[] sortedFishList = new Fish[16];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int k = 0; k < 4; k++){
                int num = Integer.valueOf(st.nextToken());
                int direction = Integer.valueOf(st.nextToken());

                Fish fish = new Fish(i, k, num, direction);
                board[i][k] = fish.num;
                sortedFishList[fish.num] = fish;
            }
        }

        Board fishBoard = new Board(board, sortedFishList);
        fishBoard.eatFish(0, 0);
    }
    // 상어 가장 큰 물고기 먹은 점수
    // 바라보는 방향으로 이동 가능, 몇 칸 이동할지는 상어 마음
    // 먹으면 그 물고기의 방향을 가짐
    // 더 먹을거없으면 끝

    // 완탐, 백트래킹
    // 분기는 이동하는 칸의 개수

    private int max = 0;
    public void recursive(Board fishBoard){
        Fish[] fishList = fishBoard.sortedFishListOrderByNum;

        fishBoard.moveAllFish();

        int y = fishBoard.sharkY;
        int x = fishBoard.sharkX;

        while(isNotOutOfBound(4, 4, y, x)){
            y = y + dy[fishBoard.sharkDirection];
            x = x + dx[fishBoard.sharkDirection];

            fishBoard.eatFish(y, x);
        }
    }

    class Fish{
        int y, x;
        private final int num;
        int direction;
        boolean isDeleted = false;

        public Fish(int y, int x, int num, int direction) {
            this.y = y;
            this.x = x;
            this.num = num;
            this.direction = direction;
        }

        public void changeDirection(){
            direction = direction == 8 ? 1 : direction + 1;
        }

        public void recoverDirection(){
            direction = direction == 1 ? 8 : direction - 1;
        }

        public Fish clone(){
            return new Fish(y, x, num, direction);
        }
    }

    class Board{
        private int[][] board = new int[4][4];
        private Fish[] sortedFishListOrderByNum = new Fish[16];
        private int sharkY = 0, sharkX = 0;
        private int sharkDirection;

        public Board(int[][] board, Fish[] sortedFishListOrderByNum) {
            this.board = board;
            this.sortedFishListOrderByNum = sortedFishListOrderByNum;
        }

        public Board clone(){
            Fish[][] boardClone = new Fish[4][4];
            for(int i = 0; i < 4; i++){
                for(int k = 0; k < 4; k++){

                }
            }
        }

        public void eatFish(int y, int x){
            sortedFishListOrderByNum[board[y][x]].isDeleted = true;
            sharkY = y;
            sharkX = x;
            sharkDirection = sortedFishListOrderByNum[board[y][x]].direction;
            board[y][x] = -1;
        }

        public boolean moveAllFish(){
            for(int i = 1; i <= 16; i++){
                if(sortedFishListOrderByNum[i].isDeleted) continue;

                while(!moveFishIfCan(sortedFishListOrderByNum[i], sharkY, sharkX)){
                    sortedFishListOrderByNum[i].changeDirection();
                }
            }

            return true;
        }

        public boolean moveFishIfCan(Fish fish, int sharkY, int sharkX){
            int y = fish.y;
            int x = fish.x;

            int nx = x + dx[fish.direction];
            int ny = y + dy[fish.direction];

            if(!isNotOutOfBound(ny, nx, 4, 4) || (sharkY == ny && sharkX == nx)){
                return false;
            }

            board[y][x] = board[ny][nx];
            board[ny][nx] = fish.num;

            return true;
        }

        public Fish getFishInSortedFishList(int num){
            if(sortedFishListOrderByNum[num] != null){
                return sortedFishListOrderByNum[num];
            }
            else{
                return null;
            }
        }


    }


    private boolean isNotOutOfBound(int ny, int nx, int n, int m){
        return 0 <= ny && ny < n && 0 <= nx && nx < m;
    }

}
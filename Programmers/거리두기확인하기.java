class Solution {
    //    private int[] dx = {1, 0, -1, 0};
     //   private int[] dy = {0, 1, 0, -1};
    
        private int[] dx = {1, 0, 1};
        private int[] dy = {0, 1, 1};
    
        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
    
            for(int i = 0; i < places.length; i++){
                boolean flag = false;
    
                for(int k = 0; k < places[0].length; k++){
                    for(int j = 0; j < places[i][k].length(); j++){
                        char sit = places[i][k].charAt(j);
                        if(sit == 'P'){
                            if(!keepDistance(i, k, j, sit, places)){
                                flag = true;
                                break;
                            }
    
                        } else{
                            continue;
                        }
                    }
                }
    
                if(flag){
                    answer[i] = 0;
    
                } else{
                    answer[i] = 1;
                }
            }
    
            return answer;
        }
    
        private boolean keepDistance(int i, int k, int j, char sit, String[][] places){
            return checkRight(k, j, places[i]) && checkRight2(k, j, places[i]) && checkUnder(k, j, places[i])
                    && checkUnder2(k, j, places[i]) && checkDiagonal(k, j, places[i]);
        }
    
        private boolean checkRight(int k, int j, String[] places){
            if(j >= 4) return true;
            char right = places[k].charAt(j + 1);
            if(right == 'P') return false;
            return true;
        }
        private boolean checkRight2(int k, int j, String[] places){
            if(j >= 3) return true;
            char right = places[k].charAt(j + 1);
            char right2 = places[k].charAt(j + 2);
            if(right2 == 'P' && right == 'O') return false;
            return true;
        }
        private boolean checkUnder(int k, int j, String[] places){
            if(k >= 4) return true;
            char under = places[k + 1].charAt(j);
            if(under == 'P') return false;
            return true;
        }
        private boolean checkUnder2(int k, int j, String[] places){
            if(k >= 3) return true;
            char under = places[k + 1].charAt(j);
            char under2 = places[k + 2].charAt(j);
            if(under2 == 'P' && under == 'O') return false;
            return true;
        }
        private boolean checkDiagonal(int k, int j, String[] places){
            if(k >= 4 || j >= 4) return true;
            char under = places[k + dy[1]].charAt(j + dx[1]);
            char right = places[k + dy[1]].charAt(j + dx[1]);
            char diagonal = places[k + dy[2]].charAt(j + dx[2]);
    
            if(diagonal == 'P' && (under == 'O' || right == 'O')) return false;
            return true;
        }
    
        private boolean isNotOutOfBound(int ny, int nx, int n, int m){
            return 0 <= ny && ny < n && 0 <= nx && nx < m;
        }
    
        class Node{
            int y, x;
    
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
    
    
    }
    // 인접한 블록에 사람이 있으면 아웃
    // 대각선에 있다면 빈 책상이면 아웃
    // n - 1까지만 확인하면 된다
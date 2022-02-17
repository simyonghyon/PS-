// 기본적인 세그트리 문제였다. 구현하는데 1시간 넘게걸린 내가 레전드
// https://www.acmicpc.net/problem/11505

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);

        int t = 1;

        //t = Integer.parseInt(br.readLine());

        Solve solve = new Solve();

        while(t > 0){
            solve.solve();
            t--;
        }
    }

}


class Solve {

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    static BufferedReader br = Main.br;
    static BufferedWriter bw = Main.bw;
    private StringTokenizer st;

    private int n;
    private int m;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        int k =Integer.valueOf(st.nextToken());

        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = Integer.valueOf(br.readLine());
        }

        Node[] segTree = new Node[n * 4];
        initSegTree(segTree, 0, n - 1, 0, nums);

        for(int i = 0; i < m + k; i++){
            st = new StringTokenizer(br.readLine(), " ");

            if(Integer.valueOf(st.nextToken()) == 1){
                changeValue(segTree, 1, n, 0, nums, Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));

            } else{
                System.out.println(getValue(segTree, 1, n, 0, nums, Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
            }
        }
    }

    class Node{
        int left, right;
        long value;

        public Node(int left, int right, long value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    private void initSegTree(Node[] segTree, int left, int right, int index, int[] nums){
        if(left == right){
            segTree[index] = new Node(left, right, nums[left]);
            return;
        }

        initSegTree(segTree, left, (left + right) / 2, index * 2 + 1, nums);
        initSegTree(segTree, (left + right)/2 + 1, right, index * 2 + 2, nums);

        segTree[index] = new Node(left, right, (segTree[index*2+1].value * segTree[index*2+2].value % 1000000007));
    }

    private void changeValue(Node[] segTree, int left, int right, int index, int[] nums, int target, int change){
        if(left == right && right == target){
            segTree[index] = new Node(left, right, change);
            return;
        }

        if(left <= target && target <= (left + right) / 2){
            changeValue(segTree, left, (left + right)/2, index*2+1, nums, target, change);
        } else if((left + right) / 2 + 1 <= target && target <= right){
            changeValue(segTree, (left + right) / 2 + 1, right, index*2+2, nums, target, change);
        }

        segTree[index] = new Node(left, right, (segTree[index*2+1].value * segTree[index*2+2].value % 1000000007));
    }

    private long getValue(Node[] segTree, int left, int right, int index, int[] nums, int targetLeft, int targetRight){
        if(targetLeft <= left && right <= targetRight){
            return segTree[index].value;
        }

        long ret = 1;
        if(targetLeft <= (left + right) / 2 && targetRight >= left){
            ret = ret * getValue(segTree, left, (left + right)/2, index*2+1, nums, targetLeft, targetRight) % 1000000007;

        }

        if(targetRight >= (left + right) / 2 + 1 && targetLeft <= right){
            ret = ret * getValue(segTree, (left + right) / 2+ 1, right, index*2+2, nums, targetLeft, targetRight) % 1000000007;
        }

        return ret % 1000000007;
    }
}
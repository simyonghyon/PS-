//https://www.acmicpc.net/problem/14428

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;


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
    private final int MOD = 1000000007;

    public void solve() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.valueOf(st.nextToken());

        int[] nums = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++){
            nums[i] = Integer.valueOf(st.nextToken());
        }
        nums[0] = Integer.MAX_VALUE;

        int[] segTree = new int[3 * n + 1];
        initTree(segTree, nums, 1, n, 0);

        m = Integer.valueOf(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            if(Integer.valueOf(st.nextToken()) == 1){
                int target = Integer.valueOf(st.nextToken());
                int changeValue = Integer.valueOf(st.nextToken());
                nums[target] = changeValue;
                changeTree(segTree, nums, 1, n, 0, target);

            } else{
                int left = Integer.valueOf(st.nextToken());
                int right = Integer.valueOf(st.nextToken());

                System.out.println(searchTree(segTree, nums, 1, n, 0, left, right));
            }
        }
    }

    private void initTree(int[] segTree, int[] nums, int left, int right, int index){
        if(left == right){
            segTree[index] = left;
            return;
        }

        initTree(segTree, nums, left, (left+right)/2, index * 2 + 1);
        initTree(segTree, nums, (left+right)/2+1, right, index*2+2);

        segTree[index] = nums[segTree[index*2+1]] <= nums[segTree[index*2+2]] ? segTree[index*2+1] : segTree[index*2+2];
    }

    private void changeTree(int[] segTree, int[] nums, int left, int right, int index, int target){
        if(left == right && left == target){
            return;
        }

        if(target <= (left + right) / 2)
            changeTree(segTree, nums, left, (left+right)/2, index * 2 + 1, target);
        else changeTree(segTree, nums, (left+right)/2+1, right, index*2+2, target);

        segTree[index] = nums[segTree[index*2+1]] <= nums[segTree[index*2+2]] ? segTree[index*2+1] : segTree[index*2+2];
    }

    private int searchTree(int[] segTree, int[] nums, int left, int right, int index, int targetLeft, int targetRight){
        if(left > targetRight || targetLeft > right) return 0;
        else if(targetLeft <= left && right <= targetRight) return segTree[index];

        int leftMin = searchTree(segTree, nums, left, (left + right) / 2, index*2+1, targetLeft, targetRight);
        int rightMin = searchTree(segTree, nums, (left+right)/2+1, right, index*2+2, targetLeft, targetRight);

        return nums[leftMin] <= nums[rightMin] ? leftMin : rightMin;
    }

}
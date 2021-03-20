//https://www.acmicpc.net/problem/7453

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		solve();
	}

	public static void solve() throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		int[] l1 = new int[n];
		int[] l2 = new int[n];
		int[] l3 = new int[n];
		int[] l4 = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			l1[i] = (Integer.parseInt(st.nextToken()));
			l2[i] = (Integer.parseInt(st.nextToken()));
			l3[i] = (Integer.parseInt(st.nextToken()));
			l4[i] = (Integer.parseInt(st.nextToken()));
		}
		
		int[] sum1 = new int[n * n];
		int[] sum2 = new int[n * n];
		
		int index = 0;
		for(int i = 0; i < n; i++) {
			for(int k = 0; k < n; k++) {
				sum1[index] = (l1[i] + l2[k]);
				sum2[index++] = (l3[i] + l4[k]);
			}
		}
		
		Arrays.sort(sum1);
		Arrays.sort(sum2);
		
		int left = 0; 
		int right = sum2.length - 1;
		long ans = 0;
		
		while(left < sum1.length && right >= 0) {
			int sum = sum1[left] + sum2[right];
			if(sum < 0) left++;
			else if(sum > 0) right--;
			else if(sum == 0) {
				int l = 0, r = 0;
				int tm = sum1[left];
				int tm2 = sum2[right];
				while(left < sum1.length && tm == sum1[left]) {
					left++;
					l++;
				}
				while(right >= 0 && tm2 == sum2[(right)]) {
					r++;
					right -= 1;
				}
				ans += (long)l * (long)r;
			}
		}
		System.out.println(ans);
	}
}

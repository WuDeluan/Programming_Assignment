package programming1.Percolation;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) // test client (described below)
	{
		int n,trials;
		PercolationStats Stats;
		
		System.out.println("Please enter n and trails:");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		trials = in.nextInt();
		
		Stats = new PercolationStats(n,trials);
		Stats.Percolations();
	}
}

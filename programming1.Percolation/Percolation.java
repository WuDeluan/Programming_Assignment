package programming1.Percolation;

public class Percolation {
	static int open = 1;
	public int[][] sites;
	public int count;
	public int N;
	WeightedQuickUnion uf;
	PercolationVisualizer visual;

	public Percolation(int n) // create n-by-n grid, with all sites blocked
	{
		this.count = 0;
		this.N = n;

		sites = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				sites[i][j] = 0;

		uf = new WeightedQuickUnion(N * N + 2);
		for (int i = 1; i <= N; i++) {
			uf.union(i - 1, N * N);
			uf.union((N - 1) * N + (i - 1), N * N + 1);
		}
	}

	public void open(int row, int col) // open site (row, col) if it is not open already
	{
		try {

			sites[row][col] = open;
			this.count++;

			if (isOpen(row - 1, col))
				uf.union((row - 1) * N + (col - 1), (row - 2) * N + (col - 1));

			if (isOpen(row + 1, col))
				uf.union((row - 1) * N + (col - 1), row * N + (col - 1));

			if (isOpen(row, col - 1))
				uf.union((row - 1) * N + (col - 1), (row - 1) * N + (col - 2));

			if (isOpen(row, col + 1))
				uf.union((row - 1) * N + (col - 1), (row - 1) * N + col);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public boolean isOpen(int row, int col) // is site (row, col) open?
	{
		try {
			if (row > 0 && row <= N && col > 0 && col <= N)
				if (sites[row][col] == open)
					return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isFull(int row, int col) // is site (row, col) full?
	{
		try {
			if (isOpen(row, col) && uf.connected(N * N, (row - 1) * N + (col - 1)))
				return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int numberOfOpenSites() // number of open sites
	{
		return count;
	}

	public boolean percolates() // does the system percolate?
	{
		if (uf.connected(N * N, N * N + 1))
			return true;
		else
			return false;
	}
}

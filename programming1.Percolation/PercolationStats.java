package programming1.Percolation;

public class PercolationStats {
	public static int[] x;
	public static int n;
	public static int trials;
	
	public PercolationStats(int n, int trials) // perform trials independent experiments on an n-by-n grid
	{
		this.n = n;
		this.trials = trials;
		x = new int[n];
	}

	public static double mean() // sample mean of percolation threshold
	{
		double sum = 0;
		for(int i = 0;i < n;i++)
				sum += x[i];
		return 1.0 * sum / n;
	}

	public static double stddev() // sample standard deviation of percolation threshold
	{
		double mean = mean();
		double sum = 0;
		for(int i = 0;i < n;i++)
			sum += Math.pow(x[i] - mean, 2);
		return 1.0 * sum / (n - 1);
	}

	public static double confidenceLo() // low endpoint of 95% confidence interval
	{
		double s = stddev();
		double mean = mean();
		
		return mean - (1.96 * Math.sqrt(s) / Math.sqrt(n));
	}

	public static double confidenceHi() // high endpoint of 95% confidence interval
	{
		double s = stddev();
		double mean = mean();
		
		return mean + (1.96 * Math.sqrt(s) / Math.sqrt(n));
	}
	
	public static void Percolations()
	{
		PercolationVisualizer PercVisual = new PercolationVisualizer();
		
		for(int i = 0;i < n;i++)
		{
			x[i] = PercVisual.PercolateVisual(trials);
		}
		
		System.out.println(mean());
		System.out.println(stddev());
		System.out.println(confidenceLo());
		System.out.println(confidenceHi());
	}
}

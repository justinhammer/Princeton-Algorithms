import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author justinhammer
 */


public class PercolationStats {
    
    private double[] testResults;
    
    
    /**
     * @description Perform trials independent experiments on an n-by-n grid
     * @param n Grid size
     * @param trials Number of trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must both  be> 0.");
        }
        testResults = new double[trials];
        for (int i = 0; i < trials; i++) {
                testResults[i] = runTests(n);
            }
    }
    
    
    /**
     * @param numberOfTestsToRun How many simulations should be run.
     * @return double
     * @description Runs numberOfTestsToRun times to estimate the percolation threshold via a Monte Carlo simulation.
     */
    private double runTests(int numberOfTestsToRun) {
        Percolation newPercolation = new Percolation(numberOfTestsToRun);
        double testCount = 0;
        while (!newPercolation.percolates()) {
            int row = StdRandom.uniform(numberOfTestsToRun) + 1;
            int col = StdRandom.uniform(numberOfTestsToRun) + 1;
            if (!newPercolation.isOpen(row, col)) {
                newPercolation.open(row, col);
                testCount++;
            }
            
        }
        return testCount / (numberOfTestsToRun * numberOfTestsToRun);
    }
    
    
    /**
     * @return double
     * @description Returns the mean for testResults.
     */
    public double mean() {
        return StdStats.mean(testResults);
    }
    
    
    /**
     * @return double
     * @description Returns the Standard Deviation of testResults.
     */
    public double stddev() {
        return StdStats.stddev(testResults);
    }
    
    
    /**
     * @return double
     * @description Returns low endpoint of 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(testResults.length));
    }
    
    
    /**
     * @return double
     * @description Returns high endpoint of 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(testResults.length));
    }
    
    
    /**
     * @description CLI for PercolationStats that prints out results.
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("Mean                    = " + stats.mean());
        System.out.println("SD                      = " + stats.stddev());
        System.out.println("95% Confidence Interval = " + stats.confidenceLo() +
                           ", " + stats.confidenceHi());
    }
}
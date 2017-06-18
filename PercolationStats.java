//package statement?

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * @author justinhammer
 */


public class PercolationStats {
    
    private double[] testResults
    
    
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
        for(int i : testResults) {
                testResults[i] = runTests(n);
            }
    }
    
    
    /**
     * 
     */
    private double runTests(int numberOfTestsToRun) {
        Percolation newPercolation = new Percolation(numberOfTestsToRun);
        double testCount = 0;
        while (!newPercolation.percolates()) {
            int i = StdRandom.uniform(n) + 1;
            int j = StdRandom.uniform(n) + 1;
            testCount++;
        }
        return testCount / (numberOfTestsToRun^2)
    }
    
    
    /**
     * 
     */
    public double mean() {
        return StdStats.mean(testResults);
    }
    
    
    /**
     * 
     */
    public double stddev() {
        return StdStats.stddev(testResults);
    }
    
    
    /**
     * 
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(testResults.length));
    }
    
    
    /**
     * 
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(testResults.length));
    }
    
    
    /**
     * 
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
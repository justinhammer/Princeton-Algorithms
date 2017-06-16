import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author justinhammer
 */


public class Percolation {
    
    private WeightedQuickUnionUF weightedUnionFind;
    private boolean[] gridSite;
    private int gridSize;

    
    /**
     * Create a n-by-n grid, with all the sites initially blocked.
     * @param n Size of grid = n^2.
     */
    public Percolation(int n) { 
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid input: n must be > 0.");
        }
        // Defining the size of our grid:
        this.gridSize = n; 
        // UF object must be n + 2 to include virtual nodes:
        weightedUnionFind = new WeightedQuickUnionUF((n * n) + 2);
        // Creating n * n grid that includes + 2 virtual nodes:
        gridSite = new Boolean[(n * n) + 2];
    }
    
    
    /**
     * @param row Row coordinate of the grid.
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if x and y coordinates are within a valid range.
     */
    public boolean areCoordinatesForGridValid(int row, int col) {
        return(x < 1 || x > this.gridSize || y < 1 || y > this.gridSize);
    }
    
    
    /**
     * @param row Row coordinate of the grid.
     * @param col Column coordinate of the grid.
     * @return int
     * @description Gives us the index of the grid site while accounting for indices of virtual sites.
     */
    public int getIndexOfGridSite(int row, int col) {
        if (areCoordinatesForGridValid(row, col)) {
            throw new java.lang.IndexOutOfBoundsException()
        }
        return (row - 1) * this.gridSize + col // Virtual nodes have indices 0 and (n^2)+1
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @description This method opens a site on the grid if it is not open already.
     */
    public void open(int row, int col) {
    
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if a site (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
    
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if a site (row, col) is full.
     */
    public boolean isFull(int row, int col) {
    
    }
    
    
    /**
     * @return int
     * @description Returns the number of open sites in the grid.
     */
    public int numberOfOpenSites() {
    
    }
    
    
    /**
     * @return boolean
     * @description Checks if the grid percolates.
     */
    public boolean percolates() { 
    
    }
    
    
    public static void main(String[] args) { // test client (optional)
    
    }   
}
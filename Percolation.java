import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author justinhammer
 */


public class Percolation {
    
    private WeightedQuickUnionUF weightedUnionFind;
    private boolean[] gridSite;
    private int gridSize;
    private int numberOfOpenSites;

    
    /**
     * @description Create an n-by-n grid, with all the sites initially blocked.
     * @param n Size of grid = n^2.
     */
    public Percolation(int n) { 
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid input: n must be > 0.");
        }
        // Defining the size of our grid:
        gridSize = n; 
        // UF object must be n + 2 to include virtual nodes:
        weightedUnionFind = new WeightedQuickUnionUF((n * n) + 2);
        // Creating n * n grid that includes + 2 virtual nodes:
        gridSite = new boolean[(n * n) + 2];
        numberOfOpenSites = 0;
    }
    
    
    /**
     * @param row Row coordinate of the grid.
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if x and y coordinates are within a valid range.
     */
    private boolean areCoordinatesForGridValid(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new java.lang.IndexOutOfBoundsException();
        } else {
            return true;
        }
    }
    
    
    /**
     * @param row Row coordinate of the grid.
     * @param col Column coordinate of the grid.
     * @return int
     * @description Gives us the index of the grid site while accounting for indices of virtual sites.
     */
    private int getIndexOfGridSite(int row, int col) {
        areCoordinatesForGridValid(row, col);
        return (row - 1) * gridSize + col; // Virtual nodes have indices 0 and (n^2)+1
    }
    
    
    /**
     * @param i First index to be connected.
     * @param j Second index to be connected.
     * @description This method connects to grid sites to one another.
     */
    private void connectSites(int i, int j) {
        weightedUnionFind.union(i, j);
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @description This method opens a site on the grid if it is not open already.
     */
    public void open(int row, int col) {
        areCoordinatesForGridValid(row, col);
        int indexOfGridSite = getIndexOfGridSite(row, col);
        if (!isOpen(row, col)) {
            gridSite[indexOfGridSite] = true;
            numberOfOpenSites++;
        }
        if (row == 1) { // Any grid site on the first row should connect to the top virtual site.
            connectSites(indexOfGridSite, 0);
        }
        if (row == gridSize) { // Any grid site on the last row should connect to the bottom virtual site.
            connectSites(indexOfGridSite, (gridSize^2) + 1);
        }
        if (row > 1 && isOpen(row - 1, col)) { // Checking to see if the site above is open.
            connectSites(indexOfGridSite, getIndexOfGridSite(row - 1, col));
        }
        if (row < gridSize && isOpen(row + 1, col)) { // Checking to see if the site below is open.
            connectSites(indexOfGridSite, getIndexOfGridSite(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) { // Checking to see if the site to the left is open.
            connectSites(indexOfGridSite, getIndexOfGridSite(row, col - 1));
        }
        if (col < gridSize && isOpen(row, col + 1)) { // Checking to see if the site to the right is open.
            connectSites(indexOfGridSite, getIndexOfGridSite(row, col + 1));
        }
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if a site (row, col) is open.
     */
    public boolean isOpen(int row, int col) {
        areCoordinatesForGridValid(row, col);
        return gridSite[(getIndexOfGridSite(row, col))];
    }
    
    
    /**
     * @param row Row coordinate of the grid. 
     * @param col Column coordinate of the grid.
     * @return boolean
     * @description Checks if a site (row, col) is full.
     */
    public boolean isFull(int row, int col) {
        areCoordinatesForGridValid(row, col);
        return (!isOpen(row, col));
    }
    
    
    /**
     * @return int
     * @description Returns the number of open sites in the grid.
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
    
    
    /**
     * @return boolean
     * @description Checks if the grid percolates.
     */
    public boolean percolates() { 
        return weightedUnionFind.connected((gridSize^2) + 1, 0);
    }
    
    
    public static void main(String[] args) { // test client (optional)
    
    }   
}
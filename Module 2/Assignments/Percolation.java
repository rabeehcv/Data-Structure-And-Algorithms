/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int openSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); // +2 for virtual top and bottom sites
        openSites = 0;
    }

    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        if (grid[row - 1][col - 1]) {
            return;
        }
        else {
            grid[row - 1][col - 1] = true;
            openSites++;
        }
        int index = (row - 1) * n + col - 1;
        if (row == 1) {
            uf.union(index, n * n); // connect to virtual top site
        }
        if (row == n) {
            uf.union(index, n * n + 1); // connect to virtual bottom site
        }
        if (row > 1 && grid[row - 2][col - 1]) {
            uf.union(index, (row - 2) * n + col - 1);
        }
        if (row < n && grid[row][col - 1]) {
            uf.union(index, row * n + col - 1);
        }
        if (col > 1 && grid[row - 1][col - 2]) {
            uf.union(index, (row - 1) * n + col - 2);
        }
        if (col < n && grid[row - 1][col]) {
            uf.union(index, (row - 1) * n + col);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        return uf.connected((row - 1) * n + col - 1, n * n);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }
   /* public static void main(String[] args) {

    }*/
}

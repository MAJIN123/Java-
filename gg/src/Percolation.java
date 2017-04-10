/**
 * Created by Maloney on 17-4-10.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] OpenState;
    private WeightedQuickUnionUF FirstConnectState;
    private WeightedQuickUnionUF SecondConnectState;
    private int N=0;

    // create n-by-n grid,with all sites blocked;
    public Percolation(int n){
        if(n<=0)
            throw new IllegalArgumentException();
        this.N=n;
        OpenState=new boolean[n][n];
        for(int i=0;i<n;++i)
            for(int j=0;j<n;++j)
                OpenState[i][j]=false;
        FirstConnectState=new WeightedQuickUnionUF((n*n)+2);
        SecondConnectState=new WeightedQuickUnionUF((n*n)+1);
    }

    // open site (row, col) if is not open already
    public    void open(int row, int col){
        if(row<1||col<1||row>N||row>N)
            throw new java.lang.IndexOutOfBoundsException();
        OpenState[row-1][col-1]=true;

        int self=(row-1)*N+col-1;
        int up=self-N;
        int down=self+N;
        int left=self-1;
        int right=self+1;

        if(row==1){         // if first line
            FirstConnectState.union(N*N,self);
            SecondConnectState.union(N*N,self);
        }
        if(row==N)          // if last line
            SecondConnectState.union(N*N,self);
        if(row>1&&isOpen(row-1, col)){
            FirstConnectState.union(self,up);
            SecondConnectState.union(self,up);
        }
        if(row<N&&isOpen(row+1,col)){
            FirstConnectState.union(self,down);
            SecondConnectState.union(self,down);
        }
        if(col<N&&isOpen(row,col+1)){
            FirstConnectState.union(self,right);
            SecondConnectState.union(self,right);
        }
        if(col>1&&isOpen(row,col-1)){
            FirstConnectState.union(self,left);
            SecondConnectState.union(self,left);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row<1||col<1||row>N||row>N)
            throw new java.lang.IndexOutOfBoundsException();
        return OpenState[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<1||col<1||row>N||row>N)
            throw new java.lang.IndexOutOfBoundsException();
        int self=(row-1)*N+col-1;
        return SecondConnectState.connected(self,N*N);
    }

    // number of open sites
    public     int numberOfOpenSites(){
        int count=0;
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(isOpen(i,j))
                    count++;
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return FirstConnectState.connected(N*N,N*N+1);
    }

    // test client
    public static void main(String[] args){
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(3, 3);
        percolation.open(5, 5);
        percolation.open(5, 1);
        percolation.open(4, 3);
        percolation.open(4, 1);
        percolation.open(3, 1);
        percolation.open(2, 2);
        percolation.open(1, 2);
        percolation.open(2, 3);
        percolation.open(4, 4);
        percolation.open(4, 5);

        System.out.println("is (5,1) full?:" + percolation.isFull(5, 1));
        System.out.println("is (5,5) full?:" + percolation.isFull(5, 5));
        System.out.println("is percolation?:" + percolation.percolates());
    }
}

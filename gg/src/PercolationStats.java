/**
 * Created by Maloney on 17-4-10.
 */
import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    private double mean;
    private double stddev;
    private int T;

    //perform trials independent experiment on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<1||trials<1)
            throw new IllegalArgumentException();

        double[] x=new double[trials];
        T=trials;

        for(int a=0;a<trials;++a){
            Percolation percolation=new Percolation(n);
            for(int b=0;b<n*n;b++){
                int i=StdRandom.uniform(n) + 1;
                int j=StdRandom.uniform(n)+1;
                while(percolation.isOpen(i,j)){
                    i=StdRandom.uniform(n)+1;
                    j=StdRandom.uniform(n)+1;
                }
                percolation.open(i,j);
                if(percolation.percolates()){
                    x[a]=(double)b/(n*n);
                    break;
                }
            }//  end for b
        }//  end for a

        for(int c=0;c<trials;++c)
            mean+=x[c];
        mean/=trials;

        for(int d=0;d<trials;++d)
            stddev=(x[d]-mean)*(x[d]-mean);
        stddev/=(trials-1);
        stddev = Math.sqrt(stddev);
    }

    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    // test client (described below)
    public static void main(String[] args){
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats ps = new PercolationStats(N,T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}

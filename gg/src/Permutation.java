import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by Maloney on 17-4-28.
 */
public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> q=new RandomizedQueue<String>();
        int i=Integer.valueOf(args[0]);
        while (!StdIn.isEmpty()){
            String item=StdIn.readString();
            q.enqueue(item);
        }
        for(String s:q){
            if(i<1)
                break;
            StdOut.println(s);
            i--;
        }
    }
}

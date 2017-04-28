/**
 * Created by Maloney on 17-4-28.
 */
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        q = (Item[]) new Object[1];
        N = 0;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return N;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; ++i)
            temp[i] = q[i];
        q = temp;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new NullPointerException();
        if (N == q.length)
            resize(q.length * 2);
        q[N++] = item;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int target = StdRandom.uniform(0, N);
        Item tmp = q[target];
        q[target] = q[N - 1];
        q[N - 1] = null;
        if (N == q.length / 4)
            resize(q.length / 2);
        return tmp;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int target = StdRandom.uniform(0, N);
        return (q[target]);
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ArryIterator();

    }

    private class ArryIterator implements Iterator<Item> {
        private int i;
        private Item[] tmp;

        ArryIterator() {
            i = N;
            tmp = (Item[]) new Object[q.length];
            for (int j = 0; j < N; ++j)
                tmp[j] = q[j];
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int target = StdRandom.uniform(0, i);
            Item result = tmp[target];
            tmp[target] = tmp[i - 1];
            tmp[i - 1] = null;
            i--;
            return result;
        }

    }
//    public static void main(String[] args)   // unit testing (optional)
}

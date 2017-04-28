/**
 * Created by Maloney on 17-4-28.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque()                           // construct an empty deque
    {
        first = last = null;
        N = 0;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return N == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return N;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new NullPointerException();
        Node oldNode = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        if (isEmpty()) {
            last = first;
            first.next = null;
        } else {
            first.next = oldNode;
            oldNode.previous = first;
        }
        N++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new NullPointerException();
        Node oldNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
            last.previous = null;
        } else {
            last.previous = oldNode;
            oldNode.next = last;
        }
        N++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item result = first.item;
        first = first.next;
        N--;
        if (isEmpty())
            first = last = null;
        else {
            first.previous = null;
        }
        return result;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item result = last.item;
        last = last.previous;
        N--;
        if (isEmpty())
            first = last = null;
        else {
            last.next = null;
        }
        return result;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node node) {
            current = node;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
//    public static void main(String[] args)   // unit testing (optional)
}



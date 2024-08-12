import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;
    private int n;

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Argument cannot be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst == null) last = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Argument cannot be null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        }
        else {
            Node current = first;
            while (current.next != last) {
                current = current.next;
            }
            last = current;
            last.next = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Deque is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        deque.addFirst(50);
        deque.addLast(60);
        deque.addFirst(40);
        System.out.print("Deque contents via iterator: ");
        for (int item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();
        Iterator<Integer> iterator = deque.iterator();
        try {
            iterator.remove();
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Caught exception when removing via iterator: " + e.getMessage());
        }
    }
}

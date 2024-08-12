import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Argument cannot be null");
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int index = StdRandom.uniform(n);
        Item item = queue[index];
        queue[index] = queue[--n];
        queue[n] = null;
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int index = StdRandom.uniform(n);
        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] shuffledIndices;
        private int current;

        public RandomizedQueueIterator() {
            shuffledIndices = new int[n];
            for (int i = 0; i < n; i++) {
                shuffledIndices[i] = i;
            }
            StdRandom.shuffle(shuffledIndices);
            current = 0;
        }

        public boolean hasNext() {
            return current < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Queue is empty");
            return queue[shuffledIndices[current++]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println("RandomizedQueue created. Is empty? " + rq.isEmpty());
        rq.enqueue(10);
        rq.enqueue(20);
        rq.enqueue(30);
        rq.enqueue(40);
        System.out.println("Items enqueued. Queue size: " + rq.size());
        System.out.println("Sampled item: " + rq.sample());
        System.out.println("Dequeued item: " + rq.dequeue());
        System.out.println("Dequeued item: " + rq.dequeue());
        System.out.println("Queue size after dequeuing: " + rq.size());
        System.out.println("Is empty? " + rq.isEmpty());
        System.out.println("Remaining items in random order:");
        for (int item : rq) {
            System.out.println(item);
        }
        System.out.println("Dequeued item: " + rq.dequeue());
        System.out.println("Dequeued item: " + rq.dequeue());
        System.out.println("Final check, is empty? " + rq.isEmpty());
    }
}

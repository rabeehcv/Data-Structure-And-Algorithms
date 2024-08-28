### Quicksort

Java sort for primitive types.

- Shuffle the array.Partition so that, for some j.
- entry a[j] is in place
- no larger entry to the left of j
- no smaller entry to the right of j
- Sort each piece recursively

#### Java code for partitioning

```
private static int partition (Comparable[], int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    while (true) {
        while (less(a[++i],a[lo])) {
            if (i==hi) {
                break;
            }
        }
        while (less(a[lo],a[--j])) {
            if (j==lo) {
                break;
            }
        }
        if (i>=j) {
            break;
        }
        exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
}
```

#### Quicksort

```
public class Quicksort {
    private static int partition (Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i==hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j==lo) {
                    break;
                }
            }
            if (i>=j) {
                break;
            }
            exch(a,i,j);
        }
        exch(a, lo, j);
        return j;
    }
    private static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
}
```

### Selection

Goal: Given an array of N items, find a kth smallest item.

#### Quick-Select

```
public static Comparable select(Comparable[] a, int k) {
    StdRandom.shuffle(a);
    int lo = 0;
    int hi = a.length - 1;
    while (hi > lo) {
        int j = partition(a, lo, hi);
        if (j > k ) hi = j - 1;
        else if (j  < k ) lo = j + 1;
        else return a[k];
    }
    return a[k];
}
```

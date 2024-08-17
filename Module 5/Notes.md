## MergeSort

Divide array into two halves. Recursively sort each half. Merge two halves.

#### Merging

```
private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    \\ copy org array into another array
    for (int k=0; k<=hi; k++){
        aux[k] = a[k];
    }

    \\merge two sorted sub arrays into the original array
    int i = lo, j = mid+1;
    for(k=0; k<=hi; k++){
        if(i>mid){
            a[k] = aux[j++];
        }
        else if(j>hi){
            a[k] = aux[i++];
        }
        else if(less(aux[j],aux[i])){
            a[k] = aux[j++];
        }
        else{
            a[k] = aux[i++];
        }
    }
    assert isSorted(a,lo,hi);
}
```

In Java, assert is a keyword used to create assertions, which are a way to test assumptions made by the programmer during code execution. An assertion is a statement that checks whether a condition is true. If the condition is false, an AssertionError is thrown, which can help identify bugs during development.

```
int x = 5;
assert x > 0 : "x should be positive";
```

In this example, the assertion checks that x is greater than 0. If x were negative or zero, the program would throw an AssertionError with the message "x should be positive."

Can enable or disable at runtime.

```
java -ea MyProgram // enable assertions
java -da MyProgram // disable assertions (default)
```

#### MergeSort

```
public class Merge{
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    for (int k=0; k<=hi; k++){
        aux[k] = a[k];
    }

    int i = lo, j = mid+1;
    for(k=0; k<=hi; k++){
        if(i>mid){
            a[k] = aux[j++];
        }
        else if(j>hi){
            a[k] = aux[i++];
        }
        else if(less(aux[j],aux[i])){
            a[k] = aux[j++];
        }
        else{
            a[k] = aux[i++];
        }
    }
    assert isSorted(a,lo,hi);
}
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    private static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }
}
```
#### Bottom up merge sort
```
public class BottomUpSort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
    //as coded before
    }
    public static void sort(Comparable[] a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) 
            for (int lo = 0; lo = N - sz; lo += sz+sz){
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1);
            }
    }
}
```

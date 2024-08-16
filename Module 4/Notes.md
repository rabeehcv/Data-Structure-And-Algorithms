## Sort

Rearrange array of N items into ascending order

#### Example 1

Sort random real numbers in ascending order.

```
public class Exp {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];
        for (int i=0; i<N; i++){
            a[i] = StdRandom.uniform();
        }
        Insertion.sort(a);
        for(int i=0; i<N; i++){
            StdOut.println(a[i]);
        }
    }
}
```

#### Example 2

Sort strings from file in alphabetical order

```
Public class StringSorter{
    public static void main(String[] args){
        String[] a = In.readStrings(args[0]);
        Insertion.sort(a);
        for(int i=0; i<a.length; i++){
            StdOut.println(a[i]);
        }
    }
}
```

#### Example 3

Sort the files in a given directory by filename.

```
public class FileSorter{
    public static void main(String[] args){
        File directory = new File(args[0]);
        File[] files = directoy.listFiles();
        Insertion.sort(files);
        for(int i=0; i<files.length; i++){
            StdOut.println(files[i].getName());
        }
    }
}
```

### Comparable API

Implement compareTo() so that v.compareTo(w):
Returns a negative integer, zero, or positive integer
if v is less than, equal to, or greater than w, respectively

#### Example 4

Implementing the Comparable interface

```
public class Date implements Comparable<Date>{
    private final int day, month, year;
    public Date(int m, int d, int y){
        day = d;
        month = m;
        year = y;
    }
    public int compareTo(Date that){
        if (this.year < that.year ) return -1;
        if (this.year > that.year ) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day ) return -1;
        if (this.day > that.day ) return +1;
        return 0;
    }
}
```

#### Two useful sorting abstractions

Less method

```
private static boolean less(Comparable v, Comparable w){
    return v.compareTo(w) < 0;
}
```

Exchange method

```
private static void exch(Comparable[] a,int i,int j){
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
}
```

#### Example 5

Test if an array is sorted.

```
private static boolean isSorted(Comparable[] a){
    for(int i=1; i<a.length; i++){
        if(less(a[i], a[i-1])){
            return false;
        }
        return true;
    }
}
```
### Selection Sort
```
public class SelectionSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i=0; i<N; i++){
            int min = i;
            for (int j=i+1; j<N; j++){
                if(less(a[j], a[min]) {
                    min = j;
                }
            exch(a, i, min);
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){
    return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a,int i,int j){
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
    }
}
```
### Insertion Sort
```
public class InsertionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i=0; i<N; i++) {
            for (int j=i; j>0; j--) {
                if(less(a[j],a[j-1]) {
                    exch(a, j, j-1);
                }
                else break;
            }
        }
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
```
### Shuffle sort
Knuth Shuffle
```
public class StdRandom {
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i=0; i<N; i++) {
            int r = StdRandom.uniform(i+1);
            exch(a, i, r);
        }
    }
}
```


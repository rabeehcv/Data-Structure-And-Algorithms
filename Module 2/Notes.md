## Union Find

### Dynamic connectivity

Given a set of N objects.

-Union command: connect two objects.

-Find/connected query: is there a path connecting the two objects? Or, Check if two objects are in the same component.

-Connected components: Maximal set of objects that are mutually
connected.

### Union-find data type (API)

Goal

. Design efficient data structure for union-find.

・Number of objects N can be huge.

・Number of operations M can be huge.

・Find queries and union commands may be intermixed.

```
public class UF

    UF(int N)                 -  initialise union find data
                                 structure with N objects.
    void union(int p, int q)  -  add connection between p and q.

    boolean connected(int p,int q) - check if p and q are in same component

    int find(int p) - component identifier for p(0 to N-1)

    int count() - no of components
```

#### Dynamic-connectivity client

Read in number of objects N from standard input.

Repeat:
– read in pair of integers from standard input
– if they are not yet connected, connect them and print out pair

```
public static void main(String[].args){
    int N = StdIn.readInt();
    UF uf = new UF(N);
    while (!StdIn.isEmpty()){
        int p = StdIn.readInt();
        int q = StdIn.readInt();
        if(!uf.connected(p,q)){
            uf.union(p,q);
            StdOut.println(p+""+q);
        }
    }
}
```

### Quick Find

Data structure.

・Integer array id[] of length N.

・Interpretation: p and q are connected iff they have the same id.

```
public class QuickFindUF{
    private int[] id;
    public QuickFindUF(int N){
        id = new int[N];
        for (int i=0; i<N; i++){
            id[i] = i;
        }
    }
    public boolean connected(int p, int q){
        return id[p] == id[q];
    }
    public void union(int p, int q){
        int pid=id[p];
        int qid=id[q];
        for(int i=0; i<id.length; i++ ){
            if(id[i]==pid){
                pid=qid;
            }
        }
    }
}
```

Quick-find is too slow. Union is too expensive. It takes N 2 array accesses to process a sequence of
N union commands on N objects.

Quadratic algorithms don't scale with technology. New computer may be 10x as fast. But, has 10x as much memory ⇒
want to solve a problem that is 10x as big. With quadratic algorithm, takes 10x as long!

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
want to solve a problem that is 10x as big. git With quadratic algorithm, takes 10x as long!

### Quick Union

Data structure.

・Integer array id[] of length N.

・Interpretation: id[i] is parent of i.

・Root of i is id[id[id[...id[i]...]]].

Find: Check if p and q have the same root.

Union: To merge components containing p and q,
set the id of p's root to the id of q's root.

```
public class QuickUnionUF{
    private int id[];
    public QuickUnionUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++){
            id[i]=i;                // set id of each object to itself
        }
    }
    private int root(int N){
        while(i != id[i]) i = id[i] ;  // finding the root of the object
        return i;
    }
    public boolean connected(int p, int q){
        return root(p) == root(q); // to check if p and q have same root
    }
    public union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;      // changing root of p to root of q
    }
}
```

Quick-union is also too slow

quick-find: initialize - N, Union - N, find - 1

quick-union: initialize - N, Union - N~, find - N // ~ includes cost of finding roots

##### Quick-find defect:

・Union too expensive (N array accesses).

・Trees are flat, but too expensive to keep them flat.

##### Quick-union defect:

・Trees can get tall.

・Find too expensive (could be N array accesses).

### Quick Union Improvement

#### Weighted Quick Union - improvement 1

- Modify quick-union to avoid tall trees.
- Keep track of size of each tree (number of objects).
- Balance by linking root of smaller tree to root of larger tree.

Data structure is same as quick-union, but maintain extra array sz[i]
to count number of objects in the tree rooted at i.

Find: Identical to quick-union.

```
return root(p) == root(q);
```

Union: Modify quick-union to:

- Link root of smaller tree to root of larger tree.
- Update the sz[] array.

```
int i = root(p);
int j = root(q);
if (i==j) return;
if (sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i]; }
else               {id[j] = i; sz[i] += sz[j]; }
```

Proposition: Depth of any node x is at most lg N.

weighted QU: Initialise - N, Union - lg N †, Connected - lg N
// † includes cost of finding roots

#### Quick union with path compression - improvement 2

Just after computing the root of p,
set the id of each examined node to point to that root.

Two-pass implementation: add second loop to root() to set the id[]
of each examined node to the root.

Simpler one-pass variant: Make every other node in path point to its
grandparent (thereby halving path length)

```
private int root(int i){
    while(i != id[i]){
        id[i] = id[id[i]];
        i = id[i];
    }
    return i;
}
```

### Union-find applications : Percolation

A model for many physical systems:

- N-by-N grid of sites.
- Each site is open with probability p (or blocked with probability 1 – p).
- System percolates iff top and bottom are connected by open sites.

Eg : Electricity, fluid flow, social network, etc

#### To check whether an N-by-N system percolates?

- Create an object for each site and name them 0 to N 2 – 1.
- Sites are in same component if connected by open sites.
- Percolates iff any site on bottom row is connected to site on top row.

Q. How to model opening a new site?

A. Mark new site as open; connect it to all of its adjacent open sites.

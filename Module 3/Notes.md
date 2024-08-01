## Stacks and Queues

- Fundamental data types for storing collections of objects.
- Both of these differ in the way in which the item to be removed is chosen. For the stack, we take out the item that was most recently added.
- Stack - LIFO (Last In First Out) - 'Push' to add new items and 'Pop' to remove recently added item. Examine the item most recently added. Push adds an element to the top of the stack.
  Pop removes an element from the top of the stack.
- Queue - FIFO (First In First Out) - 'Enqueue' to add new item and 'dequeue' to remove element. Enqueue adds an element to the end (rear) of the queue. Dequeue removes an element from the front (head) of the queue

#### Example 1

Read strings from standard input.

・If string equals "-", pop string from stack and print.

・Otherwise, push string onto stack.

```
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Practice {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.pop());
            }
            else stack.push(s);
        }
    }
}
```

#### Stack: LinkedList implementation in Java

```
public class LinkedStackOfString {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
}
```

#### Stack: Array implementation

```
public class FixedCapacityStackOfString {
    private String[] s;
    private int index = 0;

    public FixedCapacityStackOfString(int Capacity) {
        s = new String[Capacity];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public void push(String item) {
        s[index++] = item;
    }

    public String push() {
        String item = s[--index];
        s[index] = null;
        return item;
    }
}
```

#### Queue: LinkedList Implementation

```
public class LinkedList{
    private class Node{
        String item;
        Node next;
    }
    private Node first = null;
    private Node last = null;
    public boolean isEmpty(){
        return first == null;
    }
    public void enqueue(String item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }
        else{
            oldlast.next = last;
        }
    }
    public String dequeue(){
        String item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        return item;
    }
}
```

#### Generic Stack : Linkedlist Implementation

```
public class Stack<Item>{
    public Node first = null;
    public class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }
}
```

#### Arithmetic expression evaluation

```
public class Calculation{
    public static main void(String[] args){
        Stack<String> ops = new Stack<String>();
        Stack<Double> val = new Stack<Double>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("(")) ;
            else if(s.equals("+")) ops.push(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals("-")) ops.push(s);
            else if(s.equals("/")) ops.push(s);
            else if(s.equals(")")) {
                String op = ops.pop();
                if(op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if(op.equals("-")) {
                    lastValue = vals.pop();
                    firstValue = vals.pop();
                    vals.push(firstValue - lastValue);}
                else if(op.equals("*"))   vals.push(vals.pop() * vals.pop());
                else if(op.equals("/")){
                    lastValue = vals.pop();
                    firstValue = vals.pop();
                    vals.push(firstValue / lastValue);}
            }
            else{
                vals.push(Double.parseDoule(s));
            }
        }
        StdOut.println(vals.pop());
    }
}
```

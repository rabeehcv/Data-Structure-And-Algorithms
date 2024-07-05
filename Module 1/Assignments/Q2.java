/*
Write a program HelloGoodbye.java that takes two names as command-line arguments and prints hello and goodbye messages  (with the names for the hello message in the same order as the command-line arguments and with the names for the goodbye message in reverse order).
*/
import edu.princeton.cs.algs4.StdOut;

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 0) {
            StdOut.println("Hello " + args[0] + " " + args[1]);
            StdOut.println("Goodbye " + args[1] + " " + args[0]);
        }
    }
}
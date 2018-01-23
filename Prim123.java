import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Prim123 {
    /**
     * A graph instance where to calculate the MST using Prim's algorithm
     */
    private SimpleWeightedGraph graph;
    /**
     * Key value of each Vertex in the graph
     * Infinite will be represented with Integer.MAX_VALUE = 2^31 + 1
     */
    private int[] keys;
    /**
     * For each Vertex it holds its predecessor
     * To represent null I used: '!'
     */
    private char[] predesr;
    /**
     * The Priority Queue used by the algorithm
    */
    private HashSet<Character> Q;
    /**
     * Gets an instance of the graph
     * Initializes 'keys', 'predecessor', and 'Q'
     */
    public Prim123() throws IOException {
        int m=0;
        graph = SimpleWeightedGraph.getGraphInstance();

        int size = graph.m;
        keys = new int[size];
        predesr = new char[size];
        Q = new HashSet<Character>();

        /**
         * All Vertices are added to Q
         * Every Vertex will start having a key of infinite
         * No predecessors are set yet (all of them are null '!')
         */
        for (char c = 'a'; c < 'a' + size; c++) {
            Q.add(c);
            keys[charToIndex(c)] = Integer.MAX_VALUE;
            predesr[charToIndex(c)] = '!';
        }
    }
        // Given a starting Vertex, it grows a MST from the graph
     // start The Vertex where we start generating Prim's MST
    public void calc(char start)  {

        /*
         * The key of the starting Vertex is set to 0
         */

        keys[charToIndex(start)] = 0;

        /**
         * Till we have at least one Vertex in the queue Q
         */
        while (!Q.isEmpty()) {

            char node = extractMin(Q, keys);

            Set<Character> adjacent = graph.getAdj(node);
            for (Character adj : adjacent) {
                if (Q.contains(adj) && graph.getEdgeWeight(node, adj) < keys[charToIndex(adj)]) {
                    predesr[charToIndex(adj)] = node;
                    keys[charToIndex(adj)] = graph.getEdgeWeight(node, adj);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        Prim123 prim = new Prim123();
        prim.calc('a');
        prim.print();

    }

    /**
     * Print the result of the algorithm
     */
    public void print() throws IOException {
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the Output path :-");
        String output = scanner.nextLine();
        output=output.toString()+"prim.out.txt";
        PrintWriter outputfile = new PrintWriter(output);
        outputfile.println(keys.length-1);
        for (int i = 0; i < keys.length; i++) {
            if (predesr[i] != '!') {
                outputfile.println("("+predesr[i]+","+(char) (i+'a')+")="+keys[i]);
            }
        }
        outputfile.close();
    }

    /**
     * Given an array index it returns the character representing a Vertex
     *idx Index of the array that determines the Vertex (EG 0 = 'a')
     * @return The char that is the Vertex name
     */
    private static char indexToChar(int idx) {
        return (char) (idx + 'a');
    }

    /**
     * Given a char representing a Vertex name it returns its position in an array (EG 3 = 'd')
     *
     * ch The char representing the Vertex name
     * return The array index position of the Vertex
     */
    private static int charToIndex(char ch) {
        return (char) (ch - 'a');
    }

    /**
     * A simulation of a "poll" of a priority queue. It removes and returns the smallest element in Q
     * Q The Set containing Vertices (all of them at the very first iteration of the algorithm)
     * keys The value keys associated with each Vertex
     * @return The Vertex name with the smallest key value
     */
    private Character extractMin(HashSet<Character> Q, int[] keys) {
        Character result = null;
        int currentMin = Integer.MAX_VALUE;

        for (int index = 0; index < keys.length; index++) {
            if (keys[index] < currentMin && Q.contains(indexToChar(index))) {
                currentMin = keys[index];
                result = indexToChar(index);
            }
        }

        if (result != null)
            Q.remove(result);
        return result;
    }
}
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SimpleWeightedGraph {
    Integer[][] data;
    int m;
    public SimpleWeightedGraph(int v) {
        data = new Integer[v][v];
    }

    public void addEdge(char s1, char s2) {
        addEdge(s1, s2, 1);
    }

    public void addEdge(char s1, char s2, int weight) {
        data[charToIndex(s1)][charToIndex(s2)] = weight;
    }

    public int getEdgeWeight(char a, char b) {
        return data[charToIndex(a)][charToIndex(b)];
    }

    public Set<Character> getAdj(char node) {
        HashSet<Character> result = new HashSet<Character>();

        Integer[] adjacent = data[charToIndex(node)];
        for (int i = 0; i < adjacent.length; i++) {
            if (adjacent[i] != null)
                result.add(indexToChar(i));
        }

        return result;
    }

    private static char indexToChar(int i) {
        return (char) (i + 'a');
    }

    private static int charToIndex(char c) {
        return (char) (c - 'a');
    }


    public static SimpleWeightedGraph getGraphInstance() throws IOException {
        int i = 0, j = 0, v = 0, m=0;
        SimpleWeightedGraph graph = new SimpleWeightedGraph(25);
        String[] mainArray = new String[10];
        char[] ver1 = new char[20];
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter the Input path :-");
        String input = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(input));

        String line;
        while ((line = reader.readLine()) != null) {
            mainArray[i] = line;
            if (i == 0) {
                v = Integer.parseInt(mainArray[0]);
                i++;
            } else if (i == 1) {
                String vertices = mainArray[1].replace(",", "");  //remove the commas;;
                ver1 = new char[v];
                ver1 = vertices.toCharArray();
                i++;
            } else if (i == 2) {
                graph.m = Integer.parseInt(mainArray[2]);
                i++;
            } else {
                graph.data[charToIndex(line.charAt(1))][charToIndex(line.charAt(3))] = Integer.parseInt(String.valueOf(line.charAt(6)));

                i++;
            }
        }

        return graph;

    }
}
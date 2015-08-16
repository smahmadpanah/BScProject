package test;
import java.util.LinkedList;

public class Search {

    private static final String START = "2";
    private static final String END = "11";

    public static void main(String[] args) {
        // this graph is directional
        Graph graph = new Graph();
        graph.addEdge("2", "5");
        graph.addEdge("5", "7");
        graph.addEdge("5", "6");
        graph.addEdge("7", "6");
        graph.addEdge("6", "11"); // this is the only one-way connection

        LinkedList<String> visited = new LinkedList();
        visited.add(START);
        new Search().breadthFirst(graph, visited);
    }

    private void breadthFirst(Graph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        // in breadth-first, recursion needs to come after visiting adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            breadthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }
}
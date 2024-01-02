public class TestCases {
    public static void main(String args[]){
        //edge case 1: graph with one vertex
        BFS.Graph graph1 = new BFS.Graph(1);
        int source1 = 0;
        int[] distance1 = new int[1];
        int[] color1 = new int[1];
        BFS.BFS(graph1, source1, distance1, color1);
        System.out.println("This is the result of edge case 1.");
        System.out.println("Distance from source vertex " + source1 + " to vertex 0 is " + distance1[0]);
        System.out.println(" ");

        //edge case 2: a fully connected graph
        BFS.Graph graph = new BFS.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        int source = 0;
        int[] distance = new int[5];
        int[] color = new int[5];
        BFS.BFS(graph, source, distance, color);
        System.out.println("This is the result of edge case 2.");
        for (int i = 1; i < graph.V; i++) {
            System.out.println("Distance from source vertex " + source + " to vertex "+ i + " is " + distance[i]);
        }
        System.out.println(" ");

        //general case: an undirected graph with disconnected components
        BFS.Graph graph3 = new BFS.Graph(7);
        graph3.addEdge(0, 1);
        graph3.addEdge(0, 2);
        graph3.addEdge(1, 3);
        graph3.addEdge(2, 3);
        graph3.addEdge(4, 5);
        graph3.addEdge(5, 6);
        int source3 = 0;
        int[] distance3 = new int[7];
        int[] color3 = new int[7];
        BFS.BFS(graph3, source3, distance3, color3);
        System.out.println("This is the result of the general case.");
        for (int i = 1; i < distance3.length; i++){
            System.out.println("Distance from source vertex " + source3 + " to vertex "+ i + " is " + distance3[i]);
        }

    }
}


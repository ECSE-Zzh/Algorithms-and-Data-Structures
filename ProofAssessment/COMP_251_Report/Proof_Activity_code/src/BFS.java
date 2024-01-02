import java.util.*;

public class BFS {

    //initialize a graph
    static class Graph {
        int V;
        List<Integer>[] adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        void addEdge(int u, int v) {
            adjList[u].add(v);
            adjList[v].add(u);
        }
    }

    static void BFS(Graph graph, int source, int[] distance, int[] color) {
        //this piece of code is from Cormen, Leiserson, Rivest, and Stein, Introduction to Algorithms
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.V];
        Arrays.fill(distance, -1);
        Arrays.fill(color, 0);
        visited[source] = true;
        distance[source] = 0;
        color[source] = 1; // 1 represents gray
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.adjList[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    distance[v] = distance[u] + 1;
                    color[v] = 1; // 1 represents gray
                    queue.offer(v);
                }
            }
            color[u] = 2; // 2 represents black
        }
    }


    public static void main(String[] args) {
        /*check if the graph satisfies the condition :
         1. directed or undirected
         2. run on G from a given source vertex s∈V
         3. value v.d computed by BFS satisfies v.d ≥ δ(s, v)
         */
        int V = 10; // number of vertices in the graph
        double p = 0.3; // probability of edge creation
        Graph graph = new Graph(V);
        Random rand = new Random();
        for (int u = 0; u < V; u++) {
            for (int v = u + 1; v < V; v++) {
                if (rand.nextDouble() < p) {
                    graph.addEdge(u, v);
                }
            }
        }
        int source = rand.nextInt(V); // randomly choose source vertex
        int[] distance = new int[V];
        int[] color = new int[V];
        BFS(graph, source, distance, color);
        System.out.printf("BFS distances from source vertex %d:\n", source);
        for (int v = 0; v < V; v++) {
            System.out.printf("Vertex %d: distance %d, color %d\n", v, distance[v], color[v]);
        }

    }
}

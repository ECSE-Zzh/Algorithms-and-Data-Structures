import java.util.*;
import java.io.File;

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();
        /* YOUR CODE GOES HERE*/
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> deadEnd = new HashSet<>();
        Stack<Integer> nodeStack = new Stack<>();
        ArrayList<Integer> neighbour = new ArrayList<>();

        if (!nodeStack.contains(source)){
            nodeStack.push(source);
        }

        while(!nodeStack.isEmpty()){
            int curNode = nodeStack.pop();
            path.add(curNode);
            visited.add(curNode);
            if(curNode == destination){
                nodeStack.clear();
                break;
            }
            //get all neighbours of curNode
            ArrayList<Edge> myEdge = graph.getEdges();
            for (int i = 0; i < myEdge.size(); i++){
                if(curNode == myEdge.get(i).nodes[0] && !deadEnd.contains(myEdge.get(i).nodes[1]) && !visited.contains(myEdge.get(i).nodes[1]) && myEdge.get(i).weight > 0){
                    neighbour.add(myEdge.get(i).nodes[1]);
                }
            }
            //no neighbour, no path
            if (neighbour.isEmpty() && curNode != source) {
                path.clear();
                nodeStack.clear();
                nodeStack.push(source);
                visited.clear();
                deadEnd.add(curNode);
            }else{
                //push neighbour on stack and set as visited
                for (int i = 0; i < neighbour.size(); i++){
                    nodeStack.push(neighbour.get(i));
            }
                neighbour.clear();
            }
        }
        if(path.size()==1 && path.get(0).equals(source)){
            path.clear();
        }
        return path;
    }

    public static String fordfulkerson(WGraph graph){
        String answer="";
        int maxFlow = 0;
        /* YOUR CODE GOES HERE*/
        //make a residual graph: copy from original
        WGraph residualGraph = new WGraph(graph);
        //能在residual里找到路的时候:
        ArrayList<Integer> residualPath = pathDFS(residualGraph.getSource(), residualGraph.getDestination(), residualGraph);
        while(!residualPath.isEmpty()){
            //找到当前路径的bottleneck
            int bottleneck = Integer.MAX_VALUE;
            for(int i = 0; i < residualPath.size()-1; i++){
                Edge e = residualGraph.getEdge(residualPath.get(i), residualPath.get(i+1));
                if (e != null && e.weight < bottleneck) {
                    bottleneck = e.weight;
                }
            }
            /*
            update residual:
            1. update forward path (path in original graph)
             */
            for(int i = 0; i < residualPath.size()-1; i++){
                Edge forward = residualGraph.getEdge(residualPath.get(i), residualPath.get(i+1));
                if(forward != null){
                    int newWeight = forward.weight - bottleneck;
                    residualGraph.setEdge(residualPath.get(i), residualPath.get(i+1), newWeight);
                }
            /*
            2. add backward path
            */
                Edge originalE = residualGraph.getEdge(residualPath.get(i+1), residualPath.get(i));
                if (originalE != null){
                    residualGraph.setEdge(residualPath.get(i+1), residualPath.get(i), originalE.weight+bottleneck);
                }else {
                    Edge backward = new Edge(residualPath.get(i+1), residualPath.get(i), 0);
                    residualGraph.addEdge(backward);
                    backward.weight += bottleneck;
                    residualGraph.setEdge(residualPath.get(i+1), residualPath.get(i), backward.weight);
                }

            }
            maxFlow += bottleneck;
            residualPath = pathDFS(residualGraph.getSource(),residualGraph.getDestination(), residualGraph);
        }

        //copy path
        for (Edge originalEdge: graph.getEdges()){
            for(Edge residualEdge: residualGraph.getEdges()){
                if (originalEdge.nodes[0] == residualEdge.nodes[0] && originalEdge.nodes[1] == residualEdge.nodes[1]){
                    graph.setEdge(originalEdge.nodes[0], originalEdge.nodes[1], originalEdge.weight-residualEdge.weight);
                }
            }
        }
        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }

    public static void main(String[] args){
        String file = args[0];
        File f = new File(file);
        WGraph g = new WGraph(file);
        System.out.println(fordfulkerson(g));
    }
}


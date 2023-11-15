import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addWithWeight(1, 2, 1, true);
        graph.addWithWeight(1, 4, 4, true);
        graph.addWithWeight(1, 5, 2, true);
        graph.addWithWeight(2, 4, 2, true);
        graph.addWithWeight(2, 5, 5, true);
        graph.addWithWeight(4, 5, 6, true);
        //graph.addVertex(8);
        System.out.println(graph);
        System.out.println("edges number: " + graph.numEdges());
        System.out.println("is fully connected: " + graph.isFullyConnected());
        System.out.println("max order: " + Arrays.toString(graph.maxOrder()));
        System.out.println("min order: " + Arrays.toString(graph.minOrder()));
        System.out.println("is connected: " + graph.isConnected());
        int n = graph.vertexes().length - 1;
        for (Graph<Integer> clique : graph.getCliques(n)) {
            System.out.println("clique: " + clique);
        }
        HashMap[] h = graph.calcDist(1);
        HashMap dist = h[0];
        HashMap prev = h[1];
        for (Object t : graph.vertexes()) {
            System.out.println("n=" + t + " d=" + dist.get(t) + " p=" + prev.get(t));
        }
        Gson gson = new Gson();
        JsonGraph<Integer> jsonGraph = new JsonGraph<>(graph);
        System.out.println(gson.toJson(jsonGraph));
    }
}
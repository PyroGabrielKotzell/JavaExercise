import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // new graph
        Graph<Integer> graph = new Graph<>();

        // add with weight
        graph.addWithWeight(1, 2, 1, true);
        graph.addWithWeight(1, 4, 4, true);
        graph.addWithWeight(1, 5, 2, true);
        graph.addWithWeight(2, 4, 2, true);
        graph.addWithWeight(2, 5, 5, true);
        graph.addWithWeight(4, 5, 6, true);
        graph.addWithWeight(5, 7, 2, false);
        graph.addWithWeight(7, 8, 2, false);
        graph.addWithWeight(8, 7, -3, false);

        //graph.addVertex(8);

        // out of the graph
        System.out.println(graph);

        // the nnumber of edges
        System.out.println("links number: " + graph.numEdges());

        // is fully connected
        System.out.println("is fully connected: " + graph.isFullyConnected());

        // vertex with more edges/links
        System.out.println("max order: " + Arrays.toString(graph.maxOrder()));

        // vertex with fewer edges/links
        System.out.println("min order: " + Arrays.toString(graph.minOrder()));

        // is connected
        System.out.println("is connected: " + graph.isConnected());

        // minimum length the clique needs to have to be returned
        int n = graph.vertexes().length - 1;

        // out of the cliques
        for (Graph<Integer> clique : graph.getCliques(n)) {
            System.out.println("clique: " + clique);
        }

        // hashmaps with distance and previouses of the vertexes by a vertex of source
        HashMap[] h = graph.BellmanFord(1);
        HashMap dist = h[0];
        HashMap prev = h[1];

        // out of the distance and previouses
        for (Object t : graph.vertexes()) {
            System.out.println("n=" + t + " d=" + dist.get(t) + " p=" + prev.get(t));
        }

        // instance of a gson
        Gson gson = new Gson();

        // class to translate a graph to a json graph
        JsonGraph<Integer> jsonGraph = new JsonGraph<>(graph);

        // out of the json graph using the gson toJson method
        GestoreFiles g = new GestoreFiles("./graph.json");
        g.createFile();
        g.open();
        String s = gson.toJson(jsonGraph).replaceAll("},", "},\n").replaceAll("],", "],\n");
        s = s.replaceAll("\\[", "[\n");
        g.write(s);
        g.close();
    }
}
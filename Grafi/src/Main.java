import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // new graph
        Graph<Integer> graph = new Graph<>();

        // add vertexes automatically
        int exponent = 11;
        for (int i = 0; i < exponent*3; i++) {
            int w = (int) (Math.random()*2) < 1 ? -1 : 1;
            w = 1;
            graph.addWithWeight((int) (Math.random()*exponent), (int) (Math.random()*exponent),  (int)(Math.random()*exponent)*w, false);
        }

        //graph.addVertex(8);

        // out of the graph
        System.out.println(graph);

        // the nnumber of edges
        System.out.println("links number: " + graph.getNumEdges());

        // is fully connected
        System.out.println("is fully connected: " + graph.isFullyConnected());

        // vertex with more edges/links
        System.out.println("max order: " + Arrays.toString(graph.maxOrder()));

        // vertex with fewer edges/links
        System.out.println("min order: " + Arrays.toString(graph.minOrder()));

        // is connected
        System.out.println("is connected: " + graph.isConnected());

        // minimum length the clique needs to have to be returned
        int cliqMinLen = graph.getVertexes().length - 1;

        // out of the cliques
        /*for (Graph<Integer> clique : graph.getCliques(cliqMinLen)) {
            System.out.println("clique: " + clique);
        }*/

        // hashmaps with distance and previouses of the vertexes by a vertex of source
        HashMap[] h = graph.bellmanFord((Integer) graph.getVertexes()[0]);
        HashMap dist = h[0];
        HashMap prev = h[1];

        // out of the distance and previouses
        for (Object t : graph.getVertexes()) {
            System.out.println("n=" + t + " d=" + dist.get(t) + " p=" + prev.get(t));
        }

        // instance of a gson
        Gson gson = new Gson();

        // class to translate a graph to a json graph
        JsonGraph<Integer> jsonGraph = new JsonGraph<>(graph);

        graph.kruskal();

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
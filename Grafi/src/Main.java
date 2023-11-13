import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addWithWeight(1, 2, 1, true);
        g.addWithWeight(1, 4, 4, true);
        g.addWithWeight(1, 5, 2, true);
        g.addWithWeight(2, 4, 2, true);
        g.addWithWeight(2, 5, 5, true);
        g.addWithWeight(4, 5, 6, true);
        //g.addVertex(8);
        System.out.println(g);
        System.out.println("is fully connected: " + g.isFullyConnected());
        System.out.println("max order: " + Arrays.toString(g.maxOrder()));
        System.out.println("min order: " + Arrays.toString(g.minOrder()));
        System.out.println("is connected: " + g.isConnected());
        int n = g.vertexes().length - 1;
        for (Graph<Integer> clique : g.getCliques(n)) {
            System.out.println(clique);
        }
        g.calculateWeight(1);
    }
}
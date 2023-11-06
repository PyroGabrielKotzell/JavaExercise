import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.add(1, 2, true);
        g.add(1, 4, true);
        g.add(1, 5, true);
        g.add(2, 4, true);
        g.add(2, 5, true);
        g.add(4, 5, true);
        //g.addVertex(8);
        System.out.println(g);
        System.out.println("is fully connected: " + g.isFullyConnected());
        System.out.println("max order: " + Arrays.toString(g.maxOrder()));
        System.out.println("min order: " + Arrays.toString(g.minOrder()));
        System.out.println("is connected: " + g.isConnected());
        /*for (Graph<Integer> clique:g.searchCliques()) {
            System.out.println(clique);
        }*/
    }
}
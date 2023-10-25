public class Main {
    public static void main(String[] args) {
        Grafo<Integer> g = new Grafo<>();
        g.add(1, 2, true);
        g.add(1, 5, true);
        g.add(1, 4, true);
        g.add(2, 4, true);
        g.add(2, 5, true);
        g.add(4, 5, true);
        System.out.println(g);
        System.out.println("is fully connected: " + g.isFullyConnected());
        System.out.println("max order: " + g.maxOrder());
        System.out.println("min order: " + g.minOrder());
        System.out.println("is connected: " + g.isConnected());
    }
}
public class Main {
    public static void main(String[] args) {
        Grafo<Integer> g = new Grafo<>();
        g.add(1, 2, true);
        g.add(1, 4, false);
        System.out.println(g);
        System.out.println(g.isFullyConnected());
    }
}
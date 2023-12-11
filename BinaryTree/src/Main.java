public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> b = new BinaryTree<>(new Node<>(10, null, null));
        b.add(20, 10, true);
        b.add(30, 20, true);
        b.add(40, 10, false);
        b.add(50, 40, true);
        b.add(60, 40, false);
        b.add(70, 60, false);
        System.out.println(b.print());
        System.out.println(b.contaFoglie());
    }
}
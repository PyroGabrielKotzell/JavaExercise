public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> b = new BinaryTree<>(new Node<>(10, null, null));
        b.add(9, 10, true);
        b.add(8, 9, true);
        b.add(40, 10, false);
        b.add(30, 40, true);
        b.add(60, 40, false);
        b.add(70, 60, false);
        b.remove(70);
        System.out.println(b.print());
        System.out.println(b.cerca(70));
        System.out.println(b.contaFoglie());
        System.out.println(b.cercaMax(b.getRoot().getValue()).getValue());
        System.out.println(b.cercaMin(b.getRoot().getValue()).getValue());
    }
}
public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> b = new BinaryTree<>(new Node<>(1, null, null));
        b.add(2, 1, true);
        b.add(3, 1, false);
        System.out.println(b.print());
    }
}
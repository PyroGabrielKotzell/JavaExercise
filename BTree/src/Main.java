public class Main {
    public static void main(String[] args) {
        BTree<Integer> b = new BTree<>();

        b.add(0, 1);
        b.add(1, 2);
        b.add(2, 5);
        b.add(3, 3);
        b.add(4, 4);
        b.add(5, 6);
        b.add(6, 7);

        System.out.println("get: " + b.get(0));
        System.out.println("\nsearch: " + b.search(5));
        System.out.println("\nleft child: " + b.leftChild(1));
        System.out.println("\nright child: " + b.rightChild(1));
        System.out.println("\npreorder: " + b.preOrder());
    }
}
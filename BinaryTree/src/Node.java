public class Node<E> {
    private final E value;
    private Node<E> right, left;

    Node(E value, Node<E> right, Node<E> left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getRight() {
        return right;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }
}
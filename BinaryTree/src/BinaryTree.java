import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    BinaryTree() {
    }

    BinaryTree(Node<T> root) {
        this.root = root;
    }

    public void add(T value, T node, boolean left) {
        if (getNode(value) == null) {
            Node<T> tmp = getNode(node);
            if (tmp != null) {
                if (left) tmp.setLeft(new Node<>(value, null, null));
                else tmp.setRight(new Node<>(value, null, null));
            }
        }
    }

    private Node<T> getNode(T value){
        if (root != null) {
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                Node<T> tmp = c.getRight();
                if (tmp != null) s.push(tmp);
                tmp = c.getLeft();
                if (tmp != null) s.push(tmp);
                if (c.getValue().equals(value)) return c;
                c = s.peek();
                s.pop();
            }
        }
        return null;
    }

    public String print(){
        if (root != null) {
            String str = "[";
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                Node<T> tmp = c.getRight();
                if (tmp != null) s.push(tmp);
                c = s.peek();
                str += str.contains(c.getValue().toString()) ? "" : c.getValue().toString() + ",";
                s.pop();
                tmp = c.getLeft();
                if (tmp != null) s.push(tmp);

            }
            return str.substring(0, str.length()-1) + "]";
        }
        return "";
    }

    public void clear() {
        if (root != null) {
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                Node<T> tmp = c.getRight();
                if (tmp != null) s.push(tmp);
                tmp = c.getLeft();
                if (tmp != null) s.push(tmp);
                if (c.getLeft() != null) c.setLeft(null);
                if (c.getRight() != null) c.setRight(null);
                c = s.peek();
                s.pop();
            }
            root = new Node<>(null, null, null);
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        clear();
        this.root = root;
    }
}

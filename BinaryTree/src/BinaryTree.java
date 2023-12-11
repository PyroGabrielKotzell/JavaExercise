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

    private Node<T> getNode(T value) {
        if (root != null) {
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                c = s.peek();
                s.pop();
                if (c.getValue().equals(value)) return c;
                if (c.getLeft() != null) s.push(c.getLeft());
                if (c.getRight() != null) s.push(c.getRight());
            }
        }
        return null;
    }

    public boolean cerca(T value) {
        return getNode(value) != null;
    }
    
    public String print() {
        if (root != null) {
            String str = "(", tmp = "";
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                while (c.getLeft() != null && !str.contains(c.getValue().toString())){
                    str += "(";
                    tmp = "";
                    s.push(c.getLeft());
                    c = s.peek();
                }
                c = s.pop();
                str += c.getValue().toString();
                if (c.getRight() != null) {
                    str += "(";
                    tmp += ")";
                    s.push(c.getRight());
                    c = s.peek();
                }else {
                    str += ")" + tmp;
                }
            }
            return str + ")";
        }
        return "";
    }

    public int contaFoglie(){
        if (root == null) return 0;
        int n = 0;
        Stack<Node<T>> s = new Stack<>();
        Node<T> c = root;
        s.add(c);
        while (!s.isEmpty()) {
            boolean f = false;
            c = s.peek();
            s.pop();
            if (c.getLeft() != null) s.push(c.getLeft());
            else f = true;
            if (c.getRight() != null) s.push(c.getRight());
            else if (f) n++;
        }
        return n;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}

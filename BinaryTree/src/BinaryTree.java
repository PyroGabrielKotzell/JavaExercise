import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    BinaryTree() {
    }

    BinaryTree(Node<T> root) {
        this.root = root;
    }

    public void add(T value, T node, boolean left) {
        if (left && value.compareTo(node) > 0 || value.equals(node)) return;
        if (!left && value.compareTo(node) < 0) return;
        if (getNode(value) == null) {
            Node<T> tmp = getNode(node);
            if (tmp != null) {
                if (left) tmp.setLeft(new Node<>(value, null, null));
                else tmp.setRight(new Node<>(value, null, null));
            }
        }
    }

    public void remove(T value) {
        if (root != null) {
            if (value.equals(root.getValue())) {
                root = null;
                return;
            }
            Stack<Node<T>> s = new Stack<>();
            Node<T> c = root;
            s.add(c);
            while (!s.isEmpty()) {
                c = s.peek();
                s.pop();
                // check attachments
                if (value.compareTo(c.getValue()) < 0) {
                    // check left
                    if (c.getLeft() != null) {
                        if (c.getLeft().getValue().equals(value)) c.setLeft(null);
                        else s.push(c.getLeft());
                    }
                } else if (value.compareTo(c.getValue()) > 0) {
                    // check right
                    if (c.getRight() != null) {
                        if (c.getRight().getValue().equals(value)) c.setRight(null);
                        else s.push(c.getRight());
                    }
                }
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
                if (value.compareTo(c.getValue()) < 0) {
                    if (c.getLeft() != null) s.push(c.getLeft());
                } else if (c.getRight() != null) s.push(c.getRight());
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
            while (c != null || !s.isEmpty()) {
                while (c != null) {
                    s.push(c);
                    c = c.getLeft();
                    if (c != null) {
                        str += "(";
                        tmp += ")";
                    }
                }
                c = s.pop();
                str += c.getValue().toString();
                c = c.getRight();
                if (c != null) {
                    str += "(";
                    tmp += ")";
                } else {
                    str += tmp.charAt(0);
                    tmp = tmp.substring(1);
                }
            }
            return str + tmp + ")";
        }
        return "";
    }

    public int contaFoglie() {
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

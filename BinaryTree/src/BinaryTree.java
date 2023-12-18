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
        if (root == null) return;
        if (root.getValue().equals(value)) {
            root = null;
            return;
        }
        Node<T> p = getParent(value);
        Node<T> n = getNode(value);
        if (value.compareTo(root.getValue()) < 0) {
            //right
            if (n.getLeft() != null) {
                Node<T> tmp1 = cercaMax(n.getLeft().getValue()), tmp2 = cercaMax(tmp1.getValue());
                if (!getParent(tmp1.getValue()).getValue().equals(value))
                    tmp2.setRight(getParent(tmp1.getValue()));
                else tmp2.setRight(getParent(tmp1.getValue()).getRight());
                getParent(tmp1.getValue()).setLeft(null);
                p.setLeft(tmp1);
            } else p.setLeft(n.getRight());
        } else {
            //left
            if (n.getRight() != null) {
                Node<T> tmp1 = cercaMin(n.getLeft().getValue()), tmp2 = cercaMin(tmp1.getValue());
                if (!getParent(tmp1.getValue()).getValue().equals(value))
                    tmp2.setLeft(getParent(tmp1.getValue()));
                else tmp2.setLeft(getParent(tmp1.getValue()).getLeft());
                getParent(tmp1.getValue()).setRight(null);
                p.setRight(tmp1);
            } else p.setRight(n.getLeft());
        }
    }

    public Node<T> getNode(T value) {
        if (root == null) return null;
        Stack<Node<T>> s = new Stack<>();
        Node<T> c = root;
        s.add(c);
        while (!s.isEmpty()) {
            c = s.pop();
            if (c.getValue().equals(value)) return c;
            if (value.compareTo(c.getValue()) < 0) {
                if (c.getLeft() != null) s.push(c.getLeft());
            } else if (c.getRight() != null) s.push(c.getRight());
        }
        return null;
    }

    public Node<T> getParent(T value) {
        if (root == null) return null;
        if (root.getValue().equals(value)) return null;
        Stack<Node<T>> s = new Stack<>();
        Node<T> c = root;
        s.add(c);
        while (!s.isEmpty()) {
            c = s.pop();
            if (value.compareTo(c.getValue()) < 0) {
                if (c.getLeft() != null) {
                    if (c.getLeft().getValue().equals(value)) return c;
                    s.push(c.getLeft());
                }
            } else if (c.getRight() != null) {
                if (c.getRight().getValue().equals(value)) return c;
                s.push(c.getRight());
            }
        }
        return null;
    }

    public boolean cerca(T value) {
        return getNode(value) != null;
    }

    public Node<T> cercaMax(T root) {
        if (root == null) return null;
        Stack<Node<T>> s = new Stack<>();
        Node<T> max = getNode(root);
        s.add(max);
        while (!s.isEmpty()) {
            max = s.pop();
            if (max.getRight() != null) s.push(max.getRight());
            else break;
        }
        return max;
    }

    public Node<T> cercaMin(T root) {
        if (root == null) return null;
        Stack<Node<T>> s = new Stack<>();
        Node<T> min = getNode(root);
        s.add(min);
        while (!s.isEmpty()) {
            min = s.pop();
            if (min.getLeft() != null) s.push(min.getLeft());
            else break;
        }
        return min;
    }

    public String print() {
        if (root == null) return "";
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

    public int contaFoglie() {
        if (root == null) return 0;
        int n = 0;
        Stack<Node<T>> s = new Stack<>();
        Node<T> c = root;
        s.add(c);
        while (!s.isEmpty()) {
            boolean f = false;
            c = s.pop();
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

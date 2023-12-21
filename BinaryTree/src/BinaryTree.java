import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    BinaryTree() {
    }

    BinaryTree(Node<T> root) {
        this.root = root;
    }

    public void add(T value, T node, boolean left) {
        // check where you're putting it
        if (left && value.compareTo(node) >= 0) return;
        if (!left && value.compareTo(node) < 0) return;

        // check parent is appropriate
        Node<T> tmp = getParent(node);
        if (tmp != null) {
            int valToP = value.compareTo(tmp.getValue());
            int nodeToP = node.compareTo(tmp.getValue());
            if (valToP > 0 && nodeToP < 0 || valToP < 0 && nodeToP > 0) return;
        }

        // add node
        if (getNode(value) == null) {
            tmp = getNode(node);
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

        // get parent & node
        Node<T> p = getParent(value);
        Node<T> n = getNode(value);

        // 3 nodi:
        // quello da cancellare
        // il suo successore
        // e il figlio più a destra o a sinistra del successore

        // attaccare il successore al posto di quello da cancellare (utilizzare parent *p)
        // attaccare il figlio più dx o sx a i figli del nodo cancellato (parte difficile)

        if (value.compareTo(root.getValue()) < 0) {
            //left
            if (n.getLeft() != null) {
                Node<T> tmp1 = cercaMin(n.getValue()), tmp2 = cercaMin(tmp1.getValue());
                tmp2.setLeft(n.getLeft());
                getParent(tmp1.getValue()).setLeft(null);
                p.setLeft(tmp1);
            } else p.setLeft(n.getRight());
        } else {
            //right
            if (n.getRight() != null) {
                Node<T> tmp1 = cercaMin(n.getValue()), tmp2 = cercaMax(tmp1.getValue());
                tmp2.setRight(n.getRight());
                getParent(tmp1.getValue()).setLeft(null);
                p.setRight(tmp1);
            } else p.setRight(n.getLeft());
        }
    }

    public Node<T> getNode(T value) {
        if (root == null) return null;
        Stack<Node<T>> s = new Stack<>();
        // current
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

    public Node<T> getNodeIn(Node<T> root, T value) {
        if (root == null) return null;
        Stack<Node<T>> s = new Stack<>();
        // current
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
        return "(" + printR(root) + ")";
    }

    private String printR(Node<T> c) {
        if (c == null) return "";
        // recursive print
        return (c.getLeft() != null ? "(" + printR(c.getLeft()) + ")" : "") + c.getValue() + (c.getRight() != null ? "(" + printR(c.getRight()) + ")" : "");
    }

    public String printIterative() {
        if (root == null) return "";
        // biggest junk ever created
        // doesn't work, don't try
        String str = "";
        Stack<Node<T>> s = new Stack<>();
        Node<T> c = root;
        while (c != null || !s.isEmpty()) {
            while (c != null) {
                s.push(c);
                c = c.getLeft();
                // open when you go left
                str += "(";
            }
            c = s.pop();
            str += c.getValue().toString();
            // close subtree
            if (!s.isEmpty()) if (getNodeIn(s.peek().getLeft(), c.getValue()) == null) str += ")";
            // go right
            c = c.getRight();
            if (c == null) str += ")";
        }
        c = root.getRight();
        while (c != null) {
            c = c.getRight();
            str += ")";
        }
        return str;
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

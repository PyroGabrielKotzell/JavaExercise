import java.util.ArrayList;
import java.util.Stack;

public class BTree<T extends Comparable<T>> {
    private final ArrayList<T> nodi = new ArrayList<>();

    public int add(int index, T value) {
        if (nodi.contains(value)) return -1;
        try {
            if (nodi.get(index == 1 ? 0 : (index / 2 - 1)) == null) return -2;
        } catch (IndexOutOfBoundsException ignored) {
        }
        nodi.add(index, value);
        return 0;
    }

    public int search(T value) {
        return nodi.indexOf(value);
    }

    public int leftChild(T value) {
        return check(value, false);
    }

    public int rightChild(T value) {
        return check(value, true);
    }

    private int check(T value, boolean sw) {
        if (!nodi.contains(value)) return -2;
        int n;
        if (sw) n = 2;
        else n = 1;
        int in = 2 * nodi.indexOf(value) + n;
        return in < nodi.size() && nodi.contains(value) ? nodi.indexOf(nodi.get(in)) : -1;
    }

    public ArrayList<T> preOrderR(int index) {
        ArrayList<T> arr = new ArrayList<>();
        if (index < nodi.size())
            if (nodi.get(index) != null) {
                arr.add(nodi.get(index));
                arr.addAll(preOrderR(2 * index + 1));
                arr.addAll(preOrderR(2 * index + 2));
            }
        return arr;
    }

    public ArrayList<T> preOrder() {
        if (nodi.isEmpty()) return null;
        if (nodi.get(0) == null) return null;
        T f = nodi.get(0);
        ArrayList<T> arr = new ArrayList<>();
        Stack<T> s = new Stack<>();
        s.push(f);
        while (!s.isEmpty()) {
            arr.add(f = s.peek());
            s.pop();
            T tmp = rightChild(f) > 0 ? nodi.get(rightChild(f)) : null;
            if (tmp != null) s.push(tmp);
            tmp = leftChild(f) > 0 ? nodi.get(leftChild(f)) : null;
            if (tmp != null) s.push(tmp);
        }
        return arr;
    }

    public T get(int index) {
        return nodi.get(index);
    }
}

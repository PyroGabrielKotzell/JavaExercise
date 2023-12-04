import java.util.ArrayList;
import java.util.Stack;

public class BTree<T extends Comparable<T>> {
    private final ArrayList<T> nodi = new ArrayList<>();

    public int add(int index, T value) {
        if (nodi.contains(value)) return -1;
        try {
             if (nodi.get(index == 1 ? 0 : (index / 2 - 1)) == null) return -2;
        }catch (IndexOutOfBoundsException ignored){}
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
        try {
            return nodi.indexOf(nodi.get(2 * nodi.indexOf(value) + n));
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public ArrayList<T> preOrderR(int index) {
        ArrayList<T> arr = new ArrayList<>();
        try {
            if (nodi.get(index) != null) {
                arr.add(nodi.get(index));
                arr.addAll(preOrderR(2 * index + 1));
                arr.addAll(preOrderR(2 * index + 2));
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
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
            try {
                T tmp = nodi.get(nodi.indexOf(f) * 2 + 2);
                if (tmp != null) s.push(tmp);
            } catch (IndexOutOfBoundsException ignored) {}
            try {
                T tmp = nodi.get(nodi.indexOf(f) * 2 + 1);
                if (tmp != null) s.push(tmp);
            } catch (IndexOutOfBoundsException ignored) {}
        }
        return arr;
    }

    public T get(int index) {
        return nodi.get(index);
    }
}

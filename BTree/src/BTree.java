import java.util.ArrayList;

public class BTree<T extends Comparable<T>> {
    private final ArrayList<T> nodi = new ArrayList<>();

    public int add(int index, T value) {
        if (nodi.contains(value)) return -1;
        if (nodi.get(index / 2 - 1) != null) return -2;
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

    public ArrayList<T> preOrderR(int index){
        ArrayList<T> arr = new ArrayList<>();
        try {
            if (nodi.get(index) != null){
                arr.add(nodi.get(index));
                arr.addAll(preOrderR(2*index + 1));
                arr.addAll(preOrderR(2*index + 2));
            }
        }catch (IndexOutOfBoundsException e) {return null;}
        return arr;
    }

    public ArrayList<T> preOrder(){
        if()
    }

    public T get(int index) {
        return nodi.get(index);
    }
}

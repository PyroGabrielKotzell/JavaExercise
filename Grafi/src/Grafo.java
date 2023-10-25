import java.util.*;

public class Grafo<T> {
    private final HashMap<T, List<T>> hm = new HashMap<>();

    public void addVertex(T t) {
        if (!hm.containsKey(t)) hm.put(t, new LinkedList<>());
    }

    public void add(T t, T e, boolean bidirectional) {
        if (hm.containsKey(t)) hm.get(t).add(e);
        else {
            hm.put(t, new LinkedList<>());
            hm.get(t).add(e);
        }
        if (!hm.containsKey(e)) hm.put(e, new LinkedList<>());
        if (bidirectional) hm.get(e).add(t);
    }

    @SuppressWarnings("unchecked")
    private T[] keyset() {
        return (T[]) hm.keySet().toArray();
    }

    public int numArch() {
        T[] keyset = keyset();
        int arch = 0;
        for (int i = 0; i < hm.size(); i++) {
            arch = arch + hm.get(keyset[i]).size();
        }
        return arch;
    }

    public boolean isFullyConnected() {
        return numArch() >= (keyset().length * (keyset().length - 1));
    }

    public boolean isConnected() {
        for (int i = 0; i < hm.size(); i++) {
            boolean connected = false;
            for (int j = 0; j < keyset().length - 1; j++) {
                //if (hm.get(keyset[i])) return false;
            }
        }
        return true;
    }

    public T maxOrder() {
        return null;
    }

    public T minOrder() {
        return null;
    }

    public String toString() {
        return hm.toString();
    }

    public boolean exists(T t) {
        return hm.containsKey(t);
    }
}

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
    public boolean isFullyConnected() {
        T[] keyset = (T[]) hm.keySet().toArray();
        for (int i = 0; i < hm.size(); i++) {
            if (hm.get(keyset[i]).isEmpty()) return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean isConnected() {
        T[] keyset = (T[]) hm.keySet().toArray();
        for (int i = 0; i < hm.size(); i++) {
            boolean connected = false;
            for (int j = 0; j < keyset.length-1; j++) {
                if (hm.get(keyset[i])) return false;
            }
        }
        return true;
    }

    public String toString() {
        return hm.toString();
    }

    public boolean exists(T t) {
        return hm.containsKey(t);
    }
}

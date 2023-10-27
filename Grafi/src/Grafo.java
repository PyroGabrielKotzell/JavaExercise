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
        HashMap<T, T> tmp = new HashMap<>();
        for (T key : keyset()) {
            for (int i = 0; i < hm.get(key).size(); i++) {
                tmp.put(hm.get(key).get(i), null);
                tmp.put(key, null);
            }
        }
        return tmp.size() >= hm.size();
    }

    public T[] maxOrder() {
        return search(true);
    }

    public T[] minOrder() {
        return search(false);
    }

    private T[] search(boolean sw) {
        int n = hm.get(keyset()[0]).size();
        /*T[] tmp = ;
        for (T key : keyset()) {
            if(sw) if (hm.get(key).size() > n) ;
            else if (hm.get(key).size() < n)  ;
        }
        return tmp;*/
        return null;
    }

    public String toString() {
        return hm.toString();
    }

    public boolean exists(T t) {
        return hm.containsKey(t);
    }
}

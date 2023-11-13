import java.util.*;

/**
 * Graph class for creating and using graphs using HashMaps and Lists as data structures
 *
 * @param <T> the type of the graph
 */
public class Graph<T> {
    private final HashMap<T, List<T>> hm = new HashMap<>();
    private final HashMap<String, Float> edgesWeight = new HashMap<>();
    private final HashMap<T, T> previouses = new HashMap<>();

    /**
     * Adds one vertex without edges
     *
     * @param t the vertex
     */
    public void addVertex(T t) {
        if (!hm.containsKey(t)) hm.put(t, new LinkedList<>());
    }

    /**
     * Adds the vertexes and a connection from the first to the second and vice-versa if bidirectional
     *
     * @param t             the first vertex
     * @param e             the second vertex
     * @param bidirectional if the edge is both ways
     */
    public void add(T t, T e, boolean bidirectional) {
        if (hm.containsKey(t)) vertexEdges(t).add(e);
        else {
            hm.put(t, new LinkedList<>());
            vertexEdges(t).add(e);
        }
        if (!hm.containsKey(e)) hm.put(e, new LinkedList<>());
        if (bidirectional) vertexEdges(e).add(t);
    }

    /**
     * Adds weight to the vertexes connection
     *
     * @param t             the first vertex
     * @param e             the second vertex
     * @param weight        the weight value
     * @param bidirectional if the weight is equal both ways
     * @return boolean - if the weight got added or not
     */
    public boolean addWeight(T t, T e, float weight, boolean bidirectional) {
        if (hm.containsKey(t) && hm.containsKey(e)) {
            edgesWeight.put(t.toString() + e, weight);
            if (bidirectional) edgesWeight.put(e.toString() + t, weight);
            return true;
        } else return false;
    }

    /**
     * Adds the vertexes and edges with the weight to the graph
     *
     * @param t             the first vertex
     * @param e             the second vertex
     * @param weight        the weight value
     * @param bidirectional if the weight and edge is equal both ways
     */
    public void addWithWeight(T t, T e, float weight, boolean bidirectional) {
        add(t, e, bidirectional);
        addWeight(t, e, weight, bidirectional);
    }

    /**
     * Removes the vertex and the edges pointing to it
     *
     * @param t the vertex to remove
     */
    public void removeVertex(T t) {
        hm.remove(t);
        for (T key : keyset()) {
            vertexEdges(key).remove(t);
        }
    }

    /**
     * Keyset of the HashMap
     *
     * @return T[] - the keyset
     */
    @SuppressWarnings("unchecked")
    private T[] keyset() {
        return (T[]) hm.keySet().toArray();
    }

    /**
     * Vertexes of the Graph
     *
     * @return Object[] - as the type parameter is lost at runtime
     */
    public Object[] vertexes() {
        return hm.keySet().toArray();
    }

    /**
     * Number of edges, counts bidirectional as two
     *
     * @return int - number of edges
     */
    public int numEdges() {
        T[] keyset = keyset();
        int arch = 0;
        for (int i = 0; i < hm.size(); i++) {
            arch = arch + vertexEdges(keyset[i]).size();
        }
        return arch;
    }

    /**
     * Checks if the graph is fully connected
     *
     * @return boolean - if fully connected
     */
    public boolean isFullyConnected() {
        int l = keyset().length;
        return numEdges() >= (l * (l - 1));
    }

    /**
     * Checks if every vertex is connected
     *
     * @return boolean - if connected
     */
    @SuppressWarnings("all")
    public boolean isConnected() {
        HashMap<T, T> tmp = new HashMap<>();
        if (keyset().length != 0) {
            tmp.put(keyset()[0], null);
            for (T key : keyset()) {
                if (!tmp.containsKey(key)) {
                    for (int i = 0; i < tmp.size(); i++) {
                        if (vertexEdges(key).contains(tmp.keySet().toArray()[i])) {
                            tmp.put(vertexEdges(key).get(i), null);
                            tmp.put(key, null);
                        }
                    }
                } else {
                    tmp.put(key, null);
                    for (int i = 0; i < vertexEdges(key).size(); i++) {
                        tmp.put(vertexEdges(key).get(i), null);
                    }
                }
            }
            return tmp.size() >= hm.size();
        }
        return false;
    }

    /**
     * Gets the maximums of the vertexes
     *
     * @return T[] - the vertexes
     */
    public T[] maxOrder() {
        return search(true);
    }

    /**
     * Gets the minimums of the vertexes
     *
     * @return T[] - the vertexes
     */
    public T[] minOrder() {
        return search(false);
    }

    /**
     * Private search for the minimums or maximums of the vertexes
     *
     * @param sw switch for searching the min or max of the vertexes
     * @return T[] - an array of the vertexes
     */
    @SuppressWarnings("unchecked")
    private T[] search(boolean sw) {
        int n = vertexEdges(keyset()[0]).size();
        ArrayList<T> nodes = new ArrayList<>();
        for (T key : keyset()) {
            if (sw) {
                if (vertexEdges(key).size() > n) n = vertexEdges(key).size();
            } else if (vertexEdges(key).size() < n) n = vertexEdges(key).size();
        }
        for (T key : keyset()) if (vertexEdges(key).size() == n) nodes.add(key);
        return (T[]) nodes.toArray();
    }

    /**
     * Gets the edges of the vertex specified
     *
     * @param vertex the vertex
     * @return List - a list of the edges
     */
    public List<T> vertexEdges(T vertex) {
        return hm.get(vertex);
    }

    /**
     * Clones given graph by creating another one and adding all vertexes and edges
     *
     * @return Graph - the clone graph
     */
    @Override
    @SuppressWarnings("super")
    public Graph<T> clone() {
        Graph<T> newGraph = new Graph<>();
        for (T key : keyset()) {
            for (int i = 0; i < vertexEdges(key).size(); i++) {
                newGraph.add(key, vertexEdges(key).get(i), false);
            }
        }
        return newGraph;
    }

    /**
     * Initiative method for the search for cliques
     *
     * @param minLenght the minimum lenght of the cliques
     * @return Graph[] - the array of cliques
     */
    public Graph<T>[] getCliques(int minLenght) {
        ArrayList<Graph<T>> tmp = new ArrayList<>();
        tmp.add(this);
        return cliquesSearch(keyset(), tmp, this, minLenght);
    }

    /**
     * Recursive search for cliques
     *
     * @param keyset  the keyset of the current clique
     * @param cliques the list of cliques
     * @return Graph[] - the array of cliques
     */
    @SuppressWarnings("unchecked")
    private Graph<T>[] cliquesSearch(T[] keyset, ArrayList<Graph<T>> cliques, Graph<T> currentClique, int min) {
        if (currentClique.keyset().length == min && currentClique.isConnected()) {
            Graph<T>[] g = new Graph[1];
            g[0] = currentClique;
            return g;
        }
        for (T key : keyset) {
            Graph<T> gClone = currentClique.clone();
            gClone.removeVertex(key);
            if (gClone.isConnected() && !cliques.contains(gClone)) cliques.add(gClone);
            cliquesSearch(gClone.keyset(), cliques, gClone, min);
        }
        Graph<T>[] g = new Graph[cliques.toArray().length - 1];
        for (int i = 1; i < cliques.toArray().length; i++) {
            g[i - 1] = cliques.get(i);
        }
        return g;
    }

    public void calculateWeight(T t) {
        System.out.println("[Debug]: Using Dijkstra");
        Graph<T> cloned = this.clone();
        HashMap<T, Float> dist = new HashMap<>();
        for (T v : cloned.keyset()) {
            dist.put(v, Float.POSITIVE_INFINITY);
            previouses.put(v, null);
        }
        dist.put(t, (float) 0);
        T v = t;
        while (cloned.keyset().length != 0) {
            System.out.println("cycle");
            v = nodoMin(cloned, v);
            List<T> neighbours = cloned.vertexEdges(v);
            cloned.removeVertex(v);
            if (neighbours != null && v != null) {
                for (T n : neighbours) {
                    float weight = dist.get(v) + edgesWeight.get(v.toString() + n);
                    System.out.println(v + " " + n + ": " + weight);
                    if (weight < dist.get(n)) {
                        dist.put(n, weight);
                        edgesWeight.put(v.toString() + n, weight);
                        previouses.put(n, v);
                    }
                }
            }
        }
    }

    private T nodoMin(Graph<T> cloned, T v) {
        if (cloned.vertexEdges(v) == null) return null;
        T min = cloned.vertexEdges(v).get(0);
        for (T k : cloned.vertexEdges(v)) {
            Float w = edgesWeight.get(v.toString() + k);
            if (w != null && w < edgesWeight.get(v.toString() + min)) min = k;
        }
        return min;
    }

    public Float getWeight(T t, T e) {
        return edgesWeight.get(t.toString() + e);
    }

    public T getPrevious(T t) {
        return previouses.get(t);
    }

    /**
     * Check if graph equals another graph
     *
     * @param o the graph object
     * @return boolean - if the graphs have the same vertexes and edges
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass()) && ((Graph<?>) o).keyset().getClass().equals(keyset().getClass())) {
            Graph<T> oCasted = ((Graph<T>) o);
            boolean equals = true;
            for (T key : keyset()) {
                equals = Arrays.stream(oCasted.keyset()).toList().contains(key);
                if (equals) {
                    equals = vertexEdges(key).equals(oCasted.vertexEdges(key));
                } else break;
                if (!equals) break;
            }
            return equals;
        } else return false;
    }

    /**
     * Calls toString of HashMap
     *
     * @return String - the Strign visualization of the Graph
     */
    public String toString() {
        return hm.toString();
    }

    /**
     * Checks if vertex exist
     *
     * @param t the vertex
     * @return boolean - if the vertex exists
     */
    public boolean exists(T t) {
        return hm.containsKey(t);
    }
}

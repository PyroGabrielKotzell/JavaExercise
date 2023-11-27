import java.util.*;

/**
 * Graph class for creating and using graphs using HashMaps and Lists as data structures
 *
 * @param <T> the type of the graph
 */
public class Graph<T> {
    private final HashMap<T, List<T>> hm = new HashMap<>();
    private final HashMap<String, Float> edgesWeight = new HashMap<>();

    /**
     * Adds one vertex without links
     *
     * @param t the vertex
     */
    public void addVertex(T t) {
        if (!hm.containsKey(t)) hm.put(t, new LinkedList<>());
    }

    /**
     * Adds the nodes and a connection from the first to the second and vice-versa if bidirectional
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
     * Adds weight to the nodes connection
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
     * Adds the nodes and links with the weight to the graph
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
     * Removes the vertex and the links pointing to it
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
     * Number of links, counts bidirectional as two
     *
     * @return int - number of links
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
                            tmp.put(key, null);
                            for (int j = 0; j < vertexEdges(key).size(); j++) {
                                tmp.put(vertexEdges(key).get(j), null);
                            }
                            break;
                        }
                    }
                } else {
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
     * Gets the maximums of the nodes
     *
     * @return T[] - the nodes
     */
    public T[] maxOrder() {
        return search(true);
    }

    /**
     * Gets the minimums of the nodes
     *
     * @return T[] - the nodes
     */
    public T[] minOrder() {
        return search(false);
    }

    /**
     * Private search for the minimums or maximums of the nodes
     *
     * @param sw switch for searching the min or max of the nodes
     * @return T[] - an array of the nodes
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
     * Gets the links of the vertex specified
     *
     * @param vertex the vertex
     * @return List - a list of the links
     */
    public List<T> vertexEdges(T vertex) {
        return hm.get(vertex);
    }

    /**
     * Clones given graph by creating another one and adding all nodes and links
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
        if (currentClique.keyset().length == min) {
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

    /**
     * Using Dijkstra method, calculate the vertexes distance to a vertex source given
     *
     * @param t the vertex source
     * @return HashMap[] - the two hashmaps of distance (index 0) and previouses (index 1)
     */
    public HashMap[] dijkstra(T t) {
        HashMap<T, Float> dist = new HashMap<>();
        HashMap<T, T> previouses = new HashMap<>();
        ArrayList<T> Q = new ArrayList<>(List.of(keyset().clone()));
        for (T v : Q) {
            dist.put(v, Float.POSITIVE_INFINITY);
            previouses.put(v, null);
        }
        dist.put(t, 0f);
        while (!Q.isEmpty()) {
            T v = minDist(Q, dist);
            List<T> neighbours = vertexEdges(v);
            Q.remove(v);
            for (T n : neighbours) {
                if (Q.contains(n)) {
                    Float w = edgesWeight.get(v.toString() + n);
                    float weight = w == null ? 1.0f : dist.get(v) + w;
                    if (weight < dist.get(n)) {
                        dist.put(n, weight);
                        previouses.put(n, v);
                    }
                }
            }
        }
        return new HashMap[]{dist, previouses};
    }

    /**
     * Using Bellman Ford method, calculate the vertexes distance to a vertex source given
     *
     * @param t the vertex source
     * @return HashMap[] - the two hashmaps of distance (index 0) and previouses (index 1)
     */
    public HashMap[] bellmanFord(T t) {
        HashMap<T, Float> dist = new HashMap<>();
        HashMap<T, T> previouses = new HashMap<>();
        ArrayList<T> Q = new ArrayList<>(List.of(keyset().clone()));
        for (T v : Q) {
            dist.put(v, Float.POSITIVE_INFINITY);
            previouses.put(v, null);
        }
        dist.put(t, 0f);
        for (int f = 0; f < keyset().length - 1; f++) {
            for (T i : keyset()) {
                for (T j : vertexEdges(i)) {
                    if (dist.get(j) > dist.get(i) + getWeight(i, j)) {
                        dist.put(j, dist.get(i) + getWeight(i, j));
                        previouses.put(j, i);
                    }
                }
            }
        }

        for (T i : keyset()) {
            for (T j : vertexEdges(i)) {
                if (dist.get(j) > dist.get(i) + getWeight(i, j)) {
                    dist.put(j, Float.NEGATIVE_INFINITY);
                    previouses.put(j, null);
                }
            }
        }
        return new HashMap[]{dist, previouses};
    }

    /**
     * Using Kruskal, gives a new graph, it being the minimum spanning tree of this graph
     *
     * @return Graph - the graph representing the minimum spanning tree of this graph
     */
    public Graph<T> kruskal() {
        Graph<T> graph = new Graph<>();
        Float[] tmp = new Float[edgesWeight.size()];
        edgesWeight.values().toArray(tmp);
        Arrays.sort(tmp);

        LinkedList<T[]> edges = sortEdges(tmp);

        for (T key : keyset()) {
            graph.addVertex(key);
        }

        for (T[] i : edges) {
            boolean b = false;
            if (!graph.vertexEdges(i[0]).contains(i[1])) {
                graph.add(i[0], i[1], true);
            }
        }
        return graph;
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T[]> sortEdges(Float[] tmp) {
        LinkedList<T[]> edges = new LinkedList<>();
        int f = 0;
        while (edges.size() != tmp.length) {
            for (T i : keyset()) {
                for (T j : vertexEdges(i)) {
                    if (edgesWeight.get(i.toString() + j).equals(tmp[f])) {
                        edges.add((T[]) new Object[]{i, j});
                        f++;
                    }
                }
            }
        }
        return edges;
    }

    /**
     * Private method to get the vertex with minimum distance out of the ones given
     *
     * @param Q    the ArrayList of vertexes (given by calcDist())
     * @param dist the HashMap of distances (given by calcDist())
     * @return T - the vertex with minimum distance
     */
    private T minDist(ArrayList<T> Q, HashMap<T, Float> dist) {
        T min = Q.get(0);
        for (T k : Q) {
            Float w = dist.get(k);
            if (w != null && w < dist.get(min)) {
                min = k;
                break;
            }
        }
        return min;
    }

    /**
     * Get the weight of the edge from vertex t to vertex e
     *
     * @param t the first vertex
     * @param e the second vertex
     * @return Float - the value of the weight, 1 if there isn't weight or if the weight is 1
     */
    public Float getWeight(T t, T e) {
        Float w = edgesWeight.get(t.toString() + e);
        return w == null ? 1.0f : w;
    }

    /**
     * Check if graph equals another graph
     *
     * @param o the graph object
     * @return boolean - if the graphs have the same nodes and links
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass()) && ((Graph<?>) o).keyset().getClass().equals(keyset().getClass())) {
            Graph<T> oCasted = ((Graph<T>) o);
            boolean equals = true;
            for (T key : keyset()) {
                equals = Arrays.stream(oCasted.keyset()).toList().contains(key);
                if (!equals) break;
                equals = vertexEdges(key).equals(oCasted.vertexEdges(key));
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

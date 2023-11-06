import java.util.*;

/**
 * Graph class for creating and using graphs using HashMaps and Lists as data structures
 * @param <T> the type of the graph
 */
public class Graph<T> {
    private final HashMap<T, List<T>> hm = new HashMap<>();

    /**
     * Adds one vertex without edges
     * @param t the vertex
     */
    public void addVertex(T t) {
        if (!hm.containsKey(t)) hm.put(t, new LinkedList<>());
    }

    /**
     * Adds the vertexes and a connection from the first to the second and vice-versa if bidirectional
     * @param t the first vertex
     * @param e the second vertex
     * @param bidirectional if the edge is both ways
     */
    public void add(T t, T e, boolean bidirectional) {
        if (hm.containsKey(t)) hm.get(t).add(e);
        else {
            hm.put(t, new LinkedList<>());
            hm.get(t).add(e);
        }
        if (!hm.containsKey(e)) hm.put(e, new LinkedList<>());
        if (bidirectional) hm.get(e).add(t);
    }

    /**
     * Removes the vertex and the edges pointing to it
     * @param t the vertex to remove
     */
    public void removeVertex(T t) {
        hm.remove(t);
        for (T key : keyset()) {
            hm.get(key).remove(t);
        }
    }

    /**
     * Keyset of the HashMap
     * @return T[] - the keyset
     */
    @SuppressWarnings("unchecked")
    private T[] keyset() {
        return (T[]) hm.keySet().toArray();
    }

    /**
     * Number of edges, counts bidirectional as two
     * @return int - number of edges
     */
    public int numArch() {
        T[] keyset = keyset();
        int arch = 0;
        for (int i = 0; i < hm.size(); i++) {
            arch = arch + hm.get(keyset[i]).size();
        }
        return arch;
    }

    /**
     * Checks if the graph is fully connected
     * @return boolean - if fully connected
     */
    public boolean isFullyConnected() {
        return numArch() >= (keyset().length * (keyset().length - 1));
    }

    /**
     * Checks if every vertex is connected
     * @return boolean - if connected
     */
    public boolean isConnected() {
        HashMap<T, T> tmp = new HashMap<>();
        for (T key : keyset()) {
            if (!tmp.containsKey(key)){
                for (int i = 0; i < hm.get(key).size(); i++) {
                    
                }
            }
            tmp.put(key, null);
            for (int i = 0; i < hm.get(key).size(); i++) {
                tmp.put(hm.get(key).get(i), null);
            }
        }
        return tmp.size() >= hm.size();
    }

    /**
     * Initiative method for the search for cliques
     * @return Graph[] - the array of cliques
     */
    public Graph<T>[] searchCliques(){
        ArrayList<Graph<T>> tmp = new ArrayList<>();
        tmp.add(this);
        return recursiveCliqueSearch(keyset(), tmp);
    }

    /**
     * Recursive search for cliques
     * @param keyset the keyset of the current clique
     * @param cliques the list of cliques
     * @return Graph[] - the array of cliques
     */
    @SuppressWarnings("unchecked")
    private Graph<T>[] recursiveCliqueSearch(T[] keyset, ArrayList<Graph<T>> cliques){
        if (cliques.get(cliques.size()-1).keyset().length == 2){
            Graph<T>[] g = new Graph[1];
            g[0] = cliques.get(cliques.size()-1);
            return g;
        }
        for (T key : keyset) {
            if (cliques.get(cliques.size()-1).isConnected())
                cliques.add(recursiveCliqueSearch(cliques.get(0).keyset(), cliques)[0]);
        }
        return (Graph<T>[]) cliques.toArray();
    }

    /**
     * Gets the maximums of the vertexes
     * @return T[] - the vertexes
     */
    public T[] maxOrder() {
        return search(true);
    }

    /**
     * Gets the minimums of the vertexes
     * @return T[] - the vertexes
     */
    public T[] minOrder() {
        return search(false);
    }

    /**
     * Private search for the minimums or maximums of the vertexes
     * @param sw switch for searching the min or max of the vertexes
     * @return T[] - an array of the vertexes
     */
    @SuppressWarnings("unchecked")
    private T[] search(boolean sw) {
        int n = hm.get(keyset()[0]).size();
        ArrayList<T> nodes = new ArrayList<>();
        for (T key : keyset()) {
            if (sw) {
                if (hm.get(key).size() > n) n = hm.get(key).size();
            } else if (hm.get(key).size() < n) n = hm.get(key).size();
        }
        for (T key : keyset()) if (hm.get(key).size() == n) nodes.add(key);
        return (T[]) nodes.toArray();
    }

    /**
     * Gets the edges of the vertex specified
     * @param vertex the vertex
     * @return List - a list of the edges
     */
    public List<T> getVertexEdges(T vertex){
        return hm.get(vertex);
    }

    /**
     * Clones given graph by creating another one and adding all vertexes and edges
     * @param graph the graph
     * @return Graph - the clone graph
     */
    public Graph<T> clone(Graph<T> graph){
        Graph<T> newGraph = new Graph<>();
        for (T key: graph.keyset()) {
            for (int i = 0; i < graph.getVertexEdges(key).size(); i++) {
                newGraph.add(key, graph.getVertexEdges(key).get(i), false);
            }
        }
        return newGraph;
    }

    /**
     * Calls toString of HashMap
     * @return String - the Strign visualization of the Graph
     */
    public String toString() {
        return hm.toString();
    }

    /**
     * Checks if vertex exist
     * @param t the vertex
     * @return boolean - if the vertex exists
     */
    public boolean exists(T t) {
        return hm.containsKey(t);
    }
}

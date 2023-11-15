import java.util.List;

public class JsonGraph<T> {
    public vertex<T>[] nodes;
    public edge<T>[] links;

    @SuppressWarnings("unchecked")
    JsonGraph(Graph<T> graph) {
        nodes = new vertex[graph.vertexes().length];
        createVertexes(graph);
        links = new edge[graph.numEdges()];
        createEdges(graph);
    }

    @SuppressWarnings("unchecked")
    private void createVertexes(Graph<T> graph) {
        int f = 0;
        T[] ver = (T[]) graph.vertexes();
        for (T v : ver) {
            nodes[f] = new vertex<T>(v, graph.vertexEdges(v).size());
            f++;
        }
    }

    private void createEdges(Graph<T> graph) {
        int f = 0;
        for (vertex<T> V : nodes) {
            T v = V.getId();
            List<T> l = graph.vertexEdges(v);
            for (T t : l) {
                links[f] = new edge<>(v, t, graph.getWeight(v, t));
                f++;
            }
        }
    }

    public static class vertex<E> {
        private final E id;
        private final int group;

        vertex(E vertex, int group) {
            this.id = vertex;
            this.group = group;
        }

        public E getId() {
            return id;
        }

        public int getGroup() {
            return group;
        }
    }

    public static class edge<E> {
        private final E source, target;
        private final Float value;

        edge(E source, E target, Float weight) {
            this.source = source;
            this.target = target;
            this.value = weight;
        }

        public E getSource() {
            return source;
        }

        public E getTarget() {
            return target;
        }

        public Float getValue() {
            return value;
        }
    }
}

import java.util.List;

public class JsonGraph<T> {
    public T[] vertexes;
    public edge<T>[] edges;

    @SuppressWarnings("unchecked")
    JsonGraph(Graph<T> graph){
        vertexes = (T[]) graph.vertexes();
        edges = new edge[graph.numEdges()];
        createEdges(graph);
    }

    private void createEdges(Graph<T> graph) {
        int f = 0;
        for (int i = 0; i < vertexes.length; i++) {
            T v = vertexes[i];
            List<T> l = graph.vertexEdges(v);
            for (int j = 0; j < l.size(); j++) {
                edges[f] = new edge<>(v, l.get(j), graph.getWeight(v, l.get(j)));
                f++;
            }
        }
    }

    public static class edge<E>{
        private E source, target;
        private Float weight;
        edge(E source, E target, Float weight){
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public E getSource() {
            return source;
        }

        public E getTarget() {
            return target;
        }

        public Float getWeight() {
            return weight;
        }

        public void setSource(E source) {
            this.source = source;
        }

        public void setTarget(E target) {
            this.target = target;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }
    }
}
